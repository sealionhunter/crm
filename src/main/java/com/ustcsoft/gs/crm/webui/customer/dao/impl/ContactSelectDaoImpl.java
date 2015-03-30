package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactSelectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactSeleteBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.ContactSelectDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactSelectDto;

/**
 * Description: The class is working with DB{add, edit and delete}.
 * 
 * @author libaoshan
 * 
 */
public class ContactSelectDaoImpl implements ContactSelectDao {

    private static final Log LOG = LogFactory.getLog(ContactSelectDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * Get all ContactSelect
     * 
     * @param searchFlag
     *            0 is show list without conditions
     * @param searchBean
     *            is conditions
     * @param page
     *            is current page
     * @param limit
     *            is pageSize
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getContactSelect(final int searchFlag,
            final ContactSelectSearchBean searchBean, final int page, final int limit)
            throws DataAccessException {
        LOG.debug("method getContactSelect start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        String[] paramNames = { "flag", "objectID" };
        Object[] values = { searchBean.getObjectFlag(), searchBean.getObjectID() };
        List<ContactSeleteBean> contactSeleteBean = hibernateTemplate
                .executeFind(new PagingHibernateCallback(CustomerConstant.CONTACTSELECT_HQL, page,
                        paramNames, values, limit));
        total = getContactSelectTotal(paramNames, values);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, contactSeleteBean);
        LOG.debug("method getContactSelect end!");
        return map;
    }

    /**
     * Add ContactSelect
     * 
     * @param contactSelectDto
     *            entity object need add
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void saveContactSelect(final ContactSelectDto contactSelectDto)
            throws DataAccessException {
        LOG.debug("method saveContactSelect start!");
        hibernateTemplate.saveOrUpdate(contactSelectDto);
        LOG.debug("method saveContactSelect end!");
    }

    /**
     * Delete ContactSelect
     * 
     * @param contactSelectIDs
     *            need delete
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteContactSelect(final String contactSelectIDs) throws DataAccessException {
        LOG.debug("method deleteContactSelect start!");
        List<ContactSelectDto> contactSelectDtoList = new ArrayList<ContactSelectDto>();
        String[] contactSelectID = contactSelectIDs.split(", ");
        for (String element : contactSelectID) {
            ContactSelectDto contactSelectDto = new ContactSelectDto();
            contactSelectDto.setContactSelectID(Integer.valueOf(element));
            contactSelectDtoList.add(contactSelectDto);
        }
        hibernateTemplate.deleteAll(contactSelectDtoList);
        LOG.debug("method deleteContactSelect end!");
    }

    /**
     * Get the contactSelect total
     * 
     * @param paramNames
     *            the params name
     * @param values
     *            the params value
     * 
     * @return (Long) list.get(0){total}
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public long getContactSelectTotal(String[] paramNames, Object[] values)
            throws DataAccessException {
        LOG.debug("method getContactSelectTotal start!");
        List<Long> list = hibernateTemplate.findByNamedParam(
                CustomerConstant.CONTACTSELECT_COUNT_HQL, paramNames, values);
        LOG.debug("method getContactSelectTotal end!");
        return list.get(0);
    }

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}
