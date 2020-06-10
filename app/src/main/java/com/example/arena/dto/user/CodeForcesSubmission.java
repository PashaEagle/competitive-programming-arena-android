package com.example.arena.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeForcesSubmission {

    Long submittedAt;
    String problemName;
    Integer problemRating;
    String verdict;
    Integer passedTestsCount;
    String programmingLanguage;
}
