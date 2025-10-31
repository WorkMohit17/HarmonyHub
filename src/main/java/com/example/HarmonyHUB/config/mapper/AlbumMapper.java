package com.example.HarmonyHUB.config.mapper;

import com.example.HarmonyHUB.dtos.album.*;
import com.example.HarmonyHUB.dtos.song.SongSummaryDTO;
import com.example.HarmonyHUB.entities.AlbumEntity;
import com.example.HarmonyHUB.entities.SingerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumMapper {

    private final ModelMapper mapper;

    public AlbumMapper(ModelMapper mapper) {
        this.mapper = mapper;

        mapper.typeMap(AlbumEntity.class, AlbumResponseDTO.class).addMappings(m -> {
            m.map(src -> src.getSinger().getId(), AlbumResponseDTO::setSingerId);
            m.map(src -> src.getSinger().getName(), AlbumResponseDTO::setSingerName);
        });

        mapper.typeMap(AlbumEntity.class, AlbumSummaryDTO.class).addMappings(m -> {
            m.map(src -> src.getSinger().getId(), AlbumSummaryDTO::setSingerId);
            m.map(src -> src.getSinger().getName(), AlbumSummaryDTO::setSingerName);
        });
    }

    public AlbumEntity toEntity(CreateAlbumRequestDTO dto, SingerEntity singer) {
        return AlbumEntity.builder()
                .title(dto.getTitle())
                .releaseDate(dto.getReleaseDate())
                .language(dto.getLanguage())
                .singer(singer)
                .build();
    }

    public AlbumEntity updateEntity(AlbumEntity entity, UpdateAlbumRequestDTO dto) {
        if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if (dto.getReleaseDate() != null) entity.setReleaseDate(dto.getReleaseDate());
        if (dto.getLanguage() != null) entity.setLanguage(dto.getLanguage());

        return entity;
    }

    public AlbumResponseDTO toResponseDTO(AlbumEntity entity) {
        AlbumResponseDTO dto = mapper.map(entity, AlbumResponseDTO.class);

        if (entity.getSongs() != null) {
            List<SongSummaryDTO> songDTOs = entity.getSongs().stream()
                    .map(song -> mapper.map(song, SongSummaryDTO.class))
                    .collect(Collectors.toList());
            dto.setSongs(songDTOs);
        }

        return dto;
    }

    public AlbumSummaryDTO toSummaryDTO(AlbumEntity entity) {
        return mapper.map(entity, AlbumSummaryDTO.class);
    }
}

