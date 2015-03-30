Ext.define('CRM.view.systemManagement.codeManagement.CodeUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.CodeUpdate',
    id: 'CodeUpdate',
    itemId: 'CodeUpdate',
    resizable: false,
    autoShow: true,
    layout: 'fit',
    border: 0,
    height: 225,
    width: 310,
    title: '添加Code',
    modal: true,
    initComponent: function() {
        this.moduleStore = Ext.create('CRM.store.systemManagement.codeManagement.Module');
        this.categoryStore = Ext.create('CRM.store.systemManagement.codeManagement.Category');
        this.items = [ {
            xtype: 'form',
            layout: 'vbox',
            bodyPadding: 12,
            items: [ {
                xtype: 'combobox',
                name: 'addMode',
                id: 'addModeComboBox',
                fieldLabel: 'Code类别',
                labelSeparator: redStar,
                store: Ext.getCmp('CodeList').modeStore,
                margin: 3,
                queryMode: 'local',
                blankText: 'Code类别不能为空！',
                allowBlank: false,
                editable: false,
                defaultListConfig: {
                    loadMask: false
                },
                valueField: 'code',
                displayField: 'value'
//                    ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'combobox',
                name: 'module',
                fieldLabel: '模块',
                blankText: '模块不能为空！',
                labelSeparator: redStar,
                allowBlank: false,
                store: this.moduleStore,
                margin: 3,
                editable: false,
                valueField: 'code',
                displayField: 'value'
//                    ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'combobox',
                name: 'category',
                fieldLabel: '分类',
                labelSeparator: redStar,
                allowBlank: false,
                blankText: '分类不能为空！',
                store: this.categoryStore,
                margin: 3,
                editable: false,
                displayField: 'value',
                valueField: 'code',
                defaultListConfig: {
                    loadMask: false
                }
//            ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'textfield',
                name: 'modeText',
                margin: 3,
                readOnly: true,
                hidden: true,
                fieldLabel: 'Code类别'
            }, {
                xtype: 'textfield',
                name: 'moduleText',
                margin: 3,
                readOnly: true,
                hidden: true,
                fieldLabel: '模块'
            }, {
                xtype: 'textfield',
                name: 'categoryText',
                margin: 3,
                readOnly: true,
                hidden: true,
                fieldLabel: '分类'
            }, {
                xtype: 'textfield',
                name: 'code',
                margin: 3,
                maxLength: 12,
                readOnly: true,
                hidden: true,
                fieldLabel: '编号'
            }, {
                xtype: 'textfield',
                name: 'value',
                margin: 3,
                labelSeparator: redStar,
                blankText: '数据项不能为空！',
                allowBlank: false,
                enforceMaxLength: true,
                htmlEncode: true,
                maxLength: 50,
                maxLengthText: '字符长度不能超过50位',
                fieldLabel: '数据项'
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'save'
        }, {
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});
