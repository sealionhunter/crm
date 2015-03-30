Ext.define('CRM.store.systemManagement.userManagement.Group', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.Group',
    proxy: {
        type: 'ajax',
        url: 'getGroupID.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});