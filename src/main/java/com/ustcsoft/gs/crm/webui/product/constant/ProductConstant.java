package com.ustcsoft.gs.crm.webui.product.constant;

public class ProductConstant {
    public static final int SERVICE_PRODUCT = 1;
    public static final int PHYSICAL_PRODUCT = 0;
    /** 报价部分 */
    public static final int GROUPID = 0;
    public static final String PRICEMINDEFAULT = "0";
    public static final String GET_PRODUCT_RANGE_BYID = "from ProductPriceRangeDto priceRange where priceRange.productID = ? order by priceRange.id";
    public static final String SEARCH_PRODUCT_NAME = "from ProductDto product where product.name like ? ";
    public static final String SEARCH_COUNT_PRODUCT_NAME = "select count(*) from ProductDto product where product.name like ? ";
    public static final String SEARCH_PRODUCT_MINPRICE = "from ProductDto as product where product.price >= ? ";
    public static final String SEARCH_COUNT_PRODUCT_MINPRICE = "select count(*) from ProductDto as product where product.price >= ? ";
    public static final String SEARCH_PRODUCT_ADD_NAME = " and product.name like ? ";
    public static final String PERCENTSTRING = "%";
    public static final String EMPTYSTRING = "";
    public static final String SEARCH_PRODUCT_MAXPRICE = " and product.price <= ? ";
    public static final String SEARCH_PRODUCT_GROUPID = " and price.groupID = ? ";
    public static final String SEARCH_PRODUCT_MINDISCOUNT = " and price.discount >= ? ";
    public static final String SEARCH_PRODUCT_MAXDISCOUNT = " and price.discount <= ? ";
    public static final String RIGHT_PARENTHESIS = " )";
    public static final String DELETE_PRICE_RANGE_BYID = "delete from ProductPriceRangeDto price where price.productID in";
    public static final String SELECT_PRICE_RANGE = "from ProductPriceRangeDto price where price.productID = ? and price.groupID = ?";
    public static final String SEARCH_PRODUCT_PRICE = " and product.id in (select distinct price.productID from ProductPriceRangeDto price where price.groupID in (select group.groupID from GroupManagerDto group)";
    public static final String HEADER = "header";
    public static final String DATAINDEX = "dataIndex";
    public static final String RENDERER = "renderer";
    public static final String NAME = "name";
    public static final String COLUMMODLE = "columModle";
    public static final String FIELDSNAMES = "fieldsNames";
    public static final String ITEMS = "items";
    public static final String QUOTE = "'";
    public static final String SEARCH_PRODUCT_BYPRICE = " or product.price = ? ";
    public static final String NUMBERFORMAT = "[1-9][0-9]*|[1-9][0-9]*\\.[0-9]{1,2}|[0-9]\\.[0-9]{1,2}";
    public static final String DISCOUNTNUMBERFORMAT = "[1]\\.[0]{1,2}|[1]|[0]\\.[0-9]{1,2}";
    public static final String HIDDEN = "hidden";
    public static final String SORTABLE = "sortable";
    public static final String FLEX = "flex";
    public static final String MAXPRICE = "maxPrice";
    public static final String MINPRICE = "minPrice";
    public static final String MAXDISCOUNT = "maxDiscount";
    public static final String MINDISCOUNT = "minDiscount";
    public static final String DELETEPRICEBYID = "delete from ProductPriceRangeDto price where price.groupID = ? and price.productID = ?";
    public static final String PRODUCT_GET_PRODUCT_COUNT_PRICE = "select count(*) from ProductDto product where product.id in (select distinct price.productID from ProductPriceRangeDto price where price.groupID in (select group.groupID from GroupManagerDto group))";
    public static final String PRICE_GET_ALL = "from ProductDto product where product.id in (select distinct price.productID from ProductPriceRangeDto price where price.groupID in (select group.groupID from GroupManagerDto group))";

    /** 产品部分 */
    public static final String PRODUCT_DELETE = "delete from ProductDto product where product.id in";
    public static final String PRODUCT_GET = "from ProductPriceRangeDto price where price.productID =?";
    public static final String PRODUCT_GET_ALL = "from ProductDto product order by product.id DESC";
    public static final String PRODUCT_GET_ALL_PRICE = "from ProductPriceRangeDto price where price.productID =?";
    public static final String PRODUCT_GET_ALL_COUNT = "select count(*) from ProductDto product where product.id>0";
    public static final String PRODUCT_SEARCH_QUERY = "select count(*) from ProductDto";
    public static final String PRODUCT_SEARCH_LIKE = " or product.productID like ?";
    public static final String PRODUCT_SEARCH_LIKE_ID = " or product.productModel like ? order by product.id";
    public static final String PRODUCT_SEARCH_PRICE = "from ProductDto product where product.price >= ?";
    public static final String PRODUCT_SEARCH_NAME = " and product.name like ?";
    public static final String PRODUCT_SEARCH_PRODUCTID = " and product.productID like ?";
    public static final String PRODUCT_SEARCH_PRODUCTModel = " and product.productModel like ?";
    public static final String PRODUCT_SEARCH_CATEGORY = " and product.category = ?";
    public static final String PRODUCT_SEARCH_PRICES = " and product.price <= ?";
    public static final String WIDTH = "width";
    public static final String ALIGN = "align";
    public static final String PRODUCT_GET_ALL_PRICEBYUSER = "from ProductPriceRangeDto price where price.productID =? and price.groupID =?";
    public static final String MINWIDTH = "minWidth";
}
