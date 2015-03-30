package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.customer.bean.ContractBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ContractDto;

/**
 * dao interface
 * 
 * @author zhangqiuli
 */
public interface ContractDao {

    /**
     * according to proposalOrContractIDs delete proposalOrContracts
     * 
     * @param proposalOrContractIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteContract(String proposalOrContractIDs) throws DataAccessException;

    /**
     * add and update proposalOrContracts
     * 
     * @param ContractsDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void addOrUpdateContract(ContractDto ContractsDto) throws DataAccessException;

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
     */
    public Boolean NameOrOrderIdIsExit(String name);

    /**
     * @param searchFlag
     * @param contractSearchBean
     * @param start
     * @param limit
     */
    public List<ContractBean> getAllContract(int searchFlag, ContractSearchBean contractSearchBean,
            int start, int limit);

    /**
     * @param searchFlag
     * @param contractSearchBean
     * @param start
     * @param limit
     * @return type of Integer
     */
    public int getAllContractCount(int searchFlag, ContractSearchBean contractSearchBean,
            int start, int limit);

}
