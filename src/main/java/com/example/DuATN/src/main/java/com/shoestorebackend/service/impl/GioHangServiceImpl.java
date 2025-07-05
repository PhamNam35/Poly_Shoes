package com.shoestorebackend.service.impl;

import com.shoestorebackend.entity.ChiTietGioHang;
import com.shoestorebackend.entity.KhachHang;
import com.shoestorebackend.entity.SanPham;
import com.shoestorebackend.repository.ChiTietGioHangRepository;
import com.shoestorebackend.repository.KhachHangRepository;
import com.shoestorebackend.repository.SanPhamRepository;
import com.shoestorebackend.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private ChiTietGioHangRepository gioHangRepo;

    @Autowired
    private KhachHangRepository khachHangRepo;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Override
    public List<ChiTietGioHang> getGioHangByKhach(Integer khachHangId) {
        return gioHangRepo.findByKhachHang_KhachHangId(khachHangId);
    }

    @Override
    public ChiTietGioHang themVaoGio(Integer khachHangId, Integer sanPhamId, String phanLoai) {
        KhachHang kh = khachHangRepo.findById(khachHangId).orElseThrow();
        SanPham sp = sanPhamRepo.findById(sanPhamId).orElseThrow();

        ChiTietGioHang ctgh = new ChiTietGioHang();
        ctgh.setKhachHang(kh);
        ctgh.setSanPham(sp);
        ctgh.setPhanLoai(phanLoai);
        ctgh.setTrangThai(true);

        return gioHangRepo.save(ctgh);
    }

    @Override
    public void xoaKhoiGio(Integer chiTietGioHangId) {
        gioHangRepo.deleteById(chiTietGioHangId);
    }
}
