package com.system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.entity.Announce;


public interface AnnounceDA0 {

    void insert(Connection conn, Announce announce) throws SQLException;
    // 插入一条公告

    void update(Connection conn, Announce announce) throws SQLException; // 更新公告

    void delete(Connection conn, Announce announce) throws SQLException; // 删除公告

    ResultSet get(Connection conn, Announce announce) throws SQLException;
}
