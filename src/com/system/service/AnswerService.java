package com.system.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;

import com.system.daoImpl.AnswerDAOImpl;
import com.system.daoImpl.QuestionDAOImpl;
import com.system.entity.Answer;
import com.system.entity.Question;
import com.system.entity.Test;
import com.system.util.ConnectionFactory;

public class AnswerService {

    // 从数据库中取问题, 可用于批改一次quiz
    public Map<Question, Answer> getAnswer(Test test) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet questionSet = null;
        ResultSet answerSet = null;
        Map<Question, Answer> map = new HashMap<Question, Answer>();
        try {
            conn.setAutoCommit(false);
            answerSet = new AnswerDAOImpl().get(conn, test);
            // 得到一个测试的所有答案

            while (answerSet.next()) {
                long quizID = answerSet.getLong("questionID");
                //quizID 当前答案所对应的问题ID

                Question truequestion = new Question();
                Question tempQuestion = new Question();
                tempQuestion.setId(quizID);

                Answer trueAnswer = new Answer();
                trueAnswer.setAnswerID(answerSet.getLong("answerID"));
                trueAnswer.setAnswerContent(answerSet.getInt("gamerAnswer"));
                trueAnswer.setAnswerScore(answerSet.getInt("answerScore"));
                trueAnswer.setChecked(false);  // 首先初始化为false;

                int tempState = answerSet.getInt("isChecked");
                if (tempState == 1) {
                    trueAnswer.setChecked(true);
                }
                trueAnswer.setAnswerTime(answerSet.getTimestamp("answerTime").toString());

                questionSet = new QuestionDAOImpl().get(conn, tempQuestion);
                // 得到当前答案所对应的问题

                while (questionSet.next()) {
                    truequestion.setId(questionSet.getLong("questionID"));
                    truequestion.setTitle(questionSet.getString("questionContent"));
                    truequestion.setChoiceA(questionSet.getString("answer1"));
                    truequestion.setChoiceB(questionSet.getString("answer2"));
                    truequestion.setChoiceC(questionSet.getString("answer3"));
                    truequestion.setChoiceD(questionSet.getString("answer4"));
                    truequestion.setScore(questionSet.getInt("score"));
                    truequestion.setCorrectAnswer(questionSet.getInt("trueAnswer"));
                }
                map.put(truequestion, trueAnswer); // 存放问题和答案，一一对应
            }
            conn.commit();
            return map;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        } finally {
            try {
                answerSet.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                questionSet.close();
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

    /*
     * 在玩家回答了一道题之后使用。 传入test对象，其中包含ID属性 传入question对象，其中包含ID属性
     * 传入Answer对象，其中包含玩家的回答内容，回答的时间信息等 返回操作是否成功
     *
     */ // 将玩家所做的测试、测试中的问题、答案存在数据库中
    public boolean addAnswer(Test test, Question question, Answer answer) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new AnswerDAOImpl().insert(conn, test, question, answer);
            conn.commit();

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
     * 需要添加许多answer信息的时候使用，注意事项如上 如果发生异常，返回false
     */
    public boolean addAnswer(Test test, Map<Question, Answer> answerMap) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        AnswerDAOImpl impl = new AnswerDAOImpl();


        try {
            conn.setAutoCommit(false);
            for (Map.Entry<Question, Answer> entry : answerMap.entrySet()) {
                impl.insert(conn, test, entry.getKey(), entry.getValue());
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
