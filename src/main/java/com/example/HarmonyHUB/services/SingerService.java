package com.example.HarmonyHUB.services;

import com.example.HarmonyHUB.config.mapper.SingerMapper;
import com.example.HarmonyHUB.dtos.singer.CreateSingerRequestDTO;
import com.example.HarmonyHUB.dtos.singer.UpdateSingerRequestDTO;
import com.example.HarmonyHUB.dtos.singer.SingerResponseDTO;
import com.example.HarmonyHUB.dtos.singer.SingerSummaryDTO;
import com.example.HarmonyHUB.entities.SingerEntity;
import com.example.HarmonyHUB.repositories.SingerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SingerService {

    private final SingerMapper mapper;
    private final SingerRepository singerRepository;

    public SingerService(SingerMapper mapper, SingerRepository singerRepository) {
        this.mapper = mapper;
        this.singerRepository = singerRepository;
    }

    public SingerResponseDTO createSinger(CreateSingerRequestDTO dto) {
        SingerEntity entity = mapper.toEntity(dto);
        SingerEntity saved = singerRepository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    public SingerResponseDTO updateSinger(Long id, UpdateSingerRequestDTO dto) {
        SingerEntity singer = singerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Singer not found: " + id));

        if (dto.getName() != null) singer.setName(dto.getName());
        if (dto.getGenre() != null) singer.setGenre(dto.getGenre());
        if (dto.getDebutDate() != null) singer.setDebutDate(dto.getDebutDate());
        if (dto.getNationality() != null) singer.setNationality(dto.getNationality());

        return mapper.toResponseDTO(singerRepository.save(singer));
    }

    public Page<SingerSummaryDTO> getAllSingers(
            int page, int size, String sortBy, String direction, String searchKeyword) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<SingerEntity> singers = (searchKeyword != null && !searchKeyword.isBlank())
                ? singerRepository.findByNameContainingIgnoreCase(searchKeyword, pageable)
                : singerRepository.findAll(pageable);

        return singers.map(mapper::toSummaryDTO);
    }

    public SingerResponseDTO getSingerById(Long id) {
        SingerEntity singer = singerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Singer not found: " + id));
        return mapper.toResponseDTO(singer);
    }

    public void deleteSinger(Long id) {
        if (!singerRepository.existsById(id)) {
            throw new EntityNotFoundException("Singer not found: " + id);
        }
        singerRepository.deleteById(id);
    }
}
