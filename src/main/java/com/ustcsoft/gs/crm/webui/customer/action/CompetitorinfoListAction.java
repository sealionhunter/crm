/*
 * Class name: CompetitorinfoListAction
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;
import com.ustcsoft.gs.crm.webui.customer.service.CompetitorinfoService;

public class CompetitorinfoListAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private CompetitorSearchBean searchBean = null;

    public CompetitorSearchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(CompetitorSearchBean searchBean) {
        this.searchBean = searchBean;
    }

    private static Log log = LogFactory.getLog(CompetitorinfoListAction.class);

    private CompetitorinfoService competitorinfoService;

    private String record;

    private int competitorInfoId = 0;

    private CompetitorinfoDto competitorinfoDto = null;

    private String searchStr = null;

    private int searchFlag;

    private int page = 1;

    int pageSize = 25;

    private String competitorType;

    private String competitorInfoIds;

    /**
     * @return page
     */
    @Override
    public int getPage() {
        return page;
    }

    /**
     * @param page
     */
    @Override
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return competitorType
     */
    public String getCompetitorType() {
        return competitorType;
    }

    /**
     * @param competitorType
     *            the competitorType to set
     */
    public void setCompetitorType(String competitorType) {
        this.competitorType = competitorType;
    }

    /**
     * @return searchFlag
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
    public void setSearchFlag(int searchFlag) {
        this.searchFlag = searchFlag;
    }

    /**
     * @return searchStr
     */
    public String getSearchStr() {
        return searchStr;
    }

    /**
     * @param searchStr
     *            the searchStr to set
     */
    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    /**
     * @return record
     */
    public String getRecord() {
        return record;
    }

    /**
     * @param record
     *            the record to set
     */
    public void setRecord(String record) {
        this.record = record;
    }

    /**
     * @return competitorInfoId
     */
    public int getCompetitorInfoId() {
        return competitorInfoId;
    }

    /**
     * @param competitorInfoId
     *            the competitorInfoId to set
     */
    public void setCompetitorInfoId(int competitorInfoId) {
        this.competitorInfoId = competitorInfoId;
    }

    /**
     * @return competitorinfoService
     */
    public CompetitorinfoService getCompetitorinfoService() {
        return competitorinfoService;
    }

    /**
     * @param competitorinfoService
     *            the competitorinfoService to set
     */
    public void setCompetitorinfoService(CompetitorinfoService competitorinfoService) {
        this.competitorinfoService = competitorinfoService;
    }

    /**
     * @return map
     */
    @Override
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * @param map
     *            the map to set
     */
    @Override
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * @return competitorInfoIds
     */
    public String getCompetitorInfoIds() {
        return competitorInfoIds;
    }

    /**
     * @param competitorInfoIds
     *            the competitorInfoIds to set
     */
    public void setCompetitorInfoIds(String competitorInfoIds) {
        this.competitorInfoIds = competitorInfoIds;
    }

    /**
     * @return competitorInfoIds
     */
    public CompetitorinfoDto getCompetitorinfoDto() {
        return competitorinfoDto;
    }

    /**
     * @param competitorinfoDto
     *            the competitorinfoDto to set
     */
    public void setCompetitorinfoDto(CompetitorinfoDto competitorinfoDto) {
        this.competitorinfoDto = competitorinfoDto;
    }

    /**
     * get All Competitor
     * 
     * @return all Competitor
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public String getAllCompetitor() throws CRMDBException {
        log.debug("method CompetitorinfoListAction start!");
        if (searchFlag != 0) {
            searchBean = (CompetitorSearchBean) CRMUtils.jsonToBean(super.jsonString,
                    CompetitorSearchBean.class);
            searchBean.setCompetitorType(competitorType);
        } else {
            searchBean = new CompetitorSearchBean();
            searchBean.setCompetitorType(competitorType);
        }
        map = getCompetitorinfoService().getAllCompetitor(searchFlag, searchBean, page, pageSize);
        log.debug("method CompetitorinfoListAction end!");
        return SUCCESS;
    }

    /**
     * update Competitorinfo
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String updateCompetitorinfo() throws CRMDBException {
        log.debug("method updateCompetitorinfo start!");
        JSONObject json = JSONObject.fromObject(record);
        CompetitorinfoDto competitorinfoDto = (CompetitorinfoDto) JSONObject.toBean(json,
                CompetitorinfoDto.class);
        competitorinfoService.updateCompetitorinfo(competitorinfoDto);
        log.debug("method updateCompetitorinfo end!");
        return SUCCESS;
    }

    /**
     * validate method of updateCompetitorinfo
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateUpdateCompetitorinfo() throws CRMDBException {
        log.debug("method validateUpdateCompetitorinfo start!");
        competitorinfoDto = (CompetitorinfoDto) CRMUtils
                .jsonToBean(record, CompetitorinfoDto.class);
        if ("".equalsIgnoreCase(competitorinfoDto.getCompetitorName())) {
            addFieldError("competitorName", "名字不能为空");
        } else if (competitorinfoDto.getCompetitorName().length() > 30) {
            addFieldError("competitorName", "竞争对手名称不能大于30个字符或者汉字");
        } else if (competitorinfoService.judgeCompetitorName(competitorinfoDto)) {
            addFieldError("competitorName", "竞争对手名称已存在");
        }
        if ("".equalsIgnoreCase(competitorinfoDto.getCompetitorLocation())) {
            addFieldError("competitorLocation", "地址不能为空");
        } else if (competitorinfoDto.getCompetitorLocation().length() > 20) {
            addFieldError("competitorLocation", "竞争对手地址不能大于20个字符或者汉字");
        }
        if ("".equalsIgnoreCase(competitorinfoDto.getCompetitorField())) {
            addFieldError("competitorField", "竞争领域不能为空");
        } else if (competitorinfoDto.getCompetitorField().length() > 30) {
            addFieldError("competitorField", "竞争领域不能大于30个字符或者汉字");
        }
        if ("".equalsIgnoreCase(competitorinfoDto.getCompetitorProperty())) {
            addFieldError("competitorProperty", "竞争对手性质不能为空");
        } else if (competitorinfoDto.getCompetitorProperty().length() > 30) {
            addFieldError("competitorProperty", "竞争对手性质长度不能大于30");
        }
        if ("".equalsIgnoreCase(competitorinfoDto.getCompetitorMoney())) {
            addFieldError("competitorMoney", "竞争对手资金力量不能为空");
        } else if (competitorinfoDto.getCompetitorMoney().length() > 20) {
            addFieldError("competitorMoney", "竞争对手资金力量不能大于20个字符");
        }
        if (competitorinfoDto.getCompetitorPeople() != null) {
            if (competitorinfoDto.getCompetitorPeople().length() > 15) {
                addFieldError("competitorPeople", "竞争对手员工规模不能大于15个字符");
            }
        }
        if ("".equalsIgnoreCase(competitorinfoDto.getCompetitorFoundDate())) {
            addFieldError("competitorFoundDate", "竞争对手成立时间不能为空");
        }
        if ("".equalsIgnoreCase(competitorinfoDto.getCompetitorType())) {
            addFieldError("competitorType", "竞争类型不能为空");
        } else if (competitorinfoDto.getCompetitorType().length() > 20) {
            addFieldError("competitorType", "竞争类型不能大于20个字符");
        }
        if (competitorinfoDto.getCompetitorDescription() != null) {
            if (competitorinfoDto.getCompetitorDescription().length() > 1024) {
                addFieldError("competitorDescription", "竞争对手备注不能大于1024个字符");
            }
        }
        if (competitorinfoDto.getCompetitorDetail() != null) {
            if (competitorinfoDto.getCompetitorDetail().length() > 2048) {
                addFieldError("competitorDetail", "竞争对手详细信息不能大于2048个字符");
            }
        }
        showFieldError();
        log.debug("method validateUpdateCompetitorinfo end!");
    }

    /**
     * delete Competitorinfo
     * 
     * @return type of String
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteCompetitorinfo() throws CRMDBException {
        log.debug("method deleteCompetitorinfo start!");
        competitorinfoService.deleteCompetitorinfoAndCprAnalysis(competitorInfoIds);
        log.debug("method deleteCompetitorinfo end!");
        return SUCCESS;
    }

    /**
     * validate method of deleteCompetitorinfo
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateDeleteCompetitorinfo() throws CRMDBException {
        log.debug("method validateDeleteCompetitorinfo start!");
        if (!competitorInfoIds.matches("^\\[\\d+(,\\d+)*\\]$")) {
            addFieldError("competitorInfoIds", "删除信息id格式不对");
        }
        showFieldError();
        log.debug("method validateDeleteCompetitorinfo end!");
    }
}
