package com.system.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.system.entity.Announce;
import com.system.dao.AnnounceDA0;

public class AnnounceDAOImpl implements AnnounceDA0 {

    private final String format = "%Y-%m-%d %H:%i:%s";
    @Override  // 新发布一条公告
    public void insert(Connection conn, Announce announce) throws SQLException {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO gamedb.tbl_announce VALUES(NULL,?,?,STR_TO_DATE(?,'"
                + format + "'))";
        PreparedStatement ps = conn.prepareStatement(sql);

        //setString() Sets the designated parameter to the given Java String value.
        ps.setString(1, announce.getTitle());
        ps.setString(2, announce.getContent());
        ps.setString(3, announce.getTime());
        ps.execute();

        // boolean execute() Executes the SQL statement in this PreparedStatement object,
        // which may be any kind of SQL statement.
    }

    @Override
    public void update(Connection conn, Announce announce) {
        // TODO Auto-generated method stub

    }

    @Override  // 删除一条公告
    public void delete(Connection conn, Announce announce) throws SQLException {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM gamedb.tbl_announce WHERE announceID=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, announce.getId());
        ps.execute();
    }

    @Override  // 由公告ID得到该公告
    public ResultSet get(Connection conn, Announce announce) throws SQLException {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM gamedb.tbl_announce WHERE announceID=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, announce.getId());
        return ps.executeQuery();  // 执行查询公告
    }

    // SYH 查看系统所有公告
    public ResultSet getAll(Connection conn) throws SQLException{
        String sql = "SELECT * FROM gamedb.tbl_announce";
        PreparedStatement ps =conn.prepareStatement(sql);
        return ps.executeQuery();
    }


}
