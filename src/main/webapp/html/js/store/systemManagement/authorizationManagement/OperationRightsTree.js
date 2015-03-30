Ext.define('CRM.store.systemManagement.authorizationManagement.OperationRightsTree', {
    extend: 'Ext.data.TreeStore',
    proxy: {
        type: 'ajax',
        url: 'getOperationTree.action'
    },
    root: {
        name: 'root',
        id: '-1'
    }
});
