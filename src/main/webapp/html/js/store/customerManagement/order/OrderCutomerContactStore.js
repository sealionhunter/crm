Ext.define('CRM.store.customerManagement.order.OrderCutomerContactStore', {
    extend: 'CRM.store.commonStore',
    // fields : [ 'customerID','contactID', 'contactName' ],
    model: 'CRM.model.customerManagement.order.CustomerContactModel',
    id: 'customerContactModel',
    proxy: {
        type: 'ajax',
        url: 'getCustomerContact.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total' // 总数。 默认值='total'
        }
    }
});