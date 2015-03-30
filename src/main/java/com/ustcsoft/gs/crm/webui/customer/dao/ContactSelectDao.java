package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.customer.bean.ContactSelectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactSelectDto;

/**
 * Description: The interface is working with DB{add, edit and delete}.
 * 
 * @author libaoshan
 * 
 */
public interface ContactSelectDao {

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
    public Map<String, Object> getContactSelect(int searchFlag, ContactSelectSearchBean searchBean,
            int page, int limit) throws DataAccessException;

    /**
     * Add ContactSelect
     * 
     * @param contactSelectDto
     *            entity object need add
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void saveContactSelect(ContactSelectDto contactSelectDto) throws DataAccessException;

    /**
     * Delete ContactSelect
     * 
     * @param contactSelectIDs
     *            need delete
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteContactSelect(String contactSelectIDs) throws DataAccessException;

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
    public long getContactSelectTotal(String[] paramNames, Object[] values)
            throws DataAccessException;

}