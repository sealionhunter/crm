Ext.define('CRM.store.customerManagement.proposalOrContract.ContractStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.proposalOrContract.ContractModel',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllContract.action',
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