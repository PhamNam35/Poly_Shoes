package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Kieu_Giay_Giay")
@Data
public class KieuGiay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kieuGiayGiayId;
    private String tenKieuGiayGiay;
}
