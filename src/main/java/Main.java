import ru.smith.util.HibernateUtil;
import ru.smith.util.LoadDBFromCSV;

public class Main {

    public static void main(String[] args) {

        LoadDBFromCSV.load("./src/main/resources/test_case.csv");
        HibernateUtil.shutdown();
    }
}
