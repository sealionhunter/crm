Ext.define('CRM.view.customerManagement.order.OrderList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.orderList',
    title: '订单基本信息列表',
    id: 'orderList',
    margins: '0 5 0 0',
    minWidth: 100,
    store: 'customerManagement.order.Order',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.customerStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName');
        this.orderStateStore = Ext.create('CRM.store.code.Code');
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: '订单编号',
            dataIndex: 'orderID',
            minWidth: 150,
            renderer: this.rendererValue,
            flex: 1.2
        }, {
            dataIndex: 'id',
            hidden: true
        }, {
            header: '客户名称',
            dataIndex: 'customerName',
            minWidth: 100,
            renderer: this.rendererValue,
            flex: 1
        }, {
            header: '客户联系人',
            dataIndex: 'contactName',
            minWidth: 100,
            renderer: this.rendererValue,
            flex: 1
        }, {
            header: '负责人',
            dataIndex: 'ourRepresentative',
            minWidth: 100,
            renderer: this.rendererValue,
            flex: 1
        }, {
            header: '总价(万元)',
            dataIndex: 'transactionPrice',
            flex: 1,
            minWidth: 80
        }, {
            header: '订单状态',
            dataIndex: 'orderStateName',
            align: 'center',
            minWidth: 80,
            flex: 1
        }, {
            header: '预期付款日',
            dataIndex: 'deliveryDate',
            align: 'center',
            minWidth: 120,
            flex: 1
        }, {
            header: '备注',
            dataIndex: 'remark',
            minWidth: 140,
            renderer: this.rendererValue,
            flex: 1
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        // add a tbar
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'orderQueryTbar',
            items: [ {
                // 条件输入框
                xtype: 'textfield',
                width: 150,
                itemId: 'searchText',
                name: 'queryKeyWord',
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "检索内容长度不能超过1024个字符！",
                selectOnFocus: true
            }, {
                xtype: 'button',
                text: '检索',
                id: 'orderSearchBtn',
                itemId: 'queryBtn',
                action: 'queryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                id: 'orderSuperBtn',
                itemId: 'superBtn',
                action: 'openOrCloseOrderSuperQuery'
            }, '->', {
                xtype: 'button',
                id: "orderTrackBtn",
                action: "orderTrackBtn",
                text: '订单追踪',
                disabled: true
            }, {
                xtype: 'button',
                id: "orderAddBtn",
                action: "orderAddBtn",
                text: '添加'
            }, {
                xtype: 'button',
                id: "orderEditBtn",
                action: "orderEditBtn",
                text: '编辑',
                disabled: true
            }, {
                xtype: 'button',
                id: "orderDeleteBtn",
                action: "orderDeleteBtn",
                text: '删除',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'orderSuperQueryTbar',
            defaultType: 'textfield',
            layout: 'column',
            hidden: true,
            defaults: {
                labelWidth: 55,
                labelSeparator: ':',
                width: 160,
                margin: '5 5 5 5'
            },
            items: [ {
                fieldLabel: '订单编号',
                id: 'orderID_query',
                name: 'orderIDValue',
                enforceMaxLength: true,
                maxLength: 21,
                maxLengthText: "订单编号长度不能超过21个字符！"
            }, {
                fieldLabel: '客户名称',
                id: 'customerID_query',
                name: 'customerIDValue',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "客户名称长度不能超过50个字符！"
            }, {
                fieldLabel: '负责人',
                labelWidth: 45,
                id: 'ourRepresentative',
                name: 'ourRepresentativeValue',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "负责人长度不能超过50个字符！"
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 200,
                border: false,
                items: [ {
                    xtype: 'numberfield',
                    fieldLabel: '总价',
                    labelWidth: 32,
                    width: 160,
                    height: 20,
                    id: 'transactionPrice',
                    name: 'transactionPriceValue',
                    maxValue: 1000000000,
                    maxText: "成交金额的最大值为十万亿！",
                    minText: '输入值为非有效数值，请输入正数！',
                    minValue: 0,
                    negativeText: '总价不能为负数！',
                    decimalPrecision: 4
                }, {
                    xtype: 'displayfield',
                    width: 40,
                    value: '万元'
                } ]
            }, {
                xtype: 'button',
                action: 'queryBtn',
                text: '检索',
                width: 60
            } ]
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});