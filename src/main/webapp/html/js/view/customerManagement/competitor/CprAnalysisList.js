Ext.define('CRM.view.customerManagement.competitor.CprAnalysisList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.cpranalysislist',
    store: 'customerManagement.competitor.CprAnalysisStore',
    id: 'cpranalysislist',
    title: '竞争对手分析列表',
    minWidth: 100,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.industryStore = Ext.create('CRM.store.code.Code');
        this.industryStore.load({
            params: {
                code: '00010008'
            }
        });
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            align: "right",
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: '竞争对手名称',
            dataIndex: 'competitorName',
            renderer: this.rendererValue,
            flex: 1,
            minWidth: 150
        }, {
            header: '所属领域',
            dataIndex: 'areaName',
            sortable: true,
            renderer: this.rendererValue,
            flex: 0.7,
            minWidth: 105
        }, {
            header: '优势',
            dataIndex: 'advantage',
            id: 'advantageFields',
            name: 'advantage',
            renderer: this.rendererValue,
            flex: 1.5,
            minWidth: 175
        }, {
            header: '劣势',
            dataIndex: 'disadvantage',
            id: 'disadvantageFields',
            name: 'disadvantage',
            renderer: this.rendererValue,
            flex: 1.5,
            minWidth: 175
        }, {
            header: '分析时间',
            dataIndex: 'cprAnalysisTime',
            name: 'cprAnalysisTime',
            renderer: this.rendererValue,
            flex: 1,
            align: "center",
            minWidth: 150
        }, {
            header: '综合竞争力',
            dataIndex: 'compositeComp',
            flex: 1,
            align: "right",
            minWidth: 150
        }, {
            header: '综合防御力',
            dataIndex: 'compositeDefense',
            flex: 1,
            align: "right",
            minWidth: 150
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    },
    dockedItems: [ {
        dock: 'top',
        xtype: 'toolbar',
        items: [ {
            xtype: 'tbfill'
        }, {
            id: 'CprAnalysisAddBtn',
            text: '添加',
            action: 'addCprAnalysis',
            itemId: '13201'
        }, {
            id: 'CprAnalysisEditBtn',
            text: '编辑',
            xtype: 'button',
            disabled: true,
            action: 'editCprAnalysis',
            itemId: '13202'
        }, {
            id: 'CprAnalysisDelBtn',
            text: '删除',
            disabled: true,
            action: 'delCprAnalysis',
            itemId: '13203'
        } ]
    } ],
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});