package com.example.HarmonyHUB.services;

import com.example.HarmonyHUB.config.mapper.SongMapper;
import com.example.HarmonyHUB.dtos.song.CreateSongRequestDTO;
import com.example.HarmonyHUB.dtos.song.UpdateSongRequestDTO;
import com.example.HarmonyHUB.dtos.song.SongResponseDTO;
import com.example.HarmonyHUB.dtos.song.SongSummaryDTO;
import com.example.HarmonyHUB.entities.AlbumEntity;
import com.example.HarmonyHUB.entities.SingerEntity;
import com.example.HarmonyHUB.entities.SongEntity;
import com.example.HarmonyHUB.repositories.AlbumRepository;
import com.example.HarmonyHUB.repositories.SingerRepository;
import com.example.HarmonyHUB.repositories.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SongService {

    private final SongMapper mapper;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final SingerRepository singerRepository;

    public SongService(SongMapper mapper, SongRepository songRepository,
                       AlbumRepository albumRepository, SingerRepository singerRepository) {
        this.mapper = mapper;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.singerRepository = singerRepository;
    }

    public SongResponseDTO createSong(CreateSongRequestDTO dto) {
        AlbumEntity album = albumRepository.findById(dto.getAlbumId())
                .orElseThrow(() -> new EntityNotFoundException("Album not found: " + dto.getAlbumId()));

        SingerEntity singer = singerRepository.findById(dto.getSingerId())
                .orElseThrow(() -> new EntityNotFoundException("Singer not found: " + dto.getSingerId()));

        SongEntity entity = mapper.toEntity(dto, album, singer);
        SongEntity saved = songRepository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    public SongResponseDTO updateSong(Long id, UpdateSongRequestDTO dto) {
        SongEntity song = songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song not found: " + id));

        if (dto.getTitle() != null) song.setTitle(dto.getTitle());
        if (dto.getLanguage() != null) song.setLanguage(dto.getLanguage());
        if (dto.getDuration() != null) song.setDuration(dto.getDuration());
        if (dto.getReleaseDate() != null) song.setReleaseDate(dto.getReleaseDate());

        return mapper.toResponseDTO(songRepository.save(song));
    }

    public Page<SongSummaryDTO> getAllSongs(
            int page, int size, String sortBy, String direction, Long singerId, Long albumId) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<SongEntity> songs;

        if (singerId != null && albumId != null) {
            songs = songRepository.findBySingerIdAndAlbumId(singerId, albumId, pageable);
        } else if (singerId != null) {
            songs = songRepository.findBySingerId(singerId, pageable);
        } else if (albumId != null) {
            songs = songRepository.findByAlbumId(albumId, pageable);
        } else {
            songs = songRepository.findAll(pageable);
        }

        return songs.map(mapper::toSummaryDTO);
    }

    public SongResponseDTO getSongById(Long id) {
        SongEntity song = songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song not found: " + id));
        return mapper.toResponseDTO(song);
    }

    public void deleteSong(Long id) {
        if (!songRepository.existsById(id)) {
            throw new EntityNotFoundException("Song not found: " + id);
        }
        songRepository.deleteById(id);
    }
}
