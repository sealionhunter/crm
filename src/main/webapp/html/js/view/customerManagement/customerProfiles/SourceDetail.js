Ext.define('CRM.view.customerManagement.customerProfiles.SourceDetail', {
    extend: 'Ext.form.Panel',
    title: '客户来源详细信息',
    defaultType: 'displayfield',
    alias: 'widget.sourcedetail',
    id: 'sourcedetail',
    region: "east",
    collapsible: true,
    width: 300,
    autoScroll: true,
    hidden: true,
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 60,
        width: 248
    },
    items: [ {
        fieldLabel: '客户',
        name: 'customerName',
        htmlEncode: true

    }, {
        fieldLabel: '地  区 ',
        name: 'sourceArea',
        htmlEncode: true
    }, {
        fieldLabel: '来 源',
        name: 'sourceTypeName',
        htmlEncode: true

    }, {
        fieldLabel: '备  注',
        name: 'descriptions',
        htmlEncode: true
    } ]
});