Ext.define('CRM.view.salesManagement.eventFlow.EventFlow', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.eventFlow',
    title: '销售事件流程管理<span style="color:green">(*销售事件流程个数最多为15个)</span>',
    id: 'eventFlow',
    // 禁止排序
    sortableColumns: false,
    margins: '0 5 0 0',
    store: 'salesManagement.salesEventFlowCode.SalesEventFlowCode',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                return rowIndex + 1;
            }
        }), {
            header: '销售事件流程名称',
            dataIndex: 'value',
            flex: 1
        }, {
            header: '流程所属阶段',
            dataIndex: 'categoryName',
            flex: 1
        } ];
        // add a tbar
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'eventFlowTbar',
            items: [ '->', {
                xtype: 'button',
                id: 'eventFlowUpBtn',
                action: 'eventFlowUpBtn',
                text: '上移',
                disabled: true
            }, {
                xtype: 'button',
                id: 'eventFlowDownBtn',
                action: 'eventFlowDownBtn',
                text: '下移',
                disabled: true
            }, {
                xtype: 'button',
                id: 'eventFlowAddBtn',
                action: 'eventFlowAddBtn',
                text: '添加'
            }, {
                xtype: 'button',
                id: 'eventFlowEditBtn',
                action: 'eventFlowEditBtn',
                text: '编辑',
                disabled: true
            }, {
                xtype: 'button',
                id: 'eventFlowDeleteBtn',
                action: 'eventFlowDeleteBtn',
                text: '删除',
                disabled: true
            } ]
        } ];
        this.callParent(arguments);
    }
});
