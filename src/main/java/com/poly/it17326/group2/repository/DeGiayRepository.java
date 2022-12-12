/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.DeGiay;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author nguye
 */
public class DeGiayRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<DeGiay> getList() {
        List<DeGiay> list;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            TypedQuery<DeGiay> query = session.createQuery("SELECT p FROM DeGiay p", DeGiay.class);
            list = query.getResultList();
        }
        return list;
    }

    public Boolean create(DeGiay deGiay) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(deGiay);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean delete(String id) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE DeGiay p WHERE p.id = : id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int genMaDeGiay() {
        String maStr = "";
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(Ma,3,10))) from DeGiay";
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
