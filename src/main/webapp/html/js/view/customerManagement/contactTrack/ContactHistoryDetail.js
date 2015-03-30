Ext.define('CRM.view.customerManagement.contactTrack.ContactHistoryDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.contacthistorydetail',
    id: 'contacthistorydetail',
    defaultType: 'displayfield',
    hidden: true,
    collapsible: true,
    title: '客户联系历史详细信息',
    width: 300,
    height: 202,
    autoScroll: true,
    defaults: {
        labelAlign: 'left',
        labelWidth: 100,
        width: 250,
        labelPad: 0,
        x: 10,
        y: 10,
        htmlEncode: true,
        xtype: 'displayfield'
    },
    initComponent: function() {
        this.items = [{
            fieldLabel: '主题',
            name: 'contactTheme'
        }, {
            fieldLabel: '客户',
            name: 'customerName'
        }, {
            fieldLabel: '我方联系人',
            name: 'weContactName'
        }, {
            fieldLabel: '对方联系人',
            name: 'oppositeContactName'
        }, {
            fieldLabel: '联系方式',
            name: 'contactWayName'
        }, {
            fieldLabel: '联系内容',
            name: 'contactContent'
        }, {
            fieldLabel: '预计联系时间',
            name: 'planDateBegin'
        }, {
            fieldLabel: '实际开始时间',
            name: 'realityDateBegin'
        }, {
            fieldLabel: '实际结束时间',
            name: 'realityDateEnd'
        }, {
            fieldLabel: '类型',
            name: 'contactTypeName'
        }, {
            fieldLabel: '是否联系',
            name: 'ifContactName'
        }, {
            fieldLabel: '客户反馈信息',
            name: 'userFeedbackInformation'
        }, {
            fieldLabel: '策略',
            name: 'strategy'
        }, {
            fieldLabel: '备注',
            name: 'remarks'
        }, {
//            itemId: 'chanceTypeName',
//            fieldLabel: '机会状态',
//            name: 'chanceType'
//        }, {
//            itemId: 'flowStatusName',
//            fieldLabel: '事件流程状态',
//            name: 'flowStatus'
//        }, {
//            fieldLabel: '事件名称',
//            name: 'eventName'
//        }, {
//            fieldLabel: '发现机会描述',
//            name: 'findChanceContent'
//        }, {
//            fieldLabel: '验证结果',
//            itemId: 'checkResult',
//            name: 'checkResult'
//        }, {
//            fieldLabel: '验证机会描述',
//            name: 'checkChanceContent'
//        }, {
            fieldLabel: '未联系原因',
            name: 'notContantReason'
        } ];
        this.callParent(arguments);
    }
});