package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.ChucVu;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//hocnvph27417
public class ChucVuRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<ChucVu> getAll() {
        Query query = session.createQuery("FROM ChucVu");
        return query.getResultList();
    }

    public Boolean add(ChucVu chucVu) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(chucVu);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public Boolean delete(String id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM ChucVu WHERE Id = :Id");
            query.setParameter("Id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
