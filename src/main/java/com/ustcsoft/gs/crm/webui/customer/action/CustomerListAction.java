/*
 * Class name: CustomerListAction
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;

/**
 * For customer information's action operation
 * 
 * @author xujueyin and tangyunpeng
 * 
 */
public class CustomerListAction extends CRMAction {

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    private static final long serialVersionUID = 1L;

    private static final Log LOG = LogFactory.getLog(CustomerListAction.class);

    /** CustomerDto information which will be added or edited */
    private CustomerDto customerDto = null;

    /** used for get class CustomerService */
    private CustomerService customerService = null;

    private boolean flag = true;

    private boolean gonghai = false;

    private int customerID;

    private File attach;
    private String attachFileName;
    private String attachContentType;

    /**
     * validate updateCustomer method.
     * 
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public void validateUpdateCustomer() throws CRMDBException {

        customerDto = (CustomerDto) CRMUtils.jsonToBean(super.jsonString, CustomerDto.class);
        if (StringUtils.isBlank(customerDto.getCustomerAddr())) {
            addFieldError(CustomerConstant.CUSTOMER_ADDR, this.getText("customerAddr.invalid"));
        } else if (customerDto.getCustomerAddr().length() > 50) {
            addFieldError(CustomerConstant.CUSTOMER_ADDR, this.getText("customerAddrRegex.invalid"));
        }
        if (StringUtils.isBlank(customerDto.getCustomerName())) {
            addFieldError(CustomerConstant.CUSTOMER_NAME, this.getText("customerName.invalid"));
        } else if (customerDto.getCustomerName().length() > 50
                || customerDto.getCustomerName().length() < 2) {
            addFieldError(CustomerConstant.CUSTOMER_NAME, this.getText("customerName.invalid"));
        } else if (customerService.judgeCustomerName(customerDto)) {
            addFieldError(CustomerConstant.CUSTOMER_NAME, this.getText("customerNameExist.invalid"));
        }

        if (StringUtils.isBlank(customerDto.getCustomerStatement())) {
            addFieldError(CustomerConstant.CUSTOMER_STATEMENT,
                    this.getText("customerStatement.invalid"));
        } else if (customerDto.getCustomerStatement().length() > 12) {
            addFieldError(CustomerConstant.CUSTOMER_STATEMENT,
                    this.getText("customerStatement.invalid"));
        }
        if (StringUtils.isBlank(customerDto.getFee())) {
            addFieldError(CustomerConstant.FEE, this.getText("fee.invalid"));
        } else if (customerDto.getFee().length() > 12) {
            addFieldError(CustomerConstant.FEE, this.getText("feeRegex.invalid"));
        }
        if (StringUtils.isBlank(customerDto.getIndustry())) {
            addFieldError(CustomerConstant.INDUSTRY, this.getText("industry.invalid"));
        } else if (customerDto.getIndustry().length() > 12) {
            addFieldError(CustomerConstant.INDUSTRY, this.getText("industryRegex.invalid"));
        }
        if (StringUtils.isBlank(customerDto.getCustomerType())) {
            addFieldError(CustomerConstant.CUSTOMER_TYPE, this.getText("customerType.invalid"));
        } else if (customerDto.getCustomerType().length() > 12) {
            this.getText("customerTypeRegex.invalid");
        }
        if (StringUtils.isBlank(customerDto.getScale())) {
            addFieldError(CustomerConstant.SCALE, this.getText("scale.invalid"));
        } else if (customerDto.getCustomerStatement().length() > 12) {
            addFieldError(CustomerConstant.SCALE, this.getText("scaleRegex.invalid"));
        }
        if (StringUtils.isBlank(customerDto.getValueEvaluate())) {
            addFieldError(CustomerConstant.VALUE_EVALUATE, this.getText("valueEvaluate.invalid"));
        } else if (customerDto.getCustomerStatement().length() > 12) {
            addFieldError(CustomerConstant.VALUE_EVALUATE,
                    this.getText("valueEvaluateRegex.invalid"));
        }
        if (customerDto.getDescriptions().length() > 1024) {
            addFieldError(CustomerConstant.DESCRIPTIONS,
                    this.getText("workDescriptionsLength.invalid"));
        }
        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        } else if(flag) {
            boolean mayRepeat = false;
            String name = delCommonWord(customerDto.getCustomerName());
            char[] chArray = name.toCharArray();
            List<String> existNameList = customerService.getAllCustomerName(customerDto.getCustomerID());

            String mayRepeatedName = null;
            for (String n : existNameList) {
                String existName = delCommonWord(n);
                int matchedNum = 0;
                for (char ch : chArray) {
                    if (existName.indexOf(ch) != -1) {
                        matchedNum++;
                    }
                }
                if (matchedNum >= 3 || (chArray.length < 3 && chArray.length == matchedNum)) {
                    mayRepeat = true;
                    mayRepeatedName = n;
                    break;
                }
            }

            if (mayRepeat) {
                String msgFormat = "客户【%s】和已存在的客户【%s】可能重复，是否重新填写客户姓名？";
                addFieldError("CusotmerNameRepeat", String.format(msgFormat, customerDto.getCustomerName(), mayRepeatedName));
            }

            map.putAll(getFieldErrors());
        }
    }

    private String delCommonWord(String name) {
        for (String w : CustomerConstant.CUSTOMER_COMMON_WORD) {
            if (name.contains(w)) {
                name = name.replaceAll(w, "");
            }
        }
        return name;
    }

    /**
     * Query and get customer.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String getAllCustomer() throws CRMDBException {
        LOG.debug("method execute start.");
        CustomerSearchBean searchBean = null;
        if (searchFlag != 0) {
            searchBean = (CustomerSearchBean) CRMUtils.jsonToBean(super.jsonString,
                    CustomerSearchBean.class);
        }
        map = customerService.searchOrGetAllCustomer(super.searchFlag, searchBean, super.page,
                super.limit, userID, gonghai);
        LOG.debug("method execute end.");
        return SUCCESS;
    }

    /**
     * Delete customer and cooperator resume
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    public String deleteCustomer() throws CRMDBException {
        LOG.debug("method deleteCustomer start.");
        map = customerService.deleteCustomer(super.jsonString);
        LOG.debug("method deleteCustomer end.");
        return SUCCESS;
    }

    /**
     * Delete customer and cooperator resume
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    public String receiveCustomer() throws CRMDBException {
        LOG.debug("method deleteCustomer start.");
        map = customerService.receiveCustomer(customerID, userID);
        LOG.debug("method deleteCustomer end.");
        return SUCCESS;
    }

    /**
     * Update or save the customer.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    public String updateCustomer() throws CRMDBException {

        LOG.debug("method updateCustomer start.");
        /* TODO
        try {
            String uid = UUID.random().toString();
            String attachFilePath = "customer/" + customerDto.getCustomerID + "/" + uid + "_" + attachFileName;
            File dest = new File(CRMConstant.DOCUMENT_ROOT, attachFilePath);
            FileUtils.copyFile(this.attach, dest);
            customerDto.setAttach(attachFilePath);
        } catch (Exception ex) {
            LOG.error("File upload failed!", ex);
            this.map.put("errorMessages", "File upload failed!");
        }
        */
        customerService.updateCustomer(customerDto);
        LOG.debug("method updateCustomer start.");
        return SUCCESS;
    }

    /**
     * @return the customerService
     */
    public CustomerService getCustomerService() {
        return customerService;
    }

    /**
     * @param customerService
     *            the customerService to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isGonghai() {
        return gonghai;
    }

    public void setGonghai(boolean gonghai) {
        this.gonghai = gonghai;
    }

}
