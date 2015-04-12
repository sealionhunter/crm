Ext.define('CRM.view.index.WorkUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.workupdate',
    title: '添加任务',
    id: 'workupdate',
    layout: 'fit',
    width: 610,
    height: 520,
    border: false,
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.customerStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName');
        this.assigneeStore = Ext.create('CRM.store.customerManagement.customerProfiles.UserByProjectTeam');
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
                name: 'workID',
                xtype: 'textfield',
                hidden: true
            }, {
                name: 'userID',
                itemId: 'userID',
                xtype: 'textfield',
                value: USER_ID,
                hidden: true
            }, {
                name: 'teamFlag',
                xtype: 'textfield',
                id: 'workTeamFlag',
                hidden: true
            }, {
                layout: 'hbox',
                items: [ {
                    allowBlank: false,
                    blankText: '主题不能为空！',
                    labelSeparator: redStar,
                    xtype: 'textfield',
                    name: 'theme',
                    itemId: 'theme',
                    width: 530,
                    maxLength: 50,
                    enforceMaxLength: true,
                    maxLengthText: "主题长度不能超过50个字符！",
                    fieldLabel: '主题'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    fieldLabel: '客户',
                    name: 'customerID',
                    itemId: 'customerID',
                    displayField: 'customerName',
                    valueField: 'customerID',
                    store: this.customerStore,
                    queryMode: 'local',
                    editable: false,
                    value: '0',
                    forceSelection: true
                }, {
                    xtype: 'combobox',
                    allowBlank: false,
                    blankText: '完成情况不能为空！',
                    name: 'completion',
                    itemId: 'completion',
                    margin: '0 0 0 25',
                    labelSeparator: redStar,
                    fieldLabel: '完成情况',
                    store: Ext.getCmp('myWorking').completionStore,
                    queryMode: 'local',
                    editable: false,
                    forceSelection: true,
                    displayField: 'value',
                    valueField: 'code'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    allowBlank: false,
                    editable: false,
                    blankText: '优先级不能为空！',
                    name: 'priority',
                    itemId: 'priority',
                    labelSeparator: redStar,
                    fieldLabel: '优先级',
                    store: Ext.getCmp('myWorking').priorityStore,
                    queryMode: 'local',
                    forceSelection: true,
                    displayField: 'value',
                    valueField: 'code'
                }, {
                    xtype: 'combobox',
                    allowBlank: false,
                    editable: false,
                    blankText: '工作类型不能为空！',
                    name: 'workType',
                    itemId: 'workType',
                    labelSeparator: redStar,
                    margin: '0 0 0 25',
                    fieldLabel: '工作类型',
                    store: Ext.getCmp('myWorking').workTypeStore,
                    queryMode: 'local',
                    forceSelection: true,
                    displayField: 'value',
                    valueField: 'code'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'datetimefield',
                    fieldLabel: '开始时间',
                    name: 'startTime',
                    itemId: 'startTime',
                    format: 'Y-m-d H:i:s',
                    allowBlank: false,
                    blankText: '开始时间不能为空！',
                    labelSeparator: redStar,
                    value: new Date(),
                    id: 'workStratTime',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01！',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                    dateRange: {
                        begin: 'workStratTime',
                        end: 'workEndTime'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datetimefield',
                    fieldLabel: '结束时间',
                    margin: '0 0 0 25',
                    itemId: 'endTime',
                    name: 'endTime',
                    format: 'Y-m-d H:i:s',
                    id: 'workEndTime',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01！',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                    dateRange: {
                        begin: 'workStratTime',
                        end: 'workEndTime'
                    },
                    vtype: 'dateRange'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'checkboxfield',
                    fieldLabel: '邮件通知',
                    name: 'isMailInformed',
                    id: 'isMailInformed',
                    disabled: true
                }, {
                    xtype: 'combobox',
                    fieldLabel: '被分配人',
                    margin: '0 0 0 83',
                    name: 'assignees',
                    id: 'workAssignee',
                    itemId: 'assignee',
                    store: this.assigneeStore,
                    queryMode: 'local',
                    disabled: true,
                    allowBlank: false,
                    blankText: '被分配人不能为空！',
                    labelSeparator: redStar,
                    editable: false,
                    multiSelect: true,
                    forceSelection: true,
                    displayField: 'realName',
                    valueField: 'userID'
                } ]
            }, {
                xtype: 'textarea',
                name: 'workDetail',
                itemId: 'workDetail',
                maxLength: 2048,
                enforceMaxLength: true,
                maxLengthText: "任务内容长度不能超过2048个字符！",
                width: 530,
                height: 150,
                fieldLabel: '任务内容'
            }, {
                xtype: 'textarea',
                name: 'descriptions',
                itemId: 'descriptions',
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "备注长度不能超过1024个字符！",
                width: 530,
                height: 50,
                fieldLabel: '备注'
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'updateWork'
        }, {
            text: '清空',
            itemId: 'reset',
            action: 'doresetWork'
        }, {
            text: '取消',
            action: 'closeWork'
        } ];
        this.callParent(arguments);
    }
});