Ext.define('CRM.store.customerManagement.order.OrderTrack', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.order.OrderTrack',
    storeId: 'orderTrack',
    proxy: {
        type: 'ajax',
        url: 'orderTrackList.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});