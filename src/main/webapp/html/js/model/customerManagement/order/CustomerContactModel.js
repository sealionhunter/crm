Ext.define('CRM.model.customerManagement.order.CustomerContactModel', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'contactID',
        type: 'int'
    }, {
        name: 'contactName',
        type: 'string'
    }, {
        name: 'customerID',
        type: 'string'
    } ]
});