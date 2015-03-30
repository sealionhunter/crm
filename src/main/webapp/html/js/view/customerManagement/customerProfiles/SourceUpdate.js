Ext.define('CRM.view.customerManagement.customerProfiles.SourceUpdate', {
    extend: 'Ext.Window',
    alias: 'widget.sourceupdate',
    width: 380,
    height: 360,
    title: "添加客户来源",
    resizable: false,
    autoShow: false,
    constrainHeader: true,
    modal: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            border: true,
            height: 300,
            defaultType: 'textfield',
            defaults: {
                x: 20,
                width: 320,
                labelWidth: 60
            },
            items: [ {
                y: 20,
                fieldLabel: 'ID ',
                itemId: 'sourceID',
                name: 'sourceID',
                hidden: true
            }, {
                xtype: 'combo',
                itemId: 'customerIDCombo',
                y: 25,
                fieldLabel: '客户',
                labelSeparator: redStar,
                name: 'customerID',
                allowBlank: false, // 不允许为空
                blankText: "客户不能为空！", // 错误提示信息
                store: Ext.getCmp('sourceListCard').customerStore,
                queryMode: 'local',
                editable: false,
                forceSelection: true,
                valueField: 'customerID',
                displayField: 'customerName',
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.customerName)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'displayfield',
                itemId: 'customerIDDisplay',
                y: 25,
                fieldLabel: '客户',
                labelSeparator: redStar,
                name: 'customerName',
                hidden: true,
                htmlEncode: true
            }, {
                y: 30,
                fieldLabel: '地区',
                labelSeparator: redStar,
                name: 'sourceArea',
                itemId: 'sourceArea',
                enforceMaxLength: true,
                maxLength: 50,// 允许输入的最大字符数
                maxLengthText: "地区长度不能超过50个字符！",// 提示文本
                allowBlank: false,
                blankText: "地区不能为空",
                htmlEncode: true
            }, {
                xtype: 'combo',
                y: 35,
                name: 'sourceType',
                itemId: 'sourceType',
                allowBlank: false,
                blankText: "来源不能为空！",
                fieldLabel: '来源',
                labelSeparator: redStar,
                store: Ext.getCmp('sourceListCard').sourceTypeStore,
                queryMode: 'local',
                forceSelection: true,
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>'),
                editable: false,
                valueField: 'code',
                displayField: 'value'
            }, {
                y: 40,
                xtype: 'textareafield',
                fieldLabel: '备注',
                height: 150,
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "备注长度不能超过1024个字符！",
                name: 'descriptions',
                itemId: 'sourcedescriptions'
            } ]

        } ];
        this.buttons = [ {

            type: 'submit',
            text: '确定',
            action: 'updateSourceSubmitBtn'
        }, {
            xtype: 'button',
            text: '清空',
            itemId: 'reset',
            action: 'updateSourceResetBtn'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'updateSourceCancelBtn'
        } ];
        this.callParent(arguments);
    }
});
