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
                if (!row[1].isEmpty()) record.setTs(Instant.ofEpochSecond(Long.parseLong(row[1])));
                if (!row[2].isEmpty()) record.setGrp(row[2]);
                if (!row[3].isEmpty()) record.setType(row[3]);
                if (!row[4].isEmpty()) record.setSubtype(row[4]);
                if (!row[5].isEmpty()) record.setUrl(row[5]);
                if (!row[6].isEmpty()) record.setOrgid(row[6]);
                if (!row[7].isEmpty()) record.setFormid(row[7]);
                if (!row[8].isEmpty()) record.setCode(row[8]);
                if (!row[9].isEmpty()) record.setLtpa(row[9]);
                if (!row[10].isEmpty()) record.setSudirresponse(row[10]);
                if (!row[11].isEmpty()) record.setYmdh(LocalDateTime.parse(row[11],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd-HH")));

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
