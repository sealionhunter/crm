Ext.define('CRM.store.salesManagement.salesEventFlowCode.SalesEventFlowCode', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.salesManagement.salesEventFlowCode.SalesEventFlowCode',
    proxy: {
        type: 'ajax',
        url: 'salesEventFlowCode.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});