Ext.define('CRM.view.customerManagement.customerProfiles.CustomerUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.customerupdate',
    layout: 'fit',
    width: 610,
    height: 540,
    border: false,
    autoShow: true,
    modal: true,
    resizable: false,
    constrainHeader: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            width: 610,
            autoScroll: true,
            defaults: {
                border: 0,
                labelWidth: 100,
                width: 550,
                margin: '10 0 0 20'
            },
            items: [ {
                layout: 'hbox',
                items: [ {
                    name: 'customerID',
                    xtype: 'hiddenfield',
                    itemId: 'customerIDHide'
                }, {
                    allowBlank: false,
                    blankText: '客户名称不能为空！',
                    xtype: 'textfield',
                    width: 530,
                    maxLength: 50,
                    maxLengthText: "客户名称为2-50个字符！",
                    enforceMaxLength: true,
                    minLength: 2,
                    minLengthText: "客户名称为2-50个字符！",
                    name: 'customerName',
                    itemId: 'customerName',
                    labelSeparator: redStar,
                    fieldLabel: '客户名称'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    allowBlank: false,
                    blankText: '经营单元不能为空！',
                    xtype: 'combobox',
                    name: 'businessUnit',
                    itemId: 'businessUnit',
                    labelSeparator: redStar,
                    store: Ext.getCmp('customerlist').businessUnitStore,
                    queryMode: 'local',
                    fieldLabel: '经营单元',
                    editable: false,
                    displayField: 'value',
                    valueField: 'code'
                }, {
                    allowBlank: false,
                    blankText: '客户经理不能为空！',
                    xtype: 'combobox',
                    name: 'holder',
                    itemId: 'holder',
                    labelSeparator: redStar,
                    fieldLabel: '客户经理',
                    margin: '0 0 0 25',
                    store: Ext.getCmp('customerlist').userStore,
                    queryMode: 'local',
                    editable: false,
                    displayField: 'realName',
                    valueField: 'userID'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    allowBlank: false,
                    blankText: '客户类型不能为空！',
                    labelSeparator: redStar,
                    queryMode: 'local',
                    fieldLabel: '客户类型',
                    name: 'customerType',
                    itemId: 'customerType',
                    store: Ext.getCmp('customerlist').customerTypeStore,
                    editable: false,
                    displayField: 'value',
                    valueField: 'code'
                }, {
                    xtype: 'combobox',
                    allowBlank: false,
                    blankText: '市场名称不能为空！',
                    labelSeparator: redStar,
                    queryMode: 'local',
                    fieldLabel: '市场名称',
                    margin: '0 0 0 25',
                    name: 'industry',
                    itemId: 'industry',
                   // tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>'),
                    store: Ext.getCmp('customerlist').industryStore,
                    editable: false,
                    displayField: 'value',
                    valueField: 'code'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                  xtype: 'combobox',
                  allowBlank: false,
                  blankText: '所有权不能为空！',
                  labelSeparator: redStar,
                  queryMode: 'local',
                  fieldLabel: '所有权',
                  name: 'fee',
                  itemId: 'fee',
                  store: Ext.getCmp('customerlist').feeStore,
                  editable: false,
                  forceSelection: true,
                  displayField: 'value',
                  valueField: 'code'
              }, {
                    xtype: 'combobox',
                    allowBlank: false,
                    blankText: '价值评估不能为空！',
                    name: 'valueEvaluate',
                    itemId: 'valueEvaluate',
                    fieldLabel: '价值评估',
                    labelSeparator: redStar,
                    margin: '0 0 0 25',
                    store: Ext.getCmp('customerlist').valueEvaluateStore,
                    queryMode: 'local',
                    editable: false,
                    displayField: 'value',
                    valueField: 'code'
                } ]            }, {
                    layout: 'hbox',
                    items: [ {
                        xtype: 'combobox',
                        allowBlank: false,
                        blankText: '客户状态不能为空！',
                        name: 'customerStatement',
                        itemId: 'customerStatement',
                        labelSeparator: redStar,
                        fieldLabel: '客户状态',
                        store: Ext.getCmp('customerlist').customerStatementStore,
                        queryMode: 'local',
                        editable: false,
                        forceSelection: true,
                        displayField: 'value',
                        valueField: 'code'
                    }, {
                        allowBlank: false,
                        blankText: '客户名称不能为空！',
                        labelSeparator: redStar,
                        xtype: 'numberfield',
                        margin: '0 0 0 25',
                        name: 'number',
                        itemId: 'number',
                        minValue: 5,
                        minText: '公司人数不能小于5人！',
                        maxValue: 1000000,
                        maxText: '公司人数不能超过1000000人！',
                        fieldLabel: '公司人数'
                    } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    allowBlank: false,
                    blankText: '注册资金不能为空！',
                    name: 'scale',
                    itemId: 'scale',
                    labelSeparator: redStar,
                    fieldLabel: '注册资金',
                    store: Ext.getCmp('customerlist').scaleStore,
                    queryMode: 'local',
                    editable: false,
                    forceSelection: true,
                    displayField: 'value',
                    valueField: 'code'
                }, {
                    xtype: 'numberfield',
                    margin: '0 0 0 25',
                    name: 'earning',
                    itemId: 'earning',
                    minValue: 0,
                    maxValue: 1000000000,
                    maxText: '年产值最大不超过十万亿！',
                    decimalPrecision: 4,
                    fieldLabel: '年产值(万元)'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    allowBlank: false,
                    blankText: '标准地址不能为空',
                    xtype: 'textfield',
                    enforceMaxLength: true,
                    maxLength: 50,
                    width: 530,
                    maxLengthText: "标准地址最大长度不能超过50个字符！",
                    labelSeparator: redStar,
                    name: 'customerAddr',
                    itemId: 'customerAddr',
                    fieldLabel: '标准地址',
                    emptyText: '客户的具体地址，具体到门牌号！'
                } ]
            }, {
                layout: 'vbox',
                items: [ {
//                    cls: 'x-plain',
                    fieldLabel: '企业简介',
//                    layout: 'fit',
                    xtype: 'textarea',
                    name: 'descriptions',
                    itemId: 'descriptions',
                    enforceMaxLength: true,
                    maxLength: 1024,
                    maxLengthText: "企业简介最大长度不能超过1024个字符！",
                    width: 530,
                    labelWidth: 100,
                    height: 50
                }, {
                  fieldLabel: '联通业务使用情况',
                  xtype: 'textarea',
                  name: 'business1',
                  itemId: 'business1',
                  enforceMaxLength: true,
                  maxLength: 1024,
                  maxLengthText: "联通业务使用情况长度不能超过1024个字符！",
                  width: 530,
                  labelWidth: 100,
                  height: 50,
                  emptyText: "固网、移网业务及月出账信息！"
              }, {
            	  fieldLabel: '竞争对手业务使用情况',
                  xtype: 'textarea',
                  name: 'business2',
                  itemId: 'business2',
                  enforceMaxLength : true,
                  maxLength: 1024,
                  maxLengthText: "竞争对手业务使用情况长度不能超过1024个字符！",
                  width: 530,
                  labelWidth: 100,
                  height: 50,
                  emptyText: "固网、移网业务及资费或其它特殊优惠政策！"
              }, {
                  fieldLabel: '移动网络资源情况',
                  xtype: 'textarea',
                  name: 'business3',
                  itemId: 'business3',
                  enforceMaxLength : true,
                  maxLength: 1024,
                  maxLengthText: "竞争对手业务使用情况长度不能超过1024个字符！",
                  width: 530,
                  height: 100,
                  height: 50,
                  emptyText: "3G或4G的室内、室外信号情况！"
              }, {
                  fieldLabel: '固网资源情况',
                  xtype: 'textarea',
                  name: 'business4',
                  itemId: 'business4',
                  enforceMaxLength : true,
                  maxLength: 1024,
                  maxLengthText: "竞争对手业务使用情况长度不能超过1024个字符！",
                  width: 530,
                  height: 100,
                  height: 50,
                  emptyText: "是否整体覆盖、主干光纤是否进入或联通接入方式情况！"
              }, {
                  fieldLabel: '创建时间',
                  xtype: 'hiddenfield',
                  name: 'createTime'
              }, {
                  fieldLabel: '更新时间',
                  xtype: 'hiddenfield',
                  name: 'updateTime'
              }]
            }
            /*
            , {
                xtype: 'tabpanel',
                plain: true,
                activeTab: 0,
                width: 550,
                defaults: {
                    bodyStyle: 'padding:10px'
                },
                items: [ {
                    cls: 'x-plain',
                    title: '联通业务使用情况',
                    layout: 'fit',
                    xtype: 'textarea',
                    name: 'business1',
                    itemId: 'business1',
                    enforceMaxLength : true,
                    maxLength: 1024,
                    maxLengthText: "联通业务使用情况长度不能超过1024个字符！",
                    width: 550,
                    height: 140,
                    emptyText: "固网业务及月出账:\r\n\r\n\r\n移网业务及月出账:\r\n\r\n\r\n其它产品:"
                }, {
                    cls: 'x-plain',
                    title: '竞争对手业务使用情况',
                    layout: 'fit',
                    xtype: 'textarea',
                    name: 'business2',
                    itemId: 'business2',
                    enforceMaxLength : true,
                    maxLength: 1024,
                    maxLengthText: "竞争对手业务使用情况长度不能超过1024个字符！",
                    width: 550,
                    height: 140,
                    emptyText: "固网业务及资费:\r\n\r\n\r\n移网业务及资费:\r\n\r\n\r\n其它特殊优惠政策:"
                }, {
                    cls: 'x-plain',
                    title: '移动网络资源情况',
                    layout: 'fit',
                    xtype: 'textarea',
                    name: 'business3',
                    itemId: 'business3',
                    enforceMaxLength : true,
                    maxLength: 1024,
                    maxLengthText: "竞争对手业务使用情况长度不能超过1024个字符！",
                    width: 550,
                    height: 140,
                    emptyText: "3G:\r\n室外信号情况:\r\n\r\n室内信号情况:\r\n\r\n4G:\r\n室外信号情况:\r\n\r\n室内信号情况:"
                }, {
                    cls: 'x-plain',
                    title: '固网资源情况',
                    layout: 'fit',
                    xtype: 'textarea',
                    name: 'business4',
                    itemId: 'business4',
                    enforceMaxLength : true,
                    maxLength: 1024,
                    maxLengthText: "竞争对手业务使用情况长度不能超过1024个字符！",
                    width: 550,
                    height: 140,
                    emptyText: "是否整体覆盖:\r\n\r\n\r\n联通接入方式:\r\n\r\n\r\n主干光纤是否进入:"
                } ]
            }
            */
            
            ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'update'
        }, {
            itemId: 'cusReset',
            action: 'doresetCustomer'
        }, {
            text: '取消',
            action: 'closeCustomer'
        } ];
        this.callParent(arguments);
    }

});