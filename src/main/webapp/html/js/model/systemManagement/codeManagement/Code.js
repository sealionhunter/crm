Ext.define('CRM.model.systemManagement.codeManagement.Code', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'category',
        type: 'string'
    }, {
        name: 'module',
        type: 'string'
    }, {
        name: 'labelName',
        type: 'string'
    } ]
});