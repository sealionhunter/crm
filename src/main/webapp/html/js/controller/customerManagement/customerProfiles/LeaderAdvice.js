Ext.define('CRM.controller.customerManagement.customerProfiles.LeaderAdvice', {
    extend: 'Ext.app.Controller',
    views: ['customerManagement.customerProfiles.LeaderAdviceList',
            'customerManagement.customerProfiles.LeaderAdviceCard',
            'customerManagement.customerProfiles.LeaderAdviceUpdate'],
    stores:['customerManagement.customerProfiles.LeaderAdvice'],
    models: ['customerManagement.customerProfiles.LeaderAdvice'],
    init: function() {
        this.control({
            'leaderadvicelist': {
                itemdblclick: this.showEditWin,
                selectionchange: this.changeBtn
            },
            'leaderadvicelist button[action=showAddWin]': {
                click: this.showAddWin
            },
            'leaderadvicelist button[action=showEditWin]': {
                click: this.showEditWin
            },
            'leaderadvicelist button[action=showDelWin]': {
                click: this.showDelWin
            },
            'leaderadvicelist button[action=queryBtn]': {
                click: this.leaderAdviceQuery
            },
            'leaderadviceupdate button[action=addOrUpdateLeaderAdvice]': {
                click: this.addOrUpdateLeaderAdvice
            },
            'leaderadviceupdate button[action=resetLeaderAdvice]': {
                click: this.resetLeaderAdvice
            },
            'leaderadviceupdate button[action=closeWin]': {
                click: utils.winClose
            },
            'leaderadviceupdate textarea': {
                blur: utils.trimSpace
            }
        });
    },
    viewInit: function(treeId, panel, record) {
        var leaderAdviceCard = Ext.getCmp('leaderadvicecard');
        if (typeof (leaderAdviceCard) === 'undefined') {
            leaderAdviceCard = Ext.widget('leaderadvicecard');
        }
        if (typeof panel !== 'undefined') {
            panel.insert(0, leaderAdviceCard);
        }

        var leaderAdviceList = Ext.getCmp('leaderadvicelist');
        utils.authorizationControl(treeId, leaderAdviceList);
        // load page
        leaderAdviceList.userStore.load({
            params: {
                userID: USER_ID
            }
        });
        if (typeof record !== 'undefined') {
            this.record = record;
            var store  = leaderAdviceList.getStore();
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
        return leaderAdviceCard;
    },
    showAddWin: function() {
        var me =this;
        var view = Ext.widget('leaderadviceupdate');
        view.setTitle('添加领导建议');
        view.down('#customerID').setValue(me.record.get('customerID'));
        view.down('#customerName').setValue(me.record.get('customerName'));
        view.down('#userID').setValue(USER_ID);
        view.down('#userName').setValue(REAL_NAME);
        view.down('[action=resetLeaderAdvice]').setText('清空');
    },
    showEditWin: function(button) {
        var grid = button.up('grid');
        var checkRecord = grid.getSelectionModel().getSelection();
        var view = Ext.widget('leaderadviceupdate');
        view.setTitle('编辑领导建议信息');
        view.down('[action=resetLeaderAdvice]').setText('重置');
        // load select record
        view.down('form').loadRecord(checkRecord[0]);
    },
    showDelWin: function(button) {
        var grid = button.up('grid');
        utils.delRecordsCheck(grid, 'deleteLeaderAdvice.action', 'adviceID');
    },
    addOrUpdateLeaderAdvice: function(button) {
        utils.updateRecord(button, 'addOrUpdateLeaderAdvice.action', 'leaderadvicelist', true);
    },
    resetLeaderAdvice: function(button) {
        var form = button.up('window').down('form');
        var cusNameText = form.down('[name=customerName]');
        var cusIDText = form.down('[name=customerID]');
        var cusName = cusNameText.getValue();
        var cusID = cusIDText.getValue();

        var record = form.getRecord() || '';
        if (record == '') {
            setTimeout(function fn() {
                form.getForm().reset();
                cusNameText.setValue(cusName);
                cusIDText.setValue(cusID);
                form.down('[name=userID]').setValue(USER_ID);
                form.down('[name=userName]').setValue(REAL_NAME);
            }, 100);
        } else {
            form.loadRecord(record);
        }
    },
    changeBtn: function(sm, selections){
        var listGrid = Ext.getCmp('leaderadvicelist');
        listGrid.down('[action=showDelWin]').setDisabled(selections.length == 0);
        listGrid.down('[action=showEditWin]').setDisabled(selections.length != 1);
    },
    leaderAdviceQuery: function(button) {
//        alert("leaderAdviceQuery");
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