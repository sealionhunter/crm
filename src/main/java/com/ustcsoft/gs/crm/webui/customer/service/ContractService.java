package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ContractDto;

/**
 * service interface
 * 
 * @author zhangqiuli
 */
public interface ContractService {

    /**
     * according to proposalOrContractID to delete proposalOrContract
     * 
     * @param proOrConIDs
     *            proposalOrContractIDs
     * @throws CRMDBException
     */
    public void deleteContract(final String proOrConIDs) throws CRMDBException;

    /**
     * add or update proposalOrContract
     * 
     * @param proOrCon
     *            ProposalOrContractDto
     * @throws CRMDBException
     */
    public void addOrUpdateContract(final ContractDto proOrCon) throws CRMDBException;

    /**
     * 
     * @return boolean
     */
    public Boolean NameOrOrderIdIsExit(String name);

    public Map<String, Object> getContractEvent(int orderID);

    public void updateContractEvent(int eventID, String demand, ContractDto contractDto, int userID);

    // /**
    // * get file template name
    // *
    // * @param type
    // * @return contractTemplateList
    // * @throws CRMDBException
    // */
    // public List<FileTemplateNameBean> getFileTemplateName(final int type)
    // throws CRMDBException;

    /**
     * @param searchFlag
     * @param contractSearchBean
     * @param start
     * @param pageSize
     * @return type of Map<String,Object>
     */
    public Map<String, Object> getAllContract(int searchFlag,
            ContractSearchBean contractSearchBean, int start, int pageSize);

}