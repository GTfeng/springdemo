package multithreadinsert;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;

public class WriteExcel {
    public static String fileName = "ThreadInsertMysql";
    public static void WriteExcel(int time,int dataNum){
        try {
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(fileName));
//            WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);
            WritableSheet sheet = writableWorkbook.getSheet("Sheet1");
            int rows = sheet.getRows();

            Label label = new Label(0, rows+1, "string");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("添加统计数据到Excel表");
    }

}
