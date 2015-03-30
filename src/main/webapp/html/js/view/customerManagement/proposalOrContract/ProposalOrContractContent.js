Ext.define('CRM.view.customerManagement.proposalOrContract.ProposalOrContractContent', {
    extend: 'Ext.Window',
    alias: 'widget.proposalorcontractcontent',
    id: 'proposalorcontractcontent',
    defaultType: 'displayfield',
    autoShow: true,
    width: 550,
    height: 550,
    constrainHeader: true,
    layout: 'fit',
    title: "导出内容",
    modal: true,
    resizable: false,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            id: 'proposalorcontractcontentform',
            layout: 'anchor',
            items: [ {
                xtype: 'htmleditor',
                readOnly: true,
                enableColors: false,
                enableAlignments: false,
                enableFont: false,
                enableLinks: false,
                enableFormat: false,
                enableFontSize: false,
                enableSourceEdit: false,
                enableLists: false,
                name: 'proposalOrContractValue',
                anchor: '100% 100%'
            } ]
        } ];
        this.buttons = [ {
            text: '导出',
            action: 'createcontentfile'
        }, {
            text: '关闭',
            action: 'closeContent'
        } ];
        this.callParent(arguments);
    }
});
