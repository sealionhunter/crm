Ext.define('CRM.view.productManagement.product.ProductCard', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.productcard',
    id: 'productCard',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'productlist'
    }, {
        xtype: 'productdetail'
    } ]
});