package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Xuat_Xu")
@Data
public class XuatXu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer xuatXuId;
    private String tenDatNuocSanXuat;
}