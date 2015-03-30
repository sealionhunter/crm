Ext.define('CRM.store.contactManagement.contactProfiles.ContactFamilys', {
    extend: 'CRM.store.commonStore',
    // alias : 'widget.contactfamilystore',
    model: 'CRM.model.contactManagement.contactProfiles.ContactFamily',
    storeId: 'family_store',
    remoteSort: true,
    actionMethods: 'get',
    proxy: {
        type: 'ajax',
        url: 'getAllFamilyInfo.action',
        reader: {
            type: 'json',
            root: 'contactFamily',
            totalProperty: 'total'

        }
    }
});