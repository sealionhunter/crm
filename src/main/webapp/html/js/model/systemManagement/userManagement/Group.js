Ext.define('CRM.model.systemManagement.userManagement.Group', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'groupID',
        type: 'int'
    }, {
        name: 'groupName',
        type: 'string'
    } ],
    idProperty: 'groupID'
});