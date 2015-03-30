Ext.define('CRM.store.systemManagement.organizationManagement.projectTeamManagement.AddedMembers', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.UserInfo',
    pageSize: 10,
    proxy: {
        type: 'ajax',
        url: 'getProjectTeamMembers.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});