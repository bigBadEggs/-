package org.lanqiao.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {
//    // 驱动名
//    public final String driver = "oracle.jdbc.OracleDriver";
//    // 连接数据库的url
//    public final String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//    // 连接数据库账户名
//    public final String name = "scott";
//    // 数据库用户名密码
//    public final String pass = "lj123";

    // c3p0配置，提供私有化数据源
    public static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    // 获得数据源
    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }
    // 获得连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    Class<T> clazz;
    // 反射获得clazz
    @SuppressWarnings("unchecked")
    public BaseDao() {
        clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    // DML操作
    public int executeUpdate(String sql, Object[] obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        int res = 0;
        try {
            conn = getConnection();
            // 3. 创建传输对象statment
            ps = conn.prepareStatement(sql);
            // 3.1 传入参数，防止sql注入
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            // 4. 发送sql语句，并接收返回结果
            // DML：executeUpdate, DQL:executeQuery
            res = ps.executeUpdate();
            // 5. 如果返回rs类型的数据，需要将数据转换为list

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭一切
            colseSource(ps, conn);
        }
        return res;
    }

    // DQL操作
    public List<T> executeQuery(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ArrayList<T> list = new ArrayList<T>();
        try {
            conn = getConnection();
            // 3. 创建传输对象statment
            ps = conn.prepareStatement(sql); // 通配符？类型数量不确定

            // 4. 发送sql语句，并接收返回结果
            // DML：executeUpdate, DQL:executeQuery
            res = ps.executeQuery();
            // 5. 如果返回res类型的数据，需要将数据转换为list
            ResultSetMetaData rsmd = res.getMetaData(); //获得列名信息
            int columnCount = rsmd.getColumnCount(); //获得列名数量
            while (res.next()) {
                T obj = (T)clazz.newInstance();
                for(int i=0; i<columnCount; i++) {
                    Field f = clazz.getDeclaredField(rsmd.getColumnName(i+1).toLowerCase()); //列名->属性名->属性对象
                    f.setAccessible(true);
                    if(res.getObject(i+1) instanceof BigDecimal) {
                        BigDecimal bd = (BigDecimal) res.getObject(i+1);
                        int j = bd.intValue();
                        f.set(obj, j);
                    }else
                        f.set(obj, res.getObject(i+1));

                    //f.set(t, res.getObject(i+1)); //将res中的值赋给属性
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭一切
            colseSource(res, ps, conn);
        }
        return list;
    }
    // DQL操作
    public List<T> executeQuery(String sql, Object[] objs) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ArrayList<T> list = new ArrayList<T>();
        try {
            conn = getConnection();
            // 3. 创建传输对象statment
            ps = conn.prepareStatement(sql); // 通配符？类型数量不确定

            // 绑定数据
            for (int i=0; i<objs.length; i++){
                ps.setObject(i+1, objs[i]);
            }

            // 4. 发送sql语句，并接收返回结果
            // DML：executeUpdate, DQL:executeQuery
            res = ps.executeQuery();
            // 5. 如果返回res类型的数据，需要将数据转换为list
            ResultSetMetaData rsmd = res.getMetaData(); //获得列名信息
            int columnCount = rsmd.getColumnCount(); //获得列名数量
            while (res.next()) {
                T obj = (T)clazz.newInstance();
                for(int i=0; i<columnCount; i++) {
                    Field f = clazz.getDeclaredField(rsmd.getColumnName(i+1).toLowerCase()); //列名->属性名->属性对象
                    f.setAccessible(true);
                    if(res.getObject(i+1) instanceof BigDecimal) {
                        BigDecimal bd = (BigDecimal) res.getObject(i+1);
                        int j = bd.intValue();
                        f.set(obj, j);
                    }else
                        f.set(obj, res.getObject(i+1));

                    //f.set(t, res.getObject(i+1)); //将res中的值赋给属性
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭一切
            colseSource(res, ps, conn);
        }
        return list;
    }

    // 查询数据库记录条数
    public int getRecordCount(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        int count = 0;
        try {
            conn = getConnection();
            // 3. 创建传输对象statment
            ps = conn.prepareStatement(sql); // 通配符？类型数量不确定

            // 4. 发送sql语句，并接收返回结果
            // DML：executeUpdate, DQL:executeQuery
            res = ps.executeQuery();
            // 5. 如果返回res类型的数据，需要将数据转换为list
            if (res.next()) {
                count = res.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭一切
            colseSource(res, ps, conn);
        }
        return count;
    }

    // 关闭资源
    public void colseSource(PreparedStatement ps, Connection c) {
        try {
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭资源
    public void colseSource(ResultSet res, PreparedStatement ps, Connection c) {
        try {
            res.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
