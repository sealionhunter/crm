Ext.define('CRM.view.productManagement.price.ProductPriceList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.productpricelist',
    id: 'productPriceList',
    title: '产品报价管理',
    multiSelect: true,
    minWidth: 100,
    selModel: {
        // 复选框选择模式Ext.selection.CheckboxModel,disableSelection应该为false
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        Ext.apply(Ext.form.field.VTypes, {
            discount: function(v) {
                return (/^[0]\.[0-9]{1,2}$/.test(v) || /^[1]\.[0]{1,2}$/.test(v) || /^[1]$/.test(v));
            },
            discountText: '不能大于1且最多两位小数！'
        });
        Ext.apply(Ext.form.field.VTypes, {
            number: function(v) {
                return ((/^[1-9][0-9]{0,8}$/.test(v) || /^[1-9][0-9]{0,8}\.[0-9]{1,2}$/.test(v) || /^[0-9]\.[0-9]{1,2}$/.test(v)) && !/^0\.0{1,2}$/.test(v));
            },
            numberText: '不能为0且最多2位小数,最多9位整数！'
        });
        this.groupStore = Ext.create('CRM.store.systemManagement.userManagement.Group');
        this.groupStore.load({
            params: {
                groupID: 0
            }
        });
        this.columns = [];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            defaults: {
                border: false
            },
            items: [ {
                id: 'priceSearchText',
                itemId: 'searchText',
                xtype: 'textfield',
                width: 150,
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "检索内容长度不能超过1024个字符！"
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn'
            }, {
                text: '高级检索',
                xtype: 'button',
                action: 'superQueryBtn',
                id: 'superQueryBtn'
            }, {
                // 添加填充符
                xtype: "tbfill"
            }, {
                xtype: 'button',
                text: '编辑',
                action: 'priceEditBtn',
                id: 'priceEditBtn',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                action: 'priceDelBtn',
                id: 'priceDelBtn',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'discountAdvanced',
            layout: 'column',
            defaults: {
                margin: '5 5 5 5',
                labelWidth: 55,
                width: 160,
                height: 20
            },
            hidden: true,
            items: [ {
                xtype: 'textfield',
                id: 'discountProductName',
                name: 'discountProductName',
                fieldLabel: '产品名称',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: "检索产品名称长度不能超过50个字符！"
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'textfield',
                    id: 'discountMinPrice',
                    name: 'discountMinPrice',
                    fieldLabel: '价格区间',
                    margin: '0 5 0 0',
                    height: 20,
                    vtype: 'number',
                    width: 160,
                    labelWidth: 55,
                    maxLength: 12,
                    enforceMaxLength: true,
                    maxLengthText: '产品价格价格范围不能超过12位！'
                }, {
                    xtype: 'textfield',
                    id: 'discountMaxPrice',
                    name: 'discountMinPrice',
                    margin: '0 0 0 5',
                    height: 20,
                    fieldLabel: '~',
                    labelWidth: 15,
                    width: 115,
                    labelSeparator: "",
                    vtype: 'number',
                    maxLength: 12,
                    enforceMaxLength: true,
                    maxLengthText: '产品价格价格范围不能超过12位！'
                } ]
            }, {
                xtype: 'combo',
                store: this.groupStore,
                forceSelection: true,
                listConfig: {
                    loadMask: false
                },
                value: '',
                id: 'groupID',
                name: 'groupID',
                fieldLabel: '选择角色',
                editable: false,
                queryMode: 'local',
                displayField: 'groupName',
                valueField: 'groupID',
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.groupName)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'textfield',
                    id: 'minDiscount',
                    name: 'minDiscount',
                    margin: '0 5 0 0',
                    height: 20,
                    width: 160,
                    disabled: true,
                    labelWidth: 55,
                    fieldLabel: '折扣范围',
                    vtype: 'discount',
                    maxLength: 4,
                    enforceMaxLength: true,
                    maxLengthText: '产品价格价格范围不能超过4位！'
                }, {
                    xtype: 'textfield',
                    id: 'maxDiscount',
                    name: 'maxDiscount',
                    disabled: true,
                    margin: '0 0 0 5',
                    height: 20,
                    fieldLabel: '~',
                    width: 115,
                    labelWidth: 15,
                    labelSeparator: '',
                    vtype: 'discount',
                    maxLength: 4,
                    enforceMaxLength: true,
                    maxLengthText: '产品价格价格范围不能超过4位！'
                } ]
            }, {
                xtype: 'button',
                text: '检索',
                width: 60,
                action: 'queryBtn'
            } ]
        }, {
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            id: 'pagingtoolbar',
            displayInfo: true
        } ];
        this.callParent(arguments);
    }
});
