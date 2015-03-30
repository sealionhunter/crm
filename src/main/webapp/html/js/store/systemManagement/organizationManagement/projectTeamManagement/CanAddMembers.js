Ext.define('CRM.store.systemManagement.organizationManagement.projectTeamManagement.CanAddMembers', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.UserInfo',
    pageSize: 10,
    proxy: {
        type: 'ajax',
        url: 'getOtherMembers.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});