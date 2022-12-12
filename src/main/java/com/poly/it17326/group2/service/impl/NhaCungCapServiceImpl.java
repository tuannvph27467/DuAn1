package com.poly.it17326.group2.service.impl;

import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.repository.NhaCungCapRepository;
import com.poly.it17326.group2.response.ViewNhaCungCapResponse;
import com.poly.it17326.group2.service.ICommon;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapServiceImpl implements ICommon<ViewNhaCungCapResponse, NhaCungCap> {

    private NhaCungCapRepository repository = new NhaCungCapRepository();

    @Override
    public List<ViewNhaCungCapResponse> getAll() {
        List<ViewNhaCungCapResponse> list = new ArrayList<>();
        List<NhaCungCap> listNhaCungCaps = repository.getList();// lấy dữ liệu từ repository
        for (NhaCungCap nhaCungCap : listNhaCungCaps) {
            ViewNhaCungCapResponse viewNhaCungCapResponse = new ViewNhaCungCapResponse(nhaCungCap);
            list.add(viewNhaCungCapResponse);
        }
        return list;
    }

    @Override
    public Boolean create(NhaCungCap y) {
        return repository.them(y);
    }

    @Override
    public Boolean update(NhaCungCap y) {
        return repository.them(y);

    }

    @Override
    public Boolean delete(String id) {
        return repository.xoa(id);
    }

    @Override
    public int genMaTuDong() {
        return repository.genMaNhaCungCap();
    }
}
