package com.example.HarmonyHUB.dtos.singer;

import com.example.HarmonyHUB.dtos.album.AlbumSummaryDTO;
import com.example.HarmonyHUB.dtos.song.SongSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingerResponseDTO {

    private Long id;
    private String name;
    private String genre;
    private LocalDate debutDate;
    private String nationality;

    private List<AlbumSummaryDTO> albums;
    private List<SongSummaryDTO> songs;
}
