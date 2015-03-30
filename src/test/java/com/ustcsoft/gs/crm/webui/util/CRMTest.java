/**
 * 
 */
package com.ustcsoft.gs.crm.webui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shenkaichuan
 * 
 */
public class CRMTest {
    protected static final BeanFactory CTX = new ClassPathXmlApplicationContext(
            "WEB-INF/context/applicationContext.xml");
    public int[] searchFlag = { 0, 1, 2, 3 };
    public int[] submitFlag = { 0, 1, 2, 3 };
    public int currpage = 1;
    public String contactIDs_1 = "1";
    public String contactIDs_2 = "2,3";
    public Map<String, Object> map = null;
    public int userID = 1;
    public int limit = 25;
    public String date = null;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * judge if searchTime is or is not before judgeTime
     * 
     * @param searchTime
     *            searchTime
     * @param judgeTime
     *            judgeTime
     * @return true,if searchTime is before judgeTime or judgeTime is null
     * @throws ParseException
     *             date parse fail
     */
    public boolean dateAfter(String searchTime, String judgeTime) throws ParseException {
        Calendar searchDate = Calendar.getInstance();
        Calendar judgeDate = Calendar.getInstance();
        boolean x = true;
        searchDate.setTime(sdf.parse(searchTime.substring(0, 11) + "00:00"));
        if (judgeTime != null) {
            judgeDate.setTime(sdf.parse(judgeTime.substring(0, 16)));
        }
        if (judgeDate != null) {
            x = !judgeDate.before(searchDate);
        }
        return x;
    }

    /**
     * judge if searchTime is or is not after judgeTime
     * 
     * @param searchTime
     *            searchTime
     * @param judgeTime
     *            judgeTime
     * @param flag
     *            flag,searchTime is YYYY-MM-DD 00:00 when flag is not 1; else
     *            searchTime is YYYY-MM-DD 00:00,DD add one day
     * @return true,if searchTime is after judgeTime or judgeTime is null
     * @throws ParseException
     *             date parse fail
     */
    public boolean dateBefore(String searchTime, String judgeTime, int flag) throws ParseException {
        Calendar searchDate = Calendar.getInstance();
        Calendar judgeDate = Calendar.getInstance();
        boolean x = true;
        if (flag == 1) {
            searchDate.setTime(sdf.parse(searchTime));
        } else {
            searchDate.setTime(sdf.parse(searchTime.substring(0, 11) + "00:00"));
        }
        searchDate.add(Calendar.DAY_OF_YEAR, 1);
        if (judgeTime != null) {
            judgeDate.setTime(sdf.parse(judgeTime.substring(0, 16)));
        }

        if (judgeDate != null) {
            x = !judgeDate.after(searchDate);
        }
        return x;
    }

}
