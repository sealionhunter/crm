Ext.define('CRM.store.index.MessageStore', {
    extend: 'CRM.store.commonStore',
    alias: 'widget.messagestore',
    model: 'CRM.model.index.WorkModel',
    pageSize: 10,
    proxy: {
        type: 'ajax',
        url: 'getTask.action',
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