package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.response.UsersResponseDto;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.repositories.UserRepository;
import com.amalitech.caf.services.UserService;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    String[] result = {"John", "Kojo"};

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

    public UsersResponseDto findUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return UsersResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
