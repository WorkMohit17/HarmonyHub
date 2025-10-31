package com.example.HarmonyHUB.dtos.song;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSongRequestDTO {

    private String title;
    private LocalDate releaseDate;
    private BigDecimal duration;
    private String language;

    private Long albumId;
    private Long singerId;
}
