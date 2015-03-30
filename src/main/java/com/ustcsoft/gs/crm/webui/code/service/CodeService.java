package com.ustcsoft.gs.crm.webui.code.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

public interface CodeService {

    public Map<String, Object> getAllData(int currpage, int limit) throws CRMDBException;

    public Map<String, Object> getData(int currpage, int limit, String category)
            throws CRMDBException;

    public Map<String, Object> getModuleData(int currpage, int limit) throws CRMDBException;

    public Map<String, Object> getCategoryData(int currpage, int limit, String category)
            throws CRMDBException;

    public int getSizeOfDataByCategory(int currpage, int limit, String category)
            throws CRMDBException;

    public List<CodeDto> getModule() throws CRMDBException;

    public List<CodeDto> getCategory(String module) throws CRMDBException;

    public List<CodeDto> getAllCategory(int currpage, int limit) throws CRMDBException;

    public int getCountOfAllCategory(int currpage, int limit) throws CRMDBException;

    public List<CodeDto> getDataByCategory(String category) throws CRMDBException;

    public void saveCode(CodeDto codeDto) throws CRMDBException;

    public void deleteCode(CodeDto codeDto) throws CRMDBException;

    public List<CodeDto> getComboBox(String category) throws CRMDBException;

    public List<CodeDto> getCodeByCode(String code) throws CRMDBException;

    public List<CodeDto> getCode(String code, String value) throws CRMDBException;

}
