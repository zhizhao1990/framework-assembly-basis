package com.cmc.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmc.user.facade.entity.User;

/**
 * JDBC Demo.
 * @author Thomas Lee
 * @version 2017年3月28日 下午9:25:09
 */
public class JDBCDemo {

    private static final Logger LOG = LoggerFactory.getLogger(JDBCDemo.class);
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ssm_primary?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    
    private static final ArrayList<Connection> CONNECTIONS = new ArrayList<Connection>();
    private static final int MIN_CAPACITY = 20;

    static {
        CONNECTIONS.ensureCapacity(MIN_CAPACITY);
    }

    public static void main(String[] args) {
        // produceSimpleJDBCQuery(driver, url, username, password, sql);
        String sql = "select * from user where is_delete = '1'";
        List<User> users = JDBCDemo.selectList(sql);
        Iterator<User> iUsers = users.iterator();
        while (iUsers.hasNext()) {
            User user = iUsers.next();
            LOG.info(user.toString());
        }

        // 回收自定义连接池中数据库连接垃圾
        gcConnectionPool();
    }

    /**
     * 回收数据库连接池中不用的对象
     * @author Thomas Lee
     * @version 2017年3月28日 下午10:28:22
     */
    public static void gcConnectionPool() {
        Iterator<Connection> iConnection = CONNECTIONS.iterator();
        while (iConnection.hasNext()) {
            try (Connection conn = iConnection.next()) {
                // 移除已经交给垃圾回收机制管理的数据库连接
                // CONNECTIONS.remove(conn);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 产生一个简单的JDBC查询数据库的过程
     * @author Thomas Lee
     * @version 2017年3月28日 下午9:41:43
     * @param driver 驱动名称字符串
     * @param url 数据库地址
     * @param username 数据库用户名
     * @param password 数据库密码
     * @param sql 查询语句
     */
    public static void produceSimpleJDBCQuery(String driver, String url, String username, String password, String sql) {
        try {
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                try (Statement stmt = conn.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        while (rs.next()) {
                            LOG.info(rs.getString("name"));
                        }
                    }

                }
            }
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 获取结果集
     * @author Thomas Lee
     * @version 2017年3月28日 下午9:54:27
     * @param sql SQL语句
     * @return 结果集
     */
    public static ResultSet getResultSet(String sql) {
        try {
            Class.forName(DRIVER);
            // 因为关闭了数据库连接就会连带关闭ResultSet，所以在此没有直接释放数据库连接，而是交给自定义的数据库连接池进行数据库连接的生命周期管理.
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 把生成的数据库连接放到自定义的连接池中
            CONNECTIONS.add(conn);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            return null;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取用户集合 
     * @author Thomas Lee
     * @version 2017年3月28日 下午9:55:02
     * @param sql SQL语句
     * @return 用户集合
     */
    public static List<User> selectList(String sql) {
        List<User> users = new ArrayList<User>();
        try (ResultSet rs = getResultSet(sql)) {
            while (null != rs && rs.next()) {
                User user = new User();
                user.setId(rs.getString(User.ID));
                user.setName(rs.getString(User.NAME));
                user.setSex(rs.getString(User.SEX));
                user.setAge(rs.getInt(User.AGE));
                user.setCreateTime(rs.getDate(User.CREATE_TIME));
                user.setUpdateTime(rs.getDate(User.UPDATE_TIME));
                user.setDeleteTime(rs.getDate(User.DELETE_TIME));
                user.setTemp1(rs.getString(User.TEMP1));
                user.setTemp1(rs.getString(User.TEMP1));
                user.setTemp2(rs.getString(User.TEMP2));
                user.setTemp3(rs.getString(User.TEMP3));
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }
}