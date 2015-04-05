Ext.define('CRM.view.customerManagement.intentOrder.AllProductList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.allProductList',
    title: '产品列表',
    multiSelect: true,
    columnLines: true,
    pickerAlign: 'bl',
    minWidth: 100,
    selModel: {
        // 复选框选择模式Ext.selection.CheckboxModel,disableSelection应该为false
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        var me = this;
        this.store = 'customerManagement.intentOrder.Product';
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: '产品编号',
            dataIndex: 'productID',
            flex: 1.2
        }, {
            header: '产品名称',
            dataIndex: 'name',
            flex: 1
        }, {
            header: '产品型号',
            dataIndex: 'productModel',
            flex: 1
        }, {
            header: '标准价格',
            dataIndex: 'price',
            flex: 1
        }, {
            header: '折扣价格',
            dataIndex: 'discount',
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
                maxLengthText: "检索内容长度不能超过1024个字符！"
            }, {
                xtype: 'button',
                text: '检索',
                action: 'productQueryBtn'
            }, {
                text: '高级检索',
                xtype: 'button',
                action: 'superQueryBtn',
                hidden: true
            }, {
                // 添加填充符
                xtype: "tbfill"
            } ]
        }, {
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            store: this.store,
            displayInfo: true
        } ];
        this.callParent(arguments);
    }
});
