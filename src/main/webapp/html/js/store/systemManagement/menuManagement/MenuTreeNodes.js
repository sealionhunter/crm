Ext.define('CRM.store.systemManagement.menuManagement.MenuTreeNodes', {
    extend: 'Ext.data.TreeStore',
    proxy: {
        type: 'ajax',
        // url : 'html/data/menuTreeNodes.json'
        url: 'getAccessTree.action'
    },
    root: {
        text: '根节点',
        id: '-1'
    }
});