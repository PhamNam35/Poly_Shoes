package com.shoestorebackend.controller;

import com.shoestorebackend.entity.DonHang;
import com.shoestorebackend.request.DonHangRequest;
import com.shoestorebackend.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dat-hang")
@CrossOrigin
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    // ✅ 1. Đặt hàng khi đã đăng nhập
    @PostMapping
    public ResponseEntity<DonHang> datHang(@RequestBody Map<String, Object> data) {
        Integer khachHangId = (Integer) data.get("khachHangId");
        List<Integer> chiTietSpIds = (List<Integer>) data.get("chiTietSpIds");
        List<Integer> soLuongs = (List<Integer>) data.get("soLuongs");

        DonHang dh = donHangService.datHang(khachHangId, chiTietSpIds, soLuongs);
        return ResponseEntity.ok(dh);
    }

    // ✅ 2. Lịch sử đặt hàng
    @GetMapping("/{khachHangId}")
    public ResponseEntity<List<DonHang>> lichSu(@PathVariable Integer khachHangId) {
        return ResponseEntity.ok(donHangService.getDonHangByKhach(khachHangId));
    }

    // ✅ 3. Đặt hàng ẩn danh (không cần tài khoản)
    @PostMapping("/anonymous")
    public ResponseEntity<DonHang> datHangKhachVangLai(@RequestBody DonHangRequest req) {
        DonHang don = donHangService.taoDonHangAnDanh(req);
        return ResponseEntity.ok(don);
    }

}
