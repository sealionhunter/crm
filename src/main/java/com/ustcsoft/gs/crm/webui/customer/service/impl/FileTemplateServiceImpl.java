package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.FileTemplateDao;
import com.ustcsoft.gs.crm.webui.customer.dto.FileTemplateDto;
import com.ustcsoft.gs.crm.webui.customer.service.FileTemplateService;

public class FileTemplateServiceImpl implements FileTemplateService {

    private static final Log LOG = LogFactory.getLog(FileTemplateServiceImpl.class);

    private FileTemplateDao fileTemplateDao = null;

    /**
     * 根据页码和查询条件获取数据库中记录
     * 
     * @param searchFlag
     * @param searchBean
     * @param page
     * @return Map<String, Object>
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> getFileTemplate(final int searchFlag,
            final FileTemplateSearchBean searchBean, final int page, final int pageSize)
            throws CRMDBException {
        LOG.debug("method getFileTemplate start!");
        try {
            Map<String, Object> map = fileTemplateDao.getFileTemplate(searchFlag, searchBean, page,
                    pageSize);
            LOG.debug("method getFileTemplate end!");
            return map;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getFileTemplate!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * 根据合同模板ID删除相应记录
     * 
     * @param fileTemplateIDs
     * @return
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> delFileTemplate(String fileTemplateIDs) throws CRMDBException {
        LOG.debug("method delFileTemplate start!");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] strings = fileTemplateIDs.split(",");
            List<FileTemplateDto> fileTemplateDtos = fileTemplateDao.getFileTemplateByID(CRMUtils
                    .getStringForDelete(fileTemplateIDs));
            int type = fileTemplateDtos.get(0).getType();
            List<String> deleteNames = fileTemplateDao.checkFile(
                    CRMUtils.getStringForDelete(fileTemplateIDs), type);
            if (deleteNames.size() == 0) {
                fileTemplateDao.deleteFileTemplate(CRMUtils.getStringForDelete(fileTemplateIDs));
            } else {
                String errorString = "";
                outFor: for (int i = 0; i < strings.length; i++) {
                    for (FileTemplateDto fileTemplateDto : fileTemplateDtos) {
                        if (Integer.parseInt(strings[i].trim()) == fileTemplateDto
                                .getFileTemplateID()) {
                            for (String string : deleteNames) {
                                if (fileTemplateDto.getFileTemplateName().equals(string)) {
                                    errorString = errorString + string;
                                    break outFor;
                                }
                            }
                        }
                    }
                }
                if (type == 1) {
                    errorString += "正在被合同模块使用，无法删除！";
                } else {
                    errorString += "正在被建议书模块使用，无法删除！";
                }
                map.put("errorMessage", errorString);
            }
            LOG.debug("method delFileTemplate end!");
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method delFileTemplate!", e);
            throw new CRMDBException(e);
        }
        return map;
    }

    /**
     * 检查合同模板名称是否重复
     * 
     * @param fileTemplateDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean checkFileTemplateNameExist(FileTemplateDto fileTemplateDto)
            throws CRMDBException {
        LOG.debug("method checkFileTemplateNameExist start!");
        try {
            boolean nameNotExist = fileTemplateDao.checkFileTemplateNameExist(fileTemplateDto);
            LOG.debug("method checkFileTemplateNameExist end!");
            return nameNotExist;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method checkFileTemplateNameExist!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * 添加或编辑合同模板
     * 
     * @param fileTemplateDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateFileTemplate(FileTemplateDto fileTemplateDto) throws CRMDBException {
        LOG.debug("method updateFileTemplate start!");
        try {
            if (fileTemplateDto.getFileTemplateAddDate().equals("")) {
                fileTemplateDto.setFileTemplateAddDate(null);
            }
            if (fileTemplateDto.getFileTemplateEditDate().equals("")) {
                fileTemplateDto.setFileTemplateEditDate(null);
            }
            fileTemplateDao.updateFileTemplate(fileTemplateDto);
            LOG.debug("method updateFileTemplate end!");
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateFileTemplate!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * @param fileTemplateDao
     *            the fileTemplateDao to set
     */
    public void setFileTemplateDao(FileTemplateDao fileTemplateDao) {
        this.fileTemplateDao = fileTemplateDao;
    }
}
