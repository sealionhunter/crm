Ext.define('CRM.view.customerManagement.order.BindSalesEventList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.bindSalesEventList',
    title: '事件列表',
    id: 'bindSalesEventList',
    columnLines: true,
    pickerAlign: 'bl',
    minWidth: 100,
    selModel: {
        selType: 'checkboxmodel'
    },
    initComponent: function() {
        var me = this;
        this.store = Ext.create('CRM.store.salesManagement.salesEvent.SalesEventStore');
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: '事件名称',
            dataIndex: 'eventName',
            flex: 1.3
        }, {
            header: '客户联系人',
            dataIndex: 'contactName',
            flex: 1
        }, {
            header: '事件日期',
            dataIndex: 'submitDate',
            flex: 1.3
        }, {
            header: '提交人员',
            dataIndex: 'submitPersonName',
            flex: 1
        } ];
        this.dockedItems = [ {} ];
        this.callParent(arguments);
    }
});
