package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dao.OrganizationalStructureDao;
import com.ustcsoft.gs.crm.webui.system.dto.OrganizationalStructureDto;
import com.ustcsoft.gs.crm.webui.system.service.impl.OrganizationalStructureServiceImpl;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * 
 * @author jiaxu
 * 
 */
public class OrgnizationalStructureServiceImplTest extends CRMTest {
    private static OrganizationalStructureServiceImpl organizationalStructureServiceImpl = null;
    private Map<String, Object> map = new HashMap<String, Object>();

    /**
     * get bean from spring bean factory before class.
     */
    @BeforeClass
    public final static void setUpBeforeClass() {
        organizationalStructureServiceImpl = (OrganizationalStructureServiceImpl) CTX
                .getBean("organizationalStructureService");
    }

    @Before
    @After
    public void tearDown() {

    }

    /**
     * test the depth of queryOrganizationStructure.
     * 
     * @throws CRMDBException
     */
    @Test
    public void testQueryOrganizationStructureDepth() throws CRMDBException {
        map = organizationalStructureServiceImpl.queryOrganizationStructure();
        assertEquals(Integer.parseInt(map.get("depth").toString()) > 0, true);
    }

    /**
     * test the dtos of queryOrganizationStructure.
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testQueryOrganizationStructureDto() throws CRMDBException {
        map = organizationalStructureServiceImpl.queryOrganizationStructure();
        assertEquals(((List<OrganizationalStructureDto>) map.get("department")).get(0)
                .getDepartmentName() != null, true);
    }

    /**
     * test the CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testQueryOrganizationStructureThrowsCRMDBException() {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrganizationalStructureDao.class,
                "queryOrganizationStructure", new EmptyResultDataAccessException(0));
        try {
            organizationalStructureServiceImpl.queryOrganizationStructure();
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

}
