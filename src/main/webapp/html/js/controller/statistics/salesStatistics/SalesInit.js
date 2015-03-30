Ext.define('CRM.controller.statistics.salesStatistics.SalesInit', {
    extend: 'Ext.app.Controller',
    views: [ 'statistics.salesStatistics.SalesStatistics', 'statistics.salesStatistics.SalesCountShow' ],
    stores: [ 'statistics.SalesStatistics', 'statistics.SalesStatisticsGrid' ],
    models: [ 'statistics.salesStatistics.SalesStatistics' ],
    init: function() {
        this.control({
            'salesStatistics button[id=searchChart]': {
                click: this.showSearchChart
            },
            'salesStatistics > toolbar datefield': {
                blur: utils.trimSpaceSearch
            }
        });
    },
    viewInit: function(treeId) {
        var salesStatistics = Ext.getCmp('salesStatistics');
        var card = Ext.getCmp('centercard');
        if (typeof (salesStatistics) == 'undefined') {
            salesStatistics = Ext.widget('salesStatistics');
        } else {
            salesStatistics.destroy();
            salesStatistics = Ext.widget('salesStatistics');
        }
        card.insert(1, salesStatistics);
        card.getLayout().setActiveItem('salesStatistics');
        Ext.getCmp('chartPanel').hide();
        Ext.getCmp('gridPanel').hide();
        var chartStore = this.getStore('statistics.SalesStatistics');
        var gridStore = this.getStore('statistics.SalesStatisticsGrid');
        var startDate = '1970-01-01';
        var endDate = '';
        var param = {
            startDate: startDate,
            endDate: endDate
        };
        chartStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, param);
        });
        gridStore.on('beforeload', function(store) {
            Ext.apply(store.proxy.extraParams, param);
        });
        gridStore.on('load', function(store, record, opts) {
            if (gridStore.getAt(0).get('allCount') != 0) {
                Ext.getCmp('chartPanel').show();
                Ext.getCmp('gridPanel').show();
            } else {
                messageBox.alert('提示', '没有相关的数据！');
            }
        });
        chartStore.load();
        gridStore.load();
        return salesStatistics;
    },
    showSearchChart: function(button) {
        Ext.getCmp('chartPanel').hide();
        Ext.getCmp('gridPanel').hide();
        if (!Ext.getCmp('startDate').isValid() || !Ext.getCmp('endDate').isValid()) {
            messageBox.alert('提示', '起始日期要小于等于截止日期');
        } else {
            var endDate = Ext.getCmp('endDate').getValue();
            var startDate = Ext.getCmp('startDate').getValue();
            var chartStore = this.getStore('statistics.SalesStatistics');
            var gridStore = this.getStore('statistics.SalesStatisticsGrid');
            if (startDate == '' || startDate == null) {
                startDate = '1970-01-01';
            }
            var param = {
                startDate: startDate,
                endDate: endDate
            };
            chartStore.on('beforeload', function(store) {
                Ext.apply(store.proxy.extraParams, param);
            });
            chartStore.load();
            chartStore.on('load', function(store, record, opts) {
                if (store.getAt(0).get('allCount') != 0) {
                    Ext.getCmp('chartPanel').show();
                    Ext.getCmp('gridPanel').show();
                } else {
                    messageBox.alert('提示', '没有相关的数据！');
                }
            });
            gridStore.on('beforeload', function(store) {
                Ext.apply(store.proxy.extraParams, param);
            });
            gridStore.load();
        }
    }
});