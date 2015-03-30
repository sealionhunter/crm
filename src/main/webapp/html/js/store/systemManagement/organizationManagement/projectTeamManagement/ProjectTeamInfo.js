Ext.define('CRM.store.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamInfo', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamInfo',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getTeam.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});
