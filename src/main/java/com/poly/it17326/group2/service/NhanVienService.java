package com.poly.it17326.group2.service;

import com.poly.it17326.group2.domainmodel.ChucVu;
import com.poly.it17326.group2.domainmodel.NhanVien;
import com.poly.it17326.group2.response.ViewNhanVienResponse;
import java.util.List;

public interface NhanVienService {

    List<ViewNhanVienResponse> getAll();

    Boolean add(NhanVien taiKhoan);

    Boolean update(NhanVien taiKhoan);

    Boolean delete(String id);

    List<ChucVu> getChucVu();

    NhanVien getByEmailAndMatKhau(String email, String pass);

    NhanVien getByEmail(String email);
    
    Boolean updateMatKhau(String email, String matKhau);
}
