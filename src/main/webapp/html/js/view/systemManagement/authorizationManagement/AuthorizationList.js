Ext.define('CRM.view.systemManagement.authorizationManagement.AuthorizationList', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.authorizationlist',
    title: '权限管理',
    id: 'authorizationlist',
    layout: 'border',
    frame: false,
    tbar: [ {
        xtype: 'displayfield',
        name: 'authorizationTipText',
        id: 'authorizationTipText',
        width: 500,
        value: '<font color="red">请谨慎管理权限！</font>'
    }, {
        xtype: 'tbfill'
    }, {
        xtype: 'button',
        text: '保存',
        name: 'saveAuthorization',
        id: 'saveAuthorization',
        disabled: true,
        width: 70
    }, {
        xtype: 'button',
        text: '重置',
        name: 'resetAuthorization',
        id: 'resetAuthorization',
        disabled: true,
        width: 70
    } ],
    initComponent: function() {
        this.accessRightsTreeStore = Ext.create('CRM.store.systemManagement.authorizationManagement.AccessRightsTree');
        this.operationRightsTreeStore = Ext.create('CRM.store.systemManagement.authorizationManagement.OperationRightsTree');
        this.memberStore = Ext.create('CRM.store.systemManagement.authorizationManagement.Member');
        this.showGroupName = function(value) {
            switch (value) {
            case 5:
                return '业务人员';
            case 4:
                return '业务经理';
            case 3:
                return '上级管理者';
            case 2:
                return '';
            case 1:
                return '';
            }
        };
        this.items = [ {
            region: 'west',
            xtype: 'gridpanel',
            id: 'memberList',
            title: '用户角色选择(单选)',
            width: '50%',
            store: this.memberStore,
            selType: 'checkboxmodel',
            multiSelect: false,
            hideHeaders: true,
            border: true,
            columns: [ {
                header: '用户',
                dataIndex: 'memberName',
                flex: 2
            }, {
                header: '用户类型',
                dataIndex: 'memberGroupID',
                flex: 1,
                renderer: this.showGroupName
            } ]
        }, {
            region: 'center',
            xtype: 'treepanel',
            title: '访问权限',
            collapsible: false,
            border: true,
            store: this.accessRightsTreeStore,
            rootVisible: false,
            autoScroll: true,
            animate: true,
            multiSelect: true,
            enableDD: false,
            containerScroll: true,
            id: 'accessRightsTree',
            useArrows: false
        }, {
            region: 'east',
            xtype: 'panel',
            title: '操作权限',
            layout: 'card',
            border: true,
            width: '80%',
            activeItem: 0,
            id: 'operationCard',
            items: [ {
                xtype: 'treepanel',
                collapsible: false,
                store: this.operationRightsTreeStore,
                border: false,
                rootVisible: false,
                autoScroll: true,
                animate: false,
                multiSelect: true,
                useArrows: false,
                containerScroll: true,
                id: 'operationRightsTree',
                viewConfig: {
                    loadMask: false
                },
                itemId: 'operationRightsTree'
            }, {
                xtype: 'displayfield',
                value: ' ',
                itemId: 'operationStateDisplay',
                id: 'operationStateDisplay'
            } ]
        } ];
        this.callParent(arguments);
    }
});
