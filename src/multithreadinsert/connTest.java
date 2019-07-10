package multithreadinsert;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connTest {
    private static final String url = "jdbc:mysql://localhost/test";
    private static final String name = "root";
    private static final String pwd = "123456";
    Connection conn = null;

//    public static void connection(){
//        try {
//            Connection conn = DriverManager.getConnection(url,name,pwd);
//            System.out.println("连接数据库成功");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("连接数据库失败");
//        }
//
//    }


    public static Connection connection(){
        try {
            Connection conn = DriverManager.getConnection(url,name,pwd);
            System.out.println("连接数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return connection();
    }

    public static void main(String[] args){
        connection();
    }

}
