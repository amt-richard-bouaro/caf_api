package com.amalitech.caf.services;

import com.amalitech.caf.dtos.response.UsersResponseDto;

import java.util.List;

public interface UserService {
    List<UsersResponseDto> findAllusers();
}
