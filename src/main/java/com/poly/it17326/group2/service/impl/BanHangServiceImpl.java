package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.NhanVien;
import com.poly.it17326.group2.domainmodel.TrangThai;
import com.poly.it17326.group2.repository.BanHangRepository;
import com.poly.it17326.group2.repository.ChiTietSPRepository;
import com.poly.it17326.group2.repository.HoaDonChiTietRepository;
import com.poly.it17326.group2.repository.HoaDonRepository;
import com.poly.it17326.group2.repository.SanPhamRepository;
import com.poly.it17326.group2.repository.NhanVienRepository;
import com.poly.it17326.group2.repository.TrangThaiRepository;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import com.poly.it17326.group2.response.ViewHoaDonChiTietResponse;
import com.poly.it17326.group2.response.ViewHoaDonResponse;
import com.poly.it17326.group2.service.BanHangService;
import java.util.ArrayList;
import java.util.List;

public class BanHangServiceImpl implements BanHangService {

    private BanHangRepository banHangRepository = new BanHangRepository();

    private ChiTietSPRepository chiTietSPRepository = new ChiTietSPRepository();

    private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    private TrangThaiRepository trangThaiRepository = new TrangThaiRepository();

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    private NhanVienRepository taiKhoanRepository = new NhanVienRepository();

    @Override
    public List<ViewChiTietSPResponse> getAllChiTietSP() {
        List<ViewChiTietSPResponse> response = new ArrayList<>();
        List<ChiTietSP> list = chiTietSPRepository.getAll();
        for (ChiTietSP chiTietSP : list) {
            ViewChiTietSPResponse viewChiTietSPResponse = new ViewChiTietSPResponse(chiTietSP);
            response.add(viewChiTietSPResponse);
        }
        return response;
    }

    @Override
    public List<ViewHoaDonChiTietResponse> getHoaDonChiTietByHoaDon(String ma) {
        List<ViewHoaDonChiTietResponse> response = new ArrayList<>();
        List<HoaDonChiTiet> list = banHangRepository.getHDCTByHoaDon(ma);
        for (HoaDonChiTiet hoaDonChiTiet : list) {
            ViewHoaDonChiTietResponse viewHoaDonChiTietResponse = new ViewHoaDonChiTietResponse(hoaDonChiTiet);
            response.add(viewHoaDonChiTietResponse);
        }
        return response;
    }

    @Override
    public List<ViewHoaDonResponse> getHoaDonByMa(String ma) {
        List<ViewHoaDonResponse> response = new ArrayList<>();
        List<HoaDon> list = banHangRepository.getHoaDonByMa(ma);
        for (HoaDon hoaDon : list) {
            if (hoaDon.getNhanVien().getMa().equals(ma)) {
                ViewHoaDonResponse viewHoaDonResponse = new ViewHoaDonResponse(hoaDon);
                response.add(viewHoaDonResponse);
            }
        }
        return response;
    }

    @Override
    public Boolean createHDCT(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.add(hoaDonChiTiet);
    }

    @Override
    public Boolean createHD(HoaDon hoaDon) {
        return hoaDonRepository.add(hoaDon);
    }

    @Override
    public Boolean updateChiTietSP(ChiTietSP chiTietSP) {
        return chiTietSPRepository.add(chiTietSP);
    }

    @Override
    public List<TrangThai> getAllTrangThai() {
        return trangThaiRepository.getAll();
    }

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.getAll();
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.getAll();
    }

    @Override
    public List<HoaDonChiTiet> getAllHDCT() {
        return hoaDonChiTietRepository.getAll();
    }

    @Override
    public List<NhanVien> getAllTaiKhoans() {
        return taiKhoanRepository.getAll();
    }

    @Override
    public HoaDon getByMaHD(String maHD) {
        return banHangRepository.getByMaHD(maHD);
    }

    @Override
    public Boolean updateSLSP(ChiTietSP chiTietSP, String id) {
        return banHangRepository.updateSLSP(chiTietSP, id);
    }

    @Override
    public List<ViewChiTietSPResponse> getSanPham(String tenSP) {
        List<ViewChiTietSPResponse> response = new ArrayList<>();
        List<ChiTietSP> list = banHangRepository.getAll();
        for (ChiTietSP chiTietSP : list) {
            if (chiTietSP.getSanPham().getTen().contains(tenSP)) {
                ViewChiTietSPResponse viewChiTietSPResponse = new ViewChiTietSPResponse(chiTietSP);
                response.add(viewChiTietSPResponse);
            }
        }
        return response;
    }

    @Override
    public Boolean thanhToan(HoaDon hoaDon, String id) {
        return banHangRepository.thanhToan(hoaDon, id);
    }

    @Override
    public Boolean updateHDCT(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.add(hoaDonChiTiet);
    }
//////////////////////////////////////////////////////////////////

    @Override
    public Boolean huyDon(HoaDon hoaDon, String idHD) {
        return banHangRepository.huyDon(hoaDon, idHD);
    }

    @Override
    public Boolean updateKhach(HoaDon hoaDon, String id) {
        return banHangRepository.updateKhach(hoaDon, id);
    }

    @Override
    public Boolean tangSL(HoaDonChiTiet hoaDonChiTiet, String idHDCT) {
        return banHangRepository.tangSL(hoaDonChiTiet, idHDCT);
    }

    @Override
    public Boolean giamSL(HoaDonChiTiet hoaDonChiTiet, String idHDCT) {
        return banHangRepository.giamSL(hoaDonChiTiet, idHDCT);
    }

    @Override
    public Boolean updateGiamSL(ChiTietSP chiTietSP, String id) {
        return banHangRepository.upadteGiamSP(chiTietSP, id);
    }

    @Override
    public Boolean updateTangSL(ChiTietSP chiTietSP, String id) {
        return banHangRepository.upadteTangSP(chiTietSP, id);
    }

    @Override
    public Boolean xoaHDCT(String idHDCT) {
        return banHangRepository.xoaHDCT(idHDCT);
    }

}
