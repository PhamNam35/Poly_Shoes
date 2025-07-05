package com.shoestorebackend.service;

import com.shoestorebackend.dto.CreateSanPhamRequest;
import com.shoestorebackend.dto.SanPhamDTO;
import com.shoestorebackend.entity.SanPham;

import java.util.List;

public interface SanPhamService {
    List<SanPhamDTO> getAllSanPhamActive();
    List<SanPham> getAllSanPham();

    SanPham createSanPham(CreateSanPhamRequest request);
    SanPham updateSanPham(Integer id, CreateSanPhamRequest request);
    SanPhamDTO getSanPhamById(Integer id);

    void deleteSanPham(Integer id);
}
