Ext.define('CRM.view.systemManagement.passwordChange.PasswordChange', {
    extend: 'Ext.Window',
    alias: 'widget.passwordchange',
    id: 'passwordchange',
    title: "修改密码",
    constrainHeader: true,
    width: 380,
    height: 200,
    autoShow: true,
    layout: 'fit',
    resizable: false,
    modal: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            layout: 'anchor',
            defaults: {
                anchor: '90%',
                labelAlign: 'left',
                labelWidth: 80,
                margin: '10 20 10 10',
                xtype: 'textfield',
                labelSeparator: redStar,
                inputType: 'password',
                allowBlank: false,
                regex: /^[a-zA-Z0-9_]{6,20}$/,
                regexText: '密码为6-20位的数字，字母或下划线！'
            },
            items: [ {
                fieldLabel: '原密码',
                name: 'oldPassword',
                blankText: '原密码不能为空！',
                id: 'oldPassword'
            }, {
                fieldLabel: '新密码',
                name: 'newPassword',
                blankText: '新密码不能为空！',
                id: 'newPassword'
            }, {
                fieldLabel: '确认新密码',
                name: 'confirmPassword',
                blankText: '确认新密码不能为空！',
                id: 'confirmPassword'
            } ],
            buttons: [ {
                text: '确定',
                action: 'passwordChangeConfirm'
            }, {
                text: '清空',
                action: 'passwordChangeReset'
            }, {
                text: '取消',
                action: 'passwordChangeCancel'
            } ]
        } ];
        this.callParent(arguments);
    }
});