Ext.define('CRM.controller.customerManagement.customerProfiles.CustomerTransfer', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.CustomerTransfer' ],
    stores: [ 'customerManagement.customerProfiles.Department',
            'customerManagement.customerProfiles.ProjectTeam',
            'customerManagement.customerProfiles.User' ],
    init: function() {
        this.control({
            'customertransfer button[action=save]': {
                click: this.save
            },
//            'customertransfer button[action=saveAndClose]': {
//                click: this.saveAndClose
//            },
//            'customertransfer button[action=close]': {
//                click: utils.winClose
//            },
            'customertransfer button[action=search]': {
                click: this.search
            },
            'customertransfer commobox': {
                click: this.insertEmpty
            },
            'customertransfer toolbar combobox[itemId=queryMode]': {
                select: this.onSelectQueryModeToolCombo
            },
            'customertransfer form combobox[itemId=queryMode]': {
                select: this.onSelectQueryModeFormCombo
            },
            'customertransfer toolbar combobox[itemId=department]': {
                select: this.changeToolDepartment
            },
            'customertransfer toolbar combobox[itemId=projectTeam]': {
                select: this.changeToolProjectTeam
            },
            'customertransfer form combobox[itemId=department]': {
                select: this.changeFormDepartment
            },
            'customertransfer form combobox[itemId=projectTeam]': {
                select: this.changeFormProjectTeam
            }
        });
    },
    viewInit: function(flag) {
        var transferWin = Ext.getCmp('customertransfer');
        if (typeof (transferWin) == 'undefined') {
            transferWin = Ext.widget('customertransfer');
            Ext.getCmp('centercard').insert(1, transferWin);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('customertransfer');
//        var transferWin = Ext.widget('customertransfer');
//        if (flag && flag === 'show') {
            transferWin.store.load();
            transferWin.noHolderStore.load({
                params: {
                    searchFlag: 0
                }
            });
            this.loadCallbackToolD(transferWin.departmentStore);
            this.loadCallbackFormD(transferWin.departmentFormStore);
            this.loadCallbackP(transferWin.projectTeamStore);
            this.loadCallbackP(transferWin.projectTeamFormStore);
//            transferWin.show();
//        }
        return transferWin;
    },

    onSelectQueryModeToolCombo: function(combo) {
        var value = combo.getValue();
        if (value === '000100050001') {
            combo.up('toolbar').down('combo[itemId=department]').show();
            combo.up('toolbar').down('combo[itemId=department]').select(-1);
            this.changeToolDepartment(combo.up('toolbar').down('combo[itemId=department]'));
            combo.up('toolbar').down('combo[itemId=projectTeam]').hide();
            combo.up('toolbar').down('combo[itemId=user]').show();
            combo.up('toolbar').down('combo[itemId=user]').select(0);
        } else if (value === '000100050002') {
            combo.up('toolbar').down('combo[itemId=department]').hide();
            var projectTeamCombo = combo.up('toolbar').down('combo[itemId=projectTeam]');
            projectTeamCombo.show();
            projectTeamCombo.setValue(projectTeamCombo.getStore().getAt(0).get('projectTeamID'));
            this.changeToolProjectTeam(projectTeamCombo);
            combo.up('toolbar').down('combo[itemId=user]').show();
            combo.up('toolbar').down('combo[itemId=user]').select(0);
        } else if (value === '000100050003') {
            combo.up('toolbar').down('combo[itemId=department]').hide();
            combo.up('toolbar').down('combo[itemId=projectTeam]').hide();
            combo.up('toolbar').down('combo[itemId=user]').hide();
        } else if (value === '00') {
            combo.up('toolbar').down('combo[itemId=department]').hide();
            combo.up('toolbar').down('combo[itemId=projectTeam]').hide();
            combo.up('toolbar').down('combo[itemId=user]').hide();
        } else {
            return;
        }
    },

    onSelectQueryModeFormCombo: function(combo) {
        var value = combo.getValue();
        if (value === '000100050001') {
            combo.up('form').down('combo[itemId=department]').show();
            combo.up('form').down('combo[itemId=department]').select(-1);
            this.changeFormDepartment(combo.up('form').down('combo[itemId=department]'));
            combo.up('form').down('combo[itemId=projectTeam]').hide();
            combo.up('form').down('combo[itemId=user]').show();
        } else if (value === '000100050002') {
            combo.up('form').down('combo[itemId=department]').hide();
            var projectTeamCombo = combo.up('form').down('combo[itemId=projectTeam]');
            projectTeamCombo.show();
            projectTeamCombo.select(projectTeamCombo.getStore().getAt(0).get('projectTeamID'));
            this.changeFormProjectTeam(projectTeamCombo);
            combo.up('form').down('combo[itemId=user]').show();
        } else {
            return;
        }
    },

    loadCallbackToolD: function(store, component) {
        store.load({
            params: {
                departmentID: DEPARTMENT_ID,
                groupID: GROUP_ID,
                userID: USER_ID
            },
            callback: function(records, operation, success) {
                store.insert(0, {
                    departmentID: -1,
                    departmentName: '- 不限 -'
                });
                var toolCombo = Ext.getCmp('customertransfer').down('toolbar').down(
                        'combo[itemId=department]');
                if (toolCombo.getValue() !== -1) {
                    toolCombo.select(-1);
                }
            }
        });
    },

    loadCallbackFormD: function(store, component) {
        var me = this;
        store.load({
            params: {
                departmentID: DEPARTMENT_ID,
                groupID: GROUP_ID,
                userID: USER_ID
            },
            callback: function(records, operation, success) {
                store.insert(0, {
                    departmentID: -1,
                    departmentName: '- 不限 -'
                });
                var formDepartmentCombo = Ext.getCmp('customertransfer').down('form').down(
                        'combo[itemId=department]');
                if (formDepartmentCombo.getValue() !== -1) {
                    formDepartmentCombo.select(-1);
                }
                var formUserCombo = formDepartmentCombo.up('form').down('combo[itemId=user]');
                me.loadCallbackFormDU(formUserCombo);
            }
        });
    },

    loadCallbackP: function(store, component) {
        store.load({
            params: {
                userID: USER_ID
            },
            callback: function(records, operation, success) {
                if (store.getCount() === 0) {
                    store.insert(0, {
                        projectTeamID: 0,
                        projectTeamName: '- 无 -'
                    });
                }
            }
        });
    },

    loadCallbackFormDU: function(combo) {
        var store = combo.getStore();
        var formDepartmentCombo = combo.up('form').down('combo[itemId=department]');
        store.load({
            params: {
                queryDepartmentID: formDepartmentCombo.value,
                queryProjectTeamID: 0,
                userID: USER_ID
            }
        });
    },

    save: function(button) {
        this.transferCustomer(button, 'save');
    },
    saveAndClose: function(button) {
        this.transferCustomer(button, 'close');
    },
    transferCustomer: function(button, flag) {
        var form = button.up('panel');
        var grid = form.up('panel').down('grid');
        var userID = form.down('#user').getValue() || '';
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get('customerID');
        }
        if (checkRecord.length != 0 && userID != '') {
            Ext.Ajax.request({
                url: 'saveTransfer.action',
                params: {
                    jsonString: checkRecordIDs,
                    userID: userID
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    Ext.crm.msg('转移成功！', '');
//                    if (flag == 'close') {
//                        win.close();
//                    } else {
                        store.loadPage(1);
//                    }
                },
                failure: function(response) {
                    messageBox.alert('提示', '转移失败，请联系管理员！');
                }
            });
        } else if (checkRecord.length === 0) {
            messageBox.alert('提示', '请选择客户！');
        } else {
            messageBox.alert('提示', '请指定转移对象！');
        }
    },
    search: function(button) {
//        var win = button.up('panel');
        var store = button.up('grid').getStore();
        var toolbar = button.up('toolbar');
        var departmentID = toolbar.down('#department').getValue();
        var projectTeamID = toolbar.down('#projectTeam').getValue();
        var userID = toolbar.down('#user').getValue();
        var mode = toolbar.down('#queryMode').getValue();
        var searchFlag = 1;
        if (departmentID == -1) {
            departmentID = 0;
        }
        if (mode == '000100050003') {
            searchFlag = 2;
            departmentID = 0;
            projectTeamID = 0;
        }
        if (mode == '000100050002') {
            departmentID = 0;
        }
        if (mode == '000100050001') {
            projectTeamID = 0;
        }
        if (mode === '00') {
            searchFlag = 0;
        }
        store.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: searchFlag,
                departmentID: departmentID,
                projectTeamID: projectTeamID,
                userID: userID
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
    },
    changeToolDepartment: function(combo) {
        var user = combo.up('toolbar').down('#user');
        this.changeDepartment(combo, user);
    },
    changeToolProjectTeam: function(combo) {
        var user = combo.up('toolbar').down('#user');
        this.changeProjectTeam(combo, user);
    },
    changeFormDepartment: function(combo) {
        var user = combo.up('form').down('#user');
        this.changeDepartmentD(combo, user);
    },
    changeFormProjectTeam: function(combo) {
        var user = combo.up('form').down('#user');
        this.changeProjectTeamD(combo, user);
    },
    changeDepartment: function(combo, user) {
        user.clearValue();
        user.store.load({
            params: {
                queryDepartmentID: combo.value,
                queryProjectTeamID: 0,
                userID: USER_ID
            },
            callback: function(records, operation, success) {
                user.store.insert(0, {
                    userID: 0,
                    realName: '- 不限 -'
                });
                user.setValue('00');
            }
        });

        Ext.override(Ext.LoadMask, {
            onHide: function() {
                this.callParent();
            }
        });
    },

    changeDepartmentD: function(combo, user) {
        user.clearValue();
        user.store.load({
            params: {
                queryDepartmentID: combo.value,
                queryProjectTeamID: 0,
                userID: USER_ID
            }
        });

        Ext.override(Ext.LoadMask, {
            onHide: function() {
                this.callParent();
            }
        });
    },

    changeProjectTeam: function(combo, user) {
        user.clearValue();
        if (combo.value === 0) {
            user.store.removeAll();
            messageBox.alert('提示', '当前用户没有负责的团队！');
            return;
        }
        user.store.load({
            params: {
                queryDepartmentID: 0,
                queryProjectTeamID: combo.value,
                userID: USER_ID
            },
            callback: function(records, operation, success) {
                user.store.insert(0, {
                    userID: 0,
                    realName: '- 不限 -'
                });
                user.setValue(0);
            }
        });
        Ext.override(Ext.LoadMask, {
            onHide: function() {
                this.callParent();
            }
        });
    },
    changeProjectTeamD: function(combo, user) {
        user.clearValue();
        if (combo.value === 0) {
            user.store.removeAll();
            messageBox.alert('提示', '当前用户没有负责的团队！');
            return;
        }
        user.store.load({
            params: {
                queryDepartmentID: 0,
                queryProjectTeamID: combo.value,
                userID: USER_ID
            }
        });
        Ext.override(Ext.LoadMask, {
            onHide: function() {
                this.callParent();
            }
        });
    }
});