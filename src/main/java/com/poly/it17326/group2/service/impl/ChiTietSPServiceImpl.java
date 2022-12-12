package com.poly.it17326.group2.service.impl;
//
import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.domainmodel.MauSac;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.repository.ChiTietSPRepository;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import com.poly.it17326.group2.service.ChiTietSPService;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSPServiceImpl implements ChiTietSPService {

    private ChiTietSPRepository chiTietSPRepository = new ChiTietSPRepository();

    @Override
    public List<ViewChiTietSPResponse> getAll() {
        List<ViewChiTietSPResponse> response = new ArrayList<>();
        List<ChiTietSP> list = chiTietSPRepository.getAll();
        for (ChiTietSP chiTietSP : list) {
            ViewChiTietSPResponse chiTietSPResponse = new ViewChiTietSPResponse(chiTietSP);
            response.add(chiTietSPResponse);
        }
        return response;
    }

    @Override
    public List<SanPham> getSP() {
        return chiTietSPRepository.getSanPham();
    }

    @Override
    public List<MauSac> getMauSac() {
        return chiTietSPRepository.getMau();
    }

    @Override
    public List<NhaCungCap> getNCC() {
        return chiTietSPRepository.getNCC();
    }

    @Override
    public List<ThuongHieu> getTH() {
        return chiTietSPRepository.getTH();
    }

    @Override
    public List<KichThuoc> getSize() {
        return chiTietSPRepository.getSize();
    }

    @Override
    public List<DeGiay> getDeGiay() {
        return chiTietSPRepository.getDeGiay();
    }

    @Override
    public Boolean create(ChiTietSP chiTietSP) {
        return chiTietSPRepository.add(chiTietSP);
    }

    @Override
    public Boolean delete(String id) {
        return chiTietSPRepository.delete(id);
    }

    @Override
    public List<ViewChiTietSPResponse> timKiem(String tenSP) {
        List<ViewChiTietSPResponse> listTimKiem = new ArrayList<>();
        List<ChiTietSP> list = chiTietSPRepository.timKiem(tenSP);
        for (ChiTietSP chiTietSP : list) {
            ViewChiTietSPResponse chiTietSPResponse = new ViewChiTietSPResponse(chiTietSP);
            listTimKiem.add(chiTietSPResponse);
        }
        return listTimKiem;
    }

    @Override
    public int genMaCTSPTuDong() {
        return chiTietSPRepository.genMaCTSP();
    }
    
}
