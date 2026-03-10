package com.dilip.posSystem.controller;

import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.mapper.UserMapper;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.UserDto;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
       User user =  userService.getUserFromJwtToken(jwt);
       return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException {
        User user =  userService.getUserById(id);
        if(user==null){
            throw new UserException("User not found");
        }
        return ResponseEntity.ok(UserMapper.toDTO(user));

    }

}
