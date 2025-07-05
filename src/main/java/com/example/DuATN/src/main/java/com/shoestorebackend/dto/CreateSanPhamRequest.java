package com.shoestorebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CreateSanPhamRequest {
    private String tenSanPham;
    private Boolean trangThai;
    private String chatLieu;
    private String moTa;
    @JsonFormat(pattern = "yyyy-MM-dd") //Jackson (thư viện JSON mặc định) hiểu nhầm kiểu khi parse Date
    private Date ngayNhap;
    private Integer thuongHieuId;
    private Integer kieuGiayGiayId;
    private Integer xuatXuId;
}
