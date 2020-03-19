package com.example.arena.integration.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterHttpRequest {

    private String username;
    private String password;
    private String fullName;
    private Integer age;
    private String group;
}
