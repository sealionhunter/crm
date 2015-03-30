package com.ustcsoft.gs.crm.webui.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.code.dao.CodeDao;
import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.code.service.CodeService;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

public class CodeServiceImpl implements CodeService {

    private static final Log log = LogFactory.getLog(CodeServiceImpl.class);

    private CodeDao codeDao;

    public CodeDao getCodeDao() {
        return codeDao;
    }

    public void setCodeDao(CodeDao codeDao) {
        this.codeDao = codeDao;
    }

    /**
     * get all data
     * 
     * @param currpage
     * @param limit
     * 
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getAllData(int currpage, int limit) throws CRMDBException {
        log.debug("method getAllData start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CodeDto> list = null;
        try {
            list = codeDao.getAllData(currpage, limit);
        } catch (DataAccessException e) {
            log.error("method getAllData DataAccessException", e);
            throw new CRMDBException(e);
        }
        map.put(CRMConstant.ITEMS, list);
        map.put(CRMConstant.TOTAL, codeDao.getAllDataCount());
        return map;
    }

    /**
     * get data by category
     * 
     * @param currpage
     * @param limit
     * @param category
     * 
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getData(int currpage, int limit, String category)
            throws CRMDBException {
        log.debug("method getData start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CodeDto> list = null;
        try {
            list = codeDao.getDataByCategory(category, currpage, limit);
        } catch (DataAccessException e) {
            log.error("method getData DataAccessException", e);
            throw new CRMDBException(e);
        }
        map.put(CRMConstant.ITEMS, list);
        map.put(CRMConstant.TOTAL, codeDao.getDataCount(category));
        return map;

    }

    /**
     * get all module
     * 
     * @return List<CodeDto>
     */
    @Override
    public List<CodeDto> getModule() throws CRMDBException {
        log.debug("method getModule start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getModule();
        } catch (DataAccessException e) {
            log.error("method getModule DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list;
    }

    /**
     * get category by module
     * 
     * @param module
     * @return List<CodeDto>
     */
    @Override
    public List<CodeDto> getCategory(String module) throws CRMDBException {
        log.debug("method getCategory start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getCategory(module);
        } catch (DataAccessException e) {
            log.error("method getCategory DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list;
    }

    /**
     * save a code
     */
    @Override
    public void saveCode(CodeDto codeDto) throws CRMDBException {
        log.debug("method saveCode start!");
        try {
            codeDao.saveCode(codeDto);
        } catch (DataAccessException e) {
            log.error("method saveCode DataAccessException", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * delete a code
     */
    @Override
    public void deleteCode(CodeDto codeDto) throws CRMDBException {
        log.debug("method deleteCode start!");
        try {
            codeDao.deleteCode(codeDto);
        } catch (DataAccessException e) {
            log.error("method deleteCode DataAccessException", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * get a code by code
     * 
     * @param code
     * 
     * @return List<CodeDto>
     */
    @Override
    public List<CodeDto> getCodeByCode(String code) throws CRMDBException {
        log.debug("method getCodeByCode start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getCodeByCode(code);
        } catch (DataAccessException e) {
            log.error("method getCodeByCode DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list;
    }

    /**
     * get a code by code and value
     * 
     * @param code
     * @param value
     * 
     * @return List<CodeDto>
     */
    @Override
    public List<CodeDto> getCode(String code, String value) throws CRMDBException {
        log.debug("method getCode start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getCode(code, value);
        } catch (DataAccessException e) {
            log.error("method getCode DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list;
    }

    /**
     * get module
     * 
     * @param currpage
     * @param limit
     * 
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getModuleData(int currpage, int limit) throws CRMDBException {
        log.debug("method getModuleData start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CodeDto> list = null;
        try {
            list = codeDao.getModulesByCurrpage(currpage, limit);
        } catch (DataAccessException e) {
            log.error("method getModuleData DataAccessException", e);
            throw new CRMDBException(e);
        }
        map.put(CRMConstant.ITEMS, list);
        map.put(CRMConstant.TOTAL, codeDao.getModule().size());
        return map;
    }

    /**
     * get category
     * 
     * @param currpage
     * @param limit
     * @param module
     * 
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getCategoryData(int currpage, int limit, String module)
            throws CRMDBException {
        log.debug("method getCategoryData start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CodeDto> list = null;
        try {
            list = codeDao.getCategoriesByCurrpage(currpage, limit, module);
        } catch (DataAccessException e) {
            log.error("method getCategoryData DataAccessException", e);
            throw new CRMDBException(e);
        }
        map.put(CRMConstant.ITEMS, list);
        map.put(CRMConstant.TOTAL, codeDao.getCategory(module).size());
        return map;
    }

    /**
     * get size of the data get by category
     * 
     * @param currpage
     * @param limit
     * @param category
     * 
     * @return int
     */
    @Override
    public int getSizeOfDataByCategory(int currpage, int limit, String category)
            throws CRMDBException {
        log.debug("method getSizeOfDataByCategory start!");
        int size;
        List<CodeDto> list = null;
        try {
            list = codeDao.getDataByCategory(category, currpage, limit);
        } catch (DataAccessException e) {
            log.error("method getSizeOfDataByCategory DataAccessException", e);
            throw new CRMDBException(e);
        }
        size = list.size();
        return size;
    }

    /**
     * get data by category
     * 
     * @param category
     * 
     * @return List<CodeDto>
     */
    @Override
    public List<CodeDto> getDataByCategory(String category) throws CRMDBException {
        log.debug("method getDataByCategory start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getDataByCategory(category, 1, 9999);
        } catch (DataAccessException e) {
            log.error("method getDataByCategory DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list;
    }

    /**
     * get category
     * 
     * @param currpage
     * @param limit
     * 
     * @return List<CodeDto>
     */
    @Override
    public List<CodeDto> getAllCategory(int currpage, int limit) throws CRMDBException {
        log.debug("method getAllCategory start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getAllCategories(currpage, limit);
        } catch (DataAccessException e) {
            log.error("method getAllCategory DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list;
    }

    /**
     * get size of the category
     * 
     * @param currpage
     * @param limit
     * 
     * @return int
     */
    @Override
    public int getCountOfAllCategory(int currpage, int limit) throws CRMDBException {
        log.debug("method getCountOfAllCategory start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getAllCategories(currpage, limit);
        } catch (DataAccessException e) {
            log.error("method getCountOfAllCategory DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list.size();
    }

    /**
     * get a combobox
     * 
     * @param category
     * 
     * @return List<CodeDto>
     */
    @Override
    public List<CodeDto> getComboBox(String category) throws CRMDBException {
        log.debug("method getComboBox start!");
        List<CodeDto> list = null;
        try {
            list = codeDao.getComboBox(category);
        } catch (DataAccessException e) {
            log.error("method getComboBox DataAccessException", e);
            throw new CRMDBException(e);
        }
        return list;
    }

}
