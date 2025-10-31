package com.example.HarmonyHUB.repositories;

import com.example.HarmonyHUB.entities.SongEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
    Page<SongEntity> findBySingerIdAndAlbumId(Long singerId, Long albumId, Pageable pageable);

    Page<SongEntity> findBySingerId(Long singerId, Pageable pageable);

    Page<SongEntity> findByAlbumId(Long albumId, Pageable pageable);
}