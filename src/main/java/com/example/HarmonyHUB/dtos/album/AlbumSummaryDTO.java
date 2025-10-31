package com.example.HarmonyHUB.dtos.album;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumSummaryDTO {

    private Long id;
    private String title;
    private LocalDate releaseDate;
    private String language;
    private Long singerId;
    private String singerName;

}
