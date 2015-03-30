Ext.define('CRM.controller.salesManagement.eventFlow.EventFlow', {
    extend: 'Ext.app.Controller',
    views: [ 'salesManagement.eventFlow.EventFlow', 'salesManagement.eventFlow.EventFlowUpdate' ],
    stores: [ 'salesManagement.salesEventFlowCode.SalesEventFlowCode', 'salesManagement.salesEventFlowCode.SalesEventFlowCategory' ],
    models: [ 'salesManagement.salesEventFlowCode.SalesEventFlowCode', 'salesManagement.salesEventFlowCode.SalesEventFlowCategory' ],
    init: function() {
        this.control({
            'eventFlow': {
                itemdblclick: this.editEventFlow,
                selectionchange: this.changeBtnType
            },
            'eventFlow > toolbar button[action=eventFlowAddBtn]': {
                click: this.addEventFlow
            },
            'eventFlow > toolbar button[action=eventFlowEditBtn]': {
                click: this.editEventFlow
            },
            'eventFlow > toolbar button[action=eventFlowDeleteBtn]': {
                click: this.deleteEventFlow
            },
            'eventFlow > toolbar button[action=eventFlowUpBtn]': {
                click: this.upEventFlow
            },
            'eventFlow > toolbar button[action=eventFlowDownBtn]': {
                click: this.downEventFlow
            },
            'eventFlowUpdate button[action=update]': {
                click: this.update
            },
            'eventFlowUpdate button[action=doreset]': {
                click: this.doReset
            }
        });
    },

    viewInit: function(flag) {
        var transactionManagement = Ext.getCmp('eventFlow');
        if (typeof (transactionManagement) == 'undefined') {
            transactionManagement = Ext.widget('eventFlow');
        }
        Ext.getCmp('centercard').insert(1, transactionManagement);
        Ext.getCmp('centercard').getLayout().setActiveItem('eventFlow');
        transactionManagement.getStore().load();
        return transactionManagement;
    },

    changeBtnType: function(sm, selections) {
        Ext.getCmp('eventFlowDeleteBtn').setDisabled(selections.length == 0);
        Ext.getCmp('eventFlowEditBtn').setDisabled(selections.length != 1);
        Ext.getCmp('eventFlowUpBtn').setDisabled(selections.length != 1);
        Ext.getCmp('eventFlowDownBtn').setDisabled(selections.length != 1);
        var records = Ext.getCmp('eventFlow').getSelectionModel().getSelection();
        var store = Ext.getCmp('eventFlow').getStore();
        var storeCount = store.getCount();
        var intentOrderRecordCount = 0;
        var selectionIntentOrderRecordCount = 0;
        var orderRecordCount = 0;
        var selectionOrderRecordCount = 0;
        for ( var i = 2; i < storeCount - 1; i++) {
            if (store.getAt(i).data.category == '00040002') {
                intentOrderRecordCount++;
            }
            if (store.getAt(i).data.category == '00040003') {
                orderRecordCount++;
            }
        }
        Ext.Array.each(records, function(record) {
            if (record == store.getAt(storeCount - 1) || record == store.getAt(0) || record == store.getAt(1)) {
                Ext.getCmp('eventFlowDeleteBtn').setDisabled(true);
                Ext.getCmp('eventFlowEditBtn').setDisabled(true);
                Ext.getCmp('eventFlowUpBtn').setDisabled(true);
                Ext.getCmp('eventFlowDownBtn').setDisabled(true);
            }
            if (record == store.getAt(2) || record == store.getAt(intentOrderRecordCount + 2)) {
                Ext.getCmp('eventFlowUpBtn').setDisabled(true);
            }
            if (record == store.getAt(storeCount - 2) || record == store.getAt(intentOrderRecordCount + 1)) {
                Ext.getCmp('eventFlowDownBtn').setDisabled(true);
            }
            if (record.data.category == '00040002') {
                selectionIntentOrderRecordCount++;
            }
            if (record.data.category == '00040003') {
                selectionOrderRecordCount++;
            }
        });
        if (intentOrderRecordCount == selectionIntentOrderRecordCount || orderRecordCount == selectionOrderRecordCount) {
            Ext.getCmp('eventFlowDeleteBtn').setDisabled(true);
        }
    },

    editEventFlow: function(button) {
        var intentOrderRecordCount = 0;
        var orderRecordCount = 0;
        var eventFlow = Ext.getCmp('eventFlow');
        var record = eventFlow.getSelectionModel().getSelection()[0];
        var store = eventFlow.getStore();
        var storeCount = store.getCount();
        if (record.index != 0 && record.index != 1 && record.index != storeCount - 1) {
            var view = Ext.widget('eventFlowUpdate');
            view.setTitle('编辑销售事件流程名称');
            view.down('#cusReset').setText('重置');
            view.down('form').loadRecord(record);
            for ( var i = 2; i < storeCount - 1; i++) {
                if (store.getAt(i).data.category == '00040002') {
                    intentOrderRecordCount++;
                }
                if (store.getAt(i).data.category == '00040003') {
                    orderRecordCount++;
                }
            }
            // 各模块只剩一个销售流程的时候只可以修改名称，不能修改模块
            if (intentOrderRecordCount == 1 && record.data.category == '00040002') {
                view.down('#SalesEventFlowCodeCategory').setReadOnly(true);
            } else if (orderRecordCount == 1 && record.data.category == '00040003') {
                view.down('#SalesEventFlowCodeCategory').setReadOnly(true);
            } else {
                view.down('#SalesEventFlowCodeCategory').setReadOnly(false);
            }
        }
    },

    addEventFlow: function(button) {
        var eventFlow = Ext.getCmp('eventFlow');
        var storeCount = eventFlow.getStore().getCount();
        if (storeCount < 15) {
            var view = Ext.widget('eventFlowUpdate');
            view.setTitle('添加销售事件流程名称');
            view.down('#cusReset').setText('清空');
        } else {
            Ext.MessageBox.alert('提示', '销售事件流程个数最多为15！');
        }
    },

    update: function(button) {
        var win = button.up('window');
        var form = win.down('form');
        var store, storeCount, flag, oldSort;
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            var values = form.getValues();
            var record = form.getRecord() || '';
            var list = Ext.getCmp('eventFlow');
            store = list.getStore();
            storeCount = store.getCount();
            var datas = [];
            if (record == '') {
                if (values.category == '00040003') {
                    values.sort = storeCount;
                } else {
                    values.sort = 3;
                }
                values.code = 0;
                flag = 1;
                datas.push(values);
            } else {
                oldSort = record.data.sort;
                flag = 0;
                // 不修改销售模块
                if (record.data.category == values.category) {
                    values.sort = record.index + 1;
                    values.code = record.data.code;
                    datas.push(values);
                } else {
                    // 修改到00040002模块
                    if (values.category == '00040002') {
                        values.sort = 3;
                    }
                    // 修改到00040003模块
                    if (values.category == '00040003') {
                        values.sort = storeCount - 1;
                    }
                    values.code = record.data.code;
                    datas.push(values);
                }
            }
            Ext.Ajax.request({
                url: 'addOrUpdateEventFlow.action',
                params: {
                    jsonString: Ext.encode(datas),
                    flag: flag,
                    oldSort: oldSort
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText) || '';
                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        if (record == '') {
                            Ext.crm.msg("添加成功！", "");
                        } else {
                            Ext.crm.msg("编辑成功！", "");
                        }
                        if (list != '') {
                            store.load();
                        }
                        win.close();
                    }
                },
                failure: function(response) {
                    if (record == '') {
                        messageBox.alert('提示', '添加失败，请联系管理员！');
                    } else {
                        messageBox.alert('提示', '编辑失败，请联系管理员！');
                    }
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },

    deleteEventFlow: function(button) {
        var grid = button.up('toolbar').up('gridpanel');
        var store = grid.getStore();
        // 返回值为 Record 数组
        var records = grid.getSelectionModel().getSelection();
        if (records.length == 0) {
            Ext.Msg.alert('提示', '至少选择一个节点删除！');
        } else {
            box.confirm('提示', '确定要删除这些节点？', function(btn) {
                if (btn == 'yes') {
                    var datas = [];
                    Ext.Array.each(records, function(record) {
                        datas.push(record.data);
                    });
                    Ext.Ajax.request({
                        url: 'deleteEventFlow.action',
                        params: {
                            jsonString: Ext.encode(datas)
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var responseText = Ext.decode(response.responseText) || '';
                            if (responseText.items != null) {
                                Ext.Msg.alert('提示', Ext.decode(response.responseText).items);
                            } else {
                                Ext.crm.msg('删除成功！', '');
                            }
                            store.load();
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '删除失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    },

    doReset: function(button) {
        var form = button.up('window').down('form');
        if (button.text == '清空') {
            form.getForm().reset();
        } else if (button.text == '重置') {
            var selection = Ext.getCmp('eventFlow').getView().getSelectionModel().getSelection()[0];
            form.loadRecord(selection);
        }
    },

    upEventFlow: function(button) {
        var grid = button.up('toolbar').up('gridpanel');
        var store = grid.getStore();
        // 返回值为 Record 数组
        var record = grid.getSelectionModel().getSelection()[0];
        var upRecord = store.getAt(record.index - 1);
        if (record.length == 0) {
            Ext.Msg.alert('警告', '至少选择一个节点移动！');
        } else {
            var datas = [];
            record.data.sort = record.index;
            datas.push(record.data);
            upRecord.data.sort = upRecord.index + 2;
            datas.push(upRecord.data);
            Ext.Ajax.request({
                url: 'addOrUpdateEventFlow.action',
                params: {
                    jsonString: Ext.encode(datas)
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    Ext.crm.msg("上移成功！", "");
                    store.load();
                },
                failure: function(response) {
                    messageBox.alert('提示', '上移失败，请联系管理员！');
                }
            });
        }
    },

    downEventFlow: function(button) {
        var grid = button.up('toolbar').up('gridpanel');
        var store = grid.getStore();
        // 返回值为 Record 数组
        var record = grid.getSelectionModel().getSelection()[0];
        var downRecord = store.getAt(record.index + 1);
        if (record.length == 0) {
            Ext.Msg.alert('警告', '至少选择一个节点移动！');
        } else {
            var datas = [];
            record.data.sort = record.index + 2;
            datas.push(record.data);
            downRecord.data.sort = downRecord.index;
            datas.push(downRecord.data);
            Ext.Ajax.request({
                url: 'addOrUpdateEventFlow.action',
                params: {
                    jsonString: Ext.encode(datas)
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    Ext.crm.msg("下移成功！", "");
                    store.load();
                },
                failure: function(response) {
                    messageBox.alert('提示', '下移失败，请联系管理员！');
                }
            });
        }
    }
});