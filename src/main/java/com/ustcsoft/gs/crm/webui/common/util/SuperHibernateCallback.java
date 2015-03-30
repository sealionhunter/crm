package com.ustcsoft.gs.crm.webui.common.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SuperHibernateCallback implements HibernateCallback<List<?>> {
    private String hql;
    private int currPage;
    private Object valueBean;
    private int pageSize = 25;

    public SuperHibernateCallback(String hql, int currPage, Object valueBean, int pageSize) {

        this.hql = hql;
        this.currPage = currPage;
        this.valueBean = valueBean;
        this.pageSize = pageSize;
    }

    @Override
    public List<?> doInHibernate(Session session) throws HibernateException, SQLException {
        List<?> list = null;

        Query query = session.createQuery(hql);
        query.setProperties(valueBean);
        if (currPage != 0) {
            query.setFirstResult((currPage - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        list = query.list();

        return list;
    }
}
