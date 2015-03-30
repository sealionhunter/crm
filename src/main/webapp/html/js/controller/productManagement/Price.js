Ext.define('CRM.controller.productManagement.Price', {
    extend: 'Ext.app.Controller',
    views: [ 'productManagement.price.ProductPriceCard', 'productManagement.price.ProductPriceList', 'productManagement.price.ProductPriceDetail',
            'productManagement.price.ProductPriceAddForm', 'productManagement.price.ProductPrice', 'productManagement.price.ProductPriceLabel' ],
    stores: [ 'productManagement.Price', 'systemManagement.userManagement.Group', 'productManagement.Discount' ],
    models: [ 'productManagement.Price', 'productManagement.Discount' ],
    init: function() {
        this.control({
            'productpricelist': {
                selectionchange: this.changeBtn,
                itemdblclick: this.setProductPrice
            },
            'textfield': {
                blur: utils.trimSpace
            },
            'textarea': {
                blur: utils.trimSpace
            },
            'productpricelist button[action=priceEditBtn]': {
                click: this.setProductPriceBtn
            },
            'productpricelist button[action=priceDelBtn]': {
                click: this.deletePrice
            },
            'productpricelist combo[id=groupID]': {
                change: this.groupIDChange
            },
            'productpricelist button[action=queryBtn]': {
                click: this.productQuery
            },
            'productpricelist button[action=superQueryBtn]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'priceaddform button[action=submitPriceAddBtn]': {
                click: this.submitPriceAddBtn
            },
            'priceaddform button[action=resetPriceAddBtn]': {
                // click : this.resetPriceAddBtn
                click: this.setProductPriceBtn
            },
            'priceaddform button[action=cancelPriceAddBtn]': {
                click: this.cancelPriceAddBtn
            }
        });
    },

    viewInit: function(flag) {
        var productPriceCard = Ext.getCmp('productPriceCard');
        if (typeof (productPriceCard) == 'undefined') {
            productPriceCard = Ext.widget('productpricecard');
            Ext.getCmp('centercard').insert(1, productPriceCard);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('productPriceCard');
        var json;
        var ds;
        Ext.Ajax.request({
            url: 'getConstructPrice.action',
            success: function(response, d) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                json = Ext.JSON.decode(response.responseText).headerAndField;
                ds = new Ext.data.JsonStore({
                    pageSize: 25,
                    data: Ext.JSON.decode("[]"),
                    fields: json.fieldsNames
                });
                ds.setProxy(new Ext.data.proxy.Ajax({
                    url: 'getDataPrice.action',
                    reader: {
                        type: 'json',
                        root: 'items',
                        totalProperty: 'total'
                    }
                }));
                Ext.getCmp('productPriceList').reconfigure(ds, json.columModle);
                utils.hideSuperQuery(Ext.getCmp('productPriceList'));
                Ext.getCmp('productPriceList').getStore().loadPage(1);
                Ext.getCmp('pagingtoolbar').bind(ds);
            }
        });
        Ext.getCmp('groupID').getStore().load({
            params: {
                groupID: 0
            },
            callback: function(records, operation, success) {
                Ext.getCmp('groupID').getStore().insert(0, {
                    groupID: 0,
                    groupName: '- 不限 -'
                });
                Ext.getCmp('groupID').setValue(0);
            }
        });
        // Ext.getCmp('groupID').setRawValue('不限');

        return productPriceCard;
    },

    groupIDChange: function(groupID, newValue, oldValue, e) {
        if (newValue != '') {
            Ext.getCmp('minDiscount').setDisabled(false);
            Ext.getCmp('maxDiscount').setDisabled(false);
        } else {
            Ext.getCmp('minDiscount').setDisabled(true);
            Ext.getCmp('maxDiscount').setDisabled(true);
            Ext.getCmp('minDiscount').setValue('');
            Ext.getCmp('maxDiscount').setValue('');
        }
    },

    openOrCloseSuperQueryBtn: function(button) {
        var store = Ext.getCmp('productPriceList').getStore();
        store.setProxy(new Ext.data.proxy.Ajax({
            type: 'ajax',
            url: 'getDataPrice.action',
            reader: {
                type: 'json',
                root: 'items',
                totalProperty: 'total'
            }
        }));
        utils.openOrCloseSuperQueryBtn(button);

    },

    productQuery: function(button) {
        if ((Ext.getCmp('discountAdvanced').isHidden() && Ext.getCmp("priceSearchText").isValid())
                || (!Ext.getCmp('discountAdvanced').isHidden() && Ext.getCmp('discountAdvanced').getForm().isValid())) {
            var store = Ext.getCmp('productPriceList').getStore();
            var searchText = 'searchText:Ext.getCmp("priceSearchText").getValue(),';
            var productName = 'productName:Ext.getCmp("discountProductName").getValue(),';
            var minPrice = 'minPrice:Ext.getCmp("discountMinPrice").getValue(),';
            var maxPrice = 'maxPrice:Ext.getCmp("discountMaxPrice").getValue(),';
            var groupID = 'groupID:Ext.getCmp("groupID").getValue(),';
            var minDiscount = 'minDiscount:Ext.getCmp("minDiscount").getValue(),';
            var maxDiscount = 'maxDiscount:Ext.getCmp("maxDiscount").getValue()';
            var searchString = '{' + searchText + productName + minPrice + maxPrice + groupID + minDiscount + maxDiscount + '}';
            var PriceSearchBean = Ext.decode(searchString);
            var searchFlag = 1;
            if (!Ext.getCmp('discountAdvanced').isHidden()) {
                searchFlag = 2;
            }
            store.setProxy(new Ext.data.proxy.Ajax({
                type: 'ajax',
                url: 'priceSearch.action',
                reader: {
                    type: 'json',
                    root: 'items',
                    totalProperty: 'total'
                }
            }));
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: Ext.encode(PriceSearchBean)
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
                callback: function() {

                    var form = Ext.getCmp('discountAdvanced');
                    function setVal(fieldId, val) {
                        if (fieldId != 'validate' && fieldId != 'priceRangeList' && fieldId != 'groupList') {
                            form.down('[name=' + fieldId + ']').markInvalid(val);
                        }
                    }
                    var text = store.getProxy().getReader().rawData;
                    if (text.validate == false) {
                        Ext.iterate(text, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    },

    changeBtn: function(sm, selections) {
        Ext.getCmp('priceDelBtn').setDisabled(selections.length == 0);
        Ext.getCmp('priceEditBtn').setDisabled(selections.length != 1);
        if (selections.length == 1) {
            Ext.getCmp('productPriceDetail').show();
            var record = Ext.getCmp('productPriceList').getSelectionModel().getSelection()[0];
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
                    Ext.getCmp('productPriceDetail').removeAll();
                    productName = Ext.widget('productpricelabel');
                    productName.fieldLabel = "产品名称";
                    productName.name = "name";
                    productPrice = Ext.widget('productpricelabel');
                    productPrice.fieldLabel = "标准报价（元）";
                    productPrice.name = "price";
                    Ext.getCmp('productPriceDetail').add(productName);
                    Ext.getCmp('productPriceDetail').add(productPrice);
                    for ( var i = 0; i < priceRanges.length; i++) {
                        for ( var j = 0; j < groups.length; j++) {
                            if (groups[j].groupID == priceRanges[i].groupID) {
                                var price = Ext.widget('productpricelabel');
                                price.id = "group" + priceRanges[i].groupID;
                                price.name = "group" + priceRanges[i].groupID;
                                price.fieldLabel = groups[j].groupName + "折扣";
                                price.setValue(priceRanges[i].discount);
                                Ext.getCmp('productPriceDetail').add(price);
                            }
                        }
                    }
                    Ext.getCmp('productPriceDetail').getForm().loadRecord(record);
                }
            });
            Ext.getCmp('productPriceList').getStore();
        } else {
            Ext.getCmp('productPriceDetail').hide();
        }
    },

    setProductPrice: function(sm, selections) {
        var priceaddform = Ext.getCmp('priceaddform');
        if (typeof (priceaddform) == 'undefined') {
            priceaddform = Ext.widget('priceaddform');
        }
        priceaddform.down('form').removeAll();
        priceaddform.setTitle("编辑产品的折扣");
        var record = selections;
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
                productPrice.fieldLabel = "标准报价（元）";
                productPrice.labelWidth = maxLength * 12 + 40;
                productPrice.name = "price";
                productPrice.margin = '5 5 5 10';
                productPrice.columnWidth = 1;
                productPrice.readOnly = true;
                priceaddform.down('form').add(productName);
                priceaddform.down('form').add(productPrice);
                priceaddform.down('form').loadRecord(selections);
                productName.setValue(Ext.String.htmlDecode(productName.getValue()));
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
    setProductPriceBtn: function(button) {
        var priceaddform = Ext.getCmp('priceaddform');
        if (typeof (priceaddform) == 'undefined') {
            priceaddform = Ext.widget('priceaddform');
        }
        priceaddform.down('form').removeAll();
        priceaddform.setTitle("编辑产品的折扣");
        var record = Ext.getCmp('productPriceList').getSelectionModel().getSelection()[0];
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
                productName.setValue(Ext.String.htmlDecode(productName.getValue()));
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

    cancelPriceAddBtn: function(button) {
        button.up('window').close();
    },

    deletePrice: function(button) {
        var grid = Ext.getCmp('productPriceList');
        utils.delRecords(grid, 'priceDelete.action', 'id');
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
            var product = Ext.getCmp('productPriceList').getSelectionModel().getSelection()[0];
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
                        Ext.getCmp('productPriceList').getStore().load();
                        Ext.crm.msg("设置成功", "");
                        button.up('window').close();
                    }
                }
            });
        } else {
            messageBox.alert("提示", '输入信息有误，请检查输入信息！');
        }
    }
});