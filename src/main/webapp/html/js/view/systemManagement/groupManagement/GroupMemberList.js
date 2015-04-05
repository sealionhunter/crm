Ext.define('CRM.view.systemManagement.groupManagement.GroupMemberList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.groupMemberList',
    multiSelect: true,
    selModel: {
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        var me = this;
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            defaults: {
                border: false
            }
        } ];
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: '用户名',
            dataIndex: 'userName',
            flex: 1,
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '姓名',
            flex: 1,
            dataIndex: 'realName',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    }
});