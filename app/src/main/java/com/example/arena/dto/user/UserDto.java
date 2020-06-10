package com.example.arena.dto.user;

import com.example.arena.constant.Role;
import com.example.arena.entity.UserItem;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private String username;
    private String password;
    private String fullName;
    private Integer age;
    private String group;
    private Role role;
    private Long lastActiveAt;

    private GlobalData globalData;

    private String codeForcesUsername;
    private CodeForcesData codeForcesData;

    private String codeWarsUsername;
    private CodeWarsData codeWarsData;

    public static final Comparator<UserDto> BY_TOTAL_SUBMISSIONS = new Comparator<UserDto>() {
        @Override
        public int compare(UserDto o1, UserDto o2) {
            return o2.getGlobalData().getTotalAmountOfSubmissions().compareTo(o1.getGlobalData().getTotalAmountOfSubmissions());
        }
    };

    public static final Comparator<UserDto> BY_SUBMISSIONS_THIS_MONTH = new Comparator<UserDto>() {
        @Override
        public int compare(UserDto o1, UserDto o2) {
            return o2.getGlobalData().getSubmissionsLastMonth().compareTo(o1.getGlobalData().getSubmissionsLastMonth());
        }
    };
}
