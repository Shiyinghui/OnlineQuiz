package com.system.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.entity.QLibrary;
import com.system.entity.Test;
import com.system.entity.Gamer;

public interface TestDAO {
    ResultSet insert(Connection conn, Gamer gamer, QLibrary ql, Test test) throws SQLException;

    void update(Connection conn, Test test) throws SQLException;

    void delete(Connection conn, Test test) throws SQLException;

    ResultSet get(Connection conn, Test test) throws SQLException;
}
