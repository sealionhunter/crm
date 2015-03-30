Ext.define('CRM.view.systemManagement.userManagement.UserInfoBody', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.UserInfoBody',
    id: 'UserInfoBody',
    itemId: 'UserInfoBody',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'UserList'
    }, {
        xtype: 'UserDetail'
    } ]

});