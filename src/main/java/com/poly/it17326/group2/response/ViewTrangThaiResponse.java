
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.TrangThai;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ViewTrangThaiResponse {
    
    private String id;
    private String ma;
    private int ten;
    private String ngayTao;

    public ViewTrangThaiResponse() {
    }

    public ViewTrangThaiResponse(TrangThai trangThai) {
        this.id = trangThai.getId();
        this.ma = trangThai.getMa();
        this.ten = trangThai.getTenTrangThai();
        this.ngayTao = trangThai.getNgayTao();
    }
    
}
