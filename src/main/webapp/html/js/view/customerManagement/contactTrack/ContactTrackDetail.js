Ext.define('CRM.view.customerManagement.contactTrack.ContactTrackDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.contacttrackcontactdetail',
    id: 'contacttrackcontactdetail',
    title: '客户联系详细信息',
    autoScroll: true,
    collapsible: true,
    hidden: true,
    defaultType: 'displayfield',
    width: 300,
    height: 202,
    defaults: {
        htmlEncode: true,
        labelWidth: 100,
        width: 248,
        labelPad: 0,
        x: 10,
        y: 10
    },
    initComponent: function() {
        this.items = [{
            xtype: 'displayfield',
            fieldLabel: '主题',
            name: 'contactTheme'
        }, {
            xtype: 'displayfield',
            fieldLabel: '客户',
            name: 'customerName'
        }, {
            xtype: 'displayfield',
            fieldLabel: '我方联系人',
            name: 'weContactName'
        }, {
            xtype: 'displayfield',
            fieldLabel: '对方联系人',
            name: 'oppositeContactName'
        }, {
            xtype: 'displayfield',
            fieldLabel: '预计联系时间',
            name: 'planDateBegin'

        }, {
            xtype: 'displayfield',
            fieldLabel: '联系方式',
            name: 'contactWayName'
        }, {
            xtype: 'displayfield',
            fieldLabel: '联系内容',
            name: 'contactContent'
        }, {
            xtype: 'displayfield',
            fieldLabel: '类型',
            name: 'contactTypeName'
        }, {
            xtype: 'displayfield',
            fieldLabel: '备注',
            name: 'remarks'
        } ];
        this.callParent(arguments);
    },
    rendererDate: function(value) {
        if (value == null) {
            return value;
        } else {
            return value.substr(0, 19);
        }
    }
});