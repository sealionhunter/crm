Ext.define('CRM.view.customerManagement.cooperator.CooperatorUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.cooperatorupdate',
    autoShow: true,
    frame: false,
    closable: true,
    width: 560,
    id: 'cooperatorUpdate',
    height: 500,
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
                labelWidth: 80,
                margin: '5 0 0 10'
            },
            items: [ {
                layout: 'hbox',
                width: 500,
                items: [ {
                    xtype: 'hiddenfield',
                    name: 'cooperatorID'
                }, {
                    xtype: 'textfield',
                    fieldLabel: '公司名称',
                    labelSeparator: redStar,
                    labelWidth: 80,
                    name: 'cooperatorName',
                    enforceMaxLength: true,
                    maxLength: 50,
                    maxLengthText: '公司名称长度不能超过50个字符！',
                    allowBlank: false,
                    blankText: '公司名称不能为空！'
                }, {
                    xtype: 'textfield',
                    fieldLabel: '所在城市',
                    labelSeparator: redStar,
                    labelWidth: 80,
                    name: 'cooperatorCity',
                    enforceMaxLength: true,
                    maxLength: 20,
                    maxLengthText: '所在城市长度不能超过20个字符！',
                    allowBlank: false,
                    blankText: '所在城市不能为空！',
                    margin: '0 0 0 15'
                } ]
            }, {
                layout: 'hbox',
                width: 500,
                items: [ {
                    xtype: 'combo',
                    fieldLabel: "所属行业",
                    labelSeparator: redStar,
                    labelWidth: 80,
                    name: 'cooperatorIndustry',
                    queryMode: 'local',
                    store: Ext.getCmp('cooperatorList').industryStore,
                    displayField: 'value',
                    valueField: 'code',
                    emptyText: '请选择',
                    triggerAction: 'all',
                    forceSelection: true,
                    allowBlank: false,
                    blankText: '所属行业不能为空',
                    editable: false,
                    listConfig: {
                        emptyText: '未找到匹配值',
                        maxHeight: 120
                    },
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                }, {
                    xtype: 'combo',
                    fieldLabel: "类型",
                    labelSeparator: redStar,
                    labelWidth: 80,
                    name: 'cooperatorType',
                    queryMode: 'local',
                    store: Ext.getCmp('cooperatorList').typeStore,
                    displayField: 'value',
                    valueField: 'code',
                    emptyText: '请选择',
                    triggerAction: 'all',
                    forceSelection: true,
                    allowBlank: false,
                    blankText: '合作伙伴类型不能为空！',
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
                width: 500,
                items: [ {
                    xtype: 'combo',
                    fieldLabel: "人员规模",
                    labelSeparator: redStar,
                    labelWidth: 80,
                    name: 'cooperatorScale',
                    queryMode: 'local',
                    store: Ext.getCmp('cooperatorList').scaleStore,
                    displayField: 'value',
                    valueField: 'code',
                    emptyText: '请选择',
                    triggerAction: 'all',
                    forceSelection: true,
                    allowBlank: false,
                    blankText: '人员规模不能为空！',
                    editable: false,
                    listConfig: {
                        emptyText: '未找到匹配值',
                        maxHeight: 120
                    },
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                }, {
                    xtype: 'textfield',
                    fieldLabel: '网址',
                    labelWidth: 80,
                    name: 'cooperatorWebsite',
                    vtype: 'url',
                    enforceMaxLength: true,
                    maxLength: 50,
                    maxLengthText: '网址长度不能超过50个字符！',
                    margin: '0 0 0 15'
                } ]
            }, {
                layout: 'hbox',
                width: 500,
                items: [ {
                    xtype: 'textfield',
                    fieldLabel: '标准地址',
                    labelWidth: 80,
                    name: 'cooperatorAddress',
                    enforceMaxLength: true,
                    maxLength: 50,
                    maxLengthText: '标准地址长度不能超过50个字符！'
                }, {
                    xtype: 'textfield',
                    fieldLabel: '电话',
                    labelWidth: 80,
                    name: 'cooperatorTelephone',
                    enforceMaxLength: true,
                    maxLength: 12,
                    vtype: 'phone',
                    margin: '0 0 0 15'
                } ]
            }, {
                layout: 'hbox',
                width: 500,
                items: [ {
                    xtype: 'textfield',
                    fieldLabel: '传真',
                    labelWidth: 80,
                    enforceMaxLength: true,
                    maxLength: 11,
                    name: 'cooperatorFax',
                    vtype: 'fax'
                }, {
                    xtype: 'textfield',
                    fieldLabel: '邮箱',
                    labelWidth: 80,
                    name: 'cooperatorEmail',
                    enforceMaxLength: true,
                    maxLength: 50,
                    maxLengthText: '邮箱长度不能超过50个字符',
                    vtype: 'email',
                    margin: '0 0 0 15'
                } ]
            }, {
                xtype: 'textfield',
                fieldLabel: '备注',
                name: 'cooperatorRemark',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: '备注长度不能超过1024个字符！',
                width: 500
            }, {
                xtype: 'textareafield',
                fieldLabel: '公司简介',
                name: 'cooperatorDetail',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: '公司简介长度不能超过1024个字符！',
                width: 500,
                height: 160
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
