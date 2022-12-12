/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author nguye
 */
public class DoiMatKhauRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public String GetIdNhanVien(String email, String matkhau) {
        String id = "";
        try {
            String hql = "select a.id from NhanVien a where a.email = :email and a.matKhau = :matkhau";
            Query query = session.createQuery(hql);
            query.setParameter("matkhau", matkhau);
            query.setParameter("email", email);
            id = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  id;
    }

    public boolean DoiMatKhau(String matkhau, String id) {
        try {
            Transaction trans = session.beginTransaction();
            String hql = "UPDATE NHANVIEN SET matKhau = :matKhau where id = :id";
            Query query = session.createNativeQuery(hql);
            query.setParameter("matKhau", matkhau);
            query.setParameter("id", id);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
