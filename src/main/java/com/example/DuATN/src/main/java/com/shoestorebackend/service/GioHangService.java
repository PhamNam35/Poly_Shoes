package com.shoestorebackend.service;

import com.shoestorebackend.entity.ChiTietGioHang;

import java.util.List;

public interface GioHangService {
    List<ChiTietGioHang> getGioHangByKhach(Integer khachHangId);
    ChiTietGioHang themVaoGio(Integer khachHangId, Integer sanPhamId, String phanLoai);
    void xoaKhoiGio(Integer chiTietGioHangId);
}
