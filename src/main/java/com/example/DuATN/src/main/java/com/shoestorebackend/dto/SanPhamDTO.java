package com.shoestorebackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SanPhamDTO {
    private Integer sanPhamId;
    private String tenSanPham;
    private String moTa;
    private String chatLieu;
    private BigDecimal giaBan;
    private String duongDanAnh; // ảnh chính để hiển thị
}
