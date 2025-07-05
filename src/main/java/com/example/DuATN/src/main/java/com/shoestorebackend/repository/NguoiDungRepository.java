package com.shoestorebackend.repository;

import com.shoestorebackend.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    boolean existsByTenDangNhap(String tenDangNhap);
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
}

