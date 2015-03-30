Ext.define('CRM.model.customerManagement.customerProfiles.ContactSelect', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'contactSelectID',
        type: 'number'
    }, {
        name: 'objectID',
        type: 'number'
    }, {
        name: 'contactID',
        type: 'number'
    }, {
        name: 'contactName',
        type: 'string'
    }, {
        name: 'company',
        type: 'string'
    }, {
        name: 'job',
        type: 'string'
    }, {
        name: 'phoneNumber',
        type: 'string'
    } ]
});