package ru.smith.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.smith.entity.Record;
import ru.smith.util.HibernateUtil;

import java.util.List;

public class RecordDAOImpl implements RecordDAO {

    private Session session;

    @Override
    public List<Record> listAllRecords() {
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("SELECT r FROM Record r");
            session.getTransaction().commit();
            return query.list();

        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public void save(Record record) {
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(record);
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
