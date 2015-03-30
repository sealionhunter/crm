package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.FileTemplateDto;

/**
 * FileTemplateDao
 * 
 * @author heweiwei
 * 
 */
public interface FileTemplateDao {

    /**
     * 根据页码和查询条件获取数据库中记录
     * 
     * @param searchFlag
     * @param searchBean
     * @param page
     * @return type of Map<String,Object>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getFileTemplate(final int searchFlag,
            final FileTemplateSearchBean searchBean, final int page, final int pageSize)
            throws DataAccessException;

    /**
     * 根据合同模板ID删除相应记录
     * 
     * @param fileTemplateIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteFileTemplate(String fileTemplateIDs) throws DataAccessException;

    /**
     * 检查合同模板名称是否重复
     * 
     * @param fileTemplateDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public boolean checkFileTemplateNameExist(FileTemplateDto fileTemplateDto)
            throws DataAccessException;

    /**
     * 添加或编辑合同模板
     * 
     * @param fileTemplateDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void updateFileTemplate(FileTemplateDto fileTemplateDto) throws DataAccessException;

    /**
     * @param stringForDelete
     * @param type
     * @return
     */
    public List<String> checkFile(String stringForDelete, int type);

    /**
     * @param string
     * @return
     */
    public List<FileTemplateDto> getFileTemplateByID(String string);
}
