/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.KichThuoc;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author haodqph27423
 */

@Getter
@Setter
@ToString
public class ViewKichThuocReponse {
    private String id;
    private String ma;
    private String ten;

    public ViewKichThuocReponse() {
    }
    // maping dữ liệu
    public ViewKichThuocReponse(KichThuoc kichThuoc) {
        this.id = kichThuoc.getId();
        this.ma = kichThuoc.getMa();
        this.ten = kichThuoc.getTen();
    }
}
