Ext.define('CRM.view.salesManagement.salesEvent.SalesEventDetail', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.salesEventDetail',
    region: 'east',
    id: 'salesEventDetail',
    collapsible: true,
    autoScroll: true,
    hidden: true,
    width: 300,
    title: '事件详细信息',
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 60,
        htmlEncode: true,
        width: 248
    },
    items: [ {
        name: 'eventName',
        fieldLabel: '事件名称'
    }, {
        name: 'customerName',
        fieldLabel: '客户'
    }, {
        name: 'contactName',
        fieldLabel: '联系人员'
    }, {
        name: 'submitDate',
        fieldLabel: '事件日期'
    }, {
        name: 'submitPersonName',
        fieldLabel: '提交人员'
    }, {
        name: 'statusName',
        fieldLabel: '现处阶段'
    }, {
        id: 'salesEventDetailIsAbolished',
        name: 'isAbolished',
        fieldLabel: '事件状态'
    }, {
        name: 'remarks',
        fieldLabel: '备注'
    }, {
        name: 'demandNum',
        html: '<span style="color:green;font-weight:bold">各个销售阶段的需求</span>'
    } ]
});