package org.lanqiao.dao;

import org.lanqiao.entity.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao{
    //插入
    public void insert(String sql, Object[] obj) {
        executeUpdate(sql, obj);
    }
    //删除
    public int delete(int id) {
        return executeUpdate("delete order where order_id = ?", new Object[]{id});
    }
    //修改
    public void update(String sql, Object[] obj) {
        executeUpdate(sql, obj);
    }
    //查询
    public List<Order> select(String key) {   // key是查询的关键字
        return executeQuery("select order_id, user_id, course_id, o" +
                        "from course where course_name like '%?%'",
                new Object[]{key});
    }
}

// 查询 下单时间 订单号 交易状态
// select course_id from order
// 课程名 价格
// select course_name, price from course where course_id = ()
// 整合
/*
    select o.order_time, o.order_id, o.order_status, c.course_name, c.price
    from order o
    left outer join course c
    on (c.course_id = o.course_id)
*/

