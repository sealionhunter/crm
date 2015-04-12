Ext.define('CRM.view.statistics.sourceStatistics.SourceStatistics', {
    extend : 'Ext.panel.Panel',
    alias : 'widget.sourcestatistics',
    title : '统计',
    id : 'sourcestatistics',
    layout : 'fit',
    initComponent : function() {
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
            xtype : 'chart',
            style : 'background:#fff',
            animate : true,
            shadow : true,
            store : this.store,
            axes : [ {
                type : 'Numeric',
                position : 'left',
                fields : [ 'count' ],
                title : '个数',
                grid : true,
                minimum : 0
            }, {
                type : 'Category',
                position : 'bottom',
                fields : [ 'name' ],
                title : '客户来源'
            } ],
            series : [ {
                type : 'column',
                axis : 'left',
                highlight : true,
                tips : {
                    trackMouse : true,
                    width : 80,
                    height : 28,
                    renderer : function(storeItem, item) {
                        this.setTitle(storeItem.get('name') + ': ' + storeItem.get('data'));
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
        } ];
        this.tbar = [ {
            xtype : 'combobox',
            name : 'statisticsCombox',
            labelWidth : 80,
            width : 200,
            fieldLabel : '统计方式',
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
            labelWidth : 80,
            width : 200,
            fieldLabel : '图形选择',
            store : this.statisticsTypeStore,
            displayField : 'value',
            valueField : 'code',
            itemId : 'statisticsTypeCombox',
            margin : '0 0 0 15',
            queryMode : 'local',
            editable : false,
            forceSelection : true,
            value : '002000020001'
        }, {
            xtype : 'button',
            text : '确定',
            action : 'sourceStatisticsOk'
        } ];
        this.callParent(arguments);
    }
});