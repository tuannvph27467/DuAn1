/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author quynhncph26201
 */
public class ImportExcelFileNhanVien {

    public boolean ImportExcel() {
        boolean check = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Nhân viên");
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
            firstCell1.setCellValue("STT");
            firstCell2.setCellValue("Họ Tên");
            firstCell3.setCellValue("Giới Tính");
            firstCell4.setCellValue("Ngày Sinh");
            firstCell5.setCellValue("Địa Chỉ");
            firstCell6.setCellValue("Số Điện Thoại");
            firstCell7.setCellValue("Email");
            firstCell8.setCellValue("Vai Trò");
            int index = 1;
            Row row = sheet.createRow(0);
            Cell cell1 = row.createCell(0);
            Cell cell2 = row.createCell(1);
            Cell cell3 = row.createCell(2);
            Cell cell4 = row.createCell(3);
            Cell cell5 = row.createCell(4);
            Cell cell6 = row.createCell(5);
            Cell cell7 = row.createCell(6);
            Cell cell8 = row.createCell(7);
            cell1.setCellValue(String.valueOf(1));
            cell2.setCellValue("Nguyễn Văn A");
            cell3.setCellValue("Nam");
            cell4.setCellValue("01-04-2003");
            cell5.setCellValue("Thái Bình");
            cell6.setCellValue("066666666");
            cell7.setCellValue("nva6868@gmail.com");
            cell8.setCellValue("Nhân Viên");
            try {
                FileOutputStream outputStream = new FileOutputStream("MauNhanVien.xlsx");
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
