package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.ChucVu;
import com.poly.it17326.group2.repository.ChucVuRepository;
import com.poly.it17326.group2.response.ViewChucVuResponse;
import java.util.ArrayList;
import java.util.List;
import com.poly.it17326.group2.service.ICommon;
//hocnvph27417
public class ChucVuServiceImpl implements ICommon<ViewChucVuResponse, ChucVu>{

    private ChucVuRepository chucVuRepository = new ChucVuRepository();
    
    @Override
    public List<ViewChucVuResponse> getAll() {
        List<ViewChucVuResponse> response = new ArrayList<>();
        List<ChucVu> list = chucVuRepository.getAll();
        for (ChucVu chucVu : list) {
            ViewChucVuResponse viewChucVuResponse = new ViewChucVuResponse(chucVu);
            response.add(viewChucVuResponse);
        }
        return response;
    }   

    @Override
    public Boolean create(ChucVu chucVu) {
        return chucVuRepository.add(chucVu);
    }   

    @Override
    public Boolean delete(String id) {
        return chucVuRepository.delete(id);
    }

    @Override
    public Boolean update(ChucVu chucVu) {
        return chucVuRepository.add(chucVu);
    }

    @Override
    public int genMaTuDong() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
