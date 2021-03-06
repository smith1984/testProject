package ru.smith.dao;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.smith.entity.Record;

import java.util.List;

@Data
public class RecordDAOImpl implements RecordDAO {

    @NonNull
    private Session session;

    @Override
    public List<Record> listAllRecords() {
        Query query = session.createQuery("SELECT r FROM Record r");
        return query.list();
    }

    @Override
    public void update(Record record) {
        session.update(record);
    }

    @Override
    public void save(Record record) {
        session.save(record);
    }

    @Override
    public void delete(Record record) {
        session.delete(record);
    }

}
