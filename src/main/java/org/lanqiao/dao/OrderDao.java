package org.lanqiao.dao;

import org.lanqiao.entity.Order;

import java.util.List;

public interface OrderDao {
    //插入
    public void insert(String sql, Object[] obj);
    //删除
    public int delete(int id);
    //修改
    public void update(String sql, Object[] obj);
    //查询
    public List<Order> select(String key) ;
}
