Ext.define('CRM.store.systemManagement.codeManagement.Module', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.codeManagement.CodeItems',
    proxy: {
        type: 'ajax',
        url: 'getModule.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});