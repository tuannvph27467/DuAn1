package com.poly.it17326.group2.view.crud;
//

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.domainmodel.MauSac;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import com.poly.it17326.group2.response.ViewDeGiayResponse;
import com.poly.it17326.group2.response.ViewMauSacReponse;
import com.poly.it17326.group2.response.ViewNhaCungCapResponse;
import com.poly.it17326.group2.response.ViewSanPhamResponse;
import com.poly.it17326.group2.response.ViewKichThuocReponse;
import com.poly.it17326.group2.response.ViewThuongHieuReposponse;
import com.poly.it17326.group2.service.BanHangService;
import com.poly.it17326.group2.service.ChiTietSPService;
import com.poly.it17326.group2.service.ICommon;
import com.poly.it17326.group2.service.impl.BanHangServiceImpl;
import com.poly.it17326.group2.service.impl.ChiTietSPServiceImpl;
import com.poly.it17326.group2.service.impl.SanPhamServiceImpl;
import com.poly.it17326.group2.service.impl.KichThuocServiceImpl;
import com.poly.it17326.group2.service.impl.DeGiayServiceImpl;
import com.poly.it17326.group2.service.impl.MauSacServiceImpl;
import com.poly.it17326.group2.service.impl.NhaCungCapServiceImpl;
import com.poly.it17326.group2.service.impl.ThuongHieuServiceImpl;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ViewChiTietSP extends javax.swing.JFrame {

    private DefaultComboBoxModel dcbm;

    private DefaultTableModel dtm;

    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();

    private ICommon<ViewKichThuocReponse, KichThuoc> sizeService = new KichThuocServiceImpl();

    private ICommon<ViewNhaCungCapResponse, NhaCungCap> nccService = new NhaCungCapServiceImpl();

    private ICommon<ViewDeGiayResponse, DeGiay> deGiayService = new DeGiayServiceImpl();

    private ICommon<ViewNhaCungCapResponse, NhaCungCap> nhaCungCapService = new NhaCungCapServiceImpl();

    private ICommon<ViewThuongHieuReposponse, ThuongHieu> thuongHieuService = new ThuongHieuServiceImpl();

    private ICommon<ViewMauSacReponse, MauSac> mauSacService = new MauSacServiceImpl();

    private ICommon<ViewSanPhamResponse, SanPham> sanPhamService = new SanPhamServiceImpl();

    private BanHangService banHangService = new BanHangServiceImpl();

    int inDex = 0;

    public ViewChiTietSP() {
        initComponents();
        setLocationRelativeTo(null);
        loadTable(chiTietSPService.getAll());
        loadNCC(nccService.getAll());
        loadSize(sizeService.getAll());
        loadDeGiay(deGiayService.getAll());
        loadThuongHieu(thuongHieuService.getAll());
        loadMau(mauSacService.getAll());
        loadSanPham(sanPhamService.getAll());
        setDefaultCloseOperation(ViewChiTietSP.DISPOSE_ON_CLOSE);
    }

    private void loadNCC(List<ViewNhaCungCapResponse> listNCC) {
        dcbm = (DefaultComboBoxModel) cbbNCC.getModel();
        for (ViewNhaCungCapResponse viewNhaCungCapResponse : listNCC) {
            dcbm.addElement(viewNhaCungCapResponse.getTen());
        }
    }

    private void loadSize(List<ViewKichThuocReponse> listSize) {
        dcbm = (DefaultComboBoxModel) cbbSize.getModel();
        for (ViewKichThuocReponse viewSizeReponse : listSize) {
            dcbm.addElement(viewSizeReponse.getTen());
        }
    }

    private void loadDeGiay(List<ViewDeGiayResponse> listDeGiay) {
        dcbm = (DefaultComboBoxModel) cbbDeGiay.getModel();
        for (ViewDeGiayResponse deGiayResponse : listDeGiay) {
            dcbm.addElement(deGiayResponse.getTen());
        }
    }

    private void loadThuongHieu(List<ViewThuongHieuReposponse> listThuongHieu) {
        dcbm = (DefaultComboBoxModel) cbbThuongHieu.getModel();
        for (ViewThuongHieuReposponse thuongHieuReposponse : listThuongHieu) {
            dcbm.addElement(thuongHieuReposponse.getTen());
        }
    }

    private void loadMau(List<ViewMauSacReponse> listMau) {
        dcbm = (DefaultComboBoxModel) cbbMauSac.getModel();
        for (ViewMauSacReponse mauSacReponse : listMau) {
            dcbm.addElement(mauSacReponse.getTen());
        }
    }

    private void loadSanPham(List<ViewSanPhamResponse> listSanPham) {
        dcbm = (DefaultComboBoxModel) cbbMaSP.getModel();
        for (ViewSanPhamResponse sanPhamResponse : listSanPham) {
            dcbm.addElement(sanPhamResponse.getMaSP());
        }
    }

    private void loadTable(List<ViewChiTietSPResponse> list) {
        dtm = (DefaultTableModel) tblChiTietSP.getModel();
        dtm.setRowCount(0);
        for (ViewChiTietSPResponse response : list) {
            dtm.addRow(new Object[]{
                response.getMaSP(), response.getTenSP(), response.getMauSac(),
                response.getThuongHieu(), response.getNCC(), response.getDeGiay(),
                response.getKichThuoc(), response.getGia(), response.getSoLuongTon(),
                response.hTTrangThai()
            });
        }
    }

//    public void upImage(String imageName) {
//        ImageIcon icon = new ImageIcon(imageName);
//        Image image = icon.getImage();
//        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lblChonAnh.getWidth(), lblChonAnh.getHeight(), image.SCALE_SMOOTH));
//        lblChonAnh.setIcon(icon1);
//    }

    private void loadTextField(int i) {
        if (tblChiTietSP.getRowCount() > 0) {
        //    int row = tblChiTietSP.getSelectedRow();
          //  upImage(chiTietSPService.getAll().get(row).getUrl());
            cbbMaSP.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 0).toString());
            txtTenSp.setText(tblChiTietSP.getValueAt(i, 1).toString());
            cbbMauSac.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 3).toString());
            cbbThuongHieu.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 4).toString());
            cbbNCC.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 5).toString());
            cbbDeGiay.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 6).toString());
            cbbSize.setSelectedItem(Integer.parseInt(tblChiTietSP.getModel().getValueAt(i, 7).toString()));
            txtGia.setText(tblChiTietSP.getValueAt(i, 8).toString());
            txtSoLuongTon.setText(tblChiTietSP.getValueAt(i, 9).toString());
            if(tblChiTietSP.getModel().getValueAt(i, 10).toString().equalsIgnoreCase("Đang hoạt động")){
                rbnDangHD.setSelected(true);
            }else{
                rbnDungHD.setSelected(true);
            }
        }
    }

    private StringBuilder check() {
        StringBuilder sb = new StringBuilder();
        if (txtSoLuongTon.getText().isEmpty()) {
            sb.append("Không được để trống Số Lượng").append("\n");
        } else {
            try {
                Integer soLuong = Integer.parseInt(txtSoLuongTon.getText());
                if (soLuong < 0) {
                    sb.append("Số Lượng phải >0").append("\n");
                }
            } catch (Exception e) {
                sb.append("Lỗi: " + e).append("\n");
            }
        }
        if (txtGia.getText().isEmpty()) {
            sb.append("Không được để trống Giá").append("\n");
        } else {
            try {
                Integer gia = Integer.parseInt(txtGia.getText());
                if (gia < 0) {
                    sb.append("Giá phải >0").append("\n");
                }
            } catch (Exception e) {
                sb.append("Lỗi: " + e).append("\n");
            }
        }

        return sb;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        txtSoLuongTon = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbbMaSP = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbDeGiay = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        cbbNCC = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtTenSp = new javax.swing.JTextField();
        lblChonAnh = new javax.swing.JLabel();
        rbnDangHD = new javax.swing.JRadioButton();
        rbnDungHD = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        btnFrist = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Create.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        jLabel18.setText("Mã SP");

        jLabel19.setText("Màu Sắc");

        jLabel20.setText("Thương Hiệu");

        jLabel22.setText("Size");

        jLabel13.setText("Đế Giày");

        jLabel14.setText("Số Lượng");

        jLabel15.setText("Giá");

        jLabel16.setText("Trạng Thái");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        jLabel21.setText("Nhà Cung Cấp");

        cbbMaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbMaSPMouseClicked(evt);
            }
        });
        cbbMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaSPActionPerformed(evt);
            }
        });

        cbbNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNCCActionPerformed(evt);
            }
        });

        jLabel23.setText("Tên SP");

        txtTenSp.setEditable(false);
        txtTenSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSpActionPerformed(evt);
            }
        });

        lblChonAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblChonAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChonAnh.setText("Chọn Ảnh");
        lblChonAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblChonAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChonAnhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChonAnhMouseEntered(evt);
            }
        });

        rbnDangHD.setText("Đang hoạt động");
        rbnDangHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnDangHDActionPerformed(evt);
            }
        });

        rbnDungHD.setText("Dừng hoạt động");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23)
                            .addComponent(jLabel18))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbbThuongHieu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbNCC, javax.swing.GroupLayout.Alignment.LEADING, 0, 250, Short.MAX_VALUE)
                                .addComponent(cbbMauSac, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbbMaSP, 0, 250, Short.MAX_VALUE)
                                .addComponent(txtTenSp, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel22)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGia)
                    .addComponent(cbbDeGiay, 0, 250, Short.MAX_VALUE)
                    .addComponent(txtSoLuongTon)
                    .addComponent(cbbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbnDangHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbnDungHD)))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(cbbDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel14)
                                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel21))
                            .addComponent(rbnDangHD)
                            .addComponent(rbnDungHD)))
                    .addComponent(lblChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Màu Sắc", "Thương Hiệu", "Nhà Cung Cấp", "Đế Giày", "Size", "Giá", "Số Lượng", "Trạng Thái"
            }
        ));
        tblChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietSP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnFrist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/rewind.png"))); // NOI18N
        btnFrist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFristActionPerformed(evt);
            }
        });

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/2.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/fast.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jButton1.setText("Nhập ex");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xuất ex");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnUpdate)
                        .addGap(30, 30, 30)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFrist)
                        .addGap(18, 18, 18)
                        .addComponent(btnPre)
                        .addGap(24, 24, 24)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)
                        .addComponent(btnFrist)
                        .addComponent(btnPre)
                        .addComponent(btnLast)
                        .addComponent(btnMoi)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        ChiTietSP ctsp = new ChiTietSP();
        String maSanPham = (String)cbbMaSP.getSelectedItem();
        for(ViewChiTietSPResponse viewctsp : chiTietSPService.getAll()){
            if(viewctsp.getMaSP().equals(maSanPham)){
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
                return;
            }
        }
        if (check().length() > 0) {
            JOptionPane.showMessageDialog(this, check().toString());
            return;
        }
        
        int maSP = cbbMaSP.getSelectedIndex();
        SanPham sanPham = chiTietSPService.getSP().get(maSP);
        ctsp.setSanPham(sanPham);

        int mauSac = cbbMauSac.getSelectedIndex();
        MauSac ms = chiTietSPService.getMauSac().get(mauSac);
        ctsp.setMauSac(ms);

        int ncc = cbbNCC.getSelectedIndex();
        NhaCungCap nhaCungCap = chiTietSPService.getNCC().get(ncc);
        ctsp.setNhaCungCap(nhaCungCap);

        int th = cbbThuongHieu.getSelectedIndex();
        ThuongHieu thuongHieu = chiTietSPService.getTH().get(th);
        ctsp.setThuongHieu(thuongHieu);

        int deGiay = cbbDeGiay.getSelectedIndex();
        DeGiay dg = chiTietSPService.getDeGiay().get(deGiay);
        ctsp.setDeGiay(dg);

        int soSize = cbbSize.getSelectedIndex();
        KichThuoc size = chiTietSPService.getSize().get(soSize);
        ctsp.setKichThuoc(size);

        ctsp.setGia(BigDecimal.valueOf(Double.valueOf(txtGia.getText())));
        ctsp.setSoLuongTon(Integer.parseInt(txtSoLuongTon.getText()));
        ctsp.setTrangThai(1);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("YYYY-MM-dd");
        String format = simpleDateFormat.format(date);
        ctsp.setNgayTao(format);
        if (!chiTietSPService.create(ctsp)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTable(chiTietSPService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void cbbNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNCCActionPerformed

    private void txtTenSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSpActionPerformed

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        int row = tblChiTietSP.getSelectedRow();
        loadTextField(row);
        cbbMaSP.enable(false);
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
    }//GEN-LAST:event_tblChiTietSPMouseClicked

    private void lblChonAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChonAnhMouseClicked

    }//GEN-LAST:event_lblChonAnhMouseClicked

    private void lblChonAnhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChonAnhMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblChonAnhMouseEntered

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        if (inDex == 0) {
            btnLastActionPerformed(evt);
        } else {
            inDex--;
        }
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);
        loadTextField(inDex);
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        inDex = tblChiTietSP.getRowCount() - 1;
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);
        loadTextField(inDex);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFristActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFristActionPerformed
        inDex = 0;
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);
        loadTextField(inDex);

    }//GEN-LAST:event_btnFristActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = tblChiTietSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm cần xóa");
            return;
        }
        ViewChiTietSPResponse ct = chiTietSPService.getAll().get(row);
        String id = ct.getId();
        if (chiTietSPService.delete(id)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadTable(chiTietSPService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        int row = tblChiTietSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm cần sửa");
            return;
        }
        String idSP = null;
        int ma = cbbMaSP.getSelectedIndex();
        SanPham sp = banHangService.getAllSanPham().get(ma);
        ChiTietSP chiTietSP = sp.getListChiTietSP().get(0);
        idSP = chiTietSP.getId();
        ViewChiTietSPResponse ct = chiTietSPService.getAll().get(row);
        if (check().length() > 0) {
            JOptionPane.showMessageDialog(this, check().toString());
            return;
        }

        ChiTietSP ctsp = new ChiTietSP();
        ctsp.setId(idSP);
        int maSP = cbbMaSP.getSelectedIndex();
        SanPham sanPham = chiTietSPService.getSP().get(maSP);
        ctsp.setSanPham(sanPham);

        int mauSac = cbbMauSac.getSelectedIndex();
        MauSac ms = chiTietSPService.getMauSac().get(mauSac);
        ctsp.setMauSac(ms);

        int ncc = cbbNCC.getSelectedIndex();
        NhaCungCap nhaCungCap = chiTietSPService.getNCC().get(ncc);
        ctsp.setNhaCungCap(nhaCungCap);

        int th = cbbThuongHieu.getSelectedIndex();
        ThuongHieu thuongHieu = chiTietSPService.getTH().get(th);
        ctsp.setThuongHieu(thuongHieu);

        int deGiay = cbbDeGiay.getSelectedIndex();
        DeGiay dg = chiTietSPService.getDeGiay().get(deGiay);
        ctsp.setDeGiay(dg);

        int soSize = cbbSize.getSelectedIndex();
        KichThuoc size = chiTietSPService.getSize().get(soSize);
        ctsp.setKichThuoc(size);

        ctsp.setGia(BigDecimal.valueOf(Double.valueOf(txtGia.getText())));
        Integer soLuong = Integer.parseInt(txtSoLuongTon.getText());
        ctsp.setSoLuongTon(soLuong);
        int trangThai = 1;
        if (soLuong == 0) {
            trangThai = 0;
        }
        ctsp.setTrangThai(trangThai);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("YYYY-MM-dd");
        String format = simpleDateFormat.format(date);
        ctsp.setNgayTao(chiTietSP.getNgayTao());
        ctsp.setNgaySua(format);
        ChiTietSPService chiTiet = new ChiTietSPServiceImpl();
        if (chiTietSPService.create(ctsp)) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            loadTable(chiTiet.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        txtGia.setText(null);
        txtSoLuongTon.setText(null);
        
        cbbMaSP.enable(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        tblChiTietSP.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (inDex == tblChiTietSP.getRowCount() - 1) {
            btnFristActionPerformed(evt);
        } else {
            inDex++;
        }
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);

        loadTextField(inDex);
    }//GEN-LAST:event_btnNextActionPerformed

    private void cbbMaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbMaSPMouseClicked

    }//GEN-LAST:event_cbbMaSPMouseClicked

    private void cbbMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaSPActionPerformed
        int row = cbbMaSP.getSelectedIndex();
        SanPham sp = banHangService.getAllSanPham().get(row);
        txtTenSp.setText(sp.getTen());
       // upImage(sanPhamService.getAll().get(row).getUrl());
    }//GEN-LAST:event_cbbMaSPActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            File excelFile;
            FileInputStream excelFIS = null;
            BufferedInputStream excelBIS = null;
            String defaultCurrentDirectoryPath = "D:\\DuAn1";
            JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
            int exelChooser = excelFileChooser.showOpenDialog(null);

            if (exelChooser == JFileChooser.APPROVE_OPTION) {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);

                XSSFWorkbook excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);

                for (int i = 0; i < excelSheet.getLastRowNum(); i++) {
                    XSSFRow excelrow = excelSheet.getRow(i); //row
//                    for (int j = 0; j < excelrow.getLastCellNum(); j++) {
//                        XSSFCell excelCell =excelrow.getCell(j);
//                        System.out.println(excelCell.getStringCellValue());
//                    }

//                    
//                    XSSFCell excelName = excelrow.getCell(0);
//                    System.out.println(excelName);
                    XSSFCell excelId = excelrow.getCell(0);
                    XSSFCell excelChatLieu = excelrow.getCell(1);
                    XSSFCell excelDongSp = excelrow.getCell(2);
                    XSSFCell excelSize = excelrow.getCell(3);
                    XSSFCell excelNSx = excelrow.getCell(4);
                    XSSFCell excelMau = excelrow.getCell(5);
                    XSSFCell excelSanPham = excelrow.getCell(6);
                    XSSFCell excelSoLuongTon = excelrow.getCell(7);
                    XSSFCell excelGia = excelrow.getCell(8);
                    XSSFCell excelMoTa = excelrow.getCell(9);
                    XSSFCell excelImage = excelrow.getCell(10);
                    JLabel excel = new JLabel(new ImageIcon(excelImage.toString()));
                    dtm.addRow(new Object[]{
                        excelId, excelChatLieu, excelDongSp, excelSize, excelNSx, excelMau, excelSanPham, excelSoLuongTon, excelGia, excelMoTa, excelImage
                    });
                }

            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rbnDangHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnDangHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbnDangHDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewChiTietSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFrist;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbDeGiay;
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNCC;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblChonAnh;
    private javax.swing.JRadioButton rbnDangHD;
    private javax.swing.JRadioButton rbnDungHD;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenSp;
    // End of variables declaration//GEN-END:variables
}
