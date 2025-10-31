package com.example.HarmonyHUB.config.mapper;

import com.example.HarmonyHUB.dtos.song.*;
import com.example.HarmonyHUB.entities.AlbumEntity;
import com.example.HarmonyHUB.entities.SingerEntity;
import com.example.HarmonyHUB.entities.SongEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

    private final ModelMapper mapper;

    public SongMapper(ModelMapper mapper) {
        this.mapper = mapper;

        mapper.typeMap(SongEntity.class, SongSummaryDTO.class)
                .addMappings(m -> {
                    m.map(SongEntity::getId, SongSummaryDTO::setId);
                    m.map(SongEntity::getTitle, SongSummaryDTO::setTitle);
                    m.map(SongEntity::getReleaseDate, SongSummaryDTO::setReleaseDate);
                    m.map(SongEntity::getDuration, SongSummaryDTO::setDuration);
                    m.map(SongEntity::getLanguage, SongSummaryDTO::setLanguage);
                });

        mapper.typeMap(SongEntity.class, SongResponseDTO.class)
                .addMappings(m -> {
                    m.map(SongEntity::getId, SongResponseDTO::setId);
                    m.map(SongEntity::getTitle, SongResponseDTO::setTitle);
                    m.map(SongEntity::getReleaseDate, SongResponseDTO::setReleaseDate);
                    m.map(SongEntity::getDuration, SongResponseDTO::setDuration);
                    m.map(SongEntity::getLanguage, SongResponseDTO::setLanguage);
                    m.map(src -> src.getAlbum() != null ? src.getAlbum().getId() : null, SongResponseDTO::setAlbumId);
                    m.map(src -> src.getAlbum() != null ? src.getAlbum().getTitle() : null, SongResponseDTO::setAlbumTitle);
                    m.map(src -> src.getSinger() != null ? src.getSinger().getId() : null, SongResponseDTO::setSingerId);
                    m.map(src -> src.getSinger() != null ? src.getSinger().getName() : null, SongResponseDTO::setSingerName);
                });
    }

    public SongEntity toEntity(CreateSongRequestDTO dto, AlbumEntity album, SingerEntity singer) {
        return SongEntity.builder()
                .title(dto.getTitle())
                .releaseDate(dto.getReleaseDate())
                .duration(dto.getDuration())
                .language(dto.getLanguage())
                .album(album)
                .singer(singer)
                .build();
    }

    public SongEntity updateEntity(SongEntity entity, UpdateSongRequestDTO dto) {
        if (dto.getTitle() != null)
            entity.setTitle(dto.getTitle());
        if (dto.getReleaseDate() != null)
            entity.setReleaseDate(dto.getReleaseDate());
        if (dto.getDuration() != null)
            entity.setDuration(dto.getDuration());
        if (dto.getLanguage() != null)
            entity.setLanguage(dto.getLanguage());

        return entity;
    }

    public SongResponseDTO toResponseDTO(SongEntity entity) {
        return mapper.map(entity, SongResponseDTO.class);
    }

    public SongSummaryDTO toSummaryDTO(SongEntity entity) {
        return mapper.map(entity, SongSummaryDTO.class);
    }
}
