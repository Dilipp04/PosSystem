package com.dilip.posSystem.payload.response;

import com.dilip.posSystem.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDto user;
}
