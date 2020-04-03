package com.example.arena.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserItem {

    private int imageResource;
    private int place;
    private String username;
    private String lastSeenTime;
    private String group;
    private String value;
}
