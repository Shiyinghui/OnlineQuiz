package com.system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.entity.Administrator;

public interface AdministratorDAO {

    void insert(Connection conn, Administrator admin) throws SQLException;

    void update(Connection conn, Administrator admin) throws SQLException;

    void delete(Connection conn, Administrator admin) throws SQLException;

    ResultSet get(Connection conn, Administrator admin) throws SQLException;
}
