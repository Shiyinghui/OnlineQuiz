package com.system.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.system.daoImpl.GamerDAOImpl;
import com.system.entity.Gamer;
import com.system.util.ConnectionFactory;


public class deleteGService {

    public boolean delete(Gamer gamer) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new GamerDAOImpl().delete(conn, gamer);
            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                // TODO: handle exception
            }
            return false;
            // TODO: handle exception
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
