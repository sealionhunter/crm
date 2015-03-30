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
import com.ustcsoft.gs.crm.webui.system.dao.LoginDao;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author zhouzhou
 * 
 */
public class LoginDaoImpl implements LoginDao {

    /** used for getting log */
    private static final Log LOG = LogFactory.getLog(LoginDaoImpl.class);

    /** used for getting class hibernateTemplate */
    private HibernateTemplate hibernateTemplate;

    /**
     * method to find userinfo by username and password
     * 
     * @param userName
     * @param password
     * @return UserInfoDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public UserInfoDto findByUsernamePassword(String userName, String password)
            throws DataAccessException {
        LOG.debug("method findUserByPwdAndName start!");
        final String un = userName;
        final String pw = password;
        final String countSql = SystemConstant.LOGIN_HQL;
        List<UserInfoDto> userinfoDtoList = hibernateTemplate
                .executeFind(new HibernateCallback<Object>() {
                    @Override
                    public Object doInHibernate(Session session) {
                        Query query = session.createQuery(countSql);
                        query.setString(0, un);
                        query.setString(1, pw);
                        return query.list();
                    }
                });
        UserInfoDto userinfoDto = null;
        if (userinfoDtoList.size() != 0) {
            userinfoDto = userinfoDtoList.get(0);
        }
        LOG.debug("method findUserByPwdAndName end!");
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
