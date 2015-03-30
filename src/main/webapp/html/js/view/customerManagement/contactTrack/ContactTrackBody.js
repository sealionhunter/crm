Ext.define('CRM.view.customerManagement.contactTrack.ContactTrackBody', {
    extend: 'Ext.form.Panel',
    alias: 'widget.contacttrackbody',
    id: 'contacttrackbody',
    itemId: 'contacttrackbody',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        margin: '0 2 0 0',
        xtype: 'contacttrackcontactlist'
    }, {
        region: "east",
        xtype: 'contacttrackcontactdetail'
    } ]
});