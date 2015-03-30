Ext.define('CRM.view.systemManagement.organizationManagement.departmentManagement.DepartmentInfoBody', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.DepartmentInfoBody',
    id: 'DepartmentInfoBody',
    itemId: 'DepartmentInfoBody',
    layout: 'border',
    border: false,

    items: [ {
        region: 'center',
        xtype: 'DepartmentList'
    }, {
        xtype: 'DepartmentDetail'
    } ]
});
