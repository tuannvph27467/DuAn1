/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//phuongktph26630
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.MauSac;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewMauSacReponse {

    private String id;
    private String ma;
    private String ten;

    public ViewMauSacReponse() {
    }

    public ViewMauSacReponse(MauSac mauSac) {
        this.id = mauSac.getId();
        this.ma = mauSac.getMa();
        this.ten = mauSac.getTen();
    }
}
