Ext.define('CRM.controller.customerManagement.customerProfiles.Customer', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.CustomerList',
            'customerManagement.customerProfiles.CustomerUpdate',
            'customerManagement.customerProfiles.CustomerDatail',
            'customerManagement.customerProfiles.CustomerInfo' ],
    stores: [ 'customerManagement.customerProfiles.Customer' ],
    init: function() {
        this.control({
            'customerlist': {
//                select: this.changeCusDetail,
                itemdblclick: this.toEditCustomer,
                scrollershow: utils.scrollershow,
                selectionchange: this.changeBtnType
            },
            'customerlist textfield': {
                blur: utils.trimSpace
            },
            'customerlist button[action=queryBtn]': {
                click: this.customerQuery
            },
            'customerlist button[action=openOrCloseSuperQueryBtn]': {
                click: this.openOrCloseSuperQuery
            },
            'customerlist button[action=customerSuperQueryBtn]': {
                click: this.customerQuery
            },
            'customerlist button[action=customerLookResume]': {
                click: this.customerLookResume
            },
            'customerlist button[action=lookContactBtn]': {
                click: this.lookContactBtn
            },
//            'customerlist button[action=customer-transfer]': {
//                click: this.customerTransfer
//            },
            'customerlist toolbar button[action=addCustomer]': {
                click: this.addCustomer
            },
            'customerlist toolbar button[action=editCustomer]': {
                click: this.editCustomer
            },
            'customerlist toolbar button[action=deleteCustomer]': {
                click: this.deleteCustomer
            },
            'customerlist toolbar button[action=receiveCustomerCommon]': {
                click: this.receiveCustomerCommon
            },
            'customerlist toolbar button[action=addCustomerCommon]': {
                click: this.addCustomerCommon
            },
            'customerlist toolbar button[action=editCustomerCommon]': {
                click: this.editCustomerCommon
            },
            'customerlist toolbar button[action=deleteCustomerCommon]': {
                click: this.deleteCustomer
            },
            'customerlist combobox': {
                select: utils.comboChangeSelect
            },
            'customerupdate button[action=update]': {
                click: this.saveOrUpdateCustomer
            },
            'customerupdate textfield': {
                blur: utils.trimSpace
            },
            'customerlist textfield': {
                blur: utils.trimSpace
            },
            'customerupdate button[action=doresetCustomer]': {
                click: utils.resetRecord
            },
            'customerupdate button[action=closeCustomer]': {
                click: utils.winClose
            }
        });
    },
    viewInit: function(treeId, isGonghai) {
        var customerinfo = Ext.getCmp('customerinfo');
        if (typeof (customerinfo) == 'undefined') {
            customerinfo = Ext.widget('customerinfo');
            Ext.getCmp('centercard').insert(1, customerinfo);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('customerinfo');
        this.isGonghai = isGonghai;
        var customerlist = Ext.getCmp('customerlist');
        var store = customerlist.getStore();
        store.removeAll();
        store.on('beforeload', function(store, options) {
            var new_params = {
                    gonghai: isGonghai
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        if (isGonghai) {
            customerlist.setTitle("公海");
        } else {
            customerlist.setTitle("客户基本信息列表");
        }
        utils.loadPageOne(store);
        this.loadUser(customerlist.userStore);
        this.authorizationControl(treeId, customerlist, isGonghai)
        var toolbar = customerlist.down('toolbar');
        var panel = customerlist.down('form');
        if (panel.isHidden()) {
            toolbar.down('#searchText').reset();
        } else {
            toolbar.down('#queryBtn').setDisabled(false);
            toolbar.down('#searchText').setDisabled(false);
            panel.getForm().reset();
            panel.hide();
            var button = toolbar.down('#superBtn');
            button.setText('高级检索');
            button.up('gridpanel').update();
        }
        this.loadComboboxStore(customerlist);
        return customerinfo;
    },
    authorizationControl: function(treeId, list, isGonghai) {
        function setVal(id, index) {
            list.down('#' + id).hide();
        }
        var actionIds = isGonghai ? ['11102','11103','11104','11105','11106','11107'] : ['11201','11202','11203','11204','11205'];
        Ext.each(actionIds, setVal);
        utils.authorizationControl(treeId, list);
    },
    loadComboboxStore: function(list) {
        list.customerTypeStore.load({
            params: {
                code: '00010002'
            }
        });
        list.scaleStore.load({
            params: {
                code: '00010009'
            }
        });
        list.valueEvaluateStore.load({
            params: {
                code: '00010006'
            }
        });
        list.customerStatementStore.load({
            params: {
                code: '00010001'
            }
        });
        list.businessUnitStore.load({
            params: {
                code: '00010004'
            }
        });
        list.industryStore.load({
            params: {
                code: '00010008'
            }
        });
        list.feeStore.load({
            params: {
                code: '00010003'
            }
        });
    },
    changeBtnType: function(sm, selections) {
        if (this.isGonghai) {
            Ext.getCmp('customerCommonDelBtn').setDisabled(selections.length == 0);
            Ext.getCmp('receiveCustomerCommon').setDisabled(selections.length != 1);
            Ext.getCmp('customerCommonEditBtn').setDisabled(selections.length != 1);
        } else {
            Ext.getCmp('customerDelBtn').setDisabled(selections.length == 0);
            Ext.getCmp('customerEditBtn').setDisabled(selections.length != 1);
            Ext.getCmp('customerLookResBtn').setDisabled(selections.length != 1);
            Ext.getCmp('lookContactBtn').setDisabled(selections.length != 1);
        }
//        if (selections.length != 1) {
//            Ext.getCmp('customerdatail').hide();
//        } else {
//            Ext.getCmp('customerdatail').show();
//        }
    },
    changeBtnTypeCoopResume: function(sm, selections) {
        Ext.getCmp('coopResumeDelBtn').setDisabled(selections.length == 0);
        Ext.getCmp('coopResumeEditBtn').setDisabled(selections.length != 1);
    },
    openOrCloseSuperQuery: function(button) {
        var grid = button.up('grid');
        var form = grid.down('form');
        if (form.isHidden()) {
            if (grid.searchScaleStore.getCount() == 0) {
                utils.searchStoreInset(grid.searchScaleStore, grid.scaleStore, form.down('#scale'));
            }
            if (grid.searchIndustryStore.getCount() == 0) {
                utils.searchStoreInset(grid.searchIndustryStore, grid.industryStore, form.down('#industry'));
            }
            if (grid.searchFeeStore.getCount() == 0) {
                utils.searchStoreInset(grid.searchFeeStore, grid.feeStore, form.down('#fee'));
            }
        }
        utils.openOrCloseSuperQueryBtn(button);
    },
    customerQuery: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid() || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var jsonString = "";
            if (!button.up('grid').down('form').isHidden()) {
                searchFlag = 2;
                var form = button.up('form');
                var values = form.getValues();
                var scaleArray = new Array();
                scaleArray.push(values.scale);
                values.scale = scaleArray;
                var industryArray = new Array();
                industryArray.push(values.industry);
                values.industry = industryArray;
                var feeArray = new Array();
                feeArray.push(values.fee);
                values.fee = feeArray;
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
    customerLookResume: function(button) {
        var record = button.up('toolbar').up('grid').getView().getSelectionModel().getSelection()[0];
        initController('customerManagement.customerProfiles.CustomerResume').viewInit(record);
    },
    lookContactBtn: function(button) {
        var record = button.up('toolbar').up('grid').getView().getSelectionModel().getSelection()[0];
        initController('customerManagement.customerProfiles.Customer2').viewInit(record);
    },
    changeCusDetail: function(grid, record) {
        Ext.getCmp('customerdatail').loadRecord(record);
    },
    loadUser: function(userStore) {
        var me = this;
        userStore.load({
            params: {
                userID: USER_ID
            }
        });
    },

    customerTransfer: function(button) {
        initController('customerManagement.customerProfiles.CustomerTransfer').viewInit('show');
    },
    deleteCustomer: function(button) {
        var grid = button.up('grid');
        utils.delRecordsCheck(grid, 'deleteCustomer.action', 'customerID');
    },
    addCustomer: function() {
        var view = Ext.widget('customerupdate');
        view.setTitle('添加客户信息');
        view.down('#cusReset').setText('清空');
    },
    addCustomerCommon: function() {
        var view = Ext.widget('customerupdate');
        view.down('#holder').setDisabled(true);
        view.down('#holder').allowBlank = true;
        view.setTitle('添加客户信息');
        view.down('#cusReset').setText('清空');
    },
    receiveCustomerCommon: function(button) {
        var selection = button.up("panel").getView().getSelectionModel().getSelection()[0];
        if (selection === undefined) {
            return;
        }
        var customerID = selection.get("customerID");
        Ext.Ajax.request({
            url: "receiveCustomerCommon.action",
            params: {
                customerID: customerID,
                userID: USER_ID
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var responseText = Ext.decode(response.responseText) || '';
                if (responseText.message) {
                    messageBox.alert('提示', 'responseText.message');
                } else {
                    Ext.crm.msg("领取成功!", "");
                    Ext.getCmp('customerlist').getStore().loadPage(1);
                }
            },
            failure: function(response) {
                messageBox.alert('提示', '领取客户失败，请联系管理员！');
            }
        });
    },
    toEditCustomer: function(grid, record) {
        if (!Ext.getCmp('customerEditBtn').isHidden()) {
            var view = Ext.widget('customerupdate');
            view.setTitle('编辑客户信息');
            view.down('#cusReset').setText('重置');
            view.down('form').loadRecord(record);
        } else if (!Ext.getCmp('customerCommonEditBtn').isHidden()) {
            var view = Ext.widget('customerupdate');
            view.setTitle('编辑客户信息');
            view.down('#holder').setDisabled(true);
            view.down('#holder').setReadOnly(true);
            view.down('#holder').allowBlank = true;
            view.down('#cusReset').setText('重置');
            view.down('form').loadRecord(record);
            view.down('#holder').setValue('');
        }
    },
    editCustomer: function(button) {
        var selection = button.up("panel").getView().getSelectionModel().getSelection()[0];
        var view = Ext.widget('customerupdate');
        view.setTitle('编辑客户信息');
        view.down('#cusReset').setText('重置');
        view.down('form').loadRecord(selection);
    },
    editCustomerCommon: function(button) {
        var selection = button.up("panel").getView().getSelectionModel().getSelection()[0];
        var view = Ext.widget('customerupdate');
        view.setTitle('编辑客户信息');
        view.down('#holder').setDisabled(true);
        view.down('#holder').setReadOnly(true);
        view.down('#holder').allowBlank = true;
        view.down('#cusReset').setText('重置');
        view.down('form').loadRecord(selection);
        view.down('#holder').setValue('');
    },
    saveOrUpdateCustomer: function(button) {
        utils.updateRecord(button, 'updateCustomer.action', 'customerlist', true);
    }
});