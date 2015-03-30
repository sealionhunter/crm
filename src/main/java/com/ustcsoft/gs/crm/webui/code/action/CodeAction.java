package com.ustcsoft.gs.crm.webui.code.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.ustcsoft.gs.crm.webui.code.constant.CodeConstant;
import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.code.service.CodeService;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

public class CodeAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(CodeAction.class);

    private Map<String, Object> map = new HashMap<String, Object>();

    private String mode = null;

    private String module = null;

    private String category = null;

    private String code = null;

    private String value = null;

    private int limit = 25;

    private int page = 1;

    private CodeService codeService = null;

    private CodeDto codeDto = new CodeDto();

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public CodeDto getCodeDto() {
        return codeDto;
    }

    public void setCodeDto(CodeDto codeDto) {
        this.codeDto = codeDto;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static Log getLog() {
        return log;
    }

    public CodeService getCodeService() {
        return codeService;
    }

    public void setCodeService(CodeService codeService) {
        this.codeService = codeService;
    }

    /**
     * use this method to get the combobox you need the parameter is the code of
     * the combobox's category
     * 
     * @return
     * @throws CRMDBException
     */
    public String getComboBox() throws CRMDBException {
        log.debug("mothod getComboBox starts!");
        List<CodeDto> list = codeService.getComboBox(code);
        map.put(CRMConstant.ITEMS, list);
        return SUCCESS;
    }

    /**
     * get a code by code
     * 
     * @return
     * @throws CRMDBException
     */
    public String getACode() throws CRMDBException {
        log.debug("mothod getACode starts!");
        List<CodeDto> list = codeService.getCodeByCode(code);
        map.put("value", list.get(0).getValue());
        return SUCCESS;
    }

    /**
     * get all data
     * 
     * @return
     * @throws CRMDBException
     */
    public String getAllData() throws CRMDBException {
        log.debug("mothod getAllData starts!");
        map = codeService.getAllData(page, limit);
        return SUCCESS;
    }

    /**
     * get data
     * 
     * @return
     * @throws CRMDBException
     */
    public String getData() throws CRMDBException {
        log.debug("mothod getData starts!");
        if (mode.equals("1")) {
            map = codeService.getModuleData(page, limit);
        } else if (mode.equals("2")) {
            if (!module.equals("") && !module.equals("0000")) {
                map = codeService.getCategoryData(page, limit, module);
            } else {
                List<CodeDto> list = codeService.getAllCategory(page, limit);
                map.put(CRMConstant.ITEMS, list);
                map.put("total", codeService.getCountOfAllCategory(1, 9999));
            }
        } else {
            if (category.equals("") || category.equals("0000")) {
                if (module.equals("") || module.equals("0000")) {
                    map = codeService.getAllData(page, limit);
                } else {
                    map = codeService.getData(page, limit, module);
                }
            } else {
                map = codeService.getData(page, limit, category);
            }
        }
        return SUCCESS;
    }

    /**
     * get module
     * 
     * @return
     * @throws CRMDBException
     */
    public String getModule() throws CRMDBException {
        log.debug("mothod getModule starts!");
        List<CodeDto> list = codeService.getModule();
        map.put(CRMConstant.ITEMS, list);
        return SUCCESS;
    }

    /**
     * get category
     * 
     * @return
     * @throws CRMDBException
     */
    public String getCategory() throws CRMDBException {
        log.debug("mothod getCategory starts!");
        if (code.equals("0000")) {
            List<CodeDto> list = codeService.getAllCategory(1, 9999);
            map.put(CRMConstant.ITEMS, list);
        } else {
            List<CodeDto> list = codeService.getCategory(code);
            map.put(CRMConstant.ITEMS, list);
        }
        return SUCCESS;
    }

    /**
     * add a code
     * 
     * @return
     * @throws CRMDBException
     */
    public String addCode() throws CRMDBException {
        log.debug("mothod addCode starts!");
        if (isValueExisted()) {
            map.put(CodeConstant.MSG, "valueExisted");
        } else {
            String newcode = createANewCode();
            if (newcode.equals(CodeConstant.OVERFLOW)) {
                map.put(CodeConstant.MSG, CodeConstant.OVERFLOW);
            } else {
                codeDto.setCode(newcode);
                codeDto.setValue(value);
                codeService.saveCode(codeDto);
            }
        }
        return SUCCESS;
    }

    /**
     * edit a code
     * 
     * @return
     * @throws CRMDBException
     */
    public String editCode() throws CRMDBException {
        log.debug("mothod getComboBox starts!");
        codeDto.setCode(code);
        codeDto.setValue(value);
        codeService.saveCode(codeDto);
        return SUCCESS;
    }

    /**
     * delete code
     * 
     * @return
     * @throws CRMDBException
     */
    public String deleteCode() throws CRMDBException {
        log.debug("mothod deleteCode starts!");
        String[] codes = code.split(",");
        int deleteFlag = 1;
        if (codes[0].length() == 4) {
            for (String code2 : codes) {
                if (codeService.getCategory(code2).size() != 0) {
                    map.put(CodeConstant.MSG, "used");
                    deleteFlag = 0;
                    break;
                }
            }
        }
        if (codes[0].length() == 8) {
            for (String code2 : codes) {
                if (code2.substring(0, 4).equals("0004")) {
                    map.put(CodeConstant.MSG, "nodelete");
                    deleteFlag = 0;
                    break;
                }
            }
            for (String code2 : codes) {
                if (codeService.getSizeOfDataByCategory(page, limit, code2) != 0) {
                    map.put(CodeConstant.MSG, "used");
                    deleteFlag = 0;
                    break;
                }
            }
        }
        if (deleteFlag == 1) {
            for (String code2 : codes) {
                codeService.deleteCode(codeService.getCodeByCode(code2).get(0));
            }
        }
        return SUCCESS;
    }

    /**
     * create a new code for add a code data
     * 
     * @return
     * @throws CRMDBException
     */
    public String createANewCode() throws CRMDBException {
        log.debug("mothod createANewCode starts!");
        int newcode = 0;
        int max = 0;
        if (code.equals("")) {
            List<CodeDto> list = codeService.getModule();
            if (list.size() != 0) {
                newcode = Integer.parseInt(list.get(list.size() - 1).getCode());
                max = Integer.parseInt(list.get(list.size() - 1).getCode().substring(0));
                if (max != 9999) {
                    newcode++;
                    if (newcode < 10) {
                        return "000" + String.valueOf(newcode);
                    } else if (newcode < 100) {
                        return "00" + String.valueOf(newcode);
                    } else if (newcode < 1000) {
                        return "0" + String.valueOf(newcode);
                    } else {
                        return String.valueOf(newcode);
                    }
                } else {
                    return CodeConstant.OVERFLOW;
                }
            } else {
                return code + "0001";
            }
        } else if (code.length() == 4) {
            List<CodeDto> list = codeService.getCategory(code);
            if (list.size() != 0) {
                newcode = Integer.parseInt(list.get(list.size() - 1).getCode());
                max = Integer.parseInt(list.get(list.size() - 1).getCode().substring(4));
            } else {
                return code + "0001";
            }
        } else if (code.length() == 8) {
            List<CodeDto> list = codeService.getDataByCategory(code);
            if (list.size() != 0) {
                newcode = Integer.parseInt(list.get(list.size() - 1).getCode());
                max = Integer.parseInt(list.get(list.size() - 1).getCode().substring(8));
            } else {
                return code + "0001";
            }
        } else {
            return "wrong";
        }
        if (max != 9999) {
            newcode++;
            if (Integer.parseInt(code.substring(0, 4)) < 10) {
                return "000" + String.valueOf(newcode);
            } else if (Integer.parseInt(code.substring(0, 4)) < 100) {
                return "00" + String.valueOf(newcode);
            } else if (Integer.parseInt(code.substring(0, 4)) < 1000) {
                return "0" + String.valueOf(newcode);
            } else {
                return String.valueOf(newcode);
            }
        } else {
            return CodeConstant.OVERFLOW;
        }
    }

    /**
     * Check whether the code exists
     * 
     * @return boolean
     * @throws CRMDBException
     */
    public boolean isValueExisted() throws CRMDBException {
        log.debug("mothod isValueExisted starts!");
        if (code.equals("")) {
            List<CodeDto> list = codeService.getModule();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getValue().equals(value)) {
                    return true;
                }
            }
        } else if (code.length() == 4) {
            List<CodeDto> list = codeService.getCategory(code);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getValue().equals(value)) {
                    return true;
                }
            }
        } else if (code.length() == 8) {
            List<CodeDto> list = codeService.getDataByCategory(code);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getValue().equals(value)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }
}
