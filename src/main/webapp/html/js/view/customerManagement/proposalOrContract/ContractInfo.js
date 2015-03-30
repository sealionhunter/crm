Ext.define('CRM.view.customerManagement.proposalOrContract.ContractInfo', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.contractinfo',
    id: 'contractinfo',
    itemId: 'contractinfo',
    layout: 'border',
    border: false,
    items: [ {
        region: 'center',
        margin: '0 2 0 0',
        xtype: 'contractlist'
    }, {
        xtype: 'contractdetail'
    } ]
});