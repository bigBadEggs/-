package org.lanqiao.dao;

import org.lanqiao.entity.Course;
import org.lanqiao.entity.CourseKind;

import java.util.List;

public class CourseDaoImpl extends BaseDao<Course> implements CourseDao{
    //插入
    public void insert(String sql, Object[] obj) {
        executeUpdate(sql, obj);
    }
    //删除
    public int delete(Course c) {
        return executeUpdate("delete from course where course_id = ?", new Object[]{c.getCourse_id()});
    }
    //修改
    public void update(String sql, Object[] obj) {
        executeUpdate(sql, obj);
    }
    //根据二级目录查询课程信息
    public List<Course> select(CourseKind ck) {
        return executeQuery("select *\n" +
                        "    from course c, (select kind_id from coursekind\n" +
                        "    where pid = (select kind_id from coursekind where kind_name = ?)) cc\n" +
                        "    where c.kind_id = cc.kind_id",
                new Object[]{ck.getKind_name()});
    }

    // 根据一级目录 查询课程信息
    @Override
    public List<Course> selectAll(CourseKind ck) {
        return executeQuery(" select * from course c\n" +
                        "    where c.kind_id in (select kind_id from coursekind c1\n" +
                        "    where c1.pid in (select kind_id from coursekind\n" +
                        "    where pid = (select kind_id from coursekind where kind_name = ?)))",
                new Object[]{ck.getKind_name()});
    }

    // 三级目录
    @Override
    public List<Course> selectSignle(CourseKind ck, int pageBegin, int pageSize) {
        return executeQuery("select * from course\n" +
                        "    where kind_id = (select kind_id from coursekind where kind_name = ?)\n" +
                        "    limit ?,?",
                new Object[]{ck.getKind_name(), pageBegin, pageSize});
    }
}

/*
    根据父类名 得到父类 id
    select kind_id from coursekind where kind_name = ?  -- #

    根据父类id 获取子类id
    select kind_id from coursekind where pid = (#)  -- *

    得到三级id
    select kind_id from coursekind c1
    where c1.pid in (select kind_id from coursekind
    where pid = (select kind_id from coursekind where kind_name = ?))

    获取课程信息
    select * from course where kind_id = (*)
    总结
    一级
    select * from course c
    where c.kind_id in (select kind_id from coursekind c1
    where c1.pid in (select kind_id from coursekind
    where pid = (select kind_id from coursekind where kind_name = ?)))
    limit ?,?

    二级
    select *
    from course c, (select kind_id from coursekind
    where pid = (select kind_id from coursekind where kind_name = ?)) cc
    where c.kind_id = cc.kind_id
    limit ?,?

    三级
    select * from course
    where kind_id = (select kind_id from coursekind where kind_name = ?)
    limit ?,?

*/