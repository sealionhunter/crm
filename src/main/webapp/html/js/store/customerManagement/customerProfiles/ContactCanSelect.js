Ext.define('CRM.store.customerManagement.customerProfiles.ContactCanSelect', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.contactManagement.contactProfiles.Contact',
    proxy: {
        type: 'ajax',
        url: 'getContactCanSelect.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});