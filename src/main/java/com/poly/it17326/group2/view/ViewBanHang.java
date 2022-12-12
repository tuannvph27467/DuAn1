/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group2.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.domainmodel.NhanVien;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.TrangThai;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import com.poly.it17326.group2.response.ViewHoaDonChiTietResponse;
import com.poly.it17326.group2.response.ViewHoaDonResponse;
import com.poly.it17326.group2.response.ViewKhachHangResponse;
import com.poly.it17326.group2.service.BanHangService;
import com.poly.it17326.group2.service.KhachHangService;
import com.poly.it17326.group2.service.impl.BanHangServiceImpl;
import com.poly.it17326.group2.service.impl.KhachHangServiceImpl;
import com.poly.it17326.group2.util.ExportFilePdfByITextTaiQuay;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;

/**
 *
 * @author DELL
 */
public class ViewBanHang extends javax.swing.JPanel {

    private DefaultTableModel dtmHoaDon;

    private DefaultTableModel dtmKhachHang;

    private DefaultTableModel dtmSanPham;

    private DefaultTableModel dtmGioHang;

    private DefaultComboBoxModel dcbmHoaDon;

    private BanHangService banHangService = new BanHangServiceImpl();

    private KhachHangService khachHangService = new KhachHangServiceImpl();

    private List<ViewHoaDonChiTietResponse> listHoaDonChiTiet;

    private NhanVien nhanVien = new NhanVien();

    public ViewBanHang(NhanVien nv) {
        initComponents();
        nhanVien = nv;
        loadHoaDonByNhanVien(banHangService.getHoaDonByMa(nhanVien.getMa()));
        loadSanPham(banHangService.getAllChiTietSP());
    }

    private void loadHoaDonByNhanVien(List<ViewHoaDonResponse> listHoaDon) {
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        for (ViewHoaDonResponse viewHoaDonResponse : listHoaDon) {
            dtmHoaDon.addRow(new Object[]{
                viewHoaDonResponse.getMaHD(),
                viewHoaDonResponse.getNgayTao(),
                viewHoaDonResponse.getTenNhanVien(),
                viewHoaDonResponse.getTenKhachHang(),
                viewHoaDonResponse.htTrangThai()
            });
        }
    }

    private void loadKhachHang(List<ViewKhachHangResponse> listKhachHang) {
        dtmKhachHang = (DefaultTableModel) tblKhachHang.getModel();
        dtmKhachHang.setRowCount(0);
        for (ViewKhachHangResponse viewKhachHangResponse : listKhachHang) {
            dtmKhachHang.addRow(new Object[]{
                viewKhachHangResponse.getMa(),
                viewKhachHangResponse.getHoTen(),
                viewKhachHangResponse.getSdt(),
                viewKhachHangResponse.htGioiTinh(),
                viewKhachHangResponse.getNgaySinh(),
                viewKhachHangResponse.getDiaChi()
            });
        }
    }

    private void loadSanPham(List<ViewChiTietSPResponse> listSanPham) {
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();
        dtmSanPham.setRowCount(0);
        for (ViewChiTietSPResponse sanPham : listSanPham) {
            dtmSanPham.addRow(new Object[]{
                sanPham.getMaChiTietSP(), sanPham.getTenSP(),
                sanPham.getKichThuoc(), sanPham.getMauSac(),
                sanPham.getDeGiay(), sanPham.getSoLuongTon(),
                sanPham.getGia()
            });
        }
    }

