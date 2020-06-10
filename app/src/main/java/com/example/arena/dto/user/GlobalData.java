package com.example.arena.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalData {

    private Integer totalAmountOfSubmissions;
    private Integer submissionsLastMonth;
}
