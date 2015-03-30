Ext.define('CRM.view.customerManagement.contactTrack.ReFeedbackList', {
    extend: 'Ext.window.Window',
    alias: 'widget.refeedbacklist',
    title: '反馈信息',
    id: 'refeedbacklist',
    width: 610,
    height: 520,
    plain: true,
    modal: true,
    autoShow: true,
    resizable: false,
    constrainHeader: true,
    layout: 'fit',
    initComponent: function() {
//        this.chanceEventStore = Ext.create('CRM.store.salesManagement.salesEvent.SalesEventStore');
//        this.chanceEventStoreByStatus = Ext.create('CRM.store.salesManagement.salesEvent.SalesEventStore');
//        this.chanceEventStore.load();
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
            }, {
                name: 'weContact',
                xtype: 'textfield',
                hidden: true
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
                id: 'ifContact2',
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
                width: 300,
                editable: true,
                name: 'realityDateBegin',
                invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                id: 'realityDateBegin1',
                dateRange: {
                    begin: 'realityDateBegin1',
                    end: 'realityDateEnd1'
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
                name: 'realityDateEnd',
                id: 'realityDateEnd1',
                dateRange: {
                    begin: 'realityDateBegin1',
                    end: 'realityDateEnd1'
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
                id: 'userFeedbackInformation1',
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
                id: 'strategy1',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "策略长度不能超过1024个字符！",
                emptyText: '根据反馈信息指定相应的策略！'
//            }, {
//                layout: 'hbox',
//                xtype: 'checkboxgroup',
//                name: 'chanceType',
//                id: 'chancetype1',
//                fieldLabel: '机会状态',
//                defaultValue: 0,
//                items: [ {
//                    width: 90,
//                    name: 'chanceType',
//                    boxLabel: '发现机会',
//                    id: 'findchance1',
//                    inputValue: 1,
//                    hidden: false
//                }, {
//                    width: 90,
//                    name: 'chanceType',
//                    boxLabel: '验证机会',
//                    id: 'checkchance1',
//                    inputValue: 2,
//                    hidden: false
//                } ]
//            }, {
//                layout: 'hbox',
//                xtype: 'textfield',
//                name: 'eventName',
//                fieldLabel: '事件名称',
//                id: 'chancename1',
//                queryMode: 'local',
//                valueField: 'name',
//                displayField: 'eventName',
//                hidden: true,
//                disabled: true,
//                enforceMaxLength: true,
//                maxLength: 50,
//                labelSeparator: redStar,
//                emptyText: '请输入',
//                maxLengthText: "事件名称长度不能超过50个字符！",
//                blankText: "事件名称不能为空！",
//                allowBlank: false
//            }, {
//                xtype: 'combobox',
//                name: 'eventName',
//                fieldLabel: '机会事件',
//                id: 'chanceevent1',
//                queryMode: 'local',
//                valueField: 'eventID',
//                displayField: 'eventName',
//                hidden: true,
//                disabled: true,
//                enforceMaxLength: true,
//                maxLength: 50,
//                labelSeparator: redStar,
//                store: this.chanceEventStoreByStatus,
//                emptyText: '请选择',
//                editable: false,
//                blankText: "机会事件不能为空！",
//                allowBlank: false
//            }, {
//                layout: 'hbox',
//                fieldLabel: '发现机会描述',
//                xtype: 'textarea',
//                name: 'findChanceContent',
//                id: 'findchancecontent1',
//                queryMode: 'local',
//                valueField: 'name',
//                displayField: 'demand',
//                labelSeparator: redStar,
//                height: 60,
//                maxLength: 100,
//                hidden: true,
//                disabled: true,
//                enforceMaxLength: true,
//                emptyText: '请输入描述信息',
//                maxLengthText: "描述长度不能超过100个字符！",
//                blankText: "描述不能为空！",
//                allowBlank: false
//            }, {
//                xtype: 'radiogroup',
//                columns: 2,
//                name: 'checkResult',
//                fieldLabel: '验证结果',
//                id: 'checkresult1',
//                labelSeparator: redStar,
//                hidden: true,
//                disabled: true,
//                width: 300,
//                valueField: 'name',
//                items: [ {
//                    boxLabel: '成功',
//                    name: 'checkResult',
//                    itemId: 'success',
//                    inputValue: true,
//                    width: 90,
//                    checked: true
//                }, {
//                    boxLabel: '失败',
//                    itemId: 'failure',
//                    name: 'checkResult',
//                    width: 90,
//                    inputValue: false
//                } ]
//            }, {
//                layout: 'hbox',
//                fieldLabel: '验证机会描述',
//                xtype: 'textarea',
//                name: 'checkChanceContent',
//                id: 'checkchancecontent1',
//                labelSeparator: redStar,
//                queryMode: 'local',
//                valueField: 'name',
//                displayField: 'demand',
//                height: 60,
//                maxLength: 100,
//                hidden: true,
//                disabled: true,
//                enforceMaxLength: true,
//                emptyText: '请输入描述信息',
//                maxLengthText: "验证机会描述长度不能超过100个字符！",
//                blankText: "验证机会描述不能为空！",
//                allowBlank: false
            }, {
                xtype: 'textarea',
                fieldLabel: '未联系原因',
                labelSeparator: redStar,
                name: 'notContantReason',
                allowBlank: false,
                blankText: "未联系原因不能为空！",
                id: 'notContantReason1',
                disabled: true,
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "未联系原因不能超过1024个字符！",
                emptyText: '添加没有联系客户的原因！'
            } ]
        } ];
        this.buttons = [ {
            id: 'feedBackSave1',
            text: '确定',
            action: 'save1'
        }, {
            id: 'feedBackClear1',
            text: '重置',
            action: 'doClear1'
        }, {
            id: 'feedBackCancel1',
            action: 'doCancel1',
            text: '取消'
        } ];
        this.callParent(arguments);
    }
});