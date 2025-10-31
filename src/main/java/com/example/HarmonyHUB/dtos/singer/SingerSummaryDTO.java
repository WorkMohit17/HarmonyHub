package com.example.HarmonyHUB.dtos.singer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingerSummaryDTO {

    private Long id;
    private String name;
    private String genre;
}
