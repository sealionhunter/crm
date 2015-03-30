Ext.define('CRM.store.customerManagement.proposalOrContract.FileTemplateNameStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.proposalOrContract.FileTemplateNameModel',
    proxy: {
        type: 'ajax',
        url: 'getFileTemplateName.action',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});