Ext.define('CRM.view.customerManagement.cooperator.CooperatorAnalysisCenter', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.cooperatoranalysiscenter',
    id: 'cooperatoranalysiscenter',
    layout: 'border',
    border: false,
    items: [ {
        region: 'center',
        id: 'cooperatorAnalysisList',
        xtype: 'cooperatoranalysislist'
    }, {
        id: 'cooperatorAnalysisDetail',
        title: '合作伙伴分析详细信息',
        xtype: 'cooperatoranalysisdetail'
    } ]
});