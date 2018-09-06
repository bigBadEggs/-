package org.lanqiao.dao;

import org.lanqiao.entity.Period;

import java.util.List;

public class PeriodDaoImpl extends BaseDao<Period> implements PeriodDao {

    @Override
    public void insert(String sql, Object[] obj) {

    }

    @Override
    public int delete(Period p) {
        return executeUpdate("delete from period where id = ?", new Object[]{p.getId()});
    }

    @Override
    public void update(String sql, Object[] obj) {

    }

    @Override
    public List<Period> select(Period p) {
        return executeQuery("select id, period_name, video_address, course_id from period where course_id = ?",
                new Object[]{p.getCourse_id()});
    }
}
/*
    select * from period where course_id =
*/