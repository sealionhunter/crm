Ext.define('CRM.store.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamLeaderSelecting', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamLeaderSelecting',
    proxy: {
        type: 'ajax',
        url: 'getTeamLeaderSelecting.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});