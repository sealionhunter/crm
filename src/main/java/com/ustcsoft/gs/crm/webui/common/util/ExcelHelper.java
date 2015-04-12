package com.ustcsoft.gs.crm.webui.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;

public final class ExcelHelper<T> {

    public Workbook writeToExcel(InputStream template, T bean, String beanName)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(beanName, bean);

        XLSTransformer transformer = new XLSTransformer();
        Workbook wb = transformer.transformXLS(template, map);

        return wb;
    }

    public Workbook writeToExcel(InputStream template, List<T> beanList, String beanListName)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(beanListName, beanList);

        XLSTransformer transformer = new XLSTransformer();
        Workbook wb = transformer.transformXLS(template, map);

        return wb;
    }

    
}
