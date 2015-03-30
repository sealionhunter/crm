package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.customer.bean.ContractBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.ContractDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContractDto;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;

/**
 * class for contract list,delete,add,update and search
 * 
 * @author zhangqiuli
 */
public class ContractDaoImpl implements ContractDao {

    private static final Log LOG = LogFactory.getLog(ContractDaoImpl.class);
    private HibernateTemplate hibernateTemplate;

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * delete proposalOrContracts
     * 
     * @param contractIDs
     *            proposalOrContractIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteContract(String contractIDs) throws DataAccessException {
        LOG.debug("method deleteContract start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.DELETE_CONTRACT
                + CRMConstant.LEFT_PARENTHESIS + contractIDs + CRMConstant.RIGHT_PARENTHESIS);
        LOG.debug("method deleteContract end!");
    }

    /**
     * add or update proposalOrContracts
     * 
     * @param contractDto
     *            ProposalOrContractDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void addOrUpdateContract(ContractDto contractDto) throws DataAccessException {
        LOG.debug("method addOrUpdateContract start!");
        hibernateTemplate.saveOrUpdate(contractDto);
        LOG.debug("method addOrUpdateContract end!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Boolean NameOrOrderIdIsExit(String name) {
        LOG.debug("method NameOrOrderIdIsExit start!");
        List<ContractDto> contractList = hibernateTemplate.find(
                " from ContractDto con where con.contractName = ? and con.isAbolished = false",
                name);
        LOG.debug("method NameOrOrderIdIsExit end!");
        return contractList.size() != 0;
    }

    /**
     * get fileTemplateName for contract's add and update
     * 
     * @param type
     *            if type equal 1 ,get contract template name,if type equal 2
     *            ,get proposal template name
     * @return contractTemplateName
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<FileTemplateNameBean> getFileTemplateName(final int type)
            throws DataAccessException {
        LOG.debug("method getFileTemplateName start!");
        List<FileTemplateNameBean> fileTemplateName = hibernateTemplate
                .executeFind(new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) {
                        Query query = session.createQuery(CustomerConstant.GET_FILETEMPLATENAME
                                + type);
                        return query.list();
                    }
                });
        LOG.debug("method getFileTemplateName end!");
        return fileTemplateName;
    }

    /**
     * HibernateTemplate to set
     * 
     * @param hibernateTemplate
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<ContractBean> getAllContract(int searchFlag, ContractSearchBean contractSearchBean,
            int start, int limit) {
        List<ContractBean> contractBeans = new ArrayList<ContractBean>();
        String hqlString;
        if (searchFlag == 0) {
            hqlString = CustomerConstant.GETALL_CONTRACT + CustomerConstant.CONTRACT_ISABOLISHED
                    + " order by contract.contractID desc";
            contractBeans = getListForPage(hqlString, start, limit);
        }
        if (searchFlag == 1) {
            List<Object> paramList = new ArrayList<Object>();
            hqlString = CustomerConstant.GETALL_CONTRACT;
            hqlString = hqlString + CustomerConstant.CONTRACT_ISABOLISHED;
            hqlString = hqlString + " and ( contract.contractName like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString + " or contract.fileTemplateName like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString + " or contract.fileTemplateName like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString + " or contract.payEndTime like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString
                    + " or contract.payType in ( select code.code from CodeDto code where code.code like '00080001%' and length(code.code) = 12 and code.value like ?)";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString
                    + " or contract.orderID in ( select id from OrderDto order where order.orderID like ?)) order by contract.contractID desc";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            Object[] params = paramList.toArray();
            contractBeans = getListForPage(hqlString, params, start, limit);
        }
        if (searchFlag == 2) {
            List<Object> paramList = new ArrayList<Object>();
            hqlString = CustomerConstant.GETALL_CONTRACT;
            hqlString = hqlString + CustomerConstant.CONTRACT_ISABOLISHED;
            if (!StringUtils.isBlank(contractSearchBean.getPayEndTimeMin())) {
                hqlString = hqlString + " and contract.payEndTime >= ?";
                paramList.add(contractSearchBean.getPayEndTimeMin());
            }
            if (!StringUtils.isBlank(contractSearchBean.getContractName())) {
                hqlString = hqlString + " and contract.contractName like ?";
                paramList.add("%" + contractSearchBean.getContractName() + "%");
            }
            if (!StringUtils.isBlank(contractSearchBean.getFileTemplateName())) {
                hqlString = hqlString + " and contract.fileTemplateName like ?";
                paramList.add("%" + contractSearchBean.getFileTemplateName() + "%");
            }
            if (!StringUtils.isBlank(contractSearchBean.getPayType())
                    && !"0".equals(contractSearchBean.getPayType())) {
                hqlString = hqlString + " and contract.payType = ?";
                paramList.add(contractSearchBean.getPayType());
            }
            if (!StringUtils.isBlank(contractSearchBean.getPayEndTimeMax())) {
                hqlString = hqlString + " and contract.payEndTime <= ?";
                paramList.add(contractSearchBean.getPayEndTimeMax());
            }
            if (!StringUtils.isBlank(contractSearchBean.getOrderID())) {
                hqlString = hqlString
                        + " and contract.orderID in (select id from OrderDto order where order.orderID like ?"
                        + ")";
                paramList.add("%" + contractSearchBean.getOrderID() + "%");
            }
            hqlString += " order by contract.contractID desc";
            Object[] params = paramList.toArray();
            contractBeans = getListForPage(hqlString, params, start, limit);
        }
        return contractBeans;
    }

    @SuppressWarnings("unchecked")
    public List<ContractBean> getListForPage(final String hql, final Object[] params,
            final int start, final int limit) {
        List<ContractBean> list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                int i = 0;
                for (Object param : params) {
                    query.setParameter(i, param);
                    i++;
                }
                query.setFirstResult(start);
                query.setMaxResults(limit);
                List<ContractBean> list = query.list();
                return list;
            }
        });
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<ContractBean> getListForPage(final String hql, final int start, final int limit) {
        List<ContractBean> list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                query.setFirstResult(start);
                query.setMaxResults(limit);
                List<ProductDto> list = query.list();
                return list;
            }
        });
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getAllContractCount(int searchFlag, ContractSearchBean contractSearchBean,
            int start, int limit) {
        int count = 0;
        String hqlString;
        if (searchFlag == 0) {
            List<Integer> countList = hibernateTemplate.find(CustomerConstant.GETALL_CONTRACT_COUNT
                    + CustomerConstant.CONTRACT_ISABOLISHED);
            Number number = countList.get(0);
            count = number.intValue();
        }
        if (searchFlag == 1) {
            List<Object> paramList = new ArrayList<Object>();
            hqlString = CustomerConstant.GETALL_CONTRACT_COUNT;
            hqlString = hqlString + CustomerConstant.CONTRACT_ISABOLISHED;
            hqlString = hqlString + " and ( contract.contractName like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString + " or contract.fileTemplateName like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString + " or contract.fileTemplateName like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString + " or contract.payEndTime like ?";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString
                    + " or contract.payType in ( select code.code from CodeDto code where code.code like '00080001%' and length(code.code) = 12 and code.value like ?)";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            hqlString = hqlString
                    + " or contract.orderID in ( select id from OrderDto order where order.orderID like ?))";
            paramList.add("%" + contractSearchBean.getTextValue() + "%");
            Object[] params = paramList.toArray();
            List<Integer> countList = hibernateTemplate.find(hqlString, params);
            Number number = countList.get(0);
            count = number.intValue();
        }
        if (searchFlag == 2) {
            List<Object> paramList = new ArrayList<Object>();
            hqlString = CustomerConstant.GETALL_CONTRACT_COUNT;
            hqlString = hqlString + CustomerConstant.CONTRACT_ISABOLISHED;
            if (!"".equals(contractSearchBean.getPayEndTimeMin())
                    && contractSearchBean.getPayEndTimeMin() != null) {
                hqlString = hqlString + " and contract.payEndTime >= ?";
                paramList.add(contractSearchBean.getPayEndTimeMin());
            }
            if (!"".equals(contractSearchBean.getContractName())
                    && contractSearchBean.getContractName() != null) {
                hqlString = hqlString + " and contract.contractName like ?";
                paramList.add("%" + contractSearchBean.getContractName() + "%");
            }
            if (!"".equals(contractSearchBean.getFileTemplateName())
                    && contractSearchBean.getFileTemplateName() != null) {
                hqlString = hqlString + " and contract.fileTemplateName like ?";
                paramList.add("%" + contractSearchBean.getFileTemplateName() + "%");
            }
            if (!"".equals(contractSearchBean.getPayType())
                    && contractSearchBean.getPayType() != null
                    && !"0".equals(contractSearchBean.getPayType())) {
                hqlString = hqlString + " and contract.payType = ?";
                paramList.add(contractSearchBean.getPayType());
            }
            if (!"".equals(contractSearchBean.getPayEndTimeMax())
                    && contractSearchBean.getPayEndTimeMax() != null) {
                hqlString = hqlString + " and contract.payEndTime <= ?";
                paramList.add(contractSearchBean.getPayEndTimeMax());
            }
            if (!"".equals(contractSearchBean.getOrderID())
                    && contractSearchBean.getOrderID() != null) {
                hqlString = hqlString
                        + " and contract.orderID in (select id from OrderDto order where order.orderID like ?"
                        + ")";
                paramList.add("%" + contractSearchBean.getOrderID() + "%");
            }
            Object[] params = paramList.toArray();
            List<Integer> countList = hibernateTemplate.find(hqlString, params);
            Number number = countList.get(0);
            count = number.intValue();
        }
        return count;
    }
}
