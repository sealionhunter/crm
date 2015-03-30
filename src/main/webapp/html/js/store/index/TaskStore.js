Ext.define('CRM.store.index.TaskStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.index.WorkModel',
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