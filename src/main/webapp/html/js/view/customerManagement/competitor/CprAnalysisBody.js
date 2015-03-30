Ext.define('CRM.view.customerManagement.competitor.CprAnalysisBody', {
    extend: 'Ext.panel.Panel',
    defaultType: 'displayfield',
    alias: 'widget.cpranalysisbody',
    id: 'cpranalysisbody',
    itemId: 'cpranalysisbody',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'cpranalysislist',
        margins: '0 0 0 0'
    }, {
        xtype: 'detailedanalysis'
    } ]

});