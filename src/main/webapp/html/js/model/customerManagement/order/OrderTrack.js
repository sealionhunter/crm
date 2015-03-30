Ext.define('CRM.model.customerManagement.order.OrderTrack', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'editPeople',
        type: 'string'
    }, {
        name: 'orderID',
        type: 'string'
    }, {
        name: 'recordTime',
        type: 'string'
    }, {
        name: 'beforeState',
        type: 'string'
    }, {
        name: 'afterState',
        type: 'string'
    } ]
});