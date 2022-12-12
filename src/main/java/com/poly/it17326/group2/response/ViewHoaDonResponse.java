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
 * @author tuannvph27467
 */
@Getter
@Setter
@ToString
public class ViewHoaDonResponse {

    private String id;
    private String maHD;
    private String ngayTao;
    private String tenNhanVien;
    private String tenKhachHang;
    private Integer trangThai;
    private String lyDo;
    private String ngayThanhToan;
    private String ngaySua;
    private BigDecimal tienKhuyenMai;
    private BigDecimal tongTien;
    private BigDecimal thanhTien;
    private BigDecimal tienTraLai;

    public ViewHoaDonResponse() {
    }

    public ViewHoaDonResponse(HoaDon hoaDon) {
        this.id = hoaDon.getId();
        this.maHD = hoaDon.getMa();
        this.ngayTao = hoaDon.getNgayTao();
        this.tenNhanVien = hoaDon.getNhanVien().getHoTen();
        this.tenKhachHang = hoaDon.getKhachHang().getHoTen();
        this.trangThai = hoaDon.getTrangThai().getTenTrangThai();
        this.lyDo = hoaDon.getLyDo();
        this.tongTien = hoaDon.getTongTien();
        this.thanhTien = hoaDon.getThanhTien();
        this.tienTraLai = hoaDon.getTienTraLai();
    }

    public String htTrangThai() {
        if (this.trangThai == 1) {
            return "Chờ thanh toán";
        } else if (this.trangThai == 0) {
            return "Đã hủy";
        }
        return "Đã thanh toán";
    }
}
