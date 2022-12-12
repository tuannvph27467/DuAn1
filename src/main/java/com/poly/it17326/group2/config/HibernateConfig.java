package com.poly.it17326.group2.config;

import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.ChucVu;
import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.domainmodel.MauSac;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.domainmodel.NhanVien;
import com.poly.it17326.group2.domainmodel.KhachHang;
import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.domainmodel.TrangThai;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        java.util.Properties properties = new java.util.Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=QUANLYBANGIAY");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(DeGiay.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(KichThuoc.class);
        conf.addAnnotatedClass(NhaCungCap.class);
        conf.addAnnotatedClass(ThuongHieu.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(ChiTietSP.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(TrangThai.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}
