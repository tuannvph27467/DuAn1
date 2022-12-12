/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group2.view;

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.domainmodel.MauSac;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import com.poly.it17326.group2.response.ViewDeGiayResponse;
import com.poly.it17326.group2.response.ViewKichThuocReponse;
import com.poly.it17326.group2.response.ViewMauSacReponse;
import com.poly.it17326.group2.response.ViewNhaCungCapResponse;
import com.poly.it17326.group2.response.ViewSanPhamResponse;
import com.poly.it17326.group2.response.ViewThuongHieuReposponse;
import com.poly.it17326.group2.service.BanHangService;
import com.poly.it17326.group2.service.ChiTietSPService;
import com.poly.it17326.group2.service.ICommon;
import com.poly.it17326.group2.service.impl.BanHangServiceImpl;
import com.poly.it17326.group2.service.impl.ChiTietSPServiceImpl;
import com.poly.it17326.group2.service.impl.DeGiayServiceImpl;
import com.poly.it17326.group2.service.impl.KichThuocServiceImpl;
import com.poly.it17326.group2.service.impl.MauSacServiceImpl;
import com.poly.it17326.group2.service.impl.NhaCungCapServiceImpl;
import com.poly.it17326.group2.service.impl.SanPhamServiceImpl;
import com.poly.it17326.group2.service.impl.ThuongHieuServiceImpl;
import com.poly.it17326.group2.util.ExportFileCTSP;
import com.poly.it17326.group2.util.ImportExcelCTSP;
import com.poly.it17326.group2.util.TaiMauExcelCTSP;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author anhntph27418
 */
public class ViewQuanLySanPham extends javax.swing.JPanel {

    private ICommon<ViewSanPhamResponse, SanPham> sanPhamService = new SanPhamServiceImpl();
    private ICommon<ViewThuongHieuReposponse, ThuongHieu> thuongHieuService = new ThuongHieuServiceImpl();
    private ICommon<ViewKichThuocReponse, KichThuoc> kichThuocService = new KichThuocServiceImpl();
    private ICommon<ViewDeGiayResponse, DeGiay> deGiayService = new DeGiayServiceImpl();
    private ICommon<ViewNhaCungCapResponse, NhaCungCap> nhaCungCapService = new NhaCungCapServiceImpl();
    private ICommon<ViewMauSacReponse, MauSac> mauSacService = new MauSacServiceImpl();
    private BanHangService banHangService = new BanHangServiceImpl();
    private DefaultComboBoxModel dcbm;
    private int inDex = 0;
    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private DefaultTableModel dtm;
    private DefaultTableModel dtm1;
    private List<ViewChiTietSPResponse> lstctSpViewModel = new ArrayList<>();

    public ViewQuanLySanPham() {
        initComponents();
        rbnDangHD.setSelected(true);
        loadSanPham(sanPhamService.getAll());
        loadThuongHieu(thuongHieuService.getAll());
        loadMauSac(mauSacService.getAll());
        loadNhaCungCap(nhaCungCapService.getAll());
        loadDeGiay(deGiayService.getAll());
        loadKichThuoc(kichThuocService.getAll());

        loadCBBNhaCC(nhaCungCapService.getAll());
        loadCBBKichThuoc(kichThuocService.getAll());
        loadCBBDeGiay(deGiayService.getAll());
        loadCBBThuongHieu(thuongHieuService.getAll());
        loadCBBMauSac(mauSacService.getAll());
        loadCBBSanPham(sanPhamService.getAll());
        lstctSpViewModel = chiTietSPService.getAll();
        loadChiTietSP(lstctSpViewModel);

        btnSuaSP.setEnabled(false);
        btnSuaTH.setEnabled(false);
        btnSuaNCC.setEnabled(false);
        btnSuaMS.setEnabled(false);
        btnSuaDG.setEnabled(false);
        btnSuaKT.setEnabled(false);
        btnSua.setEnabled(false);

    }

    private String getTKTenSP() {
        if (cbbTenSP.getSelectedIndex() == 0) {
            return "";
        } else {
            String tenSP = cbbTenSP.getSelectedItem().toString();
            return tenSP;
        }
    }

    private int getTKTrangThai() {
        if (rbnDangHD.isSelected()) {
            return 1;
        }
        return 0;
    }

    private void loadChiTietSP(List<ViewChiTietSPResponse> lstctSpViewModel) {
        dtm = (DefaultTableModel) tblChiTietSP.getModel();
        dtm.setRowCount(0);
        for (ViewChiTietSPResponse response : lstctSpViewModel) {
            dtm.addRow(new Object[]{
                response.getMaChiTietSP(), response.getTenSP(), response.getThuongHieu(),
                response.getMauSac(), response.getNCC(), response.getDeGiay(),
                response.getKichThuoc(), response.getSoLuongTon(), response.getGia(),
                response.hTTrangThai()
            });
        }
    }

    private void loadCBBMauSac(List<ViewMauSacReponse> listMau) {
        dcbm = (DefaultComboBoxModel) cbbMauSac.getModel();
        for (ViewMauSacReponse mauSacReponse : listMau) {
            dcbm.addElement(mauSacReponse.getTen());
        }
    }

    private void loadCBBSanPham(List<ViewSanPhamResponse> listSanPham) {
        dcbm = (DefaultComboBoxModel) cbbTenSP.getModel();
        ICommon<ViewSanPhamResponse, SanPham> sanPham1 = new SanPhamServiceImpl();
        for (ViewSanPhamResponse sanPhamResponse : sanPham1.getAll()) {
            dcbm.addElement(sanPhamResponse.getTenSP());
        }
    }

    private void loadCBBDeGiay(List<ViewDeGiayResponse> listDeGiay) {
        dcbm = (DefaultComboBoxModel) cbbDeGiay.getModel();
        for (ViewDeGiayResponse deGiayResponse : listDeGiay) {
            dcbm.addElement(deGiayResponse.getTen());
        }
    }

    private void loadCBBThuongHieu(List<ViewThuongHieuReposponse> listThuongHieu) {
        dcbm = (DefaultComboBoxModel) cbbThuongHieu.getModel();
        for (ViewThuongHieuReposponse thuongHieuReposponse : listThuongHieu) {
            dcbm.addElement(thuongHieuReposponse.getTen());
        }
    }

