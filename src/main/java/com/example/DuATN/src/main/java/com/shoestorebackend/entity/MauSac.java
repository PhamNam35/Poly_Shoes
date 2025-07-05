package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Mau_Sac")
@Data
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mauSacId;
    private String tenMau;
    private String maMauHex;
}

