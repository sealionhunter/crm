Ext.define('CRM.controller.systemManagement.passwordChange.PasswordChangeController', {
    extend: 'Ext.app.Controller',
    views: [ 'systemManagement.passwordChange.PasswordChange' ],
    stores: [],
    models: [],
    init: function() {
        this.control({
            'passwordchange button[action=passwordChangeCancel]': {
                click: utils.winClose
            },
            'passwordchange button[action=passwordChangeReset]': {
                click: utils.resetRecord
            },
            'passwordchange button[action=passwordChangeConfirm]': {
                click: this.passwordChange
            }
        });
    },
    passwordChange: function(button) {
        var win = button.up('window');
        var form = win.down('form');
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            var oldPassword = Ext.getCmp('oldPassword').getValue();
            var newPassword = Ext.getCmp('newPassword').getValue();
            var confirmPassword = Ext.getCmp('confirmPassword').getValue();
            if (newPassword == confirmPassword) {
                Ext.Ajax.request({
                    url: 'passwordChange.action',
                    params: {
                        oldPassword: oldPassword,
                        newPassword: newPassword
                    },
                    success: function(response) {
                        if (CRM.checkResponse(response)) {
                            return;
                        }
                        var responseText = Ext.decode(response.responseText) || '';
                        // validate = responseText.validate || '';
                        if (responseText.validate == false) {
                            Ext.iterate(responseText, setVal);
                            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                        } else {
                            if (responseText.msg == 'changesuccess') {
                                messageBox.alert("提示", "密码修改成功！");
                                win.close();
                            } else {
                                form.down('[name=oldPassword]').markInvalid('原密码输入错误，请重新输入！');
                                messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                            }
                        }
                    },
                    failure: function(response) {
                        messageBox.alert('提示', '提交失败，请联系管理员！');
                    }
                });
            } else {
                form.down('[name=confirmPassword]').markInvalid('两次密码输入不一致，请重新输入！');
                messageBox.alert('提示', '输入信息有误，请检查输入信息！');
            }
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    }

});