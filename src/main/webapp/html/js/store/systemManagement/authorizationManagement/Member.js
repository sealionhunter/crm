Ext.define('CRM.store.systemManagement.authorizationManagement.Member', {
    extend: 'CRM.store.commonStore',
    autoLoad: false,
    model: 'CRM.model.systemManagement.authorizationManagement.Member',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getMembers.action',
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
