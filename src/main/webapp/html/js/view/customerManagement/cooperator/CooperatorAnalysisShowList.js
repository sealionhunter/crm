Ext.define('CRM.view.customerManagement.cooperator.CooperatorAnalysisShowList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.cooperatoranalysisshowlist',
    title: '合作伙伴分析列表',
    store: 'customerManagement.cooperator.CopAnalysisShowStore',
    columnLines: true,
    border: true,
    initComponent: function() {
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.columns = [ {
            xtype: 'rownumberer',
            text: '序号',
            width: 50,
            align: 'left'
        }, {
            text: '合作伙伴名称',
            dataIndex: 'cooperatorName',
            renderer: this.rendererValue,
            flex: 1
        }, {
            text: '优势领域',
            dataIndex: 'advantageField',
            renderer: this.rendererValue,
            flex: 1
        }, {
            text: '信任度',
            dataIndex: 'trustDegreeName',
            renderer: this.rendererValue,
            flex: 1
        }, {
            text: '技术水平评价',
            dataIndex: 'engLevelEvaluation',
            renderer: this.rendererValue,
            flex: 1
        }, {
            text: '合作情况概述',
            dataIndex: 'cooperationSummarize',
            renderer: this.rendererValue,
            flex: 1
        }, {
            text: '综合分析',
            dataIndex: 'comphsAnalysis',
            renderer: this.rendererValue,
            flex: 1
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});