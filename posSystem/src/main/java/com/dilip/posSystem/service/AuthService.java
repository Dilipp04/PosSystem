package com.dilip.posSystem.service;

import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.payload.dto.UserDto;
import com.dilip.posSystem.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}
