Ext.define('CRM.store.salesManagement.salesEvent.SalesEventStore', {
    extend: 'CRM.store.commonStore',
    alias: 'widget.transactionStore',
    model: 'CRM.model.salesManagement.salesEvent.SalesEventListModel',
    // autoLoad : true,
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'salesEventAction.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});