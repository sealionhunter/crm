Ext.define('CRM.view.customerManagement.proposalOrContract.ContractUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.contractupdate',
    id: 'contractupdate',
    constrainHeader: true,
    width: 580,
    y: 50,
    autoShow: true,
    layout: 'fit',
    resizable: false,
    modal: true,
    title: '合同基本信息',
    initComponent: function() {
        this.contractstore = Ext.create('CRM.store.customerManagement.proposalOrContract.FileTemplateNameStore');
        this.contractstore.load({
            params: {
                type: 1
            }
        });
        this.payTypeUpdateStore = Ext.create('CRM.store.code.Code');
        this.payTypeUpdateStore.load({
            params: {
                code: '00080001'
            }
        });
        this.items = [ {
            xtype: 'form',
            id: 'contractInfoForm',
            bodyStyle: 'padding:5px 5px 5px;background:#ffffff;',
            defaults: {
                border: 0
            },
            items: [ {
                xtype: 'container',
                layout: 'column',
                items: [ {
                    xtype: 'container',
                    columnWidth: .5,
                    layout: 'anchor',
                    defaults: {
                        labelWidth: 85
                    },
                    items: [ {
                        xtype: 'hidden',
                        name: 'ContractID'
                    }, {
                        xtype: 'hidden',
                        name: 'type',
                        id: 'typeNum'
                    }, {
                        margin: '5 0 10 0',
                        xtype: 'textfield',
                        fieldLabel: '合同名称',
                        labelSeparator: redStar,
                        id: 'contractName',
                        name: 'contractName',
                        maxLength: 50,
                        enforceMaxLength: true,
                        maxLengthText: "合同名称长度不能超过50个字符！",
                        allowBlank: false,
                        blankText: '合同名称不能为空！',
                        anchor: '96%'
                    }, {
                        margin: '0 0 10 0',
                        xtype: 'mouthfield',
                        fieldLabel: '付款截止时间',
                        format: 'Y年m月',
                        altFormats: 'm,d,Y|m.d.Y',
                        labelSeparator: redStar,
                        editable: false,
                        id: 'payDate',
                        name: 'payDate',
                        allowBlank: false,
                        blankText: '付款截止日期不能为空！',
                        anchor: '96%'
                    }, {
                        margin: '0 0 10 0',
                        xtype: 'combobox',
                        fieldLabel: '付款方式',
                        id: 'payType',
                        labelSeparator: redStar,
                        store: this.payTypeUpdateStore,
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
                        margin: '5 0 10 0',
                        fieldLabel: '选择模板',
                        xtype: 'combo',
                        id: 'contractcombo',
                        name: 'fileTemplateName',
                        hiddenName: 'fileTemplateName',
                        anchor: '99%',
                        forceSelection: true,
                        queryMode: 'local',
                        editable: false,
                        store: this.contractstore,
                        valueField: 'fileTemplateName',
                        displayField: 'fileTemplateName',
                        tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.fileTemplateName)]}</li>', '</tpl></ul>')

                    }, {
                        margin: '0 0 10 0',
                        xtype: 'textfield',
                        fieldLabel: '订单编号',
                        id: 'orderID',
                        name: 'orderID',
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
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '订单id',
                        id: 'orderKey',
                        hidden: true
                    } ]
                } ]
            }, {
                xtype: 'hidden',
                name: 'proposalOrContractAddDate',
                format: 'Y-m-d',
                id: 'proposalOrContractAddDate'
            }, {
                xtype: 'hidden',
                name: 'proposalOrContractEditDate',
                format: 'Y-m-d',
                id: 'proposalOrContractEditDate'
            } ],
            buttons: [ {
                text: '下一步',
                action: 'createContract'
            }, {
                text: '清空',
                action: 'resetContract',
                id: 'resetContract'
            }, {
                text: '取消',
                action: 'cancelContract'
            } ]
        } ];
        this.callParent(arguments);
    }
});