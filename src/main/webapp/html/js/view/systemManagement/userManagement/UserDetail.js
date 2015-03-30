Ext.define('CRM.view.systemManagement.userManagement.UserDetail', {
    extend: 'Ext.form.Panel',
    id: 'UserDetail',
    defaultType: 'displayfield',
    alias: 'widget.UserDetail',
    title: '用户详细信息',
    region: "east",
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
        fieldLabel: '用户名 ',
        name: 'userName',
        htmlEncode: true
    }, {
        fieldLabel: '姓名 ',
        name: 'realName',
        htmlEncode: true
    }, {
        fieldLabel: '用户角色',
        name: 'groupIDB',
        htmlEncode: true

    }, {
        fieldLabel: '工号 ',
        name: 'jobID',
        htmlEncode: true

    }, {
        fieldLabel: '公司 ',
        name: 'company',
        htmlEncode: true
    }, {
        fieldLabel: '部门',
        name: 'departmentIDB',
        htmlEncode: true

    }, {
        fieldLabel: '职务',
        name: 'job',
        htmlEncode: true
    }, {
        fieldLabel: '技术职称',
        name: 'jobTitleB',
        htmlEncode: true
    }, {
        fieldLabel: '邮箱 ',
        name: 'email',
        htmlEncode: true
    }, {
        fieldLabel: '手机号码',
        name: 'mobile',
        htmlEncode: true

    }, {
        fieldLabel: '办公室电话 ',
        name: 'officePhone',
        htmlEncode: true
    }, {
        fieldLabel: '学历',
        name: 'educationB',
        htmlEncode: true

    }, {
        fieldLabel: '入社时间',
        name: 'entryTime',
        htmlEncode: true

    }, {
        fieldLabel: '备注',
        name: 'descriptions',
        htmlEncode: true
    } ]
});