Ext.define('CRM.controller.statistics.salesStatistics.SalesLineInit', {
    extend: 'Ext.app.Controller',
    views: [ 'statistics.salesStatistics.SalesStatistics', 'statistics.salesStatistics.SalesCountShowLine','statistics.sourceStatistics.SourceStatistics' ],
    stores: [ 'statistics.SalesStatisticsLine', 'statistics.SalesStatisticsGrid' ],
    models: [ 'statistics.salesStatistics.SalesStatisticsLine' ],
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
      //  alert("kaishi");
        var salesCountShowLine = Ext.getCmp('salesCountShowLine');
        var card = Ext.getCmp('centercard');
        if (typeof (salesCountShowLine) == 'undefined') {
            salesCountShowLine = Ext.widget('salesCountShowLine');
        } else {
            salesCountShowLine = Ext.widget('salesCountShowLine');
        }
        card.insert(1, salesCountShowLine);
//        Ext.getCmp('SalesStatistics').show();

//        alert("插入表类型选择");
//        card.insert(1, sourcestatistics);
        //Ext.getCmp('chartPanel').hide();
        var chartStore = this.getStore('statistics.SalesStatisticsLine');
        card.getLayout().setActiveItem('salesCountShowLine');
        return salesCountShowLine;
    },
    showSearchChart: function(button) {
        Ext.getCmp('chartPanel').hide();
        if (!Ext.getCmp('startDate').isValid() || !Ext.getCmp('endDate').isValid()) {
            messageBox.alert('提示', '起始日期要小于等于截止日期');
        } else {
            var endDate = Ext.getCmp('endDate').getValue();
            var startDate = Ext.getCmp('startDate').getValue();
            var chartStore = this.getStore('statistics.SalesStatisticsLine');
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
                if (store.getAt(0).get('date') != 0) {
                    Ext.getCmp('chartPanel').show();
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