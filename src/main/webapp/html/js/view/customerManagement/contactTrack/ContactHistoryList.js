Ext.ClassManager.setAlias('Ext.selection.CheckboxModel', 'selection.checkboxmodel');
Ext.define('CRM.view.customerManagement.contactTrack.ContactHistoryList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.contacthistorylist',
    id: 'contacthistorylist',
    store: 'customerManagement.contactTrack.ContactHistoryStore',
    simpleSelect: false,
    minWidth: 100,
    multiSelect: true,
//    title: '客户联系历史列表',
    selModel: {
        selType: 'checkboxmodel'
    },
    viewConfig: {
        forceFit: true,
        stripeRows: true
    },
    initComponent: function() {
        var me = this;
        this.cutomerContactStore = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
        this.contactTypeMemoryStore = Ext.create('CRM.store.code.Code');
        this.contactWayMemoryStore = Ext.create('CRM.store.code.Code');
        this.ifContactMemoryStore = Ext.create('CRM.store.code.Code');
        this.contactTypeStore = Ext.create('CRM.store.code.Code');
        this.contactWayStore = Ext.create('CRM.store.code.Code');
        this.ifContactStore = Ext.create('CRM.store.code.Code');
        this.dockedItems = [ {
            xtype: 'toolbar',
            dock: 'top',
            items: [ {
                id: 'contactHistorySearchText',
                itemId: 'searchText',
                xtype: 'textfield',
                name: 'searchText',
                selectOnFocus: true,
                width: 150,
                maxLength: 1024,
                enforceMaxLength: true,
                vtype: 'search',
                maxLengthText: "检索内容长度不能超过1024个字符！"
            }, {
                xtype: 'button',
                text: '检索',
                id: 'contactHistorySearchBtn',
                action: 'queryBtn'
            }, {
                xtype: 'hiddenfield',
                itemId: 'customerID',
                width: 1,
                name: 'customerID'
            }, {
                xtype: 'button',
                text: '高级检索',
                id: 'openOrCloseContactHistorySuperSearch'
            }, {
                xtype: "tbfill"
            }, {
                xtype: 'button',
                text: '重新反馈',
                hidden: true,
                itemId: '12201',
                id: 'contactHistoryResponseEdit',
                action: 'contactHistoryResponseEdit',
                disabled: true
            }, {
                xtype: 'button',
                text: '添加',
                hidden: true,
                itemId: '12202',
                id: 'addContactHistory',
                action: 'addContactHistory'
            }, {
                xtype: 'button',
                text: '编辑',
                hidden: true,
                itemId: '12203',
                id: 'editContactHistory',
                action: 'editContactHistory',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                hidden: true,
                itemId: '12204',
                id: 'deleteContactHistory',
                action: 'deleteContactHistory',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            hidden: true,
            layout: 'column',
            id: 'contactHistorySuperSearchForm',
            defaults: {
                margin: '5 5 5 5'
            },
            items: [ {
                xtype: 'textfield',
                id: 'contactHistoryCustomerSearch',
                name: 'customerName',
                labelWidth: 30,
                width: 145,
                fieldLabel: '客户',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '客户长度不能超过50个字符！'
//            }, {
//                xtype: 'textfield',
//                id: 'contactHistoryWeContactSearch',
//                name: 'weContact',
//                labelWidth: 65,
//                width: 180,
//                fieldLabel: '我方联系人',
//                maxLength: 20,
//                enforceMaxLength: true,
//                maxLengthText: '我方联系人长度不能超过20个字符！'
            }, {
                xtype: 'textfield',
                id: 'contactHistoryOppositeContactSearch',
                name: 'oppositeContact',
                labelWidth: 65,
                width: 180,
                fieldLabel: '对方联系人',
                maxLength: 20,
                enforceMaxLength: true,
                maxLengthText: '对方联系人长度不能超过20个字符！'
            }, {
                xtype: 'combo',
                id: 'contactHistoryTypeSearch',
                store: this.contactTypeStore,
                queryMode: 'local',
                displayField: 'value', // 默认显示的值
                valueField: 'code',
                value: '00',
                forceSelection: true,
                editable: false,
                fieldLabel: "类型",
                labelWidth: 30,
                width: 145,
                name: 'contactType'
            }, {
                xtype: 'combo',
                id: 'contactHistoryWaySearch',
                store: this.contactWayStore,
                queryMode: 'local',
                displayField: 'value', // 默认显示的值
                valueField: 'code',
                value: '00',
                forceSelection: true,
                editable: false,
                labelWidth: 55,
                width: 170,
                fieldLabel: "联系方式",
                name: 'contactWay'
            }, {
                xtype: 'combo',
                id: 'contactHistoryIfcontactSearch',
                store: this.ifContactStore,
                queryMode: 'local',
                displayField: 'value', // 默认显示的值
                valueField: 'code',
                value: '00',
                forceSelection: true,
                editable: false,
                fieldLabel: "是否联系",
                labelWidth: 55,
                width: 170,
                name: 'ifContact'
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 333,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    id: 'contactHistoryDateStartSearch',
                    name: 'searchDateStart',
                    labelWidth: 78,
                    fieldLabel: '实际开始日期',
                    format: 'Y-m-d',
                    margin: '0 5 0 0',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    width: 193,
                    dateRange: {
                        begin: 'contactHistoryDateStartSearch',
                        end: 'contactHistoryDateEndSearch'
                    },
                    vtype: 'dateRange',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01'
                }, {
                    xtype: 'datefield',
                    id: 'contactHistoryDateEndSearch',
                    name: 'searchDateEnd',
                    labelWidth: 15,
                    fieldLabel: '～',
                    labelSeparator: ' ',
                    format: 'Y-m-d',
                    margin: '0 0 0 5',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    width: 130,
                    dateRange: {
                        begin: 'contactHistoryDateStartSearch',
                        end: 'contactHistoryDateEndSearch'
                    },
                    vtype: 'dateRange',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01'
                } ]
            }, {
                xtype: 'button',
                id: 'contactHistorySuperSearchBtn',
                text: '检索',
                width: 60,
                action: 'queryBtn'
            } ]
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            align: "right",
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: "主题",
            dataIndex: 'contactTheme',
            minWidth: 80,
            sortable: true,
            align: 'left',
            flex: 1,
            renderer: this.rendererValue
//        },  {
//            header: "客户",
//            dataIndex: 'customerName',
//            minWidth: 80,
//            sortable: true,
//            align: 'left',
//            flex: 1,
//            renderer: this.rendererValue
//        }, {
//            header: "我方联系人",
//            dataIndex: 'weContactName',
//            minWidth: 80,
//            sortable: true,
//            align: 'left',
//            flex: 1,
//            renderer: this.rendererValue
        }, {
            header: "对方联系人",
            dataIndex: 'oppositeContactName',
            minWidth: 80,
            sortable: true,
            align: 'left',
            flex: 1,
            renderer: this.rendererValue
        }, {
            header: "联系方式",
            dataIndex: 'contactWayName',
            minWidth: 80,
            sortable: true,
            align: 'left',
            flex: 1
        }, {
            header: "实际开始时间",
            dataIndex: 'realityDateBegin',
            minWidth: 150,
            sortable: true,
            align: 'center',
            flex: 1.5
        }, {
            header: "实际结束时间",
            dataIndex: 'realityDateEnd',
            minWidth: 150,
            sortable: true,
            align: 'center',
            flex: 1.5
        }, {
            header: "类型",
            dataIndex: 'contactTypeName',
            minWidth: 80,
            sortable: true,
            align: 'left',
            flex: 1
        }, {
            header: "是否联系",
            dataIndex: 'ifContactName',
            minWidth: 80,
            sortable: true,
            align: 'left',
            flex: 1
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});