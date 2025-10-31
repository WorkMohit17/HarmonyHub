package com.example.HarmonyHUB.dtos.song;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongResponseDTO {

    private Long id;
    private String title;
    private LocalDate releaseDate;
    private BigDecimal duration;
    private String language;

    private Long albumId;
    private String albumTitle;

    private Long singerId;
    private String singerName;
}
