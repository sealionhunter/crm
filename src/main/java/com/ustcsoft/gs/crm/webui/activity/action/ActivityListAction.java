package com.ustcsoft.gs.crm.webui.activity.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.activity.bean.ActivitySearchBean;
import com.ustcsoft.gs.crm.webui.activity.dto.ActivityInfoDto;
import com.ustcsoft.gs.crm.webui.activity.service.ActivityInfoService;
import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * class ActivityListAction handle the data from client and return the result to
 * client
 */
public class ActivityListAction extends CRMAction {

    private static final long serialVersionUID = 1L;
    private final static Log LOG = LogFactory.getLog(ActivityListAction.class);
    private transient ActivityInfoService activityInfoService = null;
    private transient ActivityInfoDto activity = null;

    /**
     * validate method update
     */
    @Override
    public void validateUpdate() {
        activity = (ActivityInfoDto) CRMUtils.jsonToBean(super.jsonString, ActivityInfoDto.class);
        check();
    }

    /**
     * get all activities
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");
        ActivitySearchBean searchBean = (ActivitySearchBean) CRMUtils.jsonToBean(super.jsonString,
                ActivitySearchBean.class);
        map = activityInfoService.getOrSearchActivity(super.searchFlag, searchBean, super.page,
                super.limit);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * add a activity or update a activity
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String update() throws CRMDBException {
        LOG.debug("method updateActivity start!");
        activityInfoService.updateActivity(activity);
        LOG.debug("method updateActivity end!");
        return SUCCESS;
    }

    /**
     * delete a activity
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String delete() throws CRMDBException {
        LOG.debug("method deleteActivity start.");
        activityInfoService.deleteActivity(super.jsonString);
        LOG.debug("method deleteActivity end.");
        return SUCCESS;
    }

    private void check() {
        if ("".equalsIgnoreCase(activity.getActivityType())) {
            addFieldError("activityType",
                    this.getText("activityType.invalid") + this.getText("not_null.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityName())) {
            addFieldError("activityName",
                    this.getText("activityName.invalid") + this.getText("not_null.invalid"));
        } else if (activity.getActivityName().length() > 30) {
            addFieldError("activityName",
                    this.getText("activityName.invalid") + this.getText("number_30.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityPlace())) {
            addFieldError("activityPlace",
                    this.getText("activityPlace.invalid") + this.getText("not_null.invalid"));
        } else if (activity.getActivityName().length() > 50) {
            addFieldError("activityPlace",
                    this.getText("activityPlace.invalid") + this.getText("number_50.invalid"));
        }
        if (activity.getActivityAim().length() > 100) {
            addFieldError("activityAim",
                    this.getText("activityAim.invalid") + this.getText("number_100.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityStartTime())) {
            addFieldError("activityStartTime",
                    this.getText("activityStartTime.invalid") + this.getText("not_null.invalid"));
        } else if (!CRMUtils.dateCheck(activity.getActivityStartTime())) {
            addFieldError("activityStartTime",
                    this.getText("activityStartTime.invalid") + this.getText("time.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityEndTime())) {
            addFieldError("activityEndTime",
                    this.getText("activityEndTime.invalid") + this.getText("not_null.invalid"));
        } else if (!CRMUtils.dateCheck(activity.getActivityEndTime())) {
            addFieldError("activityEndTime",
                    this.getText("activityEndTime.invalid") + this.getText("time.invalid"));
        }
        if (!CRMUtils.dateJudge(activity.getActivityStartTime(), activity.getActivityEndTime())) {
            addFieldError("activityEndTime", this.getText("timeCompare.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityEmphasis())) {
            addFieldError("activityEmphasis",
                    this.getText("activityEmphasis.invalid") + this.getText("not_null.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityLeader())) {
            addFieldError("activityLeader",
                    this.getText("activityLeader.invalid") + this.getText("not_null.invalid"));
        } else if (activity.getActivityName().length() > 20) {
            addFieldError("activityLeader",
                    this.getText("activityLeader.invalid") + this.getText("number_20.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityModifer())) {
            addFieldError("activityModifer",
                    this.getText("activityModifer.invalid") + this.getText("not_null.invalid"));
        } else if (activity.getActivityModifer().length() > 20) {
            addFieldError("activityModifer",
                    this.getText("activityModifer.invalid") + this.getText("number_20.invalid"));
        }
        if (!(activity.getActivityOurPersonNO().length() == 0 || activity.getActivityOurPersonNO()
                .matches("0|^[1-9][0-9]{0,}$"))) {
            addFieldError("activityOurPersonNO",
                    this.getText("activityOurPersonNO.invalid") + this.getText("number.invalid"));
        }
        if (!(activity.getActivityFunds().length() == 0 || activity.getActivityFunds().matches(
                "0|(^([1-9][0-9]{0,}|0).[0-9]{0,}$)|(^[1-9][0-9]{0,}$)"))) {
            addFieldError("activityFunds",
                    this.getText("activityFunds.invalid") + this.getText("number.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityPeriod())) {
            addFieldError("activityPeriod",
                    this.getText("activityPeriod.invalid") + this.getText("not_null.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityRange())) {
            addFieldError("activityRange",
                    this.getText("activityRange.invalid") + this.getText("not_null.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityState())) {
            addFieldError("activityState",
                    this.getText("activityState.invalid") + this.getText("not_null.invalid"));
        }
        if ("".equalsIgnoreCase(activity.getActivityMember())) {
            addFieldError("activityMember",
                    this.getText("activityMember.invalid") + this.getText("not_null.invalid"));
        }
        if (activity.getActivityComment().length() > 1024) {
            addFieldError("activityComment",
                    this.getText("descriptions.invalid") + this.getText("number_1024.invalid"));
        }
        if (activity.getActivityPlan().length() > 1024) {
            addFieldError("activityPlan",
                    this.getText("activityPlan.invalid") + this.getText("number_1024.invalid"));
        }
        if (activity.getActivityDetail().length() > 1024) {
            addFieldError("activityDetail",
                    this.getText("activityDetail.invalid") + this.getText("number_1024.invalid"));
        }
        if (activity.getActivityMeans().length() > 1024) {
            addFieldError("activityMeans",
                    this.getText("activityMeans.invalid") + this.getText("number_1024.invalid"));
        }
        showFieldError();
    }

    /**
     * @return activityInfoService
     */
    public ActivityInfoService getActivityInfoService() {
        return activityInfoService;
    }

    /**
     * @param activityInfoServiceImpl
     *            the activityInfoService to set
     */
    public void setActivityInfoService(ActivityInfoService activityInfoServiceImpl) {
        activityInfoService = activityInfoServiceImpl;
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
     * @return page
     */
    @Override
    public int getPage() {
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    @Override
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return activityInfo
     */
    @Override
    public String getJsonString() {
        return jsonString;
    }

    /**
     * @param jsonString
     *            the activityInfo to set
     */
    @Override
    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
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
}
