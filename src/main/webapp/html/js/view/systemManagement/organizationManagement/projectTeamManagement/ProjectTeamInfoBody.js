Ext.define('CRM.view.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamInfoBody', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.ProjectTeamInfoBody',
    id: 'ProjectTeamInfoBody',
    itemId: 'ProjectTeamInfoBody',
    layout: 'border',
    border: false,
    items: [ {
        region: 'center',
        xtype: 'ProjectTeamList'
    }, {
        region: 'east',
        xtype: 'ProjectTeamDetail'
    } ]
});