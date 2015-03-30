Ext.define('CRM.view.customerManagement.competitor.ShowCprAnalysisWin', {
    extend: 'Ext.window.Window',
    alias: 'widget.showcpranalysiswin',
    id: 'showCpranAlysisWindow',
    title: '竞争对手分析',
    layout: 'border',
    width: 1000,
    height: 500,
    closable: true,
    resizable: false,
    autoShow: false,
    modal: true,
    border: false,
    initComponent: function() {
        var me = this;
        this.items = [ {
            region: 'center',
            itemId: 'showcpranalysislist',
            xtype: 'grid',
            layout: 'fit',
            store: 'customerManagement.competitor.CprAnalysisStore',
            columns: [ Ext.create('Ext.grid.RowNumberer', {
                text: '序号',
                width: 40,
                align: "right",
                renderer: function(value, metadata, record, rowIndex) {
                    var page = me.store.currentPage, pageSize = me.store.pageSize;
                    return (page - 1) * pageSize + rowIndex + 1;
                }
            }), {
                header: '竞争对手名称',
                dataIndex: 'competitorName',
                renderer: this.rendererValue,
                minWidth: 130,
                flex: 1
            }, {
                header: '所属领域',
                dataIndex: 'areaName',
                sortable: true,
                renderer: this.rendererValue,
                minWidth: 91,
                flex: 0.7
            }, {
                header: '优势',
                dataIndex: 'advantage',
                renderer: this.rendererValue,
                minWidth: 151,
                flex: 1.2
            }, {
                header: '劣势',
                dataIndex: 'disadvantage',
                renderer: this.rendererValue,
                minWidth: 151,
                flex: 1.2
            }, {
                header: '分析时间',
                dataIndex: 'cprAnalysisTime',
                renderer: this.rendererValue,
                minWidth: 130,
                align: "center",
                flex: 1
            }, {
                header: '综合竞争力',
                dataIndex: 'compositeComp',
                minWidth: 130,
                align: "right",
                flex: 1
            }, {
                header: '综合防御力',
                dataIndex: 'compositeDefense',
                minWidth: 130,
                align: "right",
                flex: 1
            } ],
            bbar: Ext.create('CRM.view.PagingToolbar', {
                store: this.store,
            })
        }, {
            region: 'east',
            title: '竞争对手分析详情列表',
            width: 300,
            hidden: true,
            xtype: 'form',
            autoScroll: true,
            itemId: 'showDetailedAnalysis',
            id: 'showDetailedAnalysis',
            margins: '0 0 0 5',
            defaults: {
                labelAlign: 'left',
                labelWidth: 80,
                labelPad: 0,
                htmlEncode: true,
                x: 10,
                y: 10,
                xtype: 'displayfield'
            },
            items: [ {
                name: 'competitorName',
                fieldLabel: '竞争对手名称'
            }, {
                name: 'cprAnalysisTime',
                fieldLabel: '分析时间'
            }, {
                name: 'areaName',
                fieldLabel: '所属领域'
            }, {
                name: 'ability',
                fieldLabel: '对手能力分析'
            }, {
                name: 'targets',
                fieldLabel: '对手目标分析'
            }, {
                name: 'strategy',
                fieldLabel: '对手战略分析'
            }, {
                name: 'prediction',
                fieldLabel: '反应模式预测'
            }, {
                name: 'advantage',
                fieldLabel: '优势'
            }, {
                name: 'disadvantage',
                fieldLabel: '劣势'
            }, {
                name: 'advAnalysis',
                fieldLabel: '优势详细分析'
            }, {
                name: 'disadvAnalysis',
                fieldLabel: '劣势详细分析'
            }, {
                name: 'others',
                fieldLabel: '其他'
            }, {
                name: 'compositeComp',
                fieldLabel: '综合竞争力'
            }, {
                name: 'compositeDefense',
                fieldLabel: '综合防御力'
            }, {
                name: 'advice',
                fieldLabel: '建议'
            } ]
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});