package com.example.HarmonyHUB.repositories;

import com.example.HarmonyHUB.entities.SingerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository<SingerEntity, Long> {
    Page<SingerEntity> findByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);
}