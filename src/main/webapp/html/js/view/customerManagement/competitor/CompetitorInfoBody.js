Ext.define('CRM.view.customerManagement.competitor.CompetitorInfoBody', {
    extend: 'Ext.panel.Panel',
    defaultType: 'displayfield',
    alias: 'widget.competitorinfobody',
    id: 'competitorinfobody',
    layout: 'border',
    border: false,
    items: [ {
        region: 'center',
        margin: '0 0 0 0',
        xtype: 'competitorInfoList'
    }, {
        xtype: 'CompetitorInfoDetail'
    } ]
});