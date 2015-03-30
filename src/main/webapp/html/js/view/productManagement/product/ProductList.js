Ext.define('CRM.view.productManagement.product.ProductList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.productlist',
    id: 'productList',
    title: '产品列表',
    multiSelect: true,
    minWidth: 100,
    selModel: {
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        var me = this;
        this.store = 'productManagement.Product';
        this.categoryStore = Ext.create('CRM.store.code.Code');
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: 'id',
            dataIndex: 'id',
            hidden: true,
            flex: 1
        }, {
            header: '产品编号',
            dataIndex: 'productID',
            sortable: false,
            renderer: rendererValueHtml,
            minWidth: 160,
            flex: 1
        }, {
            header: '产品名称',
            dataIndex: 'name',
            sortable: false,
            minWidth: 80,
            flex: 1.5
        }, {
            header: '产品类型',
            dataIndex: 'category',
            sortable: false,
            minWidth: 80,
            renderer: this.rendererValue,
            flex: 0.5
        }, {
            header: '产品型号',
            dataIndex: 'productModel',
            renderer: rendererValueHtml,
            sortable: false,
            minWidth: 80,
            flex: 1
        }, {
            header: '标准报价(元)',
            dataIndex: 'price',
            minWidth: 80,
            flex: 1
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            defaults: {
                border: false
            },
            items: [ {
                id: 'productSearchText',
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
                action: 'superQueryBtn'
            }, {
                // 添加填充符
                xtype: "tbfill"
            }, {
                xtype: 'button',
                id: 'productPriceBtn',
                itemId: '21104',
                text: '查看报价',
                hidden: true,
                disabled: true
            }, {
                xtype: 'button',
                id: 'setProductPriceBtn',
                action: 'setProductPriceBtn',
                itemId: '21105',
                text: '设置报价',
                disabled: true
            }, {
                xtype: 'button',
                id: 'productAddBtn',
                itemId: '21101',
                text: '添加'
            }, {
                xtype: 'button',
                text: '编辑',
                id: 'productEditBtn',
                itemId: '21102',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                id: 'productDelBtn',
                itemId: '21103',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'productAdvanced',
            layout: 'column',
            defaults: {
                width: 160,
                margin: '5 5 5 5',
                height: 20
            },
            hidden: true,
            items: [ {
                xtype: 'textfield',
                id: 'productIDSearch',
                name: 'productIDSearch',
                labelWidth: 55,
                fieldLabel: '产品编号'
            }, {
                xtype: 'textfield',
                id: 'productName',
                name: 'productName',
                labelWidth: 55,
                fieldLabel: '产品名称',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '产品名称长度不能超过50个字符！'
            }, {
                xtype: 'combobox',
                store: this.categoryStore,
                value: '',
                id: 'category',
                name: 'category',
                labelWidth: 55,
                fieldLabel: '产品类型',
                editable: false,
                queryMode: 'local',
                displayField: 'value',
                valueField: 'code',
                listConfig: {
                    loadMask: false
                },
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'textfield',
                id: 'productModel',
                name: 'productModel',
                labelWidth: 55,
                fieldLabel: '产品型号',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '产品型号长度不能超过50个字符！'
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'textfield',
                    id: 'minPrice',
                    name: 'minPrice',
                    labelWidth: 55,
                    width: 160,
                    height: 20,
                    margin: '0 5 0 0',
                    fieldLabel: '价格范围',
                    vtype: 'price',
                    maxLength: 12,
                    enforceMaxLength: true,
                    maxLengthText: '产品价格价格范围不能超过12位！'
                }, {
                    xtype: 'textfield',
                    id: 'maxPrice',
                    name: 'maxPrice',
                    labelWidth: 15,
                    margin: '0 0 0 5',
                    height: 20,
                    width: 115,
                    fieldLabel: '~',
                    vtype: 'price',
                    labelSeparator: '',
                    maxLength: 12,
                    enforceMaxLength: true,
                    maxLengthText: '产品价格价格范围不能超过12位！'
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
            store: this.store,
            displayInfo: true
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        var result = "";
        if (this.categoryStore.getCount() > 0) {
            this.categoryStore.each(function(r) {
                if (r.get('code') == value) {
                    result = r.get('value');
                    return;
                }
            });
        }
        return result;
    }
});
Ext.form.VTypes['priceMask'] = /[0-9]|./;
Ext.form.VTypes['priceText'] = '不能为0且最多2位小数,最多9位整数！';
Ext.form.VTypes['price'] = function(v) {
    return ((/^[1-9][0-9]{0,8}$/.test(v) || /^[1-9][0-9]{0,8}\.[0-9]{1,2}$/.test(v) || /^[0-9]\.[0-9]{1,2}$/.test(v)) && !/^0\.0{1,2}$/.test(v));
};
function rendererValueHtml(value, metadata, record, rowIndex) {
    return Ext.String.htmlEncode(value);
}