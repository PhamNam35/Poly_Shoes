package com.shoestorebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Chi_Tiet_Don_Hang")
@Data
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chi_tiet_don_hang_id")

    private Integer chiTietDonHangId;
    private Integer soLuong;

    @ManyToOne
    @JoinColumn(name = "don_hang_id")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham")
    private ChiTietSanPham chiTietSanPham;
}


