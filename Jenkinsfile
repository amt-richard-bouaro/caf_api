def appname = 'caf-api'
def deploy_group = 'java-upskilling-group'
def deploy_group_prod = 'pc-aug-backend-prod'
def s3_bucket = 'java-upskilling'
def s3_filename = 'caf-s3-bucket'

//Slack Notification Integration
def gitName = env.GIT_BRANCH
def jobName = env.JOB_NAME
def branchName = env.BRANCH_NAME
def main_branch = ['staging', 'develop']

// Environments Declaration
environment {
  jobName = env.JOB_NAME
  branchName = env.BRANCH_NAME
}

// Successful Build
def buildSuccess = [
  [text: "CAP API Build Successful on ${branchName}",
  fallback: "CAF API Build Successful on ${branchName}",
  color: "#00FF00"
  ]
]

// Failed Build
def buildError = [
  [text: "CAF API Build Failed on ${branchName}",
  fallback: "CAF API Build Failed on ${branchName}",
  color: "#FF0000"
  ]
]

pipeline {
  agent any
  tools {maven "Maven"}

  stages {
    stage('Install Dependencies') {
        steps {
            sh 'mvn clean install'
        }
    }

    stage('Build') {
        when {
        anyOf {
            branch 'staging';
            branch 'develop';
            }
        }
        steps {
            sh 'mvn package'
        }
    }

     stage('Prepare to Deploy') {
         when {
             anyOf {
                 branch 'staging';
                 branch 'develop';
             }
         }

steps {
                withCredentials([usernamePassword(credentialsId: 'aws-cred', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    script {
                        def gitsha = sh(script: 'git log -n1 --format=format:"%H"', returnStdout: true)
                                     s3_filename = "${s3_filename}-${gitsha}"
                                     sh """
                                         aws deploy push \
                                         --application-name ${appname} \
                                         --description "This is a revision for the ${appname}-${gitsha}" \
                                         --ignore-hidden-files \
                                         --s3-location s3://${s3_bucket}/${s3_filename}.zip \
                                         --source .
                                       """
                    }
                }
            }
     }
	 stage('Deploy to Development') {
         when {
             branch 'develop'
         }
         steps {

             withCredentials([usernamePassword(credentialsId: 'aws-cred', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                      script {
                            sh """
                                            aws deploy create-deployment \
                                            --application-name ${appname} \
                                            --deployment-config-name CodeDeployDefault.OneAtATime \
                                            --deployment-group-name ${deploy_group} \
                                            --file-exists-behavior OVERWRITE \
                                            --s3-location bucket=${s3_bucket},key=${s3_filename}.zip,bundleType=zip
                                          """
                          }
                      }
                  }
	 }

    stage('Deploy To Production') {
      when {
        branch 'staging'
      }
      steps {
                     withCredentials([usernamePassword(credentialsId: 'aws-cred', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                         script {
                            sh """
                                           aws deploy create-deployment \
                                           --application-name ${appname} \
                                           --deployment-config-name CodeDeployDefault.OneAtATime \
                                           --deployment-group-name ${deploy_group_prod} \
                                           --file-exists-behavior OVERWRITE \
                                           --s3-location bucket=${s3_bucket},key=${s3_filename}.zip,bundleType=zip
                                         """
                         }
                     }
                 }
    }
    stage('Clean WS') {
      steps {
        cleanWs()
      	}
   	}
 }
 post {
    always {
      echo 'Pipeline execution completed.'
      cleanWs()
    }
    success {
      script {
        if (BRANCH_NAME in main_branch) {
            slackSend(channel:"", attachments: buildSuccess)
          }
      }
        echo 'Pipeline execution successful!'
    }
    unstable {
      echo 'Pipeline execution unstable :/'
    }
    failure {
    script {
      if (BRANCH_NAME in main_branch) {
          slackSend(channel:"", attachments: buildError)
          }
    }
        echo 'Pipeline execution failed!'
    }
    changed {
      echo 'Things were different before...'
    	}
  }
}