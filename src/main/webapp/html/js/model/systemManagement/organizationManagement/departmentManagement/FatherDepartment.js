Ext.define('CRM.model.systemManagement.organizationManagement.departmentManagement.FatherDepartment', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'departmentID',
        type: 'int'
    }, {
        name: 'departmentName',
        type: 'string'
    } ],
    idProperty: 'departmentID'
});