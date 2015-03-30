Ext.define('CRM.view.customerManagement.intentOrder.IntentOrderTransfer', {
    extend: 'Ext.window.Window',
    alias: 'widget.intentordertransfer',
    title: '意向订单转化为正式订单',
    id: 'intentordertransfer',
    width: 540,
    height: 260,
    plain: true,
    modal: true,
    autoShow: true,
    resizable: false,
    constrainHeader: true,

    layout: 'fit',
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            bodyStyle: 'overflow-x:hidden;overflow-y:auto;',
            id: 'transferForm',
            layout: 'anchor',
            defaults: {
                margin: '2 2 2 2',
                x: 35
            },
            items: [ {
                layout: 'column',
                anchor: '98%',
                name: 'salesStatusTransfer',
                border: false,
                id: 'salesStatusTransfer',
                margin: '5 20 5 10',
                items: []
            } ]
        } ];
        this.buttons = [ {
            id: 'transferSave',
            text: '确定',
            action: 'transferSave'
        }, {
            id: 'transferClear',
            text: '清空',
            action: 'transferClear'
        }, {
            id: 'transferCancel',
            action: 'transferCancel',
            text: '取消'
        } ];
        this.callParent(arguments);
    }
});