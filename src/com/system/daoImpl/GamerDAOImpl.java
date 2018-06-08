package com.system.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.entity.Gamer;
import com.system.dao.GamerDAO;

public class GamerDAOImpl implements GamerDAO{
    @Override // 新添加一个玩家
    public void insert(Connection conn, Gamer gamer) throws SQLException {
        // TODO Auto-generated method stub
        String saveSql = "INSERT INTO gamedb.tbl_gamer VALUES (null,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(saveSql);
        ps.setString(1, gamer.getEmail());
        ps.setString(2, gamer.getPassword());
        ps.setString(3, gamer.getName());
        ps.setString(4, gamer.getGender());
        ps.execute();
    }

    @Override  // 修改玩家信息
    public void update(Connection conn, Gamer gamer) throws SQLException {
        // TODO Auto-generated method stub
        String updateSql = "UPDATE gamedb.tbl_gamer SET gamerPassword=?,gamerName=?,gamerGender=? WHERE gamerEmail=?";
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setString(1, gamer.getPassword());
        ps.setString(2, gamer.getName());
        ps.setString(3, gamer.getGender());
        ps.setString(4, gamer.getEmail());
        ps.execute();
    }

    @Override   // 通过玩家邮箱删除玩家
    public void delete(Connection conn, Gamer gamer) throws SQLException {
        // TODO Auto-generated method stub
        String deleteSql = "DELETE FROM gamedb.tbl_gamer WHERE gamerEmail=?";
        PreparedStatement ps = conn.prepareStatement(deleteSql);
        ps.setString(1, gamer.getEmail());
        ps.execute();

    }

    @Override  // 由玩家邮箱查询玩家详细信息
    public ResultSet get(Connection conn, Gamer gamer) throws SQLException {
        // TODO Auto-generated method stub
        String getSql = "SELECT * FROM gamedb.tbl_gamer WHERE gamerEmail=?";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setString(1, gamer.getEmail());
        return ps.executeQuery();
    }

     // 由玩家ID查询玩家详细信息
    public ResultSet getByID(Connection conn, Gamer gamer) throws SQLException {
        // TODO Auto-generated method stub
        String getSql = "SELECT * FROM gamedb.tbl_gamer WHERE gamerID=?";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, gamer.getId());
        return ps.executeQuery();
    }

    // 查看所有玩家
    public ResultSet getAll(Connection conn)throws SQLException{
        String sql="SELECT * FROM gamedb.tbl_gamer";
        PreparedStatement ps=conn.prepareStatement(sql);
        return ps.executeQuery();
    }
}
