/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.repository.KhachHangRepository;
import com.poly.it17326.group2.response.ViewKhachHangResponse;
import com.poly.it17326.group2.response.ViewLichSuGiaoDichKhachHang;
import com.poly.it17326.group2.service.ICommon;
import com.poly.it17326.group2.service.KhachHangService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository khachHangRepository = new KhachHangRepository();

    @Override
    public List<ViewKhachHangResponse> getAll() {
        List<ViewKhachHangResponse> response = new ArrayList<>();
        List<KhachHang> listKichThuoc = khachHangRepository.getAll();// lấy dữ liệu từ repository
        for (KhachHang khachHang : listKichThuoc) {
            ViewKhachHangResponse viewSizeReponse = new ViewKhachHangResponse(khachHang);
            response.add(viewSizeReponse);
        }
        return response;
    }

    @Override
    public Boolean create(KhachHang khachHang) {
        return khachHangRepository.add(khachHang);
    }

    @Override
    public List<ViewKhachHangResponse> getByHoTenKhach(String hoTen) {
        List<ViewKhachHangResponse> response = new ArrayList<>();
        List<KhachHang> listKichThuoc = khachHangRepository.getByTenKhachHang(hoTen);
        for (KhachHang khachHang : listKichThuoc) {
            if (khachHang.getHoTen().contains(hoTen)) {
                ViewKhachHangResponse viewSizeReponse = new ViewKhachHangResponse(khachHang);
                response.add(viewSizeReponse);
            }
        }
        return response;
    }

    @Override
    public int genMaKH() {
        return khachHangRepository.genMaKH();
    }

    @Override
    public List<ViewLichSuGiaoDichKhachHang> getLichSuGiaoDich(String idKH) {
        List<ViewLichSuGiaoDichKhachHang> response = new ArrayList<>();
        List<HoaDon> listHoaDon = khachHangRepository.getLichSuGiaoDich(idKH);
        for (HoaDon hoaDon : listHoaDon) {
            ViewLichSuGiaoDichKhachHang lichSuGiaoDich = new ViewLichSuGiaoDichKhachHang(hoaDon);
            response.add(lichSuGiaoDich);
        }
        return response;
    }

    @Override
    public BigDecimal getTongTienByIdKhachHang(String idKH) {
        return khachHangRepository.getTongTienByIdKhachHang(idKH);
    }

}
