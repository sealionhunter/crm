Ext.define('CRM.store.customerManagement.order.Order', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.order.Order',
    id: 'orderStore',
    proxy: {
        type: 'ajax',
        url: 'orderList.action',
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
