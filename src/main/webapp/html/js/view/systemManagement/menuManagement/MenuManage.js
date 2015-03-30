Ext.define('CRM.view.systemManagement.menuManagement.MenuManage', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.menumanage',
    id: 'menumanage',
    title: '菜单编辑',
    layout: 'border',
    border: false,
    autoScroll: true,
    initComponent: function() {
        this.menuTreeStore = Ext.create('CRM.store.systemManagement.menuManagement.MenuTreeNodes');
        this.menuOperationStore = Ext.create('CRM.store.systemManagement.menuManagement.MenuOperationNodes');
        this.items = [ {
            region: 'center',
            xtype: 'treepanel',
            id: 'menuTreePanel',
            title: '菜单管理',
            collapsible: false,
            border: true,
            store: this.menuTreeStore,
            rootVisible: false,
            autoScroll: true,
            animate: true,
            multiSelect: true,
            enableDD: false,
            containerScroll: true,
            useArrows: true
        }, {
            region: 'east',
            xtype: 'panel',
            title: '操作权限',
            layout: 'card',
            border: true,
            width: '80%',
            id: 'menuOperationCard',
            items: [ {
                xtype: 'treepanel',
                collapsible: false,
                store: this.menuOperationStore,
                border: false,
                rootVisible: false,
                autoScroll: true,
                animate: false,
                multiSelect: true,
                useArrows: true,
                containerScroll: true,
                id: 'menuOperationTree',
                viewConfig: {
                    loadMask: false
                },
                itemId: 'menuOperationTree'
            }, {
                xtype: 'displayfield',
                value: ' ',
                itemId: 'menuOperationState',
                id: 'menuOperationState'
            } ]
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'menuManageToolBar',
            defaults: {
                border: false
            },
            items: [ '->', {
                text: '编辑名称',
                id: 'editMenuText',
                disabled: true
            }, {
                text: '保存菜单',
                id: 'saveTreeMenu',
                disabled: true
            } ]
        } ];
        this.callParent(arguments);
    }
});
