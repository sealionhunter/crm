Ext.define('CRM.store.customerManagement.intentOrder.GridStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.intentOrder.GridModel',
    id: 'intentGridStore',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: 'html/data/GridStore.json'
        },
        reader: {
            type: 'json',
            root: 'users',
            successProperty: 'success'
        }
    }
});