Ext.define('CRM.store.systemManagement.organizationManagement.departmentManagement.DepartmentInfo', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.organizationManagement.departmentManagement.DepartmentInfo',
    id: 'DepartmentInfoStore',
    proxy: {
        type: 'ajax',
        url: 'getAllDepartment.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    },
    autoLoad: true
});