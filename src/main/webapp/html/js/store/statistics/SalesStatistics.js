Ext.define('CRM.store.statistics.SalesStatistics', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.statistics.salesStatistics.SalesStatistics',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'countSalesEvents.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});