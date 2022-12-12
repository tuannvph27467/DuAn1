package com.poly.it17326.group2.view.crud;

import com.poly.it17326.group2.domainmodel.ChucVu;
import com.poly.it17326.group2.response.ViewChucVuResponse;
import com.poly.it17326.group2.service.impl.ChucVuServiceImpl;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.poly.it17326.group2.service.ICommon;
import javax.swing.JOptionPane;

public class ViewChucVu extends javax.swing.JFrame {

    private DefaultTableModel defaultTableModel;

    private ICommon<ViewChucVuResponse, ChucVu> chucVuService = new ChucVuServiceImpl();

    public ViewChucVu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public void loadTable(List<ViewChucVuResponse> chucVuResponses) {
        defaultTableModel = (DefaultTableModel) tblListChucVu.getModel();
        defaultTableModel.setRowCount(0);
        for (ViewChucVuResponse chucVuResponse : chucVuResponses) {
            defaultTableModel.addRow(new Object[]{
                chucVuResponse.getId(), chucVuResponse.getMaCV(),
                chucVuResponse.getTenCV()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListChucVu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaCV = new javax.swing.JTextField();
        txtTenCV = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDoc = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtIdCV = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHỨC VỤ");

        tblListChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã chức vụ", "Tên chức vụ"
            }
        ));
        tblListChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListChucVuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListChucVu);

        jLabel2.setText("Mã chức vụ");

        jLabel3.setText("Tên chức vụ");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnDoc.setText("Đọc");
        btnDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel4.setText("ID chức vụ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdCV)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDoc))
                            .addComponent(txtTenCV)
                            .addComponent(txtMaCV))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnDoc)
                    .addComponent(btnUpdate))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        ChucVu chucVu = new ChucVu();
        chucVu.setMa(txtMaCV.getText());
        chucVu.setTen(txtTenCV.getText());

        if (!chucVuService.create(chucVu)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTable(chucVuService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocActionPerformed
        loadTable(chucVuService.getAll());
    }//GEN-LAST:event_btnDocActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        ChucVu chucVu = new ChucVu();
        chucVu.setId(txtIdCV.getText());
        chucVu.setMa(txtMaCV.getText());
        chucVu.setTen(txtTenCV.getText());

        if (chucVuService.update(chucVu)) {
            JOptionPane.showMessageDialog(this, "Update thành công");
            loadTable(chucVuService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Update thất bại");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblListChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListChucVuMouseClicked
        int row = tblListChucVu.getSelectedRow();
        txtIdCV.setText(tblListChucVu.getValueAt(row, 0).toString());
        txtMaCV.setText(tblListChucVu.getValueAt(row, 1).toString());
        txtTenCV.setText(tblListChucVu.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tblListChucVuMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = txtIdCV.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn chức vụ dể xóa");
        } else if (chucVuService.delete(id)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadTable(chucVuService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    public static void main(String[] args) {
        new ViewChucVu().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDoc;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListChucVu;
    private javax.swing.JTextField txtIdCV;
    private javax.swing.JTextField txtMaCV;
    private javax.swing.JTextField txtTenCV;
    // End of variables declaration//GEN-END:variables
}
