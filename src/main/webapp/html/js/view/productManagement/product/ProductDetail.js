Ext.define('CRM.view.productManagement.product.ProductDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.productdetail',
    id: 'productDetail',
    region: "east",
    padding: '0 0 0 5',
    title: '产品详细信息',
    collapsible: true,
    autoScroll: true,
    hidden: true,
    width: 300,
    defaultType: 'displayfield',
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 80,
        width: 248,
        htmlEncode: true
    },
    items: [ {
        fieldLabel: '产品编号',
        name: 'productID'
    }, {
        fieldLabel: '产品名称',
        name: 'name'
    }, {
        fieldLabel: '产品类型',
        name: 'category',
        id: "detailCategory"
    }, {
        fieldLabel: '产品型号',
        name: 'productModel',
        id: 'detailProdouctModel',
        hidden: true
    }, {
        fieldLabel: '标准报价（元）',
        name: 'price',
        id: 'detailPrice'
    }, {
        fieldLabel: '产品说明',
        name: 'description'
    }, {
        fieldLabel: '备注',
        name: 'remark'
    } ]
});