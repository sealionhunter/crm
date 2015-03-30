Ext.define('CRM.store.customerManagement.proposalOrContract.FileTemplateStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.proposalOrContract.FileTemplateModel',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllFileTemplate.action',
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