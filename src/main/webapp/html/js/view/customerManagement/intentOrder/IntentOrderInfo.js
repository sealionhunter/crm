Ext.define('CRM.view.customerManagement.intentOrder.IntentOrderInfo', {
    extend: 'Ext.panel.Panel',
    defaultType: 'displayfield',
    alias: 'widget.intentOrderInfo',
    id: 'intentOrderInfo',
    itemId: 'intentOrderInfo',
    layout: 'border',
    border: false,
    autoScroll: true,
    items: [ {
        region: 'center',
        xtype: 'intentOrderList'
    }, {
        xtype: 'intentOrderDetail'
    } ]
});