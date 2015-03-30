Ext.define('CRM.view.customerManagement.proposalOrContract.ContractSelectType', {
    extend: 'Ext.window.Window',
    alias: 'widget.contractSelectType',
    id: 'contractSelectType',
    constrainHeader: true,
    width: 580,
    y: 50,
    autoShow: true,
    layout: 'fit',
    resizable: false,
    modal: true,

    initComponent: function() {
        this.contractstore = Ext.create('CRM.store.customerManagement.proposalOrContract.FileTemplateNameStore');
        this.contractstore.load({
            params: {
                type: 1
            }
        });
        this.items = [ {
            xtype: 'form',
            id: 'contractTemplateName',
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
                        fieldLabel: '选择模板',
                        xtype: 'combo',
                        id: 'contractcombo',
                        name: 'fileTemplateName',
                        hiddenName: 'fileTemplateName',
                        anchor: '96%',
                        forceSelection: true,
                        queryMode: 'local',
                        editable: false,
                        store: this.contractstore,
                        valueField: 'fileTemplateName',
                        displayField: 'fileTemplateName',
                        allowBlank: false
                    } ]
                } ]
            } ],
            buttons: [ {
                text: '上一步',
                action: 'first'
            }, {
                text: '下一步',
                action: 'createContract'
            }, {
                text: '取消',
                action: 'cancelContract'
            } ]
        } ];
        this.callParent(arguments);
    }
});