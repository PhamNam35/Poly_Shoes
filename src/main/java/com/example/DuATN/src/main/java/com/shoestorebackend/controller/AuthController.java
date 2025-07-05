package com.shoestorebackend.controller;

import com.shoestorebackend.entity.KhachHang;
import com.shoestorebackend.entity.NguoiDung;
import com.shoestorebackend.repository.KhachHangRepository;
import com.shoestorebackend.repository.NguoiDungRepository;
import com.shoestorebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private KhachHangRepository khachHangRepo;

    @Autowired
    private NguoiDungRepository nguoiDungRepo;

    @PostMapping("/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> body) {
        String user = body.get("username");
        String pass = body.get("password");
        return ResponseEntity.ok(authService.loginAdmin(user, pass));
    }

    @PostMapping("/customer")
    public ResponseEntity<?> loginCustomer(@RequestBody Map<String, String> body) {
        String user = body.get("username");
        String pass = body.get("password");
        return ResponseEntity.ok(authService.loginCustomer(user, pass));



    }
    @PostMapping("/register-customer")
    public ResponseEntity<?> dangKyKhachHang(@RequestBody KhachHang kh) {
        try {
            return ResponseEntity.ok(authService.dangKyKhachHang(kh));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // ✅ Gửi lỗi rõ ràng về frontend
        }
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> dangKyNguoiDung(@RequestBody NguoiDung nd) {
        try {
            return ResponseEntity.ok(authService.dangKyNguoiDung(nd));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}

