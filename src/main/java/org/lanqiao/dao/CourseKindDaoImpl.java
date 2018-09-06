package org.lanqiao.dao;

import org.lanqiao.entity.CourseKind;

import java.util.List;

public class CourseKindDaoImpl extends BaseDao<CourseKind> implements CourseKindDao{
    @Override
    public void insert(String sql, Object[] obj) {

    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public void update(String sql, Object[] obj) {

    }

    @Override
    public List<CourseKind> select(CourseKind ck) {
        return executeQuery("select kind_name from coursekind \n" +
                        "    where pid = (select kind_id from coursekind where kind_name = ?)",
                new Object[]{ck.getKind_name()});
    }
}

/*
    根据父类名 得到父类 id
    select kind_id from coursekind where kind_name = ck.getKind_name()  -- #
    根据父类id 获取子类名
    select kind_name from coursekind where pid = (#)
    总结
    select kind_name from coursekind
    where pid = (select kind_id from coursekind where kind_name = ?)
*/