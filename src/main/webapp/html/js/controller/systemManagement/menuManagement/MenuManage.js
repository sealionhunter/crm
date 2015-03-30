Ext.define('CRM.controller.systemManagement.menuManagement.MenuManage', {
    extend: 'Ext.app.Controller',
    stores: [ 'systemManagement.menuManagement.MenuTreeNodes', 'systemManagement.menuManagement.MenuOperationNodes' ],
    models: [],
    views: [ 'systemManagement.menuManagement.MenuManage', 'systemManagement.menuManagement.TreeEdit' ],
    init: function() {
        this.control({
            'menumanage treepanel[id=menuTreePanel]': {
                checkchange: this.nodeCheckChangeAlready,
                itemclick: this.showMenuOperationTree,
                scrollershow: utils.scrollershow,
                itemcontextmenu: this.operateMenuTree
            },
            'menumanage treepanel[id=menuOperationTree]': {
                checkchange: this.nodeCheckChange,
                scrollershow: utils.scrollershow
            },
            'menumanage button[id=editMenuText]': {
                click: this.showEditTextWindow
            },
            'menumanage button[id=saveTreeMenu]': {
                click: this.saveTreeMenu
            },
            'treeedit button[action=cancel]': {
                click: utils.winClose
            },
            'treeedit button[action=save]': {
                click: this.editText
            },
            'treeedit textfield[name=text]': {
                blur: utils.trimSpace
            }
        });
    },
    userMode: null,
    nodeList: null,
    rootNode: null,
    menuNodes: null,
    operationNodes: null,
    menuNodeNotChecked: false,
    waitToEditTextNodeOnMenuTreePanel: null,
    loadAuthorization: function() {
        var me = this;
        Ext.Ajax.request({
            url: 'getMemberAuthorization.action',
            params: {
                userMode: 0,
                id: 1
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
    loadMenuTree: function() {
        var me = this;
        var menuTreePanel = Ext.getCmp('menuTreePanel');
        var id = GROUP_ID;
        me.userMode = 2;
        Ext.getCmp('saveTreeMenu').setDisabled(false);
        var new_params = {
            userMode: me.userMode,
            id: id
        };
        var menuStore = menuTreePanel.getStore();
        menuStore.on('beforeload', function(store, options) {
            Ext.apply(store.proxy.extraParams, new_params);
        });
        menuStore.load({
            callback: function(records, options, success) {
                // 去除菜单管理节点
                if (success) {
                    var systemManageNode = menuStore.getNodeById(4);
                    var menuManageNode = menuStore.getNodeById(45);
                    if (me.menuNodes != '') {
                        systemManageNode.removeChild(menuManageNode);
                        for ( var i = 0; i < me.menuNodes.length; i++) {
                            var node = menuStore.getNodeById(me.menuNodes[i]);
                            if (typeof (node) != 'undefined') {
                                node.set('checked', true);
                                me.nodeCheckChange(node, true);
                            }
                        }
                    } else {
                        systemManageNode.removeChild(menuManageNode);
                        var rootNode = menuTreePanel.getRootNode();
                        rootNode.cascadeBy(function(node) {
                            node.set('checked', true);
                        });
                    }
                }
            }
        });
        var menuOperationTree = Ext.getCmp('menuOperationTree');
        var operationStore = menuOperationTree.getStore();
        operationStore.on('beforeload', function(store, options) {
            Ext.apply(store.proxy.extraParams, new_params);
        });
        operationStore.load({
            callback: function(records, options, success) {
                if (success) {
                    var rootNode = menuOperationTree.getRootNode();
                    if (me.operationNodes != '') {
                        for ( var i = 0; i < me.operationNodes.length; i++) {
                            var node = operationStore.getNodeById(me.operationNodes[i]);
                            if (typeof (node) != 'undefined') {
                                node.set('checked', true);
                                me.nodeCheckChange(node, true);
                            }
                        }
                    } else {
                        rootNode.cascadeBy(function(node) {
                            node.set('checked', true);
                        });
                    }
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
    showMenuOperationTree: function(tree, record) {
        var me = this;
        me.waitToEditTextNodeOnMenuTreePanel = record;
        Ext.getCmp('editMenuText').setDisabled(false);
        me.menuNodeNotChecked = false;
        me.rootNode = Ext.getCmp('menuOperationTree').getStore().getRootNode();
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
                        me.nodeCheckChange(nodeList[i], false);
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
            Ext.getCmp('menuOperationCard').getLayout().setActiveItem('menuOperationTree');
        } else if (me.menuNodeNotChecked) {
            Ext.getCmp('menuOperationState').setValue('<h3>没有访问权限，请先赋予该项访问权限</h3>');
            Ext.getCmp('menuOperationCard').getLayout().setActiveItem('menuOperationState');
        } else {
            Ext.getCmp('menuOperationState').setValue('<h3>无操作权限，只有访问权限可赋予</h3>');
            Ext.getCmp('menuOperationCard').getLayout().setActiveItem('menuOperationState');
        }
    },
    saveTreeMenu: function() {
        var me = this;
        me.recoverMenuOperationTree();
        var menuTreeCheckedNodes = Ext.getCmp('menuTreePanel').getChecked();
        var menuTreeNodeIDs = new Array();
        Ext.Array.each(menuTreeCheckedNodes, function(node) {
            if (node.data.leaf) {
                menuTreeNodeIDs.push(node.internalId);
            }
        });
        var operationCheckedNodes = Ext.getCmp('menuOperationTree').getChecked();
        var operationNodeIDs = new Array();
        Ext.Array.each(operationCheckedNodes, function(node) {
            if (node.data.leaf) {
                operationNodeIDs.push(node.internalId);
            }
        });
        var arrId = menuTreeNodeIDs.concat(operationNodeIDs);
        function doSave() {
            // 超级管理员的groupID为1
            var groupID = 1;
            Ext.Ajax.request({
                url: 'saveAuthorization.action',
                params: {
                    userMode: me.userMode,
                    jsonString: arrId,
                    id: groupID,
                    userID: USER_ID
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    me.menuNodes = menuTreeNodeIDs;
                    me.operationNodes = operationNodeIDs;
                    var responseText = Ext.decode(response.responseText) || '';
                    if (responseText.validate == false) {
                        if (responseText.savedAuthorizationIds) {
                            messageBox.alert('提示', responseText.savedAuthorizationIds);
                        }
                    } else {
                        messageBox.alert('提示', "保存菜单成功！");
                    }
                },
                failure: function(response) {
                    messageBox.alert('提示', "保存出错，请重新保存或联系管理员！");
                }
            });
        }
        if (arrId.length == 0) {
            box.confirm('提示', '您确定当前不选定菜单项？', function(button) {
                if (button == 'yes') {
                    doSave();
                }
            });
        } else {
            doSave();
        }
    },
    viewInit: function() {
        var me = this;
        var menuManage = Ext.getCmp('menumanage');
        if (typeof (menuManage) == 'undefined') {
            menuManage = Ext.widget('menumanage');
            Ext.getCmp('centercard').insert(1, menuManage);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('menumanage');
        menuManage.down('button[id=editMenuText]').setDisabled(true);
        me.loadAuthorization();
        return menuManage;
    },
    nodeCheckChangeAlready: function(node, checked) {
        var me = this;
        me.nodeCheckChange(node, checked);
        var menuTreePanel = Ext.getCmp('menuTreePanel');
        me.showMenuOperationTree(menuTreePanel, node);
    },
    nodeCheckChange: function(node, checked) {
        var me = this;
        if (!node.data.leaf) {
            node.expand();
            node.cascadeBy(function(node) {
                node.set('checked', checked);
            });
        }
        me.changeNodeParentType(node);
    },
    changeNodeParentType: function(node) {
        var me = this;
        if (!node.isRoot()) {
            var parentNode = node.parentNode;
            var flag = 0;
            parentNode.eachChild(function(n) {
                if (n.data.checked) {
                    flag++;
                }
            });
            if (flag == parentNode.childNodes.length) {
                parentNode.set('checked', true);
            } else {
                parentNode.set('checked', false);
            }
            me.changeNodeParentType(parentNode);
        }
    },
    operateMenuTree: function(view, record, item, index, e, eOpts) {
        e.preventDefault();
        e.stopEvent();
        Ext.create('Ext.menu.Menu', {
            float: true,
            items: [ {
                text: '编辑名称',
                handler: function() {
                    this.up('menu').hide();
                    var treeEdit = Ext.getCmp('treeedit');
                    if (typeof (treeEdit) == 'undefined') {
                        treeEdit = Ext.widget('treeedit');
                    }
                    var form = treeEdit.down('form');
                    form.loadRecord(record);
                }
            } ]
        }).showAt(e.getXY());
    },
    showEditTextWindow: function(button) {
        var me = this;
        var treeEdit = Ext.getCmp('treeedit');
        if (typeof (treeEdit) == 'undefined') {
            treeEdit = Ext.widget('treeedit');
        }
        var form = treeEdit.down('form');
        form.loadRecord(me.waitToEditTextNodeOnMenuTreePanel);
    },
    editText: function(button) {
        var win = button.up('window');
        var form = win.down('form');
        var id = win.down('hiddenfield[name=id]').getValue();
        var text = win.down('textfield[name=text]').getValue();
        if (text != '' && form.getForm().isValid()) {
            Ext.Ajax.request({
                url: 'editMenuText.action',
                params: {
                    id: id,
                    text: text
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var treeStore = Ext.getCmp('menuTreePanel').getStore();
                    treeStore.load({
                        callback: function(records, options, success) {
                            // 去除菜单管理节点
                            if (success) {
                                var menuManageNode = treeStore.getNodeById(45);
                                menuManageNode.remove(true);
                            }
                        }
                    });
                },
                failure: function() {
                    messageBox.alert('提示', '提交失败！');
                }
            });
            win.close();
        } else {
            messageBox.alert('提示', '菜单标题输入格式不正确！');
        }
    },
    recoverMenuOperationTree: function() {
        var me = this;
        var operationRootNode = Ext.getCmp('menuOperationTree').getRootNode();
        // Modified for EXTJS 4.2 start 20150302
//        operationRootNode.removeAll();
        utils.removeAllChild.apply(operationRootNode);
        // Modified for EXTJS 4.2 end 20150302
        for ( var i = 0; i < me.nodeList.length; i++) {
            operationRootNode.appendChild(me.nodeList[i]);
        }
        if (!Ext.getCmp('menuOperationTree').isVisible()) {
            Ext.getCmp('menuOperationCard').getLayout().setActiveItem('menuOperationTree');
        }
    }
});