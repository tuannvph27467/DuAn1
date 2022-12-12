/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.repository.DeGiayRepository;
import com.poly.it17326.group2.response.ViewDeGiayResponse;
import java.util.ArrayList;
import java.util.List;
import com.poly.it17326.group2.service.ICommon;

/**
 *
 * @author nguye
 */
public class DeGiayServiceImpl implements ICommon<ViewDeGiayResponse, DeGiay> {

    private DeGiayRepository deGiayRepository = new DeGiayRepository();

    @Override
    public List<ViewDeGiayResponse> getAll() {
        List<ViewDeGiayResponse> responses = new ArrayList<>();
        List<DeGiay> lists = deGiayRepository.getList();
        for (DeGiay deGiay : lists) {
            ViewDeGiayResponse viewDeGiayResponse = new ViewDeGiayResponse(deGiay);
            responses.add(viewDeGiayResponse);
        }
        return responses;
    }

    @Override
    public Boolean create(DeGiay deGiay) {
        return deGiayRepository.create(deGiay);
    }

    @Override
    public Boolean update(DeGiay deGiay) {
        return deGiayRepository.create(deGiay);
    }

    @Override
    public Boolean delete(String id) {
        return deGiayRepository.delete(id);
    }

    @Override
    public int genMaTuDong() {
        return deGiayRepository.genMaDeGiay();
    }
}
