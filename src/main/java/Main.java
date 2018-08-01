
import ru.smith.dao.RecordDAOImpl;
import ru.smith.entity.Record;
import ru.smith.util.LoadDBFromCSV;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {

    public static void main(String[] args) {
        /*Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select version()";
        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(result);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
        */

        LoadDBFromCSV.load("./src/main/resources/test_case.csv");
    }
}
