package com.example.HarmonyHUB.services;

import com.example.HarmonyHUB.config.mapper.AlbumMapper;
import com.example.HarmonyHUB.dtos.album.CreateAlbumRequestDTO;
import com.example.HarmonyHUB.dtos.album.UpdateAlbumRequestDTO;
import com.example.HarmonyHUB.dtos.album.AlbumResponseDTO;
import com.example.HarmonyHUB.dtos.album.AlbumSummaryDTO;
import com.example.HarmonyHUB.entities.AlbumEntity;
import com.example.HarmonyHUB.entities.SingerEntity;
import com.example.HarmonyHUB.repositories.AlbumRepository;
import com.example.HarmonyHUB.repositories.SingerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlbumService {

    private final AlbumMapper mapper;
    private final AlbumRepository albumRepository;
    private final SingerRepository singerRepository;

    public AlbumService(AlbumMapper mapper, AlbumRepository albumRepository, SingerRepository singerRepository) {
        this.mapper = mapper;
        this.albumRepository = albumRepository;
        this.singerRepository = singerRepository;
    }

    public AlbumResponseDTO createAlbum(CreateAlbumRequestDTO dto) {
        SingerEntity singer = singerRepository.findById(dto.getSingerId())
                .orElseThrow(() -> new EntityNotFoundException("Singer not found: " + dto.getSingerId()));

        AlbumEntity entity = mapper.toEntity(dto, singer);
        AlbumEntity saved = albumRepository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    public AlbumResponseDTO updateAlbum(Long id, UpdateAlbumRequestDTO dto) {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Album not found: " + id));

        if (dto.getTitle() != null)
            album.setTitle(dto.getTitle());
        if (dto.getReleaseDate() != null)
            album.setReleaseDate(dto.getReleaseDate());
        if (dto.getLanguage() != null)
            album.setLanguage(dto.getLanguage());

        return mapper.toResponseDTO(albumRepository.save(album));
    }

    public Page<AlbumSummaryDTO> getAllAlbums(
            int page, int size, String sortBy, String direction, Long singerId) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<AlbumEntity> albums = (singerId != null)
                ? albumRepository.findBySingerId(singerId, pageable)
                : albumRepository.findAll(pageable);

        return albums.map(mapper::toSummaryDTO);
    }

    public AlbumResponseDTO getAlbumById(Long id) {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Album not found: " + id));
        return mapper.toResponseDTO(album);
    }

    public void deleteAlbum(Long id) {
        if (!albumRepository.existsById(id)) {
            throw new EntityNotFoundException("Album not found: " + id);
        }
        albumRepository.deleteById(id);
    }
}
