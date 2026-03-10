package com.dilip.posSystem.controller;

import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.payload.dto.UserDto;
import com.dilip.posSystem.payload.response.AuthResponse;
import com.dilip.posSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // http://localhost:5000/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(authService.signup(userDto));
    }

    // http://localhost:5000/auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(authService.login(userDto));
    }

}
