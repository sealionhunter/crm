Ext.define('CRM.view.systemManagement.organizationManagement.projectTeamManagement.TeamMemberEdit', {
    extend: 'Ext.window.Window',
    alias: 'widget.TeamMemberEdit',
    id: 'TeamMemberEdit',
    width: 830,
    height: 402,
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    layout: 'border',
    initComponent: function() {
        this.addedProjectTeamMembersStore = Ext.create('CRM.store.systemManagement.organizationManagement.projectTeamManagement.AddedMembers');
        this.canAddProjectTeamMembersStore = Ext.create('CRM.store.systemManagement.organizationManagement.projectTeamManagement.CanAddMembers');
        this.items = [ {
            region: 'west',
            xtype: 'MemberList',
            id: 'canAddProjectTeamMembers',
            width: 380,
            store: this.canAddProjectTeamMembersStore
        }, {
            region: 'center',
            xtype: 'panel',
            id: 'teamMemberEditWindowCenterPanel',
            layout: {
                type: 'vbox',
                pack: 'center',
                padding: 5,
                align: 'stretch'
            },
            defaults: {
                xtype: 'button',
                margins: '0 0 10 0'
            },
            items: [ {
                text: '添加 > ',
                action: 'addMembers'
            }, {
                xtype: 'tbspacer',
                flex: 1
            }, {
                xtype: 'tbspacer',
                flex: 1
            }, {
                text: '< 删除',
                action: 'removeMembers'
            } ]
        }, {
            region: 'east',
            xtype: 'MemberList',
            id: 'addedProjectTeamMembers',
            width: 380,
            store: this.addedProjectTeamMembersStore
        } ];
        this.buttons = [ {
            text: '关闭',
            action: 'close'
        } ];
        this.callParent(arguments);
    }
});