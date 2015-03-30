Ext.define('CRM.view.salesManagement.salesEvent.SalesEventTrack', {
    extend: 'Ext.window.Window',
    alias: 'widget.transactionTrack',
    id: 'transactionTrackWindow',
    width: 600,
    height: 350,
    layout: 'fit',
    modal: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'grid',
            store: 'salesManagement.salesEvent.SalesEventTrack',
            itemId: 'gridTrack',
            sortableColumns: false,
            disableSelection: true,
            columns: [ {
                header: '客户',
                dataIndex: 'customerName',
                renderer: this.rendererValue
            }, {
                header: '事件状态',
                dataIndex: 'status',
                renderer: this.rendererValue
            }, {
                header: '操作人',
                dataIndex: 'editPeople',
                renderer: this.rendererValue
            }, {
                header: '开始时间',
                dataIndex: 'recordTime',
                flex: 1
            }, {
                header: '',
                dataIndex: 'isAbolished',
                width: 55,
                renderer: this.styleChange
            } ]
        } ];
        this.buttons = [ {
            text: '确定',
            scope: this,
            handler: this.close
        } ];
        this.callParent(arguments);
    },
    styleChange: function(val) {
        if (val == false) {
            return '<span style="color:green">' + '' + '</span>';
        } else {
            return '<span style="color:red">终止</span>';
        }
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});