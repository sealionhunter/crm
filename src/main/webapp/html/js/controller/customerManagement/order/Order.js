Ext.define('CRM.controller.customerManagement.order.Order', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.order.OrderList', 'customerManagement.order.OrderUpdate', 'customerManagement.order.OrderDetail', 'customerManagement.order.OrderInfo',
            'customerManagement.intentOrder.AllProductList', 'customerManagement.order.SelectProduct', 'customerManagement.order.BuyProduct', 'customerManagement.order.OrderTrack' ],
    stores: [ 'customerManagement.order.Order', 'customerManagement.intentOrder.Product', 'customerManagement.order.BuyProductStore', 'customerManagement.order.OrderTrack' ],
    models: [ 'customerManagement.order.Order', 'customerManagement.order.OrderTrack', 'customerManagement.order.BuyProductModel', 'productManagement.Product' ],
    init: function() {
        this.control({
            'orderList button[action=openOrCloseOrderSuperQuery]': {
                click: this.openOrCloseOrderSuperQuery
            },
            'orderList button[action=queryBtn]': {
                click: this.orderQuery
            },
            'orderList': {
                select: this.showDetail,
                itemdblclick: this.editOrder,
                selectionchange: this.changeBtnType
            },
            'orderList button[action=orderTrackBtn]': {
                click: this.orderTrack
            },
            'orderList toolbar button[action=orderAddBtn]': {
                click: this.addOrder
            },
            'orderList toolbar button[action=orderEditBtn]': {
                click: this.editOrder
            },
            'orderList toolbar button[action=orderDeleteBtn]': {
                click: this.deleteOrder
            },
            'selectProduct': {
                beforeclose: this.breforeCloseSelectProduct
            },
            'selectProduct button[action=submitProduct]': {
                click: this.addProductInfo
            },
            'selectProduct button[action=productQueryBtn]': {
                click: this.productQuery
            },
            'buyProduct': {
                selectionchange: this.buyProductChangeBtn
            },
            'orderUpdate': {
                beforeclose: this.closeOrderUpdate
            },
            'orderUpdate button[action=update]': {
                click: this.update
            },
            'orderUpdate button[action=doreset]': {
                click: this.doReset
            },
            'orderUpdate button[action=selectProduct]': {
                click: this.addProduct
            },
            'orderUpdate button[action=deleteProduct]': {
                click: this.deleteProduct
            },
            'orderUpdate button[action=close]': {
                click: this.close
            },
            'orderUpdate form combobox[name=customerID]': {
                select: this.clearContactStore
            },
            'orderUpdate form combobox[name=contactID]': {
                focus: this.addContactStore,
                select: this.addContactNumber
            },
            'orderUpdate  combobox[name=eventID]': {
                focus: this.addEventStore,
                select: this.showEventStatus
            },
            'orderUpdate button[action=removeLine]': {
                click: this.removeLine
            },
            'orderUpdate textarea': {
                blur: utils.trimSpaceSearch
            },
            'orderUpdate textfield': {
                blur: utils.trimSpaceSearch
            },
            'orderList textfield': {
                blur: utils.trimSpaceSearch
            }
        });
    },
    viewInit: function(flag) {
        var orderinfo = Ext.getCmp('orderinfo');
        if (typeof (orderinfo) == 'undefined') {
            orderinfo = Ext.widget('orderinfo');
            Ext.getCmp('centercard').insert(1, orderinfo);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('orderinfo');
        var queryTbar = Ext.getCmp('orderQueryTbar');
        var panel = Ext.getCmp('orderSuperQueryTbar');
        if (panel.isHidden()) {
            queryTbar.down('#searchText').reset();
        } else {
            queryTbar.down('#queryBtn').setDisabled(false);
            queryTbar.down('#searchText').setDisabled(false);
            panel.getForm().reset();
            panel.hide();
            var button = queryTbar.down('#superBtn');
            button.setText('高级检索');
            button.up('gridpanel').update();
        }
        var store = Ext.getCmp('orderList').getStore();
        var orderList = Ext.getCmp('orderList');
        if (orderList.orderStateStore.getCount() == 0) {
            orderList.orderStateStore.load({
                params: {
                    query: 'contactWay'
                }
            });
        }
        utils.loadPageOne(store);
        return orderinfo;
    },
    buyProductChangeBtn: function(sm, selections) {
        Ext.getCmp('deleteProductBtn').setDisabled(selections.length == 0);
        if (Ext.getCmp('buyProduct').getStore().getCount() == 0) {
            this.autoCheckGridHead();
        }
    },
    // 自动判断是否全选并选中或不选中表头的checkbox
    autoCheckGridHead: function() {
        grid_taskQueryPop = Ext.getCmp('buyProduct');
        var hd = Ext.query('div.x-grid-hd-checker-on');
        if (hd != null && hd[0] != null) {
            hd[0].className = hd[0].className.replace('x-grid-hd-checker-on', '');
        }
    },
    changeBtnType: function(sm, selections) {
        Ext.getCmp('orderDeleteBtn').setDisabled(selections.length == 0);
        Ext.getCmp('orderEditBtn').setDisabled(selections.length != 1);
        Ext.getCmp('orderTrackBtn').setDisabled(selections.length != 1);
        if (selections.length == 1) {
            Ext.getCmp('orderDetail').show();
        } else if (selections.length != 1) {
            Ext.getCmp('orderDetail').hide();
        }
    },
    clearContactStore: function(combo, record, index) {
        Ext.getCmp('customerRepresentativeId').reset();
        Ext.getCmp('customerRepresentativeId').getStore().removeAll();
        Ext.getCmp('customerRepresentativeId').setReadOnly(false);
        combo.up('window').down('#bindEvent').reset();
        combo.up('window').down('#bindEvent').getStore().removeAll();
        combo.up('window').down('#bindEvent').setReadOnly(false);
    },
    addContactStore: function(combo, eOpts) {
        combo.getStore().removeAll();
        if (combo.up('window').down('#customerID').value == null) {
            messageBox.alert("提示", "请先选择客户名称！");
        } else {
            var i = 0;
            combo.up('window').contactStoreAll.each(function(record) {
                if (record.get('customerID') == combo.up('window').down('#customerID').getValue()) {
                    Ext.getCmp('customerRepresentativeId').getStore().insert(i, {
                        contactName: record.get('contactName'),
                        contactID: record.get('contactID')
                    });
                    i++;
                }
            });
        }
        if (combo.store.count() == 0) {
            messageBox.alert("提示", "该客户没有联系人，请追加联系人！");
        }
    },
    addContactNumber: function(combo, record, index) {
        var contact = combo.getValue();
        Ext.Ajax.request({
            url: 'getContactNumber.action',
            params: {
                jsonString: contact
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var responseText = Ext.decode(response.responseText) || '';
                combo.up('window').down('#customerContact').setValue(responseText);
            }
        });
    },
    openOrCloseOrderSuperQuery: function(button) {
        utils.openOrCloseSuperQueryBtn(button);
    },
    loadComboboxStore: function(orderUpdate) {
        if (orderUpdate.orderStateStore.getCount() == 0) {
            orderUpdate.orderStateStore.load({
                params: {
                    code: '00090003'
                },
                callback: function(records, opeation, index) {
                    Ext.getCmp('orderStateCombo').setValue(records[0].get('code'));
                }
            });
        }
    },
    closeOrderUpdate: function() {
        if (cacheStore != '') {
            cacheStore.removeAll();
        }
    },
    addOrder: function(button) {
        var me = this;
        // 获取意向订单的所有状态
        Ext.Ajax.request({
            url: 'getCodeByCategory.action',
            params: {
                jsonString: '00040003'
            },
            success: function(resp, opts) {
                if (CRM.checkResponse(resp)) {
                    return;
                }
                orderStage = Ext.decode(resp.responseText).items;
            }
        });
        // 创建选择产品的缓存
        cacheStore = new Ext.data.JsonStore({
            data: Ext.JSON.decode("[]"),
            fields: [ {
                name: 'id',
                type: 'int'
            }, {
                name: 'orderID',
                type: 'string'
            }, {
                name: 'productID',
                type: 'string'
            }, {
                name: 'name',
                type: 'string'
            }, {
                name: 'productNumber',
                type: 'string'
            }, {
                name: 'price',
                type: 'string'
            }, {
                name: 'discount',
                type: 'string'
            } ]
        });
        var productstore = Ext.widget('buyProduct').getStore();
        productstore.removeAll();

        var view = Ext.widget('orderUpdate');
        view.setTitle('添加订单信息');
        view.down('#cusReset').setText('清空');
        view.down('#bindEvent').setReadOnly(true);
        Ext.getCmp('orderID').setValue(Ext.Date.format(new Date(), '\\F\\UJIYmdHisu'));
        Ext.getCmp('customerRepresentativeId').forceSelection = true;
        me.loadComboboxStore(view);
        Ext.getCmp('orderStateCombo').setValue(1);
        Ext.getCmp('orderStateCombo').setReadOnly(true);
        Ext.getCmp('customerRepresentativeId').setReadOnly(true);

        var customerStore = Ext.getCmp('customerID').getStore();
        customerStore.load({
            params: {
                userID: USER_ID
            },
            callback: function(records, opeation, index) {
                if (records.length == 0) {
                    messageBox.alert("提示", "无客户，请先添加客户！");
                } else {
                }
            }
        });
    },
    // 点击LIST的编辑按钮
    editOrder: function(button) {
        var me = this;
        Ext.Ajax.request({
            url: 'eventIsAbolished.action',
            params: {
                eventID: Ext.getCmp('orderList').getSelectionModel().getSelection()[0].get('eventID')
            },
            success: function(resp, opts) {
                if (CRM.checkResponse(resp)) {
                    return;
                }
                if (resp.responseText.indexOf('1') >= 0 || Ext.getCmp('orderList').getSelectionModel().getSelection()[0].get("eventID") == 0) {
                    var customerStore = Ext.getCmp('orderList').customerStore;
                    customerStore.load({
                        params: {
                            userID: USER_ID
                        }
                    });
                    Ext.Ajax.request({
                        url: 'getCodeByCategory.action',
                        params: {
                            jsonString: '00040003'
                        },
                        success: function(resp, opts) {
                            if (CRM.checkResponse(resp)) {
                                return;
                            }
                            orderStage = Ext.decode(resp.responseText).items;
                        }
                    });
                    // 创建选择产品的缓存
                    cacheStore = new Ext.data.JsonStore({
                        data: Ext.JSON.decode("[]"),
                        fields: [ {
                            name: 'id',
                            type: 'int'
                        }, {
                            name: 'orderID',
                            type: 'string'
                        }, {
                            name: 'productID',
                            type: 'string'
                        }, {
                            name: 'name',
                            type: 'string'
                        }, {
                            name: 'productNumber',
                            type: 'string'
                        }, {
                            name: 'price',
                            type: 'string'
                        }, {
                            name: 'discount',
                            type: 'string'
                        } ]
                    });

                    var orderlist = Ext.getCmp('orderList').getView();
                    var record = orderlist.getSelectionModel().getSelection();
                    var customerID = record[0].get('customerID');
                    var update = Ext.widget('orderUpdate');
                    update.setTitle('编辑订单信息');
                    update.down('#cusReset').setText('重置');

                    var customerStore = Ext.getCmp('customerID').getStore();
                    customerStore.load({
                        params: {
                            userID: USER_ID
                        },
                        callback: function(records, opeation, index) {

                        }
                    });

                    var eventID = record[0].get('eventID');
                    // 如果没绑定了销售事件
                    if (eventID == 0) {

                    } else {
                        // 如果绑定销售事件
                        var eventStore = Ext.getCmp('eventID').getStore();
                        Ext.Ajax.request({
                            url: 'findEventInfoById.action',
                            params: {
                                eventID: eventID
                            },
                            success: function(resp, opts) {
                                if (CRM.checkResponse(resp)) {
                                    return;
                                }
                                var event = Ext.JSON.decode(resp.responseText).item;
                                eventStore.insert(0, {
                                    eventName: event.eventName,
                                    eventID: event.eventID
                                });
                                Ext.getCmp('eventID').setValue(Ext.getCmp('eventID').getStore().getAt(0).get('eventID'));
                                Ext.getCmp('eventID').setDisabled(true);
                            }
                        });

                        // 显示订单阶段需求
                        Ext.Ajax.request({
                            url: 'getOrderEventDemand.action',
                            params: {
                                eventID: eventID,
                                typeString: 1
                            },
                            success: function(resp, opts) {
                                if (CRM.checkResponse(resp)) {
                                    return;
                                }
                                Ext.QuickTips.init();
                                var demands = Ext.decode(resp.responseText).items;
                                for ( var i = 0; i < orderStage.length; i++) {
                                    var checked = false;
                                    var disable = true;
                                    var demand = "";
                                    var hidden = true;
                                    if (i < demands.length) {
                                        checked = true;
                                        hidden = false;
                                        demand = demands[i].demand;
                                    }
                                    if (i == demands.length) {
                                        disable = false;
                                    }
                                    // 添加订单阶段需求checkbox
                                    var combox = Ext.create('Ext.form.Checkbox', {
                                        id: 'ckStage' + orderStage[i].code,
                                        boxLabel: me.subLengthStr(orderStage[i].value),
                                        margin: '10 0 0 0',
                                        disabled: disable,
                                        checked: checked,
                                        columnWidth: 0.33,
                                        height: 30,
                                        listeners: {
                                            change: me.checkBoxChange
                                        },
                                        labelSeparator: ''
                                    // labelableRenderTpl:["<label
                                    // data-qtip='"+orderStage[i].value+ "'
                                    // id='{id}-labelEl2'<tpl if'inputId'
                                    // for='{inputId}'</tpl>
                                    // class='{labelCls}'", "<tpl
                                    // if='labelStyle'>
                                    // style='{labelStyle}'</tpl>", "<tpl
                                    // if='fieldLabel'>{fieldLabel}{labelSeparator}</tpl>",
                                    // "</label>", "</tpl>", "<div
                                    // class'baseBodyCls} {fieldBodyCls}'
                                    // id='{id}-bodyEl'
                                    // role='presentation'>{subTplMarkup}</div>",
                                    // "<div id='{id}-errorEl'
                                    // class='{errorMsgCls}'
                                    // style'display:none'></div>", "<div
                                    // class='{clearCls}'
                                    // role='presentation'><!-- --></div>",
                                    // {compiled: true, disableFormats: true}]
                                    });
                                    Ext.getCmp('eventCheckBoxs').add(combox);
                                    // 添加订单阶段需求输入框
                                    textarea = Ext.create('Ext.form.TextArea', {
                                        id: 'txtStage' + orderStage[i].code,
                                        fieldLabel: me.subLengthStr(orderStage[i].value),
                                        width: 540,
                                        margin: '5 20 5 10',
                                        labelWidth: 82,
                                        value: demand,
                                        hidden: hidden,
                                        htmlEncode: true
                                    // labelableRenderTpl:["<tpl if='!hideLabel
                                    // && !(!fieldLabel && hideEmptyLabel)'>",
                                    // "<label
                                    // data-qtip='"+orderStage[i].value+"'
                                    // id='{id}-labelEl2'<tpl if'inputId'
                                    // for='{inputId}'</tpl>
                                    // class='{labelCls}'", "<tpl
                                    // if='labelStyle'>
                                    // style='{labelStyle}'</tpl>>", "<tpl
                                    // if='fieldLabel'>{fieldLabel}{labelSeparator}</tpl>",
                                    // "</label>", "</tpl>", "<div
                                    // class'baseBodyCls} {fieldBodyCls}'
                                    // id='{id}-bodyEl'
                                    // role='presentation'>{subTplMarkup}</div>",
                                    // "<div id='{id}-errorEl'
                                    // class='{errorMsgCls}'
                                    // style'display:none'></div>", "<div
                                    // class='{clearCls}'
                                    // role='presentation'><!-- --></div>",
                                    // {compiled: true, disableFormats: true}]
                                    });
                                    Ext.getCmp('orderContent').add(textarea);
                                    Ext.create('Ext.tip.ToolTip', {
                                        target: 'ckStage' + orderStage[i].code,
                                        html: orderStage[i].value,
                                        maxWidth: 800
                                    });
                                    Ext.create('Ext.tip.ToolTip', {
                                        target: 'txtStage' + orderStage[i].code + '-labelEl',
                                        html: orderStage[i].value,
                                        maxWidth: 800
                                    });
                                }
                            },
                            failure: function(resp, opts) {
                            }
                        });
                    }
                    update.down('#customerID').setReadOnly(true);
                    update.down('#bindEvent').disabled = false;
                    update.contactStoreById.load({
                        callback: function(records, operation, success) {
                            update.contactStoreById.insert(0, {
                                contactID: '0',
                                contactName: ''
                            });
                            update.down('form').loadRecord(record[0]);
                        }
                    });
                    setTimeout(function() {
                        update.contactStoreAll.each(function(record) {
                            if (record.get('customerID') == customerID) {
                                update.contactStoreById.add(record);
                            }
                        });
                        Ext.getCmp('customerRepresentativeId').forceSelection = true;
                    }, 500);
                    /*
                     * function fn() {
                     * update.contactStoreAll.each(function(record) { if
                     * (record.get('customerID') == customerID) {
                     * update.contactStoreById.add(record); } });
                     * Ext.getCmp('customerRepresentativeId').forceSelection =
                     * true; }
                     */
                    var store = Ext.getCmp('buyProduct').getStore();
                    var records = Ext.getCmp('orderList').getView().getSelectionModel().getSelection()[0];

                    if (Ext.getCmp('orderStateCombo').getStore().getCount() == 0) {
                        Ext.getCmp('orderStateCombo').getStore().load({
                            params: {
                                code: '00090003'
                            },
                            callback: function() {
                                Ext.getCmp('orderStateCombo').setValue(records.get('orderState'));
                            }
                        });
                    }

                    var orderID = records.get('orderID');
                    store.on('beforeload', function(store, options) {
                        var new_params = {
                            userID: GROUP_ID,
                            orderID: orderID
                        };
                        Ext.apply(store.proxy.extraParams, new_params);
                    });
                    store.load({
                        // 加载cachestore
                        callback: function(records, operation, success) {
                            for ( var i = 0; i < records.length; i++) {
                                var record = records[i].copy();
                                cacheStore.add(record);
                            }
                        }
                    });
                    // 加载订单总价
                    Ext.getCmp('totalPrice').setValue(records.get('transactionPrice'));
                } else {
                    Ext.MessageBox.alert('提示', '订单对应的销售事件已经终止，不可以编辑此订单！');
                }
            }
        });
    },
    deleteOrder: function(button) {
        var grid = button.up("panel");
        utils.delRecordsCheck(grid, 'orderDelete.action', 'id');
    },
    orderTrack: function(button) {
        var orderlist = Ext.getCmp('orderList');
        var record = orderlist.getSelectionModel().getSelection();
        var orderID = record[0].get('orderID');
        var orderTrack = Ext.widget('orderTrack');
        var orderTrackStore = orderTrack.down('grid').getStore();
        orderTrackStore.removeAll();
        orderTrackStore.load({
            params: {
                jsonString: orderID
            }
        });
    },
    subLengthStr: function(str) {
        if (str.length > 6) {
            str = str.substr(0, 5) + '...';
        }
        return str;
    },
    update: function(button) {
        if (Ext.getCmp('orderContent').getForm().isValid()) {
            var store = Ext.getCmp('buyProduct').getStore();
            if (store.getCount() < 1) {
                Ext.Msg.alert('提示', '产品为空，请添加产品');
                return;
            } else {
                // 生成订单基本信息
                var id = 0;
                if (Ext.getCmp('orderList').getView().getSelectionModel().getSelection().length > 0) {
                    id = Ext.getCmp('orderList').getView().getSelectionModel().getSelection()[0].get('id');
                }
                var eventID = Ext.getCmp('eventID').getValue();
                if (!(/^[1-9][0-9]*$/.test(Ext.getCmp('eventID').getValue()))) {
                    eventID = 0;
                }
                var orderInfor = {
                    id: id,
                    type: 1,
                    deliveryDate: Ext.getCmp('deliveryDate').getRawValue(),
                    customerContact: Ext.getCmp('customerContact').getValue(),
                    orderID: Ext.getCmp('orderID').getValue(),
                    orderState: Ext.getCmp('orderStateCombo').getValue(),
                    customerID: Ext.getCmp('customerID').getValue(),
                    contactID: Ext.getCmp('customerRepresentativeId').getValue(),
                    ourRepresentative: Ext.getCmp('ourRepresentative').getValue(),
                    ourTelephone: Ext.getCmp('ourTelephone').getValue(),
                    remark: Ext.getCmp('remark').getValue(),
                    transactionPrice: Ext.getCmp('totalPrice').getValue(),
                    eventID: eventID
                };
                // 生成订单产品信息
                var orderProduct = [];
                for ( var i = 0; i < store.getCount(); i++) {
                    var product = {
                        id: store.getAt(i).get('id'),
                        orderID: Ext.getCmp('orderID').getValue(),
                        isAbolished: 0,
                        productID: store.getAt(i).get('productID'),
                        name: store.getAt(i).get('name'),
                        price: store.getAt(i).get('price'),
                        discount: document.getElementById('discount' + store.getAt(i).get('id')).value,
                        productNumber: document.getElementById('productNumber' + store.getAt(i).get('id')).value
                    };
                    orderProduct[i] = product;
                }
                // 生成订单绑定的销售事件信息
                var demandMap = [];
                if (/^[1-9][0-9]*$/.test(Ext.getCmp('eventID').getValue())) {
                    for ( var i = 0; i < orderStage.length; i++) {
                        if (Ext.getCmp('ckStage' + orderStage[i].code).checked) {
                            var demandInfor = {
                                eventID: Ext.getCmp('eventID').getValue(),
                                status: orderStage[i].code,
                                demand: Ext.getCmp('txtStage' + orderStage[i].code).getValue()
                            };
                            demandMap[i] = demandInfor;
                        } else {
                            break;
                        }
                    }
                }
                var jsonString = {
                    orderDto: orderInfor,
                    selectProductDtos: orderProduct,
                    salesEventFlowDtos: demandMap
                };

                // 向后台提交添加订单请求
                Ext.Ajax.request({
                    url: 'addOrUpdateFormalOrder.action',
                    params: {
                        jsonString: Ext.encode(jsonString),
                        userID: USER_ID
                    },
                    success: function(resp, opts) {
                        if (CRM.checkResponse(resp)) {
                            return;
                        }
                        if (Ext.getCmp('orderUpdate').title == '添加订单信息') {
                            Ext.crm.msg("添加成功！", "");
                        } else {
                            Ext.crm.msg("编辑成功！", "");
                        }
                        Ext.getCmp('orderUpdate').close();
                        Ext.getCmp('orderList').getStore().loadPage(Ext.getCmp('orderList').getStore().currentPage);
                    },
                    failure: function(resp, opts) {
                        // 提示添加订单失败
                    }
                });
            }
        } else {
            Ext.MessageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },
    deleteProduct: function(button) {
        var grid = Ext.getCmp('buyProduct');
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        if (checkRecord.length > 0) {
            // 删除cacheStore中的产品
            for ( var i = 0; i < checkRecord.length; i++) {
                for ( var j = 0; j < cacheStore.getCount(); j++) {
                    if (cacheStore.getAt(j).get('id') == checkRecord[i].get('id')) {
                        cacheStore.removeAt(j);
                        break;
                    }
                }
            }
            // 删除buyStore中的产品
            store.remove(checkRecord);
        }
        // 重新计算总价
        var totalPrice = 0;
        var buyStore = grid.getStore();
        for ( var i = 0; i < buyStore.getCount(); i++) {
            var id = buyStore.getAt(i).get('id');
            var productNum = document.getElementById('productNumber' + id).value;
            var discount = document.getElementById('discount' + id).value;
            totalPrice += parseFloat(productNum) * parseFloat(discount);
        }
        Ext.getCmp('totalPrice').setValue((totalPrice * 0.0001).toFixed(2));
    },
    // 添加产品信息到UPDATE页面
    addProductInfo: function(button) {
        var grid = Ext.getCmp('allProduct');
        var allProductStore = grid.getStore();
        var view = Ext.getCmp('orderUpdate');
        var buyStore = view.down('#buyProduct').getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        if (cacheStore.getCount() > 0 || checkRecord.length > 0) {
            var records = [];
            for ( var i = 0; i < checkRecord.length; i++) {
                var record = checkRecord[i].copy();
                // record不在buyStore中
                if (document.getElementById('productNumber' + record.get('id')) == null || document.getElementById('productNumber' + record.get('id')) == 'undefined') {
                    record.set('productNumber', 1);
                    // record.set('discount',1200);
                    record.commit();
                } else {
                    record.set('productNumber', document.getElementById('productNumber' + record.get('id')).value);
                    record.set('discount', document.getElementById('discount' + record.get('id')).value);
                    record.commit();
                }
                records.push(record);
            }
            // 添加选中的产品到cacheStore中
            for ( var i = 0; i < records.length; i++) {
                var isExist = false;
                for ( var j = 0; j < cacheStore.getCount(); j++) {
                    if (cacheStore.getAt(j).get('id') == records[i].get('id')) {
                        isExist = true;
                        // 替换
                        cacheStore.getAt(j).set('productNumber', records[i].get('productNumber'));
                        cacheStore.getAt(j).set('discount', records[i].get('discount'));
                        cacheStore.getAt(j).commit();
                        break;
                    }
                }
                if (isExist == false) {
                    cacheStore.add(records[i]);
                }
            }
            // 删除取消的产品
            for ( var i = 0; i < allProductStore.getCount(); i++) {
                var isExist = false;
                var j;
                for (j = 0; j < cacheStore.getCount(); j++) {
                    if (cacheStore.getAt(j).get('id') == allProductStore.getAt(i).get('id')) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist == true) {
                    var isNoExist = true;
                    for ( var m = 0; m < records.length; m++) {
                        if (records[m].get('id') == allProductStore.getAt(i).get('id')) {
                            isNoExist = false;
                            break;
                        }
                    }
                    if (isNoExist == true) {
                        cacheStore.removeAt(j);
                    }
                }
            }

            // 将cacheStore中的产品添加到buyStore中
            for ( var i = 0; i < cacheStore.getCount(); i++) {
                var isExist = false;
                for ( var j = 0; j < buyStore.getCount(); j++) {
                    if (buyStore.getAt(j).get('id') == cacheStore.getAt(i).get('id')) {
                        isExist = true;
                        // 替换
                        buyStore.getAt(j).set('productNumber', cacheStore.getAt(i).get('productNumber'));
                        buyStore.getAt(j).set('discount', cacheStore.getAt(i).get('discount'));
                        buyStore.getAt(j).commit();
                        break;
                    }
                }
                if (isExist == false) {
                    buyStore.add(cacheStore.getAt(i));
                }
            }
            // 将cacheStore中取消的产品从buyStore中删除掉
            for ( var i = 0; i < buyStore.getCount(); i++) {
                var isExist = false;
                for ( var j = 0; j < cacheStore.getCount(); j++) {
                    if (cacheStore.getAt(j).get('id') == buyStore.getAt(i).get('id')) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist == false) {
                    buyStore.removeAt(i);
                    i--;
                }
            }
            // 重新计算产品总价
            var totalPrice = 0;
            for ( var i = 0; i < buyStore.getCount(); i++) {
                var id = buyStore.getAt(i).get('id');
                var productNum = document.getElementById('productNumber' + id).value;
                var discount = document.getElementById('discount' + id).value;
                totalPrice += parseFloat(productNum) * parseFloat(discount);
            }
            Ext.getCmp('totalPrice').setValue((totalPrice * 0.0001).toFixed(2));
            button.up('window').close();
        } else {
            Ext.Msg.alert('提示', '请选择产品！');
        }
    },
    addEventStore: function(combo, eOpts) {
        if (combo.up('window').down('#customerID').value == null) {
            messageBox.alert("提示", "请先选择客户名称！");
        } else {
            var store = combo.getStore();
            var opstatus = '00040002';// 暂时将 0 改为2 进行测试
            var customerID = combo.up('window').down('#customerID').getValue();
            store.setProxy(new Ext.data.proxy.Ajax({
                type: 'ajax',
                url: 'getEventByCustomer.action?opStatus=' + opstatus + '&jsonString=' + customerID,
                reader: {
                    type: 'json',
                    root: 'items',
                    totalProperty: 'total'
                }
            }));
            store.load({
                callback: function() {
                    if (combo.store.getCount() == 0) {
                        Ext.Msg.alert('提示', '无符合条件的事件！');
                    }
                }
            });
        }
    },
    // 销售事件checkbox状态改变
    showEventStatus: function(combo, record, index) {
        var me = this;
        Ext.QuickTips.init();
        for ( var i = 0; i < orderStage.length; i++) {
            // 判断是否添加过，防止重复添加
            if (document.getElementById('ckStage' + orderStage[i].code)) {
                continue;
            }
            // 添加订单阶段需求checkbox
            var combox;
            if (i == 0) {
                combox = Ext.create('Ext.form.Checkbox', {
                    id: 'ckStage' + orderStage[i].code,
                    boxLabel: me.subLengthStr(orderStage[i].value),
                    margin: '10 0 0 0',
                    disabled: false,
                    readOnly: true,
                    columnWidth: 0.33,
                    checked: true,
                    height: 30,
                    listeners: {
                        change: me.checkBoxChange
                    },
                    labelSeparator: ''
                // labelableRenderTpl:["<label data-qtip='"+orderStage[i].value+
                // "' id='{id}-labelEl2'<tpl if'inputId' for='{inputId}'</tpl>
                // class='{labelCls}'", "<tpl if='labelStyle'>
                // style='{labelStyle}'</tpl>", "<tpl
                // if='fieldLabel'>{fieldLabel}{labelSeparator}</tpl>",
                // "</label>", "</tpl>", "<div class'baseBodyCls}
                // {fieldBodyCls}' id='{id}-bodyEl'
                // role='presentation'>{subTplMarkup}</div>", "<div
                // id='{id}-errorEl' class='{errorMsgCls}'
                // style'display:none'></div>", "<div class='{clearCls}'
                // role='presentation'><!-- --></div>", {compiled: true,
                // disableFormats: true}]
                });
            } else {
                combox = Ext.create('Ext.form.Checkbox', {
                    id: 'ckStage' + orderStage[i].code,
                    boxLabel: me.subLengthStr(orderStage[i].value),
                    margin: '10 0 0 0',
                    disabled: true,
                    listeners: {
                        change: this.checkBoxChange
                    },
                    columnWidth: 0.33,
                    height: 30,
                    labelSeparator: ''
                // labelableRenderTpl:["<label data-qtip='"+orderStage[i].value+
                // "' id='{id}-labelEl2'<tpl if'inputId' for='{inputId}'</tpl>
                // class='{labelCls}'", "<tpl if='labelStyle'>
                // style='{labelStyle}'</tpl>", "<tpl
                // if='fieldLabel'>{fieldLabel}{labelSeparator}</tpl>",
                // "</label>", "</tpl>", "<div class'baseBodyCls}
                // {fieldBodyCls}' id='{id}-bodyEl'
                // role='presentation'>{subTplMarkup}</div>", "<div
                // id='{id}-errorEl' class='{errorMsgCls}'
                // style'display:none'></div>", "<div class='{clearCls}'
                // role='presentation'><!-- --></div>", {compiled: true,
                // disableFormats: true}]
                });
            }
            // 添加订单阶段需求输入框
            textarea = Ext.create('Ext.form.TextArea', {
                id: 'txtStage' + orderStage[i].code,
                fieldLabel: me.subLengthStr(orderStage[i].value),
                width: 540,
                margin: '5 20 5 10',
                labelWidth: 82,
                hidden: true,
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "备注长度不能超过1024个字符！",
                htmlEncode: true
            // labelableRenderTpl:["<tpl if='!hideLabel && !(!fieldLabel &&
            // hideEmptyLabel)'>", "<label data-qtip='"+orderStage[i].value+"'
            // id='{id}-labelEl2'<tpl if'inputId' for='{inputId}'</tpl>
            // class='{labelCls}'", "<tpl if='labelStyle'>
            // style='{labelStyle}'</tpl>>", "<tpl
            // if='fieldLabel'>{fieldLabel}{labelSeparator}</tpl>", "</label>",
            // "</tpl>", "<div class'baseBodyCls} {fieldBodyCls}'
            // id='{id}-bodyEl' role='presentation'>{subTplMarkup}</div>", "<div
            // id='{id}-errorEl' class='{errorMsgCls}'
            // style'display:none'></div>", "<div class='{clearCls}'
            // role='presentation'><!-- --></div>", {compiled: true,
            // disableFormats: true}]
            });
            if (i == 0) {
                textarea.setVisible(true);
            }
            if (i == 1) {
                combox.disabled = false;
            }
            Ext.getCmp('eventCheckBoxs').add(combox);
            Ext.getCmp('orderContent').add(textarea);
            Ext.create('Ext.tip.ToolTip', {
                target: 'ckStage' + orderStage[i].code,
                html: orderStage[i].value,
                maxWidth: 800
            });
            Ext.create('Ext.tip.ToolTip', {
                target: 'txtStage' + orderStage[i].code + '-labelEl',
                html: orderStage[i].value,
                maxWidth: 800
            });
        }
        if (combo.store.count() > 0) {

        } else {
            Ext.Msg.alert('提示', '无符合条件的销售事件');
        }
    },
    // 销售事件checkbox状态改变
    checkBoxChange: function(field, newValue, oldValue, eOpts) {
        var Id = field.id.replace('ckStage', '');
        var nextCk = 'ckStage';
        var prevCk = 'ckStage';
        var txtId = field.id.replace('ck', 'txt');
        for ( var i = 0; i < orderStage.length; i++) {
            if (orderStage[i].code == Id) {
                // prevCk
                if (i != 0) {
                    prevCk = 'ckStage' + orderStage[i - 1].code;
                }
                // nextCk
                if (i + 1 < orderStage.length) {
                    nextCk = 'ckStage' + orderStage[i + 1].code;
                }
            }
        }

        if (field.checked) {
            // 显示当前选中阶段checkbox对应的需求输入框
            Ext.getCmp(txtId).setVisible(true);
            // 设置下一个阶段的checkBox为可用状态
            if (typeof (Ext.getCmp(nextCk)) != 'undefined') {
                Ext.getCmp(nextCk).setDisabled(false);
            }
            // 设置上一阶段checkbox 为不可用状态
            if (typeof (Ext.getCmp(prevCk)) != 'undefined') {
                Ext.getCmp(prevCk).readOnly = true;
            }
        } else {
            // 隐藏取消选中的checkbox对应的需求输入框
            Ext.getCmp(txtId).setVisible(false);
            // 清空取消选中的checkbox对应的需求输入框
            Ext.getCmp(txtId).setValue('');
            // 设置下一个阶段的checkBox为不可用状态
            if (typeof (Ext.getCmp(nextCk)) != 'undefined') {
                Ext.getCmp(nextCk).setDisabled(true);
            }
            // 设置上一阶段checkbox 为可用状态
            if (typeof (Ext.getCmp(prevCk)) != 'undefined') {
                Ext.getCmp(prevCk).readOnly = false;
            }
        }
    },
    doReset: function(button) {
        if (button.text == '清空') {
            Ext.getCmp('orderUpdate').close();
            this.addOrder();
        } else if (button.text == '重置') {
            Ext.getCmp('orderUpdate').close();
            this.editOrder();
        }
    },
    productQuery: function(button) {
        if (Ext.getCmp("productSearchText").isValid()) {
            var store = Ext.getCmp('allProduct').getStore();
            var searchText = 'searchText:Ext.getCmp("productSearchText").getValue(),';
            var searchString = '{' + searchText + '}';
            var ProductSearchBean = Ext.decode(searchString);
            var searchFlag = 1;
            store.setProxy(new Ext.data.proxy.Ajax({
                type: 'ajax',
                url: 'productSearch.action?searchFlag=' + searchFlag + '&jsonString=' + Ext.encode(ProductSearchBean),
                reader: {
                    type: 'json',
                    root: 'items',
                    totalProperty: 'total'
                }
            }));
            store.loadPage(1, {
                callback: function() {
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
            store.loadPage(1);
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    },
    addProduct: function(button) {
        var me = this;
        Ext.Ajax.request({
            url: 'getAllProductByUserID.action?limit=25&page=1&start=0',
            success: function(resp, opts) {
                if (CRM.checkResponse(resp)) {
                    return;
                }
                if (Ext.decode(resp.responseText).total == 0) {
                    Ext.MessageBox.alert('提示', '没有产品，请先添加产品！');
                } else {
                    Ext.widget('selectProduct');
                    var store = Ext.getCmp('allProduct').getStore();
                    store.on("load", me.storeLoaded);
                    store.on("beforeload", me.storeBeforeload);
                    Ext.getCmp('selectProduct').close();
                    Ext.widget('selectProduct');
                    store.loadPage(1);
                }
            }
        });
    },
    // 注册allproduct beforeload 事件
    storeBeforeload: function(store, options) {
        var new_params = {
            userID: GROUP_ID
        };
        Ext.apply(store.proxy.extraParams, new_params);
        var grid = Ext.getCmp('allProduct');
        var allProductStore = grid.getStore();
        if (allProductStore.getCount() > 0) {
            var checkRecord = grid.getSelectionModel().getSelection();
            // 添加所有allProductStore中选中的产品
            var records = [];
            for ( var i = 0; i < checkRecord.length; i++) {
                var record = checkRecord[i].copy();
                // record不在buyStore中
                if (document.getElementById('productNumber' + record.get('id')) == null || document.getElementById('productNumber' + record.get('id')) == 'undefined') {
                    record.set('productNumber', 1);
                    /* record.set('discount',1200); */
                    record.commit();
                } else {
                    record.set('productNumber', document.getElementById('productNumber' + record.get('id')).value);
                    record.set('discount', document.getElementById('discount' + record.get('id')).value);
                    record.commit();
                }
                records.push(record);
            }

            // 添加新选中的产品
            for ( var i = 0; i < records.length; i++) {
                var isExist = false;
                for ( var j = 0; j < cacheStore.getCount(); j++) {
                    if (cacheStore.getAt(j).get('id') == records[i].get('id')) {
                        isExist = true;
                        cacheStore.getAt(j).set('productNumber', records[i].get('productNumber'));
                        cacheStore.getAt(j).set('discount', records[i].get('discount'));
                        cacheStore.getAt(j).commit();
                        break;
                    }
                }
                if (isExist == false) {
                    cacheStore.add(records[i]);
                }
            }
            // 删除取消的产品
            for ( var i = 0; i < allProductStore.getCount(); i++) {
                var isExist = false;
                var j;
                for (j = 0; j < cacheStore.getCount(); j++) {
                    if (cacheStore.getAt(j).get('id') == allProductStore.getAt(i).get('id')) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist == true) {
                    var isNoExist = true;
                    for ( var m = 0; m < records.length; m++) {
                        if (records[m].get('id') == allProductStore.getAt(i).get('id')) {
                            isNoExist = false;
                            break;
                        }
                    }
                    if (isNoExist == true) {
                        cacheStore.removeAt(j);
                    }
                }
            }
        }
    },
    storeLoaded: function() {
        var productStore = Ext.getCmp('allProduct').getStore();
        for ( var i = 0; i < productStore.getCount(); i++) {
            for ( var j = 0; j < cacheStore.getCount(); j++) {
                if (cacheStore.getAt(j).get('id') == productStore.getAt(i).get('id')) {
                    Ext.getCmp('allProduct').getSelectionModel().select(i, true);
                    continue;
                }
            }
        }
    },
    breforeCloseSelectProduct: function() {
        Ext.getCmp('allProduct').getStore().removeAll();
    },
    removeLine: function(button) {
        var view = button.up('panel');
        view.up('panel').remove(view);
    },
    close: function(button) {
        button.up('window').close();
    },
    showDetail: function(grid, record) {
        Ext.getCmp('orderDetail').getForm().loadRecord(record);
        if (Ext.getCmp('orderEventName').getValue() == "" || typeof (Ext.getCmp('orderEventName').getValue()) == "undefined") {
            Ext.getCmp('orderEventName').setVisible(false);
        } else {
            Ext.getCmp('orderEventName').setVisible(true);
        }
        var orderDetail = Ext.getCmp('orderDetail');
        var selection = Ext.getCmp('orderList').getSelectionModel().getSelection();
        var TabsDenamdStore = Ext.create('CRM.store.salesManagement.salesEvent.TabsDenamd');
        if (record.get('eventID') == 0) {
            Ext.getCmp('orderDetail').down('#demandNum').setVisible(false);
            Ext.getCmp('orderCurrentState').setVisible(false);
            for ( var i = 1; i <= 15; i++) {
                var demandNode = document.getElementById('eventDemandItem' + i + '');
                if (demandNode != 'undefined' && '' != demandNode && demandNode != null) {
                    demandNode.parentNode.removeChild(demandNode);
                }
            }
        } else {
            Ext.getCmp('orderDetail').down('#demandNum').setVisible(true);
            Ext.getCmp('orderCurrentState').setVisible(true);
            if (selection.length == 1) {
                // 加载销售事件当前阶段名称
                Ext.Ajax.request({
                    url: 'loadTabsAction.action',
                    params: {
                        eventID: record.get('eventID')
                    },
                    success: function(response) {
                        if (CRM.checkResponse(response)) {
                            return;
                        }
                        var responseText = Ext.decode(response.responseText) || '';
                        Ext.getCmp('orderCurrentState').setValue(responseText.currentStatusName);
                    }
                });
                TabsDenamdStore.load({
                    params: {
                        eventID: record.get('eventID')
                    },
                    callback: function(records, operation, success) {
                        Ext.Array.each(records, function(item) {
                            if (selection[0].get('status') != null) {
                                orderDetail.remove(orderDetail.down('#' + item.data.status));
                            }
                            var demand = Ext.widget('displayfield');
                            demand.labelWidth = 80;
                            demand.id = "eventDemandItem" + item.data.sort;
                            demand.itemId = item.data.status;
                            demand.fieldLabel = item.data.title;
                            demand.setValue(item.data.demands);
                            orderDetail.add(demand);
                        });
                    }
                });
            }
        }
    },
    orderQuery: function(button) {
        utils.query(button);
    }
});
var cacheStore = '';
var orderStage = [];