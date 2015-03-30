package com.ustcsoft.gs.crm.webui.code.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;

public interface CodeDao {

    public List<CodeDto> getCodeByCode(String code) throws DataAccessException;

    public List<CodeDto> getCode(String code, String value) throws DataAccessException;

    public List<CodeDto> getAllData(final int currpage, final int limit) throws DataAccessException;

    public List<CodeDto> getAllCategories(final int currpage, final int limit)
            throws DataAccessException;

    public List<CodeDto> getCategoriesByCurrpage(final int currpage, final int limit, String module)
            throws DataAccessException;

    public void saveCode(CodeDto codeDto) throws DataAccessException;

    public void deleteCode(CodeDto codeDto) throws DataAccessException;

    public List<CodeDto> getModule() throws DataAccessException;

    public List<CodeDto> getModulesByCurrpage(final int currpage, final int limit)
            throws DataAccessException;

    public List<CodeDto> getCategory(String module) throws DataAccessException;

    public List<CodeDto> getComboBox(String category) throws DataAccessException;

    public List<CodeDto> getDataByCategory(String category, final int currpage, final int limit)
            throws DataAccessException;

    public int getAllDataCount() throws DataAccessException;

    public int getDataCount(String category) throws DataAccessException;

}
