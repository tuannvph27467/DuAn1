/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.HoaDonChiTiet;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author tuannvph27467
 */
@Getter
@Setter
@ToString
public class ViewHoaDonChiTietResponse {

    private String id;
    private String ctsp;
    private String tenSP;
    private Integer soLuong;
    private int kichThuoc;
    private String mauSac;
    private BigDecimal gia;
    private BigDecimal tongTien;

    public ViewHoaDonChiTietResponse() {
    }

    public ViewHoaDonChiTietResponse(HoaDonChiTiet hoaDonChiTiet) {
        this.id = hoaDonChiTiet.getId();
        this.ctsp = hoaDonChiTiet.getChiTietSP().getMaChiTietSP();
        this.tenSP = hoaDonChiTiet.getTenSp();
        this.soLuong = hoaDonChiTiet.getSoLuong();
        this.kichThuoc = hoaDonChiTiet.getKichThuoc();
        this.mauSac = hoaDonChiTiet.getMauSac();
        this.gia = hoaDonChiTiet.getGia();
        this.tongTien = BigDecimal.valueOf(Double.valueOf(hoaDonChiTiet.getSoLuong())).multiply(hoaDonChiTiet.getGia());
    }
}
