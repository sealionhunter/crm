Ext.define('CRM.view.productManagement.product.ProductAddForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.productaddform',
    width: 550,
    y: 50,
    title: '添加产品信息',
    layout: 'fit',
    resizable: false,
    autoShow: true,
    modal: true, // modal为True表示为当window显示时对其后面的一切内容进行遮罩
    initComponent: function() {
        this.categoryStore = Ext.create('CRM.store.code.Code');
        this.categoryStore.load({
            params: {
                code: '00050001'
            },
            reader: {
                type: 'json',
                root: 'items'
            }
        });
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
                items: [ {
                    xtype: 'panel',
                    layout: 'column',
                    border: false,
                    items: [ {
                        margin: '0 0 5 0',
                        xtype: 'combo',
                        fieldLabel: '产品类型',
                        name: 'category',
                        editable: false,
                        id: 'productCategory',
                        columnWidth: 0.5,
                        labelWidth: 80,
                        store: this.categoryStore,
                        allowBlank: false,
                        blankText: '产品类型不能为空！',
                        valueField: 'code',
                        displayField: 'value',
                        queryMode: 'local',
                        labelSeparator: redStar,
                        tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                    }, {
                        columnWidth: 0.5,
                        fieldLabel: '产品编号',
                        labelWidth: 80,
                        margin: '0 0 5 12',
                        xtype: 'textfield',
                        name: 'productID',
                        id: 'productID',
                        readOnly: true,
                        labelSeparator: redStar
                    } ]
                }, {
                    xtype: 'panel',
                    layout: 'column',
                    border: false,
                    items: [ {
                        margin: '0 0 5 0',
                        columnWidth: 0.5,
                        fieldLabel: '产品名称',
                        xtype: 'textfield',
                        name: 'name',
                        id: 'name',
                        labelWidth: 80,
                        labelSeparator: redStar,
                        allowBlank: false,
                        blankText: '产品名称不能为空！',
                        maxLength: 50,
                        enforceMaxLength: true,
                        maxLengthText: '产品名称长度不能超过50个字符！'
                    }, {
                        margin: '0 0 5 12',
                        columnWidth: 0.465,
                        fieldLabel: '产品价格',
                        xtype: 'textfield',
                        name: 'price',
                        id: 'price',
                        allowBlank: false,
                        labelSeparator: redStar,
                        enforceMaxLength: true,
                        maxLength: 12,
                        maxLengthText: '产品价格最多12位！',
                        blankText: '产品价格不能为空！',
                        vtype: 'price',
                        labelWidth: 80,
                        maxLength: 12,
                        enforceMaxLength: true,
                        maxLengthText: '产品价格长度不能超过12！'
                    }, {
                        xtype: 'displayfield',
                        columnWidth: 0.035,
                        labelWidth: 18,
                        labelAlign: 'right',
                        fieldLabel: '元',
                        labelSeparator: ""
                    } ]
                }, {
                    xtype: 'panel',
                    layout: 'column',
                    border: false,
                    items: [ {
                        columnWidth: 0.5,
                        margin: '0 0 5 0',
                        fieldLabel: '产品型号',
                        xtype: 'textfield',
                        name: 'productModel',
                        id: 'productmodel',
                        labelSeparator: redStar,
                        maxLength: 20,
                        labelWidth: 80,
                        enforceMaxLength: true,
                        allowBlank: false,
                        blankText: '产品型号不能为空！',
                        maxLengthText: '产品型号长度不能超过20个字符！',
                        hidden: true
                    } ]
                }, {
                    fieldLabel: '产品说明',
                    xtype: 'textarea',
                    name: 'description',
                    id: 'description',
                    labelSeparator: redStar,
                    allowBlank: false,
                    blankText: '产品说明不能为空！',
                    height: 120,
                    maxLength: 1024,
                    labelWidth: 80,
                    enforceMaxLength: true,
                    maxLengthText: '产品说明长度不能超过1024个字符！',
                    anchor: '100%'
                }, {
                    fieldLabel: '备注',
                    xtype: 'textarea',
                    name: 'remark',
                    id: 'remark',
                    maxLength: 1024,
                    labelWidth: 80,
                    enforceMaxLength: true,
                    maxLengthText: '备注长度不能超过1024个字符！',
                    height: 60,
                    anchor: '100%'
                } ]
            } ]
        } ];
        this.buttons = [ {
            type: 'submit',
            text: '确定',
            action: 'submitProductAddBtn'
        }, {
            xtype: 'button',
            text: '清空',
            itemId: 'resetform',
            action: 'resetProductAddBtn'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'cancelProductAddBtn'
        } ];
        this.callParent(arguments);
    }
});