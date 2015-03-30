Ext.define('CRM.model.customerManagement.order.BuyProductModel', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'id',
        type: 'int'
    }, {
        name: 'orderID',
        type: 'string'
    }, {
        name: 'productID',
        type: 'string'
    }, {
        name: 'name',
        type: 'string'
    }, {
        name: 'productNumber',
        type: 'string'
    }, {
        name: 'price',
        type: 'string'
    }, {
        name: 'discount',
        type: 'string'
    } ]
});