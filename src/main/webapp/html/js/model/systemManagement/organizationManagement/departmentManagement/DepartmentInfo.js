Ext.define('CRM.model.systemManagement.organizationManagement.departmentManagement.DepartmentInfo', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'departmentID',
        type: 'int'
    }, {
        name: 'departmentName',
        type: 'string'
    }, {
        name: 'serialNumber',
        type: 'string'
    }, {
        name: 'managerName',
        type: 'string'
    }, {
        name: 'fatherDepartmentID',
        type: 'int'
    }, {
        name: 'departmentIDB',
        type: 'string'
    }, {
        name: 'departmentManager',
        type: 'int'
    }, {
        name: 'departmentPhone',
        type: 'string'
    }, {
        name: 'createTime',
        type: 'string'
    }, {
        name: 'departmentDescription',
        type: 'string'
    } ]
});