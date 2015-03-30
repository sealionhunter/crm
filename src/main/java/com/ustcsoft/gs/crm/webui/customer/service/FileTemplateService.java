package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.FileTemplateDto;

/**
 * FileTemplateService
 * 
 * @author heweiwei
 * 
 */
public interface FileTemplateService {

    /**
     * 根据页码和查询条件获取数据库中记录
     * 
     * @param searchFlag
     * @param searchBean
     * @param page
     * @return type of Map<String,Object>
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getFileTemplate(final int searchFlag,
            final FileTemplateSearchBean searchBean, final int page, final int pageSize)
            throws CRMDBException;

    /**
     * 根据合同模板ID删除相应记录
     * 
     * @param fileTemplateIDs
     * @return
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> delFileTemplate(String fileTemplateIDs) throws CRMDBException;

    /**
     * 检查合同模板名称是否重复
     * 
     * @param fileTemplateDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public boolean checkFileTemplateNameExist(FileTemplateDto fileTemplateDto)
            throws CRMDBException;

    /**
     * 添加或编辑合同模板
     * 
     * @param fileTemplateDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateFileTemplate(FileTemplateDto fileTemplateDto) throws CRMDBException;
}