Ext.define('CRM.view.productManagement.price.ProductPriceAddForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.priceaddform',
    width: 400,
    title: '设置报价信息',
    y: 200,
    layout: 'fit',
    id: 'priceaddform',
    resizable: false,
    autoShow: true,
    bodyStyle: 'background:#ffffff;',
    modal: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            layout: 'column',
            id: 'priceform',
            padding: "10 15 10 10",
            border: false,
            bodyStyle: 'overflow-x:hidden;overflow-y:auto;',
            items: []
        } ];
        this.buttons = [ {
            type: 'submit',
            text: '确定',
            action: 'submitPriceAddBtn'
        }, {
            xtype: 'button',
            text: '重置',
            action: 'resetPriceAddBtn'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'cancelPriceAddBtn'
        } ];
        this.callParent(arguments);
    }
});