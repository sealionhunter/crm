package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ProposalOrContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.ProposalOrContractDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ProposalOrContractDto;

/**
 * class for contract list,delete,add,update and search
 * 
 * @author zhangqiuli
 */
public class ProposalOrContractDaoImpl implements ProposalOrContractDao {

    private static final Log LOG = LogFactory.getLog(ProposalOrContractDaoImpl.class);
    private HibernateTemplate hibernateTemplate;

    /**
     * delete proposalOrContracts
     * 
     * @param proposalOrContractIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteProposalOrContract(String proposalOrContractIDs) throws DataAccessException {
        LOG.debug("method deleteContract start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.DELETE_CONTRACTORPROPOSAL
                + CRMConstant.LEFT_PARENTHESIS + proposalOrContractIDs
                + CRMConstant.RIGHT_PARENTHESIS);
        LOG.debug("method deleteContract end!");
    }

    /**
     * add or update proposalOrContracts
     * 
     * @param proposalOrContractDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void addOrUpdateProposalOrContract(ProposalOrContractDto proposalOrContractDto)
            throws DataAccessException {
        LOG.debug("method addOrUpdateContract start!");
        hibernateTemplate.saveOrUpdate(proposalOrContractDto);
        LOG.debug("method addOrUpdateContract end!");
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
     * proposal or contract list when searchFlag equals 0 , searchFlag equals 1
     * or 2 ,search contracts
     * 
     * @param searchFlag
     *            searchFlag = 0 : show list , searchFlag = 1 : simple search ,
     *            searchFlag = 2 : super search
     * @param proposalOrContractSearchBean
     * @param currpage
     * @return map
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> getAllProposalOrContract(final int searchFlag,
            final ProposalOrContractSearchBean proposalOrContractSearchBean, final int currpage,
            final int pageSize) throws DataAccessException {
        LOG.debug("method getAllProposalOrContract start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long count = 0;
        List<ProposalOrContractDto> proposalOrContractList = new ArrayList<ProposalOrContractDto>();
        if (searchFlag == 0) {
            proposalOrContractList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.GET_CONTRACTORPROPOSAL_HQL, currpage, "type",
                    proposalOrContractSearchBean.getType(), pageSize));
            count = (Long) hibernateTemplate.find(CustomerConstant.GET_PRO_CON_COUNT_HQL,
                    proposalOrContractSearchBean.getType()).get(0);
        } else if (searchFlag == 1) {
            final String textValue = proposalOrContractSearchBean.getTextValue();
            final String value = textValue;
            final String[] paramNames = { "searchText", "type" };
            final Object[] values = { value, proposalOrContractSearchBean.getType() };
            proposalOrContractList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.SIMPLE_CONTRACTORPROPOSAL_SEARCH, currpage, paramNames,
                    values, pageSize));
            count = (Long) hibernateTemplate.findByNamedParam(
                    CustomerConstant.COUNT + CustomerConstant.SIMPLE_CONTRACTORPROPOSAL_SEARCH,
                    paramNames, values).get(0);
        } else {
            String proposalOrContractAddDateMax = proposalOrContractSearchBean
                    .getProposalOrContractAddDateMax();
            String proposalOrContractAddDateMin = proposalOrContractSearchBean
                    .getProposalOrContractAddDateMin();
            String proposalOrContractEditDateMax = proposalOrContractSearchBean
                    .getProposalOrContractEditDateMax();
            String proposalOrContractEditDateMin = proposalOrContractSearchBean
                    .getProposalOrContractEditDateMin();
            StringBuffer searchContract_HQL = new StringBuffer(
                    CustomerConstant.SUPER_CONTRACTORPROPOSAL_SEARCH);

            if (!proposalOrContractAddDateMin.isEmpty() && !proposalOrContractAddDateMax.isEmpty()) {
                searchContract_HQL.append(CustomerConstant.PORC_ADDDATEBETWEEN);
            } else if (!proposalOrContractAddDateMin.isEmpty()
                    && proposalOrContractAddDateMax.isEmpty()) {
                searchContract_HQL.append(CustomerConstant.PORC_ADDDATESTART);
            } else if (proposalOrContractAddDateMin.isEmpty()
                    && !proposalOrContractAddDateMax.isEmpty()) {
                searchContract_HQL.append(CustomerConstant.PORC_ADDDATEEND);
            }

            if (!proposalOrContractEditDateMin.isEmpty()
                    && !proposalOrContractEditDateMax.isEmpty()) {
                searchContract_HQL.append(CustomerConstant.PORC_EDITDATEBETWEEN);
            } else if (!proposalOrContractEditDateMin.isEmpty()
                    && proposalOrContractEditDateMax.isEmpty()) {
                searchContract_HQL.append(CustomerConstant.PORC_EDITDATESTART);
            } else if (proposalOrContractEditDateMin.isEmpty()
                    && !proposalOrContractEditDateMax.isEmpty()) {
                searchContract_HQL.append(CustomerConstant.PORC_EDITDATEEND);
            }

            proposalOrContractList = hibernateTemplate
                    .executeFind(new SuperHibernateCallback(searchContract_HQL.toString(),
                            currpage, proposalOrContractSearchBean, pageSize));

            count = (Long) hibernateTemplate.executeFind(
                    new SuperHibernateCallback(CustomerConstant.COUNT
                            + searchContract_HQL.toString(), 0, proposalOrContractSearchBean,
                            pageSize)).get(0);
        }
        map.put(CRMConstant.TOTAL, count);
        map.put(CRMConstant.ITEMS, proposalOrContractList);
        LOG.debug("method getAllProposalOrContract end!");
        return map;
    }

    /**
     * for check proposalOrContractName
     * 
     * @return count == 0 if count == 0 : DB not exist a name equal this one, if
     *         count != 0 : DB exist at least one
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean checkProposalOrContractName(ProposalOrContractDto proposalOrContract)
            throws DataAccessException {
        LOG.debug("method checkProposalOrContractName start!");
        Object[] values = { proposalOrContract.getType(),
                proposalOrContract.getProposalOrContractID(),
                proposalOrContract.getProposalOrContractName() };
        long count = (Long) hibernateTemplate.find(CustomerConstant.COUNT_CONTRACTORPROPOSALNAME,
                values).get(0);
        LOG.debug("method checkProposalOrContractName end!");
        return count == 0;
    }

    /**
     * HibernateTemplate to set
     * 
     * @param hibernateTemplate
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
