Ext.define('CRM.view.customerManagement.cooperator.CooperatorDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.cooperatordetail',
    id: 'cooperatorDetail',
    defaultType: 'displayfield',
    autoScroll: true,
    title: '合作伙伴详细信息',
    region: "east",
    collapsible: true,
    hidden: true,
    width: 300,
    height: 202,
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 60,
        width: 248
    },
    initComponent: function() {
        this.items = [ {
            fieldLabel: '公司名称',
            name: 'cooperatorName',
            htmlEncode: true
        }, {
            fieldLabel: '标准地址',
            name: 'cooperatorAddress',
            htmlEncode: true
        }, {
            fieldLabel: '电话',
            name: 'cooperatorTelephone',
            htmlEncode: true
        }, {
            fieldLabel: '传真',
            name: 'cooperatorFax',
            htmlEncode: true
        }, {
            fieldLabel: '邮件',
            name: 'cooperatorEmail',
            htmlEncode: true
        }, {
            fieldLabel: '网址',
            name: 'cooperatorWebsite',
            htmlEncode: true
        }, {
            fieldLabel: '公司简介',
            name: 'cooperatorDetail',
            htmlEncode: true
        }, {
            fieldLabel: '备注',
            name: 'cooperatorRemark',
            htmlEncode: true
        } ];
        this.callParent(arguments);
    }
});
