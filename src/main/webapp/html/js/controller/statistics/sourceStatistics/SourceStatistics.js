Ext.define('CRM.controller.statistics.sourceStatistics.SourceStatistics', {
    extend: 'Ext.app.Controller',
    views: [ 'statistics.sourceStatistics.SourceStatistics', 'statistics.sourceStatistics.SourceStatisticsPieChart',
            'statistics.sourceStatistics.SourceStatisticsColumnChart' ],
    init: function() {
        this.control({
            'sourcestatistics button[action=sourceStatisticsOk]': {
                click: this.openok
            },
            'sourcestatistics button[action=sourceStatisticsPieOk]': {
                click: this.openPieChart
            }
        });

    },
    viewInit: function(treeId) {
        var sourceStatistics = Ext.getCmp('sourcestatistics');
        if (typeof (sourceStatistics) == 'undefined') {
            sourceStatistics = Ext.widget('sourcestatistics');
        }
        Ext.getCmp('centercard').insert(1, sourceStatistics);
        Ext.getCmp('centercard').getLayout().setActiveItem(sourceStatistics);
        sourceStatistics.store.load({
            params: {
                statisticsType: '002000020001'
            }
        })
        return sourceStatistics;
    },
    openok: function(button) {
        var panel = button.up('panel');
        var type = button.up('panel').down('#statisticsCombox').getValue();
        if (type === '002000010001') {
            Ext.getCmp('statisticscard').getLayout().setActiveItem('columncard');
        } else {
            Ext.getCmp('statisticscard').getLayout().setActiveItem('piecard');
        }
        panel.store.load({
            params: {
                statisticsType: button.up('panel').down('#statisticsTypeCombox').getValue()
            }
        });
    }
});