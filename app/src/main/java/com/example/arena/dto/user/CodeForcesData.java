package com.example.arena.dto.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeForcesData {

    private String username;
    private String rank;
    private String maxRank;
    private Integer rating;
    private Integer maxRating;
    private Long lastOnlineAt;
    private Long registeredAt;
    private Integer submissionsCount;
    List<CodeForcesSubmission> last30Submissions;
}
