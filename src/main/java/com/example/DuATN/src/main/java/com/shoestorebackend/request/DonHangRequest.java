package com.shoestorebackend.request;

import java.util.List;

public class DonHangRequest {
    private String hoTen;
    private String soDienThoai;
    private String diaChiGiaoHang;
    private List<Integer> chiTietSpIds;
    private List<Integer> soLuongs;

    // Getters và Setters
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChiGiaoHang() {
        return diaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        this.diaChiGiaoHang = diaChiGiaoHang;
    }

    public List<Integer> getChiTietSpIds() {
        return chiTietSpIds;
    }

    public void setChiTietSpIds(List<Integer> chiTietSpIds) {
        this.chiTietSpIds = chiTietSpIds;
    }

    public List<Integer> getSoLuongs() {
        return soLuongs;
    }

    public void setSoLuongs(List<Integer> soLuongs) {
        this.soLuongs = soLuongs;
    }
}
