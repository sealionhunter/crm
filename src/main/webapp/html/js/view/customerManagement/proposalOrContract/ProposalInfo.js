Ext.define('CRM.view.customerManagement.proposalOrContract.ProposalInfo', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.proposalinfo',
    id: 'proposalinfo',
    itemId: 'proposalinfo',
    layout: 'border',
    border: false,
    items: [ {
        region: 'center',
        margin: '0 2 0 0',
        xtype: 'proposallist'
    }, {
        xtype: 'proposaldetail'
    } ]
});