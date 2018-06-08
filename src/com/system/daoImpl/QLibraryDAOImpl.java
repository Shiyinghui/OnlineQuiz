package com.system.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.system.dao.QLibraryDAO;
import com.system.entity.QLibrary;
import com.system.entity.Administrator;

public class QLibraryDAOImpl implements QLibraryDAO {

    private final String format = "%Y-%m-%d %H:%i:%s";

    @Override
    public synchronized ResultSet insert(Connection conn, QLibrary ql, Administrator admin) throws SQLException {
        // TODO Auto-generated method stub
        String insertSQL = "INSERT INTO gamedb.tbl_QLibrary VALUES(NULL,?,?,STR_TO_DATE(?,'"
                + format + "'),STR_TO_DATE(?,'" + format + "'),?,?)";
        PreparedStatement ps = conn.prepareStatement(insertSQL);
        ps.setString(1, ql.getType());
        ps.setString(2, ql.getName());
        ps.setString(3, ql.getStartTime());
        ps.setString(4, ql.getEndTime());
        ps.setLong(5, admin.getId());
        ps.setInt(6, ql.getAmount());
        ps.execute();
        Statement st = conn.createStatement();
        String sql = "SELECT MAX(id) FROM gamedb.tbl_QLibrary";

        return st.executeQuery(sql);
    }

    @Override
    public void update(Connection conn, QLibrary ql) throws SQLException {
        // TODO Auto-generated method stub
        String updateSQL = "UPDATE gamedb.tbl_QLibrary SET startTime=STR_TO_DATE(?,'" + format
                + "'),name=?, type=?, endTime=STR_TO_DATE(?,'" + format + "') WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(updateSQL);
        ps.setString(1, ql.getStartTime());
        ps.setString(2, ql.getName());
        ps.setString(3, ql.getType());
        ps.setString(4, ql.getEndTime());
        ps.setLong(5, ql.getId());

        ps.execute();
    }

    @Override
    public void delete(Connection conn, QLibrary ql) throws SQLException {
        // TODO Auto-generated method stub
        String deleteSQL = "DELETE FROM gamedb.tbl_QLibrary WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(deleteSQL);
        ps.setLong(1, ql.getId());
        ps.execute();

    }

    @Override
    public ResultSet get(Connection conn, Administrator admin) throws SQLException {
        // TODO Auto-generated method stub
        String getSQL = "SELECT tbl_QLibrary.amountPerTest,tbl_QLibrary.id,tbl_QLibrary.type,tbl_QLibrary.name,tbl_QLibrary.startTime,tbl_QLibrary.endTime FROM tbl_administrator,tbl_QLibrary WHERE tbl_administrator.adminID=? AND tbl_QLibrary.adminID=tbl_administrator.adminID";
        PreparedStatement ps = conn.prepareStatement(getSQL);
        ps.setLong(1, admin.getId());
        return ps.executeQuery();
    }


    public ResultSet get(Connection conn, QLibrary ql) throws SQLException {
        String sql = "SELECT * FROM gamedb.tbl_QLibrary WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, ql.getId());
        return ps.executeQuery();
    }

    public ResultSet getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM gamedb.tbl_QLibrary";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }
}
