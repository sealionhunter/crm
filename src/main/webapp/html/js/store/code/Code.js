Ext.define('CRM.store.code.Code', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.code.Code',
    proxy: {
        type: 'ajax',
        url: 'getAComboBox.action',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});