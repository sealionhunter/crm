Ext.define('CRM.store.salesManagement.salesEvent.TabsDenamd', {
    extend: 'CRM.store.commonStore',
    alias: 'widget.TabsDenamdStore',
    model: 'CRM.model.salesManagement.salesEvent.TabsDenamd',
    proxy: {
        type: 'ajax',
        url: 'loadTabsAction.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});