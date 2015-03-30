Ext.define('CRM.store.systemManagement.groupManagement.CanAddMembers', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.UserInfo',
    pageSize: 10,
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