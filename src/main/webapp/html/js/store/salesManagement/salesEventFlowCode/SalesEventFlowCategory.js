Ext.define('CRM.store.salesManagement.salesEventFlowCode.SalesEventFlowCategory', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.salesManagement.salesEventFlowCode.SalesEventFlowCategory',
    proxy: {
        type: 'ajax',
        url: 'salesEventFlowCategory.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});