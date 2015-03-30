Ext.define('CRM.view.customerManagement.competitor.CompetitorUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.competitorinfoedit',
    id: 'competitorinfoedit',
    layout: 'fit',
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.staffStore = Ext.create('CRM.store.code.Code');
        this.items = [ {
            xtype: 'form',
            layout: 'vbox',
            id: 'addForm',
            bodyPadding: 10,
            width: 580,
            height: 500,
            border: 0,
            items: [ {
                xtype: 'panel',
                layout: 'hbox',
                margin: 3,
                width: 550,
                border: 0,
                items: [ {
                    xtype: 'textfield',
                    id: 'competitorName',
                    labelSeparator: redStar,
                    fieldLabel: '竞争对手名称 ',
                    htmlEncode: true,
                    name: 'competitorName',
                    allowBlank: false,
                    blankText: '竞争对手名称不能为空！',
                    maxLength: 30,
                    enforceMaxLength: true,
                    maxLengthText: '竞争对手名称长度不能超过30个字符！'
                }, {
                    xtype: 'textfield',
                    id: 'competitorLocation',
                    labelSeparator: redStar,
                    fieldLabel: '地理位置 ',
                    htmlEncode: true,
                    name: 'competitorLocation',
                    emptyText: '国家省市',
                    allowBlank: false,
                    blankText: '地理位置不能为空！',
                    maxLength: 20,
                    margin: '0 0 0 15',
                    enforceMaxLength: true,
                    maxLengthText: '地理位置长度不能超过20个字符！'
                } ]
            }, {
                xtype: 'panel',
                layout: 'hbox',
                margin: 3,
                border: 0,
                width: 550,
                items: [ {
                    xtype: 'hiddenfield',
                    itemId: 'competitorInfoId',
                    fieldLabel: '竞争对手ID',
                    name: 'competitorInfoId'
                }, {
                    xtype: 'datefield',
                    id: 'competitorFoundDate',
                    labelSeparator: redStar,
                    fieldLabel: '成立时间 ',
                    htmlEncode: true,
                    name: 'competitorFoundDate',
                    format: 'Y-m-d',
                    allowBlank: false,
                    blankText: '成立时间不能为空！',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01'
                }, {
                    xtype: 'fieldcontainer',
                    labelSeparator: redStar,
                    fieldLabel: '对手性质 ',
                    margin: '0 0 0 15',
                    items: [ {
                        xtype: 'combobox',
                        store: Ext.getCmp('competitorInfo').propertyStore,
                        id: 'competitorProperty',
                        name: 'competitorProperty',
                        queryMode: 'local',
                        displayField: 'value',
                        valueField: 'code',
                        editable: false,
                        allowBlank: false,
                        blankText: '对手性质不能为空！',
                        emptyText: '请选择一种对手性质...'
                    } ]
                } ]
            }, {
                xtype: 'panel',
                layout: 'hbox',
                width: 550,
                margin: 3,
                height: 27,
                border: 0,
                items: [ {
                    xtype: 'fieldcontainer',
                    labelSeparator: redStar,
                    fieldLabel: '资金力量 ',
                    items: [ {
                        xtype: 'combobox',
                        store: Ext.getCmp('competitorInfo').moneyStore,
                        id: 'competitorMoney',
                        name: 'competitorMoney',
                        queryMode: 'local',
                        displayField: 'value',
                        valueField: 'code',
                        editable: false,
                        allowBlank: false,
                        blankText: '资金力量不能为空！',
                        emptyText: '请选择一种资金力量'
                    } ]
                }, {
                    xtype: 'fieldcontainer',
                    fieldLabel: '员工规模',
                    margin: '0 0 0 15',
                    items: [ {
                        xtype: 'combobox',
                        store: this.staffStore,
                        id: 'competitorPeople',
                        name: 'competitorPeople',
                        queryMode: 'local',
                        displayField: 'value',
                        editable: false,
                        valueField: 'code',
                        emptyText: '请选择人数...'
                    } ]
                } ]
            }, {
                xtype: 'panel',
                layout: 'hbox',
                width: 550,
                margin: 3,
                border: 0,
                items: [ {
                    xtype: 'textfield',
                    id: 'competitorField',
                    labelSeparator: redStar,
                    fieldLabel: '竞争领域',
                    htmlEncode: true,
                    name: 'competitorField',
                    allowBlank: false,
                    blankText: '竞争领域不能为空！',
                    maxLength: 30,
                    enforceMaxLength: true,
                    maxLengthText: '竞争领域长度不能超过30个字符！'
                }, {
                    xtype: 'fieldcontainer',
                    labelSeparator: redStar,
                    fieldLabel: '竞争类型 ',
                    margin: '0 0 0 15',
                    items: [ {
                        xtype: 'combobox',
                        store: Ext.getCmp('competitorInfo').typeStore,
                        id: 'competitorType',
                        name: 'competitorType',
                        queryMode: 'local',
                        editable: false,
                        allowBlank: false,
                        blankText: '竞争类型不能为空！',
                        displayField: 'value',
                        valueField: 'code',
                        queryMode: 'local',
                        emptyText: '请选择一种类型...'
                    } ]
                } ]
            }, {
                margin: 3,
                xtype: 'htmleditor',
                id: 'competitorDetail',
                cls: 'x-plain',
                name: 'competitorDetail',
                fieldLabel: '详细信息',
                height: 200,
                width: 541,
                maxLength: 2048,
                enforceMaxLength: true,
                maxLengthText: '详细信息长度不能超过2048个字符！'
            }, {
                margin: 3,
                xtype: 'textareafield',
                id: 'competitorDescription',
                fieldLabel: '备注',
                htmlEncode: true,
                name: 'competitorDescription',
                height: 100,
                width: 541,
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: '备注长度不能超过1024个字符！'
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'add'
        }, {
            text: '清空',
            action: 'addreset'
        }, {
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});