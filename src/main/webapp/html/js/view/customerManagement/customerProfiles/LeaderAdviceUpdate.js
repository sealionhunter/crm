Ext.define('CRM.view.customerManagement.customerProfiles.LeaderAdviceUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.leaderadviceupdate',
    layout: 'fit',
    width: 610,
    height: 220,
    border: false,
    autoShow: true,
    modal: true,
    resizable: false,
    constrainHeader: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            width: 610,
            autoScroll: true,
            defaults: {
                border: 0,
                labelWidth: 100,
                width: 550,
                margin: '10 0 0 20'
            },
            items: [ {
                layout: 'hbox',
                items: [ {
                    name: 'adviceID',
                    xtype: 'hiddenfield',
                    itemId: 'adviceID'
                }, {
                    name: 'customerID',
                    xtype: 'hiddenfield',
                    itemId: 'customerID'
                }, {
                    name: 'userID',
                    xtype: 'hiddenfield',
                    itemId: 'userID'
                }, {
                    name: 'hasRead',
                    xtype: 'hiddenfield',
                    itemId: 'hasRead'
                }, {
                    name: 'createTime',
                    xtype: 'hiddenfield',
                    itemId: 'createTime'
                }, {
                    name: 'updateTime',
                    xtype: 'hiddenfield',
                    itemId: 'updateTime'
                } ]
            }, {
                layout: 'hbox',
                labelWidth: 100,
                width: 550,
                items: [ {
                    xtype: 'textfield',
                    name: 'customerName',
                    itemId: 'customerName',
                    fieldLabel: '客户名称',
                    readOnly: true
                }, {
//                    xtype: 'combobox',
//                    allowBlank: false,
//                    blankText: '领导不能为空！',
//                    name: 'userID',
//                    itemId: 'userID',
//                    labelSeparator: redStar,
//                    fieldLabel: '领导',
//                    margin: '0 0 0 25',
//                    store: Ext.getCmp('leaderadvicelist').userStore,
//                    queryMode: 'local',
//                    editable: false,
//                    displayField: 'realName',
//                    valueField: 'userID'
                    xtype: 'textfield',
                    fieldLabel: '领导名',
                    name: 'userName',
                    itemId: 'userName',
                    margin: '0 0 0 25',
                    readOnly: true
                } ]
            }, {
                xtype: 'textarea',
                fieldLabel: '建议内容',
                labelSeparator: redStar,
                name: 'adviceContent',
                height: 100,
                width: 550,
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "领导建议长度不能超过1024个字符！",
                blankText: "领导建议不能为空！",
                allowBlank: false
            }]
        }];
        this.buttons = [ {
            text: '确定',
            action: 'addOrUpdateLeaderAdvice'
        }, {
            text: '重置',
            action: 'resetLeaderAdvice'
        }, {
            text: '取消',
            action: 'closeWin'
        }];
        this.callParent(arguments);
    }
});