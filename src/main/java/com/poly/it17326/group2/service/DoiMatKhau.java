/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group2.service;

/**
 *
 * @author nguye
 */
public interface DoiMatKhau {
    String GetIdNhanVien(String email, String matkhau);
    
    Boolean DoiMatKhau(String matkhau, String id);
}
