Ext.define('CRM.view.systemManagement.userManagement.UserUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.UserUpdate',
    id: 'UserUpdate',
    itemId: 'UserUpdate',
    layout: 'fit',
    width: 610,
    height: 520,
    border: false,
    autoShow: true,
    modal: true,
    resizable: false,
    constrainHeader: true,
    initComponent: function() {
        this.departmentStore = Ext.create('CRM.store.systemManagement.userManagement.Department');
        this.projectTeamStore = Ext.create('CRM.store.customerManagement.customerProfiles.ProjectTeam');
        this.groupStore = Ext.create('CRM.store.systemManagement.userManagement.Group');
        this.educationStore = Ext.create('CRM.store.code.Code');
        this.jobTitleStore = Ext.create('CRM.store.code.Code');
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
                    name: 'userID',
                    xtype: 'hiddenfield'
                }, {
                    name: 'password',
                    xtype: 'hiddenfield'
                }, {
                    xtype: 'textfield',
                    name: 'userName',
                    maxLength: 20,
                    maxLengthText: "用户名长度不能超过20个字符！",
                    enforceMaxLength: true,
                    allowBlank: false,
                    blankText: '用户名不能为空！',
                    labelSeparator: redStar,
                    regex: /^[a-zA-Z0-9_]{1,20}$/,
                    regexText: '用户名为1-20位的数字，字母或下划线！',
                    fieldLabel: '用户名'
                }, {
                    xtype: 'textfield',
                    name: 'realName',
                    margin: '0 0 0 25',
                    allowBlank: false,
                    maxLength: 20,
                    enforceMaxLength: true,
                    maxLengthText: "姓名长度不能超过20个字符！",
                    blankText: '姓名不能为空！',
                    labelSeparator: redStar,
                    fieldLabel: '姓名'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    name: 'groupID',
                    allowBlank: false,
                    blankText: '用户角色不能为空！',
                    labelSeparator: redStar,
                    forceSelection: true,
                    fieldLabel: '用户角色',
                    editable: false,
                    store: this.groupStore,
                    queryMode: 'local',
                    displayField: 'groupName',
                    valueField: 'groupID'
                }, {
                    xtype: 'textfield',
                    name: 'jobID',
                    maxLength: 20,
                    enforceMaxLength: true,
                    maxLengthText: "工号长度不能超过20个字符！",
                    allowBlank: false,
                    margin: '0 0 0 25',
                    blankText: '工号不能为空！',
                    regex: /^[0-9]{1,20}$/,
                    regexText: '工号只能是1-20位的数字！',
                    labelSeparator: redStar,
                    fieldLabel: '工号'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    name: 'company',
                    allowBlank: false,
                    blankText: '公司不能为空！',
                    maxLength: 50,
                    enforceMaxLength: true,
                    maxLengthText: "公司长度不能超过50个字符！",
                    labelSeparator: redStar,
                    fieldLabel: '公司'
                }, {
                    xtype: 'datefield',
                    name: 'entryTime',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD',
                    margin: '0 0 0 25',
                    fieldLabel: '入社时间',
                    maxValue: new Date(),
                    maxText: '最大日期应不大于今天！'

                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    name: 'departmentID',
                    forceSelection: true,
                    allowBlank: false,
                    blankText: '部门不能为空！',
                    editable: false,
                    store: this.departmentStore,
                    queryMode: 'local',
                    labelSeparator: redStar,
                    displayField: 'departmentName',
                    valueField: 'departmentID',
                    labelSeparator: redStar,
                    fieldLabel: '部门'
                }, {
                    xtype: 'textfield',
                    margin: '0 0 0 25',
                    name: 'job',
                    maxLength: 30,
                    enforceMaxLength: true,
                    maxLengthText: "职务长度不能超过30个字符！",
                    fieldLabel: '职务'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    name: 'jobTitle',
                    forceSelection: true,
                    editable: false,
                    store: this.jobTitleStore,
                    fieldLabel: '技术职称',
                    maxLength: 5,
                    maxLengthText: "最大长度不能超过5个字符！",
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code'
                }, {
                    xtype: 'textfield',
                    name: 'mobile',
                    blankText: '',
                    allowBlank: false,
                    blankText: '手机号码不能为空！',
                    maxLength: 11,
                    enforceMaxLength: true,
                    margin: '0 0 0 25',
                    maxLengthText: "最大长度不能超过11个字符！",
                    fieldLabel: '手机号码',
                    labelSeparator: redStar,
                    regex: /^1[3-8][0-9]{9}$/,
                    regexText: '手机号码格式：13333333333'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    name: 'officePhone',
                    maxLength: 12,
                    enforceMaxLength: true,
                    regex: /^(0\d{2,3}-)?(\d{7,8})$/,
                    regexText: '电话号码格式：(010-)4444444',
                    maxLengthText: "最大长度不能超过12个字符！",
                    fieldLabel: '办公室电话'
                }, {
                    xtype: 'textfield',
                    name: 'email',
                    allowBlank: false,
                    maxLength: 50,
                    enforceMaxLength: true,
                    margin: '0 0 0 25',
                    maxLengthText: "邮箱长度不能超过50个字符！",
                    blankText: '邮箱不能为空',
                    vtype: 'email',
                    labelSeparator: redStar,
                    fieldLabel: '邮箱'
                } ]
            }, {
                layout: 'hbox',
                width: 540,
                items: [ {
                    xtype: 'combobox',
                    name: 'education',
                    forceSelection: true,
                    store: this.educationStore,
                    fieldLabel: '学历',
                    editable: false,
                    maxLength: 5,
                    maxLengthText: "最大长度不能超过5个字符！",
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code'
                }, {
                    allowBlank: false,
                    blankText: '事前联系间隔不能为空！',
                    labelSeparator: redStar,
                    xtype: 'numberfield',
                    margin: '0 0 0 25',
                    name: 'contactInterval',
                    itemId: 'contactInterval',
                    minValue: 1,
                    minText: '事前联系间隔不能小于1天！',
                    maxValue: 90,
                    maxText: '事前联系间隔不能超过90天！',
                    fieldLabel: '事前联系间隔'
                } ]
            }, {
                xtype: 'textarea',
                name: 'descriptions',
                itemId: 'descriptions',
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "备注长度不能超过1024个字符！",
                width: 530,
                height: 200,
                fieldLabel: '备注'
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'save'
        }, {
            text: '清空',
            action: 'reset'
        }, {
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});