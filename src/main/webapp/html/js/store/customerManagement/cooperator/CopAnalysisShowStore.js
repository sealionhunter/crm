Ext.define('CRM.store.customerManagement.cooperator.CopAnalysisShowStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.cooperator.CooperatorAnalysisModel',
    autoLoad: false,
    pageSize: 15,
    proxy: {
        type: 'ajax',
        url: 'getCopAnalysisForShow.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});