Ext.define('CRM.view.index.InformTask', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.informTask',
    id: 'informTask',
    title: '客户联系',
    layout: 'anchor',
    height: 200,
//    padding: '0 5 0 0',
    initComponent: function() {
        this.store = Ext.create('CRM.store.index.ContactTrackStore');
        this.html = "<div id='showinformTask'></div>";
        this.callParent(arguments);
    }
});