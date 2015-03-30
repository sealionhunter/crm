package com.ustcsoft.gs.crm.webui.customer.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.FileTemplateDto;
import com.ustcsoft.gs.crm.webui.customer.service.FileTemplateService;

/**
 * FileTemplateAction
 * 
 * @author suntanhua,heweiwei
 * 
 */
public class FileTemplateAction extends CRMAction implements ServletRequestAware {

    private static final long serialVersionUID = 1L;

    private static final Log LOG = LogFactory.getLog(FileTemplateAction.class);

    /** 储存数据总条数 */
    private long total = 0;

    private FileTemplateService fileTemplateService = null;

    private HttpServletRequest request = null;

    private boolean success = false;

    /** 生成合同模板文件路径 */
    private String filePath = "";

    /** 查询方式 */
    private int searchFlag = 0;

    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * the method is used to find all fileTemplate
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");
        final FileTemplateSearchBean searchBean = (FileTemplateSearchBean) CRMUtils.jsonToBean(
                jsonString, FileTemplateSearchBean.class);
        map = fileTemplateService.getFileTemplate(searchFlag, searchBean, page, super.limit);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * the method is used to delete fileTemplate by fileTemplateID
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String delete() throws CRMDBException {
        LOG.debug("method deleteFileTemplate start!");
        map = fileTemplateService.delFileTemplate(jsonString);
        LOG.debug("method deleteFileTemplate end!");
        return SUCCESS;
    }

    /**
     * the method is used to updateOrAdd fileTemplate by fileTemplateID
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String update() throws CRMDBException {
        LOG.debug("method updateFileTemplate start!");
        final FileTemplateDto fileTemplateDto = (FileTemplateDto) CRMUtils.jsonToBean(jsonString,
                FileTemplateDto.class);
        fileTemplateService.updateFileTemplate(fileTemplateDto);
        LOG.debug("method updateFileTemplate end!");
        return SUCCESS;
    }

    /**
     * the method is used to create the word of fileTemplate
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @throws CRMException
     *             in case of creatFolder Exception
     */
    public String createTemplateDoc() throws CRMDBException, CRMException {
        LOG.debug("method createTemplateDoc start!");
        filePath = CRMUtils.createDoc(request, "fileTemplate", name);
        success = true;
        LOG.debug("method createTemplateDoc end!");
        return SUCCESS;
    }

    /**
     * the method is used to validate data for updateFileTemplate()
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void validateUpdate() throws CRMDBException {
        LOG.debug("method validateUpdateFileTemplate start!");
        final FileTemplateDto fileTemplateDto = (FileTemplateDto) CRMUtils.jsonToBean(jsonString,
                FileTemplateDto.class);
        final String timeInvalid = this.getText("time.invalid");
        if (fileTemplateDto.getFileTemplateName().isEmpty()) {
            addFieldError("fileTemplateName", this.getText("contractNameN.invalid"));
        }
        if (fileTemplateDto.getFileTemplateName().length() > CRMConstant.LENGTH_1) {
            addFieldError("fileTemplateName", this.getText("contractName.invalid"));
        }
        if (!fileTemplateService.checkFileTemplateNameExist(fileTemplateDto)) {
            addFieldError("fileTemplateName", this.getText("contractNameCheck.invalid"));
        }
        if (fileTemplateDto.getDescriptions().length() > CRMConstant.LENGTH_2) {
            addFieldError("descriptions", this.getText("contractDescriptions.invalid"));
        }
        if (fileTemplateDto.getFileTemplateID() == 0
                && !CRMUtils.dateCheck(fileTemplateDto.getFileTemplateAddDate())) {
            addFieldError("addTimeError", timeInvalid);
        }
        if (fileTemplateDto.getFileTemplateID() != 0
                && !CRMUtils.dateCheck(fileTemplateDto.getFileTemplateEditDate())) {
            addFieldError("editTimeError", timeInvalid);
        }
        showFieldError();
        LOG.debug("method validateUpdateFileTemplate end!");
    }

    /**
     * the method is used to validate data for execute()
     * 
     */
    @Override
    public void validateExecute() {
        LOG.debug("method validateExecute start!");
        final FileTemplateSearchBean searchBean = (FileTemplateSearchBean) CRMUtils.jsonToBean(
                jsonString, FileTemplateSearchBean.class);
        final String searchText = searchBean.getSearchText();
        final String addDateStart = searchBean.getFileTemplateAddDateStart();
        final String editDateStart = searchBean.getFileTemplateEditDateStart();
        final String addDateEnd = searchBean.getFileTemplateAddDateEnd();
        final String editDateEnd = searchBean.getFileTemplateEditDateEnd();
        final String timeInvalid = this.getText("time.invalid");
        final String fileTemplateName = searchBean.getFileTemplateName();
        if (searchFlag == 1 && searchText.length() > CRMConstant.LENGTH_2) {
            addFieldError("searchTextError", this.getText("searchText.invalid"));
        }
        if (searchFlag == 2) {
            if (fileTemplateName.length() > CRMConstant.CONTRACTLENGTH_1) {
                addFieldError("fileTemplateNameError", this.getText("contractName.invalid"));
            }
            if (searchBean.getFileTemplateDescriptions().length() > CRMConstant.CONTRACTLENGTH_2) {
                addFieldError("descriptionsError", this.getText("contractDescriptions.invalid"));
            }
            if (!addDateStart.isEmpty() && !addDateEnd.isEmpty()
                    && !CRMUtils.dateJudge(addDateStart, addDateEnd)) {
                addFieldError("addTimeError", this.getText("timeCompare.invalid"));
            }
            if (!addDateStart.isEmpty()
                    && !CRMUtils.dateCheck(searchBean.getFileTemplateAddDateStart())) {
                addFieldError("addTimeStartError", timeInvalid);
            }
            if (!addDateEnd.isEmpty() && !CRMUtils.dateCheck(addDateEnd)) {
                addFieldError("addTimeEndError", timeInvalid);
            }
            if (!editDateStart.isEmpty()
                    && !CRMUtils.dateCheck(searchBean.getFileTemplateEditDateStart())) {
                addFieldError("editTimeStartError", timeInvalid);
            }
            if (!editDateEnd.isEmpty()
                    && !CRMUtils.dateCheck(searchBean.getFileTemplateEditDateEnd())) {
                addFieldError("editTimeEndError", timeInvalid);
            }
            if (!editDateStart.isEmpty() && !editDateEnd.isEmpty()) {
                if (!CRMUtils.dateJudge(editDateStart, editDateEnd)) {
                    addFieldError("editTimeError", this.getText("timeCompare.invalid"));
                }
            }
        }
        map.putAll(getFieldErrors());
        LOG.debug("method validateExecute end!");
    }

    /**
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(final long total) {
        this.total = total;
    }

    /**
     * @return the fileTemplateService
     */
    @JSON(serialize = false)
    public FileTemplateService getFileTemplateService() {
        return fileTemplateService;
    }

    /**
     * @param fileTemplateService
     *            the fileTemplateService to set
     */
    public void setFileTemplateService(final FileTemplateService fileTemplateService) {
        this.fileTemplateService = fileTemplateService;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success
     *            the success to set
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    /**
     * @param request
     *            the request to set
     */
    @Override
    public void setServletRequest(final HttpServletRequest request) {
        this.request = request;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     *            the filePath to set
     */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the searchFlag
     */
    @Override
    public int getSearchFlag() {
        return searchFlag;
    }

    /**
     * @param searchFlag
     *            the searchFlag to set
     */
    @Override
    public void setSearchFlag(final int searchFlag) {
        this.searchFlag = searchFlag;
    }
}
