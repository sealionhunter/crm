Ext.define('CRM.view.customerManagement.proposalOrContract.ContractOrderList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.contractOrderList',
    id: 'contractOrderList',
    title: '订单列表',
    multiSelect: false,
    columnLines: true,
    minWidth: 100,
    selModel: {
        // 复选框选择模式Ext.selection.CheckboxModel,disableSelection应该为false
        selType: 'checkboxmodel',
        disableSelection: true
    },
    initComponent: function() {
        var me = this;
        this.store = 'customerManagement.proposalOrContract.ContractOrderStore';
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: '订单ID',
            hidden: true,
            dataIndex: 'id',
            flex: 1
        }, {
            header: '订单编号',
            dataIndex: 'orderID',
            flex: 1
        }, {
            header: '客户名称',
            dataIndex: 'customerName',
            flex: 1
        } ];
        this.dockedItems = [ {
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            store: this.store,
            displayInfo: true
        } ];
        this.callParent(arguments);
    }
});
