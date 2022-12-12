/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.KichThuoc;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author haodqph27423
 */
public class KichThuocRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<KichThuoc> getListFromDB() {
        List<KichThuoc> list;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<KichThuoc> query = session.createQuery("SELECT p FROM KichThuoc p", KichThuoc.class);
            list = query.getResultList();
        }
        return list;
    }

    public Boolean addNew(KichThuoc kichThuoc) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(kichThuoc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean upDate(KichThuoc kichThuoc) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(kichThuoc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean delete(String id) {
        try ( Session se = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = se.beginTransaction();
            Query query = se.createQuery("DELETE KichThuoc p WHERE p.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public int genMaKichThuoc() {
        String maStr = "";
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(Ma,3,10))) from KichThuoc";
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
}
