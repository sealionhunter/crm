Ext.define('CRM.store.customerManagement.competitor.CompetitorInfoNew', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.competitor.CompetitorInfoModel',
    pageSize: 25,
    proxy: {
        // 异步获取数据，这里的URL可以改为任何动态页面，只要返回JSON数据即可
        type: 'ajax',
        url: 'getAllCompetitor.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total' // 总数。 默认值='total'
        }
    }
});