package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.daoImpl.GamerDAOImpl;
import com.system.daoImpl.AdminDAOImpl;
import com.system.entity.Gamer;
import com.system.entity.Administrator;
import com.system.util.ConnectionFactory;

public class LoginService {
    public boolean gamerLogin(Gamer gamer) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            rs = new GamerDAOImpl().get(conn, gamer);
            while (rs.next()) {
                gamer.setName(rs.getString("gamerName"));
                gamer.setGender(String.valueOf(rs.getInt("gamerGender")));
                conn.commit();
                return rs.getString("gamerPassword").equals(gamer.getPassword());
            }
            conn.commit();
            return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public boolean adminLogin(Administrator admin) { // 验证老师登陆服务,同时添加教师的信息(姓名和性别)
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            rs = new AdminDAOImpl().get(conn, admin);
            while (rs.next()) {

                admin.setName(rs.getString("adminName"));
                admin.setGender(String.valueOf(rs.getInt("adminGender")));
                conn.commit();
                return rs.getString("adminPassword").equals(admin.getPassword());
            }
            conn.commit();
            return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

