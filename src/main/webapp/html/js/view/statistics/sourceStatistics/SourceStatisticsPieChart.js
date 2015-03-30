Ext.define('CRM.view.statistics.sourceStatistics.SourceStatisticsPieChart', {
    extend: 'Ext.window.Window',
    alias: 'widget.sourcestatisticspiechart',
    layout: 'fit',
    id: 'sourcestatisticspiechart',
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
            legend: {
                position: 'right'
            },
            insetPadding: 60,
            theme: 'Base:gradients',
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
            series: [ {
                type: 'pie',
                field: 'data',
                showInLegend: true,
                tips: {
                    trackMouse: true,
                    width: 140,
                    height: 28,
                    renderer: function(storeItem, item) {
                        // calculate percentage.
                        var total = 0;
                        store.each(function(rec) {
                            total += rec.get('data');
                        });
                        this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data') / total * 100) + '%');
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
        } ];
        this.callParent(arguments);
    }
});
