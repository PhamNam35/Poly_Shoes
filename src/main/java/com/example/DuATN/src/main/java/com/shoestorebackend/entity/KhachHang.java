package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Khach_Hang")
@Data
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer khachHangId;
    @Column(unique = true, nullable = false)
    private String tenDangNhap;
    private String matKhau;
    private String soDienThoai;
    private LocalDate ngayDangKy;
    private String hoTen;

    @ManyToOne
    @JoinColumn(name = "dia_chi_giao_hang_id")
    private DiaChiGiaoHang diaChiGiaoHang;
}
