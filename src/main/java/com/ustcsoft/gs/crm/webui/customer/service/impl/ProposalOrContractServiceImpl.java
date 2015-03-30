package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ProposalOrContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.ProposalOrContractDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ProposalOrContractDto;
import com.ustcsoft.gs.crm.webui.customer.service.ProposalOrContractService;

/**
 * class for contract list,delete,add,update and search
 * 
 * @author zhangqiuli
 */
public class ProposalOrContractServiceImpl implements ProposalOrContractService {

    /** log statement */
    private static final Log LOG = LogFactory.getLog(ProposalOrContractServiceImpl.class);
    /** contractDao statement */
    private ProposalOrContractDao proposalOrContractDao = null;

    /**
     * delete proposal Or ContractID
     * 
     * @param proOrConIDs
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteProposalOrContract(final String proOrConIDs) throws CRMDBException {
        LOG.debug("method deleteProposalOrContract start!");
        try {
            proposalOrContractDao.deleteProposalOrContract(proOrConIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteProposalOrContract!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteProposalOrContract end!");
    }

    /**
     * add or update proposalOrContract
     * 
     * @param proOrConDto
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addOrUpdateProposalOrContract(final ProposalOrContractDto proOrConDto)
            throws CRMDBException {
        LOG.debug("method addOrUpdateProposalOrContract start!");
        try {
            if (proOrConDto.getProposalOrContractEditDate().equals("")) {
                proOrConDto.setProposalOrContractEditDate(null);
            }

            proposalOrContractDao.addOrUpdateProposalOrContract(proOrConDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addOrUpdateProposalOrContract!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method addOrUpdateProposalOrContract end!");
    }

    /**
     * get file template name from contractTemplate from db for add and edit
     * contract or proposal
     * 
     * @param type
     *            used to distinguish between proposal and contract
     * @return fileTemplateName proposal or contract's template name
     * @throws CRMDBException
     */
    @Override
    public List<FileTemplateNameBean> getFileTemplateName(final int type) throws CRMDBException {
        LOG.debug("method getFileTemplateName start!");
        try {
            List<FileTemplateNameBean> fileTemplateName = proposalOrContractDao
                    .getFileTemplateName(type);
            LOG.debug("method getFileTemplateName end!");
            return fileTemplateName;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getFileTemplateName!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * for show or search contract or proposal
     * 
     * @param searchFlag
     *            used to distinguish between types of search
     * @param currpage
     *            assigned page
     * @param proOrConSBean
     *            used to store the information search
     * @return map
     * @throws CRMDBException
     */
    @Override
    public Map<String, Object> getAllProposalOrContract(final int searchFlag,
            final ProposalOrContractSearchBean proOrConSBean, final int currpage, final int pageSize)
            throws CRMDBException {
        LOG.debug("method getAllProposalOrContract start!");
        try {
            final Map<String, Object> map = proposalOrContractDao.getAllProposalOrContract(
                    searchFlag, proOrConSBean, currpage, pageSize);
            LOG.debug("method getAllProposalOrContract end!");
            return map;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllProposalOrContract!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * Used to verify whether the contractName only
     * 
     * @param proOrConDto
     *            need to verify information
     * @return NameNotOrExist
     * @throws CRMDBException
     */
    @Override
    public boolean checkProposalOrContractName(final ProposalOrContractDto proOrConDto)
            throws CRMDBException {
        try {
            LOG.debug("method checkContractName start!");
            final boolean NameNotOrExist = proposalOrContractDao
                    .checkProposalOrContractName(proOrConDto);
            LOG.debug("method checkContractName end!");
            return NameNotOrExist;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method checkProposalOrContractName!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * the proposalOrContractDao to set
     * 
     * @param proposalOrContractDao
     */
    public void setProposalOrContractDao(ProposalOrContractDao proposalOrContractDao) {
        this.proposalOrContractDao = proposalOrContractDao;
    }
}