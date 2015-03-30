Ext.define('CRM.view.customerManagement.cooperator.CooperatorAnalysisShowWin', {
    extend: 'Ext.window.Window',
    alias: 'widget.cooperatoranalysisshowwin',
    width: 1000,
    height: 500,
    x: 200,
    y: 100,
    title: '合作伙伴分析窗口',
    closable: true,
    resizable: false, // can not resize the window.
    autoShow: false,
    modal: true, // only show window,other can not used.
    constrainHeader: true,
    layout: 'border',
    initComponent: function() {
        this.items = [ {
            region: 'center',
            itemId: 'showListAnalysis',
            xtype: 'cooperatoranalysisshowlist',
            layout: 'fit',
            margins: '5 5 0 0'
        }, {
            region: 'east',
            itemId: 'eastPanel',
            xtype: 'panel',
            layout: 'card',
            title: '合作伙伴分析详细信息',
            items: [ {
                xtype: 'displayfield',
                itemId: 'htmlDisplay',
                value: '显示合作伙伴分析详细信息'
            }, {
                xtype: 'cooperatoranalysisdetail',
                collapsible: false,
                itemId: 'showInfoAnalysis'
            } ],
            width: 300,
            margins: '5 5 0 0'
        } ];
        this.callParent(arguments);
    }
});