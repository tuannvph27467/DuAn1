/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.util;

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.domainmodel.MauSac;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.repository.ChiTietSPRepository;
import com.poly.it17326.group2.service.ChiTietSPService;
import com.poly.it17326.group2.service.impl.ChiTietSPServiceImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class ImportExcelCTSP {

    private ChiTietSPRepository cTSanPhamRepository;
    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();

    public ImportExcelCTSP() {
        cTSanPhamRepository = new ChiTietSPRepository();
    }

    public void ImportFile(String path) {
        try {
            List<ChiTietSP> listctsp = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            int mactsp = cTSanPhamRepository.genMaCTSP();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                String sanPhamStr = String.valueOf(getCellValue(currentRow.getCell(1))).trim();
                String thuongHieuStr = String.valueOf(getCellValue(currentRow.getCell(2))).trim();
                String mauSacStr = String.valueOf(getCellValue(currentRow.getCell(3))).trim();
                String nhaCungCapStr = String.valueOf(getCellValue(currentRow.getCell(4))).trim();
                String deGiayStr = String.valueOf(getCellValue(currentRow.getCell(5))).trim();
                String kichThuocStr = String.valueOf(getCellValue(currentRow.getCell(6))).trim();
                String soLuongTon = String.valueOf(getCellValue(currentRow.getCell(7))).trim();
                String giaBan = String.valueOf(getCellValue(currentRow.getCell(8))).trim();
                if (mauSacStr.isEmpty() && sanPhamStr.isEmpty() && kichThuocStr.isEmpty() && nhaCungCapStr.isEmpty()
                        && deGiayStr.isEmpty() && thuongHieuStr.isEmpty() && soLuongTon.isEmpty() && giaBan.isEmpty()) {
                    continue;
                }
                if (mauSacStr.isEmpty() && sanPhamStr.isEmpty() && kichThuocStr.isEmpty() && nhaCungCapStr.isEmpty()
                        && deGiayStr.isEmpty() && thuongHieuStr.isEmpty() && soLuongTon.isEmpty() && giaBan.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không để trống");
                    return;
                }
                SanPham sanPham = cTSanPhamRepository.findSanPhamByTen(sanPhamStr);
                System.out.println(sanPham.getTen());
                if (sanPham == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
                    return;
                }
                ThuongHieu thuongHieu = cTSanPhamRepository.findThuongHieuByTen(thuongHieuStr);
                System.out.println(thuongHieu.getTen());
                if (thuongHieu == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy thương hiệu");
                    return;
                }
                MauSac mauSac = cTSanPhamRepository.findMauSacByTen(mauSacStr);
                System.out.println(mauSac.getTen());
                if (mauSac == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy màu sắc");
                    return;
                }

                NhaCungCap nhaCC = cTSanPhamRepository.findNhaCungCapByTen(nhaCungCapStr);
                System.out.println(nhaCC.getTen());
                if (nhaCC == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy chất liệu");
                    return;
                }

                DeGiay deGiay = cTSanPhamRepository.findDeGiayByTen(deGiayStr);
                System.out.println(deGiay.getTen());
                if (deGiay == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy đế giày");
                    return;
                }

                KichThuoc kichThuoc = cTSanPhamRepository.findKichThuocByTen(String.valueOf((int) Double.parseDouble(kichThuocStr)));
                System.out.println(kichThuoc.getTen());
                if (kichThuoc == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kích thước");
                    return;
                }
                ChiTietSP chiTietSP = new ChiTietSP();
                ChiTietSP chiTietSPcheck = cTSanPhamRepository.findByCBB(sanPham.getTen(), thuongHieu.getTen(), mauSac.getTen(), nhaCC.getTen(), deGiay.getTen(), kichThuoc.getTen());
                if (chiTietSPcheck.getMaChiTietSP()== null) {
                    chiTietSP.setMaChiTietSP("CTSP" + mactsp++);
                    chiTietSP.setSanPham(sanPham);
                    chiTietSP.setThuongHieu(thuongHieu);
                    chiTietSP.setMauSac(mauSac);
                    chiTietSP.setNhaCungCap(nhaCC);
                    chiTietSP.setDeGiay(deGiay);
                    chiTietSP.setKichThuoc(kichThuoc);
                    chiTietSP.setSoLuongTon((int) Double.parseDouble(soLuongTon));
                    chiTietSP.setGia(new BigDecimal(giaBan));
                    chiTietSP.setTrangThai(1);
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                    simpleDateFormat.applyPattern("YYYY-MM-dd");
                    String format = simpleDateFormat.format(date);
                    chiTietSP.setNgayTao(format);
                    chiTietSPService.create(chiTietSP);
                } else {
                    chiTietSPcheck.setGia(new BigDecimal(giaBan));
                    if (chiTietSPcheck.getSoLuongTon() != null) {
                        chiTietSPcheck.setSoLuongTon((int) Double.parseDouble(soLuongTon) + chiTietSPcheck.getSoLuongTon());
                    } else {
                        chiTietSPcheck.setSoLuongTon((int) Double.parseDouble(soLuongTon));
                    }
                    chiTietSPService.create(chiTietSPcheck);
                }
            }
            cTSanPhamRepository.saveAll(listctsp);
            JOptionPane.showMessageDialog(null, "Import file excel thành công");
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}
