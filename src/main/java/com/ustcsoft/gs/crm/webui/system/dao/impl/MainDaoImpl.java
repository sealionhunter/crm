package com.ustcsoft.gs.crm.webui.system.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.MainDao;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author zhouzhou
 * 
 */
public class MainDaoImpl implements MainDao {

    /** used for getting log */
    private static final Log LOG = LogFactory.getLog(LoginDaoImpl.class);

    /** used for getting class hibernateTemplate */
    private HibernateTemplate hibernateTemplate;

    /**
     * method used to change password by userid
     * 
     * @param userID
     * @param password
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void changePasswordByUserID(final int userID, final String password)
            throws DataAccessException {
        LOG.debug("method saveOrUpdate in MainDaoImpl start");
        final String countSql = SystemConstant.UPDATE_PWD_ID_HQL;
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(countSql);
                query.setString(0, password);
                query.setInteger(1, userID);
                return query.executeUpdate();
            }
        });
        LOG.debug("method saveOrUpdate in MainDaoImpl end");
    }

    /**
     * method used to get userinfodto by userid
     * 
     * @param userID
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public UserInfoDto findByUserID(final int userID) {
        LOG.debug("method findByUserID in MainDaoImpl start");
        final String countSql = SystemConstant.GET_PWD_ID_HQL;
        List<UserInfoDto> userinfoDtoList = hibernateTemplate
                .executeFind(new HibernateCallback<Object>() {
                    @Override
                    public Object doInHibernate(Session session) {
                        Query query = session.createQuery(countSql);
                        query.setInteger(0, userID);
                        return query.list();
                    }
                });
        UserInfoDto userinfoDto = null;
        if (userinfoDtoList.size() != 0) {
            userinfoDto = userinfoDtoList.get(0);
        }
        LOG.debug("method findByUserID in MainDaoImpl end");
        return userinfoDto;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
