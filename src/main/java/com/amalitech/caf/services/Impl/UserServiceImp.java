package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.response.UsersResponseDto;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.repositories.UserRepository;
import com.amalitech.caf.services.UserService;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp implements UserService {
    
    private final UserRepository userRepository;
    
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<UsersResponseDto> findAllusers() {
        
        List<UserEntity> users = userRepository.findAll();
        
        return users.stream()
                    .map(user -> UsersResponseDto.builder()
                                                 .id(user.getId())
                                                 .firstName(user.getFirstName())
                                                 .lastName(user.getLastName())
                                                 .email(user.getEmail())
                                                 .role(user.getRole())
                                                 .build())
                    .toList();
        
        
    }
    
    
}
