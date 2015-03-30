Ext.define('CRM.store.systemManagement.codeManagement.Code', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.codeManagement.CodeItems',
    id: 'ComboboxItemsStore',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllData.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});