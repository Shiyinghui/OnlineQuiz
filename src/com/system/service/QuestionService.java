package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import com.system.daoImpl.QuestionDAOImpl;
import com.system.entity.QLibrary;
import com.system.entity.Question;
import com.system.util.ConnectionFactory;

public class QuestionService {

    // 将问题添加到题库中, 批量添加问题，比如从excel中导入
    public boolean addQuestionToQL(Vector<Question>questions,QLibrary ql){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        QuestionDAOImpl impl=new QuestionDAOImpl();
        try {
            conn.setAutoCommit(false);
            Iterator<Question> iter=questions.iterator();
            while(iter.hasNext()){
                Question question=iter.next();
                impl.insert(conn, question,ql);
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


    /*
     * 添加题目的时候使用，需要测试对象（包含ID)，需要题目对象(包含详细信息）
     */
    public boolean addQuestionToQL(Question question, QLibrary ql) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new QuestionDAOImpl().insert(conn, question,ql);
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
     * 获取一个题库的所有题目，传入QLibrary对象，其中至少包含ID,如果发生异常，返回null,否则返回Vector
     */
    public Vector<Question> getAllQuestionOfQL(QLibrary ql) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        Vector<Question> questionVector = new Vector<>();
        ResultSet questionSet = null;

        try {
            conn.setAutoCommit(false);
            questionSet = new QuestionDAOImpl().get(conn,ql);
            while (questionSet.next()) {
                Question question = new Question();
                question.setTitle(questionSet.getString("questionContent"));
                question.setId(questionSet.getLong("questionID"));
                question.setChoiceA(questionSet.getString("answer1"));
                question.setChoiceB(questionSet.getString("answer2"));
                question.setChoiceC(questionSet.getString("answer3"));
                question.setChoiceD(questionSet.getString("answer4"));
                question.setCorrectAnswer(questionSet.getInt("trueAnswer"));
                question.setScore(questionSet.getInt("score"));
                questionVector.add(question);
            }
            conn.commit();
            questionSet.close();
            return questionVector;
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
     * 修改试题时候使用，需要传入试题对象，其中至少包含试题的ID，通过ID定位试题并根据其对象的其他属性修改试题
     */
    public boolean updateQuestion(Question question) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new QuestionDAOImpl().update(conn, question);
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
     * 删除一道题目，传入题目对象，其中至少包含ID
     */
    public boolean deleteAQuestion(Question question) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new QuestionDAOImpl().delete(conn, question);
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
     * 删除某个题库的所有题目，传入测试对象，其中至少包含ID属性
     */
    public boolean deleteAllQuestionOfQL(QLibrary ql) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new QuestionDAOImpl().deleteAll(conn, ql);
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
