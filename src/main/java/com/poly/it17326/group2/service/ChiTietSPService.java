package com.poly.it17326.group2.service;
//

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.domainmodel.MauSac;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import java.util.List;

public interface ChiTietSPService {

    List<ViewChiTietSPResponse> getAll();

    List<SanPham> getSP();

    List<MauSac> getMauSac();

    List<NhaCungCap> getNCC();

    List<ThuongHieu> getTH();

    List<KichThuoc> getSize();

    List<DeGiay> getDeGiay();

    Boolean create(ChiTietSP chiTietSP);

    Boolean delete(String id);

    List<ViewChiTietSPResponse> timKiem(String tenSP);

    int genMaCTSPTuDong();
}
