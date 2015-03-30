Ext.define('CRM.view.customerManagement.order.BindSalesEvent', {
    extend: 'Ext.window.Window',
    alias: 'widget.bindSalesEvent',
    layout: 'anchor',
    autoShow: true,
    width: 500,
    height: 600,
    resizable: false,
    modal: true,
    constrainHeader: true,
    title: '选择事件',
    initComponent: function() {
        this.items = [ {
            anchor: '100% 100%',
            itemId: 'bindSalesEventList',
            xtype: 'bindSalesEventList'
        } ];
        this.buttons = [ {
            text: '确定',
            itemId: 'bindEventInfo',
            action: 'bindEventInfo'
        }, {
            text: '取消',
            scope: this,
            handler: this.close
        } ];
        this.callParent(arguments);
    }
});