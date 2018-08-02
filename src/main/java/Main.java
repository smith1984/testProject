import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.smith.entity.Record;
import ru.smith.util.HibernateUtil;
import ru.smith.util.LoadDBFromCSV;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        //LoadDBFromCSV.load("./src/main/resources/test_case.csv");
        //HibernateUtil.shutdown();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query =session.createQuery("SELECT r FROM Record r WHERE r.ts > :hour");
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();

    }
}
