package com.poly.it17326.group2.service.impl;
//

import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.repository.SanPhamRepository;
import com.poly.it17326.group2.response.ViewSanPhamResponse;
import com.poly.it17326.group2.service.ICommon;
import java.util.ArrayList;
import java.util.List;

public class SanPhamServiceImpl implements ICommon<ViewSanPhamResponse, SanPham> {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    public List<ViewSanPhamResponse> getAll() {
        List<ViewSanPhamResponse> response = new ArrayList<>();
        List<SanPham> list = sanPhamRepository.getAll();
        for (SanPham sanPham : list) {
            ViewSanPhamResponse sanPhamResponse = new ViewSanPhamResponse(sanPham);
            response.add(sanPhamResponse);
        }
        return response;
    }

    @Override
    public Boolean create(SanPham y) {
        return sanPhamRepository.add(y);
    }

    @Override
    public Boolean update(SanPham y) {
        return sanPhamRepository.add(y);
    }

    @Override
    public Boolean delete(String id) {
        return sanPhamRepository.delete(id);
    }

    @Override
    public int genMaTuDong() {
        return sanPhamRepository.genMaSanPham();
    }
    
}
