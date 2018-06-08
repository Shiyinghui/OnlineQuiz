package com.system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.entity.QLibrary;
import com.system.entity.Administrator;

public interface QLibraryDAO {

    ResultSet insert(Connection conn, QLibrary ql, Administrator admin) throws SQLException;

    void update(Connection conn, QLibrary ql) throws SQLException;

    void delete(Connection conn, QLibrary ql) throws SQLException;

    ResultSet get(Connection conn, QLibrary ql) throws SQLException;
    
    ResultSet get(Connection conn,Administrator admin) throws SQLException;
}
