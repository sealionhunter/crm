Ext.define('CRM.store.index.ContactTrackStore', {
    extend: 'Ext.data.Store',
    model: 'CRM.model.customerManagement.contactTrack.ContactModel',
    proxy: {
        type: 'ajax',
        url: 'getContactTrackInfo.action',
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