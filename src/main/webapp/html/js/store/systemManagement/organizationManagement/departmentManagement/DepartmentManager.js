Ext.define('CRM.store.systemManagement.organizationManagement.departmentManagement.DepartmentManager', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.UserInfo',
    proxy: {
        type: 'ajax',
        url: 'getDepartmentManager.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});