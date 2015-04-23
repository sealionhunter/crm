Ext.define('CRM.view.index.MessageList', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.messagelist',
    id: 'messagelist',
    height: 236,
    layout: 'fit',
    closable: false,
    resizable: false,
    autoShow: true,
    border: false,
    floating: true,
    padding: '0 0 0 0',
    minWidth: 50,
    bodyStyle: {
        background: '#ffffff'
    },
    initComponent: function() {
        var me = this;
        var messageList = Ext.getCmp('message');
        var xy = messageList.getPosition();
        var width = messageList.getWidth();
        var height = messageList.getHeight();
        var offsetWidth = document.body.offsetWidth;
        this.x = xy[0] + width - 20;
        this.y = xy[1] + height;
        this.width = offsetWidth - this.x - 5;
        this.messageStore = Ext.create('CRM.store.customerManagement.customerProfiles.Business');
        this.messageStore.on('beforeload', function(store, options) {
            var new_params = {
                userID: USER_ID,
                searchFlag: 3,
                limit: 10
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        this.messageStore.loadPage(1);
        this.items = [ {
            xtype: 'grid',
            id: 'messageGrid',
            layout: 'fit',
//            hideHeaders: true,
            border: true,
            store: this.messageStore,
            columns: [ Ext.create('Ext.grid.RowNumberer', {
                text: '序号',
                width: 40,
                align: "right",
                renderer: function(value, metadata, record, rowIndex) {
                    var page = me.messageStore.currentPage, pageSize = me.messageStore.pageSize;
                    return (page - 1) * pageSize + rowIndex + 1;
                }
            }), {
                text: '内容',
                dataIndex: 'customerName',
                width: 400,
                tips: true,
                renderer: function(value, metadata, record, rowIndex) {
                    value = value + '办理了【' + record.get('contractNumber') + '】个【'
                        + record.get('businessTypeName') + '】业务，合同金额：' + record.get('contractMoney');
                    return value;
                }
            } ],
            bbar: Ext.create('CRM.view.PagingToolbar', {
                store: this.messageStore,
            })
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});
