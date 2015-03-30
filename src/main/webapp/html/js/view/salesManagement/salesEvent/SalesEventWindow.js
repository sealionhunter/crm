Ext.define('CRM.view.salesManagement.salesEvent.SalesEventWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.transactionHandleWindow',
    id: 'transactionHandleWindow',
    width: 450,
    height: 300,
    resizable: false,
    autoShow: true,
    constrainHeader: true,
    layout: 'fit',
    modal: true,
    initComponent: function() {
        this.salesEventTransactionStore = Ext.create('CRM.store.code.Code');
        this.salesEventTransactionStore.load({
            params: {
                code: '00090004'
            }
        });
        this.items = [ {
            xtype: 'form',
            id: 'transactionHandleForm',
            defaultType: 'displayfield',
            autoScroll: true,
            defaults: {
                margin: '5 10 5 15',
                labelWidth: 80,
                htmlEncode: true,
                width: 400
            },
            items: [ {
                name: 'eventID',
                hidden: true
            }, {
                fieldLabel: '客户',
                name: 'customerName'
            }, {
                fieldLabel: '现处阶段',
                name: 'statusName'
            }, {
                hidden: true,
                name: 'nextStatus',
                itemId: 'nextStatus'
            }, {
                itemId: 'nextStatusName',
                fieldLabel: '下一阶段',
                name: 'nextStatusName'
            }, {
                xtype: 'combobox',
                id: 'salesEventTransaction',
                itemId: 'salesEventTransaction',
                name: 'salesEventTransaction',
                fieldLabel: '事件处理',
                labelSeparator: redStar,
                allowBlank: false,
                blankText: '事件处理不能为空！',
                store: this.salesEventTransactionStore,
                editable: false,
                queryMode: 'local',
                displayField: 'value',
                valueField: 'code'
            }, {
                itemId: 'nextDemand',
                fieldLabel: '下一阶段需求',
                name: 'demand',
                xtype: 'textarea',
                width: 400,
                height: 100,
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "备注长度不能超过1024个字符！"
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'transactionProcessing',
            itemId: 'transactionProcessingButton',
            id: 'transactionProcessing'
        }, {
            text: ' 重置',
            itemId: 'transactionProcessingReset',
            action: 'transactionProcessingReset'
        }, {
            text: '取消',
            scope: this,
            handler: this.close
        } ];
        this.callParent(arguments);
    }
});