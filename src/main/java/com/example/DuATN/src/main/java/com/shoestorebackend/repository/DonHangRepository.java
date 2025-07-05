package com.shoestorebackend.repository;

import com.shoestorebackend.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {

    // Lấy danh sách đơn hàng theo ID khách hàng
    List<DonHang> findByKhachHang_KhachHangId(Integer khachHangId);
}
