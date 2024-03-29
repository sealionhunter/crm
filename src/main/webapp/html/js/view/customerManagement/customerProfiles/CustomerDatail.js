Ext.define('CRM.view.customerManagement.customerProfiles.CustomerDatail', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.customerdatail',
    region: "east",
    id: 'customerdatail',
    collapsible: true,
    autoScroll: true,
    hidden: true,
    width: 300,
    height: 202,
    title: '客户详细信息',
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 60,
        htmlEncode: true,
        width: 248
    },
    items: [ {
        name: 'customerName',
        fieldLabel: '客户名称'
    }, {
        name: 'holderName',
        fieldLabel: '客户经理'
    }, {
        name: 'scaleName',
        fieldLabel: '注册资金'
    }, {
        name: 'customerTypeName',
        fieldLabel: '客户类型'
    }, {
        name: 'industryName',
        fieldLabel: '市场名称'
    }, {
        name: 'customerStatementName',
        fieldLabel: '客户状态'
    }, {
        name: 'valueEvaluateName',
        fieldLabel: '价值评估'
    }, {
        name: 'earningName',
        fieldLabel: '年产值'
    }, {
        name: 'feeName',
        fieldLabel: '所有权'
    }, {
        name: 'customerAddr',
        fieldLabel: '标准地址'
    }, {
        name: 'descriptions',
        fieldLabel: '企业简介'
    } ]
});