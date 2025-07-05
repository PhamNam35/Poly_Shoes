package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "San_Pham")
@Data
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sanPhamId;
    private String tenSanPham;
    private Boolean trangThai;
    private String chatLieu;
    private String moTa;
    private Date ngayNhap;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;

    @ManyToOne
    @JoinColumn(name = "kieu_giay_giay_id")
    private KieuGiay kieuGiay;
    @OneToMany(mappedBy = "sanPham")
    private List<ChiTietSanPham> chiTietSanPhams;
    @ManyToOne
    @JoinColumn(name = "xuat_xu_id")
    private XuatXu xuatXu;
}


