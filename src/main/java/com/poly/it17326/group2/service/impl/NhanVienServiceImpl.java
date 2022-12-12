package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.ChucVu;
import com.poly.it17326.group2.domainmodel.NhanVien;
import com.poly.it17326.group2.repository.NhanVienRepository;
import com.poly.it17326.group2.response.ViewChucVuResponse;
import com.poly.it17326.group2.response.ViewNhanVienResponse;
import java.util.ArrayList;
import java.util.List;
import com.poly.it17326.group2.service.NhanVienService;
//hocnvph27417
public class NhanVienServiceImpl implements NhanVienService{

    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    
    @Override
    public List<ViewNhanVienResponse> getAll() {
        List<ViewNhanVienResponse> response = new ArrayList<>();
        List<NhanVien> list = nhanVienRepository.getAll();
        for (NhanVien taiKhoan : list) {
            ViewNhanVienResponse viewTaiKhoanResponse = new ViewNhanVienResponse(taiKhoan);
            response.add(viewTaiKhoanResponse);
        }
        return response;
    }

    @Override
    public Boolean add(NhanVien taiKhoan) {
        return nhanVienRepository.add(taiKhoan);
    }

    @Override
    public Boolean update(NhanVien taiKhoan) {
        return nhanVienRepository.add(taiKhoan);
    }

    @Override
    public Boolean delete(String id) {
        return nhanVienRepository.delete(id);
    }

    @Override
    public List<ChucVu> getChucVu() {
        return nhanVienRepository.getChucVu();
    }

    @Override
    public NhanVien getByEmailAndMatKhau(String email, String pass) {
        return nhanVienRepository.getByEmailAndMatKhau(email, pass);
    }

    @Override
    public NhanVien getByEmail(String email) {
        return nhanVienRepository.getByEmail(email);
    }

    @Override
    public Boolean updateMatKhau(String email, String matKhau) {
        return nhanVienRepository.updateMatKhau(email, matKhau);
    }

}
