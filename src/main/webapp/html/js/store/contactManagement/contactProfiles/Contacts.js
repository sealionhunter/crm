Ext.define('CRM.store.contactManagement.contactProfiles.Contacts', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.contactManagement.contactProfiles.Contact',
    proxy: {
        type: 'ajax',
        url: 'ContactList.action',
        autoLoad: true,
        actionMethods: {
            read: 'POST'
        },
        params: {
            searchFlag: 0
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total' // 总数。 默认值='total'
        }
    }
});