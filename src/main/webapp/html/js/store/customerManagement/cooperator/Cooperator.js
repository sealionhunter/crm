Ext.define('CRM.store.customerManagement.cooperator.Cooperator', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.cooperator.Cooperator',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllCooperator.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total' // 总数。 默认值='total'
        }
    }
});
