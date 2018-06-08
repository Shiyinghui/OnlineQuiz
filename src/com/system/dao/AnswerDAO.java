package com.system.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.entity.Answer;
import com.system.entity.Question;
import com.system.entity.Test;

public interface AnswerDAO {

    void insert(Connection conn, Test test, Question question, Answer answer)
            throws SQLException;

    void update(Connection conn, Answer answer) throws SQLException;

    void delete(Connection conn, Answer answer) throws SQLException;

    ResultSet get(Connection conn, Test test) throws SQLException;
}
