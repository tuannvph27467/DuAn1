/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group2.view.ViewBanHang;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tuannvph27467
 */
public class BanHangRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<ChiTietSP> getAll() {
        Query query = session.createQuery("FROM ChiTietSP", ChiTietSP.class);

        return query.getResultList();
    }

    public List<HoaDon> getHoaDonByMa(String ma) {
        Query query = session.createQuery("Select p From HoaDon p where p.nhanVien.ma = :ma and p.trangThai.tenTrangThai = 1 ORDER BY p.ngayTao DESC", HoaDon.class);
        query.setParameter("ma", ma);
        return query.getResultList();
    }

    public List<HoaDonChiTiet> getHDCTByHoaDon(String ma) {
        Query query = session.createQuery("Select p from HoaDonChiTiet p where p.hoaDon.ma = :ma", HoaDonChiTiet.class);
        query.setParameter("ma", ma);
        return query.getResultList();
    }

    public HoaDon getByMaHD(String maHD) {
        try {
            Query query = session.createQuery("FROM HoaDon WHERE Ma = :Ma");
            query.setParameter("Ma", maHD);
            return (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean updateSLSP(ChiTietSP chiTietSP, String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE ChiTietSP p SET p.soLuongTon = :soLuongTon WHERE p.id = :id");
            query.setParameter("soLuongTon", chiTietSP.getSoLuongTon());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean huyDon(HoaDon hoaDon, String idHD) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE HoaDon p SET p.trangThai.id = :trangThai, p.lyDo = :lyDo, p.ngaySua = :ngaySua WHERE p.id = :idHD");
            query.setParameter("trangThai", hoaDon.getTrangThai().getId());
            query.setParameter("lyDo", hoaDon.getLyDo());
            query.setParameter("ngaySua", hoaDon.getNgaySua());
            query.setParameter("idHD", idHD);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean thanhToan(HoaDon hoaDon, String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE HoaDon p SET p.trangThai = :trangThai, "
                    + "p.ngayThanhToan = :ngayThanhToan, p.tongTien = :tongTien, "
                    + "p.thanhTien = :thanhTien, p.tienTraLai = :tienTraLai WHERE p.id = :id");
            query.setParameter("trangThai", hoaDon.getTrangThai());
            query.setParameter("ngayThanhToan", hoaDon.getNgayThanhToan());
            query.setParameter("tongTien", hoaDon.getTongTien());
            query.setParameter("thanhTien", hoaDon.getThanhTien());
            query.setParameter("tienTraLai", hoaDon.getTienTraLai());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean updateKhach(HoaDon hoaDon, String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE HoaDon p SET p.khachHang.id = :idKhachHang WHERE p.id = :id");
            query.setParameter("idKhachHang", hoaDon.getKhachHang().getId());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean tangSL(HoaDonChiTiet hoaDonChiTiet, String idHDCT) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE HoaDonChiTiet p SET p.soLuong = :soLuong + p.soLuong WHERE p.id = :idHDCT");;
            query.setParameter("soLuong", hoaDonChiTiet.getSoLuong());
            query.setParameter("idHDCT", idHDCT);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean giamSL(HoaDonChiTiet hoaDonChiTiet, String idHDCT) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE HoaDonChiTiet p SET p.soLuong = p.soLuong - :soLuong WHERE p.id = :idHDCT");
            query.setParameter("soLuong", hoaDonChiTiet.getSoLuong());
            query.setParameter("idHDCT", idHDCT);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean upadteTangSP(ChiTietSP chiTietSP, String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE ChiTietSP p SET p.soLuongTon = :soLuongTon + p.soLuongTon WHERE p.id = :id");
            query.setParameter("soLuongTon", chiTietSP.getSoLuongTon());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean upadteGiamSP(ChiTietSP chiTietSP, String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE ChiTietSP p SET p.soLuongTon = p.soLuongTon - :soLuongTon  WHERE p.id = :id");
            query.setParameter("soLuongTon", chiTietSP.getSoLuongTon());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean xoaHDCT(String idHDCT) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM HoaDonChiTiet p WHERE p.id = :idHDCT");
            query.setParameter("idHDCT", idHDCT);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        BanHangRepository banHangRepository = new BanHangRepository();
//        List<HoaDonChiTiet> chiTiet = banHangRepository.getHDCTByHoaDon("HD1");
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
//        hoaDonChiTiet.setSoLuong(2);
//        banHangRepository.tangSL(hoaDonChiTiet, "727A40AF-3DBC-4DCF-9C09-38AC35BB5D67");
        System.out.println(hoaDonChiTiet.getSoLuong());
    }

}
