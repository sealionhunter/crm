package com.ustcsoft.gs.crm.webui.code.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.code.constant.CodeConstant;
import com.ustcsoft.gs.crm.webui.code.dao.CodeDao;
import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;

public class CodeDaoImpl implements CodeDao {

    private static final Log log = LogFactory.getLog(CodeDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * get a data by code
     * 
     * @param code
     * 
     * @return List<CodeDto>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CodeDto> getCodeByCode(String code) throws DataAccessException {
        log.debug("method getCodeByCode start!");
        List<CodeDto> list = hibernateTemplate.find(CodeConstant.GET_CODE_BY_CODE, code);
        if (list.size() != 0) {
            list.get(0).setCode(list.get(0).getCode().replaceAll(" ", ""));
        }
        log.debug("method getCodeByCode end!");
        return list;
    }

    /**
     * get a data by code and value
     * 
     * @param code
     * @param value
     * 
     * @return List<CodeDto>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CodeDto> getCode(String code, String value) throws DataAccessException {
        log.debug("method getCode start!");
        List<CodeDto> list = hibernateTemplate.find(CodeConstant.GET_CODE, code, value);
        if (list.size() != 0) {
            list.get(0).setCode(list.get(0).getCode().replaceAll(" ", ""));
        }
        log.debug("method getCode end!");
        return list;
    }

    /**
     * get all data except module and category
     * 
     * @return List<CodeDto>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<CodeDto> getAllData(final int currpage, final int limit) throws DataAccessException {
        log.debug("method getAllData start!");
        List<CodeDto> list = hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                return session.createQuery(CodeConstant.GET_ALL_DATA)
                        .setFirstResult((currpage - 1) * limit).setMaxResults(limit).list();
            }
        });
        for (int i = 0; i < list.size(); i++) {
            List<CodeDto> clist = hibernateTemplate.findByNamedParam(
                    CodeConstant.GET_CATEGORY_OF_CODE, CodeConstant.CODE, list.get(i).getCode()
                            .substring(0, 8)
                            + "%");
            list.get(i).setCategory(clist.get(0).getValue());
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getAllData end!");
        return list;
    }

    /**
     * insert or update a data
     * 
     * @param codeDto
     * 
     */
    @Override
    public void saveCode(CodeDto codeDto) throws DataAccessException {
        log.debug("method saveCode start!");
        hibernateTemplate.saveOrUpdate(codeDto);
        log.debug("method saveCode end!");

    }

    /**
     * delete a data
     * 
     * @param codeDto
     */
    @Override
    public void deleteCode(CodeDto codeDto) throws DataAccessException {
        log.debug("method deleteCode start!");
        hibernateTemplate.delete(codeDto);
        log.debug("method deleteCode end!");
    }

    /**
     * get all data of module
     * 
     * @return List<CodeDto>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CodeDto> getModule() throws DataAccessException {
        log.debug("method getModule start!");
        List<CodeDto> list = hibernateTemplate.find(CodeConstant.GET_ALL_MODULES);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCategory("模块");
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getModule end!");
        return list;
    }

    /**
     * get all data of category in a same module
     * 
     * @param module
     * 
     * @return List<CodeDto>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CodeDto> getCategory(String module) throws DataAccessException {
        log.debug("method getCategory start!");
        List<CodeDto> mlist = hibernateTemplate.find(CodeConstant.GET_CODE_BY_CODE, module);
        module = module + "%";
        List<CodeDto> list = hibernateTemplate.findByNamedParam(
                CodeConstant.GET_CATEGORIES_BY_MODULE, CodeConstant.CODE, module);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCategory(mlist.get(0).getValue());
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getCategory end!");
        return list;
    }

    /**
     * get all data in a same category
     * 
     * @param category
     * 
     * @return List<CodeDto>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CodeDto> getDataByCategory(String category, int currpage, int limit)
            throws DataAccessException {
        log.debug("method getDataByCategory start!");
        CodeDto bean = new CodeDto();
        bean.setCode(category + "%");
        List<CodeDto> list = hibernateTemplate.executeFind(new SuperHibernateCallback(
                CodeConstant.GET_DATA_BY_CATEGORY, currpage, bean, limit));
        for (int i = 0; i < list.size(); i++) {
            List<CodeDto> clist = hibernateTemplate.findByNamedParam(
                    CodeConstant.GET_CATEGORY_OF_CODE, CodeConstant.CODE, list.get(i).getCode()
                            .substring(0, 8)
                            + "%");
            list.get(i).setCategory(clist.get(0).getValue());
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getDataByCategory end!");
        return list;
    }

    /**
     * get all data in a same category
     * 
     * @param category
     * 
     * @return List<CodeDto>
     */
    @SuppressWarnings("unchecked")
    @Override
    public int getAllDataCount() throws DataAccessException {
        log.debug("method getAllDataCount start!");
        List<Integer> list = hibernateTemplate.find(CodeConstant.GET_COUNT_OF_ALL_DATA);
        Number number = list.get(0);
        log.debug("method getAllDataCount end!");
        return number.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getDataCount(String category) throws DataAccessException {
        log.debug("method getDataCount start!");
        List<Integer> list = hibernateTemplate.findByNamedParam(
                CodeConstant.GET_COUNT_OF_DATA_BY_CATEGORY, CodeConstant.CODE, category + "%");
        Number number = list.get(0);
        log.debug("method getDataCount end!");
        return number.intValue();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<CodeDto> getAllCategories(final int currpage, final int limit)
            throws DataAccessException {
        log.debug("method getAllCategories start!");
        List<CodeDto> list = hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                return session.createQuery(CodeConstant.GET_ALL_CATEGORIES)
                        .setFirstResult((currpage - 1) * limit).setMaxResults(limit).list();
            }
        });
        for (int i = 0; i < list.size(); i++) {
            List<CodeDto> clist = hibernateTemplate.findByNamedParam(
                    CodeConstant.GET_MODULE_OF_CATEGORY, CodeConstant.CODE, list.get(i).getCode()
                            .substring(0, 4)
                            + "%");
            list.get(i).setCategory(clist.get(0).getValue());
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getAllCategories end!");
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CodeDto> getCategoriesByCurrpage(int currpage, int limit, String module)
            throws DataAccessException {
        log.debug("method getCategoriesByCurrpage start!");
        CodeDto bean = new CodeDto();
        bean.setCode(module + "%");
        List<CodeDto> clist = hibernateTemplate.find(CodeConstant.GET_CODE_BY_CODE, module);
        List<CodeDto> list = hibernateTemplate.executeFind(new SuperHibernateCallback(
                CodeConstant.GET_CATEGORIES_BY_MODULE, currpage, bean, limit));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCategory(clist.get(0).getValue());
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getCategoriesByCurrpage end!");
        return list;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<CodeDto> getModulesByCurrpage(final int currpage, final int limit)
            throws DataAccessException {
        log.debug("method getModulesByCurrpage start!");
        List<CodeDto> list = hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                return session.createQuery(CodeConstant.GET_ALL_MODULES)
                        .setFirstResult((currpage - 1) * limit).setMaxResults(limit).list();
            }
        });
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCategory("模块");
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getModulesByCurrpage end!");
        return list;
    }

    /**
     * use this method to get the combobox you need the parameter is the code of
     * the combobox's category
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CodeDto> getComboBox(String category) throws DataAccessException {
        log.debug("method getComboBox start!");
        List<CodeDto> list = hibernateTemplate.findByNamedParam(CodeConstant.GET_DATA_BY_CATEGORY,
                CodeConstant.CODE, category + "%");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCode(list.get(i).getCode().replaceAll(" ", ""));
        }
        log.debug("method getComboBox end!");
        return list;
    }

}
