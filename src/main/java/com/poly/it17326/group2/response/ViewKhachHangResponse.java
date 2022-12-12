/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.KhachHang;
import java.math.BigDecimal;
import java.util.Date;
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
public class ViewKhachHangResponse {

    private String id;
    private String ma;
    private String hoTen;
    private int gioiTinh;
    private Date ngaySinh;
    private String sdt;
    private String diaChi;
    private int capBac;
    private String ngayTao;
    private String ngaySua;

    public ViewKhachHangResponse() {
    }

    public ViewKhachHangResponse(KhachHang khachHang) {
        this.id = khachHang.getId();
        this.ma = khachHang.getMaKhachHang();
        this.hoTen = khachHang.getHoTen();
        this.gioiTinh = khachHang.getGioiTinh();
        this.ngaySinh = khachHang.getNgaySinh();
        this.sdt = khachHang.getSdt();
        this.diaChi = khachHang.getDiaChi();
        this.capBac = khachHang.getCapBac();
        this.ngayTao = khachHang.getNgayTao();
        this.ngaySua = khachHang.getNgaySua();
    }

    public String htGioiTinh(){
        if(this.gioiTinh == 0){
            return "Nam";
        }
        return "Nữ";
    }
    
    public String htCapBac(){
        if(this.capBac == 0){
            return "Đồng";
        }else if(this.capBac == 1){
            return "Bạc";
        }else if(this.capBac == 2){
            return "Vàng";
        }
        return "Kim Cương";
    }
}
