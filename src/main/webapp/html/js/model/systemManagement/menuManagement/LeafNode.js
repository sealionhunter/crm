Ext.define('CRM.model.systemManagement.menuManagement.LeafNode', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'id',
        type: 'int'
    }, {
        name: 'text',
        type: 'string'
    }, {
        name: 'leaf',
        type: 'boolean'
    }, {
        name: 'fatherID',
        type: 'int'
    } ]
});