package multithreadinsert;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class WriteExcel {
    public static void WriteExcel(int time, int dataNum) {
        int rows = 0;
        File file = new File("ThreadInsertMysql.xls");
        try {
            Workbook book = Workbook.getWorkbook(file);
            Sheet sheet = book.getSheet(0);
            rows = sheet.getRows();
            System.out.println(rows);
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fs = new FileInputStream(file);
            POIFSFileSystem ps = new POIFSFileSystem(fs);
            HSSFWorkbook wb = new HSSFWorkbook(ps);
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFRow row = sheet.getRow(0);
            row = sheet.createRow(rows + 1);
            row.createCell(0).setCellValue(dataNum * 20);
            row.createCell(1).setCellValue(dataNum);
            row.createCell(2).setCellValue(time);

            FileOutputStream out = new FileOutputStream(file);

            out.flush();
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
