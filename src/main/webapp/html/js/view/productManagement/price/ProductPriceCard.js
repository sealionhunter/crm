Ext.define('CRM.view.productManagement.price.ProductPriceCard', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.productpricecard',
    id: 'productPriceCard',
    layout: 'border',
    border: false,
    items: [ {
        region: "center",
        xtype: 'productpricelist'
    }, {
        region: "east",
        xtype: 'productpricedetail'
    } ]
});