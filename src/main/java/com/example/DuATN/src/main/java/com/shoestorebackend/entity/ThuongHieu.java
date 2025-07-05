package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Thuong_Hieu")
@Data
public class ThuongHieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer thuongHieuId;
    private String tenThuongHieu;
}
