Ext.define('CRM.view.main.Main', {
    layout: 'fit',
    extend: 'Ext.container.Viewport',
    alias : 'widget.mainView',
    listeners: {
        resize : function(mainView) {
            var scrollHeight = document.documentElement.scrollHeight,
            scrollWidth = document.documentElement.scrollWidth,
            minHeight = mainView.minHeight,
            minWidth = mainView.minWidth,
            height = mainView.height,
            width = mainView.width;
            if(minHeight > height || minWidth > width) {
                          document.body.style.overflow = 'auto';
            } else if(width >= scrollWidth && height === scrollHeight){
                          document.body.style.overflow = 'hidden';
            } else {
                          document.body.style.overflow = 'auto';
            }
            mainView.doLayout();
        }
    },
    minHeight: 600,
    minWidth: 800,
//    autoScroll: true,
    items: [ {
        xtype: 'panel',
        layout: 'fit',
        border: false,
        overflowX : 'hidden',
        overflowY : 'hidden',
        items: [ {
            xtype: 'panel',
            layout: 'border',
            border: false,
            overflowX : 'hidden',
            overflowY : 'hidden',
            items: [ {
                region: "center",
                xtype: 'panel',
                layout: 'card',
                border: false,
                activeItem: 0,
                margins: '5 5 5 0',
                id: 'centercard'
            }, {
                region: "west",
                id: 'crmTreeMenu',
                xtype: 'crmmenu'
            }, {
                region: "north",
                xtype: 'toolbar',
                style : 'background-color:#ccdcf2; background-image:url();',
                frame: false,
                height: 50,
                items: [ {
                    xtype: 'box',
                    autoEl: {
                        tag: 'img',
                        src : 'html/img/top_logo.png'
                    }
                }, '->', {
//                    id: 'message',
//                    xtype: 'button',
//                    text: '<font size="2.5">' + '【0条未读消息】' + '</font>',
//                    listeners: {
//                        click: function() {
//                            var messagelist = Ext.getCmp('messagelist');
//                            utils.changeMessageText();
//                            if (typeof (messagelist) == 'undefined') {
//                                Ext.widget('messagelist');
//                            } else {
//                                messagelist.close();
//                            }
//                        }
//                    }
//                }, {
//                    xtype: 'tbseparator',
//                    id: 'message_tbs'
//                }, {
                    xtype : 'splitbutton',
                    text : '<font color="blue" size="2.5">' + REAL_NAME + '</font>',
                    menu : [ {
                        text : '修改密码',
                        handler : function() {
                            initController('systemManagement.passwordChange.PasswordChangeController');
                            Ext.widget('passwordchange');
                        }
                    } ]
                }, '-', {
                    xtype: 'button',
                    text: '<font size="2.5">注销</font>',
                    handler: function(button) {
                        box.confirm('提示', '确定注销？', function showResult(button) {
                            if (button == 'yes') {
                                window.location.href = 'logout.action';
                            }
                        });
                    }
                }, '-', {
                    xtype: 'tbtext',
                    id: 'tbtext_clock',
                    text: '<font size="2.5">' + Ext.Date.format(new Date(), 'Y-m-d G:i:s A') + ' ' + aWeek[new Date().getDay()] + '</font>'
                } ],
                listeners: {
                    afterrender: function(container, companet, index) {
                        if (GROUP_ID == 0 || GROUP_ID == 1) {
//                            Ext.getCmp('message').hide();
//                            Ext.getCmp('message_tbs').hide();
                        }
                    }
                }
            }, {
                region: 'south',
                height: 20,
                html: '<center><span class="copytext">(C)COPYRIGHT 2013-2015, XXX 版权所有 （C）XXX<br /></span></center>'
            } ]
        } ]
    } ]
});
