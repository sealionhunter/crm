Ext.define('CRM.view.customerManagement.customerProfiles.LeaderAdviceCard', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.leaderadvicecard',
    id: 'leaderadvicecard',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'leaderadvicelist'
    }]
});