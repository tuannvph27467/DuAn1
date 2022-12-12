/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.HoaDon;
import com.poly.it17326.group2.domainmodel.KhachHang;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author DELL
 */
public class KhachHangRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<KhachHang> getAll() {
        Query query = session.createQuery("SELECT p FROM KhachHang p ORDER BY p.maKhachHang DESC");
        return query.getResultList();
    }

    public Boolean add(KhachHang khachHang) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(khachHang);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public Boolean updateCapBac(KhachHang khachHang, String idKH) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Query query = session.createQuery("UPDATE KhachHang p SET p.capBac = :capBac WHERE p.id = :idKH");
            query.setParameter("capBac", khachHang.getCapBac());
            query.setParameter("idKH", idKH);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public List<KhachHang> getByTenKhachHang(String hoTen) {
        Query query = session.createQuery("Select p From KhachHang p where p.hoTen = :hoTen");
        query.setParameter("hoTen", hoTen);
        return query.getResultList();
    }

    public List<KhachHang> getAllKhacHangDangHoatDong() {
        List<KhachHang> list = new ArrayList<>();
        try {
            org.hibernate.query.Query query = session.createQuery("SELECT p FROM KhachHang p where p.maKhachHang <> 'KH0'");
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public BigDecimal getTongTienByIdKhachHang(String idKH) {
        BigDecimal money = new BigDecimal(0);
        try {
            Query query = session.createQuery("SELECT SUM(a.thanhTien) FROM HoaDon a where a.khachHang.id = :idKH");
            query.setParameter("idKH", idKH);
            money = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return money;
    }

    public List<HoaDon> getLichSuGiaoDich(String idKH) {
        try {
            Query query = session.createQuery("SELECT p FROM HoaDon p  where p.khachHang.id = :idKH");
            query.setParameter("idKH", idKH);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int genMaKH() {
        String maStr = "";
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,3,10))) from KhachHang";
            NativeQuery query = session.createNativeQuery(nativeQuery);
            if (query.getSingleResult() != null) {
                maStr = query.getSingleResult().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;
    }

    public static void main(String[] args) {
        KhachHangRepository khachHangRepository = new KhachHangRepository();
        String id = "43992A5D-1DED-4F67-8BD4-EBD83BBCEFC0";
        BigDecimal tongTienKhachMua = khachHangRepository.getTongTienByIdKhachHang(id);
        System.out.println(tongTienKhachMua);
    }
}
