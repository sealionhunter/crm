package com.ustcsoft.gs.crm.webui.common.constant;

public class CRMConstant {

    public static final String USER_ID = "userID";
    public static final String LEFT_PARENTHESIS = "(";
    public static final String RIGHT_PARENTHESIS = ")";
    public static final String BACKSLASH = "\\";
    public static final String BRACE = "{";
    public static final String LEFT_BRACKET = "[";
    public static final String RIGHT_BRACKET = "]";
    public static final String UNDERLINE = "_";
    public static final String SINGLEQUOTE = "'";
    public static final String COMMA_SYMBOL = ",";
    public static final String PER_CENT = "%";
    public static final String DOUBLE_PER_CENT = "%%";
    public static final String VALIDATE = "validate";
    public static final String ITEMS = "items";
    public static final String TOTAL = "total";
    public static final String FALSE = "false";
    public static final String SEARCHTEXT = "searchText";
    public static final String SUCCESS = "success";
    public static final String ID = "ID";
    public static final String ORDER_SIMPLE_QUERY = "simple";
    public static final String ORDER_SUPER_QUERY = "super";
    public static final String SEARCHALL_FLAG = "00";

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int MAX_LENGTH = 2048;
    public static final int AREA_MAX_LENGTH = 20;
    public static final int COMPOSITECOMPDEFENSE_MAX_LENGTH = 100;
    public static final int PAGE_SIZE_ANALY = 15;
    public static final int PAGE_SIZE = 25;
    public static final int LENGTH_1 = 50;
    public static final int LENGTH_2 = 1024;
    public static final int CONTRACTLENGTH_1 = 50;
    public static final int CONTRACTLENGTH_2 = 1024;

    public static final String BR = "<br>";
    public static final String MSG = "msg";
    public static final String REPLACE_1 = "-";
    public static final String REPLACE_2 = " ";
    public static final String REPLACE_3 = ":";
    public static final String SPACE = "";
    public static final String LIKESPACE = "%%";

    public static final String CREATEFILEERROR = "create folder error";
    public static final String SIMPLEDATEFORMAT = "yyyy-MM-dd HH:mm";
    public static final String SIMPLEDATEFORMAT_1 = "yyyy-MM-dd";
    // Regex
    public static final String DATE_REGEX = "([1-2]\\d\\d\\d)-((0\\d)|(1[1-2]))-(([1-2]\\d)|(3[0-1]))";
    public static final String DATE_TIME_REG = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
    public static final String DATE_REG = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    public static final String TIME_REG = "^([01][0-9]|2[0-3]):[0-5][0-9]$";
    public static final String DATE_REGEX_COOPERATOR = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)   (([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    public static final String COMBO_REGEX = "\\d{1,2}";
    public static final String WEBSITE_REGEX = "[a-zA-z]+://[^\\s]*";
    public static final String TELEPHONE_REGEX = "(^(\\d{3}-|\\d{4}-)?(\\d{7,8})$)|(^1[3-8][0-9]{9}$)";
    public static final String EMAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    public static final String FAX_REGEX = "0\\d{2,3}-\\d{5,9}|0\\d{2,3}-\\d{5,9}";
    public static final String REMOVE_IDS_REGEX = "^\\d+(, \\d+)*$";
    public static final String PROJECT_NUMBER_RANGE_REGEX = "\\d+(-\\d+){0,1}";
    public static final String COMBO_REG = "^\\d+$";
    public static final String PHONE_REG = "(^(0\\d{2,3}-)?(\\d{7,8})$)";
    public static final String DEPARTMENT_PHONE_REG = "(^(0\\d{2,3}-)?(\\d{7,8})(-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))?$)";
    public static final String MOBILE_REG = "(^1[3-8][0-9]{9}$)";
    public static final String EMAIL_REG = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

}
