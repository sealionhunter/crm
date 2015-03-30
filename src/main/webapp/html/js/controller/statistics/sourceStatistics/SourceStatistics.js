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
        // utils.authorizationControl(treeId,sourceStatistics);
        return sourceStatistics;
    },
    openok: function(button) {
        var win = Ext.widget('sourcestatisticscolumnchart');
        win.show();
    },
    openPieChart: function(button) {
        var win = Ext.widget('sourcestatisticspiechart');
        win.show();
    }
});