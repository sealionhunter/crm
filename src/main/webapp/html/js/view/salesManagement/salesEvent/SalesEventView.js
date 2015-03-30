Ext.define('CRM.view.salesManagement.salesEvent.SalesEventView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.transactionMainView',
    id: 'transactionManagementView',
    itemId: 'transactionManagementView',
    layout: 'border',
    items: [ {
        region: 'center',
        xtype: 'salesEventList'
    }, {
        region: 'east',
        xtype: 'salesEventDetail'
    } ]
});