    private void loadGioHang(List<ViewHoaDonChiTietResponse> listHDCT) {
        dtmGioHang = (DefaultTableModel) tblGioHang.getModel();
        dtmGioHang.setRowCount(0);
        for (ViewHoaDonChiTietResponse gioHang : listHDCT) {
            dtmGioHang.addRow(new Object[]{
                gioHang.getCtsp(), gioHang.getTenSP(),
                gioHang.getKichThuoc(), gioHang.getMauSac(),
                gioHang.getSoLuong(), gioHang.getGia(),
                gioHang.getTongTien()
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

    private void tinhtongTien() {
        int size = tblGioHang.getRowCount();
        BigDecimal tong = new BigDecimal(0);
        BigDecimal giamGia = new BigDecimal(0);
        for (int i = 0; i < size; i++) {
            BigDecimal moneySale = new BigDecimal(0);
            BigDecimal money = new BigDecimal(tblGioHang.getValueAt(i, 5).toString()).multiply(new BigDecimal(tblGioHang.getValueAt(i, 4).toString()));
//            moneySale = new BigDecimal(tblGioHang.getValueAt(i, 4).toString()).multiply(new BigDecimal(tblGioHang.getValueAt(i, 3).toString()));
            tong = tong.add(money);
//            giamGia = giamGia.add(moneySale);
        }
        int rowHoaDon = tblHoaDon.getSelectedRow();
        if (rowHoaDon == -1) {
            return;
        }
        DecimalFormat df = new DecimalFormat("###");
        txtTongTien.setText(df.format(tong) + "VND");
        txtThanhTien.setText(df.format(tong));
        if (txtTienKhachDua.getText().trim().isEmpty()) {
            return;
        }
        BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDua.getText().trim());
        BigDecimal tienThua = tienKhachDua.subtract(tong);
//        tienThua = tienKhachDua.subtract(tong);
        if (tienThua.compareTo(BigDecimal.ZERO) == -1) {
            txtTienThua.setText(df.format(tienThua));
        } else {
            txtTienThua.setText(df.format(tienThua));
        }
//        BigDecimal tienCanTra = tong.subtract(giamGia);
//        BigDecimal tienCanThanhToan = null;
    }

    private void taoHoaDon() {
        try {
            HoaDon hoaDon = new HoaDon();
            LocalDateTime time = LocalDateTime.now();
            String maHD = "HD" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                    + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();

            String idTrangThai = null;
            for (TrangThai trangThai : banHangService.getAllTrangThai()) {
                if (trangThai.getTenTrangThai() == 1) {
                    idTrangThai = trangThai.getId();
                }
            }
            String idKhachHang = null;
            for (ViewKhachHangResponse viewKhachHangResponse : khachHangService.getAll()) {
                if (viewKhachHangResponse.getMa().equals("KH0")) {
                    idKhachHang = viewKhachHangResponse.getId();
                }
            }

            hoaDon.setMa(maHD);
            hoaDon.setNgayTao(getDate());
            hoaDon.setNhanVien(nhanVien);
            KhachHang khachHang = new KhachHang();
            khachHang.setId(idKhachHang);
            hoaDon.setKhachHang(khachHang);
            TrangThai trangThai = new TrangThai();
            trangThai.setId(idTrangThai);
            hoaDon.setTrangThai(trangThai);
            if (!banHangService.createHD(hoaDon)) {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
                loadHoaDonByNhanVien(banHangService.getHoaDonByMa(nhanVien.getMa()));
                tblHoaDon.setRowSelectionInterval(0, 0);
            } else {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại");
            }

            txtMaHD.setText(maHD);
            txtTenNV.setText(nhanVien.getHoTen());
            lblHoTenKH.setText("Khách Lẻ");
            lblMaKH.setText("KH001");
            lblCapBac.setText("Đồng");
            tinhtongTien();
        } catch (HeadlessException e) {
        }
    }

    private void clearForm() {
        tblHoaDon.clearSelection();
        loadGioHang(banHangService.getHoaDonChiTietByHoaDon(""));
        txtMaHD.setText("");
        txtTenNV.setText("");
        txtTongTien.setText("");
        txtThanhTien.setText("");
        txtTienKhachDua.setText("");
        txtTienThua.setText("");
        lblMaKH.setText("KH0");
        lblHoTenKH.setText("Khách Lẻ");
        lblCapBac.setText("Đồng");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrGioiTinh = new javax.swing.ButtonGroup();
        KhachHangView = new javax.swing.JFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlThongTinKH = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnChonKhachHang = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();
        pnlThemKH = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnThemKH = new javax.swing.JButton();
        dateNgaySinh = new com.github.lgooddatepicker.components.DatePicker();
        pnlHoaDon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        cbbTrangThaiHD = new javax.swing.JComboBox<>();
        btnTaoDon = new javax.swing.JButton();
        pnlGioHang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
        btnXoaAllSP = new javax.swing.JButton();
        pnlSanPham = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        pnlTaiQuay = new javax.swing.JPanel();
        pnlKhachHang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnChonKH = new javax.swing.JButton();
        lblMaKH = new javax.swing.JLabel();
        lblHoTenKH = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblCapBac = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnHuyDon = new javax.swing.JButton();
        txtTenNV = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienThua = new javax.swing.JTextField();
        cbbKhuyenMai = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        pnlWebcam = new javax.swing.JPanel();

        pnlThongTinKH.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaKH", "TenKH", "SDT", "GioiTinh", "NgaySinh", "DiaChi"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhachHang);

        btnChonKhachHang.setText("Chọn");
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

        jLabel5.setText("Tìm Kiếm :");

        txtTimKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTimKHMouseEntered(evt);
            }
        });
        txtTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKHActionPerformed(evt);
            }
        });
        txtTimKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKHKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinKHLayout = new javax.swing.GroupLayout(pnlThongTinKH);
        pnlThongTinKH.setLayout(pnlThongTinKHLayout);
        pnlThongTinKHLayout.setHorizontalGroup(
            pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinKHLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pnlThongTinKHLayout.setVerticalGroup(
            pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKHLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlThongTinKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnChonKhachHang)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Thông Tin Khách Hàng", pnlThongTinKH);

        pnlThemKH.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Tên KH :");

        jLabel7.setText("SDT :");

        jLabel8.setText("Giới Tính :");

        jLabel9.setText("Ngày Sinh :");

        jLabel10.setText("Địa Chỉ :");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane5.setViewportView(txtDiaChi);

        bgrGioiTinh.add(rdoNam);
        rdoNam.setText("Nam");

        bgrGioiTinh.add(rdoNu);
        rdoNu.setText("Nữ");

        btnThemKH.setText("Thêm");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThemKHLayout = new javax.swing.GroupLayout(pnlThemKH);
        pnlThemKH.setLayout(pnlThemKHLayout);
        pnlThemKHLayout.setHorizontalGroup(
            pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemKHLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThemKHLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThemKHLayout.createSequentialGroup()
                        .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThemKHLayout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlThemKHLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlThemKHLayout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(35, 35, 35)
                                        .addComponent(rdoNu))
                                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addComponent(txtTenKH)))
                            .addGroup(pnlThemKHLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        pnlThemKHLayout.setVerticalGroup(
            pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemKHLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNam)
                        .addComponent(rdoNu)))
                .addGap(24, 24, 24)
                .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlThemKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThemKHLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemKHLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(btnThemKH)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thêm Khách Hàng", pnlThemKH);

        javax.swing.GroupLayout KhachHangViewLayout = new javax.swing.GroupLayout(KhachHangView.getContentPane());
        KhachHangView.getContentPane().setLayout(KhachHangViewLayout);
        KhachHangViewLayout.setHorizontalGroup(
            KhachHangViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        KhachHangViewLayout.setVerticalGroup(
            KhachHangViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaHD", "NgayTao", "NhanVien", "KhachHang", "TrangThai"
            }
        ));
        tblHoaDon.setGridColor(new java.awt.Color(255, 255, 255));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        cbbTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Chờ Thanh Toán", "Đã Thanh Toán", "Đã Hủy" }));
        cbbTrangThaiHD.setBackground(new java.awt.Color(204, 204, 204));

        btnTaoDon.setText("Tạo Đơn");
        btnTaoDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoaDonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTrangThaiHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoDon)
                .addContainerGap())
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addComponent(cbbTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoaDonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        pnlGioHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn Chi Tiết"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CTSP", "TenSP", "KichThuoc", "MauSac", "SoLuong", "Gia", "TongTien"
            }
        ));
        tblGioHang.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tblGioHang);

        btnXoaSP.setText("Xóa Sản Phẩm");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnXoaAllSP.setText("Xóa Tất Cả");
        btnXoaAllSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGioHangLayout = new javax.swing.GroupLayout(pnlGioHang);
        pnlGioHang.setLayout(pnlGioHangLayout);
        pnlGioHangLayout.setHorizontalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaAllSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlGioHangLayout.setVerticalGroup(
            pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGioHangLayout.createSequentialGroup()
                        .addComponent(btnXoaSP)
                        .addGap(33, 33, 33)
                        .addComponent(btnXoaAllSP))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản Phẩm"));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CTSP", "TenSP", "KichThuoc", "MauSac", "DeGiay", "SoLuongTon", "Gia"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        jLabel1.setText("Tìm Kiếm :");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel2.setText("Thương Hiệu :");

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlTaiQuay.setBackground(new java.awt.Color(255, 255, 255));
        pnlTaiQuay.setBorder(javax.swing.BorderFactory.createTitledBorder("Tại Quầy"));

        pnlKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách Hàng"));

        jLabel3.setText("MaKH :");

        jLabel4.setText("Họ tên :");

        btnChonKH.setText("Chọn");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        lblMaKH.setText("MaKH");
        lblMaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaKH.setForeground(new java.awt.Color(255, 0, 51));

        lblHoTenKH.setText("HoTen");
        lblHoTenKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoTenKH.setForeground(new java.awt.Color(255, 51, 51));

        jLabel19.setText("Cấp Bậc :");

        lblCapBac.setText("Đồng");
        lblCapBac.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCapBac.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(lblMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlKhachHangLayout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlKhachHangLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(lblHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChonKH)
                .addGap(123, 123, 123))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblMaKH))
                .addGap(18, 18, 18)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblHoTenKH))
                .addGap(18, 18, 18)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblCapBac))
                .addGap(18, 18, 18)
                .addComponent(btnChonKH)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel11.setText("Mã HD :");
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 153, 153)));

        jLabel12.setText("Tên NV :");
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel13.setText("Tổng Tiền :");
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel14.setText("Khuyến Mãi :");
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel16.setText("Tiền Khách Đưa :");
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel18.setText("Tiền Thừa :");
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuyDon.setText("Hủy Đơn");
        btnHuyDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDonActionPerformed(evt);
            }
        });

        txtTenNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 153, 153)));

        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 153, 153)));

        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 153, 153)));
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyPressed(evt);
            }
        });

        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienThua.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienThua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 153, 153)));
        txtTienThua.setForeground(new java.awt.Color(255, 0, 0));

        jLabel15.setText("Thành Tiền :");
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtThanhTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 153, 153)));

        javax.swing.GroupLayout pnlTaiQuayLayout = new javax.swing.GroupLayout(pnlTaiQuay);
        pnlTaiQuay.setLayout(pnlTaiQuayLayout);
        pnlTaiQuayLayout.setHorizontalGroup(
            pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(40, 40, 40)
                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHD)
                            .addComponent(txtTenNV)
                            .addComponent(txtTongTien)
                            .addComponent(cbbKhuyenMai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addComponent(btnThanhToan)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienThua)
                            .addComponent(txtThanhTien)
                            .addComponent(txtTienKhachDua))))
                .addContainerGap())
        );
        pnlTaiQuayLayout.setVerticalGroup(
            pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel18))
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );

        pnlWebcam.setBackground(new java.awt.Color(255, 255, 255));
        pnlWebcam.setBorder(javax.swing.BorderFactory.createTitledBorder("Quét Mã"));

        javax.swing.GroupLayout pnlWebcamLayout = new javax.swing.GroupLayout(pnlWebcam);
        pnlWebcam.setLayout(pnlWebcamLayout);
        pnlWebcamLayout.setHorizontalGroup(
            pnlWebcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        pnlWebcamLayout.setVerticalGroup(
            pnlWebcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addComponent(pnlGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlTaiQuay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        try {
            int rowHD = tblHoaDon.getSelectedRow();
            if (rowHD == -1) {
                JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
                return;
            }
            KhachHangView.setVisible(true);
            KhachHangView.setSize(450, 447);
            KhachHangView.setLocationRelativeTo(null);
            List<ViewKhachHangResponse> listKhachHang = khachHangService.getAll();
            loadKhachHang(listKhachHang);
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnTaoDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonActionPerformed
        taoHoaDon();
    }//GEN-LAST:event_btnTaoDonActionPerformed

    private void txtTimKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKHKeyPressed
        String hoTenKH = txtTimKH.getText();
        KhachHangService hangService = new KhachHangServiceImpl();
        List<ViewKhachHangResponse> listKH = hangService.getByHoTenKhach(hoTenKH);
        loadKhachHang(listKH);
    }//GEN-LAST:event_txtTimKHKeyPressed

    private void txtTimKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKHMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKHMouseEntered

    private void txtTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKHActionPerformed

    }//GEN-LAST:event_txtTimKHActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        int rowKH = tblKhachHang.getSelectedRow();
        int rowHD = tblHoaDon.getSelectedRow();
        HoaDon hoaDon = new HoaDon();
        String idHD = null;
        String idKH = null;
        int capBac = 0;
        for (HoaDon hd : banHangService.getAllHoaDon()) {
            if (hd.getMa().equals(tblHoaDon.getValueAt(rowHD, 0))) {
                idHD = hd.getId();
            }
        }
        for (ViewKhachHangResponse khachHang : khachHangService.getAll()) {
            if (khachHang.getMa().equals(tblKhachHang.getValueAt(rowKH, 0))) {
                idKH = khachHang.getId();
                capBac = khachHang.getCapBac();
            }
        }
        KhachHang khachHang = new KhachHang();
        khachHang.setId(idKH);
        hoaDon.setKhachHang(khachHang);
        banHangService.updateKhach(hoaDon, idHD);
        BanHangService service = new BanHangServiceImpl();
        loadHoaDonByNhanVien(service.getHoaDonByMa(nhanVien.getMa()));
        lblMaKH.setText(tblKhachHang.getValueAt(rowKH, 0).toString());
        lblHoTenKH.setText(tblKhachHang.getValueAt(rowKH, 1).toString());
        tblHoaDon.setRowSelectionInterval(0, 0);
        KhachHangView.setVisible(false);
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int rowHD = tblHoaDon.getSelectedRow();
        String maHD = tblHoaDon.getValueAt(rowHD, 0).toString();
//        Double tongTien = 0.0;

        List<ViewHoaDonChiTietResponse> listHD = banHangService.getHoaDonChiTietByHoaDon(maHD);
        loadGioHang(listHD);
//        for (ViewHoaDonChiTietResponse viewHoaDonChiTietResponse : listHD) {
//            tongTien += viewHoaDonChiTietResponse.getTongTien();
//        }
        String maKH = null;
        String capBac = null;
        for (ViewKhachHangResponse viewKhachHangResponse : khachHangService.getAll()) {
            if (viewKhachHangResponse.getHoTen().equals(tblHoaDon.getValueAt(rowHD, 3))) {
                maKH = viewKhachHangResponse.getMa();
                capBac = viewKhachHangResponse.htCapBac();
            }
        }
        lblMaKH.setText(maKH);
        lblHoTenKH.setText(tblHoaDon.getValueAt(rowHD, 3).toString());
        lblCapBac.setText(capBac);
        txtMaHD.setText(tblHoaDon.getValueAt(rowHD, 0).toString());
        txtTenNV.setText(tblHoaDon.getValueAt(rowHD, 2).toString());
        tinhtongTien();
//        txtTongTien.setText(String.format("%,.1f", tongTien));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            int rowHD = tblHoaDon.getSelectedRow();
            if (rowHD == -1) {
                JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
                return;
            } else {
                int rowSP = tblSanPham.getSelectedRow();
                int RowGioHang = tblGioHang.getSelectedRow();
                String maHD = tblHoaDon.getValueAt(rowHD, 0).toString();
                Integer soLuongThayDoi = 0;
                List<ViewHoaDonChiTietResponse> checkHoaDonChiTiet = banHangService.getHoaDonChiTietByHoaDon(maHD);
                for (ViewHoaDonChiTietResponse hoaDonChiTietResponse : checkHoaDonChiTiet) {
                    if (hoaDonChiTietResponse.getCtsp().equals(tblSanPham.getValueAt(rowSP, 0))) {
                        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                        Object[] options = {"Thêm số lượng", "Giảm số lượng", "Hủy"};
                        int result = JOptionPane.showOptionDialog(this,
                                "Sản phẩm này đã có trong giỏ hàng, bạn muốn làm gì?",
                                "Xác nhận",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[2]);
                        if (result == 0 || result == 1) {
                            String soLuongThayDoiStr = JOptionPane.showInputDialog(this, "Nhập số lượng: ");
                            if (soLuongThayDoiStr == null) {
                                return;
                            }
                            try {
                                soLuongThayDoi = Integer.parseInt(soLuongThayDoiStr);
                                if (soLuongThayDoi < 0) {
                                    JOptionPane.showMessageDialog(this, "Số lượng không thể âm");
                                    return;
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                                return;
                            }
                            if (result == 0) {
                                Integer soLuongTon = Integer.parseInt(tblSanPham.getValueAt(rowSP, 5).toString());
                                if (soLuongThayDoi > soLuongTon) {
                                    JOptionPane.showMessageDialog(this, "Không được vượt số lượng tồn");
                                    return;
                                }
                                String idHDCT = null;
                                for (ViewHoaDonChiTietResponse hdctResponse : banHangService.getHoaDonChiTietByHoaDon(maHD)) {
                                    if (hdctResponse.getCtsp().equals(tblSanPham.getValueAt(rowSP, 0))) {
                                        idHDCT = hdctResponse.getId();
                                    }
                                }
                                String idSanPham = null;
                                for (ViewChiTietSPResponse sanPham : banHangService.getAllChiTietSP()) {
                                    if (sanPham.getMaChiTietSP().equals(tblSanPham.getValueAt(rowSP, 0))) {
                                        idSanPham = sanPham.getId();
                                    }
                                }
                                ChiTietSP chiTietSP = new ChiTietSP();
                                chiTietSP.setSoLuongTon(soLuongThayDoi);
                                hoaDonChiTiet.setSoLuong(soLuongThayDoi);
                                BanHangService service = new BanHangServiceImpl();
                                banHangService.tangSL(hoaDonChiTiet, idHDCT);
                                loadGioHang(service.getHoaDonChiTietByHoaDon(maHD));
                                banHangService.updateGiamSL(chiTietSP, idSanPham);
                                loadSanPham(service.getAllChiTietSP());
                                tinhtongTien();
                            } else {
                                Integer soLuongHDCT = 0;
                                for (ViewHoaDonChiTietResponse hdct : banHangService.getHoaDonChiTietByHoaDon(maHD)) {
                                    soLuongHDCT = hdct.getSoLuong();
                                }
                                if (soLuongThayDoi > soLuongHDCT) {
                                    JOptionPane.showMessageDialog(this, "Số lượng sau khi giảm không được âm");
                                    return;
                                }
                                String idHDCT = null;
                                for (ViewHoaDonChiTietResponse hdctResponse : banHangService.getHoaDonChiTietByHoaDon(maHD)) {
                                    if (hdctResponse.getCtsp().equals(tblSanPham.getValueAt(rowSP, 0))) {
                                        idHDCT = hdctResponse.getId();
                                    }
                                }
                                String idSanPham = null;
                                for (ViewChiTietSPResponse sanPham : banHangService.getAllChiTietSP()) {
                                    if (sanPham.getMaChiTietSP().equals(tblSanPham.getValueAt(rowSP, 0))) {
                                        idSanPham = sanPham.getId();
                                    }
                                }
                                ChiTietSP chiTietSP = new ChiTietSP();
                                chiTietSP.setSoLuongTon(soLuongThayDoi);
                                hoaDonChiTiet.setSoLuong(soLuongThayDoi);
                                hoaDonChiTiet.setSoLuong(soLuongThayDoi);
                                BanHangService service = new BanHangServiceImpl();
                                banHangService.giamSL(hoaDonChiTiet, idHDCT);
                                loadGioHang(service.getHoaDonChiTietByHoaDon(maHD));
                                banHangService.updateTangSL(chiTietSP, idSanPham);
                                loadSanPham(service.getAllChiTietSP());
                                tinhtongTien();
                            }
                        }
                        return;
                    }
                }
                String idChiTietSP = null;
                String maSP = null;
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = banHangService.getByMaHD(maHD);
                if (hoaDon != null) {
                    hoaDonChiTiet.setHoaDon(hoaDon);

                    for (ViewChiTietSPResponse sanPham : banHangService.getAllChiTietSP()) {
                        if (sanPham.getMaChiTietSP().equals(tblSanPham.getValueAt(rowSP, 0))) {
                            idChiTietSP = sanPham.getId();
                            maSP = sanPham.getMaSP();
                        }
                        ChiTietSP chiTietSP = new ChiTietSP();
                        chiTietSP.setId(idChiTietSP);
                        hoaDonChiTiet.setChiTietSP(chiTietSP);
                    }
                    hoaDonChiTiet.setMaSp(maSP);
                    hoaDonChiTiet.setTenSp(tblSanPham.getValueAt(rowSP, 1).toString());

                    try {
                        Integer soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm"));
                        Integer soLuongTon = Integer.parseInt(tblSanPham.getValueAt(rowSP, 5).toString());
                        if (soLuong <= 0 || soLuong > soLuongTon) {
                            JOptionPane.showMessageDialog(this, "Số lượng > 0 và <= số lượng tồn");
                        } else {
                            hoaDonChiTiet.setSoLuong(soLuong);
                        }

                        ChiTietSP chiTietSP = new ChiTietSP();
                        chiTietSP.setSoLuongTon(soLuongTon - soLuong);

                        hoaDonChiTiet.setMauSac(tblSanPham.getValueAt(rowSP, 3).toString());
                        hoaDonChiTiet.setKichThuoc(Integer.valueOf(tblSanPham.getValueAt(rowSP, 2).toString()));
                        BigDecimal gia = new BigDecimal(tblSanPham.getValueAt(rowSP, 6).toString());
                        hoaDonChiTiet.setGia(gia);
                        if (!banHangService.createHDCT(hoaDonChiTiet)) {
                            JOptionPane.showMessageDialog(this, "Thành công");
                            loadGioHang(banHangService.getHoaDonChiTietByHoaDon(maHD));
                            if (banHangService.updateSLSP(chiTietSP, idChiTietSP)) {
                                BanHangService service = new BanHangServiceImpl();
                                loadSanPham(service.getAllChiTietSP());
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Thất bại");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Số lượng nhập phải là số");
                    }

                }
            }
            tinhtongTien();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
            return;
        }
        HoaDon hoaDon = new HoaDon();
        String idTrangThai = null;
        String idHD = null;
        String maHD = null;
//        String idKH = null;
        for (TrangThai trangThai : banHangService.getAllTrangThai()) {
            if (trangThai.getTenTrangThai() == 2) {
                idTrangThai = trangThai.getId();
            }
        }
        for (HoaDon hd : banHangService.getAllHoaDon()) {
            if (hd.getMa().equals(txtMaHD.getText())) {
                idHD = hd.getId();
                maHD = hd.getMa();
            }
        }

//        for (ViewKhachHangResponse khachHangResponse : khachHangService.getAll()) {
//            if(khachHangResponse.getMa().equals(lblMaKH.getText())){
//                idKH = khachHangResponse.getId();
//            }
//        }
        TrangThai trangThai = new TrangThai();
        trangThai.setId(idTrangThai);
        hoaDon.setTrangThai(trangThai);
//        KhachHang khachHang = new KhachHang();
//        khachHang.setId(idKH);
//        hoaDon.setKhachHang(khachHang);
        hoaDon.setNgayThanhToan(getDate());
        BigDecimal tongTien = BigDecimal.valueOf(Double.valueOf(txtThanhTien.getText()));
        hoaDon.setTongTien(tongTien);
        BigDecimal thanhTien = BigDecimal.valueOf(Double.valueOf(txtThanhTien.getText()));
        hoaDon.setThanhTien(thanhTien);
        BigDecimal tienTraLai = BigDecimal.valueOf(Double.valueOf(txtTienThua.getText()));
        hoaDon.setTienTraLai(tienTraLai);

        if (banHangService.thanhToan(hoaDon, idHD)) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    listHoaDonChiTiet = banHangService.getHoaDonChiTietByHoaDon(maHD);
                    ExportFilePdfByITextTaiQuay export = new ExportFilePdfByITextTaiQuay();
                    export.exportBill(hoaDon, listHoaDonChiTiet);
                    JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(this, "In hóa đơn thất bại");
                }
            }
            BanHangService service = new BanHangServiceImpl();
            loadHoaDonByNhanVien(service.getHoaDonByMa(nhanVien.getMa()));
            clearForm();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyPressed
