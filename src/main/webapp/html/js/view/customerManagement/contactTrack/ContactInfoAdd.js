Ext.define('CRM.view.customerManagement.contactTrack.ContactInfoAdd', {
    extend: 'Ext.window.Window',
    alias: 'widget.contactinfoadd',
    id: 'contactinfoadd',
    layout: 'fit',
    width: 610,
    height: 520,
    autoShow: true,
    resizable: false,
    constrainHeader: true,
    modal: true,
    initComponent: function() {
//        this.customerStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName');
//        this.cutomerContactStore = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
//        this.cutomerContactStoreByCustomer = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
        this.weContactStore = Ext.create('CRM.store.customerManagement.customerProfiles.User');
        this.weContactStore.load({
            params: {
                userID: USER_ID
            }
        });
//        this.customerStore.load({
//            params: {
//                customerFlag: 0,
//                userID: USER_ID
//            }
//        });
        this.items = [ {
            xtype: 'form',
            defaults: {
                border: 0,
                labelWidth: 100,
                width: 550,
                margin: '10 20 0 20'
            },
            items: [ {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    fieldLabel: '主题',
                    name: 'contactTheme',
                    itemId: 'contactTheme',
                    width: 530,
                    maxLength: 50,
                    allowBlank: false,
                    blankText: '主题不能为空！',
                    labelSeparator: redStar,
                    enforceMaxLength: true,
                    maxLengthText: "主题长度不能超过50个字符！"
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    name: 'contactID',
                    id: 'contactID',
                    xtype: 'textfield',
                    hidden: true
                }, {
                    name: 'customerID',
                    itemId: 'customerID',
                    xtype: 'textfield',
                    hidden: true
                }, {
                    name: 'customerName',
                    xtype: 'textfield',
                    fieldLabel: '客户',
                    itemId: 'customerName',
                    readOnly: true
//                }, {
//                    xtype: 'combobox',
//                    fieldLabel: '客户',
//                    labelSeparator: redStar,
//                    id: 'customer',
//                    name: 'customerID',
//                    itemId: 'customer_ID',
//                    store: this.customerStore,
//                    queryMode: 'local',
//                    displayField: 'customerName',
//                    valueField: 'customerID',
//                    emptyText: '请选择',
//                    allowBlank: false,
//                    blankText: '客户不能为空！',
//                    editable: false
                }, {
                    xtype: 'combobox',
                    fieldLabel: '类型',
                    labelSeparator: redStar,
                    id: 'contactType',
                    name: 'contactType',
                    store: Ext.getCmp('contacttrackcontactlist').contactTypeStore,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    margin: '0 0 0 25',
                    emptyText: '请选择',
                    editable: false,
                    allowBlank: false,
                    blankText: '类型不能为空！'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    fieldLabel: '我方联系人',
                    id: 'weContact',
                    name: 'weContact',
                    queryMode: 'local',
                    displayField: 'realName',
                    valueField: 'userID',
                    emptyText: '请选择',
                    labelSeparator: redStar,
                    store: this.weContactStore,
                    blankText: "我方联系人不能为空！",
                    editable: false,
                    allowBlank: false
                }, {
                    xtype: 'combobox',
                    fieldLabel: '对方联系人',
                    id: 'oppositeContact',
                    name: 'oppositeContact',
                    itemId: 'oppositeContact',
                    queryMode: 'local',
                    displayField: 'contactName',
                    valueField: 'contactID',
                    margin: '0 0 0 25',
                    emptyText: '请选择',
                    labelSeparator: redStar,
                    store: Ext.getCmp('contacttrackcontactlist').cutomerContactStore,
                    blankText: "对方联系人不能为空！",
                    editable: false,
                    allowBlank: false
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    store: Ext.getCmp('contacttrackcontactlist').contactWayStore,
                    id: 'contactWay',
                    name: 'contactWay',
                    fieldLabel: '联系方式',
                    labelSeparator: redStar,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    emptyText: '请选择',
                    editable: false,
                    blankText: "联系方式不能为空！",
                    allowBlank: false
                }, {
                    xtype: 'datetimefield',
                    fieldLabel: '预计联系时间',
                    labelSeparator: redStar,
                    id: 'planDateBegin',
                    name: 'planDateBegin',
                    margin: '0 0 0 25',
                    format: 'Y-m-d H:i:s',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                    editable: true,
                    blankText: "预计联系时间不能为空！",
                    allowBlank: false,
                    minValue: new Date(),
                    minText: '预计联系时间不能小于今天'
                } ]
            }, {
                fieldLabel: '联系内容',
                xtype: 'textarea',
                labelSeparator: redStar,
                id: 'contactContent',
                name: 'contactContent',
                height: 180,
                width: 530,
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "联系内容长度不能超过1024个字符！",
                blankText: "联系内容不能为空！",
                allowBlank: false
            }, {
                fieldLabel: '备注',
                xtype: 'textarea',
                id: 'remarks',
                height: 50,
                width: 530,
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "备注长度不能超过1024个字符！",
                name: 'remarks'
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            id: 'infoadd',
            action: 'addSave'
        }, {
            text: '重置',
            id: 'clearInfo',
            action: 'clear'
        }, {
            text: '取消',
            action: 'doCancel'
        } ];
        this.callParent(arguments);
    }
});