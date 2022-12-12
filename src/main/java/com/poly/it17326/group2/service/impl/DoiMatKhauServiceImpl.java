/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.repository.DoiMatKhauRepository;
import com.poly.it17326.group2.service.DoiMatKhau;

/**
 *
 * @author nguye
 */
public class DoiMatKhauServiceImpl implements DoiMatKhau {

    private DoiMatKhauRepository doiMatKhauRepository = new DoiMatKhauRepository();

    @Override
    public String GetIdNhanVien(String email, String matkhau) {
        return doiMatKhauRepository.GetIdNhanVien(email, matkhau);
    }

    @Override
    public Boolean DoiMatKhau(String matkhau, String id) {
        return doiMatKhauRepository.DoiMatKhau(matkhau, id);
    }

}
