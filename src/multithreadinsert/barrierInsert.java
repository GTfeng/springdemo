package multithreadinsert;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class barrierInsert {
    private static final String url = "jdbc:mysql://localhost/test";
    //    private static final String url = "jdbc:mysql://localhost/test_teacher";
    private static final String name = "root";
    private static final String pwd = "123456";
    Connection conn = null;
    static List<Integer> time = new ArrayList();

    int perInsert = 1000;
    int insertTimes = 100;
    //    public static int execute_times = 0;
    public static AtomicInteger execute_times = new AtomicInteger(0);
    int index = 0;



}
