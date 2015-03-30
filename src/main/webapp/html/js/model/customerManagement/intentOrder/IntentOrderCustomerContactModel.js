Ext.define('CRM.model.customerManagement.intentOrder.IntentOrderCustomerContactModel', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'contactID',
        type: 'string'
    }, {
        name: 'contactName',
        type: 'string'
    }, {
        name: 'customerID',
        type: 'string'
    } ]
});