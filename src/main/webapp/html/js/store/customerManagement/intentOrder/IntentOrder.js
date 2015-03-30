Ext.define('CRM.store.customerManagement.intentOrder.IntentOrder', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.intentOrder.IntentOrder',
    id: 'intentOrderStore',
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
