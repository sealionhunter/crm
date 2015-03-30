Ext.define('CRM.model.salesManagement.salesEventFlowCode.SalesEventFlowCode', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'code',
        type: 'string'
    }, 'value', 'category', 'sort', 'categoryName' ]
});