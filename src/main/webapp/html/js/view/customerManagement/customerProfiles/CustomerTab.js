Ext.define('CRM.view.customerManagement.customerProfiles.CustomerTab', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.customertab',
    id: 'customertab',
    itemId: 'customertab',
    activeTab: 0,
    border: false,
    minWidth: 100,
    autoScroll: true,
    style: 'background-color:#dfe8f6;',
    initComponent: function() {
        this.items = [ {
            title: '联系人',
            id: 'customercontacttab',
            layout: 'fit',
            xtype: 'panel',
            border: false
        }, {
            title: '客户联系',
            id: 'customerlianxitab',
            layout: 'fit',
            xtype: 'panel',
            border: false
        }, {
            title: '客户联系历史',
            id: 'customerhistorytab',
            layout: 'fit',
            xtype: 'panel',
            border: false
        }, {
            title: '领导建议',
            id: 'leaderadvicetab',
            layout: 'fit',
            xtype: 'panel',
            border: false
        } ];
        this.callParent(arguments);
    }

});