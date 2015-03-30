Ext.define('CRM.store.customerManagement.competitor.CompetitorInfoNow', {
    extend: 'CRM.store.commonStore',
    // alias : 'widget.competitorInfoNow',
    model: 'CRM.model.customerManagement.competitor.CompetitorInfoModel',
    pageSize: 25,
    proxy: {
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