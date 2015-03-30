Ext.define('CRM.view.customerManagement.proposalOrContract.ContractDetail', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.contractdetail',
    itemId: 'contractdetail',
    id: 'contractdetail',
    collapsible: true,
    autoScroll: true,
    hidden: true,
    title: '合同详细信息',
    width: 300,
    region: 'east',
    height: 202,
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 90,
        labelAlign: "left",
        width: 250,
        htmlEncode: true
    },
    items: [ {
        fieldLabel: '名称',
        name: 'contractName'
    }, {
        fieldLabel: '订单编号',
        name: 'orderID'
    }, {
        fieldLabel: '付款方式',
        name: 'payType'
    }, {
        fieldLabel: '使用模板',
        name: 'fileTemplateName'
    }, {
        fieldLabel: '付款截止日期',
        name: 'payEndTime'
    } ]
});