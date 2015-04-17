Ext.define('CRM.view.customerManagement.customerProfiles.BusinessList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.businesslist',
    id: 'businesslist',
    store: 'customerManagement.customerProfiles.Business',
    selModel: {
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        var me = this;
        this.businessTypeStore = Ext.create('CRM.store.code.Code');
        this.businessTypeStore.load({
            params: {
                code: '00010011'
            }
        });
        this.columns = [ {
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }, {
            header: '业务类型',
            dataIndex: 'businessTypeName',
            minWidth: 145,
            flex: 1
        }, {
            header: '合同年限',
            dataIndex: 'contractYear',
            flex: 1
        }, {
            header: '数量',
            dataIndex: 'contractNumber',
            flex: 1
        }, {
            header: '合同金额（元）',
            dataIndex: 'contractMoney',
            flex: 1
        }, {
            header: '办理时间',
            dataIndex: 'startDate',
            flex: 1
        }, {
            header: '备注',
            dataIndex: 'descriptions',
            flex: 1
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            items: [ {
                xtype: 'textfield',
                itemId: 'searchText',
                width: 150,
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "检索内容长度不能超过1024个字符！"
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn'
            }, {
                xtype: "tbfill"
            }, {
                xtype: 'button',
                text: '添加',
                action : 'showAddWin',
                disabled: false
            }, {
                xtype: 'button',
                text: '编辑',
                action : 'showEditWin',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                action : 'showDelWin',
                disabled: true
            } ]
        }];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});