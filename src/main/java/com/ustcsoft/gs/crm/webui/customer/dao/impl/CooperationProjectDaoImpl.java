package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectShowBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CooperationProjectDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperationProjectDto;

/**
 * CooperationProjectDaoImplements
 * 
 * @author xujialong
 * 
 */
public class CooperationProjectDaoImpl implements CooperationProjectDao {

    private static final Log LOG = LogFactory.getLog(CooperationProjectDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * show or search project
     * 
     * @param searchFlag
     *            int 0 mean list,1 mean simple search,2 mean advanced search
     * @param searchBean
     *            CooperationProjectSearchBean cooperation project search values
     *            for searching
     * @param currpage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperation project search list for page
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> searchCooperationProject(int searchFlag,
            CooperationProjectSearchBean searchBean, int currpage, int limit) {
        LOG.debug("method searchCooperationProject start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        String project_HQL = null;
        String project_Count_HQL = null;
        List<CooperationProjectShowBean> projectList = null;
        if (searchFlag == 0) {
            project_HQL = CustomerConstant.SELECT_COOPERATIONPROJECT_HQL
                    + CustomerConstant.GET_COOPERATIONPROJECT_HQL;
            project_Count_HQL = CustomerConstant.SELECT_COOPERATIONPROJECT_COUNT_HQL
                    + CustomerConstant.GET_COOPERATIONPROJECT_HQL;
        } else if (searchFlag == 1) {
            project_HQL = CustomerConstant.SELECT_COOPERATIONPROJECT_HQL
                    + CustomerConstant.GET_COOPERATIONPROJECT_SIMPLE_HQL;
            project_Count_HQL = CustomerConstant.SELECT_COOPERATIONPROJECT_COUNT_HQL
                    + CustomerConstant.GET_COOPERATIONPROJECT_SIMPLE_HQL;
        } else {
            String projectType = searchBean.getProjectTypeSearch();
            String realBeginTimeMin = searchBean.getRealBeginTimeMin();
            String realBeginTimeMax = searchBean.getRealBeginTimeMax();
            String projectName = searchBean.getProjectNameSearch();
            String principalCooperator = searchBean.getPrincipalCooperatorSearch();
            String principalWe = searchBean.getPrincipalWeSearch();
            StringBuffer searchProject_HQL = new StringBuffer(
                    CustomerConstant.GET_COOPERATIONPROJECT_ADVANCED_HQL);

            if (!projectName.equals(CRMConstant.PER_CENT)) {
                searchProject_HQL.append(CustomerConstant.PROJECT_NAME_HQL);
            }
            if (!principalCooperator.equals(CRMConstant.PER_CENT)) {
                searchProject_HQL.append(CustomerConstant.PRINCIPAL_COOPERATOR_HQL);
            }
            if (!principalWe.equals(CRMConstant.PER_CENT)) {
                searchProject_HQL.append(CustomerConstant.PRINCIPAL_WE_HQL);
            }
            // date search
            if (realBeginTimeMin != null && realBeginTimeMin.length() != 0) {
                searchProject_HQL.append(CustomerConstant.PROJECT_DATE_MIN_HQL);
            }
            if (realBeginTimeMax != null && realBeginTimeMax.length() != 0) {
                searchProject_HQL.append(CustomerConstant.PROJECT_DATE_MAX_HQL);
            }
            // cooperation project scale search
            if (searchBean.getProjectScaleMin() != null) {
                searchProject_HQL.append(CustomerConstant.PROJECT_SCALE_MIN_HQL);
            }
            if (searchBean.getProjectScaleMax() != null) {
                searchProject_HQL.append(CustomerConstant.PROJECT_SCALE_MAX_HQL);
            }
            // projectType search
            if (projectType.length() != 0 && !CRMConstant.SEARCHALL_FLAG.equals(projectType)) {
                searchProject_HQL.append(CustomerConstant.PROJECT_TYPE_HQL);
            }
            project_HQL = CustomerConstant.SELECT_COOPERATIONPROJECT_HQL
                    + searchProject_HQL.toString();
            project_Count_HQL = CustomerConstant.SELECT_COOPERATIONPROJECT_COUNT_HQL
                    + searchProject_HQL.toString();
        }
        projectList = hibernateTemplate.executeFind(new SuperHibernateCallback(project_HQL,
                currpage, searchBean, limit));
        total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(project_Count_HQL, 0, searchBean, limit)).get(0);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, projectList);
        LOG.debug("method searchCooperationProject end!");
        return map;
    }

    /**
     * add or update cooperation project
     * 
     * @param cooperationProject
     *            CooperationProjectDto cooperation project dto for save or
     *            update
     */
    @Override
    public void updateCooperationProject(CooperationProjectDto cooperationProject) {
        LOG.debug("method updateCooperationProject start!");
        hibernateTemplate.saveOrUpdate(cooperationProject);
        LOG.debug("method updateCooperationProject end!");
    }

    /**
     * delete cooperation project about cooperationProjectID
     * 
     * @param cooperationProjectIDs
     *            String Strings includes all ids for del
     */
    @Override
    public void deleteCooperationProject(String cooperationProjectIDs) {
        LOG.debug("method deleteCooperationProject start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.DEL_PROJECT_HQL + cooperationProjectIDs);
        LOG.debug("method deleteCooperationProject end!");
    }

    /**
     * check name existed
     * 
     * @param cpd
     *            CooperationProjectDto Cooperation project dto
     * @return boolean,true mean name existing,false mean name not existing
     */
    @Override
    public boolean checkNameExisted(CooperationProjectDto cpd) {
        LOG.debug("method checkNameExisted start!");
        boolean nameExistedValid = false;
        Long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(CustomerConstant.CHECK_PROJECT_NAME_HQL, 0, cpd, 0))
                .get(0);
        if (total > 0) {
            nameExistedValid = true;
        } else {
            nameExistedValid = false;
        }
        LOG.debug("method checkNameExisted end!");
        return nameExistedValid;
    }
}