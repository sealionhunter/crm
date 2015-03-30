Ext.define('CRM.store.customerManagement.customerProfiles.CoopResume', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.CoopResume',
    pageSize: 15,
    proxy: {
        type: 'ajax',
        url: 'GetCoopResume.action',
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