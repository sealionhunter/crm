package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesTrackBean;
import com.ustcsoft.gs.crm.webui.sales.dao.impl.SalesTrackDaoImpl;
import com.ustcsoft.gs.crm.webui.sales.service.SalesTrackService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class SalesTrackServiceImplTest extends CRMTest {
    private SalesTrackService salesTrackService = null;

    @Before
    public void setUp() throws Exception {
        salesTrackService = (SalesTrackService) CTX.getBean("salesTrackService");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetAllSalesTrack() throws CRMDBException {
        map = salesTrackService.getAllSalesTrack("0");
        List<SalesTrackBean> list = new ArrayList<SalesTrackBean>();
        list = (List<SalesTrackBean>) map.get("items");
        int count = list.size();
        assertEquals(count, 0);
    }

    @Test(expected = CRMDBException.class)
    public void testGetAllSalesTrack_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesTrackDaoImpl.class, "getAllSalesTrack",
                new EmptyResultDataAccessException(0));
        salesTrackService.getAllSalesTrack("0");
    }
}
