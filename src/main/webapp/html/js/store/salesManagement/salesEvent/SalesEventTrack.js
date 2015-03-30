Ext.define('CRM.store.salesManagement.salesEvent.SalesEventTrack', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.salesManagement.salesEvent.SalesEventTrack',
    proxy: {
        type: 'ajax',
        url: 'getSalesTrack.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});