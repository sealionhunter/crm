Ext.define('CRM.view.salesManagement.salesEvent.SalesEventUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.salesopportunitiesupdate',
    id: 'salesUpdateWindow',
    layout: 'fit',
    autoShow: true,
    width: 700,
    resizable: false,
    y: 50,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.contactStoreAll = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
        this.contactStoreAll.load();
        this.customerStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName');
        this.contactStoreById = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
        this.statusCombo = Ext.create('CRM.store.salesManagement.salesEventFlowCode.SalesEventFlowCode');
        this.customerStore.load({
            params: {
                userID: USER_ID
            },
            callback: function(records, operation, success) {
                if (records.length == 0) {
                    Ext.Msg.alert('提示', '现在没有客户，请先添加客户！');
                }
            }
        });
        this.statusCombo.load();
        this.items = {
            xtype: 'form',
            defaults: {
                htmlEncode: true,
                bodyStyle: 'padding:10px',
                labelAlign: 'top'
            },
            items: [ {
                title: '<div style="font-size:12px" align="center">销售事件基本信息</div>',
                layout: 'anchor',
                items: [ {
                    xtype: 'form',
                    border: false,
                    layout: 'column',
                    padding: '5 0 5 5',
                    items: [ {
                        columnWidth: 0.5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        defaults: {
                            labelWidth: 80
                        },
                        items: [ {
                            name: 'isAbolished',
                            xtype: 'hiddenfield'
                        }, {
                            name: 'submitterID',
                            xtype: 'hiddenfield',
                            value: USER_ID
                        }, {
                            allowBlank: false,
                            blankText: '销售事件名称不能为空！',
                            id: 'eventName',
                            itemId: 'eventName',
                            name: 'eventName',
                            labelSeparator: redStar,
                            enforceMaxLength: true,
                            fieldLabel: '事件名称',
                            anchor: '90%',
                            maxLength: 50,
                            maxLengthText: "销售事件名称长度不能超过50个字符！"
                        }, {
                            allowBlank: false,
                            blankText: '销售事件状态不能为空！',
                            xtype: 'combobox',
                            itemId: 'status',
                            id: 'salesEventUpdateStatus',
                            fieldLabel: '事件状态',
                            labelSeparator: redStar,
                            store: this.statusCombo,
                            name: 'status',
                            editable: false,
                            queryMode: 'local',
                            displayField: 'value',
                            valueField: 'code',
                            anchor: '90%'
                        }, {
                            fieldLabel: '提交人',
                            name: 'submitPersonName',
                            allowBlank: false,
                            blankText: '提交人姓名不能为空！',
                            id: 'submitPerson',
                            itemId: 'submitPerson',
                            readOnly: true,
                            labelSeparator: redStar,
                            anchor: '90%'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        defaults: {
                            labelWidth: 80
                        },
                        items: [ {
                            name: 'eventID',
                            xtype: 'hiddenfield',
                            itemId: 'eventID'
                        }, {
                            name: 'submitDate',
                            xtype: 'hiddenfield',
                            itemId: 'submitDate'
                        }, {
                            xtype: 'combo',
                            fieldLabel: '客户名称',
                            labelSeparator: redStar,
                            name: 'customerID',
                            itemId: 'customerID',
                            allowBlank: false,
                            editable: false,
                            blankText: "客户名称不能为空！",
                            queryMode: 'local',
                            store: this.customerStore,
                            forceSelection: true,
                            valueField: 'customerID',
                            displayField: 'customerName',
                            anchor: '90%',
                            tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.customerName)]}</li>', '</tpl></ul>')
                        }, {
                            xtype: 'combobox',
                            name: 'contactID',
                            itemId: 'contactID',
                            labelSeparator: redStar,
                            allowBlank: false,
                            editable: false,
                            blankText: "客户联系人不能为空！",
                            id: 'salesCustomerRepresentativeId',
                            fieldLabel: '客户联系人',
                            store: this.contactStoreById,
                            forceSelection: true,
                            queryMode: 'local',
                            displayField: 'contactName',
                            valueField: 'contactID',
                            anchor: '90%',
                            tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.contactName)]}</li>', '</tpl></ul>')
                        } ]
                    } ]
                }, {
                    margin: '0 0 0 5',
                    xtype: 'textarea',
                    name: 'remarks',
                    width: 630,
                    height: 50,
                    labelWidth: 80,
                    fieldLabel: '备注',
                    htmlEncode: true,
                    enforceMaxLength: true,
                    maxLength: 1024,
                    maxLengthText: "备注长度不能超过1024个字符！"
                } ]
            }, {
                xtype: 'tabpanel',
                plain: true,
                height: 300,
                id: 'activeTab',
                title: '<div style="font-size:12px" align="center">销售事件各阶段需求</div>',
                defaults: {
                    bodyStyle: 'padding:5px'
                }
            } ]
        };
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