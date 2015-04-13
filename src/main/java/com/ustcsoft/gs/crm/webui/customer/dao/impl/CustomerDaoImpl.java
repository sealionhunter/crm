/*
 * Class name: CustomerDaoImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CountBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CustomerDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;

/**
 * Deal with the database operations on the CustomerInfo table.
 * 
 * @author tangyunpeng and xujueyin
 * 
 */
public class CustomerDaoImpl implements CustomerDao {

    private static final Log LOG = LogFactory.getLog(CustomerDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * get customer by page
     * 
     * @param currPage
     *            the current page.
     * @param userID
     * @return map the customer list by page.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAllCustomer(final int currPage, final int pageSize,
            final Integer[] userID) {

        LOG.debug("method getAllCustomer start");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CustomerBean> customerList = hibernateTemplate
                .executeFind(new PagingHibernateCallback(CustomerConstant.CUS_GET_ALL_HQL
                        + CustomerConstant.HOLDER_USER_ID, currPage, CustomerConstant.USER_ID,
                        userID, pageSize));
        map.put(CRMConstant.ITEMS, customerList);
        map.put(CRMConstant.TOTAL,
                (int) getCustomerSize(CustomerConstant.CUSTOMER_COUNT_HQL
                        + CustomerConstant.HOLDER_USER_ID, userID));
        LOG.debug("method getAllCustomer end");
        return map;
    }

    /**
     * find all activity member
     * 
     * @return activityMember
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String> findActivityMember() {

        LOG.debug("method findActivityMember start");
        List<String> activityMember = hibernateTemplate
                .find("select aid.activityMember from ActivityInfoDto aid where aid.isAbolished=0");
        LOG.debug("method findActivityMember end.");
        return activityMember;
    }

    /**
     * get using cnt
     * 
     * @param customerID
     * @return map
     */
    @Override
    public Map<String, Object> getUsingCnt(int customerID) {
        LOG.debug("method getUsingCnt start.");
        Map<String, Object> map = new HashMap<String, Object>();
        long cnt = (Long) hibernateTemplate.find(
                "select count(*) from ContactTrackInfoDto ctid where ctid.isAbolished=0 and ctid.customerID ="
                        + customerID).get(0);
        map.put("ContactTrackInfoDto", cnt);
        // cnt = (Long) hibernateTemplate.find(
        // "select count(*) from OrderDto od where od.isAbolished=0 and od.type=1 and od.customerID ="
        // + customerID).get(0);
        // map.put("OrderDto_1", cnt);
        // cnt = (Long) hibernateTemplate.find(
        // "select count(*) from OrderDto od where od.isAbolished=0 and od.type=0 and od.customerID ="
        // + customerID).get(0);
        // map.put("OrderDto_0", cnt);
        // cnt = (Long) hibernateTemplate.find(
        // "select count(*) from SalesEventDto sed where sed.isAbolished=0 and sed.customerID ="
        // + customerID).get(0);
        // map.put("SalesEventDto", cnt);
        LOG.debug("method getUsingCnt end.");
        return map;
    }

    /**
     * save or update customer information.
     * 
     * @param customerDto
     *            update object.
     */
    @Override
    public void updateCustomer(CustomerDto customerDto) {
        LOG.debug("method updateCustomer start");
        hibernateTemplate.saveOrUpdate(customerDto);
        LOG.debug("method updateCustomer end");

    }

    /**
     * get the number of records.
     * 
     * @param queryString
     * @return long
     */
    @Override
    public long getCustomerSize(String queryString, Integer[] userID) {
        List<?> result = hibernateTemplate.findByNamedParam(queryString, CustomerConstant.USER_ID,
                userID);
        return (Long) result.get(0);
    }

    /**
     * super and query customer from database.
     * 
     * @param searchFlag
     *            int remark simple or super query
     * @param searchValue
     *            CustomerSearchBean search conditions
     * @param currPage
     *            int the current page
     * @return Map<String, Object> the customer query result list for page
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> queryCustomer(int searchFlag, final CustomerSearchBean searchValue,
            int currPage, final int pageSize) {
        LOG.debug("method queryCustomer start");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CustomerBean> result = null;
        long total = 0;
        /*
         * customer simple query
         */
        if (searchFlag == 1) {
            // get customer records list
            result = hibernateTemplate.executeFind(new SuperHibernateCallback(
                    CustomerConstant.CUS_GET_ALL_HQL + CustomerConstant.CUSTOMER_SIMPLE_AFTER_HQL,
                    currPage, searchValue, pageSize));
            // get customer records total number
            total = (Long) hibernateTemplate.executeFind(
                    new SuperHibernateCallback(CustomerConstant.CUS_COUNT_BEFORE_HQL
                            + CustomerConstant.CUSTOMER_SIMPLE_AFTER_HQL, 0, searchValue, 0))
                    .get(0);
        }

        /*
         * customer super query
         */
        if (searchFlag == 2) {
            StringBuffer hqlList = new StringBuffer(CustomerConstant.CUS_GET_ALL_HQL
                    + CustomerConstant.HOLDER_USER_ID);
            hqlList.append(CustomerConstant.CUSTOMER_MID_SEARCH);
            StringBuffer hqlCount = new StringBuffer(CustomerConstant.CUS_COUNT_BEFORE_HQL
                    + CustomerConstant.HOLDER_USER_ID);
            hqlCount.append(CustomerConstant.CUSTOMER_MID_SEARCH);

            /*
             * generate query conditions and set parameter value to list of in
             */
            if ("".equals(searchValue.getEarning())) {
                searchValue.setEarning("%");
            }
            if (!"".equalsIgnoreCase(searchValue.getHolder())) {
                searchValue.setHolder(CRMUtils.trimSearch(searchValue.getHolder()));
                hqlList.append(CustomerConstant.SELECT__HOLDER);
                hqlCount.append(CustomerConstant.SELECT__HOLDER);
            }
            if (!(searchValue.getScale().length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(searchValue.getScale()[0]))) {
                hqlList.append(CustomerConstant.CUS_SCALE_IN);
                hqlCount.append(CustomerConstant.CUS_SCALE_IN);
            }
            if (!(searchValue.getIndustry().length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(searchValue.getIndustry()[0]))) {
                hqlList.append(CustomerConstant.CUS_INDUSTRY_IN);
                hqlCount.append(CustomerConstant.CUS_INDUSTRY_IN);
            }
            if (!(searchValue.getFee().length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(searchValue.getFee()[0]))) {
                hqlList.append(CustomerConstant.CUS_FEE_IN);
                hqlCount.append(CustomerConstant.CUS_FEE_IN);
            }

            // get customer records list
            result = hibernateTemplate.executeFind(new SuperHibernateCallback(hqlList.toString(),
                    currPage, searchValue, pageSize));
            // get customer records total number
            total = (Long) hibernateTemplate.executeFind(
                    new SuperHibernateCallback(hqlCount.toString(), 0, searchValue, pageSize)).get(
                    0);
        }
        map.put(CRMConstant.ITEMS, result);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method queryCustomer end");
        return map;
    }

