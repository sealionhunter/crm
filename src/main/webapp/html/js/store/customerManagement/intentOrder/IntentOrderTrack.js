Ext.define('CRM.store.customerManagement.intentOrder.IntentOrderTrack', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.intentOrder.IntentOrderTrack',
    storeId: 'intentOrderTrack',
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