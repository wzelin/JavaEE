package com.xxx.javaee.poi;

import com.xxx.javaee.util.poi.reader.AbsDefExcelReader;
import com.xxx.javaee.util.poi.reader.AbsSAXExcelReader;
import com.xxx.javaee.util.poi.reader.ExcelReader;

import java.util.List;

public class TestReader {
    public static void main(String[] args) throws Exception {
        ExcelReader reader = new AbsSAXExcelReader("D:/dddd.xlsx"){
            @Override
            public void onRowRead(int sheetIndex, int curRow, List<String> rowList) {
                for (String src:rowList) {
                    System.out.print(src+"\t");
                }
                System.out.println();
            }
        };
        reader.read(0);
    }
}
