package com.example.HarmonyHUB.dtos.album;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAlbumRequestDTO {

    private String title;
    private LocalDate releaseDate;
    private String language;

    private Long singerId;

}
