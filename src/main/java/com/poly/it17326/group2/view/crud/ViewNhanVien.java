    package com.poly.it17326.group2.view.crud;

import com.poly.it17326.group2.domainmodel.ChucVu;
import com.poly.it17326.group2.domainmodel.NhanVien;
import com.poly.it17326.group2.repository.ChucVuRepository;
import com.poly.it17326.group2.response.ViewChucVuResponse;
import com.poly.it17326.group2.response.ViewNhanVienResponse;
import com.poly.it17326.group2.service.ICommon;
import com.poly.it17326.group2.service.impl.ChucVuServiceImpl;
import com.poly.it17326.group2.service.impl.NhanVienServiceImpl;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.poly.it17326.group2.service.NhanVienService;

public class ViewNhanVien extends javax.swing.JFrame {

    private DefaultComboBoxModel dcbm;

    private DefaultTableModel dtm;

    private NhanVienService taiKhoanService = new NhanVienServiceImpl();

    private ICommon<ViewChucVuResponse, ChucVu> chucVuService = new ChucVuServiceImpl();

    private ChucVuRepository chucVuRepository = new ChucVuRepository();

    
    
    public ViewNhanVien() {
        initComponents();
        setLocationRelativeTo(null);
        loadCbb(chucVuService.getAll());
        setDefaultCloseOperation(ViewNhanVien.DISPOSE_ON_CLOSE);
    }

    private void loadCbb(List<ViewChucVuResponse> listChucVu) {
        dcbm = (DefaultComboBoxModel) cbbChucVu.getModel();
        for (ViewChucVuResponse viewChucVuResponse : listChucVu) {
            dcbm.addElement(viewChucVuResponse.getTenCV());
        }
    }

    private void loadTable(List<ViewNhanVienResponse> listTaiKhoan) {
        dtm = (DefaultTableModel) tblListTaiKhoan.getModel();
        dtm.setRowCount(0);
        for (ViewNhanVienResponse vtkr : listTaiKhoan) {
            dtm.addRow(new Object[]{
                vtkr.getId(), vtkr.getMa(), vtkr.getHoTen(),
                vtkr.getEmail(), vtkr.getNgayTao(),
                vtkr.getNgaySua(), vtkr.getCccd(), vtkr.getSdt(),
                vtkr.getMatKhau(), vtkr.getTenChucVu(), vtkr.htTrangThai()
            });
        }
    }

    private String getDate() {
        Calendar ca = new GregorianCalendar();
        int year = ca.get(Calendar.YEAR);
        int mon = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DAY_OF_MONTH);

        String ngay = year + "-" + mon + "-" + day;
        return ngay;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTrangThai = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListTaiKhoan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtTenTaiKhoan = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        txtNgaySua = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        rdoDiLam = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();
        cbbChucVu = new javax.swing.JComboBox<>();
        btnDoc = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TÀI KHOẢN");

        tblListTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã", "Họ tên", "Tên tài khoản", "Ngày tạo", "Ngày sửa", "CCCD", "SDT", "Mật khẩu", "Chức vụ", "Trạng thái"
            }
        ));
        tblListTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListTaiKhoan);

        jLabel2.setText("ID");

        jLabel3.setText("Mã");

        jLabel4.setText("Họ tên");

        jLabel5.setText("Tên tài khoản");

        jLabel6.setText("Ngày tạo");

        jLabel7.setText("Ngày sửa");

        jLabel8.setText("CCCD");

        jLabel9.setText("SDT");

        jLabel10.setText("Mật khẩu");

        jLabel11.setText("Trang thái");

        jLabel12.setText("Chức vụ");

        bgrTrangThai.add(rdoDiLam);
        rdoDiLam.setText("Đi làm");

        bgrTrangThai.add(rdoNghiViec);
        rdoNghiViec.setText("Nghỉ việc");

        btnDoc.setText("Đọc");
        btnDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoDiLam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNghiViec))
                                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(65, 65, 65)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGap(84, 84, 84)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(txtMa)))))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySua, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoDiLam)
                                .addComponent(rdoNghiViec))))
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocActionPerformed
        loadTable(taiKhoanService.getAll());
    }//GEN-LAST:event_btnDocActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        NhanVien taiKhoan = new NhanVien();
        taiKhoan.setMa(txtMa.getText());
        taiKhoan.setHoTen(txtHoTen.getText());
        taiKhoan.setNgayTao(getDate());
        taiKhoan.setCccd(txtCCCD.getText());
        taiKhoan.setSdt(txtSDT.getText());
        taiKhoan.setMatKhau(txtMatKhau.getText());
        Integer trangThai = 0;
        if (rdoDiLam.isSelected()) {
            trangThai = 1;
        }
        taiKhoan.setTrangThai(trangThai);
        int tenChucVu = cbbChucVu.getSelectedIndex();
        ChucVu chucVu = taiKhoanService.getChucVu().get(tenChucVu);
        taiKhoan.setChucVu(chucVu);

        if (!taiKhoanService.add(taiKhoan)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTable(taiKhoanService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        NhanVien taiKhoan = new NhanVien();
        taiKhoan.setId(txtID.getText());
        taiKhoan.setMa(txtMa.getText());
        taiKhoan.setHoTen(txtHoTen.getText());
        taiKhoan.setNgayTao(txtNgayTao.getText());
        taiKhoan.setNgaySua(getDate());
        taiKhoan.setCccd(txtCCCD.getText());
        taiKhoan.setSdt(txtSDT.getText());
        taiKhoan.setMatKhau(txtMatKhau.getText());
        Integer trangThai = 0;
        if (rdoDiLam.isSelected()) {
            trangThai = 1;
        }
        taiKhoan.setTrangThai(trangThai);
        int tenChucVu = cbbChucVu.getSelectedIndex();
        ChucVu chucVu = chucVuRepository.getAll().get(tenChucVu);
        taiKhoan.setChucVu(chucVu);

        if (taiKhoanService.add(taiKhoan)) {
            JOptionPane.showMessageDialog(this, "Update thành công");
            loadTable(taiKhoanService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Update thất bại");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = txtID.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn tài khoản để xóa");
        } else if (taiKhoanService.delete(id)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadTable(taiKhoanService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblListTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListTaiKhoanMouseClicked
        int row = tblListTaiKhoan.getSelectedRow();
        if (txtNgaySua.getText().equals("")) {
            txtID.setText(tblListTaiKhoan.getValueAt(row, 0).toString());
            txtMa.setText(tblListTaiKhoan.getValueAt(row, 1).toString());
            txtHoTen.setText(tblListTaiKhoan.getValueAt(row, 2).toString());
            txtTenTaiKhoan.setText(tblListTaiKhoan.getValueAt(row, 3).toString());
            txtNgayTao.setText(tblListTaiKhoan.getValueAt(row, 4).toString());
            txtCCCD.setText(tblListTaiKhoan.getValueAt(row, 6).toString());
            txtSDT.setText(tblListTaiKhoan.getValueAt(row, 7).toString());
            txtMatKhau.setText(tblListTaiKhoan.getValueAt(row, 8).toString());
            cbbChucVu.getModel().setSelectedItem(tblListTaiKhoan.getValueAt(row, 9));
            if (tblListTaiKhoan.getValueAt(row, 10).toString().equals("Đi làm")) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiViec.setSelected(true);
            }
        } else {
            txtID.setText(tblListTaiKhoan.getValueAt(row, 0).toString());
            txtMa.setText(tblListTaiKhoan.getValueAt(row, 1).toString());
            txtHoTen.setText(tblListTaiKhoan.getValueAt(row, 2).toString());
            txtTenTaiKhoan.setText(tblListTaiKhoan.getValueAt(row, 3).toString());
            txtNgayTao.setText(tblListTaiKhoan.getValueAt(row, 4).toString());
            txtNgaySua.setText(tblListTaiKhoan.getValueAt(row, 5).toString());
            txtCCCD.setText(tblListTaiKhoan.getValueAt(row, 6).toString());
            txtSDT.setText(tblListTaiKhoan.getValueAt(row, 7).toString());
            txtMatKhau.setText(tblListTaiKhoan.getValueAt(row, 8).toString());
            cbbChucVu.getModel().setSelectedItem(tblListTaiKhoan.getValueAt(row, 9));
            if (tblListTaiKhoan.getValueAt(row, 10).toString().equals("Đi làm")) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiViec.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblListTaiKhoanMouseClicked

    public static void main(String[] args) {
        new ViewNhanVien().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTrangThai;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDoc;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoDiLam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JTable tblListTaiKhoan;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySua;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables

}
