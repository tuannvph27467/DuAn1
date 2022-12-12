package com.poly.it17326.group2.util;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.response.ViewHoaDonChiTietResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class ExportFilePdfByITextTaiQuay {

    public static final String pathUnicode = "font\\unicode.ttf";

    private String getDate() {
        Calendar ca = new GregorianCalendar();
        int year = ca.get(Calendar.YEAR);
        int mon = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DAY_OF_MONTH);

        String ngay = year + "-" + mon + "-" + day;
        return ngay;
    }

    public void exportBill(HoaDon hoaDon, List<ViewHoaDonChiTietResponse> listHoaDonChiTiet) {
        try {
            String path = "HoaDon" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(pathUnicode, BaseFont.IDENTITY_H);

            Table table = new Table(columWidth);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new Cell().add("Bill Sneaker Store").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("Mã hóa đơn: " + hoaDon.getMa() + "\n Sneaker Store").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 250, 200, 200};
            Table customerInforTable = new Table(colWidth);
            customerInforTable.setFont(font);
            customerInforTable.addCell(new Cell(0, 4)
                    .add("Thông tin khách hàng").setBold().setBorder(Border.NO_BORDER));

            if (hoaDon.getKhachHang() == null) {
                customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Khách bán lẻ").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("No").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("No").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Ngày thanh toán:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
//                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
//                String date = sdf.format(hoaDon.getNgayThanhToan());
//                customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            } else {
                customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getHoTen()).setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getDiaChi()).setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Ngày thanh toán:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
//                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
//                String date = sdf.format(hoaDon.getNgayThanhToan());
//                customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            }

            float itemColWidth[] = {15, 110, 170, 50, 110, 110};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thông tin SP").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("SL").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giá bán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thành tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            int index = 1;
            DecimalFormat df = new DecimalFormat("#,###");
            for (ViewHoaDonChiTietResponse xx : listHoaDonChiTiet) {
                itemTable.addCell(new Cell().add(index++ + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenSP()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getMauSac() + " " + "Size: " + xx.getKichThuoc()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getSoLuong() + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(xx.getGia()) + " VND").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(new BigDecimal(xx.getSoLuong()).multiply(xx.getGia())) + " Vnđ").setBorder(Border.NO_BORDER));
            }

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tổng tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTongTien()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("Tiền khách trả").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//            BigDecimal tienKhachTra = hoaDon.getTienKhachTra() == null ? new BigDecimal(0) : hoaDon.getTienKhachTra();
//            itemTable.addCell(new Cell().add(df.format(tienKhachTra) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("Ngân hàng").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//            BigDecimal tienKhachCK = hoaDon.getTienKhachChuyenKhoan() == null ? new BigDecimal(0) : hoaDon.getTienKhachChuyenKhoan();
//            itemTable.addCell(new Cell().add(df.format(tienKhachCK) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//            itemTable.addCell(new Cell().add("Tiền thừa").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//            itemTable.addCell(new Cell().add(df.format(hoaDon.getTienTraLai()) + " VND").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4)
                    .add("Trường cao đẳng FPT Polytechnich, P.Trịnh Văn Bô,\nP.Phương Canh, Q.Nam Từ Liêm, TP.Hà Nội").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0686868686").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTable);
            document.add(new Paragraph("\n"));
            document.add(itemTable);
            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        new ExportFilePdfByITextTaiQuay().exportBill();
//    }
}
