package com.ustcsoft.gs.crm.webui.code.constant;

public class CodeConstant {

    public static final String MSG = "msg";
    public static final String OVERFLOW = "overflow";
    public static final String CODE = "code";
    public static final String GET_ALL_DATA = "from CodeDto as cd where length(cd.code) = 12";
    public static final String GET_COUNT_OF_ALL_DATA = "select count(*) from CodeDto as cd where length(cd.code) = 12";
    public static final String GET_ALL_MODULES = "from CodeDto as cd where length(cd.code) = 4";
    public static final String GET_ALL_CATEGORIES = "from CodeDto as cd where length(cd.code) = 8";
    public static final String GET_CODE_BY_CODE = "from CodeDto as cd where cd.code = ?";
    public static final String GET_CODE = "from CodeDto as cd where cd.code = ? and cd.value = ?";
    public static final String GET_CATEGORIES_BY_MODULE = "from CodeDto as cd where length(cd.code) = 8 and cd.code like:code";
    public static final String GET_DATA_BY_CATEGORY = "from CodeDto as cd where length(cd.code) = 12 and cd.code like:code";
    public static final String GET_COUNT_OF_DATA_BY_CATEGORY = "select count(*) from CodeDto as cd where length(cd.code) = 12 and cd.code like:code";
    public static final String GET_CATEGORY_OF_CODE = "from CodeDto as cd where length(cd.code) = 8 and cd.code like:code";
    public static final String GET_MODULE_OF_CATEGORY = "from CodeDto as cd where length(cd.code) = 4 and cd.code like:code";
}
