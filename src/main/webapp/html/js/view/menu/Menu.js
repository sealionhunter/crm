Ext.define('CRM.view.menu.Menu', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.crmmenu',
    title: '菜单',
    collapsible: true,
    store: 'systemManagement.authorizationManagement.AccessRightsTree',
    width: 200,
    id: 'menuTree',
    rootVisible: false,
    minWidth: 150,
    margins: '5 5 5 5',
    height: 800,
    layout: 'fit',
    useArrows: true,
    autoScroll: true,
    initComponent: function() {
        this.dockedItems = [ {
            xtype: 'toolbar',
            items: [ {
                text: '展开',
                action: 'expand'
            }, {
                text: '收起',
                action: 'collapse'
            } ]
        } ];
        this.callParent(arguments);
    }
});
