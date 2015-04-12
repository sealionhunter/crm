Ext.define('CRM.store.customerManagement.customerProfiles.LeaderAdvice', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.LeaderAdvice',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllLeaderAdvice.action',
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