//Ext.define('CRM.controller.systemManagement.authorizationManagement.Authorization', {
//    extend: 'Ext.app.Controller',
//    views: [ 'systemManagement.authorizationManagement.AuthorizationList' ],
//    stores: [ 'systemManagement.authorizationManagement.AccessRightsTree', 'systemManagement.authorizationManagement.OperationRightsTree',
//            'systemManagement.authorizationManagement.Member' ],
//    models: [ 'systemManagement.authorizationManagement.Member' ],
//    init: function() {
//        this.control({
//            'authorizationlist treepanel[id=operationRightsTree]': {
//                checkchange: this.nodeCheckChange,
//                scrollershow: utils.scrollershow
//            },
//            'authorizationlist treepanel[id=accessRightsTree]': {
//                checkchange: this.nodeCheckChange,
//                itemclick: this.activeOperationTree,
//                scrollershow: utils.scrollershow
//            },
//            'authorizationlist button[name=saveAuthorization]': {
//                click: this.saveAuthorization
//            },
//            'authorizationlist button[name=resetAuthorization]': {
//                click: this.resetAuthorization
//            },
//            'authorizationlist > gridpanel[id=memberList]': {
//                select: this.showAuthorization
//            }
//        });
//    },
//    nodeList: null,
//    rootNode: null,
//    userMode: null,
//    menuNodes: null,
//    saveTempNodes: null,
//    operationNodes: null,
//    getAuthorizationFinished: true,
//    accessStoreLoadFinished: true,
//    operationStoreLoadFinished: true,
//    memberStoreLoadFinished: true,
//    accessNodeNotChecked: false,
//    noAccessTree: false,
//    activeOperationTree: function(tree, record) {
//        var me = this;
//        me.accessNodeNotChecked = false;
//        me.rootNode = Ext.getCmp('operationRightsTree').getStore().getRootNode();
//        me.rootNode.removeAll();
//        me.appendOperationNodes(record);
//        me.showOperationNodes();
//    },
//    appendOperationNodes: function(node) {
//        var me = this;
//        var nodeList = me.nodeList;
//        if (node.isLeaf()) {
//            for ( var i = 0; i < nodeList.length; i++) {
//                if (nodeList[i].data.id == node.get('id') * 10) {
//                    if (node.data.checked) {
//                        nodeList[i].set('expanded', true);
//                        me.rootNode.appendChild(nodeList[i]);
//                    } else {
//                        nodeList[i].set('checked', false);
//                        me.nodeCheckChange(nodeList[i], false);
//                        me.accessNodeNotChecked = true;
//                    }
//
//                }
//            }
//        } else {
//            var childNodes = node.childNodes;
//            var nd;
//            for ( var i = 0; i < childNodes.length; i++) {
//                nd = childNodes[i];
//                me.appendOperationNodes(nd);
//            }
//        }
//    },
//    showOperationNodes: function() {
//        var me = this;
//        if (me.rootNode.hasChildNodes()) {
//            Ext.getCmp('operationCard').getLayout().setActiveItem('operationRightsTree');
//        } else if (me.accessNodeNotChecked) {
//            Ext.getCmp('operationStateDisplay').setValue('<h3>该项操作权限隐藏，请先赋予该项访问权限</h3>');
//            Ext.getCmp('operationCard').getLayout().setActiveItem('operationStateDisplay');
//        } else {
//            Ext.getCmp('operationStateDisplay').setValue('<h3>无操作权限，只有访问权限可赋予</h3>');
//            Ext.getCmp('operationCard').getLayout().setActiveItem('operationStateDisplay');
//        }
//    },
//    showAuthorization: function(rowModel, record) {
//        var me = this;
//        rowModel.setLocked(true);
//        Ext.getCmp('authorizationTipText').setValue('选择行已锁定，正在加载权限...');
//        me.getAuthorizationFinished = false;
//        var idAuthorized = record.get('memberID');
//        Ext.Ajax.request({
//            url: 'getMemberAuthorization.action',
//            params: {
//                userMode: me.userMode,
//                id: idAuthorized
//            },
//            success: function(response) {
//                var responseText = Ext.decode(response.responseText) || '';
//                me.menuNodes = responseText.menuNodes;
//                me.operationNodes = responseText.operationNodes;
//                if (me.userMode == 0) {
//                    me.loadTreeAndShowRoleAuthorization(idAuthorized, rowModel);
//                } else if (me.userMode == 1) {
//                    me.showUserAuthorization();
//                }
//                me.getAuthorizationFinished = true;
//                rowModel.setLocked(!(me.accessStoreLoadFinished && me.operationStoreLoadFinished && me.getAuthorizationFinished));
//            },
//            failure: function(response) {
//                messageBox.alert("提示", "连接后台有错，请联系管理员！");
//                me.getAuthorizationFinished = true;
//                rowModel.setLocked(!(me.accessStoreLoadFinished && me.operationStoreLoadFinished && me.getAuthorizationFinished));
//            }
//        });
//
//    },
//    showUserAuthorization: function() {
//        var me = this;
//        if (me.noAccessTree) {
//            Ext.getCmp('authorizationTipText').setValue('<font color="red">注意：当前您不能赋予该用户任何权限，请检查您是否拥有权限！</font>');
//        } else {
//            Ext.getCmp('authorizationTipText').setValue('权限加载成功');
//            var accessRightsTree = Ext.getCmp('accessRightsTree');
//            var operationRightsTree = Ext.getCmp('operationRightsTree');
//            var accessCheckedNodes = accessRightsTree.getChecked();
//            var operationCheckedNodes = operationRightsTree.getChecked();
//
//            Ext.Array.each(accessCheckedNodes, function(node) {
//                node.set('checked', false);
//            });
//            Ext.Array.each(operationCheckedNodes, function(node) {
//                node.set('checked', false);
//            });
//            for ( var i = 0; i < me.menuNodes.length; i++) {
//                node = accessRightsTree.getStore().getNodeById(me.menuNodes[i]);
//                if (node != undefined) {
//                    node.set('checked', true);
//                    me.nodeCheckChange(node, true);
//                }
//            }
//            if (me.nodeList != null) {
//                me.recoverOperationAuthorizationTree();
//            }
//            for ( var i = 0; i < me.operationNodes.length; i++) {
//                node = operationRightsTree.getStore().getNodeById(me.operationNodes[i]);
//                if (node != undefined) {
//                    node.set('checked', true);
//                    me.nodeCheckChange(node, true);
//                }
//            }
//        }
//    },
//    loadTreeAndShowRoleAuthorization: function(idAuthorized, rowModel) {
//        var me = this;
//        me.accessStoreLoadFinished = false;
//        me.operationStoreLoadFinished = false;
//        var accessRightsTree = Ext.getCmp('accessRightsTree');
//        var operationRightsTree = Ext.getCmp('operationRightsTree');
//        var new_params = {
//            userMode: me.userMode,
//            id: idAuthorized - 1
//        };
//        var menuStore = accessRightsTree.getStore();
//        menuStore.on('beforeload', function(store, options) {
//            Ext.apply(store.proxy.extraParams, new_params);
//        });
//        menuStore.load({
//            callback: function(records, options, success) {
//                if (success) {
//                    if (records.length == 0) {
//                        me.noAccessTree = true;
//                        Ext.getCmp('saveAuthorization').setDisabled(true);
//                        Ext.getCmp('resetAuthorization').setDisabled(true);
//                        Ext.getCmp('authorizationTipText').setValue('注意：该角色不能赋予任何权限！请检查该角色上级是否拥有权限');
//                        Ext.getCmp('operationStateDisplay').setValue(' ');
//                    } else {
//                        me.noAccessTree = false;
//                        Ext.getCmp('saveAuthorization').setDisabled(false);
//                        Ext.getCmp('resetAuthorization').setDisabled(false);
//                        for ( var i = 0; i < me.menuNodes.length; i++) {
//                            node = menuStore.getNodeById(me.menuNodes[i]);
//                            node.set('checked', true);
//                            me.nodeCheckChange(node, true);
//                        }
//                        Ext.getCmp('authorizationTipText').setValue('权限加载成功');
//                        Ext.getCmp('operationStateDisplay').setValue('<h3>请点击访问权限以查看操作权限</h3>');
//                        Ext.getCmp('operationCard').getLayout().setActiveItem('operationStateDisplay');
//                    }
//
//                }
//                me.accessStoreLoadFinished = true;
//                rowModel.setLocked(!(me.accessStoreLoadFinished && me.operationStoreLoadFinished && me.getAuthorizationFinished));
//            }
//
//        });
//        var operationStore = operationRightsTree.getStore();
//        operationStore.on('beforeload', function(store, options) {
//            Ext.apply(store.proxy.extraParams, new_params);
//        });
//        operationStore.load({
//            callback: function(records, options, success) {
//                if (success) {
//                    for ( var i = 0; i < me.operationNodes.length; i++) {
//                        node = operationStore.getNodeById(me.operationNodes[i]);
//                        node.set('checked', true);
//                        me.nodeCheckChange(node, true);
//                    }
//                    var rootNode = operationRightsTree.getRootNode();
//                    me.nodeList = new Array();
//                    rootNode.eachChild(function(child) {
//                        me.nodeList.push(child);
//                    });
//                    rootNode.removeAll();
//                }
//                me.operationStoreLoadFinished = true;
//                rowModel.setLocked(!(me.accessStoreLoadFinished && me.operationStoreLoadFinished && me.getAuthorizationFinished));
//            }
//
//        });
//
//    },
//    saveAuthorization: function() {
//        var me = this;
//
//        me.saveTempNodes = new Array();
//        Ext.getCmp('operationRightsTree').getRootNode().eachChild(function(child) {
//            me.saveTempNodes.push(child);
//        });
//        me.recoverOperationAuthorizationTree();
//        var accessCheckedNodes = Ext.getCmp('accessRightsTree').getChecked();
//        var operationCheckedNodes = Ext.getCmp('operationRightsTree').getChecked();
//        var accessNodeIDs = new Array();
//        Ext.Array.each(accessCheckedNodes, function(node) {
//            if (node.data.leaf) {
//                accessNodeIDs.push(node.internalId);
//            }
//        });
//        var operationNodeIDs = new Array();
//        Ext.Array.each(operationCheckedNodes, function(node) {
//            if (node.data.leaf) {
//                operationNodeIDs.push(node.internalId);
//            }
//        });
//
//        var arrId = accessNodeIDs.concat(operationNodeIDs);
//
//        /**
//         * save personal user or role authorization
//         */
//        function doSave() {
//            var record = Ext.getCmp('memberList').getSelectionModel().getSelection();
//            var idAuthorized = record[0].get('memberID');
//            Ext.Ajax.request({
//                url: 'saveAuthorization.action',
//                params: {
//                    userMode: me.userMode,
//                    jsonString: arrId,
//                    id: idAuthorized,
//                    userID: USER_ID
//                },
//                success: function(response) {
//                    me.menuNodes = accessNodeIDs;
//                    me.operationNodes = operationNodeIDs;
//                    var responseText = Ext.decode(response.responseText) || '';
//                    if (responseText.validate == false) {
//                        if (responseText.savedAuthorizationIds) {
//                            messageBox.alert('提示', responseText.savedAuthorizationIds);
//                        }
//                    } else {
//                        messageBox.alert('提示', "保存权限成功！");
//                    }
//
//                },
//                failure: function(response) {
//                    messageBox.alert('提示', "保存出错，请重新保存或联系管理员！");
//                }
//            });
//        }
//        if (arrId.length == 0) {
//            box.confirm('提示', '您确定不赋任何权限吗？', function(button) {
//                if (button == 'yes') {
//                    doSave();
//                }
//            });
//        } else {
//            doSave();
//        }
//        Ext.getCmp('operationRightsTree').getRootNode().removeAll();
//        for ( var i = 0; i < me.saveTempNodes.length; i++) {
//            Ext.getCmp('operationRightsTree').getRootNode().appendChild(me.saveTempNodes[i]);
//        }
//
//    },
//    resetAuthorization: function() {
//        var me = this;
//
//        Ext.getCmp('operationStateDisplay').setValue('<h3>请点击访问权限以查看操作权限</h3>');
//        Ext.getCmp('operationCard').getLayout().setActiveItem('operationStateDisplay');
//        var accessRightsTree = Ext.getCmp('accessRightsTree');
//        var operationRightsTree = Ext.getCmp('operationRightsTree');
//
//        var accessCheckedNodes = accessRightsTree.getChecked();
//        var operationCheckedNodes = operationRightsTree.getChecked();
//
//        Ext.Array.each(accessCheckedNodes, function(node) {
//            node.set('checked', false);
//        });
//        Ext.Array.each(operationCheckedNodes, function(node) {
//            node.set('checked', false);
//        });
//        for ( var i = 0; i < me.menuNodes.length; i++) {
//            node = accessRightsTree.getStore().getNodeById(me.menuNodes[i]);
//            node.set('checked', true);
//            me.nodeCheckChange(node, true);
//        }
//
//        if (me.nodeList != null) {
//            me.recoverOperationAuthorizationTree();
//        }
//
//        for ( var i = 0; i < me.operationNodes.length; i++) {
//            node = operationRightsTree.getStore().getNodeById(me.operationNodes[i]);
//            node.set('checked', true);
//            me.nodeCheckChange(node, true);
//        }
//    },
//    viewInit: function(flag) {
//        var me = this;
//        me.noAccessTree = false;
//        var authorizationlist = Ext.getCmp('authorizationlist');
//        if (typeof (authorizationlist) == 'undefined') {
//            authorizationlist = Ext.widget('authorizationlist');
//            authorizationlist.setTitle('权限管理');
//            Ext.getCmp('centercard').insert(17, authorizationlist);
//        }
//        Ext.getCmp('operationCard').getLayout().setActiveItem('operationStateDisplay');
//        var id = null;
//        if (GROUP_ID == 0) {
//            userMode = 2;
//        } else if (GROUP_ID > 0 && GROUP_ID < 3) {
//            me.userMode = 0;
//            id = GROUP_ID;
//        } else if (GROUP_ID >= 3) {
//            me.userMode = 1;
//            id = USER_ID;
//            Ext.getCmp('memberList').setTitle('用户选择(单选)');
//        }
//        var new_params = {
//            userMode: me.userMode,
//            id: id
//        };
//        if (me.accessStoreLoadFinished && me.operationStoreLoadFinished 
//                && me.memberStoreLoadFinished && me.getAuthorizationFinished) {
//            me.memberStoreLoadFinished = false;
//            authorizationlist.memberStore.on('beforeload', function(store, options) {
//                Ext.apply(store.proxy.extraParams, new_params);
//            });
//            authorizationlist.memberStore.load({
//                callback: function(records, options, success) {
//                    if (success) {
//
//                        if (records.length == 0) {
//                            var emptyMsg = null;
//                            if (me.userMode == 0) {
//                                emptyMsg = '角色栏为空，系统初始化有误，请联系软件供应商解决该问题！';
//                            } else {
//                                emptyMsg = '用户栏为空，请先添加用户后再执行权限管理操作！';
//                            }
//                            messageBox.alert("提醒", emptyMsg, function() {
//                                Ext.getCmp('accessRightsTree').getRootNode().removeAll();
//                                Ext.getCmp('operationRightsTree').getRootNode().removeAll();
//                            });
//                        } else {
//                            if (me.userMode == 1) {
//                                me.loadUserTree(new_params);
//
//                            } else {
//                                Ext.getCmp('memberList').getSelectionModel().select(0, false, false);
//                            }
//                        }
//                    }
//                    me.memberStoreLoadFinished = true;
//                }
//
//            });
//
//        }
//
//        Ext.getCmp('centercard').getLayout().setActiveItem('authorizationlist');
//        return authorizationlist;
//    },
//    loadUserTree: function(new_params) {
//        var me = this;
//        me.accessStoreLoadFinished = false;
//        me.operationStoreLoadFinished = false;
//        Ext.getCmp('accessRightsTree').getStore().on('beforeload', function(store, options) {
//            Ext.apply(store.proxy.extraParams, new_params);
//        });
//        Ext.getCmp('accessRightsTree').getStore().load({
//            callback: function(records, options, success) {
//                if (success) {
//                    if (records.length == 0) {
//                        me.noAccessTree = true;
//                        Ext.getCmp('saveAuthorization').setDisabled(true);
//                        Ext.getCmp('resetAuthorization').setDisabled(true);
//                        Ext.getCmp('operationStateDisplay').setValue(' ');
//                    } else {
//                        me.noAccessTree = false;
//                        Ext.getCmp('saveAuthorization').setDisabled(false);
//                        Ext.getCmp('resetAuthorization').setDisabled(false);
//                        Ext.getCmp('authorizationTipText').setValue(' ');
//                        Ext.getCmp('operationStateDisplay').setValue('<h3>请点击访问权限以查看操作权限<h3>');
//                    }
//                }
//                me.accessStoreLoadFinished = true;
//                if (me.accessStoreLoadFinished && me.operationStoreLoadFinished && me.memberStoreLoadFinished) {
//                    Ext.getCmp('memberList').getSelectionModel().select(0, false, false);
//                }
//            }
//        });
//        Ext.getCmp('operationRightsTree').getStore().on('beforeload', function(store, options) {
//            Ext.apply(store.proxy.extraParams, new_params);
//        });
//        Ext.getCmp('operationRightsTree').getStore().load({
//            callback: function(records, options, success) {
//                if (success) {
//                    var rootNode = Ext.getCmp('operationRightsTree').getRootNode();
//                    me.nodeList = new Array();
//                    rootNode.eachChild(function(child) {
//                        me.nodeList.push(child);
//                    });
//                }
//                me.operationStoreLoadFinished = true;
//
//                if (me.accessStoreLoadFinished && me.operationStoreLoadFinished && me.memberStoreLoadFinished) {
//                    Ext.getCmp('memberList').getSelectionModel().select(0, false, false);
//                }
//            }
//        });
//        me.memberStoreLoadFinished = true;
//        if (me.accessStoreLoadFinished && me.operationStoreLoadFinished && me.memberStoreLoadFinished) {
//            Ext.getCmp('memberList').getSelectionModel().select(0, false, false);
//        }
//    },
//    nodeCheckChange: function(node, checked) {
//        var me = this;
//        if (!node.data.leaf) {
//            node.expand();
//            node.cascadeBy(function(node) {
//                node.set('checked', checked);
//            });
//        }
//        me.changeNodeParentType(node);
//    },
//    changeNodeParentType: function(node) {
//        var me = this;
//        if (!node.isRoot()) {
//            var parentNode = node.parentNode;
//            var flag = 0;
//            parentNode.eachChild(function(n) {
//                if (n.data.checked) {
//                    flag++;
//                }
//            });
//            if (flag == parentNode.childNodes.length) {
//                parentNode.set('checked', true);
//            } else {
//                parentNode.set('checked', false);
//            }
//            me.changeNodeParentType(parentNode);
//        }
//    },
//    recoverOperationAuthorizationTree: function() {
//        var me = this;
//        var operationRootNode = Ext.getCmp('operationRightsTree').getRootNode();
//        operationRootNode.removeAll();
//        for ( var i = 0; i < me.nodeList.length; i++) {
//            operationRootNode.appendChild(me.nodeList[i]);
//        }
//    }
//});
