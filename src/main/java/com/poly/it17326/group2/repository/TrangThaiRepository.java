package com.poly.it17326.group2.repository;

import com.poly.it17326.group2.config.HibernateConfig;
import com.poly.it17326.group2.domainmodel.TrangThai;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

public class TrangThaiRepository {

    public List<TrangThai> getAll(){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("FROM TrangThai");
        return query.getResultList();
    }
    
}
