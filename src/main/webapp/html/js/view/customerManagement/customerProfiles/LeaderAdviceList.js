Ext.define('CRM.view.customerManagement.customerProfiles.LeaderAdviceList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.leaderadvicelist',
    id : 'leaderadvicelist',
    minWidth: 100,
    store: 'customerManagement.customerProfiles.LeaderAdvice',
    selModel: {
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        me = this;
        this.userStore = Ext.create('CRM.store.customerManagement.customerProfiles.User');

        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "领导名",
            dataIndex: 'userName',
            sortable: true,
            minWidth: 160,
            flex: 1,
            renderer: this.rendererValue
        }
//        , {
//            text: "客户",
//            dataIndex: 'customerName',
//            sortable: true,
//            minWidth: 160,
//            flex: 2,
//            renderer: this.rendererValue
//        }
        , {
            text: "建议内容",
            dataIndex: 'adviceContent',
            minWidth: 160,
            flex: 2,
            sortable: true
        }, {
            fieldLabel: '创建时间',
            dataIndex: 'createTime',
            hidden: true
        } , {
            fieldLabel: '更新时间',
            dataIndex: 'updateTime',
            hidden: true
        }];
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
            }
//            , {
//                text: '高级检索',
//                xtype: 'button',
//                action: 'superQueryBtn'
//            }
            , {
                // 添加填充符
                xtype: "tbfill"
            }, {
                xtype: 'button',
                text: '添加',
                action : 'showAddWin',
                hidden: true,
                itemId: '11401',
                disabled: false
            }, {
                xtype: 'button',
                text: '编辑',
                action : 'showEditWin',
                hidden: true,
                itemId: '11402',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                hidden: true,
                itemId: '11403',
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