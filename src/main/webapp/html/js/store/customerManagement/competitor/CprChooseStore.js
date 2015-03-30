Ext.define('CRM.store.customerManagement.competitor.CprChooseStore', {
    extend: 'CRM.store.commonStore',
    fields: [ 'competitorInfoId', 'competitorName' ],
    proxy: {
        type: 'ajax',
        url: 'getAllCprNameCprAnalysis.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});