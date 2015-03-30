Ext.define('CRM.view.productManagement.price.ProductPrice', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.productprice',
    margin: '5 5 5 10',
    // labelWidth : 150,
    columnWidth: 1,
    store: 'productManagement.Discount',
    queryMode: 'local',
    displayField: 'value',
    valueField: 'value',
    editable: false
});