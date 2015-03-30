Ext.define('CRM.store.customerManagement.cooperator.CooperatorNameCombo', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.code.Code',
    proxy: {
        type: 'ajax',
        url: 'getAllCopNameSetAnalysis.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});
