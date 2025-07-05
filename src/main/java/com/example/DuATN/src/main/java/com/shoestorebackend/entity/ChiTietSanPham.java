package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Chi_Tiet_San_Pham")
@Data
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chiTietSpId;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "kich_thuoc_id")
    private KichThuoc kichThuoc;

    @ManyToOne
    @JoinColumn(name = "mau_sac_id")
    private MauSac mauSac;

    private Integer soLuongTon;
    private Boolean trangThai;
    private LocalDate ngayCapNhap;
    private BigDecimal giaBan;
    @OneToMany(mappedBy = "chiTietSanPham")
    private List<HinhAnhSanPham> hinhAnhSanPhams;


    @ManyToOne
    @JoinColumn(name = "hinh_anh_san_sp_id")
    private HinhAnhSanPham hinhAnhSanPham;
}


