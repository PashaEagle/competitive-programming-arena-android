package com.example.arena.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeforcesSubmission {

    LocalDateTime submittedAt;
    String problemName;
    Integer problemRating;
    String verdict;
    Integer passedTestsCount;
    String programmingLanguage;
}
