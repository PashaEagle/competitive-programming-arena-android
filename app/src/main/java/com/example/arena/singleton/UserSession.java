package com.example.arena.singleton;

import com.example.arena.dto.user.UserDto;
import com.example.arena.entity.UserItem;

import java.util.ArrayList;

public final class UserSession {

    public static UserDto loggedUser;

    public static ArrayList<UserDto> allUsers;
    public static ArrayList<UserItem> allUserItems;

    public static String currentUserUsername;
    public static UserDto currentUser;

    static {
        allUsers = new ArrayList<>();
        allUserItems = new ArrayList<>();
    }
}
