package com.system.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.system.dao.AdministratorDAO;
import com.system.entity.Administrator;

public class AdminDAOImpl implements AdministratorDAO{


    @Override  //新注册一个管理员
    public void insert(Connection conn, Administrator admin) throws SQLException {
        // TODO Auto-generated method stub
        String insertSql = "INSERT INTO gamedb.tbl_administrator VALUES(NULL,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insertSql);
        ps.setString(1, admin.getEmail());
        ps.setString(2, admin.getPassword());
        ps.setString(3, admin.getName());
        ps.setString(4, admin.getGender());
        ps.setString(5, admin.getPhone());
        ps.execute();
        }


    @Override  // 管理员修改信息
    public void update(Connection conn, Administrator admin) throws SQLException {
        // TODO Auto-generated method stub

        String updateSql = "UPDATE gamedb.tbl_administrator SET adminPassword=?,adminName=?,adminGender=?,adminPhone=? WHERE adminEmail=?";
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setString(1, admin.getPassword());
        ps.setString(2, admin.getName());
        ps.setString(3, admin.getGender());
        ps.setString(4, admin.getPhone());
        ps.setString(5, admin.getEmail());
        ps.execute();
    }

    @Override  // 删除管理员
    public void delete(Connection conn, Administrator admin) throws SQLException {
        // TODO Auto-generated method stub
        String deleteSql = "DELETE FROM gamedb.tbl_administrator WHERE adminEmail=?";
        PreparedStatement ps = conn.prepareStatement(deleteSql);
        ps.setString(1, admin.getEmail());
        ps.execute();

    }

    @Override   // 由管理员邮箱得到该管理员
    public ResultSet get(Connection conn, Administrator admin) throws SQLException {
        // TODO Auto-generated method stub
        String getSql = "SELECT * FROM gamedb.tbl_administrator WHERE adminEmail=?";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setString(1, admin.getEmail());

        return ps.executeQuery();
    }

                // 查看系统所有管理员
    public ResultSet getAll(Connection conn) throws SQLException {
        String getAllSql = "SELECT * FROM gamedb.tbl_administrator";
        Statement s = conn.createStatement();
        return s.executeQuery(getAllSql);

    }
               //由管理员ID得到该管理员
    public ResultSet getById(Connection conn, Administrator admin) throws SQLException {
        // TODO Auto-generated method stub
        String getSql = "SELECT * FROM gamedb.tbl_administrator WHERE adminID=?";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, admin.getId());

        return ps.executeQuery();
    }

}
