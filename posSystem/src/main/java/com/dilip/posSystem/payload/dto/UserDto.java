package com.dilip.posSystem.payload.dto;

import com.dilip.posSystem.domain.UserRole;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class UserDto {


        private Long id;
        private String fullName;
        private String email;
        private String password;
        private String phone;
        private UserRole role;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime lastLogin;

}


