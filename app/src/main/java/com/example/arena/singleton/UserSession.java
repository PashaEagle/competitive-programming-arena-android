package com.example.arena.singleton;

import com.example.arena.dto.user.UserDto;
import com.example.arena.entity.UserItem;

import java.util.ArrayList;
import java.util.List;

public final class UserSession {

    public static UserDto loggedUser;
    public static ArrayList<UserItem> allUsers;
}
