/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class TaiMauExcelCTSP {

    public boolean ImportExcel() {
        boolean check = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Quản Lý Sản Phẩm");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell1 = firstRow.createCell(0);
            Cell firstCell2 = firstRow.createCell(1);
            Cell firstCell4 = firstRow.createCell(2);
            Cell firstCell5 = firstRow.createCell(3);
            Cell firstCell6 = firstRow.createCell(4);
            Cell firstCell7 = firstRow.createCell(5);
            Cell firstCell8 = firstRow.createCell(6);
            Cell firstCell9 = firstRow.createCell(7);
            Cell firstCell10 = firstRow.createCell(8);

            firstCell1.setCellValue("STT");
            firstCell2.setCellValue("Tên SP");
            firstCell4.setCellValue("Thương Hiệu");
            firstCell5.setCellValue("Màu Sắc");
            firstCell6.setCellValue("Nhà Cung Cấp");
            firstCell7.setCellValue("Đế Giày");
            firstCell8.setCellValue("Kích Thước");
            firstCell9.setCellValue("Số lượng tồn");
            firstCell10.setCellValue("Giá bán");

            int index = 1;
            Row row = sheet.createRow(1);
            Cell cell1 = row.createCell(0);
            Cell cell2 = row.createCell(1);
            Cell cell4 = row.createCell(2);
            Cell cell5 = row.createCell(3);
            Cell cell6 = row.createCell(4);
            Cell cell7 = row.createCell(5);
            Cell cell8 = row.createCell(6);
            Cell cell9 = row.createCell(7);
            Cell cell10 = row.createCell(8);

            cell1.setCellValue(String.valueOf(1));
            cell2.setCellValue("Nike For One");
            cell4.setCellValue("Nike");
            cell5.setCellValue("Đen");
            cell6.setCellValue("Kim Đồng");
            cell7.setCellValue("Đế Titan");
            cell8.setCellValue("37");
            cell9.setCellValue("40");
            cell10.setCellValue("5000000");


            try {
                FileOutputStream outputStream = new FileOutputStream("QuanLySanPhamTemplate.xlsx");
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
}
