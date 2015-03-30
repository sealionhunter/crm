Ext.define('CRM.store.customerManagement.cooperator.CooperatorAnalysisStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.cooperator.CooperatorAnalysisModel',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getCopAnalysisInfoForPage.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});