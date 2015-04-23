Ext.define('CRM.view.customerManagement.customerProfiles.BusinessUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.businessupdate',
    layout: 'fit',
    width: 610,
    height: 330,
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
                    name: 'businessId',
                    xtype: 'hiddenfield',
                    itemId: 'businessId'
                }, {
                    name: 'customerID',
                    xtype: 'hiddenfield',
                    itemId: 'customerID'
                }, {
                    name: 'createTime',
                    xtype: 'hiddenfield',
                    itemId: 'createTime'
                }, {
                    name: 'updateTime',
                    xtype: 'hiddenfield',
                    itemId: 'updateTime'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    allowBlank: false,
                    blankText: '业务类型不能为空！',
                    xtype: 'combobox',
                    name: 'businessType',
                    itemId: 'businessType',
                    labelSeparator: redStar,
                    store: Ext.getCmp('businesslist').businessTypeStore,
                    queryMode: 'local',
                    fieldLabel: '业务类型',
                    editable: false,
                    displayField: 'value',
                    valueField: 'code'
                }, {
                    allowBlank: false,
                    blankText: '合同年限不能为空！',
                    labelSeparator: redStar,
                    xtype: 'numberfield',
                    margin: '0 0 0 25',
                    name: 'contractYear',
                    itemId: 'contractYear',
                    minValue: 1,
                    minText: '合同年限不能小于1！',
                    maxValue: 100,
                    maxText: '合同年限不能超过100！',
                    fieldLabel: '合同年限'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    allowBlank: false,
                    blankText: '数量不能为空！',
                    labelSeparator: redStar,
                    xtype: 'numberfield',
                    name: 'contractNumber',
                    itemId: 'contractNumber',
                    minValue: 1,
                    minText: '数量不能小于1人！',
                    maxValue: 1000000,
                    maxText: '数量不能超过1000000人！',
                    fieldLabel: '数量'
                }, {
                    allowBlank: false,
                    blankText: '合同金额不能为空！',
                    labelSeparator: redStar,
                    xtype: 'numberfield',
                    margin: '0 0 0 25',
                    name: 'contractMoney',
                    itemId: 'contractMoney',
                    minValue: 1,
                    minText: '合同金额不能小于1！',
                    maxValue: 1000000,
                    maxText: '合同金额不能超过1000000元！',
                    fieldLabel: '合同金额（元）'
                } ]
            }, {
                allowBlank: false,
                blankText: '办理日期不能为空！',
                fieldLabel: '办理日期',
                name: 'startDate',
                itemId: 'startDate',
                xtype: 'datefield',
                format: 'Y-m-d',
                invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                width: 250,
                labelWidth: 100
            } , {
                fieldLabel: '备考',
                xtype: 'textarea',
                name: 'descriptions',
                itemId: 'descriptions',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "备考最大长度不能超过1024个字符！",
                width: 530,
                labelWidth: 100,
                height: 100
            } ]
        }];
        this.buttons = [ {
            text: '确定',
            action: 'addOrUpdateBusiness'
        }, {
            text: '重置',
            action: 'resetBusiness'
        }, {
            text: '取消',
            action: 'closeWin'
        }];
        this.callParent(arguments);
    }
});