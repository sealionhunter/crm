Ext.define('CRM.view.customerManagement.proposalOrContract.ContractSelectOrder', {
    extend: 'Ext.window.Window',
    alias: 'widget.contractSelectOrder',
    id: 'contractSelectOrder',
    layout: 'anchor',
    autoShow: true,
    width: 500,
    height: 400,
    resizable: false,
    modal: true,
    constrainHeader: true,
    title: '选择订单',
    initComponent: function() {
        this.items = [ {
            anchor: '100% 100%',
            id: 'contractOrderList',
            xtype: 'contractOrderList'
        } ];
        this.buttons = [ {
            text: '确定',
            action: 'selectOrder'
        }, {
            text: '取消',
            action: 'close'
        } ];
        this.callParent(arguments);
    }
});