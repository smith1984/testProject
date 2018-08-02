package ru.smith.dao;

import ru.smith.entity.Record;

import java.util.List;

public interface RecordDAO {

    List<Record> listAllRecords();

    void saveOrUpdate(Record record);

    void delete(Record record);

}
