package com.example.arena.entity;

import java.util.Comparator;

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
    private Integer submissionsCount;
    private Integer submissionsThisMonth;
    private String value;

    public static final Comparator<UserItem> BY_TOTAL_SUBMISSIONS = new Comparator<UserItem>() {
        @Override
        public int compare(UserItem o1, UserItem o2) {
            return o2.getSubmissionsCount().compareTo(o1.getSubmissionsCount());
        }
    };

    public static final Comparator<UserItem> BY_SUBMISSIONS_THIS_MONTH = new Comparator<UserItem>() {
        @Override
        public int compare(UserItem o1, UserItem o2) {
            return o2.getSubmissionsThisMonth().compareTo(o1.getSubmissionsThisMonth());
        }
    };
}
