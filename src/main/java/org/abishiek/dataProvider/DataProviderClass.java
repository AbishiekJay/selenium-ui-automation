package org.abishiek.dataProvider;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataProviderClass {
    public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/resources/data/TestData.xlsx";
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;


    public static Object[][] dataSupplier(String sheetName){
        try{
            InputStream file = new FileInputStream(TESTDATA_SHEET_PATH);
            ExcelWBook = new XSSFWorkbook(file);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            ExcelWBook.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        int lastRowNum = ExcelWSheet.getLastRowNum();
        int lastCellNum = ExcelWSheet.getRow(0).getLastCellNum();
        Object[][] obj =new Object[lastRowNum][1];
        for(int i = 0; i < lastRowNum; i++){
            Map<String,String> dataMap = new HashMap<>();
            for(int j = 0; j < lastCellNum; j++){
                dataMap.put(ExcelWSheet.getRow(0).getCell(j).toString(),
                        ExcelWSheet.getRow(i+1).getCell(j).toString());
            }
            obj[i][0] = dataMap;
        }
        return obj;
    }
}
