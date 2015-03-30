Ext.define('CRM.view.productManagement.product.ProductPriceAddForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.productpriceaddform',
    width: 400,
    y: 200,
    title: '设置产品的折扣',
    layout: 'fit',
    id: 'productpriceaddform',
    bodyStyle: 'background:#ffffff;',
    resizable: false,
    autoShow: true,
    modal: true,
    initComponent: function() {
        Ext.apply(Ext.form.field.VTypes, {
            discount: function(v) {
                return ((/^[1-9][0-9]*$/.test(v) || /^[1-9][0-9]*\.[0-9]{1,2}$/.test(v) || /^[0-9]\.[0-9]{1,2}$/.test(v)) && !/^0\.0{1,2}$/.test(v));
            },
            discountText: '请输入小于1且最多2位小数的非零数字！'
        });
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
            text: '清空',
            action: 'resetPriceAddBtn'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'cancelPriceAddBtn'
        } ];
        this.callParent(arguments);
    }
});