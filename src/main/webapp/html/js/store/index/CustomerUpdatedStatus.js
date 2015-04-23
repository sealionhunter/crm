Ext.define('CRM.store.index.CustomerUpdatedStatus', {
//    extend: 'Ext.data.Store',
    extend: 'CRM.store.commonStore',
    pageSize: 15,
    fields:[
        {name: 'days', type: 'int'},
        {name: 'customerName', type: 'string'}
    ],
    proxy: {
        type: 'ajax',
        url: 'getCustomerUpdatedStatus.action',
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