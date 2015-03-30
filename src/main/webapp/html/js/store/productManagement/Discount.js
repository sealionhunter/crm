Ext.define('CRM.store.productManagement.Discount', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.productManagement.Discount',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'getAComboBox.action?code=00050002',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});
