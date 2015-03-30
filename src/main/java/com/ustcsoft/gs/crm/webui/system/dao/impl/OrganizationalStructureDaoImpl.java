package com.ustcsoft.gs.crm.webui.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.OrganizationalStructureDao;
import com.ustcsoft.gs.crm.webui.system.dto.OrganizationalStructureDto;

/**
 * 
 * @author jiaxu
 */
public class OrganizationalStructureDaoImpl implements OrganizationalStructureDao {

    private HibernateTemplate hibernateTemplate;

    /**
     * 
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * 
     * @param hibernateTemplate
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * this method will query all organization information from database and
     * manage
     * 
     * @return Map<String,Object>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> queryOrganizationStructure() {
        List<OrganizationalStructureDto> organizationalStructureDtos;
        List<OrganizationalStructureDto> oStructureDtos;
        List<OrganizationalStructureDto> nextStructureDtos;
        List<OrganizationalStructureDto> thisStructureDtos;
        OrganizationalStructureDto organizationalStructureDto;
        Map<String, Object> map = new HashMap<String, Object>();
        organizationalStructureDtos = hibernateTemplate.find(SystemConstant.QUERY_ALL_ORGANIZATION);
        int depth = Integer.parseInt(hibernateTemplate.find(SystemConstant.QUERY_MAX_DEPTH).get(0)
                .toString());
        for (int k = 1; k <= depth; k++) {
            List<OrganizationalStructureDto> list = new ArrayList<OrganizationalStructureDto>();
            for (int i = 0; i < organizationalStructureDtos.size(); i++) {
                if (organizationalStructureDtos.get(i).getDepth() == k) {
                    list.add(organizationalStructureDtos.get(i));
                }
            }
            map.put(k + "", list);
        }
        int count = 0;
        int preCount = 0;
        thisStructureDtos = (List<OrganizationalStructureDto>) map.get("2");
        for (int i = 2; i < depth; i++) {
            nextStructureDtos = (List<OrganizationalStructureDto>) map.get(i + 1 + "");
            count = 0;
            for (int j = 0; j < thisStructureDtos.size(); j++) {
                preCount = count;
                organizationalStructureDto = new OrganizationalStructureDto();
                if (thisStructureDtos.get(j).getDepartmentID() == -2) {
                    organizationalStructureDto.setDepartmentID(-2);
                    organizationalStructureDto.setFatherDepartmentID(-2);
                    organizationalStructureDto.setDepth(i + 1);
                    organizationalStructureDto.setDepartmentName(j + i + "");
                    nextStructureDtos.add(count, organizationalStructureDto);
                    count++;
                } else {
                    for (int k = 0; k < nextStructureDtos.size(); k++) {
                        if (thisStructureDtos.get(j).getDepartmentID() == nextStructureDtos.get(k)
                                .getFatherDepartmentID()) {
                            count++;
                        }
                    }
                }
                if (preCount == count) {
                    organizationalStructureDto.setDepartmentID(-2);
                    organizationalStructureDto.setFatherDepartmentID(-2);
                    organizationalStructureDto.setDepth(i + 1);
                    organizationalStructureDto.setDepartmentName(i + j + "");
                    nextStructureDtos.add(count, organizationalStructureDto);
                    count++;
                }
            }
            thisStructureDtos = nextStructureDtos;
        }
        oStructureDtos = new ArrayList<OrganizationalStructureDto>();
        for (int i = 1; i <= depth; i++) {
            organizationalStructureDtos = new ArrayList<OrganizationalStructureDto>();
            organizationalStructureDtos = (List<OrganizationalStructureDto>) map.get(i + "");
            for (int j = 0; j < organizationalStructureDtos.size(); j++) {
                oStructureDtos.add(organizationalStructureDtos.get(j));
            }
        }
        map = new HashMap<String, Object>();
        map.put("depth", depth);
        map.put("department", oStructureDtos);
        return map;
    }
}
