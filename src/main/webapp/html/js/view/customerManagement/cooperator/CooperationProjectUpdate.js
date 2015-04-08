Ext.define('CRM.view.customerManagement.cooperator.CooperationProjectUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.cooperationprojectupdate',
    id: 'cooperationProjectUpdate',
    autoShow: true,
    frame: false,
    closable: true,
    width: 580,
    height: 520,
    resizable: false,
    layout: 'fit',
    constrainHeader: true,
    modal: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            layout: 'vbox',
            defaults: {
                border: 0,
                labelWidth: 85,
                margin: '5 0 0 10'
            },
            items: [ {
                xtype: 'hidden',
                name: 'cooperatorID',
                itemId: 'cooperatorID'
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'textfield',
                    name: 'cooperationProjectID',
                    hidden: true
                }, {
                    xtype: 'textfield',
                    fieldLabel: '项目名称',
                    labelWidth: 85,
                    labelSeparator: redStar,
                    name: 'projectName',
                    enforceMaxLength: true,
                    maxLength: 50,
                    maxLengthText: "项目名称不能超过50个字符！",
                    allowBlank: false,
                    blankText: '项目名称不能为空！'
                }, {
                    xtype: 'combo',
                    fieldLabel: '状态',
                    labelWidth: 85,
                    labelSeparator: redStar,
                    name: 'projectType',
                    queryMode: 'local',
                    store: Ext.getCmp('cooperationprojectwindow').typeStore,
                    displayField: 'value',
                    valueField: 'code',
                    triggerAction: 'all',
                    forceSelection: true,
                    allowBlank: false,
                    blankText: '状态不能为空！',
                    editable: false,
                    listConfig: {
                        emptyText: '未找到匹配值',
                        maxHeight: 120
                    },
                    margin: '0 0 0 15',
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'datefield',
                    fieldLabel: '预期开始时间',
                    labelWidth: 85,
                    labelSeparator: redStar,
                    name: 'expectedBeginTime',
                    id: 'expectedBeginTime',
                    format: 'Y-m-d',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    allowBlank: false,
                    blankText: '预期开始时间不能为空！',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'expectedBeginTime',
                        end: 'expectedEndTime'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    fieldLabel: '预期结束时间',
                    labelWidth: 85,
                    labelSeparator: redStar,
                    allowBlank: false,
                    blankText: '预期结束时间不能为空！',
                    name: 'expectedEndTime',
                    format: 'Y-m-d',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    id: 'expectedEndTime',
                    dateRange: {
                        begin: 'expectedBeginTime',
                        end: 'expectedEndTime'
                    },
                    vtype: 'dateRange',
                    margin: '0 0 0 15'
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'datefield',
                    fieldLabel: '实际开始时间',
                    labelWidth: 85,
                    name: 'realBeginTime',
                    id: 'realBeginTime',
                    format: 'Y-m-d',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'realBeginTime',
                        end: 'realEndTime'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    fieldLabel: '实际结束时间',
                    labelWidth: 85,
                    name: 'realEndTime',
                    id: 'realEndTime',
                    format: 'Y-m-d',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'realBeginTime',
                        end: 'realEndTime'
                    },
                    vtype: 'dateRange',
                    margin: '0 0 0 15'
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'textfield',
                    fieldLabel: '我方负责人',
                    labelWidth: 85,
                    labelSeparator: redStar,
                    name: 'principalWe',
                    allowBlank: false,
                    blankText: '我方负责人不能为空！',
                    enforceMaxLength: true,
                    maxLength: 20,
                    maxLengthText: "我方负责人不能超过20个字符！"
                }, {
                    xtype: 'numberfield',
                    name: 'projectScale',
                    fieldLabel: '项目总人月数',
                    labelWidth: 85,
                    id: 'projectScaleNumber',
                    allowDecimals: false,
                    minValue: 0,
                    maxValue: 10000,
                    enforceMaxLength: true,
                    maxLength: 6,
                    maxText: '项目总人月数的最大值为10000！',
                    margin: '0 0 0 15',
                    numberRange: {
                        min: 'cooperatorScaleNumber',
                        max: 'projectScaleNumber'
                    },
                    vtype: 'numberRange'
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'numberfield',
                    fieldLabel: '合作方人月数',
                    labelWidth: 85,
                    name: 'cooperatorScale',
                    id: 'cooperatorScaleNumber',
                    allowDecimals: false,
                    enforceMaxLength: true,
                    maxLength: 6,
                    maxValue: 10000,
                    maxText: '合作方人月数的最大值为10000！',
                    minValue: 0,
                    numberRange: {
                        min: 'cooperatorScaleNumber',
                        max: 'projectScaleNumber'
                    },
                    vtype: 'numberRange'
                }, {
                    xtype: 'numberfield',
                    fieldLabel: '合作方人数',
                    labelWidth: 85,
                    minValue: 0,
                    enforceMaxLength: true,
                    maxLength: 6,
                    maxValue: 10000,
                    maxText: '合作方人数的最大值为10000！',
                    name: 'cooperatorPeopleNumber',
                    allowDecimals: false,
                    margin: '0 0 0 15'
                } ]
            }, {
                layout: 'hbox',
                width: 510,
                items: [ {
                    xtype: 'textfield',
                    fieldLabel: '合作方负责人',
                    labelWidth: 85,
                    labelSeparator: redStar,
                    name: 'principalCooperator',
                    allowBlank: false,
                    blankText: '合作方负责人不能为空！',
                    enforceMaxLength: true,
                    maxLength: 20,
                    maxLengthText: "合作方负责人不能超过20个字符！"

                }, {
                    xtype: 'textfield',
                    fieldLabel: '电话',
                    labelWidth: 85,
                    labelSeparator: redStar,
                    vtype: 'phone',
                    name: 'principalCooperatorPhone',
                    allowBlank: false,
                    enforceMaxLength: true,
                    maxLength: 12,
                    blankText: '电话不能为空！',
                    margin: '0 0 0 15'
                } ]
            }, {
                xtype: 'textarea',
                fieldLabel: '合作评价',
                name: 'appraisal',
                emptyText: '项目结束后填写',
                enforceMaxLength: true,
                maxLength: 1024,
                width: 510,
                maxLengthText: "合作评价不能超过1024个字符！"
            }, {
                xtype: 'textarea',
                fieldLabel: '项目介绍',
                name: 'projectDetail',
                width: 510,
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "项目介绍不能超过1024个字符！"
            } ],

            buttons: [ {
                text: '确定',
                action: 'save'
            }, {
                text: '重置',
                action: 'reset'
            }, {
                text: '取消',
                action: 'cancel'
            } ]
        } ];
        this.callParent(arguments);
    }
});
