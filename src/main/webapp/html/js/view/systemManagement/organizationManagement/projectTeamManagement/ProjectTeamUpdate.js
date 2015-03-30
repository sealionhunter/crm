Ext.define('CRM.view.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.ProjectTeamUpdate',
    id: 'ProjectTeamUpdate',
    width: 610,
    height: 420,
    layout: 'fit',
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.departmentStore = Ext.create('CRM.store.systemManagement.userManagement.Department');
        this.leaderStore = Ext.create('CRM.store.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamLeaderSelecting');
        this.codeStore = Ext.create('CRM.store.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamStatus');
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
                    name: 'projectTeamID',
                    xtype: 'hiddenfield'
                }, {
                    xtype: 'textfield',
                    name: 'projectTeamName',
                    allowBlank: false,
                    blankText: '团队名称不能为空！',
                    enforceMaxLength: true,
                    maxLength: 15,
                    regex: /^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$/,
                    regexText: '团队名称必须为汉字、字母或数字！',
                    labelSeparator: redStar,
                    fieldLabel: '团队名称'
                }, {
                    xtype: 'combobox',
                    name: 'departmentID',
                    allowBlank: false,
                    blankText: '所属部门不能为空！',
                    forceSelection: true,
                    margin: '0 0 0 25',
//                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.departmentName)]}</li>', '</tpl></ul>'),
                    store: this.departmentStore,
                    queryMode: 'local',
                    labelSeparator: redStar,
                    displayField: 'departmentName',
                    valueField: 'departmentID',
                    fieldLabel: '所属部门',
                    editable: false
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    name: 'projectTeamLeaderID',
                    forceSelection: true,
                    allowBlank: false,
                    blankText: '团队负责人不能为空！',
                    labelSeparator: redStar,
//                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.projectTeamLeaderName)]}</li>', '</tpl></ul>'),
                    store: this.leaderStore,
                    queryMode: 'local',
                    displayField: 'projectTeamLeaderName',
                    valueField: 'projectTeamLeaderID',
                    fieldLabel: '团队负责人',
                    editable: false
                }, {
                    xtype: 'combobox',
                    name: 'projectTeamStatusCode',
                    forceSelection: true,
                    margin: '0 0 0 25',
                    store: this.codeStore,
//                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.projectTeamStatusValue)]}</li>', '</tpl></ul>'),
                    queryMode: 'local',
                    displayField: 'projectTeamStatusValue',
                    valueField: 'projectTeamStatusCode',
                    fieldLabel: '团队状态',
                    editable: false
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'datefield',
                    name: 'createTime',
                    id: 'createTime',
                    editable: true,
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    fieldLabel: '创建时间',
                    dateRange: {
                        begin: 'createTime',
                        end: 'endTime'
                    },
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    vtype: 'dateRange',
                    maxValue: new Date()
                }, {
                    xtype: 'datefield',
                    name: 'endTime',
                    id: 'endTime',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    margin: '0 0 0 25',
                    editable: true,
                    vtype: 'dateRange',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    fieldLabel: '结束时间',
                    dateRange: {
                        begin: 'createTime',
                        end: 'endTime'
                    }
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textarea',
                    name: 'description',
                    enforceMaxLength: true,
                    maxLength: 1024,
                    maxLengthText: '备注长度不能超过1024个字符！',
                    width: 530,
                    height: 120,
                    fieldLabel: '备注'
                } ]
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'save'
        }, {
            text: '重置',
            action: 'reset'
        }, {
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});