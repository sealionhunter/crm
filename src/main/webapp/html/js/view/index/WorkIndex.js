Ext.define('CRM.view.index.WorkIndex', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.workIndex',
    id: 'workIndex',
    layout: 'border',
//    margins: '5 5 5 2',
    border: false,
    initComponent: function() {
        this.items = [ {
            xtype: 'panel',
            region: 'north',
            height: 200,
            layout: 'border',
            border: '0 0 0 0',
            padding: '0 0 0 0',
            margins: '0 0 5 0',
            border: false,
            items: [ {
                xtype: 'panel',
                region: 'center',
                layout: 'column',
                // border : '0 0 0 0',
                padding: '0 0 0 0',
                margins: '0 0 0 0',
                border: false,
                items: [ {
                    columnWidth: .5,
                    xtype: 'importantTask'
                }, {
                    columnWidth: .5,
                    xtype: 'informTask'
                } ]
            }, {
                id: 'datePicker',
                xtype: 'datepicker',
                region: 'east',
                border: false,
                todayTip: '',
                padding: '0 0 0 0',
                margins: '0 0 0 0',
                width: 210,
                layout: 'anchor',
                listeners: {
                    dblclick: {
                        element: 'el',
                        fn: function() {
                            var datePicker = Ext.getCmp('datePicker');
                            var date = Ext.util.Format.date(datePicker.getValue(), 'Y-m-d');
                            var view = Ext.widget('workupdate');
                            view.down('#userID').setValue(USER_ID);
                            view.down('#assignee').hide();
                            Ext.getCmp('workTeamFlag').setValue('0');
                            Ext.getCmp('workStratTime').setValue(date);
                            if (view.customerStore.getCount() == 0) {
                                view.customerStore.load({
                                    params: {
                                        customerFlag: 2,
                                        userID: USER_ID
                                    },
                                    callback: function(records, operation, success) {
                                        view.customerStore.insert(0, {
                                            customerID: '0',
                                            customerName: '-无-'
                                        });
                                    }
                                });
                            }
                        }
                    }
                }
            } ]
        }, {
            region: 'center',
            margins: '0 0 0 0',
            xtype: 'myworkinglist'
        } ];
        this.callParent(arguments);
    }
});