package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ProposalOrContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ProposalOrContractDto;

/**
 * service interface
 * 
 * @author zhangqiuli
 */
public interface ProposalOrContractService {

    /**
     * according to proposalOrContractID to delete proposalOrContract
     * 
     * @param proOrConIDs
     *            proposalOrContractIDs
     * @throws CRMDBException
     */
    public void deleteProposalOrContract(final String proOrConIDs) throws CRMDBException;

    /**
     * add or update proposalOrContract
     * 
     * @param proOrCon
     *            ProposalOrContractDto
     * @throws CRMDBException
     */
    public void addOrUpdateProposalOrContract(final ProposalOrContractDto proOrCon)
            throws CRMDBException;

    /**
     * get file template name
     * 
     * @param type
     * @return contractTemplateList
     * @throws CRMDBException
     */
    public List<FileTemplateNameBean> getFileTemplateName(final int type) throws CRMDBException;

    /**
     * Proposal or contractDto show or search
     * 
     * @param searchFlag
     * @param proOrConSBean
     *            proposalOrContractSearchBean
     * @param currpage
     * @return map
     * @throws CRMDBException
     */
    public Map<String, Object> getAllProposalOrContract(final int searchFlag,
            final ProposalOrContractSearchBean proOrConSBean, final int currpage, final int pageSize)
            throws CRMDBException;

    /**
     * when add or update proposal Or Contract ,check Name exist or not
     * 
     * @param proOrConDto
     *            proposalOrContractDto
     * @return NameNotOrExist (false/true)
     * @throws CRMDBException
     */
    public boolean checkProposalOrContractName(final ProposalOrContractDto proOrConDto)
            throws CRMDBException;

}