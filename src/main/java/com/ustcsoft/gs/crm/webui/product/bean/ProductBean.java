package com.ustcsoft.gs.crm.webui.product.bean;

import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;

public class ProductBean extends ProductDto {
    public ProductBean(String name, int id, String category, String productID, String productModel,
            String price, String description, String remark) {
        super.setName(name);
        super.setId(id);
        super.setCategory(category);
        super.setProductID(productID);
        super.setProductModel(productModel);
        super.setPrice(price);
        super.setDescription(description);
        super.setRemark(remark);
    }
}
