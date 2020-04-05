package com.example.arena.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeWarsData {

    private String username;
    private String fullname;
    private Integer honor;
    private String clan;
    private Long leaderboardPosition;
    private List<String> skills;
    private Integer rank;
    private String rankName;
    private String rankColor;
    private Integer rankScore;
    private Integer submissionsCount;
    private List<CodeWarsSubmission> submissions;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CodeWarsSubmission {

        private String id;
        private String name;
        private String slug;
        private List<String> completedLanguages;
        private Long completedAt;
    }

}
