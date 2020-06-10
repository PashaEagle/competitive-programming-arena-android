package com.example.arena.transformer;

import com.example.arena.R;
import com.example.arena.dto.user.UserDto;
import com.example.arena.entity.UserItem;

public class UserTransformer {

    public static UserItem fromUserDtoToUserItem(UserDto userDto, int place, String value) {

        return new UserItem(R.drawable.ic_person, place, userDto.getUsername(), userDto.getGlobalData().getTotalAmountOfSubmissions(), userDto.getGlobalData().getSubmissionsLastMonth(), value);
    }
}
