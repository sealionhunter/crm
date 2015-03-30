Ext.define('CRM.store.index.WorkStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.index.WorkModel',
    pageSize: 15,
    proxy: {
        type: 'ajax',
        url: 'workAction.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    },
    sorters: [ {
        property: 'completion',
        direction: 'ASC'
    }, {
        property: 'startTime',
        direction: 'ASC'
    }, {
        property: 'priority',
        direction: 'ASC'
    } ]
});