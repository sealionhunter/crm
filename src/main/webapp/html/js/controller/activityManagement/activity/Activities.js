Ext.define('CRM.controller.activityManagement.activity.Activities', {
    extend: 'Ext.app.Controller',
    views: [ 'activityManagement.activity.ActivityInfo', 'activityManagement.activity.ActivityList', 'activityManagement.activity.ActivityDetail',
            'activityManagement.activity.ActivityAdd' ],
    stores: [ 'activityManagement.activity.ActivityStore', 'customerManagement.customerProfiles.CustomerName' ],
    models: [],
    init: function() {
        this.control({
            'activitylist': {
                select: this.changeActivityDetail,
                itemdblclick: this.showEditWindow,
                selectionchange: this.changeBtn
            },
            'activitylist combobox': {
                select: this.comboChangeSelect
            },
            'activitylist > toolbar > button[id=addActivityWindow]': {
                click: this.showAddWindow
            },
            'activitylist > toolbar > button[id=editActivityWindow]': {
                click: this.showEditWindow
            },
            'activitylist > toolbar > button[id=deleteActivityWindow]': {
                click: this.delActivity
            },
            'activitylist button[action=queryBtn]': {
                click: this.activityQuery
            },
            'activitylist toolbar button[action=superQueryBtn]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'activityadd button[action=save]': {
                click: this.updateActivity
            },
            'activityadd button[action=reset]': {
                click: utils.resetRecord
            },
            'activityadd button[action=cancel]': {
                click: this.cancelActivity
            },
            'activitylist textfield': {
                blur: utils.trimSpaceSearch
            },
            'activityadd textfield': {
                blur: utils.trimSpace
            }
        });
    },
    viewInit: function(treeId) {
        var activityinfo = Ext.getCmp('activityinfo');
        if (typeof (activityinfo) == 'undefined') {
            activityinfo = Ext.widget('activityinfo');
        }
        Ext.getCmp('centercard').insert(1, activityinfo);
        Ext.getCmp('centercard').getLayout().setActiveItem('activityinfo');
        var activityList = Ext.getCmp('card-activitylist');
        utils.authorizationControl(treeId, activityList);
        if (activityList.activityTypeCombo.getCount() == 0) {
            activityList.activityTypeCombo.load({
                params: {
                    code: '00020006'
                }
            });
        }
        if (activityList.activityEmphasisCombo.getCount() == 0) {
            activityList.activityEmphasisCombo.load({
                params: {
                    code: '00020004'
                }
            });
        }
        if (activityList.activityRangeCombo.getCount() == 0) {
            activityList.activityRangeCombo.load({
                params: {
                    code: '00020002'
                }
            });
        }
        if (activityList.activityPeriodCombo.getCount() == 0) {
            activityList.activityPeriodCombo.load({
                params: {
                    code: '00020003'
                }
            });
        }
        if (activityList.activityStateCombo.getCount() == 0) {
            activityList.activityStateCombo.load({
                params: {
                    code: '00020005'
                }
            });
        }
        utils.hideSuperQuery(activityList);
        return activityinfo;
    },
    changeActivityDetail: function(view, record, el, index, e) {
        Ext.getCmp('activitydetail').getForm().loadRecord(record);
    },
    showAddWindow: function(button) {
        Ext.widget("activityadd");
    },
    changeBtn: function(sm, selections) {
        if (selections.length != 1) {
            Ext.getCmp('activitydetail').hide();
        } else {
            Ext.getCmp('activitydetail').show();
        }
        Ext.getCmp('deleteActivityWindow').setDisabled(selections.length == 0);
        Ext.getCmp('editActivityWindow').setDisabled(selections.length !== 1);
    },
    comboChangeSelect: function(combo, records, eOpts) {
        utils.comboChangeSelect(combo, records);
    },
    showEditWindow: function(button) {
        var grid = button.up('grid');
        if (!grid.down('#31102').hidden) {
            var checkRecord = grid.getSelectionModel().getSelection();
            var win = Ext.widget('activityadd');
            win.setTitle('编辑营销活动');
            win.down('#reset').setText('重置');
            var form = win.down('form');
            form.loadRecord(checkRecord[0]);
        }
    },
    delActivity: function(button) {
        var grid = button.up('grid');
        utils.delRecords(grid, 'deleteActivity.action', 'idString');
    },
    updateActivity: function(button) {
        var win = button.up('window');
        var form = win.down('form');
        if (!form.down('#activityPlan').getValue()) {
            form.down('#activityPlan').setValue('');
        }
        if (!form.down('#activityDetail').getValue()) {
            form.down('#activityDetail').setValue('');
        }
        utils.updateRecord(button, 'updateActivity.action', "card-activitylist", "营销活动");
    },
    cancelActivity: function(button) {
        button.up('window').close();
    },
    openOrCloseSuperQueryBtn: function(button) {
        var gridPanel = button.up('gridpanel');
        var toolForm = gridPanel.down('form');
        if (toolForm.isHidden()) {
            if (gridPanel.typeSearchCombo.getCount() == 0) {
                utils.searchStoreInset(gridPanel.typeSearchCombo, gridPanel.activityTypeCombo, Ext.getCmp('activityTypeSearch'));
            }
            if (gridPanel.rangeSearchCombo.getCount() == 0) {
                utils.searchStoreInset(gridPanel.rangeSearchCombo, gridPanel.activityRangeCombo, Ext.getCmp('activityRangeSearch'));
            }
            if (gridPanel.stateSearchCombo.getCount() == 0) {
                utils.searchStoreInset(gridPanel.stateSearchCombo, gridPanel.activityStateCombo, Ext.getCmp('activityStateSearch'));
            }
        }
        utils.openOrCloseSuperQueryBtn(button);
    },
    activityQuery: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var jsonString = "";
            if (!button.up('grid').down('form').isHidden()) {
                searchFlag = 2;
                var form = button.up('form');
                var values = form.getValues();
                var activityType = values.activityType;
                var activityTypes = new Array();
                activityTypes.push(activityType);
                values.activityType = activityTypes;
                var activityState = values.activityState;
                var activityStates = new Array();
                activityStates.push(activityState);
                values.activityState = activityStates;
                var activityRange = values.activityRange;
                var activityRanges = new Array();
                activityRanges.push(activityRange);
                values.activityRange = activityRanges;
                jsonString = Ext.encode(values);
            } else {
                var searchValue = button.up('toolbar').down('#searchText').getValue().toString();
                jsonString = '{searchText : ' + Ext.encode(searchValue) + '}';
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
    }
});