    private void loadCBBNhaCC(List<ViewNhaCungCapResponse> listNCC) {
        dcbm = (DefaultComboBoxModel) cbbNCC.getModel();
        for (ViewNhaCungCapResponse viewNhaCungCapResponse : listNCC) {
            dcbm.addElement(viewNhaCungCapResponse.getTen());
        }
    }

    private void loadCBBKichThuoc(List<ViewKichThuocReponse> listKichThuoc) {
        dcbm = (DefaultComboBoxModel) cbbKichThuoc.getModel();
        for (ViewKichThuocReponse viewSizeReponse : listKichThuoc) {
            dcbm.addElement(viewSizeReponse.getTen());
        }
    }

    private void loadKichThuoc(List<ViewKichThuocReponse> list) {
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Tên"});
        for (ViewKichThuocReponse viewSizeReponse : list) {
            Object[] row = new Object[]{i++, viewSizeReponse.getMa(), viewSizeReponse.getTen()};
            model.addRow(row);
        }
        tblKichThuoc.setModel(model);
    }

    public void loadDeGiay(List<ViewDeGiayResponse> list) {
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Tên"});
        for (ViewDeGiayResponse dg : list) {
            Object[] row = new Object[]{i++, dg.getMa(), dg.getTen()};
            model.addRow(row);
        }
        tblDeGiay.setModel(model);
    }

