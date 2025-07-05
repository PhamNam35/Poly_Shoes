package com.shoestorebackend.service.impl;

import com.shoestorebackend.dto.CreateSanPhamRequest;
import com.shoestorebackend.dto.SanPhamDTO;
import com.shoestorebackend.entity.ChiTietSanPham;
import com.shoestorebackend.entity.HinhAnhSanPham;
import com.shoestorebackend.entity.SanPham;
import com.shoestorebackend.repository.KieuGiayRepository;
import com.shoestorebackend.repository.SanPhamRepository;
import com.shoestorebackend.repository.ThuongHieuRepository;
import com.shoestorebackend.repository.XuatXuRepository;
import com.shoestorebackend.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Autowired
    private KieuGiayRepository kieuGiayRepository;

    @Autowired
    private XuatXuRepository xuatXuRepository;

    @Override
    public List<SanPhamDTO> getAllSanPhamActive() {
        List<SanPham> danhSach = sanPhamRepository.findByTrangThaiTrue();
        List<SanPhamDTO> ketQua = new ArrayList<>();

        for (SanPham sp : danhSach) {
            SanPhamDTO dto = new SanPhamDTO();
            dto.setSanPhamId(sp.getSanPhamId());
            dto.setTenSanPham(sp.getTenSanPham());
            dto.setMoTa(sp.getMoTa());
            dto.setChatLieu(sp.getChatLieu());
            if (!sp.getChiTietSanPhams().isEmpty()) {
                dto.setGiaBan(sp.getChiTietSanPhams().get(0).getGiaBan());
            }


            // Lấy chi tiết sản phẩm đầu tiên (nếu có)
            if (!sp.getChiTietSanPhams().isEmpty()) {
                ChiTietSanPham chiTiet = sp.getChiTietSanPhams().get(0);

                // Lấy ảnh chính nếu có
                Optional<HinhAnhSanPham> anhChinh = chiTiet.getHinhAnhSanPhams()
                        .stream()
                        .filter(HinhAnhSanPham::getAnhChinh)
                        .findFirst();

                anhChinh.ifPresent(hinh -> dto.setDuongDanAnh(hinh.getDuongDanAnh()));
            }

            ketQua.add(dto);
        }

        return ketQua;
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }
    @Override
    public SanPham createSanPham(CreateSanPhamRequest request) {
        SanPham sp = new SanPham();
        sp.setTenSanPham(request.getTenSanPham());
        sp.setTrangThai(request.getTrangThai());
        sp.setChatLieu(request.getChatLieu());
        sp.setMoTa(request.getMoTa());
        sp.setNgayNhap(request.getNgayNhap());
        sp.setThuongHieu(thuongHieuRepository.findById(request.getThuongHieuId()).orElse(null));
        sp.setKieuGiay(kieuGiayRepository.findById(request.getKieuGiayGiayId()).orElse(null));
        sp.setXuatXu(xuatXuRepository.findById(request.getXuatXuId()).orElse(null));
        return sanPhamRepository.save(sp);
    }
    @Override
    public SanPham updateSanPham(Integer id, CreateSanPhamRequest request) {
        SanPham sp = sanPhamRepository.findById(id).orElseThrow();
        sp.setTenSanPham(request.getTenSanPham());
        sp.setTrangThai(request.getTrangThai());
        sp.setChatLieu(request.getChatLieu());
        sp.setMoTa(request.getMoTa());
        sp.setNgayNhap(request.getNgayNhap());
        sp.setThuongHieu(thuongHieuRepository.findById(request.getThuongHieuId()).orElse(null));
        sp.setKieuGiay(kieuGiayRepository.findById(request.getKieuGiayGiayId()).orElse(null));
        sp.setXuatXu(xuatXuRepository.findById(request.getXuatXuId()).orElse(null));
        return sanPhamRepository.save(sp);
    }
    @Override
    public SanPhamDTO getSanPhamById(Integer id) {
        SanPham sp = sanPhamRepository.findById(id).orElseThrow();
        SanPhamDTO dto = new SanPhamDTO();

        dto.setSanPhamId(sp.getSanPhamId());
        dto.setTenSanPham(sp.getTenSanPham());
        dto.setMoTa(sp.getMoTa());
        dto.setChatLieu(sp.getChatLieu());

        if (!sp.getChiTietSanPhams().isEmpty()) {
            ChiTietSanPham chiTiet = sp.getChiTietSanPhams().get(0);
            dto.setGiaBan(chiTiet.getGiaBan());

            Optional<HinhAnhSanPham> anhChinh = chiTiet.getHinhAnhSanPhams()
                    .stream()
                    .filter(HinhAnhSanPham::getAnhChinh)
                    .findFirst();

            anhChinh.ifPresent(ha -> dto.setDuongDanAnh(ha.getDuongDanAnh()));
        }

        return dto;
    }

    @Override
    public void deleteSanPham(Integer id) {
        SanPham sp = sanPhamRepository.findById(id).orElseThrow();
        sp.setTrangThai(false);
        sanPhamRepository.save(sp);
        }
}
