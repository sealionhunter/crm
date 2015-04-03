package com.ustcsoft.gs.crm.webui.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;

public class CRMUtils {

    /**
     * 
     * @param jsonString
     * @param beanClass
     * @return object json to bean
     */
    public static Object jsonToBean(String jsonString, Class<?> beanClass) {
        JSONObject json = JSONObject.fromObject(jsonString);
        Object object = JSONObject.toBean(json, beanClass);
        return object;
    }

    /**
     * 
     * @param str
     */
    public static String getStringForDelete(String str) {
        String newStr = CRMConstant.LEFT_PARENTHESIS + str + CRMConstant.RIGHT_PARENTHESIS;
        return newStr;
    }

    /**
     * 
     * @param str
     */
    public static Integer[] getDeleteIds(String str) {
        String[] ids = str.split(",");
        Integer[] intIds = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            intIds[i] = Integer.parseInt(ids[i].trim());
        }
        return intIds;
    }

    /**
     * date format check
     * 
     * @param str
     *            date which is checked
     * @return flag true is that date format check pass,else fail
     */
    public static boolean dateCheck(String str) {
        boolean flag = false;
        if (str == null || str.length() == 0) {
            flag = true;
        } else {
            flag = str.matches(CRMConstant.DATE_REG);
        }
        return flag;
    }

    /**
     * time format check
     * 
     * @param str
     *            time which is checked
     * @return flag true is that time format check pass,else fail
     */
    public static boolean timeCheck(String str) {
        return str.matches(CRMConstant.TIME_REG);
    }

    /**
     * judge start is or isn't after end
     * 
     * @param start
     *            start date
     * @param end
     *            end date
     * @return flag true is that start isn't after end or one other is
     *         null/"",else that start is after end
     */
    public static boolean dateJudge(String start, String end) {
        boolean flag = false;
        if (end == null || end.length() == 0 || start == null || start.length() == 0) {
            flag = true;
        } else if (dateCheck(start) && dateCheck(end)) {
            int s = Integer.parseInt(start.replaceAll(CRMConstant.REPLACE_1, CRMConstant.SPACE));
            int e = Integer.parseInt(end.replaceAll(CRMConstant.REPLACE_1, CRMConstant.SPACE));
            flag = s <= e;
        }
        return flag;
    }

    /**
     * judge start is or isn't after end
     * 
     * @param start
     *            start time
     * @param end
     *            end time
     * @return flag true is that start isn't after end or one other is
     *         null/"",else that start is after end
     */
    public static boolean timeJudge(String start, String end) {
        boolean flag = false;
        if (end == null || end.length() == 0 || start == null || start.length() == 0) {
            flag = true;
        } else if (timeCheck(start) && timeCheck(end)) {
            long s = Long.parseLong(start.replaceAll(CRMConstant.REPLACE_2, CRMConstant.SPACE)
                    .replaceAll(CRMConstant.REPLACE_3, CRMConstant.SPACE));
            long e = Long.parseLong(end.replaceAll(CRMConstant.REPLACE_2, CRMConstant.SPACE)
                    .replaceAll(CRMConstant.REPLACE_3, CRMConstant.SPACE));
            flag = s <= e;
        }
        return flag;
    }

    /**
     * judge start is or isn't after now date
     * 
     * @param start
     *            date
     * @return flag true is that start isn't after now date ,else that start is
     *         after now date
     */
    public static boolean dateJudge(String start) {
        String now = new SimpleDateFormat(CRMConstant.SIMPLEDATEFORMAT_1).format(new Date());
        return dateJudge(start, now);
    }

    /**
     * s is trimed which s is null
     * 
     * @param s
     * @return s
     */
    public static String trim(String s) {
        if (s == null) {
            return s;
        } else {
            return s.trim();
        }
    }

    /**
     * s(SearchBean) is trimed which s is null
     * 
     * @param s
     *            (SearchBean)
     * @return s (SearchBean)
     */
    public static String trimSearch(String s) {
        String change = null;
        if (StringUtils.isBlank(s)) {
            change = CRMConstant.PER_CENT;
        } else {
            s = s.replace(CRMConstant.BACKSLASH + CRMConstant.LEFT_BRACKET,
                    CRMConstant.LEFT_BRACKET).replace(CRMConstant.BACKSLASH + CRMConstant.BRACE,
                    CRMConstant.BRACE);
            change = CRMConstant.PER_CENT
                    + s.trim()
                            .replaceAll(
                                    CRMConstant.BACKSLASH + CRMConstant.LEFT_BRACKET,
                                    CRMConstant.LEFT_BRACKET + CRMConstant.LEFT_BRACKET
                                            + CRMConstant.RIGHT_BRACKET)
                            .replace(
                                    CRMConstant.PER_CENT,
                                    CRMConstant.LEFT_BRACKET + CRMConstant.PER_CENT
                                            + CRMConstant.RIGHT_BRACKET)
                            .replace(
                                    CRMConstant.UNDERLINE,
                                    CRMConstant.LEFT_BRACKET + CRMConstant.UNDERLINE
                                            + CRMConstant.RIGHT_BRACKET) + CRMConstant.PER_CENT;
        }
        return change;
    }

    /**
     * 
     * create file
     * 
     * @param request
     * @param type
     * @param name
     * @throws CRMException
     */
    public static synchronized String createDoc(final HttpServletRequest request,
            final String type, String name) throws CRMException {
        String crmPath = request.getSession().getServletContext().getRealPath("/");
        Date nowTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddkkmmss");
        String formatNowTime = format.format(nowTime);
        try {
            name = URLDecoder.decode(name, "utf8");
        } catch (UnsupportedEncodingException e1) {
            throw new CRMException(CRMConstant.CREATEFILEERROR);
        }
        String filePath = type + "file/" + name + "_" + formatNowTime + ".doc";
        String path = crmPath + filePath;
        File folderpath = new File(crmPath + type + "file\\");
        if (!folderpath.exists()) {
            boolean flag = folderpath.mkdirs();
            if (!flag) {
                throw new CRMException(CRMConstant.CREATEFILEERROR);
            }
        }

        String info = request.getParameter(type + "Value");
        if (info == null) {
            info = request.getParameter("contractValue");
        }
        info = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' "
                + "xmlns='http://www.w3.org/TR/REC-html40'> <head><meta http-equiv=Content-Type content='text/html; "
                + "charset=utf-8'><meta name=ProgId content=Word.Document><meta name=Generator content='Microsoft Word 11'>"
                + "<meta name=Originator content='Microsoft Word 11'></head>"
                + "<body>"
                + info
                + "</body>" + "</html>";

        File file = new File(path);
        PrintWriter printWrier = null;
        try {
            printWrier = new PrintWriter(file, "utf-8");
            printWrier.print(info);
        } catch (FileNotFoundException e) {
            throw new CRMException(e);
        } catch (UnsupportedEncodingException e) {
            throw new CRMException(e);
        } finally {
            if (printWrier != null) {
                printWrier.close();
            }
        }
        return filePath;
    }

    /**
     * Get today
     * 
     * @return date Today
     */
    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        // Set the time format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // get the current time
        String date = sdf.format(calendar.getTime());
        return date;
    }

    /**
     * get current time
     * @return the return value represents current time
     */
    public static String currentTimeAsString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * update customer updateTime
     * 
     * @param ht HibernateTemplate
     */
    public static void setCustomerUpdateTime(HibernateTemplate ht) {
        CustomerDto dto = new CustomerDto();
        dto.setUpdateTime(currentTimeAsString());
        ht.saveOrUpdate(dto);
    }
}
