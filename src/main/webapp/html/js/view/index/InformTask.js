Ext.define('CRM.view.index.InformTask', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.informTask',
    id: 'informTask',
    title: '客户提醒<span><a href="#" onclick="utils.showMoreCusUpdatedStatus();" style="float:right;">更多</a></span>',
    layout: 'anchor',
    height: 200,
//    padding: '0 5 0 0',
    initComponent: function() {
        this.store = Ext.create('CRM.store.index.ContactTrackStore');
        this.html = "<div id='showinformTask'></div>";
        this.callParent(arguments);
    }
});