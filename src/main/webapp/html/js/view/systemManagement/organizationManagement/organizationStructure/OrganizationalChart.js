Ext.define('CRM.view.systemManagement.organizationManagement.organizationStructure.OrganizationalChart', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.organizationalChart',
    id: 'organizationalChart',
    layout: 'fit',
    bodyStyle: {
        background: '#D9D9F3'
    },
    autoScroll: true,
    initComponent: function() {
        this.callParent(arguments);
    }
});