package com.ustcsoft.gs.crm.webui.sales.constant;

/**
 * 
 * @author shiben
 * 
 */
public class SalesEventFlowConstant {
    public static final String FIND_SALES_EVENT_FLOW_CODE = "select new com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean"
            + "(sfcode.code,sfcode.value,sfcode.category,sfcode.sort,cod.value as categoryName) "
            + "from SalesEventFlowCodeDto as sfcode,CodeDto as cod where sfcode.category = cod.code order by sfcode.sort asc";

    public static final String UPDATE_SORT_BY_PLUS = "update SalesEventFlowCodeDto sfcode set sfcode.sort = sfcode.sort +1 where sfcode.sort >= ?";

    public static final String UPDATE_SORT_BY_MINUS = "update SalesEventFlowCodeDto sfcode set sfcode.sort = sfcode.sort -1 where sfcode.sort>"
            + "any(select sfcode.sort from SalesEventFlowCodeDto sfcode where sfcode.code = ?)";
    public static final String SALES_EVENT_NAME_REPEAT = "select count(*) from SalesEventFlowCodeDto sfcode where sfcode.value =:value and sfcode.code != :code";

    public static final String DELETE_SALES_EVENT_DEMAND = "delete from SalesEventFlowDto sf where sf.status = ?";

    public static final String FIND_COUNT_SALES_EVENT_BY_STATUS = "select count(*) from SalesEventDto sales where sales.status = ?";

    public static final String FIND_SALES_EVENT_FLOW_CATEGORY = "select new map(trim(code.code) as category,code.value as categoryName) from CodeDto code "
            + "where code.code = '00040002' or code.code = '00040003'";

    public static final String GET_CODE_BY_CATEGORY = "select new com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean"
            + "(sfcode.code,sfcode.value,sfcode.category,sfcode.sort,cod.value as categoryName) "
            + "from SalesEventFlowCodeDto as sfcode,CodeDto as cod where sfcode.category = cod.code and sfcode.category = ? order by sfcode.sort asc";

    public static final String UPDATE_SORT_FROM_MAX_TO_MIN = "update SalesEventFlowCodeDto sfcode set sfcode.sort = sfcode.sort+1 where sfcode.sort <? and sfcode.sort>=?";
    public static final String UPDATE_SORT_FROM_MIN_TO_MAX = "update SalesEventFlowCodeDto sfcode set sfcode.sort = sfcode.sort-1 where sfcode.sort <=? and sfcode.sort>?";
}
