Ext.define('CRM.view.customerManagement.customerProfiles.ContactSelect2', {
    extend: 'Ext.window.Window',
    alias: 'widget.contactselect2',
    id: 'contactSelect2',
    autoShow: true,
    width: 450,
    height: 400,
    resizable: false,
    constrainHeader: true,
    modal: true,
//    layout: {
//        type: 'hbox',
//        align: 'stretch',
//        padding: 5
//    },
    layout: 'fit',
    initComponent: function() {
//        this.ContactSelectStore = Ext.create('CRM.store.customerManagement.customerProfiles.ContactSelect');
        this.ContactCanSelectStore = Ext.create('CRM.store.customerManagement.customerProfiles.ContactCanSelect');
        this.items = [ {
            xtype: 'gridpanel',
            id: 'contactSListForm',
            // multiSelect : false,
            flex: 3.2,
            store: this.ContactCanSelectStore,
            // selType : 'checkboxmodel',
            multiSelect: true,
            selModel: Ext.create('Ext.selection.CheckboxModel'),
            bbar: [ {
                xtype: 'pagingtoolbar',
                store: this.ContactCanSelectStore,
//                width: 390,
                displayInfo: true
            } ],
            columns: [ {
                text: "姓名",
                flex: 1,
                dataIndex: 'contactName',
                renderer: this.rendererValue,
                sortable: true
            }, {
                text: "单位",
                flex: 1,
                dataIndex: 'company',
                renderer: this.rendererValue,
                sortable: true
            }, {
                text: "职务",
                flex: 1,
                dataIndex: 'job',
                renderer: this.rendererValue,
                sortable: true
            }, {
                text: "联系电话",
                flex: 1,
                dataIndex: 'phoneNumber',
                renderer: this.rendererValue,
                sortable: true
            } ],
            stripeRows: true,
//            title: '可选择联系人',
//            margins: '0 2 0 0',
            tbar: [ {
                // 条件输入框
                xtype: 'textfield',
                id: 'searchText_contact',
                width: 150,
                name: 'searchText_contact',
                vtype: 'search',
                enforceMaxLength: true,
                maxLength: 1024,
                selectOnFocus: true,
                maxLengthText: "检索内容长度不能超过1024个字符！"
            }, {
                xtype: 'button',
                text: '检索',
                id: 'searchText_contactBtn',
                itemId: 'searchText'
            } ]
//        }, {
//            layout: {
//                type: 'vbox',
//                padding: '5',
//                pack: 'center',
//                align: 'stretch'
//            },
//            flex: 0.6,
//            id: 'operation',
//            border: false,
//            defaults: {
//                margins: '0 0 10 0'
//            },
//            items: [ {
//                xtype: 'button',
//                text: '添加 >',
//                id: 'contactSelectAdd',
//                action: 'contactSelectAdd',
//                disabled: true
//            }, {
//                xtype: 'tbspacer',
//                flex: 1
//            }, {
//                xtype: 'tbspacer',
//                flex: 1
//            }, {
//                xtype: 'tbspacer',
//                flex: 1
//            }, {
//                xtype: 'button',
//                text: '< 移除',
//                id: 'contactSelectRemove',
//                action: 'contactSelectRemove',
//                disabled: true
//            } ]
//        }, {
//            xtype: 'gridpanel',
//            id: 'contactSelectForm',
//            store: this.ContactSelectStore,
//            multiSelect: true,
//            selModel: Ext.create('Ext.selection.CheckboxModel'),
//            columns: [ {
//                text: "姓名",
//                flex: 1,
//                dataIndex: 'contactName',
//                renderer: this.rendererValue,
//                sortable: true
//            }, {
//                text: "单位",
//                flex: 1,
//                dataIndex: 'company',
//                renderer: this.rendererValue,
//                sortable: true
//            }, {
//                text: "职务",
//                flex: 1,
//                dataIndex: 'job',
//                renderer: this.rendererValue,
//                sortable: true
//            }, {
//                text: "联系电话",
//                flex: 1,
//                dataIndex: 'phoneNumber',
//                renderer: this.rendererValue,
//                sortable: true
//            } ],
//            bbar: [ {
//                xtype: 'pagingtoolbar',
//                store: this.ContactSelectStore,
//                width: 390,
//                displayInfo: true
//            } ],
//            stripeRows: true,
//            title: '现有联系人',
//            margins: '0 0 0 3',
//            flex: 3.2
        } ];
        this.bbar = [ {
//            xtype: 'tbtext',
//            text: '请通过双击联系人或点击按钮来删除或添加联系人'
//        }, {
            xtype: 'hidden',
            name: 'objectID',
            id: 'objectID'
        }, {
            xtype: 'hidden',
            name: 'objectFlag',
            id: 'objectFlag'
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'select'
        }, {
            text: '返回',
            action: 'closeCustomer'
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});