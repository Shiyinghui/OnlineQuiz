package com.system.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Vector;

import com.system.entity.Question;
import com.system.util.XlsUtil;
import jxl.Sheet;
import jxl.Workbook;

public class XlsService {

    public Vector<Question> resolveXls(File file) {
        Map<String, Integer> map = new XlsUtil().checkXls(file);
        Vector<Question> questionVector = new Vector<Question>();
        Workbook xlsBook = null;
        InputStream ins = null;
        if (map != null) {
            try {
                ins = new FileInputStream(file);
                xlsBook = Workbook.getWorkbook(ins);
                Sheet workSheet = xlsBook.getSheet(0);
                int rows = workSheet.getRows();
                //System.out.println(rows);
                if (rows > 1) {
                    System.out.println("准备开始解析");

                    for (int i = 1; i <rows; i++) {
                        Question question = new Question();
                        String quizContent = workSheet.getCell(map.get("contentCol"), i).getContents();
                        String quizChoiceA = workSheet.getCell(map.get("ACol"), i).getContents();
                        String quizChoiceB = workSheet.getCell(map.get("BCol"), i).getContents();
                        String quizChoiceC = workSheet.getCell(map.get("CCol"), i).getContents();
                        String quizChoiceD = workSheet.getCell(map.get("DCol"), i).getContents();
                        String quizAnswer = workSheet.getCell(map.get("answerCol"), i).getContents();
                        String quizScore = workSheet.getCell(map.get("scoreCol"), i).getContents();
                        //System.out.print("判断是不是空的");

                        if (question.equals("") || quizChoiceA.equals("") || quizChoiceB.equals("")
                                || quizChoiceC.equals("") || quizChoiceD.equals("") || quizAnswer.equals("") || quizScore.equals("")) {
                            //System.out.println("空的内容");
                            continue;
                        }
                        int trueScore = Integer.parseInt(quizScore);
                        int trueAnswer = 0;
                        switch (quizAnswer.toUpperCase()) {
                            case "A":
                                trueAnswer = 1;
                                break;
                            case "B":
                                trueAnswer = 2;
                                break;
                            case "C":
                                trueAnswer = 3;
                                break;
                            case "D":
                                trueAnswer = 4;
                                break;
                            default:
                                return null;
                        }
                        question.setTitle(quizContent);
                        question.setChoiceA(quizChoiceA);
                        question.setChoiceB(quizChoiceB);
                        question.setChoiceC(quizChoiceC);
                        question.setChoiceD(quizChoiceD);
                        question.setCorrectAnswer(trueAnswer);
                        question.setScore(trueScore);
                        questionVector.add(question);
                        System.out.println("完成第"+i);
                    }
                    return questionVector;
                } else {
                    return null;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }

}
