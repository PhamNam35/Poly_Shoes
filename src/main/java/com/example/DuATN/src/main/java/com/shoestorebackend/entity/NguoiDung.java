package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Nguoi_Dung")
@Data
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nguoiDungId;
    private String hoTen;
    private LocalDate ngaySinh;
    @Column(unique = true, nullable = false)
    private String tenDangNhap;
    private String matKhau;
    private String email;
    private String soDienThoai;
    private LocalDate ngayTao;
}

