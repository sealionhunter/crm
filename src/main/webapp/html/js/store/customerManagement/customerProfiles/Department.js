Ext.define('CRM.store.customerManagement.customerProfiles.Department', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.customerProfiles.Department',
    proxy: {
        type: 'ajax',
        url: 'getDepartment.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});