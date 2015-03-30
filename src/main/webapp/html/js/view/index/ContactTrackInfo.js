Ext.define('CRM.view.index.contactTrackInfo', {
    extend: 'Ext.window.Window',
    alias: 'widget.contacttrackinfo',
    title : "详细信息TEST",
    id: 'contacttrackinfo',
    width: 610,
    height: 520,
    layout: 'fit',
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            itemId: 'contacttrackinfoForm',
            defaults: {
                border: 0,
                labelWidth: 100,
                width: 550,
                margin: '10 20 0 20'
            },
            items: [{
                layout: 'hbox',
                items : [{
                    name: 'contactTheme',
                    fieldLabel: '主题',
                    xtype: 'textfield',
                    width: 530,
                    readOnly: true
                }]
            }, {
                layout: 'hbox',
                items: [ {
                    name: 'contactID',
                    xtype: 'displayfield',
                    hidden: true
                }, {
                    name: 'customerID',
                    itemId: 'customerID',
                    xtype: 'displayfield',
                    hidden: true
                }, {
                    name: 'customerName',
                    xtype: 'textfield',
                    fieldLabel: '客户',
                    itemId: 'customerName',
                    readOnly: true
                }, {
                    xtype: 'textfield',
                    name: 'contactTypeName',
                    fieldLabel: '类型',
                    readOnly: true,
                    hideTrigger: true,
                    margin: '0 0 0 25'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    fieldLabel: '我方联系人',
                    readOnly: true,
                    name: 'weContactName'
                }, {
                    xtype: 'textfield',
                    fieldLabel: '对方联系人',
                    readOnly: true,
                    name: 'oppositeContactName',
                    margin: '0 0 0 25'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    readOnly: true,
                    name: 'contactWayName',
                    fieldLabel: '联系方式'
                }, {
                    xtype: 'textfield',
                    fieldLabel: '预计联系时间',
                    readOnly: true,
                    name: 'planDateBegin',
                    margin: '0 0 0 25'
                } ]
            }, {
                fieldLabel: '联系内容',
                xtype: 'textarea',
                readOnly: true,
                name: 'contactContent',
                height: 180,
                width: 530
            }, {
                fieldLabel: '备注',
                xtype: 'textarea',
                readOnly: true,
                height: 50,
                width: 530,
                name: 'remarks'
            } ]
        } ];

        this.buttons = [ {
            text: '确定',
            action: 'closeContactTrackInfo'
        } ];
        this.callParent(arguments);
    }
});