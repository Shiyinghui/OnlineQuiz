package com.system.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.entity.QLibrary;
import com.system.entity.Question;


public interface QuestionDAO {


    void insert(Connection conn, Question question, QLibrary ql) throws SQLException;

    void update(Connection conn, Question question) throws SQLException;

    void delete(Connection conn, Question question) throws SQLException;

    ResultSet get(Connection conn,QLibrary ql) throws SQLException;


}
