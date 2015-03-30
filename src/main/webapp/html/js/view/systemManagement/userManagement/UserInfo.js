Ext.define('CRM.view.systemManagement.userManagement.UserInfo', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.userinfo',
    id: 'userinfo',
    itemId: 'userinfo',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'userlist'
    }, {
        xtype: 'userdetail'
    } ]

});