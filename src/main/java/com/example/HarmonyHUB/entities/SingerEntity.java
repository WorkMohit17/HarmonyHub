package com.example.HarmonyHUB.entities;

import jakarta.persistence.*;
import lombok.*;

import java.net.ProtocolFamily;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "singer_table")
public class SingerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String genre;

    @Column(name = "debut_date")
    private LocalDate debutDate;

    @Column(length = 50)
    private String nationality;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL
                , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AlbumEntity> albums = new ArrayList<>();

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL
                , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SongEntity> songs = new ArrayList<>();
}
