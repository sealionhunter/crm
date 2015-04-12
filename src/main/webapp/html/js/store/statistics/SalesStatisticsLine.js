Ext.define('CRM.store.statistics.SalesStatisticsLine', {
    extend: 'Ext.data.Store',
    model: 'CRM.model.statistics.salesStatistics.SalesStatisticsLine',
    proxy: {
        type: 'ajax',
        url: 'getCustomerCount.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});