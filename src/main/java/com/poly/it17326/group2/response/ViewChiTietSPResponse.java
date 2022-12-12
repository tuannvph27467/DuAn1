/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author anhntnph27418
 */
@Getter
@Setter
@ToString
public class ViewChiTietSPResponse {

    private String id;
    private String maChiTietSP;
    private String maSP;
    private String tenSP;
    private String thuongHieu;
    private String NCC;
    private String mauSac;
    private String kichThuoc;
    private String deGiay;
    private int soLuongTon;
    private BigDecimal gia;
    private Integer trangThai;
    private BigDecimal tongTien;

    public ViewChiTietSPResponse() {
    }

    public ViewChiTietSPResponse(ChiTietSP chiTietSP) {
        this.id = chiTietSP.getId();
        this.maChiTietSP = chiTietSP.getMaChiTietSP();
        this.maSP = chiTietSP.getSanPham().getMa();
        this.tenSP = chiTietSP.getSanPham().getTen();
        this.thuongHieu = chiTietSP.getThuongHieu().getTen();
        this.NCC = chiTietSP.getNhaCungCap().getTen();
        this.mauSac = chiTietSP.getMauSac().getTen();
        this.kichThuoc = chiTietSP.getKichThuoc().getTen();
        this.deGiay = chiTietSP.getDeGiay().getTen();
        this.soLuongTon = chiTietSP.getSoLuongTon();
        this.gia = chiTietSP.getGia();
        this.trangThai = chiTietSP.getTrangThai();       
    }
     
    public String hTTrangThai(){
        if(trangThai==1){
            return "Đang hoạt đông";
        }else{
            return "Dừng hoạt động";
        }
    }
}
