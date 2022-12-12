/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.response;

import com.poly.it17326.group2.domainmodel.DeGiay;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nguye
 */

@Getter
@Setter
@ToString
public class ViewDeGiayResponse {
    private String id;
    private String ma;
    private String ten;

    public ViewDeGiayResponse() {
    }

    public ViewDeGiayResponse(DeGiay deGiay) {
        this.id = deGiay.getId();
        this.ma = deGiay.getMa();
        this.ten = deGiay.getTen();
    }
    
    
}
