/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group2.service;

import java.util.List;

/**
 *
 * @author nguye
 */
public interface ICommon<T,Y> {
    List<T> getAll();
    Boolean create(Y y);
    Boolean update(Y y);
    Boolean delete(String id);
    int genMaTuDong();
}
