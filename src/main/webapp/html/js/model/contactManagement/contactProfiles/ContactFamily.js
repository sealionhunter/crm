Ext.define('CRM.model.contactManagement.contactProfiles.ContactFamily', {
    extend: 'Ext.data.Model',
    idProperty: 'familyID',// model's unique id
    fields: [ {
        name: 'familyID',
        type: 'number'
    }, {
        name: 'contactID',
        type: 'number'
    }, {
        name: 'familyRelation',
        type: 'string'
    }, {
        name: 'familyName',
        type: 'string'
    }, {
        name: 'birthday',
        type: 'string'
    }, {
        name: 'political',
        type: 'string'
    }, {
        name: 'company',
        type: 'string'
    }, {
        name: 'job',
        type: 'string'
    }, {
        name: 'descriptions',
        type: 'string'
    } ]
});