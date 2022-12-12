package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.ChucVu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewChucVuResponse {

    private String id;
    private String maCV;
    private String tenCV;

    public ViewChucVuResponse() {
    }

    public ViewChucVuResponse(ChucVu chucVu) {
        this.id = chucVu.getId();
        this.maCV = chucVu.getMa();
        this.tenCV = chucVu.getTen();
    }   
}
