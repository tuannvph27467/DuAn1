/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.ThuongHieu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author PCchiendqph25320
 */
@Getter
@Setter
@ToString

public class ViewThuongHieuReposponse {
    private String id;
    private String ma;
    private String ten;

    public ViewThuongHieuReposponse() {
    }

    public ViewThuongHieuReposponse(ThuongHieu thuongHieu) {
        this.id = thuongHieu.getId();
        this.ma = thuongHieu.getMa();
        this.ten = thuongHieu.getTen();
    }
    
}
