package com.system.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.system.entity.QLibrary;
import com.system.entity.Test;
import com.system.dao.TestDAO;
import com.system.entity.Gamer;

public class TestDAOImpl implements TestDAO{

    private final String format = "%Y-%m-%d %H:%i:%s";

    @Override
    public synchronized ResultSet insert(Connection conn, Gamer gamer, QLibrary ql, Test test)
            throws SQLException {
        // TODO Auto-generated method stub
        String insertSql = "INSERT INTO gamedb.tbl_test VALUES(NULL,STR_TO_DATE(?,'"
                + format + "'),?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insertSql);

        ps.setString(1, test.getTestTime());
        ps.setLong(2, gamer.getId());
        ps.setLong(3, ql.getId());
        ps.setInt(4, test.getTestScore());
        ps.execute();
        String sql = "SELECT max(testID) from gamedb.tbl_test";
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

    @Override
    public void update(Connection conn, Test test) throws SQLException {
        // TODO Auto-generated method stub
        String updateSql = "UPDATE gamedb.tbl_test SET testScore=? WHERE testID=?";
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setInt(1, test.getTestScore());
        ps.setLong(2, test.getTestID());
        ps.execute();

    }

    @Override
    public void delete(Connection conn, Test test) throws SQLException {
        // TODO Auto-generated method stub
        String deleteSql = "DELETE * FROM gamedb.tbl_test WHERE testID=?";
        PreparedStatement ps = conn.prepareStatement(deleteSql);
        ps.setLong(1, test.getTestID());
        ps.execute();

    }

    @Override
    public ResultSet get(Connection conn, Test test) throws SQLException {
        // TODO Auto-generated method stub
        String getSql = "SELECT * FROM gamedb.tbl_test WHERE testID=?";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, test.getTestID());
        return ps.executeQuery();
    }

    /*
     * 通过玩家ID查找Test信息
     */
    public ResultSet get(Connection conn, Gamer gamer) throws SQLException {

        String getSql = "SELECT gamedb.tbl_test.testScore,gamedb.tbl_test.testTime,gamedb.tbl_test.testID FROM gamedb.tbl_test,gamedb.tbl_gamer WHERE gamedb.tbl_gamer.gamerID=? AND gamedb.tbl_gamer.gamerID=gamedb.tbl_test.gamerID";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, gamer.getId());
        return ps.executeQuery();
    }

    /*
     * 通过题库ID查找Test信息
     */
    public ResultSet get(Connection conn, QLibrary ql) throws SQLException {
        String getSql = "SELECT gamedb.tbl_test.testScore,gamedb.tbl_test.testTime,gamedb.tbl_test.testID,gamedb.tbl_test.gamerID FROM gamedb.tbl_test,gamedb.tbl_QLibrary WHERE gamedb.tbl_QLibrary.id=? AND gamedb.tbl_QLibrary.id=gamedb.tbl_test.QLibraryID";
        PreparedStatement ps = conn.prepareStatement(getSql);
        ps.setLong(1, ql.getId());
        return ps.executeQuery();
    }


}
