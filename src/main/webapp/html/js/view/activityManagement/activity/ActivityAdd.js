Ext.define('CRM.view.activityManagement.activity.ActivityAdd', {
    extend: 'Ext.window.Window',
    alias: 'widget.activityadd',
    id: 'activityadd',
    title: '添加营销活动',
    layout: 'fit',
    autoShow: true,
    resizable: false,
    height: 600,
    width: 580,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.customerStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName');
        this.customerStore.load({
            params: {
                userID: USER_ID
            }
        });
        this.items = [ {
            xtype: 'form',
            autoScroll: true,
            layout: 'vbox',
            defaults: {
                border: 0,
                margin: '0 10 0 10'
            },
            items: [ {
                layout: 'hbox',
                width: 500,
                defaults: {
                    margin: '2 10 2 0',
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'textfield',
                    itemId: 'activityID',
                    name: 'activityID',
                    hidden: true
                }, {
                    xtype: 'combobox',
                    fieldLabel: '活动类型',
                    name: 'activityType',
                    itemId: 'activityType',
                    labelSeparator: redStar,
                    allowBlank: false,
                    blankText: '活动类型不能为空！',
                    store: Ext.getCmp('card-activitylist').activityTypeCombo,
                    forceSelection: true,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    editable: false
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'textfield',
                    height: 25,
                    width: 510,
                    name: 'activityName',
                    itemId: 'activityName',
                    fieldLabel: '活动名称',
                    allowBlank: false,
                    blankText: '活动名称不能为空！',
                    maxLength: 30,
                    maxLengthText: "活动名称长度不能超过30个字符！",
                    enforceMaxLength: true,
                    labelSeparator: redStar
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'textfield',
                    name: 'activityPlace',
                    itemId: 'activityPlace',
                    height: 25,
                    width: 510,
                    fieldLabel: '活动地点',
                    allowBlank: false,
                    blankText: '活动地点不能为空！',
                    maxLength: 50,
                    maxLengthText: "活动地点长度不能超过50个字符！",
                    enforceMaxLength: true,
                    labelSeparator: redStar
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'textfield',
                    width: 510,
                    name: 'activityAim',
                    itemId: 'activityAim',
                    fieldLabel: '活动目的',
                    maxLength: 100,
                    maxLengthText: "最大长度不能超过100个字符！",
                    enforceMaxLength: true
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'datefield',
                    name: 'activityStartTime',
                    itemId: 'activityStartTime',
                    id: 'activityStartTime',
                    format: 'Y-m-d',
                    fieldLabel: '开始时间',
                    allowBlank: false,
                    blankText: '开始时间不能为空！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    labelSeparator: redStar,
                    maxLength: 10,
                    enforceMaxLength: true,
                    anchor: '90%',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'activityStartTime',
                        end: 'activityEndTime'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    format: 'Y-m-d',
                    name: 'activityEndTime',
                    itemId: 'activityEndTime',
                    id: 'activityEndTime',
                    fieldLabel: '结束时间',
                    allowBlank: false,
                    maxLength: 10,
                    enforceMaxLength: true,
                    blankText: '结束时间不能为空！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    labelSeparator: redStar,
                    margin: '0 0 0 15',
                    anchor: '90%',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'activityStartTime',
                        end: 'activityEndTime'
                    },
                    vtype: 'dateRange'
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'combobox',
                    name: 'activityEmphasis',
                    itemId: 'activityEmphasis',
                    fieldLabel: '重视程度',
                    store: Ext.getCmp('card-activitylist').activityEmphasisCombo,
                    forceSelection: true,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    allowBlank: false,
                    blankText: "重视程度不能为空！",
                    labelSeparator: redStar,
                    anchor: '90%',
                    editable: false
                }, {
                    xtype: 'combobox',
                    name: 'activityRange',
                    itemId: 'activityRange',
                    fieldLabel: '活动范围',
                    store: Ext.getCmp('card-activitylist').activityRangeCombo,
                    forceSelection: true,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    allowBlank: false,
                    blankText: "活动范围不能为空！",
                    labelSeparator: redStar,
                    margin: '0 0 0 15',
                    anchor: '90%',
                    editable: false
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'textfield',
                    name: 'activityLeader',
                    itemId: 'activityLeader',
                    fieldLabel: '我方负责人',
                    allowBlank: false,
                    blankText: '我方负责人不能为空！',
                    maxLength: 20,
                    maxLengthText: "我方负责人长度不能超过20个字符！",
                    enforceMaxLength: true,
                    nanText: '输入值为非有效数值，请输入正数！',
                    labelSeparator: redStar,
                    anchor: '90%'
                }, {
                    xtype: 'numberfield',
                    name: 'activityOurPersonNO',
                    itemId: 'activityOurPersonNO',
                    fieldLabel: '我方人数',
                    value: 0,
                    allowDecimals: false,
                    maxLength: 8,
                    enforceMaxLength: true,
                    maxValue: '10000000',
                    maxText: '我方人数的最大值为10000000！',
                    nanText: '输入值为非有效数值，请输入正数！',
                    margin: '0 0 0 15',
                    minValue: 0,
                    anchor: '90%'
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'textfield',
                    name: 'activityModifer',
                    itemId: 'activityModifer',
                    fieldLabel: '录入人',
                    labelSeparator: redStar,
                    allowBlank: false,
                    blankText: '录入人不能为空！',
                    maxLength: 20,
                    maxLengthText: "录入人长度不能超过20个字符！",
                    enforceMaxLength: true,
                    anchor: '90%'
                }, {
                    xtype: 'numberfield',
                    name: 'activityFunds',
                    itemId: 'activityFunds',
                    fieldLabel: '活动经费',
                    value: 0,
                    allowDecimals: true,// 是否小数
                    decimalSeparator: '.',// 小数分隔符
                    decimalPrecision: 4,// 小数位数
                    minValue: 0, // 最小值
                    maxLength: 14,
                    enforceMaxLength: true,
                    maxValue: '10000000000000',
                    maxText: '活动经费的最大值为10000000000000！',
                    margin: '0 0 0 15',
                    width: 210,
                    anchor: '90%'
                }, {
                    xtype: 'displayfield',
                    width: 30,
                    value: '万  元'
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                defaults: {
                    htmlEncode: true,
                    labelWidth: 85
                },
                items: [ {
                    xtype: 'combobox',
                    name: 'activityPeriod',
                    itemId: 'activityPeriod',
                    fieldLabel: '活动周期',
                    store: Ext.getCmp('card-activitylist').activityPeriodCombo,
                    forceSelection: true,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    allowBlank: false,
                    blankText: "活动周期不能为空！",
                    labelSeparator: redStar,
                    anchor: '90%',
                    editable: false
                }, {
                    xtype: 'combobox',
                    name: 'activityState',
                    itemId: 'activityState',
                    fieldLabel: '活动状态',
                    store: Ext.getCmp('card-activitylist').activityStateCombo,
                    forceSelection: true,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    allowBlank: false,
                    blankText: "活动状态不能为空！",
                    labelSeparator: redStar,
                    margin: '0 0 0 15',
                    anchor: '90%',
                    editable: false
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'combobox',
                    name: 'customerName',
                    labelWidth: 85,
                    width: 510,
                    fieldLabel: '参加人员',
                    store: this.customerStore,
                    forceSelection: true,
                    multiSelect: true,
                    queryMode: 'local',
                    displayField: 'customerName',
                    valueField: 'customerID',
                    allowBlank: false,
                    blankText: "参加人员不能为空！",
                    labelSeparator: redStar,
                    editable: false,
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.customerName)]}</li>', '</tpl></ul>')
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'textarea',
                    name: 'activityComment',
                    itemId: 'activityComment',
                    labelWidth: 85,
                    width: 510,
                    height: 40,
                    fieldLabel: '备注',
                    maxLength: 1024,
                    enforceMaxLength: true,
                    maxLengthText: "备注长度不能超过1024个字符！"
                } ]
            }, {
                xtype: 'tabpanel',
                plain: true,
                defaults: {
                    height: 170
                },
                items: [ {
                    cls: 'x-plain',
                    title: "<font color='#04408C'>活动计划</font>",
                    layout: 'fit',
                    width: 510,
                    xtype: 'htmleditor',
                    emptyText: '',
                    name: 'activityPlan',
                    itemId: 'activityPlan',
                    htmlEncode: false
                }, {
                    cls: 'x-plain',
                    title: "<font color='#04408C'>详细内容</font>",
                    layout: 'fit',
                    width: 510,
                    xtype: 'htmleditor',
                    name: 'activityDetail',
                    itemId: 'activityDetail',
                    htmlEncode: false
                }, {
                    cls: 'x-plain',
                    title: "<font color='#04408C'>活动意义</font></font>",
                    layout: 'fit',
                    width: 510,
                    xtype: 'textarea',
                    htmlEncode: true,
                    name: 'activityMeans',
                    itemId: 'activityMeans',
                    enforceMaxLength: true,
                    maxLength: 1024,
                    enforceMaxLength: true,
                    maxLengthText: "活动意义长度不能超过1024个字符！"
                } ]
            } ]
        } ];

        this.buttons = [ {
            type: 'submit',
            text: '确定',
            action: 'save'
        }, {
            xtype: 'button',
            text: '清空',
            itemId: 'reset',
            action: 'reset'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});