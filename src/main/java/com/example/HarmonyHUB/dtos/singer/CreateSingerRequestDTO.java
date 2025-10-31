package com.example.HarmonyHUB.dtos.singer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSingerRequestDTO {

    private String name;
    private String genre;
    private LocalDate debutDate;
    private String nationality;
}
