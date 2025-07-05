package com.shoestorebackend.controller;

import com.shoestorebackend.entity.KhachHang;
import com.shoestorebackend.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/khach-hang")
@CrossOrigin
public class KhachHangController {

    @Autowired
    private KhachHangRepository khachHangRepository;

    // ✅ API lấy thông tin khách hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<KhachHang> getById(@PathVariable Integer id) {
        return ResponseEntity.of(khachHangRepository.findById(id));
    }
}
