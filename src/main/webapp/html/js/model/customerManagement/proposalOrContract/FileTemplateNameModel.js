Ext.define('CRM.model.customerManagement.proposalOrContract.FileTemplateNameModel', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'fileTemplateID',
        type: 'string'
    }, {
        name: 'fileTemplateName',
        type: 'string'
    }, {
        name: 'fileTemplateValue',
        type: 'string'
    }, {
        name: 'type',
        type: 'string'
    } ]
});