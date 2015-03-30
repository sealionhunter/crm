Ext.define('CRM.store.systemManagement.organizationManagement.departmentManagement.FatherDepartment', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.organizationManagement.departmentManagement.FatherDepartment',
    proxy: {
        type: 'ajax',
        url: 'getFatherDepartment.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});