Ext.define('CRM.store.customerManagement.proposalOrContract.ProposalStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.proposalOrContract.ProposalOrContractModel',
    proxy: {
        type: 'ajax',
        url: 'getAllProposalOrContract.action',
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