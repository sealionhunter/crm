Ext.define('CRM.view.systemManagement.organizationManagement.departmentManagement.DepartmentDetail', {
    extend: 'Ext.form.Panel',
    id: 'DepartmentDetail',
    defaultType: 'displayfield',
    alias: 'widget.DepartmentDetail',
    title: '部门详细信息',
    region: 'east',
    collapsible: true,
    hidden: true,
    width: 300,
    autoScroll: true,
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 90,
        width: 248
    },
    items: [ {
        fieldLabel: '部门编号',
        name: 'serialNumber',
        htmlEncode: true
    }, {
        fieldLabel: '部门名称 ',
        name: 'departmentName',
        htmlEncode: true
    }, {
        fieldLabel: '上级部门 ',
        name: 'departmentIDB',
        htmlEncode: true
    }, {
        fieldLabel: '部门经理 ',
        name: 'managerName',
        htmlEncode: true
    }, {
        fieldLabel: '部门联系电话 ',
        name: 'departmentPhone',
        htmlEncode: true
    }, {
        fieldLabel: '创建时间',
        name: 'createTime',
        htmlEncode: true
    }, {
        fieldLabel: '备注',
        name: 'departmentDescription',
        htmlEncode: true
    } ]
});