package com.shoestorebackend.repository;

import com.shoestorebackend.entity.KieuGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuGiayRepository extends JpaRepository<KieuGiay, Integer> {
}
