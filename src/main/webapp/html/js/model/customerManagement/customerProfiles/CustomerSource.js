Ext.define('CRM.model.customerManagement.customerProfiles.CustomerSource', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'sourceID',
        type: 'string'
    }, {
        name: 'customerID',
        type: 'string'
    }, {
        name: 'customerName',
        type: 'string'
    }, {
        name: 'sourceArea',
        type: 'string'
    }, {
        name: 'sourceType',
        type: 'string'
    }, {
        name: 'sourceTypeName',
        type: 'string'
    }, {
        name: 'descriptions',
        type: 'string'
    } ]
});