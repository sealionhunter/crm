Ext.define('CRM.view.customerManagement.customerProfiles.SourceInfo', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.sourceinfo',
    id: 'sourceinfo',
    itemId: 'sourceinfo',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'sourcelist'
    }, {
        xtype: 'sourcedetail'
    } ]

});