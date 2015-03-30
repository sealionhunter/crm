Ext.define('CRM.view.customerManagement.proposalOrContract.ContractReferenceInfo', {
    extend: 'Ext.form.Panel',
    alias: 'widget.contractReferenceInfo',
    id: 'contractReferenceInfo',
    title: '参考信息',
    autoScroll: true,
    region: 'east',
    width: 300,
    collapsible: true,
    items: [ {
        xtype: 'panel',
        border: false,
        padding: 5,
        layout: 'fit',
        defaults: {
            labelWidth: 100,
            width: 248,
            htmlEncode: true,
            padding: '5 0 0 0'
        },
        defaultType: 'displayfield',
        items: [ {
            fieldLabel: '合同名称',
            name: 'contractName',
            id: 'refContractName'
        }, {
            fieldLabel: '订单编号',
            name: 'orderID',
            id: 'refOrderID'
        }, {
            fieldLabel: '总价(万元)',
            name: 'transactionPrice',
            id: 'refPrice'
        }, {
            fieldLabel: '付款方式',
            name: 'payType',
            id: 'refPayType'
        }, {
            fieldLabel: '付款截止时间',
            name: 'payDate',
            id: 'refPayDate'
        }, {
            fieldLabel: '客户名称',
            name: 'customerName'
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
            fieldLabel: '负责人联系电话',
            name: 'ourTelephone'
        } ]
    } ]
});
