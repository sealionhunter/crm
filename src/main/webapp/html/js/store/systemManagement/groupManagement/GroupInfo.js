Ext.define('CRM.store.systemManagement.groupManagement.GroupInfo', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.groupManagement.GroupInfo',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getGroupMembers.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});