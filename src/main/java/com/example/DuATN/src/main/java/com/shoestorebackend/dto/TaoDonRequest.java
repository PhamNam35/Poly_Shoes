package com.shoestorebackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaoDonRequest {
    private Integer khachHangId;
    private List<Integer> chiTietSpIds;
    private List<Integer> soLuongs;
}
