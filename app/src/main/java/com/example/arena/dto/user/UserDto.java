package com.example.arena.dto.user;

import com.example.arena.constant.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String fullName;
    private Integer age;
    private String group;
    private Role role;
    private Long lastActiveAt;
    private String codeForcesUsername;
}
