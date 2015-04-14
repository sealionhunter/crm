Ext.define('CRM.view.statistics.sourceStatistics.SourceStatistics', {
    extend : 'Ext.panel.Panel',
    alias : 'widget.sourcestatistics',
    title : '统计',
    id : 'sourcestatistics',
    layout : 'fit',
    initComponent : function() {
        var me = this;
        this.statisticsStore = Ext.create('CRM.store.code.Code');
        this.statisticsTypeStore = Ext.create('CRM.store.code.Code');
        this.store = Ext.create('CRM.store.statistics.SalesStatisticsLine');
        this.statisticsStore.load({
            params : {
                code : '00200001'
            }
        });
        this.statisticsTypeStore.load({
            params : {
                code : '00200002'
            }
        });
        this.items = [ {

            region: "center",
            xtype: 'panel',
            layout: 'card',
            border: false,
            activeItem: 0,
            margins: '5 5 5 0',
            id: 'statisticscard',
            items: [ {
                xtype : 'chart',
                style : 'background:#fff',
                animate : true,
                shadow : true,
                id : 'columncard',
                store : this.store,
                axes : [ {
                    type : 'Numeric',
                    position : 'left',
                    fields : [ 'count' ],
//                    title : '个数',
                    grid : true,
                    minimum : 0
                }, {
                    type : 'Category',
                    position : 'bottom',
                    fields : [ 'name' ],
                    id : 'Category'
                } ],
                series : [ {
                    type : 'column',
                    axis : 'left',
                    highlight : true,
                    tips : {
                        trackMouse : true,
                        width : 140,
                        height : 28,
                        renderer : function(storeItem, item) {
                            this.setTitle(storeItem.get('name') + ': ' + storeItem.get('count'));
                        }
                    },
                    label : {
                        display : 'insideEnd',
                        'text-anchor' : 'middle',
                        field : 'count',
                        orientation : 'vertical',
                        color : '#333'
                    },
                    xField : 'name',
                    yField : 'count'
                } ]
            }, {
                xtype: 'chart',
                style: 'background:#fff',
                animate: true,
                shadow: true,
                id : 'piecard',
                legend: {
                    position: 'right'
                },
                insetPadding: 60,
                theme: 'Base:gradients',
                store: this.store,
                series: [ {
                    type: 'pie',
                    field: 'count',
                    showInLegend: true,
                    tips: {
                        trackMouse: true,
                        width: 140,
                        height: 28,
                        renderer: function(storeItem, item) {
                            var total = 0;
                            me.store.each(function(rec) {
                                total += rec.get('count');
                            });
                            this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('count') / total * 100) + '%');
                        }
                    },
                    highlight: {
                        segment: {
                            margin: 20
                        }
                    },
                    label: {
                        field: 'name',
                        display: 'rotate',
                        contrast: true,
                        font: '18px Arial'
                    }
                } ]
            } ] 
        } ];
        this.tbar = [ {
            xtype : 'combobox',
            name : 'statisticsCombox',
            labelWidth : 60,
            width : 200,
            fieldLabel : '图形选择',
            store : this.statisticsStore,
            displayField : 'value',
            valueField : 'code',
            itemId : 'statisticsCombox',
            queryMode : 'local',
            editable : false,
            forceSelection : true,
            value : '002000010001'
        }, {
            xtype : 'combobox',
            name : 'statisticsTypeCombox',
            labelWidth : 60,
            width : 200,
            fieldLabel : '统计方式',
            store : this.statisticsTypeStore,
            displayField : 'value',
            valueField : 'code',
            itemId : 'statisticsTypeCombox',
            margin : '0 0 0 5',
            queryMode : 'local',
            editable : false,
            forceSelection : true,
            value : '002000020001'
        }, {
            xtype : 'button',
            text : '确定',
            margin : '0 0 0 5',
            action : 'sourceStatisticsOk'
        } ];
        this.callParent(arguments);
    }
});