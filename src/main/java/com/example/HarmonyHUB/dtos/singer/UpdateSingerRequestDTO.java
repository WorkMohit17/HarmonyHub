package com.example.HarmonyHUB.dtos.singer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSingerRequestDTO {

    private String name;
    private String genre;
    private LocalDate debutDate;
    private String nationality;

}
