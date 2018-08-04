package ru.smith.controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.smith.util.HibernateUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class StopUseForm {

    private  List<Object[]> objectList;

    public List<Object[]> getObjectList() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedNativeQuery("stopUseForm");
        List<Object[]> list = query.list();
        session.getTransaction().commit();
        session.close();

        return list;
    }

}
