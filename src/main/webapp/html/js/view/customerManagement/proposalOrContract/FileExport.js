Ext.define('CRM.view.customerManagement.proposalOrContract.FileExport', {
    extend: 'Ext.Window',
    alias: 'widget.fileexport',
    id: 'fileexport',
    defaultType: 'displayfield',
    autoShow: true,
    width: 550,
    height: 550,
    constrainHeader: true,
    layout: 'fit',
    title: "模板内容",
    modal: true,
    resizable: false,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            id: 'fileexportform',
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
                name: 'fileTemplateValue',
                anchor: '100% 100%'
            } ]
        } ];
        this.buttons = [ {
            text: '导出',
            action: 'fileExport'
        }, {
            text: '关闭',
            action: 'closeFileExport'
        } ];
        this.callParent(arguments);
    }
});