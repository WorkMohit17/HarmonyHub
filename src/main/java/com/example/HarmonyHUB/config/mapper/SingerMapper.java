package com.example.HarmonyHUB.config.mapper;

import com.example.HarmonyHUB.dtos.album.AlbumSummaryDTO;
import com.example.HarmonyHUB.dtos.singer.*;
import com.example.HarmonyHUB.dtos.song.SongSummaryDTO;
import com.example.HarmonyHUB.entities.SingerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SingerMapper {

    private final ModelMapper mapper;

    public SingerMapper(ModelMapper mapper) {
        this.mapper = mapper;

        mapper.typeMap(SingerEntity.class, SingerSummaryDTO.class)
                .addMappings(m -> {
                    m.map(SingerEntity::getId, SingerSummaryDTO::setId);
                    m.map(SingerEntity::getName, SingerSummaryDTO::setName);
                    m.map(SingerEntity::getGenre, SingerSummaryDTO::setGenre);
                });

        mapper.typeMap(SingerEntity.class, SingerResponseDTO.class)
                .addMappings(m -> {
                    m.map(SingerEntity::getId, SingerResponseDTO::setId);
                    m.map(SingerEntity::getName, SingerResponseDTO::setName);
                    m.map(SingerEntity::getGenre, SingerResponseDTO::setGenre);
                    m.map(SingerEntity::getDebutDate, SingerResponseDTO::setDebutDate);
                    m.map(SingerEntity::getNationality, SingerResponseDTO::setNationality);
                });
    }

    public SingerEntity toEntity(CreateSingerRequestDTO dto) {
        return SingerEntity.builder()
                .name(dto.getName())
                .genre(dto.getGenre())
                .debutDate(dto.getDebutDate())
                .nationality(dto.getNationality())
                .build();
    }

    public SingerEntity updateEntity(SingerEntity entity, UpdateSingerRequestDTO dto) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getGenre() != null) entity.setGenre(dto.getGenre());
        if (dto.getDebutDate() != null) entity.setDebutDate(dto.getDebutDate());
        if (dto.getNationality() != null) entity.setNationality(dto.getNationality());

        return entity;
    }

    public SingerResponseDTO toResponseDTO(SingerEntity entity) {
        SingerResponseDTO dto = mapper.map(entity, SingerResponseDTO.class);

        if (entity.getAlbums() != null) {
            List<AlbumSummaryDTO> albumDTOs = entity.getAlbums().stream()
                    .map(album -> mapper.map(album, AlbumSummaryDTO.class))
                    .collect(Collectors.toList());
            dto.setAlbums(albumDTOs);
        }

        if (entity.getSongs() != null) {
            List<SongSummaryDTO> songDTOs = entity.getSongs().stream()
                    .map(song -> mapper.map(song, SongSummaryDTO.class))
                    .collect(Collectors.toList());
            dto.setSongs(songDTOs);
        }

        return dto;
    }

    public SingerSummaryDTO toSummaryDTO(SingerEntity entity) {
        return mapper.map(entity, SingerSummaryDTO.class);
    }
}
