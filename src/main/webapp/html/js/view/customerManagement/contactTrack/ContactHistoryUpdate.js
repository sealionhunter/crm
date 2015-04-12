Ext.define('CRM.view.customerManagement.contactTrack.ContactHistoryUpdate', {
    extend : 'Ext.window.Window',
    alias : 'widget.contacthistoryupdate',
    id : 'contacthistoryupdate',
    width : 610,
    height : 540,
    plain : true,
    modal : true,
    autoShow : true,
    resizable : false,
    constrainHeader : true,
    layout : 'fit',
    initComponent : function() {
        this.items = [ {
            xtype : 'form',
            defaults : {
                border : 0,
                labelWidth : 100,
                width : 550,
                margin : '10 20 0 20'
            },
            items : [ {
                layout : 'hbox',
                items : [ {
                    xtype : 'textfield',
                    fieldLabel : '主题',
                    name : 'contactTheme',
                    itemId : 'contactTheme',
                    width : 530,
                    maxLength : 50,
                    allowBlank : false,
                    blankText : '主题不能为空！',
                    labelSeparator : redStar,
                    enforceMaxLength : true,
                    maxLengthText : "主题长度不能超过50个字符！"
                }, {
                    name : 'contactID',
                    xtype : 'hiddenfield'
                }, {
                    name : 'customerID',
                    itemId : 'customerID',
                    xtype : 'hiddenfield'
                }, {
                    fieldLabel : '创建时间',
                    xtype : 'hiddenfield',
                    name : 'createTime'
                }, {
                    fieldLabel : '更新时间',
                    xtype : 'hiddenfield',
                    name : 'updateTime'
                }, {
                    fieldLabel : '更新时间',
                    xtype : 'hiddenfield',
                    name : 'planDateBegin'
                } ]
            }, {
                layout : 'hbox',
                items : [ {
                    xtype : 'combobox',
                    fieldLabel : '对方联系人',
//                    id : 'oppositeContact',
                    name : 'oppositeContact',
                    itemId : 'oppositeContact',
                    queryMode : 'local',
                    displayField : 'contactName',
                    valueField : 'contactID',
                    emptyText : '请选择',
                    labelSeparator : redStar,
                    store : Ext.getCmp('contacthistorylist').cutomerContactStore,
                    blankText : "对方联系人不能为空！",
                    editable : false,
                    allowBlank : false
                }, {
                    xtype : 'combobox',
                    fieldLabel : '类型',
                    labelSeparator : redStar,
//                    id : 'contactType',
                    name : 'contactType',
                    store : Ext.getCmp('contacthistorylist').contactTypeStore,
                    queryMode : 'local',
                    displayField : 'value',
                    valueField : 'code',
                    margin : '0 0 0 25',
                    emptyText : '请选择',
                    editable : false,
                    allowBlank : false,
                    blankText : '类型不能为空！'
                } ]
            }, {
                layout : 'hbox',
                items : [ {
                    xtype : 'combobox',
                    store : Ext.getCmp('contacthistorylist').contactWayStore,
//                    id : 'contactWay',
                    name : 'contactWay',
                    fieldLabel : '联系方式',
                    labelSeparator : redStar,
                    queryMode : 'local',
                    displayField : 'value',
                    valueField : 'code',
                    emptyText : '请选择',
                    editable : false,
                    blankText : "联系方式不能为空！",
                    allowBlank : false
                } ]
            }, {
                fieldLabel : '联系内容',
                xtype : 'textarea',
                labelSeparator : redStar,
//                id : 'contactContent',
                name : 'contactContent',
                height : 50,
                width : 530,
                maxLength : 1024,
                enforceMaxLength : true,
                maxLengthText : "联系内容长度不能超过1024个字符！",
                blankText : "联系内容不能为空！",
                allowBlank : false
            }, {
                fieldLabel : '备注',
                xtype : 'textarea',
//                id : 'remarks',
                height : 50,
                width : 530,
                maxLength : 1024,
                enforceMaxLength : true,
                maxLengthText : "备注长度不能超过1024个字符！",
                name : 'remarks'
            }, {
                xtype : 'radiogroup',
                fieldLabel : '是否联系',
                columns : 2,
                id : 'ifContact2',
                width : 300,
                name : 'ifContact',
                items : [ {
                    boxLabel : '已联系',
                    itemId : 'contact',
                    name : 'ifContact',
                    width : 90,
                    inputValue : true,
                    checked : true
                }, {
                    boxLabel : '未联系',
                    itemId : 'noContact',
                    width : 90,
                    name : 'ifContact',
                    inputValue : false
                } ]
            }, {layout : 'hbox',
                items : [ {
                    xtype : 'datetimefield',
                    fieldLabel : "开始时间",
                    labelSeparator : redStar,
                    emptyText : '年-月-日 时:分:秒',
                    isFormField : true,
                    format : 'Y-m-d H:i:s',
                    allowBlank : false,
                    blankText : "开始时间不能为空！",
                    editable : true,
                    name : 'realityDateBegin',
                    invalidText : '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                    id : 'realityDateBegin1',
                    minValue: utils.getDate(-1),
                    minText: '开始时间不能小于昨天',
                    maxValue: utils.getDate(1),
                    maxText: '开始时间不能大于今天',
                    dateRange : {
                        begin : 'realityDateBegin1',
                        end : 'realityDateEnd1'
                    },
                    vtype : 'dateRange'
                }, {
                    xtype : 'datetimefield',
                    fieldLabel : "结束时间",
                    labelSeparator : redStar,
                    emptyText : '年-月-日  时:分:秒',
                    format : 'Y-m-d H:i:s',
                    invalidText : '日期格式不正确，正确格式为：YYYY-MM-DD hh:mm:ss！',
                    allowBlank : false,
                    blankText : "结束时间不能为空！",
                    margin : '0 0 0 25',
                    editable : true,
                    minValue: utils.getDate(-1),
                    minText: '结束时间不能小于昨天',
                    maxValue: utils.getDate(1),
                    maxText: '结束时间不能大于今天',
                    name : 'realityDateEnd',
                    id : 'realityDateEnd1',
                    dateRange : {
                        begin : 'realityDateBegin1',
                        end : 'realityDateEnd1'
                    },
                    vtype : 'dateRange'
                } ]
            }, {
                xtype : 'textarea',
                fieldLabel : '反馈信息',
                labelSeparator : redStar,
                name : 'userFeedbackInformation',
                allowBlank : false,
                queryMode : 'local',
                valueField : 'name',
                displayField : 'userFeedbackInformation',
                blankText : "反馈信息不能为空！",
                id : 'userFeedbackInformation1',
                maxLength : 1024,
                width : 530,
                enforceMaxLength : true,
                maxLengthText : "反馈信息长度不能超过1024个字符！",
                emptyText : '添加一些用户反馈信息！'
            }, {
                xtype : 'textarea',
                fieldLabel : '策略',
                labelSeparator : redStar,
                name : 'strategy',
                queryMode : 'local',
                valueField : 'name',
                displayField : 'strategy',
                allowBlank : false,
                blankText : "策略不能为空！",
                id : 'strategy1',
                enforceMaxLength : true,
                maxLength : 1024,
                width : 530,
                maxLengthText : "策略长度不能超过1024个字符！",
                emptyText : '根据反馈信息指定相应的策略！'
            }, {
                xtype : 'textarea',
                fieldLabel : '未联系原因',
                labelSeparator : redStar,
                name : 'notContantReason',
                allowBlank : false,
                blankText : "未联系原因不能为空！",
                id : 'notContantReason1',
                disabled : true,
                enforceMaxLength : true,
                width : 530,
                maxLength : 1024,
                maxLengthText : "未联系原因不能超过1024个字符！",
                emptyText : '添加没有联系客户的原因！'
            } ]
        } ];
        this.buttons = [ {
            text : '确定',
            action : 'save'
        }, {
            text : '重置',
            itemId : 'reset',
            action : 'reset'
        }, {
            action : 'cancel',
            text : '取消'
        } ];
        this.callParent(arguments);
    }
});