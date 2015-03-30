Ext.define('CRM.view.customerManagement.cooperator.CooperatorBody', {
    extend: 'Ext.panel.Panel',
    defaultType: 'displayfield',
    alias: 'widget.cooperatorbody',
    id: 'cooperatorbody',
    itemId: 'cooperatorbody',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        margin: '0 2 0 0',
        xtype: 'cooperatorlist'
    }, {
        xtype: 'cooperatordetail'
    } ]

});
