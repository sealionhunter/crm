Ext.define('CRM.controller.customerManagement.customerProfiles.Business', {
    extend: 'Ext.app.Controller',
    views: ['customerManagement.customerProfiles.BusinessList',
            'customerManagement.customerProfiles.BusinessCard',
            'customerManagement.customerProfiles.BusinessUpdate'],
    stores:['customerManagement.customerProfiles.Business'],
    models: ['customerManagement.customerProfiles.Business'],
    init: function() {
        this.control({
            'businesslist': {
                itemdblclick: this.showEditWin,
                selectionchange: this.changeBtn
            },
            'businesslist button[action=showAddWin]': {
                click: this.showAddWin
            },
            'businesslist button[action=showEditWin]': {
                click: this.showEditWin
            },
            'businesslist button[action=showDelWin]': {
                click: this.showDelWin
            },
            'businesslist button[action=queryBtn]': {
                click: this.businessQuery
            },
            'businessupdate button[action=addOrUpdateBusiness]': {
                click: this.addOrUpdateBusiness
            },
            'businessupdate button[action=resetBusiness]': {
                click: this.resetBusiness
            },
            'businessupdate button[action=closeWin]': {
                click: utils.winClose
            },
            'businessupdate textarea': {
                blur: utils.trimSpace
            }
        });
    },
    viewInit: function(treeId, panel, record) {
        var card = Ext.getCmp('businesscard');
        if (typeof (businessCard) === 'undefined') {
            card = Ext.widget('businesscard');
        }
        if (typeof panel !== 'undefined') {
            panel.insert(0, card);
        }

        var businessList = Ext.getCmp('businesslist');

        // load page
        if (typeof record !== 'undefined') {
            this.record = record;
            var store  = businessList.getStore();
            alert(record.get('customerID'));
            store.on('beforeload', function(store, options) {
                var new_params = {
                        customerID: record.get('customerID')
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            // load page one
            store.loadPage(1);
            store.currentPage = 1;
        }
        return businessList;
    },
    showAddWin: function() {
        var me =this;
        var view = Ext.widget('businessupdate');
        view.setTitle('添加业务');
        view.down('#customerID').setValue(me.record.get('customerID'));
        view.down('[action=resetBusiness]').setText('清空');
    },
    showEditWin: function(button) {
        var grid = button.up('grid');
        var checkRecord = grid.getSelectionModel().getSelection();
        var view = Ext.widget('business');
        view.setTitle('编辑领导建议信息');
        view.down('[action=resetBusiness]').setText('重置');
        // load select record
        view.down('form').loadRecord(checkRecord[0]);
    },
    showDelWin: function(button) {
        var grid = button.up('grid');
        // check delete
        var checkRecord = grid.getSelectionModel().getSelection();
        utils.delRecordsCheck(grid, 'deleteBusiness.action', 'businessId');
    },
    addOrUpdateBusiness: function(button) {
        utils.updateRecord(button, 'addOrUpdateBusiness.action', 'businesslist', true);
    },
    resetBusiness: function(button) {
        var form = button.up('window').down('form');
        form.loadRecord(record);
    },
    changeBtn: function(sm, selections){
        var listGrid = Ext.getCmp('businesslist');
        listGrid.down('[action=showDelWin]').setDisabled(selections.length == 0);
        listGrid.down('[action=showEditWin]').setDisabled(selections.length != 1);

    },
    businessQuery: function(button) {
        if (button.up('toolbar').down('#searchText').isValid()) {
            var store = button.up('grid').getStore();
            var searchValue = button.up('toolbar').down('#searchText').getValue().toString();
            var jsonString = Ext.encode(Ext.decode('{searchText : ' + Ext.encode(searchValue) + '}'));
            var searchFlag = 1;
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
    }
});