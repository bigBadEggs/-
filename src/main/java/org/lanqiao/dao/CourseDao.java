package org.lanqiao.dao;

import org.lanqiao.entity.Course;
import org.lanqiao.entity.CourseKind;

import java.util.List;

public interface CourseDao{
    //插入
    public void insert(String sql, Object[] obj);
    //删除
    public int delete(Course c);
    //修改
    public void update(String sql, Object[] obj);
    //查询二级标题课程
    public List<Course> select(CourseKind ck) ;
    //查询一级标题课程
    public List<Course> selectAll(CourseKind ck) ;
    // 查询三级标题课程
    public List<Course> selectSignle(CourseKind ck, int pageBegin, int pageSize) ;
}
