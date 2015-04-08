Ext.define('CRM.store.customerManagement.proposalOrContract.ContractOrderStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.order.Order',
    pageSize: 10,
    proxy: {
        type: 'ajax',
        url: 'getOrderByStatus.action',
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