Ext.define('CRM.model.customerManagement.intentOrder.GridModel', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'productName',
        type: 'string'
    }, {
        name: 'productPrice',
        type: 'string'
    }, {
        name: 'productNumber',
        type: 'string'
    } ]
});