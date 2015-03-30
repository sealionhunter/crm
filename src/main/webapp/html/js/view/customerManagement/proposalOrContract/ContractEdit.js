Ext.define('CRM.view.customerManagement.proposalOrContract.ContractEdit', {
    extend: 'Ext.window.Window',
    alias: 'widget.contractedit',
    id: 'contractEdit',
    title: '编辑合同',
    constrainHeader: true,
    width: 580,
    height: 570,
    y: 10,
    autoShow: true,
    layout: 'fit',
    resizable: false,
    modal: true,
    initComponent: function() {
        this.proposalstore = Ext.create('CRM.store.customerManagement.proposalOrContract.FileTemplateNameStore');
        this.proposalstore.load({
            params: {
                type: 1
            }
        });
        this.payTypeEditStore = Ext.create('CRM.store.code.Code');
        this.payTypeEditStore.load({
            params: {
                code: '00080001'
            }
        });
        this.items = [ {
            xtype: 'form',
            id: 'proposalInfoForm',
            bodyStyle: 'padding:5px;background:#ffffff',
            labelWidth: 80,
            items: [ {
                xtype: 'container',
                layout: 'column',
                defaults: {
                    labelWidth: 80
                },
                items: [ {
                    xtype: 'container',
                    columnWidth: .5,
                    layout: 'anchor',
                    items: [ {
                        xtype: 'hidden',
                        name: 'ContractID',
                        id: 'contractID'
                    }, {
                        margin: '0 0 10 0',
                        xtype: 'textfield',
                        fieldLabel: '合同名称',
                        labelSeparator: redStar,
                        id: 'contractName',
                        name: 'ContractName',
                        maxLength: 50,
                        enforceMaxLength: true,
                        maxLengthText: "名称长度不能超过50字符",
                        allowBlank: false,
                        blankText: '名称不能为空！',
                        anchor: '96%'
                    }, {
                        margin: '0 0 10 0',
                        xtype: 'mouthfield',
                        fieldLabel: '付款截止时间',
                        format: 'Y-m',
                        altFormats: 'Y-m|m.Y',
                        labelSeparator: redStar,
                        editable: false,
                        id: 'payEndTime',
                        name: 'payEndTime',
                        allowBlank: false,
                        enforceMaxLength: true,
                        blankText: '付款截止日期不能为空！',
                        anchor: '96%'
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '订单id',
                        id: 'orderKey',
                        name: 'orderKey',
                        value: 5,
                        hidden: true
                    }, {
                        margin: '0 0 10 0',
                        xtype: 'combobox',
                        fieldLabel: '付款方式',
                        id: 'payType',
                        labelSeparator: redStar,
                        store: this.payTypeEditStore,
                        name: 'proposalOrContractObject',
                        queryMode: 'local',
                        displayField: 'value',
                        valueField: 'code',
                        editable: false,
                        allowBlank: false,
                        blankText: '付款方式不能为空！',
                        anchor: '96%',
                        tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                    } ]
                }, {
                    xtype: 'container',
                    columnWidth: .5,
                    layout: 'anchor',
                    defaults: {
                        labelWidth: 88
                    },
                    items: [ {
                        margin: '0 0 10 0',
                        fieldLabel: '选择模板',
                        xtype: 'combo',
                        id: 'fileTemplateName',
                        name: 'fileTemplateName',
                        anchor: '99%',
                        forceSelection: true,
                        queryMode: 'local',
                        store: this.proposalstore,
                        valueField: 'fileTemplateName',
                        displayField: 'fileTemplateName',
                        tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{' + '[Ext.htmlEncode(values.fileTemplateName)]}</li>', '</tpl></ul>')
                    }, {
                        margin: '0 0 10 0',
                        xtype: 'textfield',
                        fieldLabel: '订单编号',
                        id: 'orderID',
                        readOnly: true,
                        anchor: '99%'
                    }, {
                        width: 70,
                        xtype: 'button',
                        text: '绑定订单',
                        action: 'bingOrder'
                    }, {
                        margin: '0 0 0 5',
                        width: 70,
                        xtype: 'button',
                        text: '取消绑定',
                        action: 'cancleBingOrder'
                    } ]
                } ]
            }, {
                xtype: 'htmleditor',
                fieldLabel: '内容',
                labelSeparator: redStar,
                height: 320,
                name: 'ContractValue',
                id: 'contractValue',
                anchor: '100%',
                labelAlign: 'top'
            }, {
                xtype: 'textarea',
                fieldLabel: '中标',
                name: 'zhongbiao',
                id: 'zhongbiao',
                anchor: '100%',
                height: 80,
                maxLength: 1024,
                enforceMaxLength: true,
                labelAlign: 'top',
                maxLengthText: "中标长度不能超过1024字符"
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