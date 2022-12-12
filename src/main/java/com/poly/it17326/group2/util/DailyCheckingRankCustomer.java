/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.util;

import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.repository.KhachHangRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class DailyCheckingRankCustomer extends Thread {

    private static final KhachHangRepository khachHangRespository = new KhachHangRepository();

    public static void dailyChecking() {
        Thread countDownThread = new Thread() {
            @Override
            public void run() {
                do {
                    List<KhachHang> listKhachHang = khachHangRespository.getAllKhacHangDangHoatDong();
                    for (KhachHang xx : listKhachHang) {
                        BigDecimal tienKhachMua = khachHangRespository.getTongTienByIdKhachHang(xx.getId());
                        if (tienKhachMua == null) {
                            continue;
                        }
                        if (tienKhachMua != null) {
                            if (tienKhachMua.compareTo(new BigDecimal(3000000)) < 0) {
                                xx.setCapBac(0);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(3000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(5000000)) < 0) {
                                xx.setCapBac(1);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(5000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(10000000)) < 0) {
                                xx.setCapBac(2);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(10000000)) > 0) {
                                xx.setCapBac(3);
                            }
                            khachHangRespository.add(xx);
                        }
                    }
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        };
        countDownThread.start();
    }

    public static void dailyCheckingRankCustomer() {
        Thread countDownThread = new Thread() {
            @Override
            public void run() {
                do {
                    List<KhachHang> listKhachHang = khachHangRespository.getAllKhacHangDangHoatDong();
                    for (KhachHang xx : listKhachHang) {
                        BigDecimal tienKhachMua = khachHangRespository.getTongTienByIdKhachHang(xx.getId());
                        if (tienKhachMua == null) {
                            continue;
                        }
                        if (tienKhachMua != null) {
                            if (tienKhachMua.compareTo(new BigDecimal(3000000)) < 0) {
                                xx.setCapBac(0);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(3000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(5000000)) < 0) {
                                xx.setCapBac(1);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(5000000)) > 0 && tienKhachMua.compareTo(new BigDecimal(10000000)) < 0) {
                                xx.setCapBac(2);
                            }
                            if (tienKhachMua.compareTo(new BigDecimal(10000000)) > 0) {
                                xx.setCapBac(3);
                            }
                            khachHangRespository.updateCapBac(xx, xx.getId());
                        }
                    }
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        };
        countDownThread.start();
    }
}
