package com.ustcsoft.gs.crm.webui.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.activity.bean.ActivityBean;
import com.ustcsoft.gs.crm.webui.activity.bean.ActivitySearchBean;
import com.ustcsoft.gs.crm.webui.activity.dto.ActivityInfoDto;
import com.ustcsoft.gs.crm.webui.activity.service.ActivityInfoService;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * test class ActivityInfoServiceImpl
 * 
 * @author xuzhen
 * 
 */
public class ActivityInfoServiceImplTest extends CRMTest {

    private static final String TEST0 = "技术";
    private static final String TEST1 = "技术经验交流会";
    private static final String TEST2 = "高新";
    private static final String TEST3 = "01";
    private static final String TEST4 = "1";
    private static final String TEST5 = "2012-10-01";
    private static final String TEST6 = "2012-10-10";
    private static final String TOTAL = "total";
    private static final String ITEMS = "items";
    private static ActivityInfoService activityInfoService = null;
    ActivityInfoDto activity = new ActivityInfoDto();
    private Map<String, Object> map = null;
    List<ActivityBean> activityInfoList = null;
    private static ActivitySearchBean activitySearchBean = new ActivitySearchBean();
    private static int deleteId = 0;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        activityInfoService = (ActivityInfoService) CTX.getBean("activityInfoService");
    }

    @Before
    public void setUp() throws Exception {
        activity.setActivityName(TEST1);
        activity.setActivityPlace("");
        activity.setActivityType("01");
        activity.setActivityStartTime("");
        activity.setActivityEndTime("");
        activity.setActivityEmphasis("1");
        activity.setActivityRange("1");
        activity.setActivityLeader("小张");
        activity.setActivityModifer("小王");
        activity.setActivityPeriod("1");
        activity.setActivityState("1");
        activity.setCustomerName(new int[] { 1 });

        activitySearchBean.setSearchText(TEST0);
        activitySearchBean.setActivityName(TEST0);
        activitySearchBean.setActivityPlace(TEST2);
        String[] test1 = { TEST3 };
        String[] test2 = { TEST4 };
        activitySearchBean.setActivityType(test1);
        activitySearchBean.setActivityState(test2);
        activitySearchBean.setActivityRange(test2);
        activitySearchBean.setActivityStartTime(TEST5);
        activitySearchBean.setActivityEndTime(TEST6);
    }

    /**
     * test method updateActivity
     * 
     * @throws CRMDBException
     */
    @Test
    public void testUpdateActivity() throws CRMDBException {

        map = activityInfoService.getOrSearchActivity(0, null, 1, 25);
        int start = Integer.parseInt(map.get(TOTAL).toString());
        activityInfoService.updateActivity(activity);
        map = activityInfoService.getOrSearchActivity(0, null, 1, 25);
        int end = Integer.parseInt(map.get(TOTAL).toString());
        deleteId = activity.getActivityID();
        Assert.assertEquals(end - start, 1);
    }

    /**
     * test method getOrSearchActivity
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetOrSearchActivity() throws CRMDBException {
        map = activityInfoService.getOrSearchActivity(0, null, 1, 25);
        Assert.assertNotNull(map);

        map = activityInfoService.getOrSearchActivity(1, activitySearchBean, 1, 25);
        Assert.assertNotNull(map);
        activityInfoList = (List<ActivityBean>) map.get(ITEMS);
        ActivityBean result = null;
        if (activityInfoList.size() != 0) {
            for (int i = 0; i < activityInfoList.size(); i++) {
                result = activityInfoList.get(i);
                boolean b1 = result.getActivityName().contains(TEST0);
                boolean b2 = result.getActivityTypeName().contains(TEST0);
                boolean b3 = result.getActivityStartTime().contains(TEST0);
                boolean b4 = result.getActivityEndTime().contains(TEST0);
                boolean b5 = result.getActivityPlace().contains(TEST0);
                boolean b6 = result.getActivityStateName().contains(TEST0);
                boolean b7 = result.getActivityRangeName().contains(TEST0);
                Assert.assertEquals(true, b1 || b2 || b3 || b4 || b5 || b6 || b7);
            }
        }

        map = activityInfoService.getOrSearchActivity(2, activitySearchBean, 1, 25);
        Assert.assertNotNull(map);
        activityInfoList = (List<ActivityBean>) map.get(ITEMS);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date test5 = sdf.parse(TEST5);
            Date test6 = sdf.parse(TEST6);
            if (activityInfoList.size() != 0) {
                for (int i = 0; i < activityInfoList.size(); i++) {
                    result = activityInfoList.get(i);
                    Assert.assertSame(TEST0, result.getActivityName());
                    Assert.assertSame(TEST2, result.getActivityPlace());
                    Assert.assertEquals(TEST3, result.getActivityTypeName());
                    Assert.assertEquals(TEST4, result.getActivityStateName());
                    Assert.assertEquals(TEST4, result.getActivityRangeName());
                    Date startTime = sdf.parse(result.getActivityStartTime());
                    Date endTime = sdf.parse(result.getActivityEndTime());
                    Assert.assertEquals(true, startTime.after(test5));
                    Assert.assertEquals(true, endTime.before(test6));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * test method deleteActivity
     * 
     * @throws CRMDBException
     */
    @Test
    public final void testDeleteActivity() throws CRMDBException {
        map = activityInfoService.getOrSearchActivity(0, null, 1, 25);
        int start = Integer.parseInt(map.get(TOTAL).toString());
        activityInfoService.deleteActivity(String.valueOf(deleteId));
        map = activityInfoService.getOrSearchActivity(0, null, 1, 25);
        int end = Integer.parseInt(map.get(TOTAL).toString());
        Assert.assertEquals(start - end, 1);
    }

    /**
     * test exception for method updateSource
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testUpdateActivityThrowsCRMDBException() throws CRMDBException {
        ActivityInfoDto act = new ActivityInfoDto();
        act.setCustomerName(new int[] { 1 });
        activityInfoService.updateActivity(act);
    }

    /**
     * test exception for method getOrSearchActivity
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetOrSearchActivityThrowsCRMDBException() throws CRMDBException {
        ActivitySearchBean searchBean = new ActivitySearchBean();
        searchBean.setActivityEndTime("2012-06-0111");
        map = activityInfoService.getOrSearchActivity(2, searchBean, 1, 25);
    }

    /**
     * test exception for method deleteActivity
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testDeleteActivityThrowsCRMDBException() throws CRMDBException {
        activityInfoService.deleteActivity("");
    }

    @After
    public void tearDown() throws Exception {
    }

}
