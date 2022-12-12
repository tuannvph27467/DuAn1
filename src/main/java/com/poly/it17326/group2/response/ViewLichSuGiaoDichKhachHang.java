/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.HoaDon;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author DELL
 */
@Getter
@Setter
@ToString
public class ViewLichSuGiaoDichKhachHang {

    private String maHD;
    private String ngayThanhToan;
    private BigDecimal tongTien;
    private String tenKH;
    private String sdt;
    private int gioiTinh;
    private int capBac;

    public ViewLichSuGiaoDichKhachHang() {
    }

    public ViewLichSuGiaoDichKhachHang(HoaDon hoaDon) {
        this.maHD = hoaDon.getMa();
        this.ngayThanhToan = hoaDon.getNgayThanhToan();
        this.tongTien = hoaDon.getThanhTien();
        this.tenKH = hoaDon.getKhachHang().getHoTen();
        this.sdt = hoaDon.getKhachHang().getSdt();
        this.gioiTinh = hoaDon.getKhachHang().getGioiTinh();
        this.capBac = hoaDon.getKhachHang().getCapBac();
    }

    public String htGioiTinh() {
        if (this.gioiTinh == 0) {
            return "Nam";
        }
        return "Nữ";
    }

    public String htCapBac() {
        if (this.capBac == 0) {
            return "Đồng";
        } else if (this.capBac == 1) {
            return "Bạc";
        } else if (this.capBac == 2) {
            return "Vàng";
        }
        return "Kim Cương";
    }

}
