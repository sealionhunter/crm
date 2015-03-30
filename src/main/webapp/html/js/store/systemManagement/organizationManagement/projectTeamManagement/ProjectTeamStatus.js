Ext.define('CRM.store.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamStatus', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamStatus',
    proxy: {
        type: 'ajax',
        url: 'getProjectTeamStatus.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});