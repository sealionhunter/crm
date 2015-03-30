$(function() {
    $("#loginBtn").mouseover(function() {
        $("#loginBtn").removeClass("loginBtnNormal").addClass("loginBtnEnter");
    });
    $("#loginBtn").mouseleave(function() {
        $("#loginBtn").removeClass("loginBtnEnter").addClass("loginBtnNormal");
    });
    $("#loginBtn").click(function() {
        $("#loginBtn").removeClass("loginBtnNormal").addClass("loginBtnEnter");
        var userName = $("#userName").val();
        var password = $("#password").val();
        var str = /^[a-zA-Z0-9_]{1}[a-zA-Z0-9]{0,19}$/g;// 用户名长度1-20之间
        if (userName.trim() != '' && userName.trim() != null) {
            if (str.test(userName)) {
                Ext.Ajax.request({
                    url: 'login.action',
                    params: {
                        userName: userName,
                        password: password
                    },
                    success: function(response) {
                        var responseText = Ext.decode(response.responseText) || '';
                        validate = responseText.validate || '';
                        if (responseText.validate == false) {
                            Ext.iterate(responseText, setVal);
                            var errorMs = '';
                            if (responseText.userName != null) {
                                errorMs = responseText.userName;
                            } else if (responseText.password != null) {
                                errorMs = responseText.password;
                            }
                            Ext.Msg.alert('提示', errorMs);
                        } else {
                            userInfo = responseText.userInfo;
                            if (userInfo != null) {
                                window.location.href = 'main.action';
                            } else {
                                Ext.Msg.alert('提示', '账号或密码不正确！');
                            }
                        }
                    },
                    failure: function(response) {
                        Ext.Msg.alert('提示', '出错，请联系管理员！');
                    }
                });
            } else {
                Ext.Msg.alert('提示', '账号或密码不正确！');
            }
        } else {
            Ext.Msg.alert('提示', '用户名不能为空！');
        }
    });
});
$(function() {
    $("#clearBtn").mouseover(function() {
        $("#clearBtn").removeClass("clearBtnNormal").addClass("clearBtnEnter");
    });
    $("#clearBtn").mouseleave(function() {
        $("#clearBtn").removeClass("clearBtnEnter").addClass("clearBtnNormal");
    });
    $("#clearBtn").click(function() {
        $("#clearBtn").removeClass("clearBtnNormal").addClass("clearBtnEnter");
    });
});
function clearValue() {
    $("input[name=userId]").val("");
    $("input[name=password]").val("");
}
function changeInput() {
    if ($(".inputNormal").length > 0) {
        $(".inputNormal").removeClass("inputNormal").addClass("inputFocus");
    }
}

function changeInputNormal() {
    $(".inputFocus").removeClass("inputFocus").addClass("inputNormal");
}
function changePwd() {
    if ($(".passwordNormal").length > 0) {
        $(".passwordNormal").removeClass("passwordNormal").addClass("passwordFocus");
    }
}
function changePwdNormal() {
    $(".passwordFocus").removeClass("passwordFocus").addClass("passwordNormal");
}

//Ext.onReady(function() {
//    Ext.form.Field.prototype.msgTarget = 'side';
//    var login = Ext.create('Ext.form.Panel', {
//        frame: true,
//        id: 'login',
//        width: 300,
//        height: 160,
//        enableKeyEvents: true,
//        buttonAlign: 'center',
//        defaults: {
//            labelAlign: 'right',
//            labelWidth: 50,
//            margin: '10 20 10 10',
//            xtype: "textfield",
//            anchor: '90%',
//            enforceMaxLength: true,
//            enableKeyEvents: true,
//            maxLength: 20
//        },
//        keys: [ {
//            key: [ 10, 13 ],
//            fn: submit
//        } ],
//        items: [ {
//            xtype: 'header',
//            html: '<div align="left"><h2>用户登录</h2></div>',
//            height: 30
//        }, {
//            fieldLabel: "用户名",
//            name: 'userName',
//            id: 'userName',
//            listeners: {
//                blur: blur,
//                specialkey: function(field, e) {
//                    if (e.getKey() == e.ENTER) {
//                        var password = Ext.getCmp('password');
//                        if (password.getValue() == '') {
//                            password.focus();
//                        } else {
//                            submit();
//                        }
//                    }
//                }
//            }
//        }, {
//            fieldLabel: "密&nbsp;&nbsp;&nbsp;码",
//            name: 'password',
//            id: 'password',
//            inputType: 'password',
//            listeners: {
//                specialkey: function(field, e) {
//                    if (e.getKey() == e.ENTER) {
//                        submit();
//                    }
//                }
//            }
//        } ],
//        buttons: [ {
//            text: "确 定",
//            handler: submit
//        }, {
//            text: "清空",
//            handler: reset
//        } ]
//    });
//    login.render('loginForm');
//});
