Ext.define('CRM.view.customerManagement.contactTrack.ContactTrackList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.contacttrackcontactlist',
//    title: "客户联系列表",
    id: 'contacttrackcontactlist',
    minWidth: 100,
    store: 'customerManagement.contactTrack.ContactStore',
    selModel: {
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        var me = this;
        this.contactWayStore = Ext.create('CRM.store.code.Code');
        this.contactTypeStore = Ext.create('CRM.store.code.Code');
        this.contactWaySearchStore = Ext.create('CRM.store.code.Code');
        this.contactTypeSearchStore = Ext.create('CRM.store.code.Code');
        this.cutomerContactStore = Ext.create('CRM.store.customerManagement.order.OrderCutomerContactStore');
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 40,
            align: "right",
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: '主题',
            dataIndex: 'contactTheme',
            flex: 1,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            header: '客户',
            dataIndex: 'customerName',
            flex: 1,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            header: '我方联系人',
            dataIndex: 'weContactName',
            flex: 1,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            header: '对方联系人',
            dataIndex: 'oppositeContactName',
            flex: 1,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            header: '联系方式',
            dataIndex: 'contactWayName',
            minWidth: 100,
            flex: 1,
            renderer: this.rendererValue
        }, {
            header: '预计联系时间',
            dataIndex: 'planDateBegin',
            minWidth: 150,
            flex: 1,
            align: 'center'
        }, {
            header: '类型',
            dataIndex: 'contactTypeName',
            minWidth: 100,
            flex: 1,
            renderer: this.rendererValue
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'contactTrackSearch',
            items: [ {
                xtype: 'textfield',
                itemId: 'searchText',
                width: 150,
                vtype: 'search',
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "检索内容长度不能超过1024个字节！",
                selectOnFocus: true
            }, {
                xtype: 'button',
                id: 'searchButton',
                action: 'queryBtn',
                text: '检索'
            }, {
                xtype: 'hiddenfield',
                itemId: 'customerID',
                width: 1,
                name: 'customerID'
            }, {
                xtype: 'button',
                text: '高级检索',
                id: 'expertSearchButton',
                action: 'expertSearchButton'
            }, {
                xtype: 'tbfill'
            }, {
                id: 'contactSaveHistory',
                text: "客户反馈",
                hidden: true,
                itemId: '12101',
                disabled: true
            }, {
                id: 'contactAdd',
                text: '添加',
                itemId: '12102',
                hidden: true,
                action: 'contactAdd'
            }, {
                id: 'contactEdit',
                text: '编辑',
                itemId: '12103',
                hidden: true,
                action: 'contactEdit',
                disabled: true
            }, {
                id: 'contactDelete',
                itemId: '12104',
                text: '删除',
                hidden: true,
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'contactTrackPanel',
            layout: 'column',
            hidden: true,
            defaults: {
                margin: '5 5 5 5'
            },
            items: [ {
                fieldLabel: '客户',
                labelWidth: 30,
                xtype: 'textfield',
                name: 'customerName',
                maxLength: 50,
                width: 145,
                enforceMaxLength: true,
                maxLengthText: "客户长度不能超过50个字节！",
                vtype: 'search',
                selectOnFocus: true
            }, {
                name: 'weContact',
                itemId: 'we',
                labelWidth: 65,
                xtype: 'textfield',
                width: 180,
                maxLength: 20,
                enforceMaxLength: true,
                maxLengthText: "我方联系人长度不能超过20个字节！",
                fieldLabel: '我方联系人',
                vtype: 'search',
                selectOnFocus: true
            }, {
                fieldLabel: '对方联系人',
                itemId: 'opposite',
                xtype: 'textfield',
                name: 'oppositeContact',
                maxLength: 20,
                enforceMaxLength: true,
                maxLengthText: "对方联系人长度不能超过20个字节！",
                labelWidth: 65,
                width: 180,
                vtype: 'search',
                selectOnFocus: true
            }, {
                xtype: 'combobox',
                id: 'contactWaySearch',
                name: 'contactWay',
                itemId: 'Way',
                fieldLabel: '联系方式',
                labelWidth: 55,
                displayField: 'value',
                valueField: 'code',
                queryMode: 'local',
                value: '00',
                emptyText: '请选择',
                width: 170,
                store: this.contactWaySearchStore,
                editable: false,
                allowBlank: true,
                forceSelection: true
//                ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'combobox',
                id: 'contactTypeSearch',
                name: 'contactType',
                itemId: 'Type',
                fieldLabel: '类型',
                labelWidth: 30,
                width: 145,
                displayField: 'value',
                valueField: 'code',
                queryMode: 'local',
                store: this.contactTypeSearchStore,
                value: '00',
                emptyText: '请选择',
                editable: false,
                allowBlank: true,
                forceSelection: true
//                ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 310,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    id: 'planDateMin',
                    name: 'planDateMin',
                    fieldLabel: '联系日期',
                    format: 'Y-m-d',
                    width: 170,
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'planDateMin',
                        end: 'planDateMax'
                    },
                    labelWidth: 55,
                    vtype: 'dateRange',
                    margin: '0 5 0 0',
                    allowBlank: true,
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01'
                }, {
                    xtype: 'datefield',
                    id: 'planDateMax',
                    name: 'planDateMax',
                    width: 130,
                    labelWidth: 15,
                    fieldLabel: '～',
                    labelSeparator: ' ',
                    format: 'Y-m-d',
                    margin: '0 0 0 5',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'planDateMin',
                        end: 'planDateMax'
                    },
                    vtype: 'dateRange',
                    allowBlank: true,
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01'
                } ]
            }, {
                xtype: 'button',
                action: 'queryBtn',
                id: 'contactTrackSearchLook',
                text: '检索',
                width: 60
            } ]
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});
