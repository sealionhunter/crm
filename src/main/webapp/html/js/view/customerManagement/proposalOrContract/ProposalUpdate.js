Ext.define('CRM.view.customerManagement.proposalOrContract.ProposalUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.proposalupdate',
    id: 'proposalupdate',
    constrainHeader: true,
    width: 580,
    height: 590,
    y: 10,
    autoShow: true,
    layout: 'fit',
    resizable: false,
    modal: true,
    initComponent: function() {
        this.proposalstore = Ext.create('CRM.store.customerManagement.proposalOrContract.FileTemplateNameStore');
        this.proposalstore.load({
            params: {
                type: 2
            }
        });
        this.items = [ {
            xtype: 'form',
            id: 'proposalInfoForm',
            frame: true,
            bodyStyle: 'padding:5px 5px 5px',
            fieldDefaults: {
                labelAlign: 'top'
            },
            items: [ {
                xtype: 'container',
                layout: 'column',
                items: [ {
                    xtype: 'container',
                    columnWidth: .5,
                    layout: 'anchor',
                    items: [ {
                        xtype: 'hidden',
                        name: 'proposalOrContractID'
                    }, {
                        xtype: 'hidden',
                        name: 'type'
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '名称',
                        labelSeparator: redStar,
                        name: 'proposalOrContractName',
                        maxLength: 50,
                        enforceMaxLength: true,
                        maxLengthText: "名称长度不能超过50个字符！",
                        allowBlank: false,
                        blankText: '名称不能为空！',
                        anchor: '96%'
                    }, {
                        fieldLabel: '选择模板',
                        xtype: 'combo',
                        id: 'proposalcombox',
                        name: 'fileTemplateName',
                        editable: false,
                        hiddenName: 'fileTemplateName',
                        anchor: '95%',
                        forceSelection: true,
                        queryMode: 'local',
                        store: this.proposalstore,
                        valueField: 'fileTemplateName',
                        displayField: 'fileTemplateName',
                        tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{' + '[Ext.htmlEncode(values.fileTemplateName)]}</li>', '</tpl></ul>')
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '类型',
                        labelSeparator: redStar,
                        id: 'proposalType',
                        enforceMaxLength: true,
                        name: 'proposalOrContractType',
                        allowBlank: false,
                        blankText: '类型不能为空！',
                        anchor: '96%',
                        maxLength: 50,
                        maxLengthText: "类型长度不能超过50个字符！"
                    } ]
                }, {
                    xtype: 'container',
                    columnWidth: .5,
                    layout: 'anchor',
                    items: [ {
                        xtype: 'textfield',
                        fieldLabel: '对象',
                        labelSeparator: redStar,
                        name: 'proposalOrContractObject',
                        allowBlank: false,
                        enforceMaxLength: true,
                        blankText: '对象不能为空！',
                        anchor: '100%',
                        maxLength: 50,
                        maxLengthText: "对象长度不能超过50个字符！"
                    }, {
                        xtype: 'textarea',
                        fieldLabel: '描述',
                        name: 'descriptions',
                        anchor: '100%',
                        enforceMaxLength: true,
                        maxLength: 1024,
                        maxLengthText: '描述长度不能超过1024个字符！'
                    } ]
                } ]
            }, {
                xtype: 'htmleditor',
                fieldLabel: '内容',
                labelSeparator: redStar,
                height: 360,
                name: 'proposalOrContractValue',
                id: 'proposalValueEditor',
                anchor: '100%'
            }, {
                xtype: 'hidden',
                name: 'proposalOrContractAddDate',
                format: 'Y-m-d',
                id: 'proposalAddDate'
            }, {
                xtype: 'hidden',
                name: 'proposalOrContractEditDate',
                format: 'Y-m-d',
                id: 'proposalEditDate'
            } ],
            buttons: [ {
                text: '确定',
                action: 'save'
            }, {
                text: '清空',
                action: 'resetProposal',
                id: 'resetProposal'
            }, {
                text: '取消',
                action: 'cancelProposal'
            } ]
        } ];
        this.callParent(arguments);
    }
});