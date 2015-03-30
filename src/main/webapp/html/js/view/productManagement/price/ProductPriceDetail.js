Ext.define('CRM.view.productManagement.price.ProductPriceDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.productpricedetail',
    id: 'productPriceDetail',
    region: "east",
    padding: '0 0 0 5',
    title: '价格详细信息',
    collapsible: true,
    autoScroll: true,
    hidden: true,
    width: 300,
    defaultType: 'displayfield',
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 120,
        width: 248,
        htmlEncode: true
    },
    items: []
});
