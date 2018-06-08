package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.daoImpl.GamerDAOImpl;
import com.system.daoImpl.AdminDAOImpl;
import com.system.entity.Gamer;
import com.system.entity.Administrator;
import com.system.util.ConnectionFactory;
public class RegisterService {

    public boolean GamerRegister(Gamer gamer){
        Connection conn=ConnectionFactory.getInstance().makeConnection();
        ResultSet rs=null;
        try {
            conn.setAutoCommit(false);
            rs=new GamerDAOImpl().get(conn, gamer); //通过邮箱注册

            // 不能重复注册
            while(rs.next()){
                return false;
            }
            new GamerDAOImpl().insert(conn, gamer);
            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return false;
            // TODO: handle exception
        }finally{
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
    public boolean AdminRegister(Administrator admin){
        Connection conn=ConnectionFactory.getInstance().makeConnection();
        ResultSet rs=null;
        try {
            conn.setAutoCommit(false);
            rs=new AdminDAOImpl().get(conn, admin); //通过邮箱注册

            // 不能重复注册
            while(rs.next()){
                return false;
            }
            new AdminDAOImpl().insert(conn, admin);
            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return false;
            // TODO: handle exception
        }finally{
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
