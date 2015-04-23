Ext.define('CRM.view.index.CusUpdatedStatusListWin', {
    extend: 'Ext.window.Window',
    alias: 'widget.cusupdatedstatuslistwin',
    title: '客户信息更新详情',
    width: 800,
    height: 450,
    layout: 'fit',
    resizable: false,
    modal: true,
    constrainHeader: true,
    buttonAlign: 'center',
    initComponent: function() {
        var me = this;
        this.store = Ext.create('CRM.store.index.CustomerUpdatedStatus');

        this.items = [ {
            xtype: 'grid',
            itemId: 'cusupdatedstatuslist',
            store: me.store,
            columns: [ Ext.create('Ext.grid.RowNumberer', {
                text: '序号',
                width: 45,
                renderer: function(value, metadata, record, rowIndex) {
                    var page = me.store.currentPage, pageSize = me.store.pageSize;
                    return (page - 1) * pageSize + rowIndex + 1;
                }
            }), {
                  text: '客户',
                  dataIndex: 'customerName',
                  flex: 2 
            }, { text: '未更新天数',
                dataIndex: 'days',
                flex: 1,
                renderer: function(value) {
                    if (value <= 30) {
                        return '<span style="color:blue;">' + value + '</span>';
                    } else if (value <= 60) {
                        return '<span style="color:yellow;">' + value + '</span>';
                    } else {
                        return '<span style="color:red;">' + value + '</span>';
                    }
                }
            },{ text: '剩余天数',
                dataIndex: 'remainDays',
                flex: 1 ,
                renderer: function(value, metadata, record, rowIndex) {
                    return 90 - record.get('days');
                }
          }],
          dockedItems: [{
              dock: 'bottom',
              xtype: 'pagingtoolbar',
              store: this.store,
              displayInfo: true
          }]
        }];
        this.buttons = [ {
            text: '关闭',
            action: 'close',
            handler: function(button) {
                button.up('window').close();
            }
        } ];
        this.callParent(arguments);
    }

});