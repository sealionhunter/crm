Ext.define('CRM.store.systemManagement.userManagement.Department', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.Department',
    proxy: {
        type: 'ajax',
        url: 'getUserDepartment.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});