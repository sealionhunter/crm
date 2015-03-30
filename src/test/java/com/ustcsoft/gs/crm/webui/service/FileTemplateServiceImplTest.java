package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.FileTemplateDto;
import com.ustcsoft.gs.crm.webui.customer.service.FileTemplateService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class FileTemplateServiceImplTest extends CRMTest {

    private static FileTemplateService service = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        service = (FileTemplateService) CTX.getBean("fileTemplateService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * 测试更新方法-添加
     * 
     * @throws CRMDBException
     */
    @Test
    public void testUpdateFileTemplateAdd() throws CRMDBException {
        FileTemplateSearchBean searchBean = new FileTemplateSearchBean();
        FileTemplateDto fileTemplateDto = new FileTemplateDto();
        fileTemplateDto.setFileTemplateID(0);
        fileTemplateDto.setFileTemplateName("测试1");
        fileTemplateDto.setFileTemplateAddDate("2012-01-01");
        fileTemplateDto.setFileTemplateEditDate("2012-01-01");
        fileTemplateDto.setType(3);
        searchBean.setType(3);
        searchBean.setFileTemplateName("测试1");
        service.updateFileTemplate(fileTemplateDto);
        Map<String, Object> map = service.getFileTemplate(0, searchBean, 1, 25);
        assertEquals((long) 1, map.get("total"));
    }

    /**
     * 测试更新方法-编辑
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings({ "unchecked" })
    @Test
    public void testUpdateFileTemplateEdit() throws CRMDBException {
        FileTemplateSearchBean searchBean = new FileTemplateSearchBean();
        FileTemplateDto fileTemplateDto = new FileTemplateDto();
        searchBean.setSearchText("");
        searchBean.setType(3);
        Map<String, Object> map = service.getFileTemplate(0, searchBean, 1, 25);
        List<FileTemplateDto> list = (List<FileTemplateDto>) map.get("items");
        FileTemplateDto result = list.get(0);
        fileTemplateDto.setFileTemplateName("编辑1");
        fileTemplateDto.setFileTemplateID(result.getFileTemplateID());
        fileTemplateDto.setFileTemplateAddDate("2012-01-01");
        fileTemplateDto.setFileTemplateEditDate("2012-01-01");
        fileTemplateDto.setType(3);
        service.updateFileTemplate(fileTemplateDto);
        Map<String, Object> map2 = service.getFileTemplate(0, searchBean, 1, 25);
        List<FileTemplateDto> list2 = (List<FileTemplateDto>) map2.get("items");
        FileTemplateDto result2 = list2.get(0);
        assertEquals("编辑1", result2.getFileTemplateName());
    }

    /**
     * 测试检查用户名重复方法-用户名不重复
     * 
     * @throws CRMDBException
     */
    @Test
    public void testCheckFileTemplateNameExistTrue() throws CRMDBException {
        FileTemplateDto fileTemplateDto = new FileTemplateDto();
        fileTemplateDto.setFileTemplateName("名称");
        fileTemplateDto.setType(3);
        assertEquals(true, service.checkFileTemplateNameExist(fileTemplateDto));
    }

    /**
     * 测试检查用户名重复方法-用户名重复
     * 
     * @throws CRMDBException
     */
    @Test
    public void testCheckFileTemplateNameExistFalse() throws CRMDBException {
        FileTemplateDto fileTemplateDto = new FileTemplateDto();
        fileTemplateDto.setFileTemplateName("编辑1");
        fileTemplateDto.setType(3);
        fileTemplateDto.setFileTemplateAddDate("");
        fileTemplateDto.setFileTemplateEditDate("");
        fileTemplateDto.setFileTemplateID(0);
        assertEquals(false, service.checkFileTemplateNameExist(fileTemplateDto));
    }

    /**
     * 测试删除方法
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testDelFileTemplate() throws CRMDBException {
        FileTemplateSearchBean searchBean = new FileTemplateSearchBean();
        FileTemplateDto fileTemplateDto = new FileTemplateDto();
        fileTemplateDto.setFileTemplateID(0);
        fileTemplateDto.setFileTemplateName("测试2");
        fileTemplateDto.setFileTemplateAddDate("");
        fileTemplateDto.setFileTemplateEditDate("");
        fileTemplateDto.setType(3);
        searchBean.setType(3);
        service.updateFileTemplate(fileTemplateDto);
        Map<String, Object> map = service.getFileTemplate(0, searchBean, 1, 25);
        List<FileTemplateDto> list = (List<FileTemplateDto>) map.get("items");
        FileTemplateDto result = list.get(1);
        assertEquals((long) 2, map.get("total"));
        service.delFileTemplate(result.getFileTemplateID() + "");
        assertEquals((long) 1, service.getFileTemplate(0, searchBean, 1, 25).get("total"));
    }

    /**
     * 测试查询方法-查询所有
     * 
     * @throws CRMDBException
     */
    @Test
    public void testGetFileTemplateAll() throws CRMDBException {
        FileTemplateSearchBean searchBean = new FileTemplateSearchBean();
        searchBean.setType(3);
        assertEquals((long) 1, service.getFileTemplate(0, searchBean, 1, 25).get("total"));
    }

    /**
     * 测试查询方法-模糊查询
     * 
     * @throws CRMDBException
     */
    @Test
    public void testGetFileTemplateSimple() throws CRMDBException {
        FileTemplateSearchBean searchBean = new FileTemplateSearchBean();
        searchBean.setSearchText("编辑1");
        searchBean.setType(3);
        assertEquals((long) 1, service.getFileTemplate(1, searchBean, 1, 25).get("total"));
        searchBean.setSearchText("测试2");
        searchBean.setType(3);
        assertEquals((long) 0, service.getFileTemplate(1, searchBean, 1, 25).get("total"));
    }

    /**
     * 测试查询方法-高级查询
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetFileTemplateAllSuper() throws CRMDBException {
        FileTemplateSearchBean searchBean = new FileTemplateSearchBean();
        searchBean.setFileTemplateName("编辑1");
        searchBean.setFileTemplateAddDateStart("2000-01-01");
        searchBean.setFileTemplateAddDateEnd("2050-01-01");
        searchBean.setFileTemplateEditDateStart("2000-01-01");
        searchBean.setFileTemplateEditDateEnd("2050-01-01");
        searchBean.setFileTemplateDescriptions("");
        searchBean.setType(3);
        assertEquals((long) 1, service.getFileTemplate(2, searchBean, 1, 25).get("total"));
        searchBean.setFileTemplateName("条件");
        assertEquals((long) 0, service.getFileTemplate(2, searchBean, 1, 25).get("total"));
        searchBean.setType(3);
        Map<String, Object> map = service.getFileTemplate(0, searchBean, 1, 25);
        List<FileTemplateDto> list = (List<FileTemplateDto>) map.get("items");
        FileTemplateDto result = list.get(0);
        service.delFileTemplate(result.getFileTemplateID() + "");
    }

    /**
     * 测试查询方法-异常
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetFileTemplateAllException() throws CRMDBException {
        FileTemplateSearchBean searchBean = new FileTemplateSearchBean();
        searchBean.setType(3);
        service.getFileTemplate(0, searchBean, -1, 25);
    }

    /**
     * 测试删除方法-异常
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testDelFileTemplateException() throws CRMDBException {
        service.delFileTemplate("rwe");
    }

    /**
     * 测试检查用户名重复方法-异常
     * 
     * @throws CRMDBException
     */
    // @Test(expected = CRMDBException.class)
    // public void testCheckFileTemplateNameExistException() throws
    // CRMDBException {
    // FileTemplateDto fileTemplateDto = new FileTemplateDto();
    // fileTemplateDto.setFileTemplateName("名称");
    // fileTemplateDto.setType(1);
    // fileTemplateDto.setFileTemplateAddDate("");
    // fileTemplateDto.setFileTemplateEditDate("");
    // fileTemplateDto.setFileTemplateID(0);
    // service.checkFileTemplateNameExist(fileTemplateDto);
    // }

    /**
     * 测试更新方法-添加异常
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testUpdateFileTemplateAddException() throws CRMDBException {
        FileTemplateDto fileTemplateDto = new FileTemplateDto();
        fileTemplateDto.setFileTemplateID(0);
        fileTemplateDto.setFileTemplateName("9");
        fileTemplateDto.setFileTemplateAddDate("2012-01-01rwrwr");
        fileTemplateDto.setFileTemplateEditDate("2012-01-01");
        fileTemplateDto.setType(1);
        service.updateFileTemplate(fileTemplateDto);
    }
}