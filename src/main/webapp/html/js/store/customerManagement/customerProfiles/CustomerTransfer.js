Ext.define('CRM.store.customerManagement.customerProfiles.CustomerTransfer', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.Customer',
    pageSize: 20,
    id: 'CustomerStore',
    proxy: {
        type: 'ajax',
        url: 'getCustomerForTransfer.action',
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