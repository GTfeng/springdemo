package multithreadinsert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InsertThread extends Thread {
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




    public void run() {
        try {
            conn = DriverManager.getConnection(url, name, pwd);
            System.out.println("连接数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }


        Long begin = new Date().getTime();
        // sql前缀




//        String prefix = "INSERT INTO test_teacher (t_name,t_password,sex,description,pic_url,school_name,regist_date,remark) VALUES ";
        String prefix = "INSERT INTO `test`.`stu` (`tname`, `sex`, `tel`, `addr`, `fruit`, `hobbit`, `grade`, `email`, `tname2`, `sex2`, `tel2`, `addr2`, `fruit2`, `hobbit2`, `grade2`, `email2`) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(" ");//准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= insertTimes; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= perInsert; j++) {
                    // 构建SQL后缀
                    suffix.append("('" + "qwer" + "','qwer'" + ",'123'" + ",'1234'" + ",'adsf'" + ",'adsf'" + ",'" + "sdf" + "','asdf" + "','qwer" + "','qwer" + "','qwer" + "','qwer" + "','qwer" + "','qwer" + "','qwer" + "','qwer'" + "),");
//                    System.out.println(suffix);
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println(perInsert*insertTimes+"万条数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
        int useTime = (int) ((end-begin)/1000);
        time.add(useTime);


        try {
            conn.close();
            System.out.println("数据库已关闭");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库关闭失败");
        }

//        execute_times += 1;
//        if(execute_times>18){
//            System.out.println("十个线程都执行完成");
//        }
//        index=execute_times.incrementAndGet();
//        if(execute_times.get()>18){
//            System.out.println("全部sql操作执行完毕");
//        }
        index = execute_times.getAndAdd(1);
        if(index>18){
            System.out.println("全部sql操作执行完毕");
            writeExcel();
        }

    }

    public static void writeExcel(){
        int longest = 0;
        for(int i=0;i<time.size();i++){
            if(time.get(i)>longest){
                longest = time.get(i);
            }
        }
        System.out.println(longest);
        WriteExcel.WriteExcel(longest,1);
    }

}
