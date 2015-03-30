Ext.define('CRM.controller.salesManagement.transactionManagement.TransactionManagement', {
    extend: 'Ext.app.Controller',
    views: [ 'salesManagement.salesEvent.SalesEventList', 'salesManagement.salesEvent.SalesEventView', 'salesManagement.salesEvent.SalesEventDetail',
            'salesManagement.salesEvent.SalesEventWindow', 'salesManagement.salesEvent.SalesEventTrack',
            'salesManagement.salesEvent.SalesEventUpdate',

            'customerManagement.order.OrderList', 'salesManagement.salesEvent.SalesOrderUpdate', 'customerManagement.order.OrderDetail',
            'customerManagement.order.OrderInfo', 'customerManagement.intentOrder.AllProductList', 'customerManagement.order.SelectProduct',
            'customerManagement.order.BuyProduct', 'customerManagement.order.OrderTrack' ],
    stores: [ 'salesManagement.salesEvent.SalesEventStore', 'salesManagement.salesEvent.SalesEventTrack', 'salesManagement.salesEvent.TabsDenamd',
            'customerManagement.order.OrderCutomerContactStore', 'salesManagement.salesEventFlowCode.SalesEventFlowCode',

            'customerManagement.order.Order', 'customerManagement.intentOrder.Product', 'customerManagement.order.BuyProductStore',
            'customerManagement.order.OrderTrack' ],
    models: [ 'customerManagement.order.CustomerContactModel', 'salesManagement.salesEvent.TabsDenamd',
            'salesManagement.salesEventFlowCode.SalesEventFlowCode',

            'customerManagement.order.Order', 'customerManagement.order.OrderTrack', 'customerManagement.order.BuyProductModel',
            'productManagement.Product' ],
    init: function() {
        this.control({
            'salesEventList': {
                select: this.changeTransactionDetail,
                itemdblclick: this.editSalesOpportunities,
                selectionchange: this.changeBtnType
            },
            'salesEventList button[action=queryBtn]': {
                click: this.queryBtn
            },
            'salesEventList button[action=openOrCloseAdvancedSearchBtn]': {
                click: this.openOrCloseSuperSearch
            },
            'salesEventList >toolbar button[action=deleteSalesEvent]': {
                click: this.deleteSalesEvent
            },
            'salesEventList > toolbar button[action=addSalesOpportunities]': {
                click: this.addSalesOpportunities
            },
            'salesEventList > toolbar button[action=editSalesOpportunities]': {
                click: this.editSalesOpportunities
            },
            'salesEventList combobox': {
                select: this.comboChangeSelect
            },
            'salesEventList button[action=transactionTrackBtn]': {
                click: this.transactionTrackBtn
            },
            'salesEventList button[name=superquery]': {
                click: this.salesEventSuperQuery
            },
            'salesEventList actioncolumn': {
                itemclick: this.transactionHandleBtn
            },
            'transactionHandleWindow button[action=transactionProcessingReset]': {
                click: this.transactionProcessingReset
            },
            'transactionHandleWindow button[action=transactionProcessing]': {
                click: this.transactionProcessing
            },
            'salesopportunitiesupdate button[action=update]': {
                click: this.update
            },
            'salesopportunitiesupdate button[action=doreset]': {
                click: this.doReset
            },
            'salesopportunitiesupdate button[action=close]': {
                click: this.close
            },
            'salesopportunitiesupdate form combobox[name=customerID]': {
                select: this.clearContactStore
            },
            'salesopportunitiesupdate form combobox[name=contactID]': {
                focus: this.addContactStore
            },
            'salesopportunitiesupdate textarea': {
                blur: utils.trimSpaceSearch
            },
            'salesopportunitiesupdate textfield': {
                blur: utils.trimSpaceSearch
            },
            'salesEventList textfield': {
                blur: utils.trimSpaceSearch
            },
            'transactionHandleWindow combobox[name=salesEventTransaction]': {
                select: this.changeTransactionHandleWindowStyle
            },

            'salesOrderUpdate button[action=selectProduct]': {
                click: this.salesAddProduct
            },
            'salesOrderUpdate button[action=deleteProduct]': {
                click: this.salesDeleteProduct
            },
            'selectProduct button[action=submitProduct]': {
                click: this.salesAddProductInfo
            },
            'selectProduct button[action=productQueryBtn]': {
                click: this.salesProductQuery
            },
            'salesOrderUpdate button[action=update]': {
                click: this.salesOrderUpdate
            }
        });
    },

    viewInit: function(flag) {
        var transactionManagement = Ext.getCmp('transactionManagementView');
        if (typeof (transactionManagement) == 'undefined') {
            transactionManagement = Ext.widget('transactionMainView');
        }
        Ext.getCmp('centercard').insert(1, transactionManagement);
        Ext.getCmp('centercard').getLayout().setActiveItem('transactionManagementView');
        var grid = Ext.getCmp('salesEventList');
        grid.salesEventStatusCombo.load();
        var store = grid.getStore();
        var queryTbar = Ext.getCmp('salesEventQueryTbar');
        var panel = Ext.getCmp('salesEventPanel');
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
        utils.loadPageOne(store);
        return transactionManagement;
    },

    changeTransactionDetail: function(grid, record) {
        var selection = Ext.getCmp('salesEventList').getSelectionModel().getSelection();
        var salesEventDetail = Ext.getCmp('salesEventDetail');
        salesEventDetail.loadRecord(record);
        var salesEventDetailIsAbolished = Ext.getCmp('salesEventDetailIsAbolished');
        if (salesEventDetailIsAbolished.getValue()) {
            salesEventDetailIsAbolished.setValue('终止');
        } else {
            if (record.get('statusName') == '中标') {
                salesEventDetailIsAbolished.setValue('完成');
            } else {
                salesEventDetailIsAbolished.setValue('进行中');
            }
        }
        if ((selection[0].get('status') <= 0)) {
            salesEventDetail.down('#demandNum').setVisible(false);
        } else {
            if (selection.length == 1) {
                var TabsDenamdStore = Ext.widget('TabsDenamdStore');
                TabsDenamdStore.load({
                    params: {
                        eventID: record.get('eventID')
                    },
                    callback: function(records, operation, success) {
                        Ext.Array.each(records, function(item) {
                            if (selection[0].get('status') != null) {
                                salesEventDetail.remove(salesEventDetail.down('#' + item.data.status));
                            }
                            var demand = Ext.widget('displayfield');
                            demand.labelWidth = 60;
                            demand.itemId = item.data.status;
                            demand.fieldLabel = item.data.title;
                            demand.setValue(Ext.String.htmlEncode(item.data.demands));
                            salesEventDetail.add(demand);
                        });
                    }
                });
            }
        }
    },

    openOrCloseSuperSearch: function(button) {
        var toolBar = button.up('toolbar');
        var gridPanel = toolBar.up('gridpanel');
        var gridStore = gridPanel.getStore();
        var toolForm = gridPanel.down('form');
        var grid = button.up('grid');
        if (toolForm.isHidden()) {
            toolBar.down('button[action="queryBtn"]').setDisabled(true);
            toolBar.down('#searchText').setDisabled(true);
            toolBar.down('#searchText').reset();
            if (grid.searchSalesEventStatus.getCount() == 0) {
                grid.salesEventStatusCombo.each(function(record) {
                    grid.searchSalesEventStatus.add(record);
                });
                grid.searchSalesEventStatus.insert(0, {
                    code: '0',
                    value: '- 不限  -'
                });
                toolForm.down('#status').reset();
            }
            toolForm.show();
            button.setText('关闭检索');
            gridPanel.update();
        } else {
            toolBar.down('button[action="queryBtn"]').setDisabled(false);
            toolBar.down('#searchText').setDisabled(false);
            toolForm.getForm().reset();
            toolForm.hide();
            button.setText('高级检索');
            gridPanel.update();
        }
        gridStore.proxy.url = 'salesEventAction.action';
        gridStore.on('beforeload', function(store, options) {
            var new_params = {};
            Ext.apply(store.proxy.extraParams, new_params);
        });
        gridStore.loadPage(1);
    },

    transactionTrackBtn: function() {
        var transactionTrackwindow = Ext.widget('transactionTrack');
        var title = Ext.getCmp('salesEventList').getSelectionModel().getSelection()[0].get('eventName') + '的销售事件履历';
        var id = Ext.getCmp('salesEventList').getSelectionModel().getSelection()[0].get('eventID');
        var salesTrackStore = Ext.getCmp('transactionTrackWindow').down('#gridTrack').getStore();
        transactionTrackwindow.setTitle(title);
        salesTrackStore.removeAll();
        salesTrackStore.load({
            params: {
                jsonString: id
            }
        });
        transactionTrackwindow.show();
    },

    queryBtn: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var searchKey = button.up('grid').down('#searchText').getValue();
            var store = button.up('grid').getStore();
            var jsonString = '{searchKey : ' + Ext.encode(searchKey) + '}';
            store.proxy.url = 'salesEventSearchAction.action';
            store.on('beforeload', function(store, options) {
                var new_params = {
                    jsonString: jsonString
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
                callback: function() {
                    if (store.getCount() == 0) {
                        messageBox.alert('提示', '未检索到符合条件的数据！');
                    }
                }
            });
        } else {
            messageBox.alert('提示', '您搜索的值不符合规范，请重新输入！');
        }
    },

    salesEventSuperQuery: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var value = button.up('form').getValues();
            var jsonString = Ext.encode(value);
            var store = button.up('grid').getStore();
            store.proxy.url = 'salesSuperQueryAction.action';
            store.on('beforeload', function(store, options) {
                var new_params = {
                    jsonString: jsonString
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
                callback: function() {
                    if (store.getCount() == 0) {
                        messageBox.alert('提示', '未检索到符合条件的数据！');
                    }
                }
            });
        } else {
            messageBox.alert('提示', '您搜索的值不符合规范，请重新输入！');
        }
    },

    transactionHandleBtn: function(grid, record) {
        if (record.get('statusName') == '中标') {
            Ext.MessageBox.alert('提示', '该销售机会已经中标，处理完成！');
        } else {
            var TabsDenamdStore = Ext.widget('TabsDenamdStore');
            var eventName = record.get('eventName');
            var transactionHandleWindow = Ext.widget('transactionHandleWindow');
            transactionHandleWindow.setTitle(eventName + '的事件处理');
            transactionHandleWindow.down('form').loadRecord(record);
            if (record.get('isAbolished')) {
                transactionHandleWindow.down('#salesEventTransaction').setValue('000900040002');
                transactionHandleWindow.down('#nextDemand').setVisible(false);
                transactionHandleWindow.setHeight(200);
            } else {
                Ext.getCmp('salesEventTransaction').setValue('000900040001');
                transactionHandleWindow.setHeight(300);
            }
            TabsDenamdStore.load({
                params: {
                    eventID: record.get('eventID')
                },
                callback: function(records, operation, success) {
                    Ext.Array.each(records, function(item) {
                        if (item.data.sort == record.get('sort') + 1) {
                            var nextStatus = item.data.status;
                            transactionHandleWindow.down('#nextStatusName').setValue(item.data.title);
                            transactionHandleWindow.down('#nextDemand').setValue(item.data.demands);
                            transactionHandleWindow.down('#nextStatus').setValue(nextStatus);
                        }
                    });
                }
            });
            transactionHandleWindow.show();
        }
    },

    transactionProcessing: function(button) {
        var grid = Ext.getCmp('salesEventList');
        var store = grid.getStore();
        var win = button.up('window');
        var form = win.down('form');
        var record = form.getRecord();
        var formValues = form.getForm().getValues();
        var nextDemand = form.down('#nextDemand').getValue();
        var nextStatus = form.down('#nextStatus').getValue();
        record.data.submitterID = USER_ID;
        record.data.nextDemand = nextDemand;
        var value = record.data;
        if (nextStatus == 3 && formValues.salesEventTransaction == '000900040001') {
            Ext.Ajax.request({
                url: 'orderIsExist.action',
                params: {
                    eventID: record.data.eventID
                },
                method: 'POST',
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    if (Ext.decode(response.responseText).isExist == false) {
                        box.confirm('提示', '该事件还没有订单，请添加订单！', function(button) {
                            if (button == 'yes') {
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
                                var view = Ext.widget('salesOrderUpdate');
                                var customerStore = view.down('#customerID').getStore();
                                var productstore = Ext.getCmp('buyProduct').getStore();
                                productstore.removeAll();
                                customerStore.load({
                                    params: {
                                        userID: USER_ID
                                    },
                                    callback: function(records, opeation, index) {
                                        view.setTitle('添加订单信息');
                                        view.down('#cusReset').setText('清空');
                                        Ext.getCmp('salesOrderID').setValue(Ext.Date.format(new Date(), '\\F\\UJIYmdHisu'));
                                        Ext.getCmp('customerRepresentativeId').forceSelection = true;
                                        view.orderStateStore.load({
                                            params: {
                                                code: '00090003'
                                            },
                                            callback: function() {
                                                Ext.getCmp('salesOrderStateCombo').setValue(
                                                        Ext.getCmp('salesOrderStateCombo').getStore().getAt(0).get('code'));
                                            }
                                        });
                                        view.contactStoreById.load({
                                            params: {
                                                opStatus: '00040003',
                                                jsonString: value.customerID
                                            },
                                            callback: function() {
                                                if (view.contactStoreById.getCount() == 0) {
                                                    Ext.Msg.alert('提示', '无符合条件的事件');
                                                }
                                            }
                                        });
                                        Ext.getCmp('salesOrderStateCombo').setReadOnly(true);
                                        Ext.getCmp('customerRepresentativeId').setReadOnly(true);
                                        view.down('#customerID').setValue(value.customerID);
                                        view.down('#customerID').setReadOnly(true);

                                        Ext.getCmp('eventID').setValue(value.eventID);
                                        Ext.getCmp('eventID').setReadOnly(true);
                                        Ext.getCmp('customerRepresentativeId').setValue(value.contactID);
                                        view.getEventStore.load();
                                        var contact = Ext.getCmp('customerRepresentativeId').getValue();
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
                                                view.down('#customerContact').setValue(responseText);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    } else {
                        box.confirm('提示', '确定允许该事件进入下一阶段？', function showResult(button) {
                            if (button == 'yes') {
                                Ext.getCmp('transactionHandleWindow').close();
                                Ext.Ajax.request({
                                    url: 'salesAddOrUpdateEvent.action',
                                    params: {
                                        jsonString: Ext.encode(value),
                                        flag: 'transactionAllow',
                                        nextStatus: nextStatus
                                    },
                                    method: 'POST',
                                    success: function(response) {
                                        if (CRM.checkResponse(response)) {
                                            return;
                                        }
                                        store.loadPage(store.currentPage);
                                        Ext.crm.msg('该事件成功进入下一阶段', '');
                                    },
                                    failure: function(response) {
                                        Ext.Msg.alert('错误', '事件处理失败，请联系管理员！');
                                    }
                                });
                            }
                        });
                    }
                },
                failure: function(response) {
                    Ext.Msg.alert('错误', '查询订单是否存在出错，请联系管理员！');
                }
            });
        }
        if (nextStatus != 3 && formValues.salesEventTransaction == '000900040001') {
            box.confirm('提示', '确定允许该事件进入下一阶段？', function showResult(button) {
                if (button == 'yes') {
                    Ext.getCmp('transactionHandleWindow').close();
                    Ext.Ajax.request({
                        url: 'salesAddOrUpdateEvent.action',
                        params: {
                            jsonString: Ext.encode(value),
                            flag: 'transactionAllow',
                            nextStatus: nextStatus
                        },
                        method: 'POST',
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            store.loadPage(store.currentPage);
                            Ext.crm.msg('该事件成功进入下一阶段', '');
                        },
                        failure: function(response) {
                            Ext.Msg.alert('错误', '事件处理失败，请联系管理员！');
                        }
                    });
                }
            });
        }
        if (formValues.salesEventTransaction == '000900040002') {
            box.confirm('注意', '确定中止该事件继续进行？', function showResult(button) {
                if (button == 'yes') {
                    Ext.getCmp('transactionHandleWindow').close();
                    Ext.Ajax.request({
                        url: 'salesAddOrUpdateEvent.action',
                        params: {
                            jsonString: Ext.encode(value),
                            flag: 'transactionRefused'
                        },
                        method: 'POST',
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            store.loadPage(store.currentPage);
                            Ext.crm.msg('已成功中止该事件', '');
                        },
                        failure: function(response) {
                            Ext.Msg.alert('错误', '事件处理失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    },

    deleteSalesEvent: function(button, e, eOpts) {
        var salesEventList = Ext.getCmp('salesEventList');
        utils.delRecordsCheck(salesEventList, 'salesEventDeleteAction.action', 'eventID');
    },

    addSalesOpportunities: function(button, e, eOpts) {
        var view = Ext.widget('salesopportunitiesupdate');
        this.loadTabs(view, 0);
        view.setTitle('添加销售事件信息');
        view.down('#cusReset').setText('清空');
        view.down('#submitPerson').setValue(REAL_NAME);
    },

    editSalesOpportunities: function(button, e, eOpts) {
        var salesopportunitieslist = Ext.getCmp('salesEventList');
        var record = salesopportunitieslist.getSelectionModel().getSelection();
        var view = Ext.widget('salesopportunitiesupdate');
        this.loadTabs(view, record[0].get('sort') - 1);
        view.down('#status').setReadOnly(true);
        var customerID = record[0].get('customerID');
        view.setTitle('编辑销售事件信息');
        view.down('#cusReset').setText('重置');
        var contactStoreById = Ext.getCmp('salesUpdateWindow').contactStoreById;
        contactStoreById.load();
        view.down('form').loadRecord(record[0]);
        view.contactStoreAll.each(function(record) {
            if (record.get('customerID') == customerID) {
                view.contactStoreById.add(record);
            }
        });
        Ext.getCmp('salesCustomerRepresentativeId').forceSelection = true;
        this.loadTabsDenamdStore(record[0].get('eventID'));
    },

    loadTabs: function(view, num) {
        var activeTab = Ext.getCmp('activeTab');
        view.statusCombo.load({
            callback: function(records, operation, success) {
                Ext.Array.each(records, function(record) {
                    var tabTitleTip = record.get('value');
                    var tabTitle = tabTitleTip.substring(0, 5);
                    if (tabTitleTip.length > 6) {
                        tabTitle = tabTitle + '..';
                    }
                    if (tabTitleTip.length > 25) {
                        var num = Math.floor(tabTitleTip.length / 2);
                        var tabTitleTip1 = tabTitleTip.substring(0, num);
                        var tabTitleTip2 = tabTitleTip.substring(num + 1, tabTitleTip.length);
                        tabTitleTip = tabTitleTip1 + '<br>' + tabTitleTip2;
                    }
                    activeTab.add({
                        title: tabTitle,
                        tabConfig: {
                            tooltip: {
                                text: tabTitleTip
                            }
                        },
                        closable: false,
                        itemId: 'activeTab' + record.get('code'),
                        items: [ {
                            xtype: 'textarea',
                            name: 'demand',
                            itemId: 'demand',
                            width: 650,
                            height: 200,
                            labelWidth: 40,
                            fieldLabel: '需求',
                            enforceMaxLength: true,
                            maxLength: 1024,
                            maxLengthText: '需求长度不能超过1024个字符！'
                        }, {
                            name: 'demandStatus',
                            xtype: 'hiddenfield',
                            itemId: 'demandStatus',
                            value: record.get('code')
                        } ]
                    });
                });
                activeTab.setActiveTab(num);
            }
        });
    },

    loadTabsDenamdStore: function(eventID) {
        var TabsDenamdStore = Ext.widget('TabsDenamdStore');
        setTimeout(fn, 300);
        function fn() {
            TabsDenamdStore.load({
                params: {
                    eventID: eventID
                },
                callback: function(records, operation, success) {
                    var activeTab = Ext.getCmp('activeTab');
                    Ext.Array.each(records, function(item) {
                        activeTab.child('#activeTab' + item.data.status).down('#demand').setValue(item.data.demands);
                    });
                }
            });
        }
    },

    changeBtnType: function(sm, selections) {
        Ext.getCmp('salesEventDelBtn').setDisabled(selections.length == 0);
        Ext.getCmp('salesOpportunitiesEditBtn').setDisabled(selections.length != 1);
        if (selections.length == 1) {
            Ext.getCmp('salesEventDetail').show();
            Ext.getCmp('transactionTrackBtn').setDisabled(false);
        } else if (selections.length != 1) {
            Ext.getCmp('salesEventDetail').hide();
            Ext.getCmp('transactionTrackBtn').setDisabled(true);
        }
    },

    update: function(button, e, eOpts) {
        var view = Ext.getCmp('salesUpdateWindow');
        if (view.down('#cusReset').getText() == '清空') {
            flag = 'add';
        } else {
            flag = 'update';
        }
        utils.updateRecord(button, 'salesAddOrUpdateEvent.action?flag=' + flag, 'salesEventList');
    },

    doReset: function(button, e, eOpts) {
        var form = button.up('window').down('form');
        if (button.text == '清空') {
            form.getForm().reset();
            form.down('#submitPerson').setValue(REAL_NAME);
        } else if (button.text == '重置') {
            button.up('window').contactStoreById.load();
            var selection = Ext.getCmp('salesEventList').getView().getSelectionModel().getSelection()[0];
            form.loadRecord(selection);
            var TabsDenamdStore = Ext.widget('TabsDenamdStore');
            TabsDenamdStore.load({
                params: {
                    eventID: selection.get('eventID')
                },
                callback: function(records, operation, success) {
                    var activeTab = Ext.getCmp('activeTab');
                    Ext.Array.each(records, function(item) {
                        activeTab.child('#activeTab' + item.data.status).down('#demand').setValue(item.data.demands);
                    });
                }
            });
        }

    },

    close: function(button, e, eOpts) {
        button.up('window').close();
    },

    clearContactStore: function(combo, record, index) {
        combo.up('window').contactStoreById.removeAll();
        Ext.getCmp('salesCustomerRepresentativeId').reset();
        Ext.getCmp('salesCustomerRepresentativeId').setReadOnly(false);
    },

    addContactStore: function(combo, eOpts) {
        combo.store.removeAll();
        combo.up('window').contactStoreAll.each(function(record) {
            if (record.get('customerID') == combo.up('window').down('#customerID').getValue()) {
                combo.up('window').contactStoreById.add(record);
            }
        });
        if (combo.up('window').down('#customerID').value == null) {
            Ext.MessageBox.alert("提示", "请先选择客户名称！");
        } else {
            if (combo.up('window').contactStoreById.getCount() == 0) {
                Ext.MessageBox.alert("提示", "请先给客户添加联系人！");
            }
        }
    },

    transactionProcessingReset: function(button) {
        var form = button.up('window').down('form');
        var TabsDenamdStore = Ext.widget('TabsDenamdStore');
        var values = form.getRecord();
        Ext.getCmp('salesEventTransaction').setValue('000900040001');
        form.down('#nextDemand').setVisible(true);
        button.up('window').setHeight(300);
        TabsDenamdStore.load({
            params: {
                eventID: values.data.eventID
            },
            callback: function(records, operation, success) {
                Ext.Array.each(records, function(item) {
                    if (item.data.sort == values.data.sort + 1) {
                        form.down('#nextDemand').setValue(item.data.demands);
                    }
                });
            }
        });
    },

    comboChangeSelect: function(combo, records, eOpts) {
        if (records[0].get('code') == '0' && records.length > 1) {
            combo.setValue(records[1].get('code'));
        } else {
            Ext.Array.each(records, function(record) {
                if (record.get('code') == '0') {
                    combo.setValue('0');
                    return false;
                }
            });
        }
    },

    changeTransactionHandleWindowStyle: function(combo, records, eOpts) {
        var form = combo.up('form');
        var win = Ext.getCmp('transactionHandleWindow');
        Ext.Array.each(records, function(record) {
            if (record.get('code') == '000900040002') {
                form.down('#nextDemand').setVisible(false);
                win.setHeight(200);
            } else {
                form.down('#nextDemand').setVisible(true);
                win.setHeight(300);
            }
        });
    },

    salesAddProduct: function(button) {
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
                    store.on("load", me.salesStoreLoaded);
                    store.on("beforeload", me.salesStoreBeforeload);
                    Ext.getCmp('selectProduct').close();
                    Ext.widget('selectProduct');
                    store.loadPage(1);
                }
            }
        });
    },

    salesStoreLoaded: function() {
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

    // 注册allproduct beforeload 事件
    salesStoreBeforeload: function(store, options) {
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

    // 添加产品信息到UPDATE页面
    salesAddProductInfo: function(button) {
        var grid = Ext.getCmp('allProduct');
        var allProductStore = grid.getStore();
        var view = Ext.getCmp('salesOrderUpdate');
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
            Ext.Msg.alert('提示', '请选择产品');
        }
    },

    salesProductQuery: function(button) {
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

    salesDeleteProduct: function(button) {
        var grid = Ext.getCmp('buyProduct');
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        if (checkRecord.length > 0) {
            store.remove(checkRecord);
        }
    },

    salesOrderUpdate: function(button) {
        var store = Ext.getCmp('buyProduct').getStore();
        if (store.getCount() < 1) {
            Ext.Msg.alert('提示', '产品为空，请添加产品');
            return;
        } else {
            // 生成订单基本信息
            var id = 0;
            var eventID = Ext.getCmp('eventID').getValue();
            var orderInfor = {
                id: id,
                type: 1,
                deliveryDate: Ext.getCmp('salesDeliveryDate').getRawValue(),
                customerContact: Ext.getCmp('customerContact').getValue(),
                orderID: Ext.getCmp('salesOrderID').getValue(),
                orderState: Ext.getCmp('salesOrderStateCombo').getValue(),
                customerID: Ext.getCmp('salesCustomerID').getValue(),
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
                    orderID: Ext.getCmp('salesOrderID').getValue(),
                    isAbolished: 0,
                    productID: store.getAt(i).get('productID'),
                    name: store.getAt(i).get('name'),
                    price: store.getAt(i).get('price'),
                    discount: document.getElementById('discount' + store.getAt(i).get('id')).value,
                    productNumber: document.getElementById('productNumber' + store.getAt(i).get('id')).value
                };
                orderProduct[i] = product;
            }
            var demandMap = [];
            var jsonString = {
                orderDto: orderInfor,
                selectProductDtos: orderProduct,
                salesEventFlowDtos: demandMap
            };
            // 向后台提交添加订单请求
            Ext.Ajax.request({
                url: 'addOrUpdateFormalOrder.action',
                params: {
                    jsonString: Ext.encode(jsonString)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    Ext.getCmp('salesOrderUpdate').close();
                    Ext.crm.msg("生成订单成功！", "");
                },
                failure: function(resp, opts) {
                    // 提示添加订单失败
                }
            });
        }
    }
});
var cacheStore = '';
var orderStage = [];