package ru.smith.controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.smith.util.HibernateUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean
@ViewScoped
public class TSMoreX {

    private List<Object[]> objectList;

    public List<Object[]> getObjectList() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Instant instant = ((Instant)session.getNamedQuery("maxTimeStamp").list().get(0)).minus(Duration.ofHours(1));

        Query query = session.getNamedQuery("recordsWithTSMoreX");
        query.setParameter("hour", instant);
        List<Object[]> list= query.list();

        Set<String> set = new HashSet<>();
        list.forEach(obj ->
                set.add(obj[0] + ";" + obj[1]));
        set.forEach(System.out::println);
        list = new ArrayList<>();
        for (String o : set) {
            list.add(o.split(";"));
        }
        session.getTransaction().commit();
        session.close();

        return list;
    }

}