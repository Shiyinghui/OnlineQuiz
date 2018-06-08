package com.system.util;

import java.io.*;
import jxl.*;
import java.util.*;


public class XlsUtil {

    public Map<String, Integer> checkXls(File file) {
        InputStream ins = null;
        Workbook readwb = null;
        int contentCol = -1;
        int ACol = -1;
        int BCol = -1;
        int CCol = -1;
        int DCol = -1;
        int answerCol = -1;
        int scoreCol = -1;
        try {
            ins = new FileInputStream(file);
            readwb = Workbook.getWorkbook(ins);
            Sheet readSheet = readwb.getSheet(0);
            int cols = readSheet.getColumns();
            //System.out.println(cols);
             int rows = readSheet.getRows();
            //System.out.println(rows);
            for (int i = 0; i < cols; i++) {
                Cell cell = readSheet.getCell(i, 0);
                //第一个参数是列，第二个是行

                String contents = cell.getContents();
                // System.out.println(contents);
                switch (contents.toUpperCase()) {
                    case "QUESTION":
                        contentCol = i;
                         //System.out.println("QOK");
                        break;
                    case "A":
                        ACol = i;
                        //System.out.println("AOK");
                        break;
                    case "B":
                        BCol = i;
                       // System.out.println("BOK");
                        break;
                    case "C":
                        CCol = i;
                         //System.out.println("COK");
                        break;
                    case "D":
                        DCol = i;
                         //System.out.println("DOK");
                        break;
                    case "ANSWER":
                        answerCol = i;
                        // System.out.println("ANSWEROK");
                        break;
                    case "SCORE":
                        scoreCol = i;
                     //   System.out.println("SCOREOK");
                        break;
                    default:
                        break;
                }

            }
            if (contentCol == -1 || ACol == -1 || BCol == -1 || CCol == -1 || DCol == -1 || answerCol == -1
                 || scoreCol == -1) {
                return null;
            }
            Map<String,Integer> map = new HashMap<String, Integer>();
            map.put("contentCol", contentCol);
            map.put("ACol",ACol);
            map.put("BCol", BCol);
            map.put("CCol", CCol);
            map.put("DCol", DCol);
            map.put("answerCol", answerCol);
            map.put("scoreCol", scoreCol);
           // System.out.println(contentCol);
            //System.out.println(ACol);
           // System.out.println(BCol);
           // System.out.println(CCol);
           // System.out.println(DCol);
           // System.out.println(answerCol);
            // System.out.println(scoreCol);
            return map;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}
