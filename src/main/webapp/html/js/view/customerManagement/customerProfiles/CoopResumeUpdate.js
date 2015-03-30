Ext.define('CRM.view.customerManagement.customerProfiles.CoopResumeUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.coopresumeupdate',
    id: 'coopresumeupdate',
    layout: 'fit',
    width: 610,
    height: 520,
    border: false,
    autoShow: true,
    modal: true,
    resizable: false,
    constrainHeader: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            layout: 'vbox',
            defaults: {
                border: 0,
                labelWidth: 100,
                width: 550,
                margin: '10 20 0 20'
            },
            items: [ {
                layout: 'hbox',
                items: [ {
                    name: 'coopResumeID',
                    itemId: 'coopResumeID',
                    xtype: 'hidden'
                }, {
                    name: 'customerID',
                    itemId: 'customerID',
                    xtype: 'hidden'
                }, {
                    allowBlank: false,// 不允许为空
                    maxLength: 50,
                    enforceMaxLength: true,
                    maxLengthText: "项目名称最大长度不能超过50个字符！",
                    blankText: '项目名称不能为空！',// 为空时的显示内容
                    xtype: 'textfield',
                    name: 'projectName',
                    itemId: 'projectName',
                    labelSeparator: redStar,
                    fieldLabel: '项目名称'
                }, {
                    allowBlank: false,// 不允许为空
                    blankText: '合作状态不能为空！',// 为空时的显示内容
                    xtype: 'combobox',
                    name: 'projectType',
                    itemId: 'projectType',
//                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>'),
                    margin: '0 0 0 25',
                    labelSeparator: redStar,
                    fieldLabel: '合作状态',
                    store: Ext.getCmp('coopResumelist').projectTypeStore,
                    editable: false,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    allowBlank: false,// 不允许为空
                    blankText: '预期开始日期不能为空！',// 为空时的显示内容
                    xtype: 'datefield',
                    format: 'Y-m-d',
                    fieldLabel: '预期开始日期',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    labelSeparator: redStar,
                    name: 'expBeginDate',
                    itemId: 'expBeginDate',
                    id: 'expBeginDate',
                    vtype: 'dateRange',
                    dateRange: {
                        begin: 'expBeginDate',
                        end: 'expEndDate'
                    }
                }, {
                    allowBlank: false,// 不允许为空
                    blankText: '预期结束日期不能为空！',// 为空时的显示内容
                    xtype: 'datefield',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    format: 'Y-m-d',
                    labelSeparator: redStar,
                    margin: '0 0 0 25',
                    fieldLabel: '预期结束日期',
                    itemId: 'expEndDate',
                    name: 'expEndDate',
                    id: 'expEndDate',
                    vtype: 'dateRange',
                    dateRange: {
                        begin: 'expBeginDate',
                        end: 'expEndDate'
                    }
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'datefield',
                    name: 'beginDate',
                    itemId: 'beginDate',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    fieldLabel: '开始日期',
                    id: 'coop_beginDate',
                    dateRange: {
                        begin: 'coop_beginDate',
                        end: 'coop_endDate'
                    }
                }, {
                    xtype: 'datefield',
                    format: 'Y-m-d',
                    name: 'endDate',
                    itemId: 'endDate',
                    id: 'coop_endDate',
                    margin: '0 0 0 25',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    fieldLabel: '结束日期',
                    dateRange: {
                        begin: 'coop_beginDate',
                        end: 'coop_endDate'
                    }
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    allowBlank: false,// 不允许为空
                    blankText: '项目规模(万元)不能为空！',// 为空时的显示内容
                    xtype: 'numberfield',
                    name: 'projectScale',
                    itemId: 'projectScale',
                    minValue: 0,
                    maxValue: 10000,
                    maxLength: 5,
                    enforceMaxLength: true,
                    maxText: '项目规模(万元)不超过10000',
                    decimalPrecision: 1,
                    labelSeparator: redStar,
                    fieldLabel: '项目规模(万元)'
                }, {
                    allowBlank: false,// 不允许为空
                    blankText: '项目人数不能为空！',// 为空时的显示内容
                    name: 'peopleNumber',
                    itemId: 'peopleNumber',
                    xtype: 'numberfield',
                    minValue: 0,
                    maxValue: 10000,
                    maxLength: 5,
                    enforceMaxLength: true,
                    maxText: '项目人数不超过10000！',
                    allowDecimals: false,
                    margin: '0 0 0 25',
                    labelSeparator: redStar,
                    fieldLabel: '项目人数'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    allowBlank: false,// 不允许为空
                    blankText: '我方负责人不能为空！',// 为空时的显示内容
                    maxLength: 20,
                    minLength: 2,
                    enforceMaxLength: true,
                    minLengthText: "我方负责人为2-20个字符！",
                    maxLengthText: "我方负责人为2-20个字符！",
                    name: 'principalWe',
                    itemId: 'principalWe',
                    labelSeparator: redStar,
                    fieldLabel: '我方负责人'
                }, {
                    xtype: 'textfield',
                    allowBlank: false,// 不允许为空
                    maxLength: 20,
                    maxLengthText: "客户负责人为2-20个字符！",
                    minLength: 2,
                    enforceMaxLength: true,
                    minLengthText: "客户负责人为2-20个字符！",
                    blankText: '客户负责人不能为空！',// 为空时的显示内容
                    name: 'principalThey',
                    itemId: 'principalThey',
                    margin: '0 0 0 25',
                    labelSeparator: redStar,
                    fieldLabel: '客户负责人'
                } ]
            }, {
                xtype: 'textarea',
                name: 'projectDetail',
                itemId: 'projectDetail',
                enforceMaxLength: true,
                maxLength: 1024,
                width: 530,
                fieldLabel: '项目内容'
            }, {
                xtype: 'textarea',
                itemId: 'appraisal',
                enforceMaxLength: true,
                maxLength: 1024,
                name: 'appraisal',
                width: 530,
                fieldLabel: '合作评价'
            }, {
                xtype: 'textarea',
                itemId: 'descriptions',
                enforceMaxLength: true,
                maxLength: 1024,
                name: 'descriptions',
                width: 530,
                fieldLabel: '备注'
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