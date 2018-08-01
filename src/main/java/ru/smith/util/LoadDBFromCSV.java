package ru.smith.util;

import ru.smith.dao.RecordDAOImpl;
import ru.smith.entity.Record;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoadDBFromCSV {

    public static void load(String path) {
        Record record;
        RecordDAOImpl recordDAOImpl = new RecordDAOImpl();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(path));

            line = br.readLine();
            line = br.readLine();


           // while ((line = br.readLine()) != null) {

                String[] row = line.split(cvsSplitBy);

                if (row.length == 12) {

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


                    recordDAOImpl.saveOrUpdate(record);
                    recordDAOImpl.saveOrUpdate(record);
                    recordDAOImpl.saveOrUpdate(record);
                }
            //}

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
