package com.amalitech.caf.services;

import com.amalitech.caf.dtos.user.UsersResponseDto;

import java.util.List;

public interface UserService {
    List<UsersResponseDto> findAllusers();

    UsersResponseDto findUser(String email);
}
