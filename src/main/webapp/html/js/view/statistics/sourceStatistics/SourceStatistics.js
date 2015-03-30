Ext.define('CRM.view.statistics.sourceStatistics.SourceStatistics', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.sourcestatistics',
    id: 'sourcestatistics',
    layout: 'fit',
    initComponent: function() {
        this.sourceStatisticsStore = Ext.create('CRM.store.comboBox.ComboBox');
        this.sourceStatisticsStore.load({
            params: {
                query: 'statistics'
            }
        });
        this.tbar = [ {
            xtype: 'combobox',
            name: 'sourceStatisticsCombox',
            labelWidth: 80,
            width: 200,
            fieldLabel: '图形选择',
            store: this.sourceStatisticsStore,
            displayField: 'value',
            valueField: 'name',
            xtype: 'combobox',
            itemId: 'sourceStatisticsCombox',
            margin: '0 0 0 15',
            queryMode: 'local',
            editable: false,
            forceSelection: true,
            value: '01'
        }, {
            xtype: 'button',
            text: '确定',
            action: 'sourceStatisticsOk'
        }, '-', {
            xtype: 'button',
            text: '柱状图',
            action: 'sourceStatisticsOk'
        }, {
            xtype: 'button',
            text: '饼状图',
            action: 'sourceStatisticsPieOk'
        } ];
        this.callParent(arguments);
    }
});