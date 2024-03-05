package com.amalitech.caf.configs.openAPI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Richard Bouaro", email = "richard.bouaro@amalitech.com", url = "https://amalitech.org"),

        description = "OpenAPI documentation for CAF Tournaments API", title = "CAF API",
        version = "1.0.0", license = @License(name = "AMALITECH", url = "https://amalitech.org")

), servers = {@Server(description = "Development Server", url = "http://localhost:7007"),
        @Server(description = "Production Server", url = "http://localhost:7007")

},

        security = {@SecurityRequirement(name = "BearerAuth"),}


)
@SecurityScheme(name = "BearerAuth", description = "", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)

public class OpenAPIConfig {
//    @Override
//    public void addViewControllers(final ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/", "/swagger-ui/index.html");
//        // use setStatusCode(HttpStatus.XYZ) for any custom status code if required, e.g. MOVED_PERMANENTLY
//        registry.addRedirectViewController("/swagger-ui", "/swagger-ui/index.html");
//        // any other alias
//    }
}
