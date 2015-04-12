package com.ustcsoft.gs.crm.webui.common.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.opensymphony.xwork2.ActionSupport;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.ExcelHelper;

public abstract class CRMExcelExportAction<T> extends ActionSupport {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2185278526114886048L;

    public static final String EXCEL_BEAN_NAME = "CRMBean";

    public static final String EXCEL_BEAN_LIST_NAME = "CRMBeanList";

    // download params
    private String downloadFileName = null;
    private InputStream excelStream = null;
    private String type = null;

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String exportExcel() throws CRMDBException {
        ByteArrayOutputStream baos = null;
        try {
            List<T> targetList = getExportTarget();
            ExcelHelper<T> helper = new ExcelHelper<T>();
            Workbook wb = null;
            if (targetList.size() == 1) {
                wb = helper.writeToExcel(getTemplate(), targetList.get(0), EXCEL_BEAN_NAME);
            } else {
                wb = helper.writeToExcel(getTemplate(), targetList, EXCEL_BEAN_LIST_NAME);
            }
            baos = new ByteArrayOutputStream();
            wb.write(baos);
            baos.flush();
            byte[] excelBytes = baos.toByteArray();
            excelStream = new ByteArrayInputStream(excelBytes, 0, excelBytes.length);

            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            downloadFileName = "客户信息_" + sf.format(new Date()) + ".xls";
            downloadFileName = new String(downloadFileName.getBytes(), "ISO8859-1");
        } catch (CRMDBException e) {
            throw e;
        } catch (Exception e) {
            throw new CRMDBException("导出Excel失败！", e);
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
            }
        }
        return SUCCESS;
    }

    public abstract List<T> getExportTarget() throws CRMDBException;

    public abstract InputStream getTemplate() throws IOException;
}
