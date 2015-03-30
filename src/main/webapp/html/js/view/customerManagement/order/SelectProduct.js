Ext.define('CRM.view.customerManagement.order.SelectProduct', {
    extend: 'Ext.window.Window',
    alias: 'widget.selectProduct',
    id: 'selectProduct',
    layout: 'anchor',
    autoShow: true,
    width: 500,
    height: 600,
    resizable: false,
    modal: true,
    constrainHeader: true,
    title: '选择产品',
    initComponent: function() {
        this.items = [ {
            anchor: '100% 100%',
            id: 'allProduct',
            itemId: 'allProduct',
            xtype: 'allProductList'
        } ];
        this.buttons = [ {
            text: '确定',
            itemId: 'submitProduct',
            action: 'submitProduct'
        }, {
            text: '取消',
            scope: this,
            handler: this.close
        } ];
        this.callParent(arguments);
    }
});