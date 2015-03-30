Ext.define('CRM.controller.customerManagement.customerProfiles.CustomerResume', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.CoopResumeList',
            'customerManagement.customerProfiles.CoopResumeUpdate' ],
    stores: [ 'customerManagement.customerProfiles.CoopResume' ],
    init: function() {
        this.control({
            'coopResumelist': {
                itemdblclick: this.toEditCoopResume,
                selectionchange: this.changeBtnTypeCoopResume
            },
            'coopResumelist button[action=openOrCloseSuperQueryBtn]': {
                click: this.coopResumeOpenOrCloseSuperQuery
            },
            'coopResumelist button[action=addCoop]': {
                click: this.addCoopResume
            },
            'coopResumelist button[action=editCoop]': {
                click: this.editCoopResume
            },
            'coopResumelist button[action=deleteCoop]': {
                click: this.deleteCoopResume
            },
            'coopresumeupdate button[action=update]': {
                click: this.updateCoopResume
            },
            'coopresumeupdate button[action=doreset]': {
                click: this.resetRecord
            },
            'coopresumeupdate button[action=close]': {
                click: utils.winClose
            },
            'coopresumeupdate textfield': {
                blur: utils.trimSpace
            },
            'coopResumelist combobox': {
                select: utils.comboChangeSelect
            },
            'coopResumelist textfield': {
                blur: utils.trimSpace
            },
            'coopResumelist button[action=queryBtn]': {
                click: this.coopResumeQuery
            },
            'coopResumelist button[action=coopResumeSuperQueryBtn]': {
                click: this.coopResumeQuery
            }
        });
    },
    resetRecord: function(button) {
        var form = button.up('panel').down('form');
        var record = form.getRecord() || '';
        if (record == '') {
            var customerID = form.down('#customerID').getValue();
            form.getForm().reset();
            form.down('#customerID').setValue(customerID);
        } else {
            form.loadRecord(record);
        }
    },
    viewInit: function(selection, panel) {
        if (typeof panel === 'undefined') {
           return;
        } 
        var win = Ext.widget('coopResumelist');
        panel.insert(0, win);
//        var customerName = Ext.String.htmlEncode(selection.get('customerName'));
        var customerID = selection.get('customerID');
        win.down('#customerID').setValue(customerID);
        if (win.projectTypeStore.getCount() == 0) {
            win.projectTypeStore.load({
                params: {
                    code: '00010006'
                }
            });
        }
        var store = win.getStore();
//        win.setTitle('合作履历表-' + customerName);
        this.coopStoreLoad(customerID, store, 1, 0);
    },
    toEditCoopResume: function(grid, record) {
        var view = Ext.widget('coopresumeupdate');
        view.setTitle('编辑客户信息');
        view.down('#cusReset').setText('重置');
        view.down('form').loadRecord(record);
    },
    changeBtnTypeCoopResume: function(sm, selections) {
        Ext.getCmp('coopResumeDelBtn').setDisabled(selections.length == 0);
        Ext.getCmp('coopResumeEditBtn').setDisabled(selections.length != 1);
    },
    addCoopResume: function(button) {
        var selection = Ext.getCmp('customerlist').getView().getSelectionModel().getSelection()[0];
        var customerID = selection.get('customerID');
        var view = Ext.widget('coopresumeupdate');
        view.down('#customerID').setValue(customerID);
        view.setTitle('添加合作履历');
        view.down('#cusReset').setText('清空');
    },
    editCoopResume: function(button) {
        var coopResumelist = button.up('gridpanel');
        var record = coopResumelist.getSelectionModel().getSelection();
        var update = Ext.widget('coopresumeupdate');
        update.setTitle('编辑合作履历');
        update.down('#cusReset').setText('重置');
        update.down('form').loadRecord(record[0]);
    },
    deleteCoopResume: function(button) {
        var grid = button.up('gridpanel');
        utils.delRecords(grid, 'coopResumeDelete.action', 'coopResumeID');
    },
    updateCoopResume: function(button) {
        utils.updateRecord(button, 'coopResumeAddOrUpdate.action', 'coopresumelistgrid');
    },
    coopResumeOpenOrCloseSuperQuery: function(button) {
        var win = button.up('grid');
        var toolForm = win.down('form');
        var customerID = win.down('#customerID').getValue();
        var store = win.getStore();
        if (toolForm.isHidden()) {
            if (win.projectTypeSearchStore.getCount() == 0) {
                utils.searchStoreInset(win.projectTypeSearchStore, win.projectTypeStore, toolForm.down('#projectType'));
            }
        }
        utils.openOrCloseSuperQueryBtn(button);
        this.coopStoreLoad(customerID, store, 1, 0);
    },
    coopStoreLoad: function(customerID, store, page, searchFlag) {
        store.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                customerID: customerID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1);
        store.currentPage = 1;
    },
    coopResumeQuery: function(button) {
        var store = button.up('grid').getStore();
        var searchFlag = 1;
        var jsonString;
        if (!button.up('grid').down('form').isHidden()) {
            searchFlag = 2;
            var form = button.up('form');
            var values = form.getValues();
            var projectTypeArray = new Array();
            projectTypeArray.push(values.projectType);
            values.projectType = projectTypeArray;
            jsonString = Ext.encode(values);
        } else {
            var customerID = button.up('panel').down('#customerID').getValue();
            var searchValue = button.up('toolbar').down('#searchText').getValue();
            var searchBean = Ext.decode('{searchText : "' + searchValue + '",customerID:"' + customerID + '"}');
            jsonString = Ext.encode(searchBean);
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
    }
});