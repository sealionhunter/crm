Ext.define('CRM.view.statistics.salesStatistics.SalesCountShowLine', {
    extend: 'Ext.chart.Chart',
    alias: 'widget.salesCountShowLine',
    id: 'salesCountShowLine',
    width: 400,
    store: 'statistics.SalesStatisticsLine',
    hidden: false,
    axes: [ {
                 type: 'Numeric',
                 position: 'left',
                 fields: ['count','rightCount'],
                    title: 'Sample Values',
                    grid: true,
                    minimum: 0
                    
                },
                {
                    type: 'Category',
                    position: 'bottom',
                    fields: 'state',
                    title: 'Sample Metrics'
                }],
     series: [ {
                    type: 'line',
                    highlight: {
                        size: 7,
                        radius: 7
                    },
                    axis: 'left',
                    xField: 'state',
                    yField: 'count',
                    markerConfig: {
                        type: 'cross',
                        size: 4,
                        radius: 4,
                        'stroke-width': 0
                    },
    tips: {
                        trackMouse: true,
                        width: 140,
                        height: 28,
                        renderer: function(storeItem, item) {
                          this.setTitle(storeItem.get('state') + ': ' + storeItem.get('count') );
                        }
    }
                }
                ,{type: 'line',
                    highlight: {
                        size: 7,
                        radius: 7
                    },
                    axis: 'left',
                    xField: 'state',
                    yField: 'rightCount',
                    markerConfig: {
                        type: 'circle',
                        size: 4,
                        radius: 4,
                        'stroke-width': 0},
                    tips: {
                            trackMouse: true,
                            width: 140,
                            height: 28,
                            renderer: function(storeItem, item) {
                              this.setTitle(storeItem.get('state') + ': ' + storeItem.get('rightCount') );
                            }
        }
                }
    ]
});
