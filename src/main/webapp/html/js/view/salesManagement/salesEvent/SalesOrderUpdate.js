Ext.define('CRM.view.salesManagement.salesEvent.SalesOrderUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.salesOrderUpdate',
    layout: 'fit',
    id: 'salesOrderUpdate',
    autoShow: true,
    height: 650,
    width: 600,
    resizable: false,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.getEventStore = Ext.create('CRM.store.salesManagement.salesEvent.SalesEventStore');
        this.orderStateStore = Ext.create('CRM.store.code.Code');
        this.contactStoreAll = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
        this.contactStoreAll.load();
        this.contactStoreById = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
        this.contactStoreById.load();
        this.items = [ {
            xtype: 'form',
            id: 'orderContent',
            bodyStyle: 'overflow-x:hidden; overflow-y:scroll',
            defaults: {
                border: 0,
                margin: '5 20 0 10'
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
                    id: 'salesOrderID',
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
                    id: 'salesOrderStateCombo',
                    fieldLabel: '订单状态',
                    editable: false,
                    labelSeparator: redStar,
                    name: 'orderState',
                    margin: '0 0 0 15',
                    store: this.orderStateStore,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code'
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    xtype: 'combo',
                    y: 25,
                    fieldLabel: '客户名称 ',
                    labelSeparator: redStar,
                    id: 'salesCustomerID',
                    queryMode: 'local',
                    name: 'customerID',
                    itemId: 'customerID',
                    editable: false,
                    allowBlank: false,
                    blankText: "客户名称不能为空！",
                    store: Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName'),
                    forceSelection: true,
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
                    itemId: 'deliveryDate',
                    id: 'salesDeliveryDate',
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
                    id: 'customerRepresentativeId',
                    fieldLabel: '客户联系人',
                    labelSeparator: redStar,
                    store: this.contactStoreById,
                    queryMode: 'local',
                    editable: false,
                    displayField: 'contactName',
                    valueField: 'contactID',
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.contactName)]}</li>', '</tpl></ul>')
                }, {
                    xtype: 'textfield',
                    margin: '0 0 0 15',
                    name: 'customerContact',
                    itemId: 'customerContact',
                    fieldLabel: '客户联系电话',
                    id: 'customerContact',
                    readOnly: true
                } ]
            }, {
                xtype: 'textfield',
                name: 'type',
                id: 'orderUpdateType',
                hidden: true
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    allowBlank: false,
                    blankText: '负责人不能为空！',
                    xtype: 'textfield',
                    id: 'ourRepresentative',
                    name: 'ourRepresentative',
                    itemId: 'ourRepresentative',
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
                    id: 'ourTelephone',
                    name: 'ourTelephone',
                    fieldLabel: '负责人联系电话',
                    vtype: 'phone',
                    labelSeparator: redStar,
                    enforceMaxLength: true,
                    maxLength: 11,
                    maxLengthText: "不能超过11个字符！"
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
                height: 200,
                items: [ {
                    xtype: 'buyProduct'
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    margin: '10 20 0 0',
                    itemId: 'bindEvent',
                    xtype: 'combobox',
                    id: 'eventID',
                    queryMode: 'local',
                    fieldLabel: '销售事件',
                    listConfig: {
                        loadMask: false
                    },
                    editable: false,
                    store: this.getEventStore,
                    displayField: 'eventName',
                    valueField: 'eventID',
                    name: 'eventID',
                    labelWidth: 60,
                    width: 200
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
