Ext.define('CRM.controller.systemManagement.userManagement.User', {
    extend: 'Ext.app.Controller',
    models: [ 'systemManagement.userManagement.UserInfo' ],// 声明该控制层要用到的model
    stores: [ 'systemManagement.userManagement.UserInfo' ],// 声明该控制层要用到的store
    views: [ 'systemManagement.userManagement.UserInfoBody', 'systemManagement.userManagement.UserList',
            'systemManagement.userManagement.UserUpdate', 'systemManagement.userManagement.UserDetail' ],// 声明该控制层要用到的view
    init: function() {
        this.control({
            'UserList': {
                selectionchange: this.changeBtn,
                itemdblclick: this.userEdit,
                select: this.showDetail
            },
            'UserList button[action=userAdd]': {
                click: this.userAdd
            },
            'UserList button[action=userEdit]': {
                click: this.userEdit
            },
            'UserList button[action=userDelete]': {
                click: this.userDelete
            },
            'UserUpdate button[action=save]': {
                click: this.saveUpdate
            },
            'UserUpdate button[action=reset]': {
                click: utils.resetRecord
            },
            'UserUpdate textfield': {
                blur: utils.trimSpace
            },
            'UserList textfield': {
                blur: utils.trimSpace
            },
            'UserUpdate button[action=cancel]': {
                click: utils.winClose
            },
            'UserList button[action=openOrCloseSuperQueryBtn]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'UserList button[action=queryBtn]': {
                click: this.queryUser
            }
        });
    },
    viewInit: function(flag) {
        var userinfo = Ext.getCmp('UserInfoBody');
        var jsonString = "";
        if (typeof (userinfo) == 'undefined') {
            userinfo = Ext.widget('UserInfoBody');
        }
        Ext.getCmp('centercard').insert(1, userinfo);
        Ext.getCmp('centercard').getLayout().setActiveItem('UserInfoBody');
        var list = Ext.getCmp('UserList');
        var toolbar = Ext.getCmp('userToolBar');
        var panel = Ext.getCmp('queryUser');
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
        var userStore = list.getStore();
        jsonString = '{"groupID":"' + GROUP_ID + '","userID" : "' + USER_ID + '","projectTeamID" : "' + PROJECT_TEAM_ID + '"}';
        userStore.on("beforeload", function(store, options) {
            var new_params = {
                searchFlag: 0,
                departmentID: DEPARTMENT_ID,
                jsonString: jsonString
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        userStore.loadPage(1);
        list.setTitle('用户列表');
        return userinfo;
    },
    loadCallbackD: function(store, component) {
        store.load({
            callback: function(records, operation, success) {
                store.insert(0, {
                    departmentID: 0,
                    departmentName: '- 不限 -'
                });
            }
        });
    },
    showDetail: function(grid, record) {
        Ext.getCmp('UserDetail').loadRecord(record);
    },
    changeBtn: function(sm, selections) {
        if (selections.length != 1) {
            Ext.getCmp('UserDetail').hide();
        } else {
            Ext.getCmp('UserDetail').show();
        }
        Ext.getCmp('userDelete').setDisabled(selections.length == 0);
        Ext.getCmp('userEdit').setDisabled(selections.length != 1);
    },
    storeLoad: function(store, paramNames) {
        store.load({
            params: {
                code: paramNames
            },
            callback: function(records, operation, success) {
                store.insert(0, {
                    value: '-无-',
                    code: '00'
                });
            }
        });
    },

    userGroupLoad: function(store, groupID) {
        if (groupID == GROUP_ID) {
            store.load({
                params: {
                    groupID: 0
                }
            });
        }
    },

    userAdd: function() {
        var userInfo = Ext.widget('UserUpdate');
        userInfo.departmentStore.load({
            params: {
                departmentID: DEPARTMENT_ID,
                groupID: GROUP_ID,
                userID: USER_ID
            }
        });
        userInfo.setTitle('添加用户');
        userInfo.groupStore.load({
            params: {
                groupID: GROUP_ID
            }
        });
        this.storeLoad(userInfo.educationStore, '00030002');
        this.storeLoad(userInfo.jobTitleStore, '00100002');

    },
    userEdit: function() {
        var row = Ext.getCmp('UserList').getSelectionModel().getSelection()[0];
        var userInfo = Ext.widget('UserUpdate');
        var departCombo = userInfo.down('combobox[name="departmentID"]');
        var groupCombo = userInfo.down('combobox[name="groupID"]');
        userInfo.departmentStore.load({
            params: {
                departmentID: DEPARTMENT_ID,
                groupID: GROUP_ID,
                userID: USER_ID
            },
            callback: function() {
                if (userInfo.departmentStore.getCount() === 0) {
                    userInfo.departmentStore.insert(0, {
                        departmentID: row.get('departmentID'),
                        departmentName: row.get('departmentIDB')
                    });
                }
                userInfo.down('form').loadRecord(row);
            }
        });
        userInfo.groupStore.load({
            params: {
                groupID: 0
            }
        });
        userInfo.setTitle('编辑用户');
        userInfo.down('button[action=reset]').setText('重置');
        this.storeLoad(userInfo.educationStore, '00030002');
        this.storeLoad(userInfo.jobTitleStore, '00100002');
        if (row.get('userID') == USER_ID) {
            departCombo.setReadOnly(true);
            groupCombo.setReadOnly(true);
        }
    },
    userDelete: function(button) {
        var grid = button.up('gridpanel');
        utils.delRecordsCheck(grid, 'deleteUser.action', 'userID');
    },
    saveUpdate: function(button) {
        var win = button.up('window');
        var form = win.down('form');
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            var values = form.getValues();
            var record = form.getRecord() || '';
            var store = Ext.getCmp('UserList').getStore();
            if ((GROUP_ID < 3) && (values.groupID > 3) && (values.departmentID == undefined || values.departmentID == 0)) {
                messageBox.alert("提示", "部门信息不能为空");

            } else if ((GROUP_ID < 3) && (values.groupID == 3) && (values.departmentID == undefined || values.departmentID == 0)) {
                messageBox.alert("提示", "部门信息不能为空");
            } else {
                if (GROUP_ID == 3) {
                    values['departmentID'] = DEPARTMENT_ID;
                } else if (GROUP_ID == 4) {
                    values['departmentID'] = DEPARTMENT_ID;
                }
                Ext.Ajax.request({
                    url: 'updateUser.action',
                    params: {
                        jsonString: Ext.encode(values)
                    },
                    success: function(response) {
                        if (CRM.checkResponse(response)) {
                            return;
                        }
                        var responseText = Ext.decode(response.responseText) || '';
                        var validate = responseText.validate || '';
                        if (responseText.validate == false) {
                            Ext.iterate(responseText, setVal);
                            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                        } else {
                            if (record == '') {
                                Ext.crm.msg("添加成功！", "");
                            } else {
                                Ext.crm.msg("编辑成功！", "");
                            }
                            store.loadPage(store.currentPage);
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
            }
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },
    queryUser: function(button) {
        if (button.up('grid').down('form').isHidden() && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var jsonString = "";
            var searchFlag = 2;
            var form = button.up('grid').down('form');
            var store = button.up('grid').getStore();
            if (form.isHidden()) {
                searchFlag = 1;
                var searchValue = button.up('toolbar').down('#searchText').getValue();
                jsonString = '{"groupID":"' + GROUP_ID + '","searchText" : "' + searchValue;
                jsonString = jsonString + '","userID" : "' + USER_ID + '","projectTeamID" : "' + PROJECT_TEAM_ID + '"}';
            } else {
                if (form.getForm().isValid()) {
                    var departmentID = button.up('form').down('combobox').getValue();
                    var values = form.getValues();
                    values['groupID'] = GROUP_ID;
                    values['userID'] = USER_ID;
                    values['projectTeamID'] = PROJECT_TEAM_ID;
                    if (departmentID != null) {
                        values['departmentID'] = departmentID;
                    }
                    jsonString = Ext.encode(values);
                }
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
    openOrCloseSuperQueryBtn: function(button) {
        var list = Ext.getCmp('UserList');
        var toolBar = button.up('toolbar');
        var gridPanel = toolBar.up('gridpanel');
        var toolForm = gridPanel.down('form');
        if (toolForm.isHidden()) {
            toolBar.down('button[action="queryBtn"]').setDisabled(true);
            toolBar.down('#searchText').setDisabled(true);
            toolBar.down('#searchText').reset();
            this.loadCallbackD(list.departmentStore);
            toolForm.show();
            button.setText('关闭检索');
            setTimeout(function fn() {
                toolForm.down('combobox[name="departmentID"]').setValue(0);
            }, 300);
            gridPanel.update();
        } else {
            toolBar.down('button[action="queryBtn"]').setDisabled(false);
            toolBar.down('#searchText').setDisabled(false);
            toolForm.getForm().reset();
            toolForm.hide();
            button.setText('高级检索');
            gridPanel.update();
        }
        var store = gridPanel.getStore();
        utils.loadPageOne(store);
    }
});