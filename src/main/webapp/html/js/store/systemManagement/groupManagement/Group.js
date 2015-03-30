Ext.define('CRM.store.systemManagement.groupManagement.Group', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.Group',
    proxy: {
        type: 'ajax',
        url: 'getGroup.action',
        reader: {
            type: 'json',
            root: 'items',
            total: 'total'
        }
    }
});