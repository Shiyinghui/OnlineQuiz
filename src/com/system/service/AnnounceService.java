package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.system.daoImpl.AnnounceDAOImpl;
import com.system.entity.Announce;
import com.system.util.ConnectionFactory;

public class AnnounceService {

    // 新发布一条公告
    public boolean addAnnounce(Announce announce) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new AnnounceDAOImpl().insert(conn, announce);
            conn.commit();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // 删除一条公告
    public boolean deleteAnnounce(Announce announce) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            new AnnounceDAOImpl().delete(conn, announce);
            conn.commit();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return false;
        } finally {
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

    // 所有公告
    public List<Announce> getAllAnnounce() {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        List<Announce> ansList = new ArrayList<Announce>();
        ResultSet ansSet = null;
        try {
            conn.setAutoCommit(false);
            ansSet = new AnnounceDAOImpl().getAll(conn);
            while (ansSet.next()) {
                Announce temp = new Announce();
                temp.setId(ansSet.getLong("announceID"));
                temp.setContent(ansSet.getString("announceContent"));
                temp.setTitle(ansSet.getString("announceTitle"));
                temp.setTime(ansSet.getTimestamp("announceDate").toString());
                ansList.add(temp);
            }
            conn.commit();
            return ansList;
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (ansSet != null) {
                try {
                    ansSet.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    // 由公告ID得到公告
    public Announce get(Announce announce){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet res = null;
        Announce temp = new Announce();
        try {
            conn.setAutoCommit(false);
            res = new AnnounceDAOImpl().get(conn,announce);
            while (res.next()) {
                temp.setId(res.getLong("announceID"));
                temp.setContent(res.getString("announceContent"));
                temp.setTitle(res.getString("announceTitle"));
                temp.setTime(res.getTimestamp("announceDate").toString());
            }
            conn.commit();
            return temp;
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
