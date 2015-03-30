Ext.define('CRM.store.customerManagement.customerProfiles.Customer', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.Customer',
    pageSize: 25,
    id: 'CustomerStore',
    proxy: {
        type: 'ajax',
        url: 'getAllCustomer.action',
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