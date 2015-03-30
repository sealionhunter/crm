Ext.define('CRM.view.systemManagement.groupManagement.GroupUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.groupupdate',
    id: 'groupupdate',
    layout: 'fit',
    resizable: false,
    autoShow: true,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.accessRightsTreeStore = Ext.create('CRM.store.systemManagement.groupManagement.GroupAccessRightsTree');
        this.operationRightsTreeStore = Ext.create('CRM.store.systemManagement.authorizationManagement.OperationRightsTree');
        this.items = [ {
            xtype: 'form',
            layout: 'border',
            width: 530,
            height: 485,
            defaults: {
                border: 0
            },
            items: [ {
                region: 'north',
                items: [ {
                    name: 'groupID',
                    itemId: 'groupIDBox',
                    xtype: 'hiddenfield'
                }, {
                    margin: '10 10 10 10',
                    xtype: 'textfield',
                    width: 510,
                    itemId: 'groupNameBox',
                    name: 'groupName',
                    labelSeparator: redStar,
                    allowBlank: false,
                    blankText: '角色名称不能为空！',
                    maxLength: 15,
                    enforceMaxLength: true,
                    regex: /^[_a-zA-Z0-9\u4e00-\u9fa5]{1,20}$/,
                    regexText: '角色名必须1-15位的数字，字母，下划线或汉字！',
                    labelSeparator: redStar,
                    labelWidth: 70,
                    fieldLabel: '角色名称'
                } ]
            }, {
                layout: 'hbox',
                region: 'center',
                items: [ {
                    width: 260,
                    height: 430,
                    margin: '0 0 5 5',
                    xtype: 'treepanel',
                    title: '访问权限',
                    collapsible: false,
                    border: true,
                    store: this.accessRightsTreeStore,
                    rootVisible: false,
                    autoScroll: true,
                    animate: true,
                    multiSelect: true,
                    enableDD: false,
                    containerScroll: true,
                    id: 'groupAccessRightsTree',
                    useArrows: false
                }, {
                    margin: '0 5 5 5',
                    xtype: 'panel',
                    title: '操作权限',
                    layout: 'card',
                    border: true,
                    width: 240,
                    height: 430,
                    activeItem: 0,
                    id: 'groupOperationCard',
                    items: [ {
                        xtype: 'treepanel',
                        collapsible: false,
                        store: this.operationRightsTreeStore,
                        border: false,
                        rootVisible: false,
                        animate: false,
                        multiSelect: true,
                        useArrows: false,
                        containerScroll: true,
                        id: 'groupOperationRightsTree',
                        viewConfig: {
                            loadMask: false
                        },
                        itemId: 'groupOperationRightsTree'
                    }, {
                        xtype: 'displayfield',
                        value: ' ',
                        itemId: 'groupOperationStateDisplay',
                        id: 'groupOperationStateDisplay'
                    } ]
                } ]
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'save'
        }, {
            itemId: 'groReset',
            action: 'reset'
        }, {
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});