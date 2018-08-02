package ru.smith.util;

import org.hibernate.Session;
import ru.smith.dao.RecordDAOImpl;
import ru.smith.entity.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LoadDBFromCSV {

    public static void load(String path) {
        Record record;
        BufferedReader br = null;
        String line;
        String[] row;
        int count;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        RecordDAOImpl recordDAO = new RecordDAOImpl(session);

        try {

            br = new BufferedReader(new FileReader(path));
            br.readLine();
            count = 0;
            while ((line = br.readLine()) != null) {

                count++;
                row = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                record = new Record();
                record.setSsoid(row[0]);
                record.setTs(Instant.ofEpochSecond(Long.parseLong(row[1])));
                record.setGrp(row[2]);
                record.setType(row[3]);
                record.setSubtype(row[4]);
                record.setUrl(row[5]);
                record.setOrgid(row[6]);
                record.setFormid(row[7]);
                record.setCode(row[8]);
                record.setLtpa(row[9]);
                record.setSudirresponse(row[10]);
                record.setYmdh(LocalDateTime.parse(row[11], DateTimeFormatter.ofPattern("yyyy-MM-dd-HH")));

                recordDAO.save(record);
                if (count % 100 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            System.out.println("В базу данных добавленно записей - " + count);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
