Ext.define('CRM.view.customerManagement.intentOrder.IntentOrderList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.intentOrderList',
    title: '意向订单基本信息列表',
    id: 'intentOrderList',
    margins: '0 5 0 0',
    minWidth: 100,
    store: 'customerManagement.intentOrder.IntentOrder',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.customerStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName');
        // this.customerContactWay = Ext.create('CRM.store.comboBox.ComboBox');
        // this.orderTypeStore = Ext.create('CRM.store.code.ComboBox');
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
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
            dataIndex: 'customerID',
            hidden: true
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
            minWidth: 60,
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
            id: 'intentorderQueryTbar',
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
                id: 'intentorderSearchBtn',
                itemId: 'queryBtn',
                action: 'queryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                id: 'intentorderSuperBtn',
                itemId: 'superBtn',
                action: 'openOrCloseOrderSuperQuery'
            }, '->', {
                xtype: 'button',
                id: "orderChangeBtn",
                action: "intentOrderToOrder",
                text: '生成正式订单',
                disabled: true
            }, {
                xtype: 'button',
                id: "intentorderTrackBtn",
                action: "orderTrackBtn",
                text: '订单追踪',
                disabled: true
            }, {
                xtype: 'button',
                // id : "orderAddBtn",
                action: "orderAddBtn",
                text: '添加'
            }, {
                xtype: 'button',
                id: "intentorderEditBtn",
                action: "orderEditBtn",
                text: '编辑',
                disabled: true
            }, {
                xtype: 'button',
                id: "intentorderDeleteBtn",
                action: "orderDeleteBtn",
                text: '删除',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'intentorderSuperQueryTbar',
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
                name: 'orderIDValue',
                enforceMaxLength: true,
                maxLength: 21,
                maxLengthText: "订单编号长度不能超过21个字符！"
            }, {
                fieldLabel: '客户名称',
                name: 'customerIDValue',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "客户名称长度不能超过50个字符！"
            }, {
                fieldLabel: '负责人',
                labelWidth: 45,
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
                    height: 20,
                    width: 160,
                    name: 'transactionPriceValue',
                    maxValue: 1000000000,
                    maxText: "成交金额的最大值为十万亿！",
                    nanText: '输入值为非有效数值，请输入正数！',
                    minValue: 0,
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