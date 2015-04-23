Ext.define('CRM.view.customerManagement.customerProfiles.BusinessCard', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.businesscard',
    id: 'businesscard',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'businesslist'
    }]
});