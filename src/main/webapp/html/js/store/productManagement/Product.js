Ext.define('CRM.store.productManagement.Product', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.productManagement.Product',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllProduct.action',
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
