package com.system.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.entity.Answer;
import com.system.entity.Question;
import com.system.entity.Test;
import com.system.dao.AnswerDAO;

public class AnswerDAOImpl implements AnswerDAO{
    private final String format = "%Y-%m-%d %H:%i:%s";

    @Override // 插入问题的答案
    public void insert(Connection conn, Test test, Question question, Answer answer) throws SQLException {
        // TODO Auto-generated method stub

        String insertSql = "INSERT INTO gamedb.tbl_answer VALUES(NULL,?,?,?,?,STR_TO_DATE(?,'"
                + format + "'),?)";
        PreparedStatement ps = conn.prepareStatement(insertSql);

        ps.setLong(1, test.getTestID());
        ps.setLong(2, question.getId());
        ps.setInt(3, answer.getAnswerContent());
        ps.setInt(4, answer.getAnswerScore());
        ps.setString(5, answer.getAnswerTime());
        ps.setInt(6, (answer.isChecked() == true ? 1 : 0));
        ps.execute();
    }

    @Override  // 更新玩家的答案，分数等
    public void update(Connection conn, Answer answer) throws SQLException {
        // TODO Auto-generated method stub
        String updateSql = "UPDATE gamedb.tbl_answer SET gamerAnswer=?,answerScore=? ,isChecked=? WHERE answerID=?";
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setInt(1, answer.getAnswerContent());
        ps.setInt(2, answer.getAnswerScore());
        ps.setInt(3, answer.isChecked() == true ? 1 : 0);
        ps.setLong(4, answer.getAnswerID());
        ps.execute();

    }

    @Override  // 删除某一个答案
    public void delete(Connection conn, Answer answer) throws SQLException {
        // TODO Auto-generated method stub
        String deleteSql = "DELETE * FROM gamedb.tbl_answer WHERE answerID=?";
        PreparedStatement ps = conn.prepareStatement(deleteSql);
        ps.setLong(1, answer.getAnswerID());
        ps.execute();

    }

    @Override
    /*
     * 从 tbl_test表和 tbl_answer表 中通过测试的ID来查找answer
     *
     */
    public ResultSet get(Connection conn, Test test) throws SQLException {
        // TODO Auto-generated method stub
        String getSql = "SELECT gamedb.tbl_answer.answerID,gamedb.tbl_answer.testID,gamedb.tbl_answer.questionID,gamedb.tbl_answer.gamerAnswer,gamedb.tbl_answer.answerScore,gamedb.tbl_answer.answerTime,gamedb.tbl_answer.isChecked FROM gamedb.tbl_answer,gamedb.tbl_test WHERE gamedb.tbl_test.testID=? AND gamedb.tbl_test.testID=gamedb.tbl_answer.testID";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, test.getTestID());
        return ps.executeQuery();
    }

    /*
     * 通过question的ID来查找answer
     */
    public ResultSet get(Connection conn, Question question) throws SQLException {

        String getSql = "SELECT gamedb.tbl_answer.answerID,gamedb.tbl_answer.testID,gamedb.tbl_answer.questionID,gamedb.tbl_answer.gamerAnswer,gamedb.tbl_answer.answerScore,gamedb.tbl_answer.answerTime,gamedb.tbl_answer.isChecked FROM gamedb.tbl_answer,gamedb.tbl_question WHERE gamedb.tbl_question.questionID=? AND gamedb.tbl_question.questionID=gamedb.tbl_answer.questionID";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, question.getId());
        return ps.executeQuery();
    }

   //由答案ID查看答案的所有信息
    public ResultSet get(Connection conn, Answer answer) throws SQLException {
        String getSql = "SELECT * FROM gamedb.tbl_answer WHERE answerID=?";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, answer.getAnswerID());
        return ps.executeQuery();
    }


}
