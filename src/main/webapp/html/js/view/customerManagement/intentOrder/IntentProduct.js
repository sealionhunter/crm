Ext.define('CRM.view.customerManagement.intentOrder.IntentProduct', {
    extend: 'Ext.window.Window',
    alias: 'widget.intentProduct',
    id: 'intentProduct',
    layout: 'fit',
    autoShow: 'true',
    height: 700,
    width: 800,
    resizable: false,
    modal: true,
    constrainHeader: true,
    initComponent: function() {
        this.items = [ {
            layout: 'border',
            border: false,
            items: [ {
                region: "center",
                xtype: 'serviceproductlist'
            } ]
        } ];
    }
});