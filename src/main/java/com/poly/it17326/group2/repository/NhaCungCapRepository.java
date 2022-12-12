/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.NhaCungCap;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class NhaCungCapRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<NhaCungCap> getList() {
        List<NhaCungCap> list;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<NhaCungCap> query = session.createQuery("SELECT p FROM NhaCungCap p", NhaCungCap.class);
            list = query.getResultList();
        }
        return list;
    }

    public Boolean them(NhaCungCap nhaCungCap) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(nhaCungCap);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean xoa(String id) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE NhaCungCap p WHERE p.id = : id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int genMaNhaCungCap() {
        String maStr = "";
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(Ma,4,10))) from NhaCungCap";
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
