Ext.define('CRM.view.systemManagement.groupManagement.GroupManage', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.groupmanage',
    title: '角色管理',
    id: 'groupmanage',
    layout: 'border',
    border : false,
    frame: false,
    initComponent: function() {
        this.groupStore = Ext.create('CRM.store.systemManagement.groupManagement.Group');
        this.groupMemberStore = Ext.create('CRM.store.systemManagement.groupManagement.GroupInfo');
        this.items = [ {
            region: 'west',
            xtype: 'gridpanel',
            id: 'groupList',
            title: '角色列表（单选）',
            border : false,
            minWidth: 50,
            tbar: [ '->', {
                text: '添加',
                id: 'groupAdd',
                action: 'groupAddBtn'
            }, {
                text: '编辑',
                id: 'groupEdit',
                action: 'groupEditBtn',
                disabled: true
            }, {
                text: '删除',
                id: 'groupDelete',
                action: 'groupDeleteBtn',
                disabled: true
            } ],
            width: '30%',
            store: this.groupStore,
            selType: 'checkboxmodel',
            multiSelect: false,
            hideHeaders: true,
            border: true,
            columns: [ {
                header: '角色编号',
                dataIndex: 'groupID',
                flex: 1,
                hidden: true
            }, {
                header: '角色名称',
                dataIndex: 'groupName',
                flex: 1,
                minWidth: 100
            } ]
        }, {
            region: 'center',
            xtype: 'gridpanel',
            id: 'groupMembers',
            title: '角色成员列表',
            border : false,
            sortableColumns: false,
            tbar: [ '->', {
                text: '成员管理',
                id: 'groupMemberEdit',
                action: 'groupMemberEdit',
                disabled: true
            } ],
            store: this.groupMemberStore,
            border: true,
            columns: [ {
                header: '用户名',
                dataIndex: 'userName',
                minWidth: 150,
                flex: 1,
                renderer: function(value, metadata, record, rowIndex) {
                    return Ext.String.htmlEncode(value);
                }
            }, {
                header: '姓名',
                dataIndex: 'realName',
                minWidth: 150,
                flex: 1,
                renderer: function(value, metadata, record, rowIndex) {
                    return Ext.String.htmlEncode(value);
                }
            }, {
                header: '职务',
                dataIndex: 'job',
                minWidth: 150,
                flex: 1,
                renderer: function(value, metadata, record, rowIndex) {
                    return Ext.String.htmlEncode(value);
                }
            } ],

            dockedItems: [ {
                xtype: 'pagingtoolbar',
                store: this.groupMemberStore,
                dock: 'bottom',
                displayInfo: true
            } ]
        } ];
        this.callParent(arguments);
    }
});
