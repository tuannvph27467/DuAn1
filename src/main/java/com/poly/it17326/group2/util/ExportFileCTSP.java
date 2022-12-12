/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.util;

import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class ExportFileCTSP {

    public boolean ExportFileExcel(List<ViewChiTietSPResponse> lst) {
        boolean check = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Quản Lý Sản Phẩm");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell1 = firstRow.createCell(0);
            Cell firstCell2 = firstRow.createCell(1);
            Cell firstCell3 = firstRow.createCell(2);
            Cell firstCell4 = firstRow.createCell(3);
            Cell firstCell5 = firstRow.createCell(4);
            Cell firstCell6 = firstRow.createCell(5);
            Cell firstCell7 = firstRow.createCell(6);
            Cell firstCell8 = firstRow.createCell(7);
            Cell firstCell9 = firstRow.createCell(8);
            Cell firstCell10 = firstRow.createCell(9);
            Cell firstCell11 = firstRow.createCell(10);

            firstCell1.setCellValue("STT");
            firstCell2.setCellValue("Mã CTSP");
            firstCell3.setCellValue("Tên SP");
            firstCell4.setCellValue("Thương Hiệu");
            firstCell5.setCellValue("Màu Sắc");
            firstCell6.setCellValue("Nhà Cung Cấp");
            firstCell7.setCellValue("Đế Giày");
            firstCell8.setCellValue("Kích Thước");
            firstCell9.setCellValue("Số lượng tồn");
            firstCell10.setCellValue("Giá bán");
            firstCell11.setCellValue("Trạng thái");
            int index = 1;
            for (ViewChiTietSPResponse xx : lst) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(index++);

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(xx.getMaChiTietSP());

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(xx.getTenSP());

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(xx.getThuongHieu());

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(xx.getMauSac());

                Cell cell6 = row.createCell(5);
                cell6.setCellValue(xx.getNCC());

                Cell cell7 = row.createCell(6);
                cell7.setCellValue(xx.getDeGiay());

                Cell cell8 = row.createCell(7);
                cell8.setCellValue(xx.getKichThuoc());

                Cell cell9 = row.createCell(8);
                cell9.setCellValue(xx.getSoLuongTon());

                Cell cell10 = row.createCell(9);
                DecimalFormat df = new DecimalFormat("#,###");
                cell10.setCellValue(df.format(xx.getGia()));

                Cell cell11 = row.createCell(10);
                cell11.setCellValue(xx.getTrangThai() == 1 ? "Đang hoạt động" : "Dừng hoạt động");

            }
            try {
                FileOutputStream outputStream = new FileOutputStream("ChiTietSanPham" + Calendar.getInstance().getTimeInMillis() + ".xlsx");
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
}