    /**
     * 
     * @param customerDto
     * @return true
     */
    @Override
    public boolean judgeCustomerName(CustomerDto customerDto) {

        LOG.debug("method judgeCustomerName start");
        String[] paramNames = { CustomerConstant.CUSTOMER_NAME, CustomerConstant.CUSTOMER_ID };
        Object[] values = { customerDto.getCustomerName(), customerDto.getCustomerID() };
        long count = (Long) hibernateTemplate.findByNamedParam(
                CustomerConstant.CUS_CUSTOMERNAME_COUNT, paramNames, values).get(0);
        LOG.debug("method judgeCustomerName end");
        return count > 0;
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

    /**
     * delete customer and cooperator resume
     * 
     * @param customerIDs
     */
    @Override
    public void deleteCustomer(String customerIDs) {
        LOG.debug("method deleteCustomer start");
        hibernateTemplate.bulkUpdate(CustomerConstant.CUSTOMER_DEL_HQL + customerIDs);
        // hibernateTemplate.bulkUpdate(CustomerConstant.CUS_COOP_DEL_HQL +
        // customerIDs);
        hibernateTemplate.bulkUpdate(CustomerConstant.CUS_WORK_DEL_HQL + customerIDs);
        // hibernateTemplate.bulkUpdate(CustomerConstant.CUS_COURSE_DEL_HQL +
        // customerIDs);
        // hibernateTemplate.bulkUpdate(CustomerConstant.CUS_CONTACT_SELECT_DEL_HQL
        // + customerIDs);

        hibernateTemplate.bulkUpdate(CustomerConstant.CUS_CONTACT_DEL_HQL + customerIDs);
        hibernateTemplate.bulkUpdate(CustomerConstant.CUS_CONTACTTRACK_DEL_HQL + customerIDs);
        hibernateTemplate.bulkUpdate(CustomerConstant.CUS_LEADER_DEL_HQL + customerIDs);
        LOG.debug("method deleteCustomer end");
    }

    /**
     * 
     * @param customerID
     * @return String
     */
    @SuppressWarnings("unchecked")
    @Override
    public String getCusNameByID(int customerID) {
        LOG.debug("method getCusNameByID start.");
        List<String> cusNameList = hibernateTemplate
                .find("select cd.customerName from CustomerDto cd where cd.customerID ="
                        + customerID);
        if (cusNameList.size() > 0) {
            LOG.debug("method getCusNameByID end.");
            return cusNameList.get(0);
        }
        LOG.debug("method getCusNameByID end.");
        return "";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllCustomerName(int customerID) {
        LOG.debug("method getAllCustomerName start.");
        List<String> nameList = hibernateTemplate
                .find("select cus.customerName from CustomerDto as cus where cus.customerID != "
                        + customerID + " and isAbolished = 0");
        LOG.debug("method getAllCustomerName end.");
        return nameList;
    }

    @Override
    public boolean isHolderExist(int customerID) {
        LOG.debug("method judgeCustomerName start");
        int holder = (int) hibernateTemplate.findByNamedParam(
                "select cus.holder from CustomerDto cus where cus.customerID = :customerID",
                "customerID", customerID).get(0);
        LOG.debug("method judgeCustomerName end");
        return holder > 0;
    }

    @Override
    public void receiveCustomer(int customerID, int userID) {
        LOG.debug("method judgeCustomerName start");
        CustomerDto dto = (CustomerDto) hibernateTemplate.get(CustomerDto.class, customerID);
        dto.setHolder(userID);
        hibernateTemplate.update(dto);
        LOG.debug("method judgeCustomerName end");
    }

    @SuppressWarnings("unchecked")
    public CustomerBean findCustomerById(int id) {
        LOG.debug("method findCustomerById start.");
        String hql = CustomerConstant.CUS_GET_ALL_HQL + CustomerConstant.CUS_GET_BY_ID;
        List<Map<String, Object>> mapList = hibernateTemplate.find(hql, id);
        CustomerBean bean = null;
        if (mapList == null || mapList.isEmpty()) {
            return bean;
        } else {
            bean = initCustomerBean(mapList.get(0));
        }
        LOG.debug("method findCustomerById end.");

        return bean;
    }

    @SuppressWarnings("unchecked")
    public List<CustomerBean> findCustomerListByIds(String ids) {
        LOG.debug("method findCustomerListByIds start.");
        List<Map<String, Object>> list = null;
        List<CustomerBean> beanList = new ArrayList<CustomerBean>();
        if (ids != null && !ids.isEmpty()) {
            String hql = CustomerConstant.CUS_GET_ALL_HQL + CustomerConstant.CUS_GET_IN_IDS + "("
                    + ids + ")";

            list = hibernateTemplate.find(hql);
            for (Map<String, Object> map : list) {
                beanList.add(initCustomerBean(map));
            }
        }
        LOG.debug("method findCustomerListByIds end.");
        return beanList;
    }

    private static CustomerBean initCustomerBean(Map<String, Object> map) {
        CustomerBean bean = new CustomerBean();
        bean.setBusiness1((String) map.get("business1"));
        bean.setBusiness2((String) map.get("business2"));
        bean.setBusiness3((String) map.get("business3"));
        bean.setBusiness4((String) map.get("business4"));
        bean.setBusinessUnit((String) map.get("businessUnit"));
        bean.setCreateTime((String) map.get("createTime"));
        bean.setCustomerAddr((String) map.get("customerAddr"));
        bean.setCustomerID((int) map.get("customerID"));
        bean.setCustomerName((String) map.get("customerName"));
        bean.setCustomerStatement((String) map.get("customerStatement"));
        bean.setCustomerStatementName((String) map.get("customerStatementName"));
        bean.setCustomerType((String) map.get("customerType"));
        bean.setCustomerTypeName((String) map.get("customerTypeName"));
        bean.setDescriptions((String) map.get("descriptions"));
        bean.setEarning((String) map.get("earning"));
        bean.setFee((String) map.get("fee"));
        bean.setFeeName((String) map.get("feeName"));
        bean.setHolder((int) map.get("holder"));
        bean.setHolderName((String) map.get("holderName"));
        bean.setIndustry((String) map.get("industry"));
        bean.setIndustryName((String) map.get("industryName"));
        bean.setNumber((int) map.get("number"));
        bean.setScale((String) map.get("scale"));
        bean.setScaleName((String) map.get("scaleName"));
        bean.setUpdateTime((String) map.get("updateTime"));
        bean.setValueEvaluate((String) map.get("valueEvaluate"));
        bean.setValueEvaluateName((String) map.get("valueEvaluateName"));

        return bean;
    }

    @Override
    public Map<String, Object> getCustomerCount(String statisticsType, int userID) {

        Map<String, Object> map = new HashMap<String, Object>();
        String sql = null;
        switch (statisticsType) {
        case "002000020001":
            sql = "select new com.ustcsoft.gs.crm.webui.customer.bean.CountBean(code.value, code.code, COUNT(*))"
                    + "from CustomerDto c, CodeDto code where code.code = c.customerStatement and c.isAbolished = 0 group by code.value, code.code order by code.code";
            break;
        case "002000020002":
            sql = "select new com.ustcsoft.gs.crm.webui.customer.bean.CountBean(code.value, code.code, COUNT(*))"
                    + " from CustomerDto c, CodeDto code where code.code = c.businessUnit and c.isAbolished = 0 group by code.value, code.code order by code.code";
            break;
        case "002000020003":
            sql = "select new com.ustcsoft.gs.crm.webui.customer.bean.CountBean(code.value, code.code, COUNT(*))"
                    + " from CustomerDto c, CodeDto code where code.code = c.industry and c.isAbolished = 0 group by code.value, code.code order by code.code";
            break;
        default:
            break;
        }
        List<CountBean> nameList = hibernateTemplate.find(sql);
        map.put(CRMConstant.ITEMS, nameList);
        return map;
    }
}
