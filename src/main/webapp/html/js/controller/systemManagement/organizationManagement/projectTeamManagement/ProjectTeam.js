Ext.define('CRM.controller.systemManagement.organizationManagement.projectTeamManagement.ProjectTeam', {
    extend: 'Ext.app.Controller',
    models: [ 'systemManagement.organizationManagement.projectTeamManagement.ProjectTeamInfo',
              'systemManagement.userManagement.UserInfo' ],
    stores: [ 'systemManagement.organizationManagement.projectTeamManagement.ProjectTeamInfo',
            'systemManagement.organizationManagement.projectTeamManagement.AddedMembers',
            'systemManagement.organizationManagement.projectTeamManagement.CanAddMembers' ],
    views: [ 'systemManagement.organizationManagement.projectTeamManagement.ProjectTeamInfoBody',
            'systemManagement.organizationManagement.projectTeamManagement.ProjectTeamList',
            'systemManagement.organizationManagement.projectTeamManagement.ProjectTeamUpdate',
            'systemManagement.organizationManagement.projectTeamManagement.ProjectTeamDetail',
            'systemManagement.organizationManagement.projectTeamManagement.TeamMemberEdit',
            'systemManagement.organizationManagement.projectTeamManagement.MemberList' ],
    init: function() {
        this.control({
            'ProjectTeamList': {
                selectionchange: this.changeBtn,
                itemdblclick: this.projectTeamEdit,
                select: this.showDetail
            },
            'ProjectTeamList toolbar combobox': {
                select: this.comboSelectChange
            },
            'ProjectTeamList button[action=projectTeamAdd]': {
                click: this.projectTeamAdd
            },
            'ProjectTeamList button[action=projectTeamEdit]': {
                click: this.projectTeamEdit
            },
            'ProjectTeamList button[action=teamMemberEdit]': {
                click: this.teamMemberEdit
            },
            'ProjectTeamList button[action=projectTeamDelete]': {
                click: this.projectTeamDelete
            },
            'ProjectTeamUpdate button[action=save]': {
                click: this.saveOrUpdate
            },
            'ProjectTeamUpdate button[action=reset]': {
                click: utils.resetRecord
            },
            'ProjectTeamUpdate textfield': {
                blur: utils.trimSpace
            },
            'ProjectTeamUpdate': {
                render: this.loadStore
            },
            'ProjectTeamList textfield': {
                blur: utils.trimSpace
            },
            'ProjectTeamUpdate button[action=cancel]': {
                click: utils.winClose
            },
            'ProjectTeamList button[action=queryBtn]': {
                click: this.queryProjectTeam
            },
            'TeamMemberEdit button[action=close]': {
                click: utils.winClose
            },
            'MemberList[id=addedProjectTeamMembers]': {
                selectionchange: this.setRemoveMembersBtnShow
            },
            'MemberList[id=canAddProjectTeamMembers]': {
                selectionchange: this.setAddMembersBtnShow
            },
            'MemberList[id=addedProjectTeamMembers] button[action=simpleQuery]': {
                click: this.queryMembers
            },
            'MemberList[id=canAddProjectTeamMembers] button[action=simpleQuery]': {
                click: this.queryMembers
            },
            'panel[id=teamMemberEditWindowCenterPanel] button[action=removeMembers]': {
                click: this.removeMembers
            },
            'panel[id=teamMemberEditWindowCenterPanel] button[action=addMembers]': {
                click: this.addMembers
            }
        });
    },
    projectTeamID: null,
    departmentIDs: null,
    viewInit: function(flag) {
        var projectTeamInfoBody = Ext.getCmp('ProjectTeamInfoBody');
        if (typeof (projectTeamInfoBody) == 'undefined') {
            projectTeamInfoBody = Ext.widget('ProjectTeamInfoBody');
        }
        Ext.getCmp('centercard').insert(1, projectTeamInfoBody);
        Ext.getCmp('centercard').getLayout().setActiveItem('ProjectTeamInfoBody');
        var view = Ext.getCmp('ProjectTeamList');
        view.setTitle('团队基本信息列表');
        view.down('textfield[name=projectTeamName]').reset();
        var departmentStore = view.departmentStore;
        this.departmentStoreLoad(departmentStore);

        return projectTeamInfoBody;
    },
    departmentStoreLoad: function(store) {
        var me = this;
        store.on('beforeload', function(store) {
            var new_param = {
                userID: USER_ID,
                groupID: GROUP_ID,
                departmentID: DEPARTMENT_ID
            };
            Ext.apply(store.proxy.extraParams, new_param);
        });
        store.load({
            callback: function(records, operation, success) {
                var view = Ext.getCmp('ProjectTeamList');
                var departmentIDArray = new Array();
                store.each(function(record) {
                    departmentIDArray.push(record.get('departmentID'));
                });
                me.departmentIDs = departmentIDArray;
                if (me.departmentIDs.length != 0) {
                    var projectTeamStore = view.getStore();
                    projectTeamStore.on('beforeload', function(store, options) {
                        var new_params = {
                            jsonString: '{"departmentIDs" : ' + Ext.encode(me.departmentIDs) + ', "projectTeamName": ""}'
                        };
                        Ext.apply(store.proxy.extraParams, new_params);
                    });
                    projectTeamStore.loadPage(1);
                }
                store.insert(0, {
                    departmentName: '- 不限 -',
                    departmentID: 0
                });
                view.down('combobox').setValue(0);
            }
        });
        Ext.override(Ext.LoadMask, {
            onHide: function() {
                this.callParent();
            }
        });
    },
    loadStore: function(view) {
        view.departmentStore.on('beforeload', function(store) {
            var new_param = {
                userID: USER_ID,
                groupID: GROUP_ID,
                departmentID: DEPARTMENT_ID
            };
            Ext.apply(store.proxy.extraParams, new_param);
        });
        view.departmentStore.load();
        var jsonString = '{"userID":' + USER_ID + ',"groupID":' + GROUP_ID + ',"departmentID":' + DEPARTMENT_ID + ',"projectTeamID":'
                + PROJECT_TEAM_ID + '}';
        view.leaderStore.on('beforeload', function(store) {
            var param = {
                jsonString: jsonString
            };
            Ext.apply(store.proxy.extraParams, param);
        });
        view.leaderStore.load({
            callback: function() {
                if (view.title == '编辑团队') {
                    var record = view.down('form').getRecord();
                    view.leaderStore.insert(0, {
                        projectTeamLeaderName: record.get('projectTeamLeaderName') + '(当前负责人)',
                        projectTeamLeaderID: record.get('projectTeamLeaderID')
                    });
                    view.down('combobox[name=projectTeamLeaderID]').setValue(record.get('projectTeamLeaderID'));
                } else {
                    return;
                }
            }
        });
        view.codeStore.load();
    },
    comboSelectChange: function(combo, records) {
        if (records[0].get('departmentID') == 0 && records.length > 1) {
            combo.setValue(records[1].get('departmentID'));
        } else {
            Ext.Array.each(records, function(record) {
                if (record.get('departmentID') == 0) {
                    combo.setValue(0);
                    return;
                }
            });
        }
    },
    setRemoveMembersBtnShow: function(sm, selections) {
        Ext.getCmp('teamMemberEditWindowCenterPanel').down('button[action=removeMembers]').setDisabled(selections.length == 0);
    },
    setAddMembersBtnShow: function(sm, selections) {
        Ext.getCmp('teamMemberEditWindowCenterPanel').down('button[action=addMembers]').setDisabled(selections.length == 0);
    },
    teamMemberEdit: function() {
        var me = this;
        var records = Ext.getCmp('ProjectTeamList').getSelectionModel().getSelection();
        me.projectTeamID = records[0].get('projectTeamID');
        var memberEditWindow = Ext.getCmp('TeamMemberEdit');
        if (typeof (memberEditWindow) == 'undefined') {
            memberEditWindow = Ext.widget('TeamMemberEdit');
            memberEditWindow.setTitle('编辑团队成员');
            Ext.getCmp('addedProjectTeamMembers').setTitle(records[0].get('projectTeamName') + ' 团队成员');
            Ext.getCmp('canAddProjectTeamMembers').setTitle('其他团队成员');
            var removeBtn = Ext.getCmp('teamMemberEditWindowCenterPanel').down('button[action=addMembers]');
            var addBtn = Ext.getCmp('teamMemberEditWindowCenterPanel').down('button[action=removeMembers]');
            removeBtn.setDisabled(true);
            addBtn.setDisabled(true);
        }
        // 已添加成员store
        var addedMembersStore = memberEditWindow.down('MemberList[id=addedProjectTeamMembers]').getStore();
        // 查询已添加成员，queryMembersMode设置为1
        var paramAdded = {
            queryMembersMode: 1,
            jsonString: '{"projectTeamID" : ' + me.projectTeamID + ', "searchText" : ""}'
        };
        addedMembersStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, paramAdded);
        });
        addedMembersStore.loadPage(1);
        // 可添加成员store
        var canAddMembersStore = memberEditWindow.down('MemberList[id=canAddProjectTeamMembers]').getStore();
        // 查询可添加成员，queryMembersMode设置为2
        var paramCanAdd = {
            queryMembersMode: 2,
            jsonString: '{"userID" : ' + USER_ID + ', "projectTeamID" : ' + me.projectTeamID + ', "searchText" : ""}'
        };
        canAddMembersStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, paramCanAdd);
        });
        canAddMembersStore.loadPage(1);
    },
    operateMembers: function(operateMode, grid, actionURL, hintMsg, crmSuccessMsg, crmFailureMsg) {
        var me = this;
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get('userID');
        }
        if (checkRecord.length != 0) {
            box.confirm('提示', hintMsg, function(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: actionURL,
                        params: {
                            projectTeamID: me.projectTeamID,
                            receivedIDs: checkRecordIDs
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var currentPage = store.currentPage;
                            var pageSize = store.pageSize;
                            var total = null;
                            if (operateMode == 1) {
                                var responseText = Ext.decode(response.responseText);
                                var leaderID = responseText.leaderID || '';
                                var ok = responseText.ok;
                                var removeRecLength = checkRecord.length;
                                if (leaderID != '') {
                                    removeRecLength = removeRecLength - 1;
                                }
                                total = store.totalCount - removeRecLength;
                                if (total <= (currentPage - 1) * pageSize) {
                                    currentPage = currentPage - 1;
                                }
                                if (total == 0) {
                                    currentPage = 1;
                                }
                                store.loadPage(currentPage);
                                // 移除团队成员后，可添加成员grid重新load
                                var canAddStore = Ext.getCmp('canAddProjectTeamMembers').getStore();
                                canAddStore.loadPage(canAddStore.currentPage);
                                if (leaderID == '') {
                                    Ext.crm.msg(crmSuccessMsg, "");
                                } else if (ok == true) {
                                    for ( var i = 0; i < checkRecord.length; i++) {
                                        if (checkRecord[i].get('userID') == leaderID) {
                                            var realName = checkRecord[i].get('realName');
                                            var jobID = checkRecord[i].get('jobID');
                                            var hintMsg = realName + '(' + jobID + ')';
                                            messageBox.alert('提示', hintMsg + ' 是团队负责人，不能删除！');
                                        }
                                    }
                                } else if (ok == false) {
                                    messageBox.alert('提示', '团队负责人不能删除！');
                                }
                            } else if (operateMode == 2) {
                                total = store.totalCount - checkRecord.length;
                                if (total <= (currentPage - 1) * pageSize) {
                                    currentPage = currentPage - 1;
                                }
                                if (total == 0) {
                                    currentPage = 1;
                                }
                                Ext.crm.msg(crmSuccessMsg, "");
                                store.loadPage(currentPage);
                                // 添加团队成员后，已添加成员grid重新load
                                var addedStore = Ext.getCmp('addedProjectTeamMembers').getStore();
                                addedStore.loadPage(addedStore.currentPage);
                                // 如果operateMode不为1或2，将两个grid都重新load第一页
                            } else {
                                var addedStore = Ext.getCmp('addedProjectTeamMembers').getStore();
                                addedStore.loadPage(1);
                                var canAddStore = Ext.getCmp('canAddProjectTeamMembers').getStore();
                                canAddStore.loadPage(1);
                            }
                        },
                        failure: function() {
                            messageBox.alert('提示', crmFailureMsg + '请联系管理员！');
                        }
                    });
                }
            });
        }
    },
    removeMembers: function(button) {
        var me = this;
        var grid = Ext.getCmp('addedProjectTeamMembers');
        // 根据operateMode参数，去操作成员，operateMode为1，移除团队成员
        me.operateMembers(1, grid, 'removeTeamMembers.action', '确定移除所选用户？', '移除成功！', '移除失败，');
    },
    addMembers: function(button) {
        var me = this;
        var grid = Ext.getCmp('canAddProjectTeamMembers');
        // 根据operateMode参数，去操作成员，operateMode为2，添加成员到该团队
        me.operateMembers(2, grid, 'addTeamMembers.action', '确定添加所选用户到该团队？', '添加成功！', '添加失败，');
    },
    queryMembers: function(button) {
        var me = this;
        if (button.up('toolbar').down('textfield').isValid()) {
            var store = button.up('grid').getStore();
            var queryMode = 0;
            if (button.up('grid').getId() == 'addedProjectTeamMembers') {
                queryMode = 1;
            } else if (button.up('grid').getId() == 'canAddProjectTeamMembers') {
                queryMode = 2;
            }
            var searchValue = button.up('toolbar').down('textfield').getValue();
            var jsonString = '{"userID" :' + USER_ID + ', "projectTeamID" : ' + me.projectTeamID + ', "searchText" : ' + Ext.encode(searchValue)
                    + '}';
            var new_params = {
                queryMembersMode: queryMode,
                jsonString: jsonString
            };
            store.on('beforeload', function(store, options) {
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
    showDetail: function(grid, record) {
        Ext.getCmp('ProjectTeamDetail').loadRecord(record);
    },
    changeBtn: function(sm, selections) {
        if (selections.length != 1) {
            Ext.getCmp('ProjectTeamDetail').hide();
        } else {
            Ext.getCmp('ProjectTeamDetail').show();
        }
        Ext.getCmp('projectTeamDelete').setDisabled(selections.length == 0);
        Ext.getCmp('projectTeamEdit').setDisabled(selections.length != 1);
        Ext.getCmp('teamMemberEditBtn').setDisabled(selections.length != 1);
    },
    queryProjectTeam: function(button) {
        var me = this;
        if (button.up('toolbar').down('textfield[name="projectTeamName"]').isValid()) {
            if (me.departmentIDs.length == 0) {
                messageBox.alert("提示", "未检索到符合条件的数据！");
                return;
            }
            var grid = Ext.getCmp('ProjectTeamList');
            var jsonString = '{';
            var departmentID = grid.down('combobox[name="department"]').getValue();
            var departmentIDs = new Array();
            departmentIDs.push(departmentID);
            if (departmentIDs == null || departmentIDs == '' || Ext.encode(departmentIDs) == '[0]') {
                departmentIDs = me.departmentIDs;
            }
            jsonString = jsonString + '"departmentIDs":' + Ext.encode(departmentIDs);
            var projectTeamName = grid.down('textfield[name="projectTeamName"]').getValue();
            jsonString = jsonString + ', "projectTeamName":' + Ext.encode(projectTeamName);
            jsonString = jsonString + '}';
            var store = grid.getStore();
            var new_params = {
                jsonString: jsonString
            };
            store.on('beforeload', function(store, options) {
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
                callback: function() {
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },

    projectTeamAdd: function() {
        var projectTeamUpdate = Ext.getCmp('ProjectTeamUpdate');
        if (typeof (projectTeamUpdate) == 'undefined') {
            projectTeamUpdate = Ext.widget('ProjectTeamUpdate');
        }
        projectTeamUpdate.down('button[action=reset]').setText('清空');
        projectTeamUpdate.setTitle('添加团队');
    },
    projectTeamEdit: function() {
        var win = Ext.getCmp('ProjectTeamUpdate');
        if (typeof (win) == 'undefined') {
            win = Ext.widget('ProjectTeamUpdate');
        }
        var grid = Ext.getCmp('ProjectTeamList');
        var records = grid.getSelectionModel().getSelection();
        win.setTitle('编辑团队');
        win.down('form').loadRecord(records[0]);
    },
    saveOrUpdate: function(button) {
        utils.updateRecord(button, 'saveOrUpdateProjectTeam.action', 'ProjectTeamList', '');
    },
    projectTeamDelete: function(button) {
        var grid = Ext.getCmp('ProjectTeamList');
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get('projectTeamID');
        }
        box.confirm('提示', '你确定要删除这些数据吗？', function(button) {
            if (button == 'yes') {
                Ext.Ajax.request({
                    url: 'deleteProjectTeam.action',
                    params: {
                        receivedIDs: checkRecordIDs
                    },
                    success: function(response, opts) {
                        if (CRM.checkResponse(response)) {
                            return;
                        }
                        var store = grid.getStore();
                        var currentPage = store.currentPage;
                        var pageSize = store.pageSize;
                        var total = store.totalCount - checkRecord.length;
                        if (total <= (currentPage - 1) * pageSize) {
                            currentPage = currentPage - 1;
                        }
                        if (total == 0) {
                            currentPage = 1;
                        }
                        store.loadPage(currentPage);
                        Ext.crm.msg("删除成功！", "");
                    },
                    failure: function() {
                        messageBox.alert('提示', '删除失败，请联系管理员！');
                    }
                });
            }
        });
    }

});