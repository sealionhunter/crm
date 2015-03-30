Ext.define('CRM.view.customerManagement.intentOrder.ProductOrder', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.productOrder',
    border: false,
    layout: 'column',
    items: [ {
        columnWidth: .3,
        border: false,
        xtype: 'combobox',
        store: new Ext.data.Store({
            fields: [ 'value', 'key' ],
            data: [ {
                'value': '哇哈哈',
                'key': '产品一'
            }, {
                'value': '乐呵呵',
                'key': '产品二'
            } ]
        }),
        allowBlank: false,
        editable: false,
        queryMode: 'local',
        displayField: 'key',
        valueField: 'value',
        margin: '5 5 0 0',
        name: 'productName',
        height: 22
    }, {
        columnWidth: .3,
        xtype: 'textfield',
        name: 'productPrice',
        allowBlank: false,
        labelSeparator: '',
        margin: '5 5 0 0',
        allowBlack: false,
        height: 22
    }, {
        columnWidth: .3,
        xtype: 'textfield',
        name: 'productNumber',
        allowBlank: false,
        margin: '5 5 0 0',
        labelSeparator: '',
        allowBlack: false,
        height: 22
    }, {
        columnWidth: .1,
        xtype: 'button',
        text: 'delete',
        action: 'removeLine',
        margin: '5 0 0 0',
        height: 22
    } ]
});