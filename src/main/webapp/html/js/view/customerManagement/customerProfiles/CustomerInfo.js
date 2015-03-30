Ext.define('CRM.view.customerManagement.customerProfiles.CustomerInfo', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.customerinfo',
    id: 'customerinfo',
    itemId: 'customerinfo',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
//        margin: '0 2 0 0',
        xtype: 'customerlist'
//    }, {
//        xtype: 'customerdatail'
    } ]

});