//        int rowHD = tblHoaDon.getSelectedRow();
//        String maHD = tblHoaDon.getValueAt(rowHD, 0).toString();
//        BigDecimal tienKhachTra = new BigDecimal(0);    
//        BigDecimal tienThua = new BigDecimal(0);  
//        BigDecimal tongTien = new BigDecimal(0);
//        DecimalFormat df = new DecimalFormat("#,###");
//        txtTienKhachDua.setText(df.format(tienKhachTra) + "NVD");
//
//        List<ViewHoaDonChiTietResponse> listHD = banHangService.getHoaDonChiTietByHoaDon(maHD);
//        loadGioHang(listHD);
//
//        for (ViewHoaDonChiTietResponse viewHoaDonChiTietResponse : listHD) {
//            tongTien += viewHoaDonChiTietResponse.getTongTien();
//        }
//         tienThua = tienKhachTra - tongTien;
//        txtTienThua.setText(String.format("%,.0f", tienThua));
        tinhtongTien();
    }//GEN-LAST:event_txtTienKhachDuaKeyPressed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn");
            return;
        }
        int rowHDCT = tblGioHang.getSelectedRow();
        if (rowHDCT == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sản phẩm không?",
                "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            String idCTSP = null;
            String idHDCT = null;
            String maHD = tblHoaDon.getValueAt(rowHD, 0).toString();
            String maCTSP = tblGioHang.getValueAt(rowHDCT, 0).toString();
            for (ViewHoaDonChiTietResponse hoaDonChiTietResponse : banHangService.getHoaDonChiTietByHoaDon(maHD)) {
                if (hoaDonChiTietResponse.getCtsp().equals(maCTSP)) {
                    idHDCT = hoaDonChiTietResponse.getId();
                }
            }
            for (ViewChiTietSPResponse chiTietSPResponse : banHangService.getAllChiTietSP()) {
                if (chiTietSPResponse.getMaChiTietSP().equals(maCTSP)) {
                    idCTSP = chiTietSPResponse.getId();
                }
            }

            Integer soLuong = Integer.parseInt(tblGioHang.getValueAt(rowHDCT, 4).toString());
            ChiTietSP chiTietSP = new ChiTietSP();
            chiTietSP.setSoLuongTon(soLuong);
            BanHangService service = new BanHangServiceImpl();
            banHangService.xoaHDCT(idHDCT);
            loadGioHang(service.getHoaDonChiTietByHoaDon(maHD));
            banHangService.updateTangSL(chiTietSP, idCTSP);
            loadSanPham(service.getAllChiTietSP());
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");

        }

    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnXoaAllSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllSPActionPerformed
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn");
            return;
        }
        int rowHDCT = tblGioHang.getSelectedRow();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa tất cả sản phẩm không?",
                "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            String idCTSP = null;
            String idHDCT = null;
            String maHD = tblHoaDon.getValueAt(rowHD, 0).toString();
            for (ViewHoaDonChiTietResponse hoaDonChiTietResponse : banHangService.getHoaDonChiTietByHoaDon(maHD)) {
                for (ViewChiTietSPResponse chiTietSPResponse : banHangService.getAllChiTietSP()) {
                    if (chiTietSPResponse.getMaChiTietSP().equals(hoaDonChiTietResponse.getCtsp())) {
                        idHDCT = hoaDonChiTietResponse.getId();
                        idCTSP = chiTietSPResponse.getId();
                        ChiTietSP chiTietSP = new ChiTietSP();
                        Integer soLuong = hoaDonChiTietResponse.getSoLuong();
                        chiTietSP.setSoLuongTon(soLuong);
                        banHangService.xoaHDCT(idHDCT);
                        banHangService.updateTangSL(chiTietSP, idCTSP);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            BanHangService service = new BanHangServiceImpl();
            loadGioHang(service.getHoaDonChiTietByHoaDon(maHD));
            loadSanPham(service.getAllChiTietSP());
            clearForm();
        }
    }//GEN-LAST:event_btnXoaAllSPActionPerformed

    private void tblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseEntered

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed

    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnHuyDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDonActionPerformed
        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn để hủy");
        } else {
            int chon = JOptionPane.showConfirmDialog(this, " Xác nhận hủy đơn !", "Xác nhận",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (chon == JOptionPane.YES_OPTION) {
                String lyDo = JOptionPane.showInputDialog(this, "Lý do hủy đơn");
                HoaDon hoaDon = new HoaDon();
                String idHD = null;
                String idTrangThai = null;
                String maHD = tblHoaDon.getValueAt(rowHD, 0).toString();
                String idChiTietSP = null;

                for (TrangThai trangThai : banHangService.getAllTrangThai()) {
                    if (trangThai.getTenTrangThai() == 0) {
                        idTrangThai = trangThai.getId();
                    }
                }

                for (HoaDon hd : banHangService.getAllHoaDon()) {
                    if (hd.getMa().equals(txtMaHD.getText())) {
                        idHD = hd.getId();
                    }
                }

                TrangThai trangThai = new TrangThai();
                trangThai.setId(idTrangThai);
                hoaDon.setTrangThai(trangThai);
                hoaDon.setLyDo(lyDo);
                hoaDon.setNgaySua(getDate());

                banHangService.huyDon(hoaDon, idHD);
                BanHangService service = new BanHangServiceImpl();
                JOptionPane.showMessageDialog(this, "Hủy đơn thành công");
                loadHoaDonByNhanVien(service.getHoaDonByMa(nhanVien.getMa()));

                for (ViewHoaDonChiTietResponse vhdctr : banHangService.getHoaDonChiTietByHoaDon(maHD)) {
                    for (ViewChiTietSPResponse chiTietSPResponse : banHangService.getAllChiTietSP()) {
                        if (chiTietSPResponse.getMaChiTietSP().equals(vhdctr.getCtsp())) {
                            idChiTietSP = chiTietSPResponse.getId();
                            ChiTietSP chiTietSP = new ChiTietSP();
                            Integer soLuong = vhdctr.getSoLuong();
                            chiTietSP.setSoLuongTon(soLuong);
                            banHangService.updateTangSL(chiTietSP, idChiTietSP);
                            loadSanPham(service.getAllChiTietSP());
                        }
                    }
                }
                clearForm();
            }
        }
    }//GEN-LAST:event_btnHuyDonActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        try {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKhachHang("KH" + khachHangService.genMaKH());
            khachHang.setNgayTao(getDate());
            khachHang.setHoTen(txtTenKH.getText());
            khachHang.setSdt(txtSDT.getText());
            int gioiTinh = 0;
            if (rdoNam.isSelected()) {
                gioiTinh = 0;
            } else {
                gioiTinh = 1;
            }
            khachHang.setGioiTinh(gioiTinh);
            khachHang.setNgaySinh(java.sql.Date.valueOf(dateNgaySinh.getDate()));
            khachHang.setDiaChi(txtDiaChi.getText());
            if (!khachHangService.create(khachHang)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                pnlThongTinKH.setVisible(true); 
                pnlThemKH.setVisible(false);
                KhachHangService service = new KhachHangServiceImpl();
                loadKhachHang(service.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_btnThemKHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame KhachHangView;
    private javax.swing.ButtonGroup bgrGioiTinh;
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnHuyDon;
    private javax.swing.JButton btnTaoDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaAllSP;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cbbKhuyenMai;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JComboBox<String> cbbTrangThaiHD;
    private com.github.lgooddatepicker.components.DatePicker dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCapBac;
    private javax.swing.JLabel lblHoTenKH;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlTaiQuay;
    private javax.swing.JPanel pnlThemKH;
    private javax.swing.JPanel pnlThongTinKH;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
