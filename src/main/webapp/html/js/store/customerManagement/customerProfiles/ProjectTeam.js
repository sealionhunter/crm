Ext.define('CRM.store.customerManagement.customerProfiles.ProjectTeam', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.ProjectTeam',
    proxy: {
        type: 'ajax',
        url: 'getProjectTeam.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});