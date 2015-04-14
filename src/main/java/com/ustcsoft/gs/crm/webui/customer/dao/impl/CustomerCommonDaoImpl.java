package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.customer.dao.CustomerCommonDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;

public class CustomerCommonDaoImpl implements CustomerCommonDao {

    private static final String FREEABLE_CUSTOMER = "select cus from CustomerDto cus where cus.updateTime <= CONVERT(varchar(19), getdate() - 90, 120) and cus.holder != 0 and cus.isAbolished = false";
    private static final Log LOG = LogFactory.getLog(ContactTrackDaoImpl.class);
    private HibernateTemplate hibernateTemplate;

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void freeCustomer() {
        @SuppressWarnings("unchecked")
        List<CustomerDto> list = hibernateTemplate
                .find(FREEABLE_CUSTOMER);

        for (CustomerDto dto : list) {
            dto.setOldHolder(dto.getHolder());
            dto.setHolder(0);
            hibernateTemplate.update(dto);;
            LOG.info("free customer " + dto.getCustomerName());
        }
    }
}
