package com.shoestorebackend.controller;

import com.shoestorebackend.entity.ChiTietGioHang;
import com.shoestorebackend.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gio-hang")
@CrossOrigin
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;

    @GetMapping("/{khachHangId}")
    public ResponseEntity<List<ChiTietGioHang>> getGioHang(@PathVariable Integer khachHangId) {
        return ResponseEntity.ok(gioHangService.getGioHangByKhach(khachHangId));
    }

    @PostMapping("/them")
    public ResponseEntity<ChiTietGioHang> themVaoGio(@RequestBody Map<String, Object> data) {
        Integer khachHangId = (Integer) data.get("khachHangId");
        Integer sanPhamId = (Integer) data.get("sanPhamId");
        String phanLoai = (String) data.get("phanLoai");

        return ResponseEntity.ok(gioHangService.themVaoGio(khachHangId, sanPhamId, phanLoai));
    }

    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<?> xoaKhoiGio(@PathVariable Integer id) {
        gioHangService.xoaKhoiGio(id);
        return ResponseEntity.ok("Đã xóa khỏi giỏ");
    }
}
