Ext.define('CRM.store.customerManagement.customerProfiles.UserByProjectTeam', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.UserInfo',
    proxy: {
        type: 'ajax',
        url: 'getUserByProjectTeam.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});