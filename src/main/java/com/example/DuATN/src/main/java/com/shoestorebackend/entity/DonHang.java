package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Don_Hang", schema = "dbo")
@Data
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "don_hang_id")
    private Integer donHangId;
    @Column(name = "tong_tien")

    private BigDecimal tongTien;
    @Column(name = "ngay_dat_hang")

    private LocalDate ngayDatHang;
    @Column(name = "trang_thai")

    private String trangThai;
    @Column(name = "dia_chi_giao_hang")
    private String diaChiGiaoHang;
    @Column(name = "trang_thai_thanh_toan")

    private String trangThaiThanhToan;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;
}


