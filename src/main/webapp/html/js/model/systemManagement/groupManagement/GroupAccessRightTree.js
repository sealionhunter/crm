Ext.define('CRM.model.systemManagement.groupManagement.GroupAccessRightTree', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'memberID',
        type: 'int'
    }, {
        name: 'memberName',
        type: 'string'
    }, {
        name: 'memberRealName',
        type: 'string'
    }, {
        name: 'memberGroupID',
        type: 'int'
    } ]
});
