Ext.define('CRM.store.systemManagement.authorizationManagement.AccessRightsTree', {
    extend: 'Ext.data.TreeStore',
    proxy: {
        type: 'ajax',
        url: 'getAccessTree.action'
    },
    root: {
        name: 'root',
        id: '-1'
    }
});
