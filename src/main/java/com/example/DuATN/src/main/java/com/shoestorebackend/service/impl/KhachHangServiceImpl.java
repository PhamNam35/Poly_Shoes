package com.shoestorebackend.service.impl;

import com.shoestorebackend.entity.KhachHang;
import com.shoestorebackend.repository.KhachHangRepository;
import com.shoestorebackend.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public KhachHang login(String username, String password) {
        return khachHangRepository.findByTenDangNhap(username)
                .filter(kh -> kh.getMatKhau().equals(password))
                .orElseThrow(() -> new RuntimeException("Sai thông tin đăng nhập"));
    }

}

