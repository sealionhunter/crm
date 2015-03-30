Ext.define('CRM.store.systemManagement.groupManagement.OrganizationChart', {
    extend: 'CRM.store.commonStore',
    autoLoad: true,
    model: [ 'CRM.systemManagement.organizationManagement.OrganizationChart.OrganizationChart' ],
    proxy: {
        type: 'ajax',
        url: 'organizationStructure.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});