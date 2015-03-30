Ext.define('CRM.view.customerManagement.proposalOrContract.FileTemplateBody', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.filetemplatebody',
    id: 'filetemplatebody',
    itemId: 'filetemplatebody',
    layout: 'border',
    items: [ {
        region: "center",
        xtype: 'filetemplatelist'
    }, {
        region: "east",
        xtype: 'filetemplateinfo'
    } ]
});