Ext.define('CRM.view.statistics.sourceStatistics.SourceStatisticsColumnChart', {
    extend: 'Ext.window.Window',
    alias: 'widget.sourcestatisticscolumnchart',
    layout: 'fit',
    id: 'sourcestatisticscolumnchart',
    maximizable: true,
    title: '客户来源统计',
    width: 800,
    height: 600,
    initComponent: function() {
        this.items = [ {
            xtype: 'chart',
            style: 'background:#fff',
            animate: true,
            shadow: true,
            store: {
                fields: [ 'name', 'data' ],
                data: [ {
                    'name': '广告',
                    'data': 88
                }, {
                    'name': '员工推荐',
                    'data': 79
                }, {
                    'name': '外部推荐',
                    'data': 52
                }, {
                    'name': '合作伙伴',
                    'data': 43
                }, {
                    'name': '展览会',
                    'data': 73
                }, {
                    'name': '口头推荐',
                    'data': 67
                }, {
                    'name': '其他',
                    'data': 61
                } ]
            },
            axes: [ {
                type: 'Numeric',
                position: 'left',
                fields: [ 'data' ],
                title: '个数',
                grid: true,
                minimum: 0
            }, {
                type: 'Category',
                position: 'bottom',
                fields: [ 'name' ],
                title: '客户来源'
            } ],
            series: [ {
                type: 'column',
                axis: 'left',
                highlight: true,
                tips: {
                    trackMouse: true,
                    width: 80,
                    height: 28,
                    renderer: function(storeItem, item) {
                        this.setTitle(storeItem.get('name') + ': ' + storeItem.get('data'));
                    }
                },
                label: {
                    display: 'insideEnd',
                    'text-anchor': 'middle',
                    field: 'data',
                    orientation: 'vertical',
                    color: '#333'
                },
                xField: 'name',
                yField: 'data'
            } ]
        } ];
        this.callParent(arguments);
    }
});