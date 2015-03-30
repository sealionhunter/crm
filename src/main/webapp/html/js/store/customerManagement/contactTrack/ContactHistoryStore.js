Ext.define('CRM.store.customerManagement.contactTrack.ContactHistoryStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.contactTrack.ContactModel',
    remoteSort: true,
    proxy: {
        type: 'ajax',
        url: 'contactHistoryAction.action',
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