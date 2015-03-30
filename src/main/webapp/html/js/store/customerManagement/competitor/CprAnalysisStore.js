Ext.define('CRM.store.customerManagement.competitor.CprAnalysisStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.competitor.CprAnalysis',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'CprAnalysis.action',
        reader: {
            type: 'json',
            root: 'CprAnalysis',
            // 总数。 默认值='total'
            totalProperty: 'total'
        }
    }
});