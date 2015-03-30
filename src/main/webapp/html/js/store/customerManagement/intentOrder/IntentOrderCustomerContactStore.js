Ext.define('CRM.store.customerManagement.intentOrder.IntentOrderCustomerContactStore', {
    extend: 'CRM.store.commonStore',
    // fields : [ 'customerID','contactID', 'contactName' ],
    model: 'CRM.model.customerManagement.intentOrder.IntentOrderCustomerContactModel',
    id: 'intentCustomerContactStore',
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