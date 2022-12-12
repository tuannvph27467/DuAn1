/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.HoaDonChiTiet;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tuannvph27467
 */
public class HoaDonChiTietRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<HoaDonChiTiet> getAll() {
        Query query = session.createQuery("FROM HoaDonChiTiet");
        return query.getResultList();
    }

    public Boolean add(HoaDonChiTiet hoaDonChiTiet) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(hoaDonChiTiet);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM HoaDonChiTiet WHERE Id = :Id");
            query.setParameter("Id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
