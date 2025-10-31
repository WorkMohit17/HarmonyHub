package com.example.HarmonyHUB.dtos.album;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAlbumRequestDTO {

    private String title;
    private LocalDate releaseDate;
    private String language;

}
