package com.example.HarmonyHUB.dtos.song;

import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongSummaryDTO {
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private BigDecimal duration;
    private String language;
}
