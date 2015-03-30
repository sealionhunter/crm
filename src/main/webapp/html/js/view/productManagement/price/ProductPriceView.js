Ext.define('CRM.view.productManagement.price.ProductPriceView', {
    extend: 'Ext.window.Window',
    alias: 'widget.productpriceview',
    width: 350,
    height: 200,
    title: '设置报价信息',
    layout: 'fit',
    resizable: false,
    autoShow: true,
    modal: true, // modal为True表示为当window显示时对其后面的一切内容进行遮罩
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            title: '<font style="font-size:12px">产品报价信息</font>',
            defaultType: 'displayfield',
            defaults: {
                x: 10,
                y: 10,
                labelWidth: 80,
                width: 248,
                htmlEncode: true
            },
            items: [ {
                fieldLabel: '上级管理者',
                value: '0.75折'
            }, {
                fieldLabel: '业务经理',
                value: '0.85折'
            }, {
                fieldLabel: '业务员',
                value: '0.90折'
            } ]
        } ];

        this.buttons = [ {
            type: 'submit',
            text: '确定',
            action: 'submitServiceProductAddBtn'
        }, {
            xtype: 'button',
            text: '清空',
            itemId: 'resetform',
            action: 'resetServiceProductAddBtn'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'cancelServiceProductAddBtn'
        } ];
        this.callParent(arguments);
    }
});