Ext.define('CRM.view.systemManagement.menuManagement.TreeEdit', {
    extend: 'Ext.window.Window',
    alias: 'widget.treeedit',
    id: 'treeedit',
    title: '编辑名称',
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            width: 230,
            height: 50,
            defaults: {
                border: 0,
                margin: '5 20 0 10'
            },
            items: [ {
                xtype: 'hiddenfield',
                name: 'id'
            }, {
                width: 200,
                xtype: 'textfield',
                name: 'text',
                allowBlank: false,
                blankText: '名称不能为空！',
                labelSeparator: redStar,
                enforceMaxLength: true,
                maxLength: 15,
                regex: /^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$/,
                regexText: '名称必须为汉字、字母或数字！',
                labelWidth: 50,
                fieldLabel: '名称'
            } ]
        } ];
        this.buttons = [ {
            text: '保存',
            action: 'save'
        }, {
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});