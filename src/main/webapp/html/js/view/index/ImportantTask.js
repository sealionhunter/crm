Ext.define('CRM.view.index.ImportantTask', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.importantTask',
    id: 'importantTask',
    title: '个人重要任务',
    layout: 'anchor',
    height: 200,
    padding: '0 5 0 0',
    initComponent: function() {
        this.store = Ext.create('CRM.store.index.TaskStore');
        this.html = "<div id='showimportantTask'></div>";
        this.callParent(arguments);
    }
});