package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Dia_Chi_Giao_Hang")
@Data
public class DiaChiGiaoHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diaChiGiaoHangId;
    private String tenNguoiNhan;
    private String soDienThoai;
    private String diaChiCuThe;
    private String ghiChu;
    private Boolean macDinh;
}


