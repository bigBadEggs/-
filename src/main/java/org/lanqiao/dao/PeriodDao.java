package org.lanqiao.dao;


import org.lanqiao.entity.Period;

import java.util.List;

public interface PeriodDao {
    //插入
    public void insert(String sql, Object[] obj);
    //删除
    public int delete(Period p);
    //修改
    public void update(String sql, Object[] obj);
    //查询
    public List<Period> select(Period p) ;
}
