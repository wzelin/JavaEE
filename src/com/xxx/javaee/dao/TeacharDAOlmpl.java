package com.xxx.javaee.dao;

import com.xxx.javaee.bean.Teachar;
import com.xxx.javaee.bean.param.TeaParam;
import com.xxx.javaee.jdbc.JDBCUtil;
import com.xxx.javaee.jdbc.ObjectUtil;
import com.xxx.javaee.bean.param.Pager;
import com.xxx.javaee.jdbc.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacharDAOlmpl implements TeacharDAO {
    @Override
    public void intsert(Teachar teachar) {

        Connection conn = null;
        PreparedStatement psd = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into tab_teachar(`name`,`age`,`sex`,`college`,`subject`) " +
                    "values(?,?,?,?,?)";
            psd = conn.prepareStatement(sql);

            psd.setString(1,teachar.getName());
            psd.setInt(2,teachar.getAge());
            psd.setInt(3,teachar.getSex());
            psd.setInt(4,teachar.getCollege());
            psd.setString(5,teachar.getSubject());

            psd.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCUtil.close(conn,psd,null);
        }

    }

    @Override
    public void delete(long id) {
        Connection conn = null;
        PreparedStatement psd = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from tab_teachar where id = ?";
            psd = conn.prepareStatement(sql);

            psd.setLong(1,id);

            psd.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCUtil.close(conn,psd,null);
        }
    }

    private String initSql(TeaParam sp, Pager<Teachar> pager){
        return new SQL(){{
            if(pager == null) {
                SELECT("count(s.id) as total");
            }else{
                SELECT("s.*");
            }
            FROM("tab_teachar s");
            if(sp.getName() != null && !"".equals(sp.getName())){
                WHERE("s.name like ?");
            }
            if(sp.getStart() != null){
                WHERE("s.age >= ?");
            }
            if(sp.getEnd() != null){
                WHERE("s.age <= ?");
            }

        }}.toString();

    }

    /**
     * 查询当前页的数据
     * @param sp
     * @return
     */
    public List<Teachar> queryPageList(TeaParam sp,Pager<Teachar> pager){
        String querySql = initSql(sp,pager) + " LIMIT ?,?";
        System.out.println(querySql);

        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;


        try {
            conn = JDBCUtil.getConnection();
            psd = conn.prepareStatement(querySql);

            int index = 1;
            if(sp.getName() != null && !"".equals(sp.getName())){
                psd.setString(index++,"%"+sp.getName()+"%");
            }
            if(sp.getStart() != null){
                psd.setInt(index++,sp.getStart());
            }
            if(sp.getEnd() != null){
                psd.setInt(index++,sp.getEnd());
            }
            psd.setInt(index++,pager.getStart());
            psd.setInt(index++,pager.getPageSize());

            rs = psd.executeQuery();
            List<Teachar> list = new ArrayList<>();
            while (rs.next()){
                Teachar s = ObjectUtil.getInstance(rs,Teachar.class);
                list.add(s);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,psd,rs);
        }

        return null;
    }

    private int queryCount(TeaParam sp){
        String sql = initSql(sp,null);
        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;


        try {
            conn = JDBCUtil.getConnection();
            psd = conn.prepareStatement(sql);

            int index = 1;
            if(sp.getName() != null && !"".equals(sp.getName())){
                psd.setString(index++,"%"+sp.getName()+"%");
            }
            if(sp.getStart() != null){
                psd.setInt(index++,sp.getStart());
            }
            if(sp.getEnd() != null){
                psd.setInt(index++,sp.getEnd());
            }

            rs = psd.executeQuery();
            rs.next();

            return rs.getInt("total");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,psd,rs);
        }

        return 0;
    }

    @Override
    public Pager<Teachar> query(TeaParam sp, Pager<Teachar> pager) {
        pager.setTotal(queryCount(sp));
        pager.setList(queryPageList(sp,pager));
        return pager;
    }

    @Override
    public void update(Teachar teachar) {
        Connection conn = null;
        PreparedStatement psd = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "update tab_teachar set name=?,age = ?," +
                    "sex = ?,subject = ?,college = ? where id =  ?";
            psd = conn.prepareStatement(sql);

            psd.setString(1,teachar.getName());
            psd.setInt(2,teachar.getAge());
            psd.setInt(3,teachar.getSex());
            psd.setInt(5,teachar.getCollege());
            psd.setString(4,teachar.getSubject());
            psd.setLong(6,teachar.getId());

            psd.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCUtil.close(conn,psd,null);
        }
    }
}
