Ext.define('CRM.view.statistics.salesStatistics.SalesStatistics', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.salesStatistics',
    id: 'salesStatistics',
    layout: 'fit',
    initComponent: function() {
        this.tbar = [ {
            margin: '0 0 0 10',
            id: 'startDate',
            xtype: 'datefield',
            name: 'startDate',
            itemId: 'startDate',
            format: 'Y-m-d',
            labelWidth: 80,
            width: 180,
            fieldLabel: '起始日期',
            maxLength: 10,
            enforceMaxLength: true,
            invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
            dateRange: {
                begin: 'startDate',
                end: 'endDate'
            },
            vtype: 'dateRange'
        }, {
            margin: '0 0 0 10',
            id: 'endDate',
            xtype: 'datefield',
            name: 'endDate',
            itemId: 'endDate',
            format: 'Y-m-d',
            labelWidth: 80,
            width: 180,
            maxLength: 10,
            enforceMaxLength: true,
            fieldLabel: '截止日期',
            invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
            dateRange: {
                begin: 'startDate',
                end: 'endDate'
            },
            vtype: 'dateRange'
        }, {
            margin: '0 0 0 5',
            xtype: 'button',
            text: '查看',
            id: 'searchChart',
            action: 'searchChart'
        } ];
        this.items = [ {
            xtype: 'salesCountShow',
            autoLoad: false
        } ];
        this.callParent(arguments);
    }
});