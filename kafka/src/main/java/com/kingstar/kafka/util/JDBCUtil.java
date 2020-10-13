package com.kingstar.kafka.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiayc
 * @date 2020/5/15 13:11
 */
@Data
@Slf4j
public class JDBCUtil {

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    private static int index = 0;

    private CallableStatement callableStatement = null;//创建CallableStatement对象
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rst = null;

    public JDBCUtil() {

    }

    public JDBCUtil(String driver, String url, String username, String password) {
        JDBCUtil.driver = driver;
        JDBCUtil.url = url;
        JDBCUtil.username = username;
        JDBCUtil.password = password;
    }

    /**
     * 初始化数据库连接 并返回连接
     *
     * @return 数据库连接
     */
    public Connection initConnection() {
        try {
            // 加载数据库驱动程序
            Class.forName(driver);
            // 获取连接
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.info("JDBCUtil get connection exception");
        }
        return conn;
    }

    /**
     * insert update delete SQL语句的执行的统一方法
     *
     * @param sql    SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 受影响的行数
     */
    public int executeUpdate(String sql, Object[] params) throws SQLException {
        // 受影响的行数
        int affectedLine = 0;
        // 调用SQL
        pst = conn.prepareStatement(sql);
        // 参数赋值
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
        }
            /*在此 PreparedStatement 对象中执行 SQL 语句，
                                          该语句必须是一个 SQL 数据操作语言（Data Manipulation Language，DML）语句，比如 INSERT、UPDATE 或 DELETE
                                          语句；或者是无返回内容的 SQL 语句，比如 DDL 语句。    */
        // 执行
        affectedLine = pst.executeUpdate();
        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     *
     * @param sql    SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    private ResultSet executeQueryRS(String sql, Object[] params) throws SQLException {
        // 调用SQL
        pst = this.conn.prepareStatement(sql);

        // 参数赋值
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
        }
        // 执行
        rst = pst.executeQuery();

        return rst;
    }


    /**
     * 获取结果集，并将结果放在List中
     *
     * @param sql SQL语句
     * @return List
     * 结果集
     * @params 参数，没有则为null
     */
    public List<Map<String, Object>> executeQuery(String sql, Object[] params) throws SQLException {
        // 执行SQL获得结果集
        ResultSet rs = executeQueryRS(sql, params);

        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;

        // 结果集列数
        int columnCount = 0;

        rsmd = rs.getMetaData();

        // 获得结果集列数
        columnCount = rsmd.getColumnCount();

        // 创建List
        List<Map<String, Object>> list = new ArrayList<>();

        // 将ResultSet的结果保存到List中
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 1; i <= columnCount; i++) {
                map.put(rsmd.getColumnLabel(i), rs.getObject(i));
            }
            //每一个map代表一条记录，把所有记录存在list中
            list.add(map);
        }

        return list;
    }

    /**
     * 关闭所有资源
     */
    private void closeAll() {
        // 关闭结果集对象
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭PreparedStatement对象
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭CallableStatement 对象
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭Connection 对象
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 执行sql脚本
     *
     * @param br
     * @param conn
     */
    public void executeSqlScript(BufferedReader br, Connection conn) {
        br.lines().forEach(sql -> {
            try {
                pst = conn.prepareStatement(sql);
                pst.addBatch();
                if ((index % 2000) != 0 && index <= 2097152) {
                    index++;
                } else {
                    pst.executeBatch();
                    this.conn.commit();
                    index = 0;
                }
            } catch (Exception e) {
                log.info("【sql脚本执行异常】sql:{}", sql);
                e.printStackTrace();
            }
        });
    }

//    /**
//     * SQL 查询将查询结果：一行一列
//     *
//     * @param sql    SQL语句
//     * @param params 参数数组，若没有参数则为null
//     * @return 结果集
//     */
//    public Object executeQuerySingle(String sql, Object[] params) throws SQLException {
//        Object object = null;
//        try {
//            // 获得连接
//            conn = this.initConnection();
//
//            // 调用SQL
//            pst = conn.prepareStatement(sql);
//
//            // 参数赋值
//            if (params != null) {
//                for (int i = 0; i < params.length; i++) {
//                    pst.setObject(i + 1, params[i]);
//                }
//            }
//
//            // 执行
//            rst = pst.executeQuery();
//
//            if (rst.next()) {
//                object = rst.getObject(1);
//            }
//
//        } finally {
//            closeAll();
//        }
//
//        return object;
//    }

    public static void main(String[] args) throws SQLException {
        JDBCUtil jdbcUtil = new JDBCUtil("com.mysql.cj.jdbc.Driver", "jdbc:mysql://192.168.100.61:3306/kingstar_banana_test_cockroach?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8", "root", "12345678");
        Connection connection = jdbcUtil.initConnection();

        for (int i = 0; i < 10000000; i++) {
            List<Map<String, Object>> maps = jdbcUtil.executeQuery("select REPLACE (unix_timestamp(CURRENT_TIMESTAMP(3)+300),'.','') as  oper_time;", null);
            String value = JSON.toJSONString(maps);
            if(value.contains("0000")){
                System.out.println(value);
            }
        }
        connection.close();
    }
}

