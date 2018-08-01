package ru.smith.dao;

import ru.smith.entity.Record;

import java.util.List;

public interface RecordDAO {

    public List<Record> listAllRecords();
    public void save(Record record);

}
