Ext.define('CRM.view.productManagement.product.ProductEditForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.producteditform',
    id: 'productEditForm',
    width: 550,
    y: 50,
    title: '修改产品信息',
    layout: 'fit',
    resizable: false,
    autoShow: true,
    modal: true,
    initComponent: function() {
        Ext.apply(Ext.form.field.VTypes, {
            price: function(v) {
                return ((/^[1-9][0-9]{0,8}$/.test(v) || /^[1-9][0-9]{0,8}\.[0-9]{1,2}$/.test(v) || /^[0-9]\.[0-9]{1,2}$/.test(v)) && !/^0\.0{1,2}$/.test(v));
            },
            priceText: '不能为0且最多2位小数,最多9位整数！'
        });
        this.items = [ {
            xtype: 'form',
            border: false,
            defaults: {
                htmlEncode: true,
                bodyStyle: 'padding:10px'
            },
            items: [ {
                xtype: 'panel',
                layout: 'anchor',
                border: false,
                items: [ {
                    xtype: 'panel',
                    layout: 'column',
                    border: false,
                    margin: '0 0 5 0',
                    defaults: {
                        border: 0,
                        labelWidth: 70,
                        width: 252
                    },
                    items: [ {
                        fieldLabel: '产品编号',
                        xtype: 'textfield',
                        name: 'productID',
                        id: 'productnumber',
                        labelSeparator: redStar,
                        readOnly: true
                    }, {
                        fieldLabel: '产品名称',
                        xtype: 'textfield',
                        margin: '0 0 5 12',
                        name: 'name',
                        id: 'name',
                        labelSeparator: redStar,
                        allowBlank: false,
                        blankText: "产品名称不能为空！",
                        maxLength: 50,
                        enforceMaxLength: true,
                        maxLengthText: "产品名称长度不能超过50个字符！"
                    } ]
                }, {
                    xtype: 'panel',
                    layout: 'column',
                    margin: '0 0 5 0',
                    border: false,
                    defaults: {
                        border: 0,
                        width: 252,
                        labelWidth: 70
                    },
                    items: [ {
                        fieldLabel: '产品价格',
                        xtype: 'textfield',
                        name: 'price',
                        id: 'price',
                        labelSeparator: redStar,
                        allowBlank: false,
                        vtype: 'price',
                        blankText: "产品价格不能为空！",
                        maxLength: 12,
                        enforceMaxLength: true,
                        maxLengthText: "产品价格长度不能超过12！"
                    }, {
                        fieldLabel: '产品类型',
                        margin: '0 0 5 12',
                        xtype: 'textfield',
                        name: 'categoryShow',
                        id: 'productCategory',
                        width: 252,
                        disabled: true
                    } ]
                }, {
                    xtype: 'panel',
                    layout: 'column',
                    margin: '0 0 5 0',
                    border: false,
                    defaults: {
                        border: 0,
                        width: 252,
                        labelWidth: 70
                    },
                    items: [ {

                        fieldLabel: '产品型号',
                        xtype: 'textfield',
                        name: 'productModel',
                        id: 'productmodel',
                        labelSeparator: redStar,
                        allowBlank: false,
                        blankText: "产品型号不能为空！",
                        maxLength: 20,
                        enforceMaxLength: true,
                        maxLengthText: "产品型号长度不能超过20个字符！"
                    }, {
                        fieldLabel: '产品类型',
                        xtype: 'textfield',
                        name: 'category',
                        width: 252,
                        hidden: true
                    } ]
                }, {
                    fieldLabel: '产品说明',
                    xtype: 'textarea',
                    name: 'description',
                    id: 'description',
                    labelWidth: 70,
                    anchor: '100%',
                    height: 80,
                    labelSeparator: redStar,
                    allowBlank: false,
                    blankText: "产品说明不能为空！",
                    maxLength: 1024,
                    maxLengthText: "产品说明长度不能超过1024个字符！"
                }, {
                    fieldLabel: '备注',
                    xtype: 'textarea',
                    name: 'remark',
                    id: 'remark',
                    labelWidth: 70,
                    maxLength: 1024,
                    enforceMaxLength: true,
                    maxLengthText: '备注长度不能超过1024个字符！',
                    height: 80,
                    anchor: '100%'
                }, {
                    fieldLabel: '产品id',
                    xtype: 'textfield',
                    name: 'id',
                    hidden: true
                } ]
            } ]
        } ];
        this.buttons = [ {
            type: 'submit',
            text: '确定',
            action: 'submitProductEditBtn'
        }, {
            xtype: 'button',
            text: '重置',
            itemId: 'resetform',
            action: 'resetProductEditBtn'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'cancelProductEditBtn'
        } ];
        this.callParent(arguments);
    }
});