Ext.define('CRM.store.customerManagement.customerProfiles.CustomerName', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.CustomerName',
    proxy: {
        type: 'ajax',
        url: 'getCustomerSource.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});