Ext.define('CRM.view.customerManagement.intentOrder.IntentSelectProduct', {
    extend: 'Ext.window.Window',
    alias: 'widget.intentSelectProduct',
    id: 'intentSelectProduct',
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