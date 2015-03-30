Ext.define('CRM.controller.systemManagement.groupManagement.Group', {
    extend: 'Ext.app.Controller',

    views: [ 'systemManagement.groupManagement.GroupManage',
            'systemManagement.groupManagement.GroupUpdate',
            'systemManagement.groupManagement.GroupMemberEdit',
            'systemManagement.groupManagement.GroupMemberList',
            'systemManagement.groupManagement.OtherMemberList' ],
    init: function() {
        this.control({
            'groupmanage gridpanel[id=groupList]': {
                selectionchange: this.changeBtn,
                select: this.showGroupMembers
            },
            'groupmanage button[action=groupAddBtn]': {
                click: this.groupAdd
            },
            'groupmanage button[action=groupEditBtn]': {
                click: this.groupEdit
            },
            'groupmanage button[action=groupDeleteBtn]': {
                click: this.groupDelete
            },
            'groupupdate textfield': {
                blur: utils.trimSpace
            },
            'groupupdate treepanel[id=groupAccessRightsTree]': {
                checkchange: this.accessTreeNodeCheckChange,
                itemclick: this.showGroupOperationTree,
                scrollershow: utils.scrollershow
            },
            'groupupdate treepanel[id=groupOperationRightsTree]': {
                checkchange: utils.nodeCheckChange,
                scrollershow: utils.scrollershow
            },
            'groupupdate button[action=save]': {
                click: this.savaUpdate
            },
            'groupmanage button[action=groupMemberEdit]': {
                click: this.groupMemberEdit
            },
            'GroupMemberEdit button[action=close]': {
                click: utils.winClose
            },
            'groupupdate button[action=cancel]': {
                click: utils.winClose
            },
            'groupupdate button[action=reset]': {
                click: this.resetRecord
            },
            'groupMemberList': {
                selectionchange: this.setRemoveMembersBtnShow
            },
            'OtherMemberList': {
                selectionchange: this.setAddMembersBtnShow
            },
            'GroupMemberEdit button[action=memberMoveToRightBtn]': {
                click: this.removeMembers
            },
            'GroupMemberEdit button[action=memberMoveToLeftBtn]': {
                click: this.addMembers
            },
            'OtherMemberList combobox': {
                change: this.reloadOtherMember
            }
        });
    },

    flag: null,
    editID: null,
    editName: null,
    selectGroupID: null,
    nodeList: null,
    rootNode: null,
    menuNodes: null,
    saveTempNodes: null,
    operationNodes: null,
    menuNodeNotChecked: false,

    viewInit: function() {
        var groupManage = Ext.getCmp('groupmanage');
        if (typeof (groupManage) == 'undefined') {
            groupInfo = Ext.widget('groupmanage');
        }

        var groupStore = Ext.getCmp('groupList').getStore();
        var param = {
            groupID: GROUP_ID
        };
        groupStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, param);
        });
        groupStore.load();
        var store = Ext.getCmp('groupMembers').getStore();
        var paramGroupID = {
                groupID: -2
        };
        store.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, paramGroupID);
        });
        store.loadPage(1);
        Ext.getCmp('centercard').insert(1, groupInfo);
        Ext.getCmp('centercard').getLayout().setActiveItem('groupmanage');

        return groupManage;
    },

    loadMenuTree: function() {
        var me = this;
        var groupAccessRightsTree = Ext.getCmp('groupAccessRightsTree');
        var operationRightsTree = Ext.getCmp('groupOperationRightsTree');
        var new_params = {
            userMode: 0,
            id: 1
        };
        var groupAccessRightsTreeStore = groupAccessRightsTree.getStore();
        var operationStore = operationRightsTree.getStore();
        groupAccessRightsTreeStore.on('beforeload', function(store, options) {
            Ext.apply(store.proxy.extraParams, new_params);
        });

        groupAccessRightsTreeStore.load({
            callback: function(records, options, success) {
                // 去除菜单管理子节点
                if (success) {
                    if (me.menuNodes != "" && me.menuNodes != null) {
                        for ( var i = 0; i < me.menuNodes.length; i++) {
                            var node = groupAccessRightsTreeStore.getNodeById(me.menuNodes[i]);
                            if (node != undefined) {
                                node.set('checked', true);
                                utils.nodeCheckChange(node, true);
                            }
                        }
                    }

                    var rootNode = groupAccessRightsTree.getRootNode();
                    var systemManageNode = null;
                    var menuManageNode = null;
                    rootNode.cascadeBy(function(node) {
                        if (node.get('id') == 4) {
                            systemManageNode = node;
                        }
                        if (node.get('id') == 45) {
                            menuManageNode = node;
                        }
                    });
                    systemManageNode.removeChild(menuManageNode);
                }
            }
        });

        operationStore.on('beforeload', function(store, options) {
            Ext.apply(store.proxy.extraParams, new_params);
        });
        operationStore.load({
            callback: function(records, options, success) {
                if (success) {
                    if (me.operationNodes != "" && me.operationNodes != null) {
                        for ( var i = 0; i < me.operationNodes.length; i++) {
                            var node = operationStore.getNodeById(me.operationNodes[i]);
                            if (node != undefined) {
                                node.set('checked', true);
                                utils.nodeCheckChange(node, true);
                            }
                        }
                    }
                    var rootNode = operationRightsTree.getRootNode();
                    me.nodeList = new Array();
                    rootNode.eachChild(function(child) {
                        me.nodeList.push(child);
                    });
                    // Modified for EXTJS 4.2 start 20150302
//                    rootNode.removeAll();
                    utils.removeAllChild.apply(rootNode);
                    // Modified for EXTJS 4.2 end 20150302
                }
            }
        });
    },

    showGroupOperationTree: function(tree, record) {
        var me = this;
        me.rootNode = Ext.getCmp('groupOperationRightsTree').getStore().getRootNode();
        me.menuNodeNotChecked = false;
        // Modified for EXTJS 4.2 start 20150302
//        me.rootNode.removeAll();
        utils.removeAllChild.apply(me.rootNode);
        // Modified for EXTJS 4.2 end 20150302
        me.appendOperationNodes(record);
        me.showOperationNodes();
    },

    appendOperationNodes: function(node) {
        var me = this;
        var nodeList = me.nodeList;
        if (node.isLeaf()) {
            for ( var i = 0; i < nodeList.length; i++) {
                if (nodeList[i].data.id == node.get('id') * 10) {
                    if (node.data.checked) {
                        nodeList[i].set('expanded', true);
                        me.rootNode.appendChild(nodeList[i]);
                    } else {
                        nodeList[i].set('checked', false);
                        utils.nodeCheckChange(nodeList[i], false);
                        me.menuNodeNotChecked = true;
                    }
                }
            }
        } else {
            var childNodes = node.childNodes;
            var nd;
            for ( var i = 0; i < childNodes.length; i++) {
                nd = childNodes[i];
                me.appendOperationNodes(nd);
            }
        }
    },

    showOperationNodes: function() {
        var me = this;
        if (me.rootNode.hasChildNodes()) {
            Ext.getCmp('groupOperationCard').getLayout().setActiveItem('groupOperationRightsTree');
        } else if (me.menuNodeNotChecked) {
            Ext.getCmp('groupOperationStateDisplay').setValue('<h3>该项操作权限隐藏，请先赋予该项访问权限</h3>');
            Ext.getCmp('groupOperationCard').getLayout().setActiveItem('groupOperationStateDisplay');
        } else {
            Ext.getCmp('groupOperationStateDisplay').setValue('<h3>无操作权限，只有访问权限可赋予</h3>');
            Ext.getCmp('groupOperationCard').getLayout().setActiveItem('groupOperationStateDisplay');
        }
    },

    accessTreeNodeCheckChange: function(node, checked) {
        utils.nodeCheckChange(node, checked);
        this.showGroupOperationTree(Ext.getCmp('groupAccessRightsTree'), node);
    },

    changeBtn: function(sm, selections) {
        var record = Ext.getCmp('groupList').getSelectionModel().getSelection();
        if (typeof (record[0]) != 'undefined') {
            var groupID = record[0].get('groupID');
            Ext.getCmp('groupEdit').setDisabled(selections.length != 1);
            Ext.getCmp('groupMemberEdit').setDisabled(selections.length != 1);
            if (groupID == 1 || groupID == 2) {
                Ext.getCmp('groupDelete').setDisabled(true);
            } else {
                Ext.getCmp('groupDelete').setDisabled(selections.length != 1);
            }
            if (groupID == 1) {
                Ext.getCmp('groupEdit').setDisabled(true);
            }
        } else {
            Ext.getCmp('groupEdit').setDisabled(true);
            Ext.getCmp('groupDelete').setDisabled(true);
            Ext.getCmp('groupMemberEdit').setDisabled(selections.length != 1);
        }
    },

    showGroupMembers: function(grid, record) {
        var groupID = record.get('groupID');
        var view = Ext.getCmp('groupMembers');
        var store = view.getStore();
        var paramGroupID = {
            groupID: groupID,
            departmentID: DEPARTMENT_ID
        };
        store.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, paramGroupID);
        });
        store.loadPage(1);
    },

    groupAdd: function() {
        var me = this;
        var view = Ext.widget('groupupdate');
        me.menuNodes = null;
        me.operationNodes = null;
        me.flag = 'add';
        view.setTitle('添加角色信息');
        view.down('#groReset').setText('清空');
        Ext.getCmp('groupOperationCard').getLayout().setActiveItem('groupOperationStateDisplay');
        me.loadMenuTree();
    },

    groupEdit: function() {
        var me = this;
        var view = Ext.widget('groupupdate');
        view.setTitle('编辑角色信息');
        view.down('#groReset').setText('重置');
        var record = Ext.getCmp('groupList').getSelectionModel().getSelection();
        var idAuthorized = record[0].get('groupID');
        me.editName = record[0].get('groupName');
        me.selectGroupID = record[0].get('groupID');
        Ext.getCmp('groupOperationCard').getLayout().setActiveItem('groupOperationStateDisplay');
        me.flag = 'edit';
        view.down('form').down('#groupIDBox').setValue(record[0].get('groupID'));
        view.down('form').down('#groupNameBox').setValue(me.editName);
        Ext.Ajax.request({
            url: 'getMemberAuthorization.action',
            params: {
                userMode: 0,
                id: idAuthorized
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var responseText = Ext.decode(response.responseText) || '';
                me.menuNodes = responseText.menuNodes;
                me.operationNodes = responseText.operationNodes;
                me.loadMenuTree();
            },
            failure: function(response) {
                messageBox.alert('提示', '连接后台有错，请联系管理员！');
            }
        });
    },

    groupDelete: function() {
        var view = Ext.getCmp('groupList');
        var record = view.getSelectionModel().getSelection();
        var userCount = Ext.getCmp('groupMembers').getStore().getCount();
        var store = view.getStore();
        var groupID = record[0].get('groupID');
        if (userCount != 0) {
            messageBox.alert('提示', '该角色下有未处理的用户，角色不能删除！');
        } else {
            box.confirm('提示', '你确定要删除所选角色吗？', function(button, text) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: 'deleteGroup.action',
                        params: {
                            groupID: encodeURI(groupID)
                        },
                        method: 'POST',
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            Ext.crm.msg('删除成功！', '');
                            store.load();
                        },
                        failure: function() {
                            messageBox.alert('提示', '删除失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    },

    savaUpdate: function(button) {
        var me = this;
        this.updateRecord(button, 'updateGroup.action', 'groupList', me.flag);
    },

    recoverOperationAuthorizationTree: function() {
        var me = this;
        var operationRootNode = Ext.getCmp('groupOperationRightsTree').getRootNode();
        // Modified for EXTJS 4.2 start 20150302
//        operationRootNode.removeAll();
        utils.removeAllChild.apply(operationRootNode);
        // Modified for EXTJS 4.2 end 20150302
        for ( var i = 0; i < me.nodeList.length; i++) {
            operationRootNode.appendChild(me.nodeList[i]);
        }
    },

    updateRecord: function(button, url, listString, flag) {
        var win = button.up('window');
        var form = win.down('form');
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            var me = this;
            var values = form.getValues();
            var list = Ext.getCmp(listString) || '';
            var store = null;
            if (list != '') {
                store = list.getStore();
            }
            Ext.Ajax.request({
                url: url,
                params: {
                    jsonString: Ext.encode(values)
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText) || '';
                    me.selectGroupID = responseText.groupID;
                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        me.saveAuthorization();
                        win.close();
                        store.load();
                        if (flag == 2) {
                            var teamFlag = responseText.flag || '';
                            if (teamFlag > 0) {
                                Ext.Ajax.request({
                                    url: 'setIsMailed.action',
                                    params: {
                                        jsonString: teamFlag
                                    }
                                });
                            }
                        }
                    }
                },
                failure: function(response) {
                    if (me.flag == 'add') {
                        messageBox.alert('提示', '添加失败，请联系管理员！');
                    } else {
                        messageBox.alert('提示', '编辑失败，请联系管理员！');
                    }
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },

    resetRecord: function() {
        var me = this;
        var view = Ext.getCmp('groupupdate');
        var groupAccessRightsTree = Ext.getCmp('groupAccessRightsTree');
        var groupOperationRightsTree = Ext.getCmp('groupOperationRightsTree');
        var groupCheckNodes = groupAccessRightsTree.getChecked();
        var groupOperationCheckNodes = groupOperationRightsTree.getChecked();
        Ext.Array.each(groupCheckNodes, function(node) {
            node.set('checked', false);
        });
        Ext.Array.each(groupOperationCheckNodes, function(node) {
            node.set('checked', false);
        });
        if (me.flag == 'add') {
            view.down('form').down('#groupNameBox').reset();
        } else {
            view.down('form').down('#groupNameBox').setValue(me.editName);
            for ( var i = 0; i < me.menuNodes.length; i++) {
                node = groupAccessRightsTree.getStore().getNodeById(me.menuNodes[i]);
                node.set('checked', true);
                utils.nodeCheckChange(node, true);
            }

            for ( var i = 0; i < me.operationNodes.length; i++) {
                var node = groupOperationRightsTree.getStore().getNodeById(me.operationNodes[i]);
                if (node != undefined) {
                    node.set('checked', true);
                    utils.nodeCheckChange(node, true);
                }
            }
        }
    },

    saveAuthorization: function() {
        var me = this;
        me.saveTempNodes = new Array();
        Ext.getCmp('groupOperationRightsTree').getRootNode().eachChild(function(child) {
            me.saveTempNodes.push(child);
        });
        me.recoverOperationAuthorizationTree();
        var accessCheckedNodes = Ext.getCmp('groupAccessRightsTree').getChecked();
        var operationCheckedNodes = Ext.getCmp('groupOperationRightsTree').getChecked();
        var accessNodeIDs = new Array();
        Ext.Array.each(accessCheckedNodes, function(node) {
            if (node.data.leaf) {
                accessNodeIDs.push(node.internalId);
            }
        });
        var operationNodeIDs = new Array();
        Ext.Array.each(operationCheckedNodes, function(node) {
            if (node.data.leaf) {
                operationNodeIDs.push(node.internalId);
            }
        });
        var arrId = accessNodeIDs.concat(operationNodeIDs);
        /**
         * save personal user or role authorization
         */
        function doSave() {
            var idAuthorized = me.selectGroupID;
            Ext.Ajax.request({
                url: 'saveAuthorization.action',
                params: {
                    userMode: 0,
                    jsonString: arrId,
                    id: idAuthorized,
                    userID: USER_ID
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    me.menuNodes = accessNodeIDs;
                    me.operationNodes = operationNodeIDs;
                    var responseText = Ext.decode(response.responseText) || '';
                    if (responseText.validate == false) {
                        if (responseText.savedAuthorizationIds) {
                            messageBox.alert('提示', responseText.savedAuthorizationIds);
                        }
                    } else {
                        if (me.flag == 'add') {
                            Ext.crm.msg('添加成功！', '');
                        } else {
                            Ext.crm.msg('编辑成功！', '');
                        }
                    }
                },
                failure: function(response) {
                    messageBox.alert('提示', "保存出错，请重新保存或联系管理员！");
                }
            });
        }
        doSave();

        // Modified for EXTJS 4.2 start 20150302
//        Ext.getCmp('groupOperationRightsTree').getRootNode().removeAll();
        var operationRootNode = Ext.getCmp('groupOperationRightsTree').getRootNode();

        utils.removeAllChild.apply(operationRootNode);
        // Modified for EXTJS 4.2 end 20150302
        for ( var i = 0; i < me.saveTempNodes.length; i++) {
            Ext.getCmp('groupOperationRightsTree').getRootNode().appendChild(me.saveTempNodes[i]);
        }
    },

    groupMemberEdit: function(button) {
        var me = this;
        var records = Ext.getCmp('groupList').getSelectionModel().getSelection();
        me.editID = records[0].get('groupID');
        me.editName = records[0].get('groupName');
        var memberEditWindow = Ext.getCmp('GroupMemberEdit');
        if (typeof (memberEditWindow) == 'undefined') {
            memberEditWindow = Ext.widget('GroupMemberEdit');
            addedMembers = Ext.getCmp('addedGroupMembers');
            memberEditWindow.setTitle('管理角色成员');
            addedMembers.setTitle(me.editName);
        }

        var addedMembersStore = Ext.getCmp('addedGroupMembers').getStore();
        var paramAdded = {
            groupID: me.editID,
            departmentID: DEPARTMENT_ID
        };
        addedMembersStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, paramAdded);
        });
        addedMembersStore.loadPage(1);

        var canAddMembersStore = Ext.getCmp('canAddGroupMembers').getStore();
        var paramCanAdd = {
            groupID: -1,
            departmentID: DEPARTMENT_ID
        };
        canAddMembersStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, paramCanAdd);
        });
        canAddMembersStore.loadPage(1);

        var groupStore = Ext.getCmp('canAddGroupID').getStore();
        var param = {
            groupID: GROUP_ID
        };
        groupStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, param);
        });
        groupStore.load({
            callback: function(records, operation, success) {
                var node = groupStore.getById(me.editID);
                groupStore.remove(node);
            }
        });
    },

    reloadOtherMember: function(comnbobox) {
        var canAddMembersStore = Ext.getCmp('canAddGroupMembers').getStore();
        var value = Ext.getCmp('canAddGroupID').getValue();
        var paramCanAdd = {
            groupID: value,
            departmentID: DEPARTMENT_ID
        };
        canAddMembersStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, paramCanAdd);
        });
        canAddMembersStore.loadPage(1);
    },

    setRemoveMembersBtnShow: function(sm, selections) {
        Ext.getCmp('memberMoveToRight').setDisabled(selections.length == 0);
    },

    setAddMembersBtnShow: function(sm, selections) {
        Ext.getCmp('memberMoveToLeft').setDisabled(selections.length == 0);
    },

    operateMembers: function(operateMode, actionURL) {
        var me = this;
        var value = Ext.getCmp('canAddGroupID').getValue();
        var grid = null;
        if (operateMode == 1) {
            grid = Ext.getCmp('addedGroupMembers');
        } else {
            grid = Ext.getCmp('canAddGroupMembers');
        }
        var membersStore = Ext.getCmp('groupMembers').getStore();
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get('userID');
        }
        if (checkRecord.length != 0) {
            box.confirm('提示', '确定转移所选用户？', function showResult(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: actionURL,
                        params: {
                            otherGroupID: value,
                            groupID: me.editID,
                            receivedIDs: checkRecordIDs
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var currentPage = store.currentPage;
                            var pageSize = store.pageSize;
                            var total = null;
                            // 移除成员，计算total
                            if (operateMode == 1) {
                                total = store.totalCount - checkRecord.length;
                                if (total <= (currentPage - 1) * pageSize) {
                                    currentPage = currentPage - 1;
                                }
                                if (total == 0) {
                                    currentPage = 1;
                                }
                                store.loadPage(currentPage);
                                var canAddStore = Ext.getCmp('canAddGroupMembers').getStore();
                                canAddStore.loadPage(canAddStore.currentPage);
                                membersStore.load();
                            } else if (operateMode == 2) {
                                store.loadPage(currentPage);
                                var addedStore = Ext.getCmp('addedGroupMembers').getStore();
                                addedStore.loadPage(addedStore.currentPage);
                                membersStore.load();
                            } else {
                                var addedStore = Ext.getCmp('addedGroupMembers').getStore();
                                addedStore.loadPage(store.currentPage);
                                var canAddStore = Ext.getCmp('canAddGroupMembers').getStore();
                                canAddStore.loadPage(store.currentPage);
                                membersStore.load();
                            }
                        },
                        failure: function() {
                            messageBox.alert('提示', '转移失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    },

    removeMembers: function(button) {
        var me = this;
        var value = Ext.getCmp('canAddGroupID').getValue();
        if (value == null) {
            messageBox.alert('提示', '请选择所要转移到的角色！');
        } else {
            me.operateMembers(1, 'removeGroupMembers.action');
        }
    },

    addMembers: function(button) {
        var me = this;
        me.operateMembers(2, 'addGroupMembers.action');
    }

});