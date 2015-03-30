Ext.define('CRM.view.customerManagement.intentOrder.IntentOrderUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.intentOrderUpdate',
    views: [ 'customerManagement.intentOrder.ProductOrder' ],
    id: 'intentOrderUpdate',
    layout: 'fit',
    autoShow: true,
    height: 650,
    width: 600,
    resizable: false,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.getEventStore = Ext.create('CRM.store.salesManagement.salesEvent.SalesEventStore');
        this.orderStateStore = Ext.create('CRM.store.code.Code');
        this.contactStoreAll = Ext.create('CRM.store.customerManagement.intentOrder.IntentOrderCustomerContactStore');
        this.contactStoreByID = Ext.create('CRM.store.customerManagement.intentOrder.IntentOrderCustomerContactStore');
        this.contactStoreByID.load();
        this.contactStoreAll.load();
        this.items = [ {
            xtype: 'form',
            id: 'intentOrderContent',
            bodyStyle: 'overflow-x:hidden; overflow-y:scroll',
            defaults: {
                border: false,
                margin: '5 10 0 10'
            },
            items: [ {
                layout: 'hbox',
                width: 540,
                items: [ {
                    name: 'id',
                    xtype: 'hiddenfield',
                    itemId: 'order_id'
                }, {
                    allowBlank: false,
                    blankText: '订单编号不能为空！',
                    id: 'intentorderID',
                    itemId: 'orderID',
                    xtype: 'textfield',
                    readOnly: true,
                    name: 'orderID',
                    labelSeparator: redStar,
                    fieldLabel: '订单编号'
                }, {
                    allowBlank: false,
                    blankText: '状态不能为空！',
                    xtype: 'combobox',
                    id: 'intentorderStateCombo',
                    fieldLabel: '订单状态',
                    editable: false,
                    labelSeparator: redStar,
                    name: 'orderState',
                    margin: '0 0 0 15',
                    store: this.orderStateStore,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    xtype: 'combobox',
                    y: 25,
                    fieldLabel: '客户名称 ',
                    labelSeparator: redStar,
                    defaultListConfig: {
                        loadMask: false
                    },
                    name: 'customerID',
                    id: 'customerID',
                    itemId: 'customerID',
                    editable: false,
                    queryMode: 'local',
                    allowBlank: false,
                    blankText: "客户名称不能为空！",
                    store: Ext.getCmp('intentOrderList').customerStore,
                    valueField: 'customerID',
                    displayField: 'customerName',
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.customerName)]}</li>', '</tpl></ul>')
                }, {
                    allowBlank: false,
                    blankText: '预期付款日期不能为空！',
                    xtype: 'datefield',
                    margin: '0 0 0 15',
                    format: 'Y-m-d',
                    name: 'deliveryDate',
                    id: 'deliveryDate',
                    itemId: 'deliveryDate',
                    invalidText: '预期付款日期格式不正确，正确格式为：YYYY-MM-DD！',
                    labelSeparator: redStar,
                    fieldLabel: '预期付款日',
                    value: new Date(),
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01'
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    xtype: 'combobox',
                    name: 'contactID',
                    allowBlank: false,
                    blankText: '不能为空！',
                    id: 'customerRepresentativeID',
                    fieldLabel: '客户联系人',
                    labelSeparator: redStar,
                    store: this.contactStoreByID,
                    queryMode: 'local',
                    listConfig: {
                        loadMask: false
                    },
                    editable: false,
                    displayField: 'contactName',
                    valueField: 'contactID',
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.contactName)]}</li>', '</tpl></ul>')
                }, {
                    xtype: 'textfield',
                    margin: '0 0 0 15',
                    id: 'customerContact',
                    itemId: 'customerContact',
                    name: 'customerContact',
                    fieldLabel: '客户联系电话',
                    readOnly: true
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    allowBlank: false,
                    blankText: '负责人不能为空！',
                    xtype: 'textfield',
                    name: 'ourRepresentative',
                    itemId: 'ourRepresentative',
                    id: 'ourRepresentative',
                    labelSeparator: redStar,
                    enforceMaxLength: true,
                    maxLength: 50,
                    maxLengthText: "负责人长度不能超过50个字符！",
                    fieldLabel: '负责人',
                    htmlEncode: true
                }, {
                    allowBlank: false,
                    blankText: '不能为空！',
                    xtype: 'textfield',
                    margin: '0 0 0 15',
                    name: 'ourTelephone',
                    id: 'ourTelephone',
                    fieldLabel: '负责人联系电话',
                    vtype: 'phone',
                    labelSeparator: redStar,
                    enforceMaxLength: true,
                    maxLength: 11
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                height: 80,
                items: [ {
                    xtype: 'displayfield',
                    value: '备注:',
                    width: 85
                }, {
                    xtype: 'textarea',
                    id: 'remark',
                    name: 'remark',
                    margin: '5 20 10 10',
                    width: 435,
                    height: 60,
                    htmlEncode: true,
                    enforceMaxLength: true,
                    maxLength: 1024,
                    maxLengthText: "备注长度不能超过1024个字符！"
                } ]
            }, {
                width: 540,
                xtype: 'panel',
                layout: 'fit',
                height: 205,
                items: [ {
                    xtype: 'buyProduct'
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                hidden: true,
                items: [ {
                    xtype: 'textfield',
                    width: 240,
                    margin: '0 0 0 30',
                    name: 'transactionPrice',
                    itemId: 'totalPrice',
                    fieldLabel: '总价',
                    value: 0,
                    readOnly: true
                }, {
                    xtype: 'displayfield',
                    margin: '0 0 0 3',
                    width: 40,
                    value: '万元'
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    margin: '10 20 0 0',
                    itemId: 'bindEvent',
                    id: 'eventID',
                    xtype: 'combobox',
                    queryMode: 'local',
                    fieldLabel: '销售事件',
                    listConfig: {
                        loadMask: false
                    },
                    value: 0,
                    editable: false,
                    store: this.getEventStore,
                    displayField: 'eventName',
                    valueField: 'eventID',
                    name: 'eventID',
                    labelWidth: 60,
                    width: 200,
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.eventName)]}</li>', '</tpl></ul>')
                }, {
                    margin: '5 20 0 0',
                    border: false,
                    layout: 'column',
                    width: 320,
                    id: 'eventCheckBoxs',
                    items: []
                } ]
            } ]
        } ];
        this.buttons = [ {
            text: '生成正式订单',
            id: 'intentorderchange',
            action: 'intentOrderToOrder',
            hidden: true
        }, {
            text: '确定',
            action: 'update'
        }, {
            itemId: 'cusReset',
            action: 'doreset'
        }, {
            text: '取消',
            action: 'close'
        } ];
        this.callParent(arguments);
    }
});
