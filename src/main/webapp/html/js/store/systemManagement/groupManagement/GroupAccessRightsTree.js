Ext.define('CRM.store.systemManagement.groupManagement.GroupAccessRightsTree', {
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
