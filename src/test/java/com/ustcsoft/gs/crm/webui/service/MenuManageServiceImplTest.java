package com.ustcsoft.gs.crm.webui.service;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.service.MenuManageService;

/**
 * MenuManageService junit test
 * 
 * @author wangzhanxu
 * 
 */
public class MenuManageServiceImplTest {
    /** get bean factory */
    protected static final BeanFactory CTX = new ClassPathXmlApplicationContext(
            "WEB-INF/context/applicationContext.xml");
    private static MenuManageService menuManageService = null;
    private static HibernateTemplate hibernateTemplate = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        menuManageService = (MenuManageService) CTX.getBean("menuManageService");
        hibernateTemplate = (HibernateTemplate) CTX.getBean("hibernateTemplate");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * test editMenuText method of MenuManageServiceImpl class
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test(expected = CRMDBException.class)
    public void testEditMenuText() throws CRMDBException {
        int id = 1;
        String text = "kehuguanli";
        menuManageService.editMenuText(id, text);
        List<String> textList = hibernateTemplate
                .find("select td.text as t from TreeDto as td where td.id=" + id);
        Assert.assertEquals(text, textList.get(0));
        text = "客户管理";
        menuManageService.editMenuText(id, text);
        textList = hibernateTemplate.find("select td.text as t from TreeDto as td where td.id="
                + id);
        Assert.assertEquals(text, textList.get(0));
        menuManageService
                .editMenuText(
                        100000,
                        "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
    }
}
