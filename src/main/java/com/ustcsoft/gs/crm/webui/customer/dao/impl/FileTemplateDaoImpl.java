package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.FileTemplateDao;
import com.ustcsoft.gs.crm.webui.customer.dto.FileTemplateDto;
import com.ustcsoft.gs.crm.webui.customer.service.impl.FileTemplateServiceImpl;

public class FileTemplateDaoImpl implements FileTemplateDao {

    private static final Log LOG = LogFactory.getLog(FileTemplateServiceImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * 根据页码和查询条件获取数据库中记录
     * 
     * @param searchFlag
     * @param searchBean
     * @param page
     * @return type Map<String,Object>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> getFileTemplate(final int searchFlag,
            final FileTemplateSearchBean searchBean, final int page, final int pageSize)
            throws DataAccessException {
        LOG.debug("method getFileTemplate start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        List<FileTemplateDto> fileTemplateList = new ArrayList<FileTemplateDto>();
        // 查询所有合同模板
        if (searchFlag == 0) {
            fileTemplateList = hibernateTemplate
                    .executeFind(new PagingHibernateCallback(CustomerConstant.FILETEMPLATE_HQL,
                            page, "type", searchBean.getType(), pageSize));
            total = (Long) hibernateTemplate.find(CustomerConstant.FILETEMPLATECOUNT_HQL,
                    searchBean.getType()).get(0);
        }
        // 模糊查询合同模板
        else if (searchFlag == 1) {
            final String searchText = searchBean.getSearchText();
            String[] paramNames = { "searchText", "type" };
            Object[] values = { searchText, searchBean.getType() };
            fileTemplateList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.FILETEMPLATE_SEARCH, page, paramNames, values, pageSize));
            total = (Long) hibernateTemplate.findByNamedParam(
                    CustomerConstant.FILETEMPLATECOUNT_SEARCH, paramNames, values).get(0);
        }
        // 高级查询合同模板
        else if (searchFlag == 2) {
            StringBuffer searchHql = new StringBuffer(CustomerConstant.FILETEMPLATE_ADVANCESEARCH);
            StringBuffer searchCountHql = new StringBuffer(
                    CustomerConstant.FILETEMPLATECOUNT_ADVANCESEARCH);
            if (!searchBean.getFileTemplateAddDateStart().isEmpty()) {
                searchHql.append(CustomerConstant.FILETEMPLATEADDDATESTART);
                searchCountHql.append(CustomerConstant.FILETEMPLATEADDDATESTART);
            }
            if (!searchBean.getFileTemplateAddDateEnd().isEmpty()) {
                searchHql.append(CustomerConstant.FILETEMPLATEADDDATEEND);
                searchCountHql.append(CustomerConstant.FILETEMPLATEADDDATEEND);
            }
            if (!searchBean.getFileTemplateEditDateStart().isEmpty()) {
                searchHql.append(CustomerConstant.FILETEMPLATEEDITDATESTART);
                searchCountHql.append(CustomerConstant.FILETEMPLATEEDITDATESTART);
            }
            if (!searchBean.getFileTemplateEditDateEnd().isEmpty()) {
                searchHql.append(CustomerConstant.FILETEMPLATEEDITDATEEND);
                searchCountHql.append(CustomerConstant.FILETEMPLATEEDITDATEEND);
            }
            fileTemplateList = hibernateTemplate.executeFind(new SuperHibernateCallback(searchHql
                    .toString(), page, searchBean, pageSize));
            total = (Long) hibernateTemplate.executeFind(
                    new SuperHibernateCallback(searchCountHql.toString(), 0, searchBean, pageSize))
                    .get(0);
        }
        map.put("total", total);
        map.put("items", fileTemplateList);
        LOG.debug("method getFileTemplate end!");
        return map;
    }

    /**
     * 根据合同模板ID删除相应记录
     * 
     * @param fileTemplateIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteFileTemplate(String fileTemplateIDs) throws DataAccessException {
        LOG.debug("method deleteFileTemplate start!");
        String hql = CustomerConstant.DELETE_FILETEMPLATE_HQL + fileTemplateIDs;
        String updateFileTemplateName = CustomerConstant.UPDATE_FILETEMPLATENAME + fileTemplateIDs
                + ")";
        hibernateTemplate.bulkUpdate(hql);
        hibernateTemplate.bulkUpdate(updateFileTemplateName);
        LOG.debug("method deleteFileTemplate end!");
    }

    /**
     * 添加或编辑合同模板
     * 
     * @param fileTemplateDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void updateFileTemplate(FileTemplateDto fileTemplateDto) throws DataAccessException {
        LOG.debug("method updateFileTemplate start!");
        hibernateTemplate.saveOrUpdate(fileTemplateDto);
        LOG.debug("method updateFileTemplate end!");
    }

    /**
     * 检查合同模板名称是否重复
     * 
     * @param fileTemplateDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean checkFileTemplateNameExist(FileTemplateDto fileTemplateDto)
            throws DataAccessException {
        LOG.debug("method checkFileTemplateNameExist start!");
        Object[] values = { fileTemplateDto.getFileTemplateID(),
                fileTemplateDto.getFileTemplateName(), fileTemplateDto.getType() };
        long size = (Long) hibernateTemplate.find(CustomerConstant.FIND_FILETEMPLATE_HQL, values)
                .get(0);
        if (size == 0) {
            LOG.debug("method checkFileTemplateNameExist end!");
            return true;
        } else {
            LOG.debug("method checkFileTemplateNameExist end!");
            return false;
        }
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> checkFile(String stringForDelete, int type) {
        LOG.debug("method checkFile start!");
        List<String> deleteIDs;
        if (type == 1) {
            deleteIDs = hibernateTemplate
                    .find("select distinct contract.fileTemplateName from ContractDto contract where contract.isAbolished = 0 and contract.fileTemplateName in (select fileTemplateName from FileTemplateDto fileTemplate where fileTemplate.fileTemplateID in"
                            + stringForDelete + ")");
        } else {
            deleteIDs = hibernateTemplate
                    .find("select distinct pro.fileTemplateName from ProposalOrContractDto pro where pro.isAbolished = 0 and pro.fileTemplateName in (select fileTemplateName from FileTemplateDto fileTemplate where fileTemplate.fileTemplateID in"
                            + stringForDelete + ")");
        }
        LOG.debug("method checkFile end!");
        return deleteIDs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ustcsoft.gs.crm.webui.customer.dao.FileTemplateDao#getFileTemplateByID
     * (java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<FileTemplateDto> getFileTemplateByID(String string) {
        List<FileTemplateDto> fileTemplateDtos = hibernateTemplate
                .find("from FileTemplateDto file where file.id in " + string);
        return fileTemplateDtos;
    }
}