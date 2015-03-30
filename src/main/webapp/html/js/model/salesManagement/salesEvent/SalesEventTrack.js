Ext.define('CRM.model.salesManagement.salesEvent.SalesEventTrack', {
    extend: 'Ext.data.Model',
    fields: [ 'id', 'customerName', 'status', 'editPeople', 'startDate', 'isAbolished', 'recordTime' ]
});