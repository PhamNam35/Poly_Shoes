package com.shoestorebackend.service.impl;

import com.shoestorebackend.entity.KhachHang;
import com.shoestorebackend.entity.NguoiDung;
import com.shoestorebackend.repository.KhachHangRepository;
import com.shoestorebackend.repository.NguoiDungRepository;
import com.shoestorebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private NguoiDungRepository nguoiDungRepo;

    @Autowired
    private KhachHangRepository khachHangRepo;

    // ✅ Đăng nhập admin (nhân viên)
    @Override
    public NguoiDung loginAdmin(String username, String password) {
        return nguoiDungRepo.findByTenDangNhap(username)
                .filter(nd -> nd.getMatKhau().equals(password))
                .orElseThrow(() -> new RuntimeException("Sai tài khoản admin"));
    }

    // ✅ Đăng nhập khách hàng
    @Override
    public KhachHang loginCustomer(String username, String password) {
        return khachHangRepo.findByTenDangNhap(username)
                .filter(kh -> kh.getMatKhau().equals(password))
                .orElseThrow(() -> new RuntimeException("Sai tài khoản khách"));
    }

    // ✅ Đăng ký khách hàng (có kiểm tra trùng tên đăng nhập)
    @Override
    public KhachHang dangKyKhachHang(KhachHang kh) {
        if (khachHangRepo.existsByTenDangNhap(kh.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }
        return khachHangRepo.save(kh);
    }
    @Override
    // ✅ Đăng ký nhân viên (admin/user) (nếu bạn sử dụng)
    public NguoiDung dangKyNguoiDung(NguoiDung nd) {
        if (nguoiDungRepo.existsByTenDangNhap(nd.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }
        return nguoiDungRepo.save(nd);
    }
}
