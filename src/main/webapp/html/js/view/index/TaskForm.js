Ext.define('CRM.view.index.TaskForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.taskform',
    title: '详细信息',
    id: 'taskform',
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
            itemId: 'taskForm',
            layout: 'vbox',
            defaults: {
                border: 0,
                labelWidth: 100,
                width: 550,
                margin: '10 20 0 20'
            },
            items: [ {
                id: 'taskFormWorkID',
                name: 'workID',
                xtype: 'displayfield',
                hidden: true
            }, {
                name: 'userID',
                xtype: 'displayfield',
                hidden: true
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    name: 'theme',
                    width: 530,
                    readOnly: true,
                    fieldLabel: '主题'
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    name: 'customerName',
                    fieldLabel: '客户',
                    width: 530,
                    readOnly: true,
                    hideTrigger: true
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    name: 'workTypeName',
                    width: 530,
                    fieldLabel: '任务类型',
                    readOnly: true,
                    hideTrigger: true
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    name: 'priorityName',
                    fieldLabel: '优先级',
                    readOnly: true,
                    hideTrigger: true
                }, {
                    xtype: 'textfield',
                    name: 'completionName',
                    fieldLabel: '完成情况',
                    margin: '0 0 0 25',
                    readOnly: true,
                    hideTrigger: true,
                    border: false
                } ]
            }, {
                layout: 'hbox',
                items: [ {
                    xtype: 'textfield',
                    fieldLabel: '开始时间',
                    name: 'startTime',
                    readOnly: true
                }, {
                    xtype: 'textfield',
                    fieldLabel: '结束时间',
                    margin: '0 0 0 25',
                    name: 'endTime',
                    readOnly: true
                } ]
            }, {
                xtype: 'textarea',
                name: 'workDetail',
                width: 530,
                height: 150,
                readOnly: true,
                fieldLabel: '任务内容'
            }, {
                xtype: 'textarea',
                name: 'descriptions',
                width: 530,
                readOnly: true,
                fieldLabel: '备注'
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'closeTask'
        } ];
        this.callParent(arguments);
    }

});