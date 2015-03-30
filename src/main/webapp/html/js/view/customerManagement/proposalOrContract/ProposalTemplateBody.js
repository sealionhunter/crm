Ext.define('CRM.view.customerManagement.proposalOrContract.ProposalTemplateBody', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.proposaltemplatebody',
    id: 'proposaltemplatebody',
    itemId: 'proposaltemplatebody',
    layout: 'border',
    items: [ {
        region: "center",
        xtype: 'proposaltemplatelist'
    }, {
        region: "east",
        xtype: 'proposaltemplateinfo'
    } ]
});