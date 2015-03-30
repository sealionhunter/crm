Ext.define('CRM.model.customerManagement.proposalOrContract.FileTemplateModel', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'fileTemplateID',
        type: 'int'
    }, {
        name: 'fileTemplateName',
        type: 'string'
    }, {
        name: 'fileTemplateAddDate',
        type: 'string'
    }, {
        name: 'fileTemplateEditDate',
        type: 'string'
    }, {
        name: 'descriptions',
        type: 'string'
    }, {
        name: 'fileTemplateValue',
        type: 'string'
    }, {
        name: 'type',
        type: 'int'
    } ]
});