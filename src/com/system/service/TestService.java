package com.system.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.system.daoImpl.*;
import com.system.entity.Administrator;
import com.system.entity.Gamer;
import com.system.entity.QLibrary;
import com.system.entity.Test;
import com.system.util.ConnectionFactory;

public class TestService {

    /*
     * 通过testID查找对应的题库和管理员，返回为Map，只含有一对key-value； 如果发生异常，返回null
     * 这一对key-value包含了对应对象的所有信息，除了管理员的密码
     */
    public Map<QLibrary, Administrator> getDetailOfTest(Test test) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();

        ResultSet qlSet = null;
        ResultSet adminSet = null;
        ResultSet testSet = null;

        TestDAOImpl testImpl = new TestDAOImpl();
        AdminDAOImpl adminImpl = new AdminDAOImpl();
        QLibraryDAOImpl qlImpl = new QLibraryDAOImpl();

        try {
            conn.setAutoCommit(false);
            testSet = testImpl.get(conn, test);
            Administrator admin = new Administrator();
            QLibrary ql = new QLibrary();

            while (testSet.next()) {
               QLibrary tempQL = new QLibrary();
               tempQL.setId(testSet.getLong("QLibraryID"));
               qlSet = qlImpl.get(conn,tempQL);
               while(qlSet.next())
               {
                   Long id = qlSet.getLong("id");
                   String type = qlSet.getString("type");
                   String name = qlSet.getString("name");
                   String startTime = qlSet.getTimestamp("startTime").toString();
                   String endTime = qlSet.getTimestamp("endTime").toString();
                   long adminID = qlSet.getLong("adminID");
                   Administrator tempAdmin = new Administrator();
                   tempAdmin.setId(adminID);

                   ql.setStartTime(startTime);
                   ql.setEndTime(endTime);
                   ql.setId(id);
                   ql.setType(type);
                   ql.setName(name);
                   adminSet = adminImpl.getById(conn, tempAdmin);
                   while(adminSet.next())
                   {
                       long AdminID = adminSet.getLong("adminID");
                       String AdminEmail = adminSet.getString("adminEmail");
                       String AdminName = adminSet.getString("adminName");
                       String AdminPhone = adminSet.getString("adminPhone");

                       int gender = adminSet.getInt("adminGender");
                       String trueGender = "1";
                       if (gender == 0) {
                           trueGender = "0";
                       }
                       admin.setId(AdminID);
                       admin.setEmail(AdminEmail);
                       admin.setName(AdminName);
                       admin.setPhone(AdminPhone);
                       admin.setGender(trueGender);
                   }
               }

            }
                conn.commit();
            Map<QLibrary, Administrator> returnMap = new HashMap<>();
            returnMap.put(ql, admin);
            return returnMap;
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
                qlSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                testSet.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
     * 玩家答题时创建一个测试test, 需要传入Test对象, gamer对象，包含其ID属性 返回值为这次答题分配的testID信息，
     * 之后为每道题添加答案的时候需要利用该ID，如果发生异常，则返回-1L
     */
    public long beginTest(Test test, QLibrary qlWithID,Gamer gamerWithID) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet testIdSet = null;
        try {
            conn.setAutoCommit(false);
            testIdSet = new TestDAOImpl().insert(conn, gamerWithID,qlWithID,test);
            conn.commit();
            while (testIdSet.next()) {
                return testIdSet.getLong("max(testID)");
            }
            return -1L;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return -1L;
        } finally {
            try {
                testIdSet.close();
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

    /*
     * 根据测试的ID查找测试的信息 需要传入测试对象，其中至少包含测试的ID 返回对应该ID的唯一测试结果，这次测试的分数。
     * 如果没有查到相应，返回一个没有内容的Test对象 如果查询过程发生异常，返回null
     */
    public Test getTestRecord(Test test) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet recordSet = null;
        try {
            conn.setAutoCommit(false);
            recordSet = new TestDAOImpl().get(conn, test);
            while (recordSet.next()) {
                Test tempTest = new Test();

                tempTest.setTestID(recordSet.getLong("testID"));
                tempTest.setTestScore(recordSet.getInt("testScore"));
                tempTest.setTestTime(recordSet.getTimestamp("testTime").toString());
                conn.commit();
                return tempTest;
            }
            return new Test();
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
                recordSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
     * 根据题库的ID信息查看测试信息，如果发生异常，返回null
     */
    public Map<Test, Gamer> getTestRecord(QLibrary ql) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet testSet = null;
        ResultSet gamerSet = null;

        Map<Test, Gamer> map = new HashMap<>();
        try {
            conn.setAutoCommit(false);
            testSet = new TestDAOImpl().get(conn, ql);
            while (testSet.next()) {
                Test test = new Test();
                Gamer gamer = new Gamer();
                Gamer tempG = new Gamer();
                long gid = testSet.getLong("gamerID");
                tempG.setId(gid);
                gamerSet = new GamerDAOImpl().getByID(conn, tempG);
                while (gamerSet.next()) {

                    Long gamerID = gamerSet.getLong("gamerID");
                    String gamerEmail = gamerSet.getString("gamerEmail");
                    String gamerName = gamerSet.getString("gamerName");
                    int gamerGender = gamerSet.getInt("gamerGender");
                    gamer.setId(gamerID);
                    gamer.setEmail(gamerEmail);
                    gamer.setGender(String.valueOf(gamerGender));
                    gamer.setName(gamerName);

                }

                test.setTestID(testSet.getLong("testID"));
                test.setTestScore(testSet.getInt("testScore"));
                test.setTestTime(testSet.getTimestamp("testTime").toString());
                map.put(test, gamer);
            }
            conn.commit();
            return map;
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
                testSet.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    /*
     * 通过玩家的ID查询玩家所做的测试，如果发生异常，返回null
     */
    public Vector<Test> getTestRecord(Gamer gamer) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        ResultSet testSet = null;
        try {
            conn.setAutoCommit(false);
            testSet = new TestDAOImpl().get(conn, gamer);
            Vector<Test> testVector = new Vector<Test>();
            while (testSet.next()) {
                Test tempTest = new Test();
                tempTest.setTestID(testSet.getLong("testID"));
                tempTest.setTestScore(testSet.getInt("testScore"));
                tempTest.setTestTime(testSet.getTimestamp("testTime").toString());
                testVector.add(tempTest);
            }
            conn.commit();
            return testVector;

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
                testSet.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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
