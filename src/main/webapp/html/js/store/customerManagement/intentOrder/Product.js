Ext.define('CRM.store.customerManagement.intentOrder.Product', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.productManagement.Product',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllProductByUserID.action',
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
