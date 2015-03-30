Ext.define('CRM.controller.productManagement.Product', {
    extend: 'Ext.app.Controller',
    views: [ 'productManagement.product.ProductList', 'productManagement.product.ProductCard', 'productManagement.product.ProductDetail',
            'productManagement.product.ProductAddForm', 'productManagement.product.ProductEditForm', 'productManagement.product.ProductPriceAddForm',
            'productManagement.price.ProductPrice' ],
    stores: [ 'productManagement.Product', 'systemManagement.userManagement.Group', 'productManagement.Discount' ],
    models: [ 'productManagement.Product', 'productManagement.Discount' ],
    init: function() {
        this.control({
            'productlist': {
                itemdblclick: this.editProduct,
                selectionchange: this.changeBtn
            },
            'productlist toolbar button[id=productAddBtn]': {
                click: this.addProduct
            },
            'textfield': {
                blur: utils.trimSpace
            },
            'textarea': {
                blur: utils.trimSpace
            },
            'productlist toolbar button[id=productEditBtn]': {
                click: this.editProductBtn
            },
            'productlist toolbar button[id=productDelBtn]': {
                click: this.deleteProduct
            },
            'productlist button[action=queryBtn]': {
                click: this.productQuery
            },
            'productlist toolbar button[action=superQueryBtn]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'productaddform [action=submitProductAddBtn]': {
                click: this.submitProductAdd
            },
            'productaddform [action=resetProductAddBtn]': {
                click: this.resetProductAddBtn
            },
            'productaddform [action=cancelProductAddBtn]': {
                click: this.cancelProductAddBtn
            },
            'productaddform [id=productCategory]': {
                change: this.categoryChange
            },
            'productaddform textfield[name=price]': {
                blur: this.priceBlur
            },
            'producteditform textfield[name=price]': {
                blur: this.priceBlur
            },
            'producteditform [action=resetProductEditBtn]': {
                click: this.resetProductEditBtn
            },
            'producteditform [action=cancelProductEditBtn]': {
                click: this.cancelProductEditBtn
            },
            'producteditform [action=submitProductEditBtn]': {
                click: this.submitProductEdit
            },
            'productlist button[action=setProductPriceBtn]': {
                click: this.setPrice
            },
            'productpriceaddform button[action=submitPriceAddBtn]': {
                click: this.submitPriceAddBtn
            },
            'productpriceaddform button[action=resetPriceAddBtn]': {
                click: this.resetPriceAddBtn
            },
            'productpriceaddform button[action=cancelPriceAddBtn]': {
                click: this.cancelPriceAddBtn
            }
        });
    },
    viewInit: function(flag) {
        var ProductCard = Ext.getCmp('productCard');
        if (typeof (ProductCard) == 'undefined') {
            ProductCard = Ext.widget('productcard');
            Ext.getCmp('centercard').insert(1, ProductCard);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('productCard');
        Ext.getCmp('productList').getStore().setProxy(new Ext.data.proxy.Ajax({
            url: 'getAllProduct.action',
            reader: {
                type: 'json',
                root: 'items',
                totalProperty: 'total'
            }
        }));
        utils.hideSuperQuery(Ext.getCmp('productList'));
        Ext.getCmp('category').getStore().load({
            params: {
                code: '00050001'
            },
            callback: function(records, operation, success) {
                Ext.getCmp('category').getStore().insert(0, {
                    value: '- 不限 -',
                    code: "0"
                });
                Ext.getCmp('productList').getStore().loadPage(1);
            }
        });
        Ext.getCmp('category').setRawValue('- 不限 -');
        Ext.getCmp('category').setValue("0");
        return ProductCard;
    },
    changeBtn: function(sm, selections) {
        Ext.getCmp('productEditBtn').setDisabled(selections.length != 1);
        Ext.getCmp('productDelBtn').setDisabled(selections.length == 0);
        Ext.getCmp('productPriceBtn').setDisabled(selections.length != 1);
        Ext.getCmp('setProductPriceBtn').setDisabled(selections.length != 1);
        if (selections.length != 1) {
            Ext.getCmp('productDetail').hide();
        } else {
            Ext.getCmp('productDetail').show();
            var record = Ext.getCmp('productList').getSelectionModel().getSelection()[0];
            Ext.getCmp('productDetail').getForm().loadRecord(record);
            var name = Ext.getCmp('productDetail').down('[name = "name"]');
            name.setValue(Ext.String.htmlDecode(name.getValue()));
            if (record.get("category") == "000500010001") {
                Ext.getCmp('detailCategory').setValue('服务类产品');
            } else {
                // 显示产品型号
                Ext.getCmp('detailProdouctModel').show();
                Ext.getCmp('detailCategory').setValue('实体产品');
            }
        }
    },
    priceBlur: function(f) {
        var v = f.getValue();
        if ((/^[1-9][0-9]*$/.test(v) || /^[1-9][0-9]*\.[0-9]{1,2}$/.test(v) || /^[0-9]\.[0-9]{1,2}$/.test(v)) && !/^0\.0{1,2}$/.test(v)) {
            if (v.indexOf('.') == -1) {
                f.setValue(v + '.00');
            } else if (v.indexOf('.') == v.length - 2) {
                f.setValue(v + '0');
            }
        }
        if (v > 999999999999) {
            f.markInvalid("产品价格不能超过<999999999999.00>元！");
        }
    },
    addProduct: function(sm, selections) {
        Ext.widget('productaddform');
    },
    categoryChange: function(field, newValue, oldValue, e) {
        if (newValue == "000500010001") {
            Ext.getCmp('productmodel').setDisabled(true);
            Ext.getCmp('productmodel').hide();
            Ext.getCmp('productmodel').setValue('');
            Ext.getCmp('productID').setValue(Ext.Date.format(new Date(), '\\F\\WYmdHisu'));
        } else {
            Ext.getCmp('productmodel').setDisabled(false);
            Ext.getCmp('productmodel').show();
            Ext.getCmp('productID').setValue(Ext.Date.format(new Date(), '\\S\\TYmdHisu'));
        }
    },
    editProduct: function(sm, selections) {
        var grid = Ext.getCmp('productList');
        var checkRecords = grid.getSelectionModel().getSelection();
        if (checkRecords.length == 1) {
            var productEidt = Ext.widget('producteditform');
            productEidt.down('form').loadRecord(checkRecords[0]);
            var editname = productEidt.down('form').down('[name = "name"]');
            editname.setValue(Ext.String.htmlDecode(editname.getValue()));
            if (checkRecords[0].get("category") == "000500010001") {
                Ext.getCmp('productmodel').disable(true);
                Ext.getCmp('productmodel').hide(true);
                Ext.getCmp('productCategory').setValue('服务类产品');
                productEidt.setHeight(320);
            } else {
                Ext.getCmp('productCategory').setValue('实体产品');
                productEidt.setHeight(350);
            }
        } else {
            Ext.alert("notice", "编辑时，请选择一行");
        }
    },
    editProductBtn: function(button) {
        var grid = Ext.getCmp('productList');
        var checkRecords = grid.getSelectionModel().getSelection();
        if (checkRecords.length == 1) {
            var productEidt = Ext.widget('producteditform');
            productEidt.down('form').loadRecord(checkRecords[0]);
            var editname = productEidt.down('form').down('[name = "name"]');
            editname.setValue(Ext.String.htmlDecode(editname.getValue()));
            if (checkRecords[0].get("category") == "000500010001") {
                Ext.getCmp('productmodel').disable(true);
                Ext.getCmp('productmodel').hide(true);
                Ext.getCmp('productCategory').setValue('服务类产品');
                productEidt.setHeight(320);
            } else {
                Ext.getCmp('productCategory').setValue('实体产品');
                productEidt.setHeight(350);
            }
        } else {
            Ext.alert("notice", "编辑时，请选择一行");
        }
    },
    submitProductEdit: function(button) {
        var form = button.up('window').down('form');
        var productInfo = form.getValues();
        if (form.getForm().isValid() && form.down('[name="price"]').getValue() <= 999999999999) {
            // 将添加的产品提交到服务器
            Ext.Ajax.request({
                url: 'addOrUpdateProduct.action',
                params: {
                    jsonString: Ext.encode(productInfo)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    button.up('window').close();
                    Ext.crm.msg("编辑成功", "");
                    Ext.getCmp('productList').getStore().load();
                },
                failure: function(resp, opts) {
                    messageBox.alert("编辑失败!请联系管理员！");
                }
            });
        } else {
            messageBox.alert("提示", '输入信息有误，请检查输入信息！');
            if (form.down('[name="price"]').getValue() > 999999999999) {
                form.down('[name="price"]').markInvalid("产品价格不能超过<999999999999.00>元！");
            }
        }
    },
    deleteProduct: function(button) {
        var grid = Ext.getCmp('productList');
        utils.delRecordsCheck(grid, 'deletesProduct.action', "id");
    },
    openOrCloseSuperQueryBtn: function(button) {
        var store = Ext.getCmp('productList').getStore();
        store.setProxy(new Ext.data.proxy.Ajax({
            url: 'getAllProduct.action',
            reader: {
                type: 'json',
                root: 'items',
                totalProperty: 'total'
            }
        }));
        utils.openOrCloseSuperQueryBtn(button);
        Ext.getCmp('category').setRawValue('- 不限 -');
        Ext.getCmp('category').setValue("0");
    },
    productQuery: function(button) {
        if ((Ext.getCmp('productAdvanced').isHidden() && Ext.getCmp("productSearchText").isValid())
                || (!Ext.getCmp('productAdvanced').isHidden() && Ext.getCmp('productAdvanced').getForm().isValid())) {
            var store = Ext.getCmp('productList').getStore();
            var searchText = 'searchText:Ext.getCmp("productSearchText").getValue(),';
            var productName = 'productName:Ext.getCmp("productName").getValue(),';
            var productID = 'productID:Ext.getCmp("productIDSearch").getValue(),';
            var productModel = 'productModel:Ext.getCmp("productModel").getValue(),';
            var category = 'category:Ext.getCmp("category").getValue(),';
            var minPrice = 'minPrice:Ext.getCmp("minPrice").getValue(),';
            var maxPrice = 'maxPrice:Ext.getCmp("maxPrice").getValue()';
            var searchString = '{' + searchText + productID + productModel + category + productName + minPrice + maxPrice + '}';
            var ProductSearchBean = Ext.decode(searchString);
            var searchFlag = 1;
            if (!Ext.getCmp('productAdvanced').isHidden()) {
                searchFlag = 2;
            }
            store.setProxy(new Ext.data.proxy.Ajax({
                type: 'ajax',
                url: 'productSearch.action',
                reader: {
                    type: 'json',
                    root: 'items',
                    totalProperty: 'total'
                }
            }));
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: Ext.encode(ProductSearchBean)
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
                callback: function() {
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    },
    submitProductAdd: function(button) {
        var form = button.up('window').down('form');
        if (form.getForm().isValid() && form.down('[name="price"]').getValue() <= 999999999999) {
            var productInfo = form.getValues();
            // 将添加的产品提交到服务器
            Ext.Ajax.request({
                url: 'addOrUpdateProduct.action',
                params: {
                    jsonString: Ext.encode(productInfo)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    button.up('window').close();
                    Ext.crm.msg("添加成功", "");
                    Ext.getCmp('productList').getStore().loadPage(1);
                },
                failure: function(resp, opts) {
                    messageBox.alert("添加失败，请联系管理员！");
                }
            });
        } else {
            messageBox.alert("提示", '输入信息有误，请检查输入信息！');
            if (form.down('[name="price"]').getValue() > 999999999999) {
                form.down('[name="price"]').markInvalid("产品价格不能超过<999999999999.00>元!");
            }
        }
    },
    resetProductAddBtn: function(button) {
        button.up('window').close();
        Ext.widget('productaddform');
    },
    cancelProductAddBtn: function(button) {
        button.up('window').close();
    },
    resetProductEditBtn: function(button) {
        var productCategory = Ext.getCmp('productCategory').getValue();
        button.up('window').down('form').getForm().reset();
        var grid = Ext.getCmp('productList');
        var checkRecords = grid.getSelectionModel().getSelection();
        if (checkRecords.length == 1) {
            button.up('window').down('form').loadRecord(checkRecords[0]);
            Ext.getCmp('productCategory').setValue(productCategory);
            if (checkRecords[0].get("category") == 1) {
                Ext.getCmp('productmodel').disable(true);
            }
            var editname = button.up('window').down('form').down('[name = "name"]');
            editname.setValue(Ext.String.htmlDecode(editname.getValue()));
        } else {
            messageBox.alert("提示", "编辑时，请选择一行");
        }
    },
    cancelProductEditBtn: function(button) {
        button.up('window').close();
    },
    setPrice: function(button) {
        var priceaddform = Ext.getCmp('productpriceaddform');
        if (typeof (priceaddform) == 'undefined') {
            priceaddform = Ext.widget('productpriceaddform');
        }
        priceaddform.down('form').removeAll();
        priceaddform.setTitle('设置产品的折扣');
        var record = Ext.getCmp('productList').getSelectionModel().getSelection()[0];
        Ext.Ajax.request({
            url: 'getProductDiscountPrice.action',
            params: {
                groupID: 0,
                productID: record.get('id')
            },
            method: 'POST',
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var groups = Ext.JSON.decode(response.responseText).groupList.items;
                var priceRanges = Ext.JSON.decode(response.responseText).priceRangeList;
                var maxLength = 4;
                for ( var i = 0; i < groups.length; i++) {
                    if (groups[i].groupName.length > maxLength) {
                        maxLength = groups[i].groupName.length;
                    }
                }
                productName = Ext.create('Ext.form.field.Text');
                productName.labelWidth = maxLength * 12 + 40;
                productName.fieldLabel = "产品名称";
                productName.name = "name";
                productName.margin = '5 5 5 10';
                productName.columnWidth = 1;
                productName.readOnly = true;
                productPrice = Ext.create('Ext.form.field.Text');
                productPrice.labelWidth = maxLength * 12 + 40;
                productPrice.fieldLabel = "标准报价（元）";
                productPrice.name = "price";
                productPrice.margin = '5 5 5 10';
                productPrice.columnWidth = 1;
                productPrice.readOnly = true;
                priceaddform.down('form').add(productName);
                priceaddform.down('form').add(productPrice);
                priceaddform.down('form').loadRecord(record);
                var nameText = priceaddform.down('form').down('[name="name"]');
                nameText.setValue(Ext.String.htmlDecode(nameText.getValue()));
                for ( var i = 0; i < groups.length; i++) {
                    var price = Ext.widget('productprice');
                    price.name = "group" + groups[i].groupID;
                    price.id = "groupID" + groups[i].groupID;
                    for ( var j = 0; j < priceRanges.length; j++) {
                        if (groups[i].groupID == priceRanges[j].groupID) {
                            price.setRawValue(priceRanges[j].discount);
                        }
                    }
                    price.labelWidth = maxLength * 12 + 40;
                    price.fieldLabel = groups[i].groupName + "折扣";
                    priceaddform.down('form').add(price);
                }
                if (groups.length <= 4) {
                    priceaddform.down('form').setHeight(groups.length * 32 + 84);
                    priceaddform.setHeight(groups.length * 32 + 155);
                } else {
                    priceaddform.down('form').setHeight(212);
                    priceaddform.setHeight(283);
                }
            }
        });
    },
    submitPriceAddBtn: function(button) {
        var form = Ext.getCmp('priceform');
        function setVal(fieldId, val) {
            if (fieldId != 'validate' && fieldId != 'priceRangeList' && fieldId != 'groupList') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            var value = form.getValues();
            var product = Ext.getCmp('productList').getSelectionModel().getSelection()[0];
            var productId = product.get('id');
            var priceRangeList = [];
            var i = 0;
            for ( var group in value) {
                i++;
                if (i <= 2) {
                    continue;
                }
                var discount;
                if (eval("value." + group) == "") {
                    discount = "-1";
                } else {
                    discount = eval("value." + group);
                }
                var priceRange = {
                    "groupID": group.replace("group", ""),
                    "discount": discount,
                    "productID": "" + productId
                };
                priceRangeList[priceRangeList.length] = priceRange;
            }
            Ext.Ajax.request({
                url: 'setProductDiscountPrice.action',
                params: {
                    jsonString: Ext.encode(priceRangeList),
                    productID: productId
                },
                method: 'POST',
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText) || '';
                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        Ext.crm.msg("设置成功", "");
                        button.up('window').close();
                    }
                }
            });
        }
    },

    resetPriceAddBtn: function(button) {
        var priceaddform = Ext.getCmp('productpriceaddform');
        button.up('window').down('form').getForm().reset();
        var record = Ext.getCmp('productList').getSelectionModel().getSelection()[0];
        priceaddform.down('form').loadRecord(record);
        var nameText = priceaddform.down('form').down('[name="name"]');
        nameText.setValue(Ext.String.htmlDecode(nameText.getValue()));
    },

    cancelPriceAddBtn: function(button) {
        button.up('window').close();
    }
});