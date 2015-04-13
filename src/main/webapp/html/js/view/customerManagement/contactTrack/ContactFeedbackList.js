Ext.define('CRM.view.customerManagement.contactTrack.ContactFeedbackList', {
    extend: 'Ext.window.Window',
    alias: 'widget.contacttrackcontactfeedbacklist',
    title: '反馈信息',
    id: 'contacttrackcontactfeedbacklist',
    width: 610,
    height: 520,
    modal: true,
    autoShow: true,
    frame: false,
    closable: true,
    resizable: false,
    constrainHeader: true,
    layout: 'fit',
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            defaults: {
                border: 0,
                labelWidth: 100,
                width: 530,
                margin: '10 20 0 20'
            },
            items: [ {
                name: 'contactID',
                xtype: 'textfield',
                hidden: true
            }, {
                name: 'customerID',
                xtype: 'textfield',
                hidden: true
            }, {
                name: 'contactTheme',
                xtype: 'textfield',
                hidden: true
//            }, {
//                name: 'weContact',
//                xtype: 'textfield',
//                hidden: true
            }, {
                name: 'oppositeContact',
                xtype: 'textfield',
                hidden: true
            }, {
                name: 'contactWay',
                xtype: 'textfield',
                hidden: true
            }, {
                name: 'contactContent',
                xtype: 'textfield',
                hidden: true
            }, {
                name: 'contactType',
                xtype: 'textfield',
                hidden: true
            }, {
                name: 'planDateBegin',
                xtype: 'datetimefield',
                hidden: true
            }, {
                name: 'remarks',
                xtype: 'textfield',
                hidden: true
            }, {
                xtype: 'radiogroup',
                fieldLabel: '是否联系',
                columns: 2,
                id: 'ifContact1',
                width: 300,
                name: 'ifContact',
                items: [ {
                    boxLabel: '已联系',
                    itemId: 'contact',
                    name: 'ifContact',
                    width: 90,
                    inputValue: true,
                    checked: true
                }, {
                    boxLabel: '未联系',
                    itemId: 'noContact',
                    width: 90,
                    name: 'ifContact',
                    inputValue: false
                } ]
            }, {
                xtype: 'datetimefield',
                fieldLabel: "开始时间",
                labelSeparator: redStar,
                emptyText: '年-月-日      时：分：秒',
                isFormField: true,
                format: 'Y-m-d H:i:s',
                allowBlank: false,
                blankText: "开始时间不能为空！",
                minValue: '01/01/1753',
                minText: '日期不能小于1753-01-01！',
                width: 300,
                editable: true,
                name: 'realityDateBegin',
                invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                id: 'realityDateBegin',
                dateRange: {
                    begin: 'realityDateBegin',
                    end: 'realityDateEnd'
                },
                vtype: 'dateRange'
            }, {
                xtype: 'datetimefield',
                fieldLabel: "结束时间",
                labelSeparator: redStar,
                emptyText: '年-月-日      时：分:秒',
                format: 'Y-m-d H:i:s',
                invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                allowBlank: false,
                blankText: "结束时间不能为空！",
                width: 300,
                editable: true,
                minValue: '01/01/1753',
                minText: '日期不能小于1753-01-01！',
                name: 'realityDateEnd',
                id: 'realityDateEnd',
                dateRange: {
                    begin: 'realityDateBegin',
                    end: 'realityDateEnd'
                },
                vtype: 'dateRange'
            }, {
                xtype: 'textarea',
                fieldLabel: '反馈信息',
                labelSeparator: redStar,
                name: 'userFeedbackInformation',
                allowBlank: false,
                queryMode: 'local',
                valueField: 'name',
                displayField: 'userFeedbackInformation',
                blankText: "反馈信息不能为空！",
                id: 'userFeedbackInformation',
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "反馈信息长度不能超过1024个字符！",
                emptyText: '添加一些用户反馈信息！'
            }, {
                xtype: 'textarea',
                fieldLabel: '策略',
                labelSeparator: redStar,
                name: 'strategy',
                queryMode: 'local',
                valueField: 'name',
                displayField: 'strategy',
                allowBlank: false,
                blankText: "策略不能为空！",
                id: 'strategy',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "策略长度不能超过1024个字符！",
                emptyText: '根据反馈信息指定相应的策略！'
            }, {
                xtype: 'textarea',
                fieldLabel: '未联系原因',
                labelSeparator: redStar,
                name: 'notContantReason',
                allowBlank: false,
                blankText: "未联系原因不能为空！",
                id: 'notContantReason',
                disabled: true,
                enforceMaxLength: true,
                hidden: true,
                maxLength: 1024,
                maxLengthText: "未联系原因不能超过1024个字符！",
                emptyText: '添加没有联系客户的原因！'
            } ]
        } ];
        this.buttons = [ {
            id: 'feedBackSave',
            text: '确定',
            action: 'save'
        }, {
            id: 'feedBackClear',
            text: '清空',
            action: 'doClear'
        }, {
            id: 'feedBackCancel',
            action: 'doCancel',
            text: '取消'
        } ];
        this.callParent(arguments);
    }
});