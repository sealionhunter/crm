Ext.define('CRM.view.systemManagement.groupManagement.GroupMemberEdit', {
    extend: 'Ext.window.Window',
    alias: 'widget.GroupMemberEdit',
    width: 900,
    height: 420,
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    layout: {
        type: 'hbox',
        align: 'stretch',
        padding: 5
    },
    defaults: {
        width: 400
    },
    initComponent: function() {
        this.addedGroupMembersStore = Ext.create('CRM.store.systemManagement.groupManagement.AddedMembers');
        this.canAddGroupMembersStore = Ext.create('CRM.store.systemManagement.groupManagement.CanAddMembers');
        this.items = [ {
            xtype: 'groupMemberList',
            id: 'addedGroupMembers',
            store: this.canAddGroupMembersStore
        }, {
            layout: {
                type: 'vbox',
                padding: '5',
                pack: 'center',
                align: 'stretch'
            },
            flex: 0.6,
            id: 'operation',
            border: false,
            defaults: {
                margins: '0 0 10 0'
            },
            items: [ {
                xtype: 'button',
                text: '右移 >',
                id: 'memberMoveToRight',
                action: 'memberMoveToRightBtn',
                disabled: true
            }, {
                xtype: 'tbspacer',
                flex: 1
            }, {
                xtype: 'tbspacer',
                flex: 1
            }, {
                xtype: 'button',
                text: '< 左移',
                id: 'memberMoveToLeft',
                action: 'memberMoveToLeftBtn',
                disabled: true
            } ]
        }, {
            xtype: 'OtherMemberList',
            id: 'canAddGroupMembers',
            title: '其他角色成员',
            store: this.addedGroupMembersStore
        } ];
        this.buttons = [ {
            text: '关闭',
            action: 'close'
        } ];
        this.callParent(arguments);
    }
});
