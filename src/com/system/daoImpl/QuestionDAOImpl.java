package com.system.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.dao.QuestionDAO;
import com.system.entity.QLibrary;
import com.system.entity.Question;

public class QuestionDAOImpl implements QuestionDAO{

    @Override   // 新增一个问题
    public void insert(Connection conn, Question question, QLibrary ql) throws SQLException {
        // TODO Auto-generated method stub
        String insertSQL = "INSERT INTO gamedb.tbl_question VALUES(NULL,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insertSQL);
        ps.setString(1, question.getTitle());
        ps.setString(2, question.getChoiceA());
        ps.setString(3, question.getChoiceB());
        ps.setString(4, question.getChoiceC());
        ps.setString(5, question.getChoiceD());
        ps.setInt(6, question.getCorrectAnswer());
        ps.setInt(7, question.getScore());
        ps.setLong(8,ql.getId());
        System.out.println(ps.toString());
        ps.execute();

    }

    @Override  // 通过问题ID修改问题信息
    public void update(Connection conn, Question question) throws SQLException {
        // TODO Auto-generated method stub
        String updateSQL = "UPDATE gamedb.tbl_question SET questionContent=?,answer1=?,answer2=?,answer3=?,answer4=?,trueAnswer=?,score=? WHERE questionID=?";
        PreparedStatement ps = conn.prepareStatement(updateSQL);
        ps.setString(1, question.getTitle());
        ps.setString(2, question.getChoiceA());
        ps.setString(3, question.getChoiceB());
        ps.setString(4, question.getChoiceC());
        ps.setString(5, question.getChoiceD());
        ps.setInt(6, question.getCorrectAnswer());
        ps.setInt(7, question.getScore());
        ps.setLong(8, question.getId());
        ps.execute();
    }

    @Override  // 通过问题ID删除问题
    public void delete(Connection conn, Question question) throws SQLException {
        // TODO Auto-generated method stub
        String deleteSQL = "DELETE  FROM gamedb.tbl_question WHERE questionID=?";
        PreparedStatement ps = conn.prepareStatement(deleteSQL);
        ps.setLong(1, question.getId());
        ps.execute();
    }

    // 删除某一个题库的所有问题
    public void deleteAll(Connection conn, QLibrary ql) throws SQLException{
        String deleteSQL = "DELETE FROM gamedb.tbl_question WHERE QLibraryID=?";
        PreparedStatement ps = conn.prepareStatement(deleteSQL);
        ps.setLong(1,ql.getId());
        ps.execute();
    }
    /*
     * 通过问题ID查询问题信息
     */
    public ResultSet get(Connection conn,Question question) throws SQLException{
        String getSQL="SELECT * FROM gamedb.tbl_question WHERE questionID=?";
        PreparedStatement ps=conn.prepareStatement(getSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ps.setLong(1, question.getId());
        return ps.executeQuery();

    }
    
    public ResultSet get(Connection conn, QLibrary ql) throws SQLException{
        String getSQL = "SELECT gamedb.tbl_question.questionID,gamedb.tbl_question.questionContent,gamedb.tbl_question.answer1,gamedb.tbl_question.answer2,gamedb.tbl_question.answer3,gamedb.tbl_question.answer4,gamedb.tbl_question.trueAnswer,gamedb.tbl_question.score FROM gamedb.tbl_QLibrary,gamedb.tbl_question WHERE gamedb.tbl_QLibrary.id=? AND gamedb.tbl_question.QLibraryID=gamedb.tbl_QLibrary.id";
        PreparedStatement ps = conn.prepareStatement(getSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ps.setLong(1,ql.getId());
        return ps.executeQuery();
    }

}

