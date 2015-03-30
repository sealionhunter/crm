Ext.define('CRM.store.statistics.SalesStatisticsGrid', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.statistics.salesStatistics.SalesStatistics',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'countSalesEvents.action',
        reader: {
            type: 'json',
            root: 'item'
        }
    }
});