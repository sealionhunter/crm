Ext.define('CRM.view.customerManagement.contactTrack.ContactHistoryBody', {
    extend: 'Ext.form.Panel',
    alias: 'widget.contacthistorybody',
    id: 'contacthistorybody',
    itemId: 'contacthistorybody',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        margin: '0 2 0 0',
        xtype: 'contacthistorylist'
    }, {
        region: "east",
        xtype: 'contacthistorydetail'
    } ]
});