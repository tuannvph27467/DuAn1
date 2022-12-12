package com.poly.it17326.group2.service;

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.NhanVien;
import com.poly.it17326.group2.domainmodel.TrangThai;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import com.poly.it17326.group2.response.ViewHoaDonChiTietResponse;
import com.poly.it17326.group2.response.ViewHoaDonResponse;
import java.util.List;

public interface BanHangService {

    List<ViewChiTietSPResponse> getAllChiTietSP();

    List<ViewChiTietSPResponse> getSanPham(String tenSP);

    List<ViewHoaDonChiTietResponse> getHoaDonChiTietByHoaDon(String ma);

    List<ViewHoaDonResponse> getHoaDonByMa(String Ma);

    Boolean createHDCT(HoaDonChiTiet hoaDonChiTiet);

    Boolean createHD(HoaDon hoaDon);

    Boolean updateChiTietSP(ChiTietSP chiTietSP);

    Boolean updateHDCT(HoaDonChiTiet hoaDonChiTiet);

    List<TrangThai> getAllTrangThai();

    List<HoaDon> getAllHoaDon();

    List<SanPham> getAllSanPham();

    List<HoaDonChiTiet> getAllHDCT();

    List<NhanVien> getAllTaiKhoans();

    HoaDon getByMaHD(String maHD);

    Boolean updateSLSP(ChiTietSP chiTietSP, String id);
    ///////////////////////////////////////////////
    Boolean huyDon(HoaDon hoaDon, String idHD);

    Boolean updateKhach(HoaDon hoaDon, String id);

    Boolean tangSL(HoaDonChiTiet hoaDonChiTiet, String idHDCT);

    Boolean giamSL(HoaDonChiTiet hoaDonChiTiet, String idHDCT);

    Boolean updateGiamSL(ChiTietSP chiTietSP, String id);

    Boolean updateTangSL(ChiTietSP chiTietSP, String id);

    Boolean thanhToan(HoaDon hoaDon, String id);
    
    Boolean xoaHDCT(String idHDCT);
}
