package com.shoestorebackend.service.impl;

import com.shoestorebackend.entity.ChiTietDonHang;
import com.shoestorebackend.entity.ChiTietSanPham;
import com.shoestorebackend.entity.DonHang;
import com.shoestorebackend.entity.KhachHang;
import com.shoestorebackend.repository.ChiTietDonHangRepository;
import com.shoestorebackend.repository.ChiTietSanPhamRepository;
import com.shoestorebackend.repository.DonHangRepository;
import com.shoestorebackend.repository.KhachHangRepository;
import com.shoestorebackend.request.DonHangRequest;
import com.shoestorebackend.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository donHangRepo;

    @Autowired
    private ChiTietDonHangRepository chiTietRepo;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepo;

    @Autowired
    private KhachHangRepository khachHangRepo;

    @Override
    public DonHang taoDonHangAnDanh(DonHangRequest req) {
        DonHang don = new DonHang();
        don.setNgayDatHang(LocalDate.now());
        don.setTrangThai("Đang xử lý");
        don.setTrangThaiThanhToan("Chưa thanh toán");
        don.setDiaChiGiaoHang(req.getDiaChiGiaoHang());

        List<ChiTietDonHang> chiTietList = new ArrayList<>();
        BigDecimal tongTien = BigDecimal.ZERO;

        for (int i = 0; i < req.getChiTietSpIds().size(); i++) {
            Integer spId = req.getChiTietSpIds().get(i);
            Integer sl = req.getSoLuongs().get(i);
            ChiTietSanPham ctsp = chiTietSanPhamRepo.findById(spId).orElseThrow();

            ChiTietDonHang ctdh = new ChiTietDonHang();
            ctdh.setSoLuong(sl);
            ctdh.setChiTietSanPham(ctsp);
            ctdh.setDonHang(don);

            tongTien = tongTien.add(ctsp.getGiaBan().multiply(BigDecimal.valueOf(sl)));
            chiTietList.add(ctdh);
        }

        don.setTongTien(tongTien);

        donHangRepo.save(don);
        chiTietRepo.saveAll(chiTietList);

        return don;
    }
    @Override
    public DonHang datHang(Integer khachHangId, List<Integer> chiTietSpIds, List<Integer> soLuongs) {
        // Tìm khách hàng theo ID
        KhachHang kh = khachHangRepo.findById(khachHangId).orElseThrow();

        // Tạo đơn hàng mới, KHÔNG set ID
        DonHang dh = new DonHang();
        dh.setKhachHang(kh);
        dh.setNgayDatHang(LocalDate.now());
        dh.setTrangThai("Đang xử lý");
        dh.setTrangThaiThanhToan("Chưa thanh toán");
        dh.setDiaChiGiaoHang(kh.getDiaChiGiaoHang().getDiaChiCuThe());

        // Lưu đơn hàng để sinh donHangId
        DonHang donHangDaLuu = donHangRepo.save(dh);

        BigDecimal tongTien = BigDecimal.ZERO;

        // Duyệt từng sản phẩm trong đơn
        for (int i = 0; i < chiTietSpIds.size(); i++) {
            ChiTietSanPham ctsp = chiTietSanPhamRepo.findById(chiTietSpIds.get(i)).orElseThrow();
            Integer soLuong = soLuongs.get(i);

            BigDecimal thanhTien = ctsp.getGiaBan().multiply(BigDecimal.valueOf(soLuong));
            tongTien = tongTien.add(thanhTien);

            ChiTietDonHang ctDon = new ChiTietDonHang();
            ctDon.setDonHang(donHangDaLuu);
            ctDon.setChiTietSanPham(ctsp);
            ctDon.setSoLuong(soLuong);

            chiTietRepo.save(ctDon);
        }

        // Cập nhật tổng tiền sau khi thêm chi tiết
        donHangDaLuu.setTongTien(tongTien);
        return donHangRepo.save(donHangDaLuu);
    }

    @Override
    public List<DonHang> getDonHangByKhach(Integer khachHangId) {
        return donHangRepo.findByKhachHang_KhachHangId(khachHangId);
    }
}