    public void loadNhaCungCap(List<ViewNhaCungCapResponse> list) {
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"STT", "Mã", "Tên"});
        for (ViewNhaCungCapResponse x : list) {
            Object[] row = new Object[]{i++, x.getMa(), x.getTen()};
            model.addRow(row);
        }
        tblNhaCungCap.setModel(model);
    }

    public void loadThuongHieu(List<ViewThuongHieuReposponse> list) {
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"STT", "Mã", "Tên"});
        for (ViewThuongHieuReposponse th : list) {
            Object[] row = new Object[]{i++, th.getMa(), th.getTen()};
            model.addRow(row);
        }
        tblThuongHieu.setModel(model);
    }

    public void loadSanPham(List<ViewSanPhamResponse> listSanPham) {
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"STT", "Mã SP", "Tên SP"});
        for (ViewSanPhamResponse sp : listSanPham) {
            Object[] row = new Object[]{i++, sp.getMaSP(), sp.getTenSP()};
            model.addRow(row);
        }
        tblCrudSanPham.setModel(model);
    }

    private void loadMauSac(List<ViewMauSacReponse> list) {
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"STT", "Mã SP", "Tên SP"});
        for (ViewMauSacReponse viewSizeReponse : list) {
            Object[] row = new Object[]{i++, viewSizeReponse.getMa(), viewSizeReponse.getTen()};
            model.addRow(row);
        }
        tblMauSac.setModel(model);
    }

    private void loadTextField(int i) {
        if (tblChiTietSP.getRowCount() > 0) {
            txtMaCTSP.setText(tblChiTietSP.getValueAt(i, 0).toString());
            cbbTenSP.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 1).toString());
            cbbThuongHieu.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 2).toString());
            cbbMauSac.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 3).toString());
            cbbNCC.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 4).toString());
            cbbDeGiay.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 5).toString());
            
            cbbKichThuoc.setSelectedItem(tblChiTietSP.getModel().getValueAt(i, 6).toString());
            
            txtSoLuongTon.setText(tblChiTietSP.getValueAt(i, 7).toString());
            txtGia.setText(tblChiTietSP.getValueAt(i, 8).toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CrudSanPham = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnThoatSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCrudSanPham = new javax.swing.JTable();
        CrudThuongHieu = new javax.swing.JFrame();
        jLabel6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtTenThuongHieu = new javax.swing.JTextField();
        txtMaThuongHieu = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblThuongHieu = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btnThemTH = new javax.swing.JButton();
        btnSuaTH = new javax.swing.JButton();
        btnThoatTH = new javax.swing.JButton();
        btnLamMoiTH = new javax.swing.JButton();
        CrudMauSac = new javax.swing.JFrame();
        jLabel28 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtTenMauSac = new javax.swing.JTextField();
        txtMaMauSac = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        btnThemMS = new javax.swing.JButton();
        btnSuaMS = new javax.swing.JButton();
        btnThoatMS = new javax.swing.JButton();
        btnLamMoiMS = new javax.swing.JButton();
        CrudNhaCungCap = new javax.swing.JFrame();
        jLabel31 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtTenNhaCC = new javax.swing.JTextField();
        txtMaNhaCC = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblNhaCungCap = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        btnThemNCC = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();
        btnThoatNCC = new javax.swing.JButton();
        btnLamMoiNCC = new javax.swing.JButton();
        CrudDeGiay = new javax.swing.JFrame();
        jLabel34 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtTenDeGiay = new javax.swing.JTextField();
        txtMaDeGiay = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblDeGiay = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        btnThemDG = new javax.swing.JButton();
        btnSuaDG = new javax.swing.JButton();
        btnThoatDG = new javax.swing.JButton();
        btnLamMoiDG = new javax.swing.JButton();
        CrudKichThuoc = new javax.swing.JFrame();
        jLabel37 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtTenKichThuoc = new javax.swing.JTextField();
        txtMaKichThuoc = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblKichThuoc = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        btnThemKT = new javax.swing.JButton();
        btnSuaKT = new javax.swing.JButton();
        btnThoatKT = new javax.swing.JButton();
        btnLamMoiKT = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbbNCC = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbDeGiay = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        txtSoLuongTon = new javax.swing.JTextField();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        btnThuongHieu = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        btnNhaCungCap = new javax.swing.JButton();
        btnDeGiay = new javax.swing.JButton();
        btnKichThuoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        btnTaiMau = new javax.swing.JButton();
        btnNhapFile = new javax.swing.JButton();
        txtMaCTSP = new javax.swing.JTextField();
        cbbTenSP = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        btnFrist = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        rbnDangHD = new javax.swing.JRadioButton();
        rbnDungHD = new javax.swing.JRadioButton();

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SẢN PHẨM");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnThoatSP.setText("Thoát");
        btnThoatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatSPActionPerformed(evt);
            }
        });

        btnLamMoiSP.setText("Làm Mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThoatSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemSP)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiSP)
                .addGap(18, 18, 18)
                .addComponent(btnThoatSP)
                .addGap(23, 23, 23))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã SP");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên SP");

        txtMaSanPham.setEditable(false);

        tblCrudSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm"
            }
        ));
        tblCrudSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCrudSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCrudSanPham);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtMaSanPham))
                        .addGap(12, 12, 12))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CrudSanPhamLayout = new javax.swing.GroupLayout(CrudSanPham.getContentPane());
        CrudSanPham.getContentPane().setLayout(CrudSanPhamLayout);
        CrudSanPhamLayout.setHorizontalGroup(
            CrudSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CrudSanPhamLayout.setVerticalGroup(
            CrudSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrudSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("THƯƠNG HIỆU");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Mã TH");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Tên TH");

        txtMaThuongHieu.setEditable(false);

        tblThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thương Hiệu", "Tên Thương Hiệu"
            }
        ));
        tblThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblThuongHieu);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtMaThuongHieu))
                        .addGap(12, 12, 12))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtMaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtTenThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnThemTH.setText("Thêm");
        btnThemTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTHActionPerformed(evt);
            }
        });

        btnSuaTH.setText("Sửa");
        btnSuaTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTHActionPerformed(evt);
            }
        });

        btnThoatTH.setText("Thoát");
        btnThoatTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatTHActionPerformed(evt);
            }
        });

        btnLamMoiTH.setText("Làm Mới");
        btnLamMoiTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThoatTH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemTH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiTH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaTH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemTH)
                .addGap(18, 18, 18)
                .addComponent(btnSuaTH)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiTH)
                .addGap(18, 18, 18)
                .addComponent(btnThoatTH)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout CrudThuongHieuLayout = new javax.swing.GroupLayout(CrudThuongHieu.getContentPane());
        CrudThuongHieu.getContentPane().setLayout(CrudThuongHieuLayout);
        CrudThuongHieuLayout.setHorizontalGroup(
            CrudThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudThuongHieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CrudThuongHieuLayout.setVerticalGroup(
            CrudThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudThuongHieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrudThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("MÀU SẮC");

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Mã MS");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Tên MS");

        txtMaMauSac.setEditable(false);

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Màu Sắc", "Tên Màu Sắc"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblMauSac);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtMaMauSac))
                        .addGap(12, 12, 12))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtMaMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtTenMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnThemMS.setText("Thêm");
        btnThemMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMSActionPerformed(evt);
            }
        });

        btnSuaMS.setText("Sửa");
        btnSuaMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMSActionPerformed(evt);
            }
        });

        btnThoatMS.setText("Thoát");
        btnThoatMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatMSActionPerformed(evt);
            }
        });

        btnLamMoiMS.setText("Làm Mới");
        btnLamMoiMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaMS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemMS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThoatMS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiMS, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemMS)
                .addGap(18, 18, 18)
                .addComponent(btnSuaMS)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiMS)
                .addGap(18, 18, 18)
                .addComponent(btnThoatMS)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout CrudMauSacLayout = new javax.swing.GroupLayout(CrudMauSac.getContentPane());
        CrudMauSac.getContentPane().setLayout(CrudMauSacLayout);
        CrudMauSacLayout.setHorizontalGroup(
            CrudMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudMauSacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CrudMauSacLayout.setVerticalGroup(
            CrudMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudMauSacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrudMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("NHÀ CUNG CẤP");

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Mã NCC");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Tên NCC");

        txtMaNhaCC.setEditable(false);

        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã NCC", "Tên NCC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblNhaCungCap);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNhaCC, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtMaNhaCC))
                        .addGap(12, 12, 12))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtMaNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtTenNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnThemNCC.setText("Thêm");
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });

        btnSuaNCC.setText("Sửa");
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });

        btnThoatNCC.setText("Thoát");
        btnThoatNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatNCCActionPerformed(evt);
            }
        });

        btnLamMoiNCC.setText("Làm mới");
        btnLamMoiNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoiNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(btnSuaNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThoatNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemNCC)
                .addGap(18, 18, 18)
                .addComponent(btnSuaNCC)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiNCC)
                .addGap(18, 18, 18)
                .addComponent(btnThoatNCC)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout CrudNhaCungCapLayout = new javax.swing.GroupLayout(CrudNhaCungCap.getContentPane());
        CrudNhaCungCap.getContentPane().setLayout(CrudNhaCungCapLayout);
        CrudNhaCungCapLayout.setHorizontalGroup(
            CrudNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CrudNhaCungCapLayout.setVerticalGroup(
            CrudNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrudNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("ĐẾ GIÀY");

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Mã ĐG");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Tên ĐG");

        txtMaDeGiay.setEditable(false);

        tblDeGiay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Đế Giày", "Tên Đế Giày"
            }
        ));
        tblDeGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDeGiayMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblDeGiay);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenDeGiay, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtMaDeGiay))
                        .addGap(12, 12, 12))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtMaDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtTenDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnThemDG.setText("Thêm");
        btnThemDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDGActionPerformed(evt);
            }
        });

        btnSuaDG.setText("Sửa");
        btnSuaDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDGActionPerformed(evt);
            }
        });

        btnThoatDG.setText("Thoát");
        btnThoatDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatDGActionPerformed(evt);
            }
        });

        btnLamMoiDG.setText("Làm Mới");
        btnLamMoiDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThoatDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(btnLamMoiDG))
                    .addComponent(btnSuaDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemDG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemDG)
                .addGap(18, 18, 18)
                .addComponent(btnSuaDG)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiDG)
                .addGap(18, 18, 18)
                .addComponent(btnThoatDG)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout CrudDeGiayLayout = new javax.swing.GroupLayout(CrudDeGiay.getContentPane());
        CrudDeGiay.getContentPane().setLayout(CrudDeGiayLayout);
        CrudDeGiayLayout.setHorizontalGroup(
            CrudDeGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudDeGiayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CrudDeGiayLayout.setVerticalGroup(
            CrudDeGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudDeGiayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrudDeGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("KÍCH THƯỚC");

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Mã KT");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("Tên KT");

        txtMaKichThuoc.setEditable(false);

        tblKichThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Kích Thước", "Tên Kích Thước"
            }
        ));
        tblKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblKichThuoc);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtMaKichThuoc))
                        .addGap(12, 12, 12))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtMaKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtTenKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnThemKT.setText("Thêm");
        btnThemKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKTActionPerformed(evt);
            }
        });

        btnSuaKT.setText("Sửa");
        btnSuaKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKTActionPerformed(evt);
            }
        });

        btnThoatKT.setText("Thoát");
        btnThoatKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatKTActionPerformed(evt);
            }
        });

        btnLamMoiKT.setText("Làm Mới");
        btnLamMoiKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(btnLamMoiKT)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(btnSuaKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThoatKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemKT)
                .addGap(18, 18, 18)
                .addComponent(btnSuaKT)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiKT)
                .addGap(18, 18, 18)
                .addComponent(btnThoatKT)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout CrudKichThuocLayout = new javax.swing.GroupLayout(CrudKichThuoc.getContentPane());
        CrudKichThuoc.getContentPane().setLayout(CrudKichThuocLayout);
        CrudKichThuocLayout.setHorizontalGroup(
            CrudKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudKichThuocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CrudKichThuocLayout.setVerticalGroup(
            CrudKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudKichThuocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrudKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(1292, 784));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Mã CTSP");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Tên SP");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Thương Hiệu");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Màu Sắc");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Nhà Cung Cấp");

        cbbNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNCCActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Đế Giày");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Kích Thước");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Số Lượng");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Giá");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        btnThuongHieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThuongHieu.setText("...");
        btnThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuongHieuActionPerformed(evt);
            }
        });

        btnSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSanPham.setText("...");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnMauSac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMauSac.setText("...");
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        btnNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNhaCungCap.setText("...");
        btnNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCapActionPerformed(evt);
            }
        });

        btnDeGiay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeGiay.setText("...");
        btnDeGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeGiayActionPerformed(evt);
            }
        });

        btnKichThuoc.setBackground(new java.awt.Color(255, 255, 255));
        btnKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKichThuoc.setText("...");
        btnKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichThuocActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        btnXuatFile.setText("Xuất File");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnTaiMau.setText("Tải Mẫu");
        btnTaiMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiMauActionPerformed(evt);
            }
        });

        btnNhapFile.setText("Nhập File");
        btnNhapFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNhapFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaiMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXuatFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaiMau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNhapFile)
                .addContainerGap())
        );

        txtMaCTSP.setEditable(false);
        txtMaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23)
                            .addComponent(jLabel18))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbNCC, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(btnThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbbTenSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(37, 37, 37)
                                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel22)
                    .addComponent(jLabel14))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGia)
                    .addComponent(cbbDeGiay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoLuongTon)
                    .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(cbbDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeGiay))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(btnSanPham)
                                    .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel14)
                                        .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(btnMauSac)
                                    .addComponent(jLabel15)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22)
                                .addComponent(btnKichThuoc)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(btnNhaCungCap)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm Kiếm");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Tên SP", "Thương Hiệu", "Màu Sắc", "Nhà CC", "Đế Giày", "Kích Thước", "Số Lượng Tồn", "Giá", "Trạng Thái"
            }
        ));
        tblChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietSP);

        btnFrist.setText("|<");
        btnFrist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFristActionPerformed(evt);
            }
        });

        btnPre.setText("<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        rbnDangHD.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbnDangHD);
        rbnDangHD.setText("Đang hoạt động");
        rbnDangHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnDangHDActionPerformed(evt);
            }
        });

        rbnDungHD.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbnDungHD);
        rbnDungHD.setText("Dừng hoạt động");
        rbnDungHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnDungHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btnFrist)
                            .addGap(18, 18, 18)
                            .addComponent(btnPre)
                            .addGap(18, 18, 18)
                            .addComponent(btnNext)
                            .addGap(18, 18, 18)
                            .addComponent(btnLast)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbnDangHD)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rbnDungHD))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbnDangHD)
                        .addComponent(rbnDungHD))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFrist)
                        .addComponent(btnPre)
                        .addComponent(btnNext)
                        .addComponent(btnLast)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNCCActionPerformed

    private void txtMaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCTSPActionPerformed

    private void rbnDangHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnDangHDActionPerformed
        try {
            dtm1 = (DefaultTableModel) tblChiTietSP.getModel();
            dtm1.setRowCount(0);
            ChiTietSPService chi = new ChiTietSPServiceImpl();
            List<ViewChiTietSPResponse> lstSpViewModel = chi.getAll();
            for (ViewChiTietSPResponse ctsp : lstSpViewModel) {
                dtm1.addRow(new Object[]{
                    ctsp.getMaChiTietSP(), ctsp.getTenSP(), ctsp.getThuongHieu(),
                    ctsp.getMauSac(), ctsp.getNCC(), ctsp.getDeGiay(),
                    ctsp.getKichThuoc(), ctsp.getSoLuongTon(), ctsp.getGia(),
                    ctsp.hTTrangThai()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rbnDangHDActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        btnThemSP.setEnabled(true);
        btnSuaSP.setEnabled(false);
        tblCrudSanPham.clearSelection();
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void btnLamMoiTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTHActionPerformed
        txtMaThuongHieu.setText("");
        txtTenThuongHieu.setText("");
        btnThemTH.setEnabled(true);
        btnSuaTH.setEnabled(false);
        tblThuongHieu.clearSelection();
    }//GEN-LAST:event_btnLamMoiTHActionPerformed

    private void btnLamMoiMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiMSActionPerformed
        txtMaMauSac.setText("");
        txtTenMauSac.setText("");
        btnThemMS.setEnabled(true);
        btnSuaMS.setEnabled(false);
        tblMauSac.clearSelection();
    }//GEN-LAST:event_btnLamMoiMSActionPerformed

    private void btnLamMoiNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNCCActionPerformed
        txtMaNhaCC.setText("");
        txtTenNhaCC.setText("");
        btnThemNCC.setEnabled(true);
        btnSuaNCC.setEnabled(false);
        tblNhaCungCap.clearSelection();
    }//GEN-LAST:event_btnLamMoiNCCActionPerformed

    private void btnLamMoiDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDGActionPerformed
        txtMaDeGiay.setText("");
        txtTenDeGiay.setText("");
        btnThemDG.setEnabled(true);
        btnSuaDG.setEnabled(false);
        tblDeGiay.clearSelection();
    }//GEN-LAST:event_btnLamMoiDGActionPerformed

    private void btnLamMoiKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKTActionPerformed
        txtMaKichThuoc.setText("");
        txtTenKichThuoc.setText("");
        btnThemKT.setEnabled(true);
        btnSuaKT.setEnabled(false);
        tblKichThuoc.clearSelection();
    }//GEN-LAST:event_btnLamMoiKTActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        try {
            if (txtTenSanPham.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên sản phẩm không được rỗng");
                return;
            }
            if (txtTenSanPham.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên sản phẩm tối đa 100 ký tự");
                return;
            }
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern("YYYY-MM-dd");
            String format = simpleDateFormat.format(date);
            SanPham sp = new SanPham();
            sp.setMa("Sp" + sanPhamService.genMaTuDong());
            sp.setTen(txtTenSanPham.getText());
            sp.setNgayTao(format);
            sp.setTrangThai(1);
            if (!sanPhamService.create(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadSanPham(sanPhamService.getAll());
                cbbTenSP.removeAllItems();
                loadCBBSanPham(sanPhamService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        try {
            CrudSanPham.setVisible(true);
            CrudSanPham.setSize(550, 330);
            CrudSanPham.setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuongHieuActionPerformed
        try {
            CrudThuongHieu.setVisible(true);
            CrudThuongHieu.setSize(550, 330);
            CrudThuongHieu.setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThuongHieuActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        try {
            CrudMauSac.setVisible(true);
            CrudMauSac.setSize(550, 330);
            CrudMauSac.setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCapActionPerformed
        try {
            CrudNhaCungCap.setVisible(true);
            CrudNhaCungCap.setSize(550, 330);
            CrudNhaCungCap.setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNhaCungCapActionPerformed

    private void btnDeGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeGiayActionPerformed
        try {
            CrudDeGiay.setVisible(true);
            CrudDeGiay.setSize(550, 330);
            CrudDeGiay.setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDeGiayActionPerformed

    private void btnKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichThuocActionPerformed
        try {
            CrudKichThuoc.setVisible(true);
            CrudKichThuoc.setSize(550, 330);
            CrudKichThuoc.setLocationRelativeTo(null);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnKichThuocActionPerformed

    private void btnThoatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatSPActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        CrudSanPham.dispose();
    }//GEN-LAST:event_btnThoatSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed

        try {
            if (txtTenSanPham.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên sản phẩm không được rỗng");
                return;
            }
            if (txtTenSanPham.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên sản phẩm tối đa 100 ký tự");
                return;
            }
            int chon = tblCrudSanPham.getSelectedRow();
            if (chon < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern("YYYY-MM-dd");
            String format = simpleDateFormat.format(date);
            SanPham sp = new SanPham();
            sp.setId(sanPhamService.getAll().get(chon).getId());
            sp.setMa(txtMaSanPham.getText());
            sp.setTen(txtTenSanPham.getText());
            sp.setNgaySua(format);
            sp.setNgayTao(sanPhamService.getAll().get(chon).getNgayTao());
            sp.setTrangThai(1);
            ICommon<ViewSanPhamResponse, SanPham> sp1Service = new SanPhamServiceImpl();
            if (sanPhamService.update(sp)) {
                JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");
                loadSanPham(sp1Service.getAll());
                cbbTenSP.removeAllItems();
                loadCBBSanPham(sanPhamService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void tblCrudSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCrudSanPhamMouseClicked
        int index = tblCrudSanPham.getSelectedRow();
        txtMaSanPham.setText(tblCrudSanPham.getValueAt(index, 1).toString());
        txtTenSanPham.setText(tblCrudSanPham.getValueAt(index, 2).toString());
        btnThemSP.setEnabled(false);
        btnSuaSP.setEnabled(true);
    }//GEN-LAST:event_tblCrudSanPhamMouseClicked

    private void tblThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieuMouseClicked
        int index = tblThuongHieu.getSelectedRow();
        txtMaThuongHieu.setText(tblThuongHieu.getValueAt(index, 1).toString());
        txtTenThuongHieu.setText(tblThuongHieu.getValueAt(index, 2).toString());
        btnThemTH.setEnabled(false);
        btnSuaTH.setEnabled(true);
    }//GEN-LAST:event_tblThuongHieuMouseClicked

    private void btnThemTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTHActionPerformed
        try {
            if (txtTenThuongHieu.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên thương hiệu không được rỗng");
                return;
            }
            if (txtTenThuongHieu.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên thương hiệu tối đa 100 ký tự");
                return;
            }
            ThuongHieu th = new ThuongHieu();
            th.setMa("Th" + thuongHieuService.genMaTuDong());
            th.setTen(txtTenThuongHieu.getText());
            if (!thuongHieuService.create(th)) {
                JOptionPane.showMessageDialog(this, "Thêm thương hiệu thành công");
                loadThuongHieu(thuongHieuService.getAll());
                cbbThuongHieu.removeAllItems();
                loadCBBThuongHieu(thuongHieuService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thương hiệu thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemTHActionPerformed

    private void btnThoatTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatTHActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        CrudThuongHieu.dispose();
    }//GEN-LAST:event_btnThoatTHActionPerformed

    private void btnSuaTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTHActionPerformed
        if (txtTenThuongHieu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, " Tên thương hiệu không được rỗng");
            return;
        }
        if (txtTenThuongHieu.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, " Tên thương hiệu tối đa 100 ký tự");
            return;
        }
        int chon = tblThuongHieu.getSelectedRow();
        if (chon < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }
        ThuongHieu th = new ThuongHieu();
        th.setId(thuongHieuService.getAll().get(chon).getId());
        th.setMa(txtMaThuongHieu.getText());
        th.setTen(txtTenThuongHieu.getText());
        ICommon<ViewThuongHieuReposponse, ThuongHieu> thuongHieu1Service = new ThuongHieuServiceImpl();
        if (!thuongHieuService.update(th)) {
            JOptionPane.showMessageDialog(this, "Sửa thương hiệu thành công");
            loadThuongHieu(thuongHieu1Service.getAll());
            cbbThuongHieu.removeAllItems();
            loadCBBThuongHieu(thuongHieuService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thương hiệu thất bại");
        }
    }//GEN-LAST:event_btnSuaTHActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        int index = tblMauSac.getSelectedRow();
        txtMaMauSac.setText(tblMauSac.getValueAt(index, 1).toString());
        txtTenMauSac.setText(tblMauSac.getValueAt(index, 2).toString());
        btnThemMS.setEnabled(false);
        btnSuaMS.setEnabled(true);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void btnThoatMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatMSActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        CrudMauSac.dispose();
    }//GEN-LAST:event_btnThoatMSActionPerformed

    private void btnThemMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMSActionPerformed
        try {
            if (txtTenMauSac.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên màu sắc không được rỗng");
                return;
            }
            if (txtTenMauSac.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên màu sắc tối đa 100 ký tự");
                return;
            }
            MauSac ms = new MauSac();
            ms.setMa("Ms" + mauSacService.genMaTuDong());
            ms.setTen(txtTenMauSac.getText());
            if (!mauSacService.create(ms)) {
                JOptionPane.showMessageDialog(this, "Thêm màu sắc thành công");
                loadMauSac(mauSacService.getAll());
                cbbMauSac.removeAllItems();
                loadCBBMauSac(mauSacService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm màu sắc thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemMSActionPerformed

    private void btnSuaMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMSActionPerformed
        try {
            if (txtTenMauSac.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên màu sắc không được rỗng");
                return;
            }
            if (txtTenMauSac.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên màu sắc tối đa 100 ký tự");
                return;
            }
            int chon = tblMauSac.getSelectedRow();
            if (chon < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            MauSac ms = new MauSac();
            ms.setId(mauSacService.getAll().get(chon).getId());
            ms.setMa(txtMaMauSac.getText());
            ms.setTen(txtTenMauSac.getText());
            ICommon<ViewMauSacReponse, MauSac> mauSac1Service = new MauSacServiceImpl();
            if (mauSacService.update(ms)) {
                JOptionPane.showMessageDialog(this, "Sửa màu sắc thành công");
                loadMauSac(mauSac1Service.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa màu sắc thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaMSActionPerformed

    private void btnThoatNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatNCCActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        CrudNhaCungCap.dispose();
    }//GEN-LAST:event_btnThoatNCCActionPerformed

    private void tblNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaCungCapMouseClicked
        int index = tblNhaCungCap.getSelectedRow();
        txtMaNhaCC.setText(tblNhaCungCap.getValueAt(index, 1).toString());
        txtTenNhaCC.setText(tblNhaCungCap.getValueAt(index, 2).toString());
        btnThemNCC.setEnabled(false);
        btnSuaNCC.setEnabled(true);
    }//GEN-LAST:event_tblNhaCungCapMouseClicked

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        try {
            if (txtTenNhaCC.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên nhà cung cấp không được rỗng");
                return;
            }
            if (txtTenNhaCC.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên nhà cung cấp tối đa 100 ký tự");
                return;
            }
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMa("Ncc" + nhaCungCapService.genMaTuDong());
            ncc.setTen(txtTenNhaCC.getText());
            if (!nhaCungCapService.create(ncc)) {
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công");
                loadNhaCungCap(nhaCungCapService.getAll());
                cbbNCC.removeAllItems();
                loadCBBNhaCC(nhaCungCapService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        try {
            if (txtTenNhaCC.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên nhà cung cấp không được rỗng");
                return;
            }
            if (txtTenNhaCC.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên nhà cung cấp tối đa 100 ký tự");
                return;
            }
            int chon = tblNhaCungCap.getSelectedRow();
            if (chon < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            NhaCungCap ncc = new NhaCungCap();
            ncc.setId(nhaCungCapService.getAll().get(chon).getId());
            ncc.setMa(txtMaNhaCC.getText());
            ncc.setTen(txtTenNhaCC.getText());
            ICommon<ViewNhaCungCapResponse, NhaCungCap> nhaCC1Service = new NhaCungCapServiceImpl();
            if (nhaCungCapService.update(ncc)) {
                JOptionPane.showMessageDialog(this, "Sửa nhà cung cấp thành công");
                loadNhaCungCap(nhaCC1Service.getAll());
                cbbNCC.removeAllItems();
                loadCBBNhaCC(nhaCungCapService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa nhà cung cấp thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaNCCActionPerformed

    private void btnThoatDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatDGActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        CrudDeGiay.dispose();
    }//GEN-LAST:event_btnThoatDGActionPerformed

    private void tblDeGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeGiayMouseClicked
        int index = tblDeGiay.getSelectedRow();
        txtMaDeGiay.setText(tblDeGiay.getValueAt(index, 1).toString());
        txtTenDeGiay.setText(tblDeGiay.getValueAt(index, 2).toString());
        btnThemDG.setEnabled(false);
        btnSuaDG.setEnabled(true);
    }//GEN-LAST:event_tblDeGiayMouseClicked

    private void btnThemDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDGActionPerformed
        try {
            if (txtTenDeGiay.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên đế giày không được rỗng");
                return;
            }
            if (txtTenDeGiay.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên đế giày tối đa 100 ký tự");
                return;
            }
            DeGiay dg = new DeGiay();
            dg.setMa("Dg" + deGiayService.genMaTuDong());
            dg.setTen(txtTenDeGiay.getText());
            if (!deGiayService.create(dg)) {
                JOptionPane.showMessageDialog(this, "Thêm đế giày thành công");
                loadDeGiay(deGiayService.getAll());
                cbbDeGiay.removeAllItems();
                loadCBBDeGiay(deGiayService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm đế giày thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemDGActionPerformed

    private void btnSuaDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDGActionPerformed
        try {
            if (txtTenDeGiay.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Tên đế giày không được rỗng");
                return;
            }
            if (txtTenDeGiay.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Tên đế giày tối đa 100 ký tự");
                return;
            }
            int chon = tblDeGiay.getSelectedRow();
            if (chon < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            DeGiay dg = new DeGiay();
            dg.setId(deGiayService.getAll().get(chon).getId());
            dg.setMa(txtMaDeGiay.getText());
            dg.setTen(txtTenDeGiay.getText());
            ICommon<ViewDeGiayResponse, DeGiay> deGiay1Service = new DeGiayServiceImpl();
            if (deGiayService.update(dg)) {
                JOptionPane.showMessageDialog(this, "Sửa đế giày thành công");
                loadDeGiay(deGiay1Service.getAll());
                cbbDeGiay.removeAllItems();
                loadCBBDeGiay(deGiayService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa đế giày thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaDGActionPerformed

    private void btnThoatKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatKTActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        CrudKichThuoc.dispose();
    }//GEN-LAST:event_btnThoatKTActionPerformed

    private void tblKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocMouseClicked
        int index = tblKichThuoc.getSelectedRow();
        txtMaKichThuoc.setText(tblKichThuoc.getValueAt(index, 1).toString());
        txtTenKichThuoc.setText(tblKichThuoc.getValueAt(index, 2).toString());
        btnThemKT.setEnabled(false);
        btnSuaKT.setEnabled(true);
    }//GEN-LAST:event_tblKichThuocMouseClicked

    private void btnThemKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKTActionPerformed
        try {
            if (txtTenKichThuoc.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Kích thước không được rỗng");
                return;
            }
            if (txtTenKichThuoc.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Kích thước tối đa 100 ký tự");
                return;
            }
            KichThuoc kt = new KichThuoc();
            kt.setMa("Kt" + kichThuocService.genMaTuDong());
            kt.setTen(txtTenKichThuoc.getText());
            if (!kichThuocService.create(kt)) {
                JOptionPane.showMessageDialog(this, "Thêm kích thước thành công");
                loadKichThuoc(kichThuocService.getAll());
                cbbKichThuoc.removeAllItems();
                loadCBBKichThuoc(kichThuocService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm kích thước thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemKTActionPerformed

    private void btnSuaKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKTActionPerformed
        try {
            if (txtTenKichThuoc.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, " Kích thước không được rỗng");
                return;
            }
            if (txtTenKichThuoc.getText().length() > 100) {
                JOptionPane.showMessageDialog(this, " Kích thước tối đa 100 ký tự");
                return;
            }
            int chon = tblKichThuoc.getSelectedRow();
            if (chon < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            KichThuoc kt = new KichThuoc();
            kt.setId(kichThuocService.getAll().get(chon).getId());
            kt.setMa(txtMaKichThuoc.getText());
            kt.setTen(txtTenKichThuoc.getText());
            ICommon<ViewKichThuocReponse, KichThuoc> kichThuoc1Service = new KichThuocServiceImpl();
            if (kichThuocService.update(kt)) {
                JOptionPane.showMessageDialog(this, "Sửa kích thước thành công");
                loadKichThuoc(kichThuoc1Service.getAll());
                cbbKichThuoc.removeAllItems();
                loadCBBKichThuoc(kichThuocService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa kích thước thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaKTActionPerformed

    private void btnFristActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFristActionPerformed
        inDex = 0;
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);
        loadTextField(inDex);
    }//GEN-LAST:event_btnFristActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        if (inDex == 0) {
            btnLastActionPerformed(evt);
        } else {
            inDex--;
        }
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);
        loadTextField(inDex);
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (inDex == tblChiTietSP.getRowCount() - 1) {
            btnFristActionPerformed(evt);
        } else {
            inDex++;
        }
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);
        loadTextField(inDex);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        inDex = tblChiTietSP.getRowCount() - 1;
        tblChiTietSP.setRowSelectionInterval(inDex, inDex);
        loadTextField(inDex);
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        int row = tblChiTietSP.getSelectedRow();
        loadTextField(row);
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
    }//GEN-LAST:event_tblChiTietSPMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            if (txtGia.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá bán không được rỗng");
                return;
            }

            if (txtSoLuongTon.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn không được rỗng");
                return;
            }

            try {
                int soLuongTon = 0;
                soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
                if (soLuongTon <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng tồn lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số");
                return;
            }

            try {
                int giaBan = 0;
                giaBan = Integer.parseInt(txtGia.getText().trim());
                if (giaBan < 0) {
                    JOptionPane.showMessageDialog(this, "Giá bán lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá bán phải là số");
                return;
            }
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setMaChiTietSP("CTSP" + chiTietSPService.genMaCTSPTuDong());
            int tenSP = cbbTenSP.getSelectedIndex();
            SanPham sanPham = chiTietSPService.getSP().get(tenSP);
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

            int kichThuoc = cbbKichThuoc.getSelectedIndex();
            KichThuoc size = chiTietSPService.getSize().get(kichThuoc);
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
                loadChiTietSP(chiTietSPService.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        btnSua.setEnabled(false);
        btnThem.setEnabled(true);
        tblChiTietSP.clearSelection();
        txtMaCTSP.setText("");
        txtGia.setText("");
        txtSoLuongTon.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            if (txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán không được rỗng");
            return;
        }
        
        if (txtSoLuongTon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn không được rỗng");
            return;
        }

        try {
            int soLuongTon = 0;
            soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
            if (soLuongTon < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn lớn hơn hoặc bằng 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số");
            return;
        }

        try {
            int giaBan = 0;
            giaBan = Integer.parseInt(txtGia.getText().trim());
            if (giaBan < 0) {
                JOptionPane.showMessageDialog(this, "Giá bán lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số");
            return;
        }
            
            ChiTietSP ctsp = new ChiTietSP();
            int row = tblChiTietSP.getSelectedRow();
            int tenSP = cbbTenSP.getSelectedIndex();
     
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm cần sửa");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            String idCTSP = lstctSpViewModel.get(row).getId();
            if (rbnDungHD.isSelected()) {
                List<ViewChiTietSPResponse> lstSpViewModel = chiTietSPService.timKiem(String.valueOf(0));
                ctsp.setId(lstSpViewModel.get(row).getId());
                System.out.println(lstSpViewModel.get(row).getId());
            } else {
                ctsp.setId(idCTSP);
                System.out.println(idCTSP);
            }

            ctsp.setMaChiTietSP(txtMaCTSP.getText());
            System.out.println(txtMaCTSP.getText());
            SanPham sanPham = chiTietSPService.getSP().get(tenSP);
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

            int kichThuoc = cbbKichThuoc.getSelectedIndex();
            KichThuoc size = chiTietSPService.getSize().get(kichThuoc);
            ctsp.setKichThuoc(size);

            ctsp.setGia(BigDecimal.valueOf(Double.valueOf(txtGia.getText())));
            Integer soLuong = Integer.parseInt(txtSoLuongTon.getText());
            ctsp.setSoLuongTon(soLuong);
            int trangThai = 1;
            if (rbnDungHD.isSelected()) {
                trangThai = 0;
            }
            ctsp.setTrangThai(trangThai);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern("YYYY-MM-dd");
            String format = simpleDateFormat.format(date);
            ctsp.setNgayTao(format);
            ctsp.setNgaySua(format);
            ChiTietSPService chiTiet = new ChiTietSPServiceImpl();
            if (chiTietSPService.create(ctsp)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                loadChiTietSP(chiTiet.getAll());
//                rbnDangHD.setSelected(true);
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnNhapFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapFileActionPerformed
        JFileChooser fc = new JFileChooser("D:\\Code1\\sourcecode");
        int check = fc.showOpenDialog(null);
        File file = null;
        if (check == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        ImportExcelCTSP excelCTSP = new ImportExcelCTSP();
        excelCTSP.ImportFile(file.getAbsolutePath());
        ChiTietSPService chiTiet1 = new ChiTietSPServiceImpl();
        loadChiTietSP(chiTiet1.getAll());
    }//GEN-LAST:event_btnNhapFileActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        try {
            ExportFileCTSP export = new ExportFileCTSP();
            boolean check = export.ExportFileExcel(chiTietSPService.getAll());
            if (check) {
                JOptionPane.showMessageDialog(this, "Xuất file excel thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xuất file excel thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnTaiMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiMauActionPerformed
        try {
            TaiMauExcelCTSP importEX = new TaiMauExcelCTSP();
            boolean check = importEX.ImportExcel();
            if (check) {
                JOptionPane.showMessageDialog(this, "Tải mẫu Excel thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Tải mẫu Excel không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTaiMauActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        try {
            lstctSpViewModel = chiTietSPService.timKiem(txtTimKiem.getText());
            loadChiTietSP(lstctSpViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void rbnDungHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnDungHDActionPerformed
        try {
            dtm1 = (DefaultTableModel) tblChiTietSP.getModel();
            dtm1.setRowCount(0);
            ChiTietSPService ct = new ChiTietSPServiceImpl();
            List<ViewChiTietSPResponse> lstSpViewModel = ct.timKiem(String.valueOf(0));
            for (ViewChiTietSPResponse ctsp : lstSpViewModel) {
                dtm1.addRow(new Object[]{
                    ctsp.getMaChiTietSP(), ctsp.getTenSP(), ctsp.getThuongHieu(),
                    ctsp.getMauSac(), ctsp.getNCC(), ctsp.getDeGiay(),
                    ctsp.getKichThuoc(), ctsp.getSoLuongTon(), ctsp.getGia(),
                    ctsp.hTTrangThai()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rbnDungHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame CrudDeGiay;
    private javax.swing.JFrame CrudKichThuoc;
    private javax.swing.JFrame CrudMauSac;
    private javax.swing.JFrame CrudNhaCungCap;
    private javax.swing.JFrame CrudSanPham;
    private javax.swing.JFrame CrudThuongHieu;
    private javax.swing.JButton btnDeGiay;
    private javax.swing.JButton btnFrist;
    private javax.swing.JButton btnKichThuoc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiDG;
    private javax.swing.JButton btnLamMoiKT;
    private javax.swing.JButton btnLamMoiMS;
    private javax.swing.JButton btnLamMoiNCC;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLamMoiTH;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNhaCungCap;
    private javax.swing.JButton btnNhapFile;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaDG;
    private javax.swing.JButton btnSuaKT;
    private javax.swing.JButton btnSuaMS;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaTH;
    private javax.swing.JButton btnTaiMau;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemDG;
    private javax.swing.JButton btnThemKT;
    private javax.swing.JButton btnThemMS;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemTH;
    private javax.swing.JButton btnThoatDG;
    private javax.swing.JButton btnThoatKT;
    private javax.swing.JButton btnThoatMS;
    private javax.swing.JButton btnThoatNCC;
    private javax.swing.JButton btnThoatSP;
    private javax.swing.JButton btnThoatTH;
    private javax.swing.JButton btnThuongHieu;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbDeGiay;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNCC;
    private javax.swing.JComboBox<String> cbbTenSP;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JRadioButton rbnDangHD;
    private javax.swing.JRadioButton rbnDungHD;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTable tblCrudSanPham;
    private javax.swing.JTable tblDeGiay;
    private javax.swing.JTable tblKichThuoc;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblNhaCungCap;
    private javax.swing.JTable tblThuongHieu;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaCTSP;
    private javax.swing.JTextField txtMaDeGiay;
    private javax.swing.JTextField txtMaKichThuoc;
    private javax.swing.JTextField txtMaMauSac;
    private javax.swing.JTextField txtMaNhaCC;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMaThuongHieu;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenDeGiay;
    private javax.swing.JTextField txtTenKichThuoc;
    private javax.swing.JTextField txtTenMauSac;
    private javax.swing.JTextField txtTenNhaCC;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuongHieu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
