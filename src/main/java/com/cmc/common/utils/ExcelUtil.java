package com.cmc.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.cmc.user.facade.entity.User;

/**
 * Microsoft Office Excel Utilities
 * 
 * <p>有待全部使用POI替换JXL
 * 
 * @author Thomas Lee
 * @since 2016年11月20日 下午8:09:35
 */
public class ExcelUtil {

    /**
     * 导出（POI）
     * 
     * @param data   传入的字符串二维数组
     * @return
     */
    public static ByteArrayOutputStream create(String[][] data) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLUE.index);
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setIndention((short) 10);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setBorderLeft((short) 10);

        for (int x = 0; x < data.length; x++) {
            HSSFRow row = sheet.createRow(x);
            for (int y = 0; y < data[x].length; y++) {
                row.createCell(y).setCellValue(data[x][y]);
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            workbook.write(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream;
    }

    /**
     * 导入
     * 
     * @param file
     * @return
     */
    public static List<User> read(File file) {
        List<User> users = new ArrayList<User>();
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(file);
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            return null;
        }
        Sheet sheet = book.getSheet(0);
        for (int x = 0; x < sheet.getRows(); x++) {
            User user = new User();
            user.setName(sheet.getCell(0, x).getContents());
            user.setSex(sheet.getCell(1, x).getContents());
            users.add(user);
        }
        book.close();
        return users;
    }

}