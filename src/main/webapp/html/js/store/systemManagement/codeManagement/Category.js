Ext.define('CRM.store.systemManagement.codeManagement.Category', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.codeManagement.CodeItems',
    proxy: {
        type: 'ajax',
        url: 'getCategory.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});