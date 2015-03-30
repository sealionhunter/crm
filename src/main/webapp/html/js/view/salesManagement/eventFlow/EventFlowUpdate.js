Ext.define('CRM.view.salesManagement.eventFlow.EventFlowUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.eventFlowUpdate',
    id: 'eventFlowUpdate',
    layout: 'fit',
    autoShow: true,
    resizable: false,
    constrainHeader: true,
    modal: true,
    initComponent: function() {
        this.categoryStore = Ext.create('CRM.store.salesManagement.salesEventFlowCode.SalesEventFlowCategory');
        this.categoryStore.load();
        this.items = [ {
            xtype: 'form',
            width: 340,
            border: false,
            bodyPadding: 5,
            fieldDefaults: {
                labelAlign: 'left',
                labelWidth: 110,
                anchor: '100%',
                margin: '5 5 5 5'
            },
            items: [ {
                xtype: 'combobox',
                id: 'SalesEventFlowCodeCategory',
                itemId: 'SalesEventFlowCodeCategory',
                name: 'category',
                fieldLabel: '流程所属阶段',
                labelSeparator: redStar,
                allowBlank: false,
                blankText: '事件模块名称不能为空！',
                store: this.categoryStore,
                editable: false,
                queryMode: 'local',
                displayField: 'categoryName',
                valueField: 'category'
            }, {
                xtype: 'textfield',
                id: 'eventFlowName',
                itemId: 'eventFlowName',
                name: 'value',
                fieldLabel: '销售事件流程名称',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '最大长度不能超过50个字符！',
                labelSeparator: redStar,
                allowBlank: false,
                blankText: '销售事件流程名称不能为空！',
                regex: /^[a-zA-Z0-9\u4e00-\u9fa5]{1,100}$/,
                regexText: "名称只能为汉字、字母或数字！"
            } ]
        } ];

        this.buttons = [ {
            text: '确定',
            action: 'update',
            itemId: 'updateButton',
            id: 'eventFlowUpdateButton'
        }, {
            itemId: 'cusReset',
            action: 'doreset'
        }, {
            text: '取消',
            scope: this,
            handler: this.close
        } ];

        this.callParent(arguments);
    }
});
