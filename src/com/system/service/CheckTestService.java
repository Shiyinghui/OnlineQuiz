package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.daoImpl.AnswerDAOImpl;
import com.system.daoImpl.QuestionDAOImpl;
import com.system.daoImpl.TestDAOImpl;
import com.system.entity.Answer;
import com.system.entity.Question;
import com.system.entity.Test;
import com.system.util.ConnectionFactory;

/*
  SYH checkTest()方法可能存在问题
 */
public class CheckTestService {

   /* public int checkTest(Test test) {

        Connection conn = ConnectionFactory.getInstance().makeConnection();
        AnswerDAOImpl answerImpl = new AnswerDAOImpl();
        QuestionDAOImpl questionImpl = new QuestionDAOImpl();
        TestDAOImpl testImpl = new TestDAOImpl();
        ResultSet answerSet = null;

        try {

            conn.setAutoCommit(false);
            answerSet = answerImpl.get(conn, test); // 获得一个测试的所有答案
            int testScore = 0;
            while (answerSet.next()) {
               // int tempState = answerSet.getInt("isChecked");
                long answerId = answerSet.getLong("answerID");

                // 答案对应的问题ID
                long quizID = answerSet.getLong("questionID");

                //玩家答案
                int gamerAnswer = answerSet.getInt("gamerAnswer");

                Question tempQuiz = new Question();
                tempQuiz.setId(quizID);

                // 得到测试
                ResultSet quizSet = questionImpl.get(conn, tempQuiz);

                //first() Moves the cursor to the first row in this ResultSet object.
                    if(!quizSet.first()) {
                        Answer thisAnswer = new Answer();
                        thisAnswer.setAnswerID(answerId);
                        thisAnswer.setAnswerScore(0);
                        thisAnswer.setChecked(true);
                        thisAnswer.setAnswerContent(0);
                        answerImpl.update(conn,thisAnswer);
                        quizSet.beforeFirst();
                    }
                        //beforeFirst() Moves the cursor to the front of this ResultSet object,
                        // just before the first row.
                    quizSet.beforeFirst();
                    while (quizSet.next()) {
                        int trueAnswer = quizSet.getInt("trueAnswer");
                        int score = quizSet.getInt("score");

                        if (trueAnswer == gamerAnswer) {
                            testScore += score;
                            Answer tempAnswer = new Answer();
                            tempAnswer.setAnswerID(answerId);
                            tempAnswer.setAnswerScore(score);
                            tempAnswer.setChecked(true);
                            tempAnswer.setAnswerContent(gamerAnswer);
                            answerImpl.update(conn, tempAnswer);
                        } else {
                            Answer tempAnswer = new Answer();
                            tempAnswer.setAnswerID(answerId);
                            tempAnswer.setAnswerScore(0);
                            tempAnswer.setChecked(true);
                            tempAnswer.setAnswerContent(gamerAnswer);
                            answerImpl.update(conn, tempAnswer);
                        }
                    }
                }

            test.setTestScore(testScore);
            testImpl.update(conn, test);
            conn.commit();
            return testScore;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return -1;
        } finally {
            try {
                answerSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }*/


       public int checkTest(Test test) {

        Connection conn = ConnectionFactory.getInstance().makeConnection();
        AnswerDAOImpl answerImpl = new AnswerDAOImpl();
        QuestionDAOImpl questionImpl = new QuestionDAOImpl();
        TestDAOImpl testImpl = new TestDAOImpl();
        ResultSet answerSet = null;

        try {

            conn.setAutoCommit(false);
            answerSet = answerImpl.get(conn, test); // 获得一个测试的所有答案
            int testScore = 0;
            while (answerSet.next()) {
                long answerId = answerSet.getLong("answerID");

                // 答案对应的问题ID
                long questionID = answerSet.getLong("questionID");

                //玩家答案
                int gamerAnswer = answerSet.getInt("gamerAnswer");

                Question tempQ = new Question();
                tempQ.setId(questionID);

                // 得到问题
                ResultSet qSet = questionImpl.get(conn, tempQ);
                while (qSet.next()) {
                        int trueAnswer = qSet.getInt("trueAnswer");
                        int score = qSet.getInt("score");

                        if (trueAnswer == gamerAnswer) {
                            testScore += score;
                            Answer tempAnswer = new Answer();
                            tempAnswer.setAnswerID(answerId);
                            tempAnswer.setAnswerScore(score);
                            tempAnswer.setChecked(true);
                            tempAnswer.setAnswerContent(gamerAnswer);
                            answerImpl.update(conn, tempAnswer);
                        } else {
                            Answer tempAnswer = new Answer();
                            tempAnswer.setAnswerID(answerId);
                            tempAnswer.setAnswerScore(0);
                            tempAnswer.setChecked(true);
                            tempAnswer.setAnswerContent(gamerAnswer);
                            answerImpl.update(conn, tempAnswer);
                        }
                    }
                }

            test.setTestScore(testScore);
            testImpl.update(conn, test);
            conn.commit();
            return testScore;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return -1;
        } finally {
            try {
                answerSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}


