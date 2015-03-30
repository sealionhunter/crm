Ext.define('CRM.view.contactManagement.contactProfiles.ContactCard', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.contactCard',
    id: 'contactCard',
    itemId: 'contactCard',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'contactlist'
//    }, {
//        xtype: 'contactdetail'
    } ]
});