Ext.define('CRM.view.customerManagement.order.OrderTrack', {
    extend: 'Ext.window.Window',
    alias: 'widget.orderTrack',
    title: '订单追踪',
    layout: 'fit',
    height: 400,
    width: 600,
    resizable: false,// 设置window不可更改大小
    autoShow: true,
    modal: true,
    autoShow: true,// 自动显示
    id: 'orderTrackWindow',
    initComponent: function() {
        this.items = [ {
            xtype: 'grid',
            store: 'customerManagement.order.OrderTrack',
            columns: [ Ext.create('Ext.grid.RowNumberer', {
                text: '序号',
                width: 40
            }), {
                text: '修改人',
                dataIndex: 'editPeople',
                renderer: Ext.getCmp('orderList').rendererValue,
                minWidth: 100,
                flex: 1
            }, {
                text: '订单编号',
                dataIndex: 'orderID',
                minWidth: 100,
                flex: 2
            }, {
                text: '订单状态',
                dataIndex: 'afterState',
                minWidth: 100,
                flex: 1
            }, {
                text: '修改时间',
                dataIndex: 'recordTime',
                minWidth: 100,
                align: 'center',
                flex: 2
            } ]
        } ];
        this.callParent(arguments);
    }
});