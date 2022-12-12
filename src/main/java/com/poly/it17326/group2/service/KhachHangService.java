/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group2.service;

import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.response.ViewKhachHangResponse;
import com.poly.it17326.group2.response.ViewLichSuGiaoDichKhachHang;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface KhachHangService {

    List<ViewKhachHangResponse> getAll();

    List<ViewKhachHangResponse> getByHoTenKhach(String hoTen);

    Boolean create(KhachHang khachHang);

    int genMaKH();

    List<ViewLichSuGiaoDichKhachHang> getLichSuGiaoDich(String idKH);
    
    BigDecimal getTongTienByIdKhachHang(String idKH);
}
