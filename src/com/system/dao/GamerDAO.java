package com.system.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.entity.Gamer;

public interface GamerDAO {
    void insert(Connection conn, Gamer gamer) throws SQLException;

    void update(Connection conn, Gamer gamer) throws SQLException;

    void delete(Connection conn, Gamer gamer) throws SQLException;

    ResultSet get(Connection conn, Gamer gamer) throws SQLException;
}
