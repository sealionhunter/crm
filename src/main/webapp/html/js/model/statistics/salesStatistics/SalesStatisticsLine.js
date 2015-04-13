Ext.define('CRM.model.statistics.salesStatistics.SalesStatisticsLine', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'name',
        type: 'string'
    }, {
        name: 'count',
        type: 'int'
//    }, {
//        name: 'leftCount',
//        type: 'int'
//    }, {
//        name: 'count',
//        type: 'int'
//    }, {
//        name: 'allCount',
//        type: 'int'
    } ]
});