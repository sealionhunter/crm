Ext.define('CRM.store.customerManagement.order.BuyProductStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.order.BuyProductModel',
    proxy: {
        type: 'ajax',
        url: 'buyProduct.action',
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