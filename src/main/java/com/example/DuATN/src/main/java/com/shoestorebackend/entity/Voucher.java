package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Voucher")
@Data
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voucherId;
    private Integer soLuongConLai;
    private String loai;
    private Boolean trangThai;
    private LocalDate hanSuDung;
}

