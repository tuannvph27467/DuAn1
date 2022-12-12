package com.poly.it17326.group2.util;

import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.repository.KhachHangRepository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author
 */
public class DailyResetRankCustomer extends Thread {

    private static final KhachHangRepository khachHangRepository = new KhachHangRepository();

    public static void dailyChecking() {
        Thread countDownThread = new Thread() {
            @Override
            public void run() {
                do {
                    List<KhachHang> listKhachHang = khachHangRepository.getAllKhacHangDangHoatDong();
//                    for (KhachHang xx : listKhachHang) {
//                        if (xx.getThoiDiemResetGanNhat() == null) {
//                            if (xx.getCreatedDate() + 15552000000L < Calendar.getInstance().getTimeInMillis()) {
//                                xx.setThoiDiemResetGanNhat(Calendar.getInstance().getTimeInMillis());
//                                xx.setCapBac(0);
//                                khachHangRepository.add(xx);
//                            }
//                        } else {
//                            if (xx.getThoiDiemResetGanNhat() + 15552000000L < Calendar.getInstance().getTimeInMillis()) {
//                                xx.setThoiDiemResetGanNhat(Calendar.getInstance().getTimeInMillis());
//                                xx.setCapBac(0);
//                                khachHangRepository.add(xx);
//                            }
//                        }
//                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        };
        countDownThread.start();
    }
}
