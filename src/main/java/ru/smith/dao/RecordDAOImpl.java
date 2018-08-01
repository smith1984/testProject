package ru.smith.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.smith.entity.Record;
import ru.smith.util.HibernateUtil;

import java.util.List;

public class RecordDAOImpl implements RecordDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Record> listAllRecords() {
        try{
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
            HibernateUtil.shutdown();
        }
    }

    @Override
    public void saveOrUpdate(Record record) {
        try{
            session.beginTransaction();
            session.saveOrUpdate(record);
            session.getTransaction().commit();
            System.out.println("Record add db");
        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
