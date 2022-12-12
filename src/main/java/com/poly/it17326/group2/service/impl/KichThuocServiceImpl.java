/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.repository.KichThuocRepository;
import com.poly.it17326.group2.response.ViewKichThuocReponse;
import com.poly.it17326.group2.service.ICommon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haodqph27423
 */
public class KichThuocServiceImpl implements ICommon<ViewKichThuocReponse, KichThuoc> {

    private KichThuocRepository kichThuocRepository = new KichThuocRepository();

    @Override
    public List<ViewKichThuocReponse> getAll() {
        List<ViewKichThuocReponse> listReponse = new ArrayList<>();
        List<KichThuoc> listKichThuoc = kichThuocRepository.getListFromDB();// lấy dữ liệu từ repository
        for (KichThuoc size : listKichThuoc) {
            ViewKichThuocReponse viewSizeReponse = new ViewKichThuocReponse(size);
            listReponse.add(viewSizeReponse);
        }
        return listReponse;
    }

    @Override
    public Boolean create(KichThuoc y) {
        return kichThuocRepository.addNew(y);
    }

    @Override
    public Boolean update(KichThuoc y) {
        return kichThuocRepository.upDate(y);
    }

    @Override
    public Boolean delete(String id) {
        return kichThuocRepository.delete(id);
    }
    
    @Override
    public int genMaTuDong() {
        return kichThuocRepository.genMaKichThuoc();
    }

}
