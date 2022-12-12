/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.repository.ThuongHieuRepository;
import com.poly.it17326.group2.response.ViewThuongHieuReposponse;
import com.poly.it17326.group2.service.ICommon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ThuongHieuServiceImpl implements ICommon<ViewThuongHieuReposponse, ThuongHieu> {

    private ThuongHieuRepository thuongHieuRepository = new ThuongHieuRepository();

   @Override
    public List<ViewThuongHieuReposponse> getAll() {
        List<ViewThuongHieuReposponse> responses = new ArrayList<>();
        List<ThuongHieu> lists = thuongHieuRepository.getList();
        for (ThuongHieu thuongHieu : lists) {
            ViewThuongHieuReposponse viewDeGiayResponse = new ViewThuongHieuReposponse(thuongHieu);
            responses.add(viewDeGiayResponse);
        }
        return responses;
    }


    @Override
    public Boolean create(ThuongHieu thuongHieu) {
        return thuongHieuRepository.them(thuongHieu);
    }

    @Override
    public Boolean update(ThuongHieu thuongHieu) {
        return thuongHieuRepository.them(thuongHieu);
    }

    @Override
    public Boolean delete(String id) {
        return thuongHieuRepository.xoa(id);
    }

    @Override
    public int genMaTuDong() {
        return thuongHieuRepository.genMaThuongHieu();
    }
}
