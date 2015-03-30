package com.ustcsoft.gs.crm.webui.common.util;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PagingHibernateCallback implements HibernateCallback<List<?>> {
    private String hql;
    private int currPage;
    private String[] paramNames;
    private Object[] values;
    private int pageSize;

    public PagingHibernateCallback(String hql, int currPage, int pageSize) {
        this.hql = hql;
        this.currPage = currPage;
        this.pageSize = pageSize;
    }

    public PagingHibernateCallback(String hql, int currPage, String[] paramNames, Object[] values,
            int pageSize) {

        this.hql = hql;
        this.currPage = currPage;
        this.paramNames = paramNames;
        this.values = values;
        this.pageSize = pageSize;
    }

    public PagingHibernateCallback(String hql, int currPage, String paramName, Object value,
            int pageSize) {
        this(hql, currPage, new String[] { paramName }, new Object[] { value }, pageSize);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List<?> doInHibernate(Session session) throws HibernateException, SQLException {
        List<?> list = null;
        Query query = session.createQuery(hql);
        if (values != null) {
            if (paramNames.length != values.length) {
                throw new IllegalArgumentException(
                        "Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < values.length; i++) {
                Object value = values[i];
                String paramName = paramNames[i];
                if (value instanceof Collection) {
                    query.setParameterList(paramName, (Collection) value);
                } else if (value instanceof Object[]) {
                    query.setParameterList(paramName, (Object[]) value);
                } else {
                    query.setParameter(paramName, value);
                }
            }
        }
        if (currPage != 0) {
            query.setFirstResult((currPage - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        list = query.list();

        return list;
    }
}
