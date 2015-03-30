Ext.define('CRM.controller.systemManagement.organizationManagement.departmentManagement.Department', {
    extend: 'Ext.app.Controller',
    models: [ 'systemManagement.organizationManagement.departmentManagement.FatherDepartment',
            'systemManagement.organizationManagement.departmentManagement.DepartmentInfo' ],
    stores: [ 'systemManagement.organizationManagement.departmentManagement.FatherDepartment',
            'systemManagement.organizationManagement.departmentManagement.DepartmentManager',
            'systemManagement.organizationManagement.departmentManagement.DepartmentInfo' ],
    views: [ 'systemManagement.organizationManagement.departmentManagement.DepartmentInfoBody',
            'systemManagement.organizationManagement.departmentManagement.DepartmentList',
            'systemManagement.organizationManagement.departmentManagement.DepartmentUpdate',
            'systemManagement.organizationManagement.departmentManagement.DepartmentDetail' ],
    init: function() {
        this.control({
            'DepartmentList': {
                selectionchange: this.changeBtn,
                itemdblclick: this.departmentEdit,
                select: this.showDetail
            },
            'DepartmentList combobox': {
                select: this.comboChangeSelect
            },
            'DepartmentList button[action=departmentAdd]': {
                click: this.departmentAdd
            },
            'DepartmentList button[action=departmentEdit]': {
                click: this.departmentEdit
            },
            'DepartmentList button[action=departmentDelete]': {
                click: this.departmentDelete
            },
            'DepartmentList button[action=queryBtn]': {
                click: this.queryDepartment
            },
            'DepartmentList button[action=openOrCloseSuperQueryBtn]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'DepartmentUpdate combobox[name=fatherDepartmentID]': {
                select: this.loadManagerCombobox
            },
            'DepartmentUpdate combobox[name=departmentManager]': {
                focus: this.selectFatherDepartment
            },
            'DepartmentUpdate button[action=save]': {
                click: this.saveUpdate
            },
            'DepartmentUpdate textfield': {
                blur: utils.trimSpace
            },
            'DepartmentList textfield': {
                blur: utils.trimSpace
            },
            'DepartmentUpdate button[action=reset]': {
                click: utils.resetRecord
            },
            'DepartmentUpdate button[action=cancel]': {
                click: utils.winClose
            }
        });
    },

    editRecord: null,
    viewInit: function(flag) {
        var jsonString = '{"groupID":"' + GROUP_ID + '","userID":"' + USER_ID + '"}';
        var departmentinfo = Ext.getCmp('DepartmentInfoBody');
        if (typeof (departmentinfo) == 'undefined') {
            departmentinfo = Ext.widget('DepartmentInfoBody');
        }
        Ext.getCmp('centercard').insert(1, departmentinfo);
        Ext.getCmp('centercard').getLayout().setActiveItem('DepartmentInfoBody');
        var list = Ext.getCmp('DepartmentList');
        this.queryFatherDepartmentStoreLoad(list.departmentStore);
        var listStore = list.getStore();
        var toolbar = Ext.getCmp('deparmentToolBar');
        var panel = Ext.getCmp('queryDepartment');
        if (panel.isHidden()) {
            toolbar.down('#searchText').reset();
        } else {
            toolbar.down('#queryButton').setDisabled(false);
            toolbar.down('#searchText').setDisabled(false);
            panel.getForm().reset();
            panel.hide();
            var button = toolbar.down('#superBtn');
            button.setText('高级检索');
            button.up('gridpanel').update();
        }
        listStore.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                departmentID: DEPARTMENT_ID,
                jsonString: jsonString
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        listStore.loadPage(1);
        return departmentinfo;
    },

    /**
     * 高级检索中上级部门下拉框加入不限
     */
    queryFatherDepartmentStoreLoad: function(store) {
        store.load({
            callback: function(records, operation, success) {
                store.insert(0, {
                    departmentID: 0,
                    departmentName: '- 不限 -'
                });
            }
        });
        Ext.override(Ext.LoadMask, {
            onHide: function() {
                this.callParent();
            }
        });
    },

    fatherDepartmentStoreLoad: function(record, store) {
        store.on('beforeload', function(store, options) {
            var new_params = {
                departmentID: record.get('departmentID'),
                userID: USER_ID,
                groupID: GROUP_ID,
                loginDepartmentID: DEPARTMENT_ID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.load();
    },

    /**
     * 加载部门经理选择框的数据（该部门的上级部门的直属非任职用户）
     */
    userStoreLoad: function(departmentID, store) {
        var me = this;
        store.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                departmentID: departmentID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        if (me.editRecord != null) {
            store.load({
                callback: function(records, operation, success) {
                    store.insert(0, {
                        realName: me.editRecord.get('managerName'),
                        userID: me.editRecord.get('departmentManager')
                    });
                }
            });
        } else {
            store.load();
        }
        Ext.override(Ext.LoadMask, {
            onHide: function() {
                this.callParent();
            }
        });
    },

    loadManagerCombobox: function(combo, record, index) {
        var departmentManager = combo.up('form').down('combobox[name=departmentManager]');
        departmentManager.clearValue();
        var store = departmentManager.getStore();
        this.userStoreLoad(combo.value, store);
        departmentManager.setReadOnly(false);
    },

    comboChangeSelect: function(combo, records, eOpts) {
        if (records[0].get('departmentName') == '- 不限 -' && records.length > 1) {
            combo.setValue(records[1].get('departmentID'));
        } else {
            Ext.Array.each(records, function(record) {
                if (record.get('departmentName') == '- 不限 -') {
                    combo.setValue(0);
                    return false;
                }
            });
        }
    },

    selectFatherDepartment: function(combo) {
        var value = Ext.getCmp('fatherDepartmentIDCombo1').getValue();
        var store = combo.getStore();
        if (value == null) {
            messageBox.alert('提示', '请先选择上级部门！');
            combo.setReadOnly(true);
        } else if (store.getCount() == 0) {
            messageBox.alert('提示', '上级部门无可选用户！');
            combo.setReadOnly(true);
        }
    },

    departmentAdd: function() {
        var me = this;
        var view = Ext.widget('DepartmentUpdate');
        view.setTitle('添加部门信息');
        view.down('#depReset').setText('清空');
        view.departmentStore.load({
            params: {
                userID: USER_ID,
                groupID: GROUP_ID,
                loginDepartmentID: DEPARTMENT_ID
            }
        });
        me.editRecord = null;
        this.userStoreLoad(0, view.userStore);
    },

    departmentEdit: function() {
        var me = this;
        var rows = Ext.getCmp('DepartmentList').getSelectionModel().getSelection();
        var record = rows[0];
        if (record.get('departmentID') == DEPARTMENT_ID) {
            messageBox.alert("提示", "不能编辑自己所在的部门！");
        } else {
            var view = Ext.widget('DepartmentUpdate');
            me.editRecord = record;
            view.setTitle('编辑部门信息');
            view.down('#depReset').setText('重置');
            this.fatherDepartmentStoreLoad(record, view.departmentStore);
            this.userStoreLoad(record.get('fatherDepartmentID'), view.userStore);
            setTimeout(function fn() {
                view.down('form').loadRecord(record);
                view.down('form').down('combobox[name=departmentManager]').setValue(record.get('departmentManager'));
            }, 300);
        }
    },

    departmentDelete: function(button) {
        var grid = button.up('grid');
        this.delRecords(grid, 'deleteDeparment.action', 'departmentID');
    },

    showDetail: function(grid, record) {
        Ext.getCmp('DepartmentDetail').loadRecord(record);
    },

    changeBtn: function(sm, selections) {
        if (selections.length != 1) {
            Ext.getCmp('DepartmentDetail').hide();
        } else {
            Ext.getCmp('DepartmentDetail').show();
        }
        Ext.getCmp('departmentDelete').setDisabled(selections.length == 0);
        Ext.getCmp('departmentEdit').setDisabled(selections.length != 1);
    },

    saveUpdate: function(button) {
        utils.updateRecord(button, 'updateDepartment.action', 'DepartmentList', '部门管理');
    },

    openOrCloseSuperQueryBtn: function(button) {
        utils.openOrCloseSuperQueryBtn(button);
        Ext.getCmp('fatherDepartmentIDCombo').setValue(0);
    },

    queryDepartment: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var jsonString = "";
            if (!button.up('grid').down('form').isHidden()) {
                searchFlag = 2;
                var form = button.up('form');
                var values = form.getValues();
                values['groupID'] = GROUP_ID;
                values['userID'] = USER_ID;
                jsonString = Ext.encode(values);
            } else {
                var searchValue = button.up('toolbar').down('#searchText').getValue().toString();
                jsonString = '{"searchText" : ' + Ext.encode(searchValue) + ',"groupID" : "' + GROUP_ID + '","userID" : "' + USER_ID + '"}';
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    departmentID: DEPARTMENT_ID,
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

    delRecords: function(grid, url, idString) {
        var store = grid.getStore();
        var flag = 0;
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get(idString);
            if (checkRecord[i].get('departmentID') == DEPARTMENT_ID) {
                flag = 1;
            }
        }
        if (flag == 1) {
            messageBox.alert("提示", "不能删除自己所在的部门！");
        } else if (checkRecord.length != 0) {
            box.confirm('提示', '确定删除所选信息？', function showResult(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: url,
                        params: {
                            jsonString: checkRecordIDs
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var currentPage = store.currentPage;
                            var pageSize = store.pageSize;
                            var total = store.totalCount - checkRecord.length;
                            if (total <= (currentPage - 1) * pageSize) {
                                currentPage = currentPage - 1;
                            }
                            if (total == 0) {
                                currentPage = 1;
                            }
                            var responseText = Ext.decode(response.responseText) || '';
                            var flag = responseText.flag;
                            if (flag) {
                                Ext.crm.msg('删除成功！', '');
                            } else {
                                messageBox.alert('提示', '所选部门中包含未处理的子部门，请先处理子部门！');
                            }
                            store.loadPage(currentPage);
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '删除失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    }
});