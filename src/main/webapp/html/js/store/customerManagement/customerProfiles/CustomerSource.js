Ext.define('CRM.store.customerManagement.customerProfiles.CustomerSource', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.CustomerSource',
    proxy: {
        type: 'ajax',
        url: 'CustomerSourceList.action',
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