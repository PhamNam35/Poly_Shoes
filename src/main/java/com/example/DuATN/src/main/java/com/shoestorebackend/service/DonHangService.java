package com.shoestorebackend.service;

import com.shoestorebackend.entity.DonHang;
import com.shoestorebackend.request.DonHangRequest;

import java.util.List;

public interface DonHangService {
    DonHang datHang(Integer khachHangId, List<Integer> chiTietSpIds, List<Integer> soLuongs);

    List<DonHang> getDonHangByKhach(Integer khachHangId);
    DonHang taoDonHangAnDanh(DonHangRequest req);

}
