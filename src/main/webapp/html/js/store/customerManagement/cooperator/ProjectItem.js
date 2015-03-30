Ext.define('CRM.store.customerManagement.cooperator.ProjectItem', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.cooperator.ProjectItem',
    id: 'ProjectItem',
    proxy: {
        type: 'ajax',
        url: 'getAllCooperationProject.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});
