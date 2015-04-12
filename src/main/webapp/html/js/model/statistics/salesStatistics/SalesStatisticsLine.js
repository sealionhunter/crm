Ext.define('CRM.model.statistics.salesStatistics.SalesStatisticsLine', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'state',
        type: 'string'
    }, {
        name: 'rightCount',
        type: 'int'
    }, {
        name: 'leftCount',
        type: 'int'
    }, {
        name: 'count',
        type: 'int'
    }, {
        name: 'allCount',
        type: 'int'
    } ]
});