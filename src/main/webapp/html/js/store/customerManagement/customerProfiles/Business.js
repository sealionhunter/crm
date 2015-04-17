Ext.define('CRM.store.customerManagement.customerProfiles.Business', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.Business',
    proxy: {
        type: 'ajax',
        url: 'getAllBusiness.action',
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