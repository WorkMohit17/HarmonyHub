package com.example.HarmonyHUB.dtos.album;

import com.example.HarmonyHUB.dtos.song.SongSummaryDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResponseDTO {

    private String title;
    private LocalDate releaseDate;
    private String language;

    private Long singerId;
    private Long singerName;

    private List<SongSummaryDTO> songs;
}
