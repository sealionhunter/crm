Ext.define('CRM.view.customerManagement.intentOrder.IntentOrderDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.intentOrderDetail',
    id: 'intentOrderDetail',
    title: '意向订单详细信息',
    autoScroll: true,
    defaultType: 'displayfield',
    region: 'east',
    width: 300,
    collapsible: true,
    hidden: true,
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 80,
        width: 248,
        htmlEncode: true
    },
    items: [ {
        fieldLabel: '订单编号',
        name: 'orderID'
    }, {
        fieldLabel: '客户名称',
        name: 'customerName'
    }, {
        fieldLabel: '总价(万元)',
        name: 'transactionPrice'
    }, {
        fieldLabel: '订单状态',
        name: 'orderStateName'
    }, {
        fieldLabel: '客户联系人',
        name: 'contactName'
    }, {
        fieldLabel: '客户联系电话',
        name: 'customerContact'
    }, {
        fieldLabel: '负责人',
        name: 'ourRepresentative'
    }, {
        fieldLabel: '负责人电话',
        name: 'ourTelephone'
    }, {
        fieldLabel: '预期付款日期',
        name: 'deliveryDate'
    }, {
        fieldLabel: '备注',
        name: 'remark'
    }, {
        name: 'demandNum',
        itemId: 'demandNum',
        html: '<span style="color:green;font-weight:bold">各个销售阶段的需求</span>'
    }, {
        fieldLabel: '销售事件名称',
        id: 'intentEventName',
        name: 'eventName'
    }, {
        fieldLabel: '当前阶段',
        id: 'currentState'
    } ]
});