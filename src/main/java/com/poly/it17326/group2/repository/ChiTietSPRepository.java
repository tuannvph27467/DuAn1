/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.ChiTietSP;
import com.poly.it17326.group2.domainmodel.DeGiay;
import com.poly.it17326.group2.domainmodel.MauSac;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import com.poly.it17326.group2.domainmodel.SanPham;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import com.poly.it17326.group2.domainmodel.ThuongHieu;
import com.poly.it17326.group2.response.ViewChiTietSPResponse;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author anhntnph27418
 */
public class ChiTietSPRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<ChiTietSP> getAll() {
        Query query = session.createQuery("SELECT p FROM ChiTietSP p WHERE p.trangThai = 1 ORDER BY CONVERT(INT,SUBSTRING(p.maChiTietSP,5,10)) DESC");
        return query.getResultList();
    }

    public List<SanPham> getSanPham() {
        Query query = session.createQuery("FROM SanPham");
        return query.getResultList();
    }

    public List<MauSac> getMau() {
        Query query = session.createQuery("FROM MauSac");
        return query.getResultList();
    }

    public List<NhaCungCap> getNCC() {
        Query query = session.createQuery("FROM NhaCungCap");
        return query.getResultList();
    }

    public List<ThuongHieu> getTH() {
        Query query = session.createQuery("FROM ThuongHieu");
        return query.getResultList();
    }

    public List<KichThuoc> getSize() {
        Query query = session.createQuery("FROM KichThuoc");
        return query.getResultList();
    }

    public List<DeGiay> getDeGiay() {
        Query query = session.createQuery("FROM DeGiay");
        return query.getResultList();
    }

    public Boolean add(ChiTietSP chiTietSP) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(chiTietSP);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public Boolean delete(String id) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM ChiTietSP WHERE Id = :Id");
            query.setParameter("Id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ChiTietSP getById(String id) {
        try {
            Query query = session.createQuery("FROM ChiTietSP WHERE Id = :Id");
            query.setParameter("Id", id);
            return (ChiTietSP) query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }

    public int genMaCTSP() {
        String maStr = "";
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(a.MaChiTietSP,5,10))) from ChiTietSP a";
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

    public SanPham findSanPhamByTen(String ten) {
        Query query = session.createQuery("Select p From SanPham p where p.ten = :ten");
        query.setParameter("ten", ten);
        return (SanPham) query.getSingleResult();
    }

    public KichThuoc findKichThuocByTen(String ten) {
        Query query = session.createQuery("Select p From KichThuoc p where p.ten = :ten");
        query.setParameter("ten", ten);
        return (KichThuoc) query.getSingleResult();
    }

    public DeGiay findDeGiayByTen(String ten) {
        Query query = session.createQuery("Select p From DeGiay p where p.ten = :ten");
        query.setParameter("ten", ten);
        return (DeGiay) query.getSingleResult();
    }

    public MauSac findMauSacByTen(String ten) {
        Query query = session.createQuery("Select p From MauSac p where p.ten = :ten");
        query.setParameter("ten", ten);
        return (MauSac) query.getSingleResult();
    }

    public ThuongHieu findThuongHieuByTen(String ten) {
        Query query = session.createQuery("Select p From ThuongHieu p where p.ten = :ten");
        query.setParameter("ten", ten);
        return (ThuongHieu) query.getSingleResult();
    }

    public NhaCungCap findNhaCungCapByTen(String ten) {
        Query query = session.createQuery("Select p From NhaCungCap p where p.ten = :ten");
        query.setParameter("ten", ten);
        return (NhaCungCap) query.getSingleResult();
    }

    public List<ChiTietSP> timKiem(String ten) {
        List<ChiTietSP> lst = new ArrayList<>();
        try {
            Query query = session.createQuery("SELECT p FROM ChiTietSP p WHERE p.sanPham.ten LIKE CONCAT('%',:ten,'%') "
                    + "OR p.thuongHieu.ten LIKE CONCAT('%',:thuongHieu,'%') "
                    + "OR p.kichThuoc.ten LIKE CONCAT('%',:kichThuoc,'%') "
                    + "OR p.trangThai LIKE CONCAT('%',:trangThai,'%') ORDER BY CONVERT(INT,SUBSTRING(p.maChiTietSP,5,10)) DESC");
            query.setParameter("ten", ten);
            query.setParameter("thuongHieu", ten);
            query.setParameter("kichThuoc", ten);
            query.setParameter("trangThai", ten);
            lst = query.getResultList();
        } catch (Exception e) {
        }
        return lst;
    }

    public static void main(String[] args) {
        ChiTietSPRepository repo = new ChiTietSPRepository();
        List<ChiTietSP> list = repo.timKiem("1");
        for (ChiTietSP chiTietSP : list) {
            System.out.println(chiTietSP.getId());
        }

    }

    public ChiTietSP findByCBB(
            String tenSp, String tenThuongHieu, String tenMauSac, String tenNhaCungCap, String tenDeGiay, String tenKichThuoc) {
        ChiTietSP chiTietSP = new ChiTietSP();
        try {
            String hql = "SELECT p FROM ChiTietSP p WHERE p.sanPham.ten = :tenSP "
                    + "AND p.thuongHieu.ten = :tenThuongHieu "
                    + "AND p.mauSac.ten = :tenMauSac "
                    + "AND p.nhaCungCap.ten = :tenNhaCungCap "
                    + "AND p.deGiay.ten = :tenDeGiay "
                    + "And p.kichThuoc.ten = :tenKichThuoc";
            Query query = session.createQuery(hql);
            query.setParameter("tenSP", tenSp);
            query.setParameter("tenThuongHieu", tenThuongHieu.trim());
            query.setParameter("tenMauSac", tenMauSac.trim());
            query.setParameter("tenNhaCungCap", tenNhaCungCap.trim());
            query.setParameter("tenDeGiay", tenDeGiay.trim());
            query.setParameter("tenKichThuoc", tenKichThuoc);
            chiTietSP = (ChiTietSP) query.getSingleResult();
        } catch (Exception e) {
        }
        return chiTietSP;
    }

    protected Transaction trans;

    public Boolean saveAll(List<ChiTietSP> list) {
        try {
            trans = session.beginTransaction();
            for (ChiTietSP xx : list) {
                session.saveOrUpdate(xx);
            }
            trans.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
