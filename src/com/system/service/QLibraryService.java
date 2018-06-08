package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.daoImpl.QLibraryDAOImpl;
import com.system.entity.QLibrary;
import com.system.entity.Administrator;
import com.system.util.ConnectionFactory;


public class QLibraryService {


     // 添加一个题库，同时得到题库的ID
    public QLibrary addQLToGetId(QLibrary QL, Administrator Admin) {
        QLibrary ql = QL;
        if (ql.getName() == null || ql.getStartTime() == null || ql.getType() == null) {
            return null;
        }
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ConsultService cService = new ConsultService();
        try {
            conn.setAutoCommit(false);
            Administrator  admin = cService.getAdmin(Admin);
            ResultSet set = new QLibraryDAOImpl().insert(conn, ql, admin);
            conn.commit();
            while (set.next()) {
                ql.setId(set.getLong(1));
                // System.out.println("已经添加");
                return ql;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

            }
            return null;
            // TODO: handle exception
        } finally {
            try {
                conn.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    /*
     * 管理员添加题库，需要传入管理员的Email和题库的相关信息（名字，类型，开始结束时间）
     */
    public boolean addQLibrary(QLibrary ql, Administrator admin) {
        if (ql.getName() == null || ql.getStartTime() == null || ql.getType() == null) {
            return false;
        }
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ConsultService cService = new ConsultService();
        try {
            conn.setAutoCommit(false);
            admin = cService.getAdmin(admin);
            new QLibraryDAOImpl().insert(conn, ql,admin );
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

            }
            return false;
            // TODO: handle exception
        } finally {
            try {
                conn.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    /*
     * 获得某个管理员的所有题库，需要传入管理员的Email,如果发生异常，返回null
     */
    public List<QLibrary> getQLibrary(Administrator admin) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ConsultService cService = new ConsultService();
        ResultSet qlSet = null;
        List<QLibrary> qlList = new ArrayList<QLibrary>();
        try {
            conn.setAutoCommit(false);
            if (admin.getId() == -1L) {
                admin = cService.getAdmin(admin);
            }
            qlSet = new QLibraryDAOImpl().get(conn, admin);
            while (qlSet.next()) {
                QLibrary ql = new QLibrary();
                ql.setId(qlSet.getLong("id"));
                ql.setName(qlSet.getString("name"));
                ql.setType(qlSet.getString("type"));
                ql.setStartTime(qlSet.getTimestamp("startTime").toString());
                ql.setEndTime(qlSet.getTimestamp("endTime").toString());
                ql.setAmount(qlSet.getInt("amountPerTest"));

                qlList.add(ql);
            }
            conn.commit();

            return qlList;
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
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                qlSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public List<QLibrary> getAll() {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet qlSet = null;
        List<QLibrary> qlList = new ArrayList<QLibrary>();
        try {
            conn.setAutoCommit(false);
            qlSet = new QLibraryDAOImpl().getAll(conn);
            while (qlSet.next()) {
                QLibrary ql = new QLibrary();
                ql.setId(qlSet.getLong("id"));
                ql.setName(qlSet.getString("name"));
                ql.setType(qlSet.getString("type"));
                ql.setStartTime(qlSet.getTimestamp("startTime").toString());
                ql.setEndTime(qlSet.getTimestamp("endTime").toString());
                ql.setAmount(qlSet.getInt("amountPerTest"));

                qlList.add(ql);
            }
            conn.commit();

            return qlList;
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
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                qlSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
