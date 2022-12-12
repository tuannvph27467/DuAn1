/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group2.view;

import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.response.ViewKhachHangResponse;
import com.poly.it17326.group2.response.ViewLichSuGiaoDichKhachHang;
import com.poly.it17326.group2.service.KhachHangService;
import com.poly.it17326.group2.service.impl.KhachHangServiceImpl;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class ViewKhachHang extends javax.swing.JPanel {

    private final KhachHangService khachHangService = new KhachHangServiceImpl();

    private DefaultTableModel tableModelKH;

    private DefaultTableModel tableModelLichSuGiaoDich;

    public ViewKhachHang() {
        initComponents();
        loadKhachHang(khachHangService.getAll());
    }

    private String getDate() {
        Calendar ca = new GregorianCalendar();
        int year = ca.get(Calendar.YEAR);
        int mon = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DAY_OF_MONTH);

        String ngay = year + "-" + mon + "-" + day;
        return ngay;
    }

    private void loadKhachHang(List<ViewKhachHangResponse> listKH) {
        tableModelKH = (DefaultTableModel) tblTTKhachHang.getModel();
        tableModelKH.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= listKH.size(); i++) {
            for (ViewKhachHangResponse khachHangResponse : listKH) {
                tableModelKH.addRow(new Object[]{
                    i++, khachHangResponse.getMa(), khachHangResponse.getHoTen(),
                    khachHangResponse.htGioiTinh(),
                    dateFormat.format(khachHangResponse.getNgaySinh()),
                    khachHangResponse.getSdt(), khachHangResponse.getDiaChi(),
                    khachHangResponse.htCapBac()
                });
            }
        }
    }

    private void loadLichSuGiaoDich(List<ViewLichSuGiaoDichKhachHang> list) {
        tableModelLichSuGiaoDich = (DefaultTableModel) tblLichSuGD.getModel();
        tableModelLichSuGiaoDich.setRowCount(0);
        for (int i = 1; i <= list.size(); i++) {
            for (ViewLichSuGiaoDichKhachHang lichSuGiaoDichKhachHang : list) {
                tableModelLichSuGiaoDich.addRow(new Object[]{
                    i++, lichSuGiaoDichKhachHang.getTenKH(), lichSuGiaoDichKhachHang.getSdt(),
                    lichSuGiaoDichKhachHang.htGioiTinh(), lichSuGiaoDichKhachHang.getMaHD(),
                    lichSuGiaoDichKhachHang.getNgayThanhToan(),lichSuGiaoDichKhachHang.getTongTien(),
                    lichSuGiaoDichKhachHang.htCapBac()
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrGioiTinh = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        pbgTienDo = new javax.swing.JProgressBar();
        jLabel30 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        rbnNam = new javax.swing.JRadioButton();
        rbnNu = new javax.swing.JRadioButton();
        cbbCapBac = new javax.swing.JComboBox<>();
        txtDiaChi = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        dateNgaySinh = new com.github.lgooddatepicker.components.DatePicker();
        jPanel25 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblTTKhachHang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblLichSuGD = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thiết lập thông tin khách hàng"));

        pbgTienDo.setBackground(new java.awt.Color(255, 255, 255));
        pbgTienDo.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        pbgTienDo.setForeground(new java.awt.Color(255, 102, 102));
        pbgTienDo.setStringPainted(true);

        jLabel30.setText("Mã Khách Hàng:");
        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtMaKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaKH.setEnabled(false);

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel31.setText("Tên Khách Hàng:");
        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel32.setText("SĐT:");
        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel33.setText("Giới Tính:");
        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        rbnNam.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rbnNam);
        rbnNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbnNam.setText("Nam");

        rbnNu.setBackground(new java.awt.Color(255, 255, 255));
        bgrGioiTinh.add(rbnNu);
        rbnNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbnNu.setText("Nữ");

        cbbCapBac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đồng", "Bạc", "Vàng", "Kim cương" }));
        cbbCapBac.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel35.setText("Địa Chỉ:");
        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel36.setText("Ngày Sinh:");
        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel37.setText("Tiến Trình:");
        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel38.setText("Cấp Bậc:");
        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnThem)
                .addGap(30, 30, 30)
                .addComponent(btnSua)
                .addGap(31, 31, 31)
                .addComponent(btnLamMoi)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel30)
                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(rbnNu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbCapBac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbgTienDo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbnNu)
                                    .addComponent(rbnNam)
                                    .addComponent(jLabel33))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel36)
                                .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(29, 29, 29)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38))
                            .addGap(33, 33, 33)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pbgTienDo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        tblTTKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Tên KH", "Giới tính", "Ngày sinh", "SĐT", "Địa chỉ", "Cấp bậc"
            }
        ));
        tblTTKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTTKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblTTKhachHangMouseEntered(evt);
            }
        });
        jScrollPane12.setViewportView(tblTTKhachHang);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thông tin khách hàng", jPanel26);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        tblLichSuGD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên KH", "SĐT", "Giới Tính", "Mã HĐ", "Ngày giao dịch", "Tổng tiền", "Cấp Bậc"
            }
        ));
        jScrollPane14.setViewportView(tblLichSuGD);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lịch sử giao dịch", jPanel27);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Thông tin khách hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        dateNgaySinh.setText("");
        cbbCapBac.setSelectedIndex(0);
        rbnNam.setSelected(false);
        rbnNu.setSelected(false);
        bgrGioiTinh.clearSelection();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKhachHang("KH" + khachHangService.genMaKH());
            khachHang.setNgayTao(getDate());
            khachHang.setHoTen(txtTenKH.getText());
            khachHang.setSdt(txtSDT.getText());
            int gioiTinh = 0;
            if (rbnNam.isSelected()) {
                gioiTinh = 0;
            } else {
                gioiTinh = 1;
            }
            khachHang.setGioiTinh(gioiTinh);
            khachHang.setNgaySinh(java.sql.Date.valueOf(dateNgaySinh.getDate()));
            khachHang.setDiaChi(txtDiaChi.getText());
            if (!khachHangService.create(khachHang)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                KhachHangService service = new KhachHangServiceImpl();
                loadKhachHang(service.getAll());
                tblTTKhachHang.setRowSelectionInterval(0, 0);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblTTKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTTKhachHangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTTKhachHangMouseEntered

    private void tblTTKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTTKhachHangMouseClicked
        int rowKH = tblTTKhachHang.getSelectedRow();
        ViewKhachHangResponse response = khachHangService.getAll().get(rowKH);
        txtMaKH.setText(tblTTKhachHang.getValueAt(rowKH, 1).toString());
        txtTenKH.setText(tblTTKhachHang.getValueAt(rowKH, 2).toString());
        txtSDT.setText(tblTTKhachHang.getValueAt(rowKH, 5).toString());
        dateNgaySinh.setDate(LocalDate.parse(tblTTKhachHang.getValueAt(rowKH, 4).toString()));
        if (tblTTKhachHang.getValueAt(rowKH, 3).toString().equals("Nam")) {
            rbnNam.setSelected(true);
        } else {
            rbnNu.setSelected(true);
        }
        txtDiaChi.setText(tblTTKhachHang.getValueAt(rowKH, 6).toString());
        cbbCapBac.getModel().setSelectedItem(tblTTKhachHang.getValueAt(rowKH, 7));

        String idKH = null;
        for (ViewKhachHangResponse khachHangResponse : khachHangService.getAll()) {
            if (khachHangResponse.getMa().equals(tblTTKhachHang.getValueAt(rowKH, 1))) {
                idKH = khachHangResponse.getId();
            }
        }
        loadLichSuGiaoDich(khachHangService.getLichSuGiaoDich(idKH));

        if (response.getMa().equals("KH000")) {
            pbgTienDo.setMaximum(0);
            pbgTienDo.setValue(0);
        }
        if (response.getCapBac() == 0 && !response.getMa().equals("KH000")) {
            pbgTienDo.setMaximum(3000000);
            BigDecimal money = khachHangService.getTongTienByIdKhachHang(response.getId());
            if (money != null) {
                int tien = (int) money.doubleValue();
                pbgTienDo.setValue(tien);
            }
        }
        if (response.getCapBac() == 1) {
            pbgTienDo.setMaximum(5000000);
            BigDecimal money = khachHangService.getTongTienByIdKhachHang(response.getId());
            if (money != null) {
                int tien = (int) money.doubleValue();
                pbgTienDo.setValue(tien);
            }
        }
        if (response.getCapBac() == 2) {
            pbgTienDo.setMaximum(10000000);
            BigDecimal money = khachHangService.getTongTienByIdKhachHang(response.getId());
            if (money != null) {
                int tien = (int) money.doubleValue();
                pbgTienDo.setValue(tien);
            }
        }

        if (response.getCapBac() == 3) {
            pbgTienDo.setMaximum(20000000);
            BigDecimal money = khachHangService.getTongTienByIdKhachHang(response.getId());
            if (money != null) {
                int tien = (int) money.doubleValue();
                pbgTienDo.setValue(tien);
            }
        }
    }//GEN-LAST:event_tblTTKhachHangMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            int rowKH = tblTTKhachHang.getSelectedRow();
            if (rowKH == -1) {
                JOptionPane.showMessageDialog(this, "Chọn khách hàng để sửa");
                return;
            }
            KhachHang khachHang = new KhachHang();
            String idKH = null;
            String ngayTao = null;
            for (ViewKhachHangResponse khachHangResponse : khachHangService.getAll()) {
                if (khachHangResponse.getMa().equals(txtMaKH.getText())) {
                    idKH = khachHangResponse.getId();
                    ngayTao = khachHangResponse.getNgayTao();
                }
            }
            khachHang.setId(idKH);
            khachHang.setMaKhachHang(txtMaKH.getText());
            khachHang.setNgayTao(ngayTao);
            khachHang.setHoTen(txtTenKH.getText());
            khachHang.setSdt(txtSDT.getText());
            int gioiTinh = 0;
            if (rbnNam.isSelected()) {
                gioiTinh = 0;
            } else {
                gioiTinh = 1;
            }
            khachHang.setGioiTinh(gioiTinh);
            khachHang.setNgaySinh(java.sql.Date.valueOf(dateNgaySinh.getDate()));
            khachHang.setDiaChi(txtDiaChi.getText());
            if (khachHangService.create(khachHang)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                KhachHangService service = new KhachHangServiceImpl();
                loadKhachHang(service.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        String tenKH = txtTimKiem.getText();
        List<ViewKhachHangResponse> listKH = khachHangService.getByHoTenKhach(tenKH);
        loadKhachHang(listKH);
    }//GEN-LAST:event_txtTimKiemKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrGioiTinh;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbbCapBac;
    private com.github.lgooddatepicker.components.DatePicker dateNgaySinh;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JProgressBar pbgTienDo;
    private javax.swing.JRadioButton rbnNam;
    private javax.swing.JRadioButton rbnNu;
    private javax.swing.JTable tblLichSuGD;
    private javax.swing.JTable tblTTKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
