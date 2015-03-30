Ext.define('CRM.view.activityManagement.activity.ActivityInfo', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.activityinfo',
    id: 'activityinfo',
    itemId: 'activityinfo',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'activitylist'
    }, {
        xtype: 'activitydetail'
    } ]

});