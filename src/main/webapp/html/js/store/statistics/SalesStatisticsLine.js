Ext.define('CRM.store.statistics.SalesStatisticsLine', {
    extend: 'Ext.data.Store',
    model: 'CRM.model.statistics.salesStatistics.SalesStatisticsLine',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        url: 'getCustomerCount.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});