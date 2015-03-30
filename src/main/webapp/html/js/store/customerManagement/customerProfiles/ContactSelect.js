Ext.define('CRM.store.customerManagement.customerProfiles.ContactSelect', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.ContactSelect',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getContactSelect.action',
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