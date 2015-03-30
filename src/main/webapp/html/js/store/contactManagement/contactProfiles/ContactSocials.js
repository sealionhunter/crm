Ext.define('CRM.store.contactManagement.contactProfiles.ContactSocials', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.contactManagement.contactProfiles.ContactSocial',
    remoteSort: true,
    storeId: 'social_store',
    actionMethods: 'get',
    proxy: {
        type: 'ajax',
        url: 'getAllSocialInfo.action',
        reader: {
            type: 'json',
            root: 'contactSocial',
            totalProperty: 'total'

        }
    }
});