Ext.define('CRM.controller.customerManagement.intentOrder.IntentOrder', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.intentOrder.IntentOrderList', 'customerManagement.intentOrder.IntentOrderDetail',
            'customerManagement.intentOrder.IntentOrderTrack', 'customerManagement.intentOrder.AllProductList',
            'customerManagement.intentOrder.IntentOrderUpdate', 'customerManagement.intentOrder.IntentOrderInfo',
            'customerManagement.order.BuyProduct', 'customerManagement.intentOrder.IntentSelectProduct',
            'customerManagement.intentOrder.IntentOrderTransfer' ],
    stores: [ 'customerManagement.intentOrder.IntentOrder', 'customerManagement.intentOrder.Product', 'customerManagement.order.BuyProductStore',
            'customerManagement.intentOrder.IntentOrderTrack' ],
    models: [ 'customerManagement.intentOrder.IntentOrder', 'productManagement.Product', 'customerManagement.order.BuyProductModel',
            'customerManagement.intentOrder.IntentOrderTrack' ],

    init: function() {
        this.control({
            'intentOrderList button[action=openOrCloseOrderSuperQuery]': {
                click: this.openOrCloseOrderSuperQuery
            },
            'intentOrderList button[action=queryBtn]': {
                click: this.orderQuery
            },
            'intentOrderList': {
                select: this.showDetail,
                itemdblclick: this.editOrder,
                selectionchange: this.changeBtnType
            },
            'intentOrderList button[action=intentOrderToOrder]': {
                click: this.changeToOrder
            },
            'intentOrderList button[action=orderTrackBtn]': {
                click: this.orderTrack
            },
            'intentOrderList toolbar button[action=orderAddBtn]': {
                click: this.addOrder
            },
            'intentOrderList toolbar button[action=orderEditBtn]': {
                click: this.editOrder
            },
            'intentOrderList toolbar button[action=orderDeleteBtn]': {
                click: this.deleteOrder
            },
            'intentSelectProduct button[action=submitProduct]': {
                click: this.addProductInfo
            },
            'intentSelectProduct button[action=productQueryBtn]': {
                click: this.productQuery
            },
            'intentSelectProduct grid[id=allProduct]': {
                beforedeselect: this.multiSelectProduct
            },
            'intentSelectProduct': {
                beforeclose: this.breforeCloseSelectProduct
            },
            'buyProduct': {
                selectionchange: this.buyProductChangeBtn
            },
            'intentOrderUpdate': {
                beforeclose: this.closeIntentOrderUpdate
            },
            'intentOrderUpdate button[action=intentOrderToOrder]': {
                click: this.intentOrderChange
            },
            'intentOrderUpdate button[action=selectProduct]': {
                click: this.addProduct
            },
            'intentOrderUpdate button[action=deleteProduct]': {
                click: this.deleteProduct
            },
            'intentOrderUpdate button[action=update]': {
                click: this.update
            },
            'intentOrderUpdate button[action=doreset]': {
                click: this.doReset
            },
            'intentOrderUpdate button[action=close]': {
                click: this.close
            },
            'intentOrderUpdate  combobox[name=customerID]': {
                select: this.clearContactStore
            },
            'intentOrderUpdate  combobox[name=contactID]': {
                focus: this.addContactStore,
                select: this.addContactNumber
            },
            'intentOrderUpdate  combobox[name=eventID]': {
                focus: this.addEventStore,
                select: this.showEventStatus
            },
            'intentOrderUpdate textarea': {
                blur: utils.trimSpaceSearch
            },
            'intentOrderUpdate textfield': {
                blur: utils.trimSpaceSearch
            },
            'intentOrderList textfield': {
                blur: utils.trimSpaceSearch
            },
            'intentordertransfer button[action=transferSave]': {
                click: this.transferSave
            },
            'intentordertransfer button[action=transferClear]': {
                click: this.doClear
            },
            'intentordertransfer button[action=transferCancel]': {
                click: this.close
            }
        });
    },
    // 页面初始化
    viewInit: function(flag) {
        var intentOrderInfo = Ext.getCmp('intentOrderInfo');
        if (typeof (intentOrderInfo) == 'undefined') {
            intentOrderInfo = Ext.widget('intentOrderInfo');
            Ext.getCmp('centercard').insert(1, intentOrderInfo);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('intentOrderInfo');
        var queryTbar = Ext.getCmp('intentorderQueryTbar');
        var panel = Ext.getCmp('intentorderSuperQueryTbar');
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
        var store = Ext.getCmp('intentOrderList').getStore();

        store.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                userID: USER_ID,
                typeString: '0'
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1);
        return intentOrderInfo;
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
    doClear: function(button) {
        var form = button.up('window').down('form');
        form.getForm().reset();
    },
    // 改变按钮
    changeBtnType: function(sm, selections) {
        Ext.getCmp('intentorderDeleteBtn').setDisabled(selections.length == 0);
        Ext.getCmp('intentorderEditBtn').setDisabled(selections.length != 1);
        // 判断订单对应的销售事件是否终止，如果终止，提示不可以编辑
        Ext.getCmp('orderChangeBtn').setDisabled(selections.length != 1);
        Ext.getCmp('intentorderTrackBtn').setDisabled(selections.length != 1);
        if (selections.length == 1) {
            Ext.getCmp('intentOrderDetail').show();
        } else if (selections.length != 1) {
            Ext.getCmp('intentOrderDetail').hide();
        }
    },
    // 选择客户下拉框事件
    clearContactStore: function(combo, record, index) {
        Ext.getCmp('customerRepresentativeID').reset();
        Ext.getCmp('customerRepresentativeID').getStore().removeAll();
        Ext.getCmp('customerRepresentativeID').setReadOnly(false);
        combo.up('window').down('#bindEvent').reset();
        combo.up('window').down('#bindEvent').getStore().removeAll();
        combo.up('window').down('#bindEvent').setReadOnly(false);
    },
    // 点击客户联系人下拉框
    addContactStore: function(combo, eOpts) {
        combo.getStore().removeAll();
        if (combo.up('window').down('#customerID').value == null) {
            messageBox.alert("提示", "请先选择客户名称！");
        } else {
            var i = 0;
            combo.up('window').contactStoreAll.each(function(record) {
                if (record.get('customerID') == combo.up('window').down('#customerID').getValue()) {
                    Ext.getCmp('customerRepresentativeID').getStore().insert(i, {
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
    // 添加联系人电话
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
    // 打开或者关闭高级检索按钮
    openOrCloseOrderSuperQuery: function(button) {
        utils.openOrCloseSuperQueryBtn(button);
    },
    // 订单类型（进行中、未进行、已完成等）
    loadComboboxStore: function(orderUpdate) {
        if (orderUpdate.orderStateStore.getCount() == 0) {
            orderUpdate.orderStateStore.load({
                params: {
                    code: '00090003'
                },
                callback: function(records, opeation, index) {

                    Ext.getCmp('intentorderStateCombo').setValue(records[0].get('code'));
                }
            });
        }
    },
    closeIntentOrderUpdate: function() {
        if (cacheStore != '') {
            cacheStore.removeAll();
        }
    },
    // 点击LIST中的添加按钮
    addOrder: function(button) {
        var me = this;
        // 获取意向订单的所有状态
        Ext.Ajax.request({
            url: 'getCodeByCategory.action',
            params: {
                jsonString: '00040002'
            },
            success: function(resp, opts) {
                if (CRM.checkResponse(resp)) {
                    return;
                }
                intentOrderStage = Ext.decode(resp.responseText).items;
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

        var view = Ext.widget('intentOrderUpdate');
        view.setTitle('添加意向订单信息');
        view.down('#cusReset').setText('清空');
        view.down('#bindEvent').setReadOnly(true);
        Ext.getCmp('intentorderchange').setVisible(false);
        Ext.getCmp('intentorderID').setValue(Ext.Date.format(new Date(), '\\F\\UJIYmdHisu'));
        Ext.getCmp('customerRepresentativeID').forceSelection = true;
        me.loadComboboxStore(view);
        Ext.getCmp('intentorderStateCombo').setReadOnly(true);
        Ext.getCmp('customerRepresentativeID').setReadOnly(true);

        var customerStore = Ext.getCmp('intentOrderList').customerStore;
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
        // 判断订单对应的销售事件是否终止，如果终止，提示不可以编辑
        Ext.Ajax.request({
            url: 'eventIsAbolished.action',
            params: {
                eventID: Ext.getCmp('intentOrderList').getSelectionModel().getSelection()[0].get('eventID')
            },
            success: function(resp, opts) {
                if (CRM.checkResponse(resp)) {
                    return;
                }
                if (resp.responseText.indexOf('1') >= 0 || Ext.getCmp('intentOrderList').getSelectionModel().getSelection()[0].get("eventID") == 0) {
                    Ext.Ajax.request({
                        url: 'getCodeByCategory.action',
                        params: {
                            jsonString: '00040002'
                        },
                        success: function(resp, opts) {
                            if (CRM.checkResponse(resp)) {
                                return;
                            }
                            intentOrderStage = Ext.decode(resp.responseText).items;
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
                    var customerStore = Ext.getCmp('intentOrderList').customerStore;
                    customerStore.load({
                        params: {
                            userID: USER_ID
                        }
                    });
                    var orderlist = Ext.getCmp('intentOrderList').getView();
                    var record = orderlist.getSelectionModel().getSelection();
                    var customerID = record[0].get('customerID');
                    var update = Ext.widget('intentOrderUpdate');
                    update.setTitle('编辑意向订单信息');
                    update.down('#cusReset').setText('重置');
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
                                Ext.getCmp('eventID').setDisabled(true);
                                Ext.getCmp('eventID').setValue(Ext.getCmp('eventID').getStore().getAt(0).get('eventID'));
                            }
                        });
                        // 显示意向订单阶段需求
                        Ext.Ajax.request({
                            url: 'getOrderEventDemand.action',
                            params: {
                                eventID: eventID,
                                typeString: 0
                            },
                            success: function(resp, opts) {
                                if (CRM.checkResponse(resp)) {
                                    return;
                                }
                                var demands = Ext.decode(resp.responseText).items;
                                for ( var i = 0; i < intentOrderStage.length; i++) {
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
                                        id: 'ckStage' + intentOrderStage[i].code,
                                        boxLabel: me.subLengthStr(intentOrderStage[i].value),
                                        margin: '10 0 0 0',
                                        columnWidth: 0.33,
                                        disabled: disable,
                                        checked: checked,
                                        labelSeparator: '',
                                        height: 30,
                                        listeners: {
                                            change: me.checkBoxChange
                                        }
                                    });
                                    Ext.getCmp('eventCheckBoxs').add(combox);

                                    // 添加订单阶段需求输入框
                                    textarea = Ext.create('Ext.form.TextArea', {
                                        id: 'txtStage' + intentOrderStage[i].code,
                                        fieldLabel: me.subLengthStr(intentOrderStage[i].value),
                                        width: 540,
                                        margin: '5 20 5 10',
                                        labelWidth: 82,
                                        value: demand,
                                        hidden: hidden,
                                        htmlEncode: true
                                    });
                                    Ext.getCmp('intentOrderContent').add(textarea);
                                    Ext.create('Ext.tip.ToolTip', {
                                        target: 'ckStage' + intentOrderStage[i].code,
                                        html: intentOrderStage[i].value,
                                        maxWidth: 800
                                    });
                                    Ext.create('Ext.tip.ToolTip', {
                                        target: 'txtStage' + intentOrderStage[i].code + '-labelEl',
                                        html: intentOrderStage[i].value,
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
                    update.contactStoreByID.load({
                        callback: function(records, operation, success) {
                            update.contactStoreByID.insert(0, {
                                contactID: '0',
                                contactName: ''
                            });
                            update.down('form').loadRecord(record[0]);
                        }
                    });
                    setTimeout(function() {
                        update.contactStoreAll.each(function(record) {
                            if (record.get('customerID') == customerID) {
                                update.contactStoreByID.add(record);
                            }
                        });
                        Ext.getCmp('customerRepresentativeID').forceSelection = true;
                    }, 500);

                    var records = Ext.getCmp('intentOrderList').getView().getSelectionModel().getSelection()[0];
                    if (Ext.getCmp('intentorderStateCombo').getStore().getCount() == 0) {
                        Ext.getCmp('intentorderStateCombo').getStore().load({
                            params: {
                                code: '00090003'
                            },
                            callback: function() {
                                Ext.getCmp('intentorderStateCombo').setValue(records.get('orderState'));
                            }
                        });
                    }
                    var store = Ext.getCmp('buyProduct').getStore();
                    var orderID = records.get('orderID');
                    store.on('beforeload', function(store, options) {
                        var new_params = {
                            userID: USER_ID,
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
    // 点击LIST的删除按钮
    deleteOrder: function(button) {
        var grid = button.up("panel");
        utils.delRecords(grid, 'orderDelete.action', 'id');
    },
    // 点击LIST的订单追踪按钮
    orderTrack: function(button) {
        var orderlist = Ext.getCmp('intentOrderList');
        var record = orderlist.getSelectionModel().getSelection();
        var orderID = record[0].get('orderID');
        var orderTrack = Ext.widget('intentOrderTrack');
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
    // 点击UPDATE页面的确定按钮
    update: function(button) {
        if (Ext.getCmp('intentOrderContent').getForm().isValid()) {
            var store = Ext.getCmp('buyProduct').getStore();
            if (store.getCount() < 1) {
                Ext.Msg.alert('提示', '产品为空，请添加产品！');
                return;
            } else {
                // 生成订单基本信息
                var id = 0;
                if (Ext.getCmp('intentOrderList').getView().getSelectionModel().getSelection().length > 0) {
                    id = Ext.getCmp('intentOrderList').getView().getSelectionModel().getSelection()[0].get('id');
                }
                var eventID = Ext.getCmp('eventID').getValue();
                if (!(/^[1-9][0-9]*$/.test(Ext.getCmp('eventID').getValue()))) {
                    eventID = 0;
                }
                // 生成订单基本信息
                var orderInfor = {
                    id: id,
                    deliveryDate: Ext.getCmp('deliveryDate').getRawValue(),
                    customerContact: Ext.getCmp('customerContact').getValue(),
                    orderID: Ext.getCmp('intentorderID').getValue(),
                    orderState: Ext.getCmp('intentorderStateCombo').getValue(),
                    customerID: Ext.getCmp('customerID').getValue(),
                    contactID: Ext.getCmp('customerRepresentativeID').getValue(),
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
                        orderID: Ext.getCmp('intentorderID').getValue(),
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
                if (Ext.getCmp('eventID').getValue() != 0) {
                    for ( var i = 0; i < intentOrderStage.length; i++) {
                        if (Ext.getCmp('ckStage' + intentOrderStage[i].code).checked) {
                            var demandInfor = {
                                eventID: Ext.getCmp('eventID').getValue(),
                                status: intentOrderStage[i].code,
                                demand: Ext.getCmp('txtStage' + intentOrderStage[i].code).getValue()
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

                // 向后台提交添加意向订单请求
                Ext.Ajax.request({
                    url: 'orderAddOrUpdate.action',
                    params: {
                        jsonString: Ext.encode(jsonString),
                        userID: USER_ID
                    },
                    success: function(resp, opts) {
                        if (CRM.checkResponse(resp)) {
                            return;
                        }
                        if (Ext.getCmp('intentOrderUpdate').title == '添加意向订单信息') {
                            Ext.crm.msg("添加成功！", "");
                        } else {
                            Ext.crm.msg("编辑成功！", "");
                        }
                        Ext.getCmp('intentOrderUpdate').close();
                        Ext.getCmp('intentOrderList').getStore().loadPage(Ext.getCmp('intentOrderList').getStore().currentPage);

                    },
                    failure: function(resp, opts) {
                        // 提示添加意向订单失败
                        Ext.MessageBox.alert('提示', '添加失败！');
                    }
                });
            }
        } else {
            Ext.MessageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },
    // 点击UPDATE页面中产品的删除按钮
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

    // 点击UPDATE页面中的添加产品按钮
    addProduct: function(button) {
        var me = this;
        // 向后台提交添加意向订单请求
        Ext.Ajax.request({
            url: 'getAllProductByUserID.action?limit=25&page=1&start=0',
            success: function(resp, opts) {
                if (CRM.checkResponse(resp)) {
                    return;
                }
                if (Ext.decode(resp.responseText).total == 0) {
                    Ext.MessageBox.alert('提示', '没有产品，请先添加产品！');
                } else {
                    Ext.widget('intentSelectProduct');
                    var store = Ext.getCmp('allProduct').getStore();
                    store.on("load", me.storeLoaded);
                    store.on("beforeload", me.storeBeforeload);
                    Ext.getCmp('intentSelectProduct').close();
                    Ext.widget('intentSelectProduct');
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
                if (document.getElementById('productNumber' + record.get('id')) == null
                        || document.getElementById('productNumber' + record.get('id')) == 'undefined') {
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
    multiSelectProduct: function() {
        // alert(Ext.getCmp('allProduct').getSelectionModel().getSelection().length);
    },
    // 添加产品信息到UPDATE页面
    addProductInfo: function(button) {
        var grid = Ext.getCmp('allProduct');
        var allProductStore = grid.getStore();
        var view = Ext.getCmp('intentOrderUpdate');
        var buyStore = view.down('#buyProduct').getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        if (cacheStore.getCount() > 0 || checkRecord.length > 0) {
            var records = [];
            for ( var i = 0; i < checkRecord.length; i++) {
                var record = checkRecord[i].copy();
                // record不在buyStore中
                if (document.getElementById('productNumber' + record.get('id')) == null
                        || document.getElementById('productNumber' + record.get('id')) == 'undefined') {
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
    // 销售事件checkbox状态改变
    checkBoxChange: function(field, newValue, oldValue, eOpts) {
        var Id = field.id.replace('ckStage', '');
        var nextCk = 'ckStage';
        var prevCk = 'ckStage';
        var txtId = field.id.replace('ck', 'txt');
        for ( var i = 0; i < intentOrderStage.length; i++) {
            if (intentOrderStage[i].code == Id) {
                // prevCk
                if (i != 0) {
                    prevCk = 'ckStage' + intentOrderStage[i - 1].code;
                }
                // nextCk
                if (i + 1 < intentOrderStage.length) {
                    nextCk = 'ckStage' + intentOrderStage[i + 1].code;
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
    // 销售事件checkbox状态改变
    checkBoxChangeTransfer: function(field, newValue, oldValue, eOpts) {
        var Id = field.id.replace('checkTransfer', '');
        var nextCk = 'checkTransfer';
        var prevCk = 'checkTransfer';
        var txtId = field.id.replace('check', 'txt');
        for ( var i = 0; i < orderTransfer.length; i++) {
            if (orderTransfer[i].code == Id) {
                // prevCk
                if (i != 0) {
                    prevCk = 'checkTransfer' + orderTransfer[i - 1].code;
                }
                // nextCk
                if (i + 1 < orderTransfer.length) {
                    nextCk = 'checkTransfer' + orderTransfer[i + 1].code;
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
    // 重置、清空按钮
    doReset: function(button) {
        if (button.text == '清空') {
            Ext.getCmp('intentOrderUpdate').close();
            this.addOrder();
        } else if (button.text == '重置') {
            Ext.getCmp('intentOrderUpdate').close();
            this.editOrder();
        }
    },
    // **********************************************************************************************************
    // 订单LIST中生成正式订单
    changeToOrder: function(button) {
        var me = this;
        // 判断订单对应的销售事件是否终止，如果终止，提示不可以编辑
        Ext.Ajax.request({
            url: 'eventIsAbolished.action',
            params: {
                eventID: Ext.getCmp('intentOrderList').getSelectionModel().getSelection()[0].get('eventID')
            },
            success: function(resp, opts) {
                if (CRM.checkResponse(resp)) {
                    return;
                }
                var record = Ext.getCmp('intentOrderList').getSelectionModel().getSelection();
                var eventID = record[0].get("eventID");
                if (resp.responseText.indexOf('2') >= 0 || eventID == 0) {
                    if (record.length > 0) {
                        if (eventID == 0) {
                            var orderInfor = {
                                customerContact: record[0].get("customerContact"),
                                orderID: record[0].get("orderID"),
                                orderState: record[0].get("orderState"),
                                customerID: record[0].get("customerID"),
                                contactID: record[0].get("contactID"),
                                ourRepresentative: record[0].get("ourRepresentative"),
                                ourTelephone: record[0].get("ourTelephone"),
                                remark: record[0].get("remark"),
                                transactionPrice: record[0].get("transactionPrice"),
                                eventID: record[0].get("eventID"),
                                id: record[0].get("id")
                            };
                            var jsonString = {
                                orderDto: orderInfor
                            };
                            box.confirm('提示', '确定生成正式订单？', function showResult(button) {
                                if (button == 'yes') {
                                    Ext.Ajax.request({
                                        url: 'changeToOrder.action',
                                        params: {
                                            jsonString: Ext.encode(jsonString)
                                        },
                                        success: function(response) {
                                            if (CRM.checkResponse(response)) {
                                                return;
                                            }
                                            var currentPage = Ext.getCmp('intentOrderList').getStore().currentPage;
                                            var pageSize = Ext.getCmp('intentOrderList').getStore().pageSize;
                                            var total = Ext.getCmp('intentOrderList').getStore().totalCount - record.length;
                                            if (total <= (currentPage - 1) * pageSize) {
                                                currentPage = currentPage - 1;
                                            }
                                            if (total == 0) {
                                                currentPage = 1;
                                            }
                                            Ext.crm.msg("生成成功！", "");
                                            Ext.getCmp('intentOrderList').getStore().loadPage(currentPage);
                                        },
                                        failure: function(response) {
                                            messageBox.alert('提示', '生成失败，请联系管理员！');
                                        }
                                    });
                                }
                            });
                        } else {
                            Ext.Ajax.request({
                                url: 'getCodeByCategory.action',
                                params: {
                                    jsonString: '00040003'
                                },
                                success: function(resp, opts) {
                                    if (CRM.checkResponse(resp)) {
                                        return;
                                    }
                                    orderTransfer = Ext.decode(resp.responseText).items;
                                    if (orderTransfer.length > 0) {
                                        for ( var i = 0; i < orderTransfer.length; i++) {
                                            var checked = false;
                                            var hidden = true;
                                            if (i == 0) {
                                                checked = true;
                                                hidden = false;
                                            }
                                            var disable = true;
                                            var demand = "";
                                            // 添加订单阶段需求checkbox
                                            var checkbox = Ext.create('Ext.form.Checkbox', {
                                                id: 'checkTransfer' + orderTransfer[i].code,
                                                boxLabel: me.subLengthStr(orderTransfer[i].value),
                                                disabled: disable,
                                                columnWidth: 0.22,
                                                checked: checked,
                                                height: 20,
                                                labelSeparator: '',
                                                listeners: {
                                                    change: me.checkBoxChangeTransfer
                                                }
                                            });
                                            Ext.widget('intentordertransfer');
                                            Ext.getCmp('salesStatusTransfer').add(checkbox);
                                            Ext.create('Ext.tip.ToolTip', {
                                                target: 'checkTransfer' + orderTransfer[i].code,
                                                html: orderTransfer[i].value,
                                                maxWidth: 800
                                            });
                                            // 添加订单阶段需求输入框
                                            textarea = Ext.create('Ext.form.TextArea', {
                                                id: 'txtTransfer' + orderTransfer[i].code,
                                                fieldLabel: me.subLengthStr(orderTransfer[i].value),
                                                anchor: '90%',
                                                margin: '5 20 5 10',
                                                labelWidth: 82,
                                                hidden: hidden,
                                                value: demand,
                                                enforceMaxLength: true,
                                                maxLength: 1024,
                                                maxLengthText: "备注长度不能超过1024个字符！",
                                                htmlEncode: true
                                            });
                                            Ext.getCmp('transferForm').add(textarea);
                                            Ext.create('Ext.tip.ToolTip', {
                                                target: 'txtTransfer' + orderTransfer[i].code,
                                                html: orderTransfer[i].value,
                                                maxWidth: 800
                                            });
                                        }
                                        Ext.getCmp('checkTransfer' + orderTransfer[0].code).readOnly = true;
                                        Ext.getCmp('checkTransfer' + orderTransfer[0].code).setDisabled(false);
                                        Ext.getCmp('txtTransfer' + orderTransfer[0].code).setDisabled(false);
                                        if (orderTransfer.length > 1) {
                                            Ext.getCmp('checkTransfer' + orderTransfer[1].code).setDisabled(false);
                                        }
                                    }
                                }
                            });
                        }
                    }
                } else if (resp.responseText.indexOf('0') >= 0) {
                    Ext.MessageBox.alert('提示', '订单对应的销售事件已经终止，不可以生成正式订单！');
                } else {
                    Ext.MessageBox.alert('提示', '意向订单阶段还未结束，不可以生成正式订单！');
                }
            }
        });
    },
    transferSave: function() {
        var record = Ext.getCmp('intentOrderList').getSelectionModel().getSelection();
        if (record.length > 0) {
            var orderInfor = {
                customerContact: record[0].get("customerContact"),
                orderID: record[0].get("orderID"),
                orderState: record[0].get("orderState"),
                customerID: record[0].get("customerID"),
                contactID: record[0].get("contactID"),
                ourRepresentative: record[0].get("ourRepresentative"),
                ourTelephone: record[0].get("ourTelephone"),
                remark: record[0].get("remark"),
                transactionPrice: record[0].get("transactionPrice"),
                eventID: record[0].get("eventID"),
                id: record[0].get("id")
            };
            var demandMap = [];
            for ( var i = 0; i < orderTransfer.length; i++) {
                if (Ext.getCmp('checkTransfer' + orderTransfer[i].code).checked) {
                    var demandInfor = {
                        eventID: record[0].get("eventID"),
                        status: orderTransfer[i].code,
                        demand: Ext.getCmp('txtTransfer' + orderTransfer[i].code).getValue()
                    };
                    demandMap[i] = demandInfor;
                } else {
                    break;
                }
            }
            var jsonString = {
                orderDto: orderInfor,
                salesEventFlowDtos: demandMap
            };
            Ext.Ajax.request({
                url: 'changeToOrder.action',
                params: {
                    userID: USER_ID,
                    jsonString: Ext.encode(jsonString)
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var currentPage = Ext.getCmp('intentOrderList').getStore().currentPage;
                    var pageSize = Ext.getCmp('intentOrderList').getStore().pageSize;
                    var total = Ext.getCmp('intentOrderList').getStore().totalCount - record.length;
                    if (total <= (currentPage - 1) * pageSize) {
                        currentPage = currentPage - 1;
                    }
                    if (total == 0) {
                        currentPage = 1;
                    }
                    Ext.crm.msg("生成成功！", "");
                    Ext.getCmp('intentOrderList').getStore().loadPage(currentPage);
                    Ext.getCmp('intentordertransfer').close();
                },
                failure: function(response) {
                    messageBox.alert('提示', '生成失败，请联系管理员！');
                }
            });
        }
    },

    // UPDATE页面的生成正式订单
    intentOrderChange: function(button) {
        var store = Ext.getCmp('buyProduct').getStore();
        if (store.getCount() < 1) {
            Ext.Msg.alert('提示', '产品为空，请添加产品');
            return;
        }
        var id = button.up('window').down('#order_id').getValue();
        box.confirm('提示', '确定将所选生成正式订单？', function(button) {
            if (button == 'yes') {
                Ext.Ajax.request({
                    url: 'changeToOrder.action',
                    params: {
                        jsonString: id
                    },
                    success: function(response) {
                        if (CRM.checkResponse(response)) {
                            return;
                        }
                        Ext.getCmp('intentOrderUpdate').close();
                        var store = Ext.getCmp('intentOrderList').getStore();
                        var currentPage = store.currentPage;
                        var pageSize = store.pageSize;
                        var total = store.totalCount - 1;
                        if (total <= (currentPage - 1) * pageSize) {
                            currentPage = currentPage - 1;
                        }
                        if (total == 0) {
                            currentPage = 1;
                        }
                        store.loadPage(currentPage);
                        Ext.crm.msg("生成订单成功！", "");
                    },
                    failure: function(response) {
                        messageBox.alert('提示', '生成失败，请联系管理员！');
                    }
                });
            }
        });
    },
    // 取消按钮
    close: function(button) {
        button.up('window').close();
    },
    // 显示详细信息
    showDetail: function(grid, record) {
        Ext.getCmp('intentOrderDetail').getForm().loadRecord(record);
        if (Ext.getCmp('intentEventName').getValue() == "" || typeof (Ext.getCmp('intentEventName').getValue()) == "undefined") {
            Ext.getCmp('intentEventName').setVisible(false);
        } else {
            Ext.getCmp('intentEventName').setVisible(true);
        }
        var intentOrderDetail = Ext.getCmp('intentOrderDetail');
        var TabsDenamdStore = Ext.create('CRM.store.salesManagement.salesEvent.TabsDenamd');
        var selection = Ext.getCmp('intentOrderList').getSelectionModel().getSelection();
        if (record.get('eventID') == 0) {
            Ext.getCmp('currentState').setVisible(false);
            Ext.getCmp('intentOrderDetail').down('#demandNum').setVisible(false);
            for ( var i = 1; i <= 15; i++) {
                var demandNode = document.getElementById('demandItem' + i + '');
                if (demandNode != 'undefined' && '' != demandNode && demandNode != null) {
                    demandNode.parentNode.removeChild(demandNode);
                }
            }
        } else {
            Ext.getCmp('intentOrderDetail').down('#demandNum').setVisible(true);
            if (selection.length == 1) {
                Ext.getCmp('currentState').setVisible(true);
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
                        Ext.getCmp('currentState').setValue(responseText.currentStatusName);
                    }
                });
                TabsDenamdStore.load({
                    params: {
                        eventID: record.get('eventID')
                    },
                    callback: function(records, operation, success) {
                        Ext.Array.each(records, function(item) {
                            if (selection[0].get('status') != null) {
                                intentOrderDetail.remove(intentOrderDetail.down('#' + item.data.status));
                            }
                            var demand = Ext.widget('displayfield');
                            demand.labelWidth = 80;
                            demand.id = "demandItem" + item.data.sort;
                            demand.itemId = item.data.status;
                            demand.fieldLabel = item.data.title;
                            demand.setValue(item.data.demands);
                            intentOrderDetail.add(demand);
                        });
                    }
                });
            }
        }
    },
    // 点击销售事件下拉框
    addEventStore: function(combo, eOpts) {
        if (combo.up('window').down('#customerID').value == null) {
            messageBox.alert("提示", "请先选择客户名称！");
        } else {
            var store = combo.getStore();
            var opstatus = '00040001';
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
                        Ext.Msg.alert('提示', '无符合条件的事件');
                    }
                }
            });
        }
    },
    // 选择销售事件下拉框
    showEventStatus: function(combo, record, index) {
        var me = this;
        Ext.QuickTips.init();
        for ( var i = 0; i < intentOrderStage.length; i++) {
            // 判断是否添加过，防止重复添加
            if (document.getElementById('ckStage' + intentOrderStage[i].code)) {
                continue;
            }
            // 添加订单阶段需求checkbox
            var combox;
            if (i == 0) {
                combox = Ext.create('Ext.form.Checkbox', {
                    id: 'ckStage' + intentOrderStage[i].code,
                    boxLabel: me.subLengthStr(intentOrderStage[i].value),
                    labelSeparator: '',
                    margin: '10 0 0 0',
                    disabled: false,
                    readOnly: true,
                    columnWidth: 0.33,
                    checked: true,
                    height: 30,
                    listeners: {
                        change: this.checkBoxChange
                    }
                });

            } else {
                combox = Ext.create('Ext.form.Checkbox', {
                    id: 'ckStage' + intentOrderStage[i].code,
                    boxLabel: me.subLengthStr(intentOrderStage[i].value),
                    margin: '10 0 0 0',
                    disabled: true,
                    columnWidth: 0.33,
                    labelSeparator: '',
                    height: 30,
                    listeners: {
                        change: this.checkBoxChange
                    }
                });
            }
            // 添加订单阶段需求输入框
            textarea = Ext.create('Ext.form.TextArea', {
                id: 'txtStage' + intentOrderStage[i].code,
                fieldLabel: me.subLengthStr(intentOrderStage[i].value),
                width: 540,
                margin: '5 20 5 10',
                labelWidth: 82,
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "备注长度不能超过1024个字符！",
                hidden: true,
                htmlEncode: true
            });
            if (i == 0) {
                textarea.setVisible(true);
            }
            if (i == 1) {
                combox.disabled = false;
            }
            Ext.getCmp('eventCheckBoxs').add(combox);
            Ext.getCmp('intentOrderContent').add(textarea);
            Ext.create('Ext.tip.ToolTip', {
                target: 'ckStage' + intentOrderStage[i].code,
                html: intentOrderStage[i].value,
                maxWidth: 800
            });
            Ext.create('Ext.tip.ToolTip', {
                target: 'txtStage' + intentOrderStage[i].code + '-labelEl',
                html: intentOrderStage[i].value,
                maxWidth: 800
            });
        }
        if (combo.store.count() > 0) {

        } else {
            Ext.Msg.alert('提示', '无符合条件的销售事件');
        }
    },

    // 点击检索按钮
    orderQuery: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var jsonString = "";
            if (!button.up('grid').down('form').isHidden()) {
                searchFlag = 2;
                var form = button.up('form');
                var values = form.getValues();
                jsonString = Ext.encode(values);
            } else {
                var searchValue = button.up('toolbar').down('#searchText').getValue().toString();
                jsonString = '{searchText : ' + Ext.encode(searchValue) + '}';
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: jsonString,
                    typeString: '0'
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
            store.currentPage = 1;
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    },
    // 产品的检索
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
    }
});
var cacheStore = '';
var intentOrderStage = [];
var orderTransfer = [];
