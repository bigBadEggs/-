package org.lanqiao.dao;

import org.lanqiao.entity.CourseKind;

import java.util.List;

public interface CourseKindDao {
    //插入
    public void insert(String sql, Object[] obj);
    //删除
    public int delete(int id);
    //修改
    public void update(String sql, Object[] obj);
    //查询
    public List<CourseKind> select(CourseKind ck) ;
}
