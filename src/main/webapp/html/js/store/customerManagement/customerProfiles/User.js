Ext.define('CRM.store.customerManagement.customerProfiles.User', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.UserInfo',
    proxy: {
        type: 'ajax',
        url: 'getUser.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});