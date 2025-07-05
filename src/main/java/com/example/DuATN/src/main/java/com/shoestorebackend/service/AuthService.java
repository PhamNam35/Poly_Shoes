package com.shoestorebackend.service;

import com.shoestorebackend.entity.KhachHang;
import com.shoestorebackend.entity.NguoiDung;

public interface AuthService {
    NguoiDung loginAdmin(String username, String password);
    KhachHang loginCustomer(String username, String password);
    KhachHang dangKyKhachHang(KhachHang kh);
    NguoiDung dangKyNguoiDung(NguoiDung nd);

}
