package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ProposalOrContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ProposalOrContractDto;

/**
 * dao interface
 * 
 * @author zhangqiuli
 */
public interface ProposalOrContractDao {

    /**
     * according to proposalOrContractIDs delete proposalOrContracts
     * 
     * @param proposalOrContractIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteProposalOrContract(String proposalOrContractIDs) throws DataAccessException;

    /**
     * add and update proposalOrContracts
     * 
     * @param proposalOrContractsDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void addOrUpdateProposalOrContract(ProposalOrContractDto proposalOrContractsDto)
            throws DataAccessException;

    /**
     * get contract template name
     * 
     * @param type
     * @return ContractTemplateName
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public List<FileTemplateNameBean> getFileTemplateName(final int type)
            throws DataAccessException;

    /**
     * contract show or search
     * 
     * @param searchFlag
     * @param proposalOrContractSearchBean
     * @param currpage
     * @return map
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllProposalOrContract(final int searchFlag,
            final ProposalOrContractSearchBean proposalOrContractSearchBean, final int currpage,
            final int pageSize) throws DataAccessException;

    /**
     * for check contractName must have one
     * 
     * @param proposalOrContract
     * @return false/true
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public boolean checkProposalOrContractName(ProposalOrContractDto proposalOrContract)
            throws DataAccessException;

}
