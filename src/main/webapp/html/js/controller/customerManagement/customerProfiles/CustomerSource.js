Ext.define('CRM.controller.customerManagement.customerProfiles.CustomerSource', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.SourceInfo', 'customerManagement.customerProfiles.SourceList', 'customerManagement.customerProfiles.SourceUpdate',
            'customerManagement.customerProfiles.SourceDetail' ],
    stores: [ 'customerManagement.customerProfiles.CustomerName', 'customerManagement.customerProfiles.CustomerSource' ],

    model: [ 'customerManagement.customerProfiles.CustomerName', 'customerManagement.customerProfiles.CustomerSource' ],

    init: function() {
        this.control({
            'sourcelist': {
                select: this.changeSourceDetail,
                itemdblclick: this.editSource,
                selectionchange: this.changeBtn
            },
            'sourcelist textfield': {
            // blur : utils.trimSpaceSearch
            },
            'sourceupdate textfield': {
                blur: utils.trimSpace
            },
            'sourcelist combobox': {
                select: this.comboChangeSelect
            },
            'sourcelist toolbar button[id=SourceAddBtn]': {
                click: this.addSource
            },
            'sourcelist toolbar button[id=SourceEditBtn]': {
                click: this.editSource
            },
            'sourcelist toolbar button[id=SourceDelBtn]': {
                click: this.delSource
            },
            'sourcelist toolbar button[action=superQueryBtn]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'sourcelist button[action=queryBtn]': {
                click: this.sourceQuery
            },
            'sourceupdate button[action=updateSourceSubmitBtn]': {
                click: this.updateSourceSubmit
            },
            'sourceupdate button[action=updateSourceResetBtn]': {
                click: utils.resetRecord
            },
            'sourceupdate button[action=updateSourceCancelBtn]': {
                click: utils.winClose
            }
        });
    },
    viewInit: function(treeId) {
        var sourceinfo = Ext.getCmp('sourceinfo');
        if (typeof (sourceinfo) == 'undefined') {
            sourceinfo = Ext.widget('sourceinfo');
        }
        Ext.getCmp('centercard').insert(1, sourceinfo);
        Ext.getCmp('centercard').getLayout().setActiveItem('sourceinfo');
        var sourceList = Ext.getCmp('sourceListCard');
        utils.authorizationControl(treeId, sourceList);
        if (sourceList.sourceTypeStore.getCount() == 0) {
            sourceList.sourceTypeStore.load({
                params: {
                    code: '00010004'
                }
            });
        }
        sourceList.customerStore.load({
            params: {
                customerFlag: 0,
                userID: USER_ID
//            },
//            callback: function(records, opeation, index) {
//                count = records.length;
            }
        });
        utils.hideSuperQuery(sourceList);
        return sourceinfo;
    },
    changeSourceDetail: function(view, record, el, index, e) {
        Ext.getCmp('sourcedetail').getForm().loadRecord(record);
    },
    changeBtn: function(sm, selections) {
        if (selections.length != 1) {
            Ext.getCmp('sourcedetail').hide();
        } else {
            Ext.getCmp('sourcedetail').show();
        }
        Ext.getCmp('SourceDelBtn').setDisabled(selections.length == 0);
        Ext.getCmp('SourceEditBtn').setDisabled(selections.length !== 1);
    },
    comboChangeSelect: function(combo, records, eOpts) {
        utils.comboChangeSelect(combo, records);
    },
    addSource: function(button) {
        var win = Ext.widget('sourceupdate');
        var customerStore = button.up('grid').customerStore;
        if (count == 0) {
            messageBox.alert("提示", "无客户，请先添加客户！");
        } else {
            customerStore.load({
                params: {
                    customerFlag: 1,
                    userID: USER_ID
                },
                callback: function(records, opeation, index) {
                    if (records.length == 0) {
                        messageBox.alert("提示", "客户来源已添加完毕，请先添加新客户！");
                    } else {
                        win.show();
                    }
                }
            });
        }
    },
    editSource: function(button) {
        var grid = button.up('grid');
        if (!grid.down('#11202').hidden) {
            var checkRecord = grid.getSelectionModel().getSelection();
            var win = Ext.widget('sourceupdate');
            var form = win.down('form');
            win.show();
            win.setTitle('编辑客户来源');
            win.down('#reset').setText('重置');
            button.up('grid').customerStore.load({
                params: {
                    customerFlag: 0,
                    userID: USER_ID
                }
            });
            form.loadRecord(checkRecord[0]);
            win.down('#customerIDCombo').hide();
            win.down('#customerIDDisplay').show();
        }
    },
    delSource: function(button) {
        var grid = button.up('grid');
        utils.delRecords(grid, 'deleteSource.action', 'sourceID');
    },
    openOrCloseSuperQueryBtn: function(button) {
        var gridPanel = button.up('gridpanel');
        var toolForm = gridPanel.down('form');
        if (toolForm.isHidden()) {
            if (gridPanel.typeSearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.typeSearchStore, gridPanel.sourceTypeStore, Ext.getCmp('sourceTypeCombo'));
            }
        }
        utils.openOrCloseSuperQueryBtn(button);
    },
    sourceQuery: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid() || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var jsonString = "";
            if (!button.up('grid').down('form').isHidden()) {
                searchFlag = 2;
                var form = button.up('form');
                var values = form.getValues();
                var sourceTypeArray = new Array();
                sourceTypeArray.push(values.sourceType);
                values.sourceType = sourceTypeArray;
                jsonString = Ext.encode(values);
            } else {
                var searchValue = button.up('toolbar').down('#searchText').getValue().toString();
                jsonString = Ext.encode(Ext.decode('{searchText : ' + Ext.encode(searchValue) + '}'));
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: jsonString
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
    updateSourceSubmit: function(button) {
        utils.updateRecord(button, 'updateSource.action', "sourceListCard", "客户来源");
    }
});