
import ru.smith.dao.RecordDAOImpl;
import ru.smith.entity.Record;

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

       /* Record record = new Record();
        record.setSsoid("650ae77a-ffce-475d-a930-c7e345e0658c");
        record.setTs(1499763594);
        record.setGrp("guis_-47");
        record.setType("formcontroller");
        record.setSubtype("send");
        record.setUrl("https://www.mos.ru/pgu/ru/application/guis/-47/#step_1");
        record.setOrgid("guis");
        record.setFormid("-47");
        record.setCode("MPGU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
        LocalDateTime dateTime = LocalDateTime.parse("2017-07-11-09", formatter);
        record.setYmdh(dateTime);
        new RecordDAOImpl().saveOrUpdate(record);
*/


    }
}
