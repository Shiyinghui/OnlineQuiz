package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.system.daoImpl.GamerDAOImpl;
import com.system.daoImpl.AdminDAOImpl;
import com.system.entity.Gamer;
import com.system.entity.Administrator;
import com.system.util.ConnectionFactory;

public class ConsultService {


    // 获得所有玩家的集合，如果查询异常返回null
    public Vector<Gamer> getAllGamers() {
        Vector<Gamer> gamers = new Vector<Gamer>();
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet gamerSet = null;
        try {
            conn.setAutoCommit(false);
            gamerSet = new GamerDAOImpl().getAll(conn);
            while (gamerSet.next()) {
                Gamer tempG = new Gamer();

                tempG.setId(gamerSet.getLong("gamerID"));
                tempG.setEmail(gamerSet.getString("gamerEmail"));
                tempG.setName(gamerSet.getString("gamerName"));
                tempG.setPassword(gamerSet.getString("gamerPassword"));
                tempG.setGender(String.valueOf(gamerSet.getInt("gamerGender")));

                gamers.add(tempG);
            }
            conn.commit();
            return gamers;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        } finally {
            try {
                gamerSet.close();
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


    //  返回所有管理员的集合，如果查询异常返回null
    public List<Administrator> getAllAdmins() {
        List<Administrator> admins = new ArrayList<Administrator>();
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet adminSet = null;
        try {
            conn.setAutoCommit(false);
            adminSet = new AdminDAOImpl().getAll(conn);
            while (adminSet.next()) {
                Administrator tempA = new Administrator();
                tempA.setId(adminSet.getLong("adminID"));
                tempA.setEmail(adminSet.getString("adminEmail"));
                tempA.setGender(String.valueOf(adminSet.getInt("adminGender")));
                tempA.setName(adminSet.getString("adminName"));
                tempA.setPassword(adminSet.getString("adminPassword"));
                tempA.setPhone(adminSet.getString("adminPhone"));
                admins.add(tempA);
            }
            conn.commit();
            return admins;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        } finally {
            try {
                adminSet.close();
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


    // 通过邮箱得到玩家
    public  Gamer getGamer(Gamer gamer) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet gamerSet = null;
        Gamer g = new Gamer();
        try {
            conn.setAutoCommit(false);

            // 通过邮箱得到玩家
            gamerSet = new GamerDAOImpl().get(conn, gamer);
            while (gamerSet.next()) {
                g.setId(gamerSet.getLong("gamerID"));
                g.setEmail(gamerSet.getString("gamerEmail"));
                g.setName(gamerSet.getString("gamerName"));
                g.setGender(String.valueOf(gamerSet.getInt("gamerGender")));
                g.setPassword(gamerSet.getString("gamerPassword"));
                conn.commit();
                return g;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                // TODO: handle exception
            }
            return null;
            // TODO: handle exception
        } finally {
            try {
                gamerSet.close();
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


    // 由邮箱获取管理员
    public Administrator getAdmin(Administrator admin) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet adminSet = null;
        Administrator a = new Administrator();
        try {
            conn.setAutoCommit(false);

            // 由邮箱获取管理员
            adminSet = new AdminDAOImpl().get(conn, admin);
            while (adminSet.next()) {
                a.setId(adminSet.getLong("adminID"));
                a.setEmail(adminSet.getString("adminEmail"));
                a.setPassword(adminSet.getString("adminPassword"));
                a.setName(adminSet.getString("adminName"));
                a.setGender(String.valueOf(adminSet.getInt("adminGender")));
                a.setPhone(adminSet.getString("adminPhone"));

                conn.commit();
                return a;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                // TODO: handle exception
            }
            return null;
            // TODO: handle exception
        } finally {
            try {
                adminSet.close();
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

    //通过ID获取玩家
    public  Gamer getGamerByID(Gamer gamer) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet gamerSet = null;
        Gamer g = new Gamer();
        try {
            conn.setAutoCommit(false);
            gamerSet = new GamerDAOImpl().getByID(conn, gamer);
            while (gamerSet.next()) {
                g.setId(gamerSet.getLong("gamerID"));
                g.setEmail(gamerSet.getString("gamerEmail"));
                g.setGender(String.valueOf(gamerSet.getInt("gamerGender")));
                g.setPassword(gamerSet.getString("gamerPassword"));
                g.setName(gamerSet.getString("gamerName"));

            }
            conn.commit();
            return g;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        } finally {
            if (gamerSet != null) {
                try {
                    gamerSet.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    // 通过ID获取管理员
    public Administrator getAdminByID(Administrator admin) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        Administrator a = admin;
        ResultSet adminSet = null;
        try {
            conn.setAutoCommit(false);
            adminSet = new AdminDAOImpl().getById(conn, admin);
            while (adminSet.next()) {
                a.setId(adminSet.getLong("adminID"));
                a.setEmail(adminSet.getString("adminEmail"));
                a.setPassword(adminSet.getString("adminPassword"));
                a.setGender(String.valueOf(adminSet.getInt("adminGender")));
                a.setName(adminSet.getString("adminName"));
                a.setPhone(adminSet.getString("adminPhone"));
                return a;
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        } finally {
            try {
                adminSet.close();
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
