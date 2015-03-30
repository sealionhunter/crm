Ext.define('CRM.view.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamDetail', {
    extend: 'Ext.form.Panel',
    id: 'ProjectTeamDetail',
    defaultType: 'displayfield',
    alias: 'widget.ProjectTeamDetail',
    title: '团队详细信息',
    collapsible: true,
    hidden: true,
    width: 300,
    autoScroll: true,
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 70,
        width: 248
    },
    items: [ {
        fieldLabel: '团队名称 ',
        name: 'projectTeamName',
        htmlEncode: true
    }, {
        fieldLabel: '所属部门 ',
        name: 'departmentName',
        htmlEncode: true
    }, {
        fieldLabel: '团队负责人 ',
        name: 'projectTeamLeaderName',
        htmlEncode: true
    }, {
        fieldLabel: '团队状态 ',
        name: 'projectTeamStatusValue',
        htmlEncode: true
    }, {
        fieldLabel: '创建时间 ',
        name: 'createTime',
        htmlEncode: true
    }, {
        fieldLabel: '结束时间 ',
        name: 'endTime',
        htmlEncode: true
    }, {
        fieldLabel: '备注',
        name: 'description',
        htmlEncode: true
    } ]
});