Ext.define('CRM.view.systemManagement.organizationManagement.departmentManagement.DepartmentUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.DepartmentUpdate',
    id: 'DepartmentUpdate',
    itemId: 'DepartmentUpdate',
    layout: 'fit',
    width: 610,
    height: 420,
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.userStore = Ext.create('CRM.store.systemManagement.organizationManagement.departmentManagement.DepartmentManager');
        this.departmentStore = Ext.create('CRM.store.systemManagement.organizationManagement.departmentManagement.FatherDepartment');
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
                    name: 'departmentID',
                    xtype: 'hiddenfield'
                }, {
                    xtype: 'textfield',
                    name: 'departmentName',
                    enforceMaxLength: true,
                    allowBlank: false,
                    blankText: '部门名不能为空！',
                    maxLength: 20,
                    maxLengthText: '部门名长度不能超过20个字符！',
                    labelSeparator: redStar,
                    fieldLabel: '部门名称'
                }, {
                    xtype: 'datefield',
                    name: 'createTime',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD',
                    margin: '0 0 0 25',
                    fieldLabel: '创建时间',
                    maxValue: new Date(),
                    maxText: '最大日期应不大于今天！'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'combobox',
                    name: 'fatherDepartmentID',
                    id: 'fatherDepartmentIDCombo1',
                    allowBlank: false,
                    blankText: '上级部门不能为空！',
                    forceSelection: true,
                    store: this.departmentStore,
                    queryMode: 'local',
                    labelSeparator: redStar,
                    displayField: 'departmentName',
                    valueField: 'departmentID',
                    fieldLabel: '上级部门',
                    editable: false
//                    ,
//                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.departmentName)]}</li>', '</tpl></ul>')
                }, {
                    xtype: 'combobox',
                    name: 'departmentManager',
                    id: 'departmentManagerCombo',
                    forceSelection: true,
                    allowBlank: false,
                    blankText: '部门经理不能为空！',
                    margin: '0 0 0 25',
                    labelSeparator: redStar,
                    store: this.userStore,
                    queryMode: 'local',
                    displayField: 'realName',
                    valueField: 'userID',
                    fieldLabel: '部门经理',
                    editable: false
//                    ,
//                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.realName)]}</li>', '</tpl></ul>')
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    name: 'departmentPhone',
                    maxLength: 17,
                    enforceMaxLength: true,
                    regex: /^(0\d{2,3}-)?(\d{7,8})(-(\d{4}|\d{3}|\d{2}|\d{1}))?$/,
                    regexText: '电话号码格式：(010-)4444444(-0001),支持1~4位分机号',
                    maxLengthText: '最大长度不能超过17个字符！',
                    fieldLabel: '部门办公室电话'
                } ]
            }, {
                xtype: 'textarea',
                name: 'departmentDescription',
                itemId: 'departmentDescription',
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: '备注长度不能超过1024个字符！',
                width: 530,
                height: 120,
                fieldLabel: '备注'
            } ]
        } ];

        this.buttons = [ {
            text: '确定',
            action: 'save'
        }, {
            itemId: 'depReset',
            action: 'reset'
        }, {
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});