package com.shoestorebackend.controller;

import com.shoestorebackend.dto.TaoDonRequest;
import com.shoestorebackend.entity.DonHang;
import com.shoestorebackend.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminDonHangController {

    @Autowired
    private DonHangService donHangService;

    @PostMapping("/tao-don")
    public ResponseEntity<?> taoDonOffline(@RequestBody TaoDonRequest request) {
        try {
            DonHang donHang = donHangService.datHang(
                    request.getKhachHangId(),
                    request.getChiTietSpIds(),
                    request.getSoLuongs()
            );
            return ResponseEntity.ok(donHang);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Tạo đơn thất bại: " + e.getMessage());
        }
    }
}




