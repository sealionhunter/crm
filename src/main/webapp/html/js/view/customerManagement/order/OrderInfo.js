Ext.define('CRM.view.customerManagement.order.OrderInfo', {
    extend: 'Ext.panel.Panel',
    defaultType: 'displayfield',
    alias: 'widget.orderinfo',
    id: 'orderinfo',
    itemId: 'orderinfo',
    layout: 'border',
    border: false,
    items: [ {
        region: 'center',
        xtype: 'orderList'
    }, {
        xtype: 'orderDetail'
    } ]
});