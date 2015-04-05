Ext.define('CRM.view.salesManagement.salesEvent.SalesEventList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.salesEventList',
    id: 'salesEventList',
    title: '销售事件列表',
    minWidth: 100,
    store: 'salesManagement.salesEvent.SalesEventStore',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.salesEventStatusCombo = Ext.create('CRM.store.salesManagement.salesEventFlowCode.SalesEventFlowCode');
        this.searchSalesEventStatus = Ext.create('CRM.store.salesManagement.salesEventFlowCode.SalesEventFlowCode');
        this.dockedItems = [ {
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            id: 'pagingtool',
            store: 'salesManagement.salesEvent.SalesEventStore',
            displayInfo: true,
            displayMsg: '第{0}-{1}条事件，共{2}条',
            beforePageText: '当前',
            afterPageText: '共{0}页'
        }, {
            xtype: 'toolbar',
            dock: 'top',
            id: 'salesEventQueryTbar',
            items: [ {
                xtype: 'textfield',
                itemId: 'searchText',
                enforceMaxLength: true,
                width: 150,
                maxLength: 1024,
                maxLengthText: '最大长度不能超过1024个字符！'
            }, {
                xtype: 'button',
                text: '检索',
                itemId: 'queryBtn',
                action: 'queryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                itemId: 'superBtn',
                action: 'openOrCloseAdvancedSearchBtn'
            }, '->', {
                xtype: 'button',
                text: '事件履历',
                disabled: true,
                id: 'transactionTrackBtn',
                action: 'transactionTrackBtn'
            }, {
                xtype: 'button',
                itemId: '11104',
                text: '添加',
                action: 'addSalesOpportunities'
            }, {
                xtype: 'button',
                id: 'salesOpportunitiesEditBtn',
                text: '编辑',
                itemId: '11105',
                disabled: true,
                action: 'editSalesOpportunities'
            }, {
                xtype: 'button',
                id: 'salesEventDelBtn',
                itemId: '11106',
                text: '删除',
                disabled: true,
                action: 'deleteSalesEvent'
            } ]
        }, {
            xtype: 'form',
            id: 'salesEventPanel',
            layout: 'column',
            hidden: true,
            defaults: {
                xtype: 'textfield',
                width: 150,
                margin: '5 5 5 5'
            },
            items: [ {
                fieldLabel: '事件名称',
                name: 'eventName',
                labelWidth: 55,
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '事件名称名称最大长度不能超过50个字符！',
                selectOnFocus: true
            }, {
                fieldLabel: '客户',
                name: 'customerName',
                labelWidth: 30,
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '客户名称最大长度不能超过50个字符！',
                selectOnFocus: true
            }, {
                fieldLabel: '提交人员',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '提交人员最大长度不能超过50个字符！',
                name: 'submitPersonName',
                labelWidth: 55,
                width: 170,
                selectOnFocus: true
            }, {
                xtype: 'combobox',
                fieldLabel: '现处状态',
                labelWidth: 55,
                id: 'searchSalesEventStatusStatus',
                name: 'status',
                itemId: 'status',
                store: this.searchSalesEventStatus,
                queryMode: 'local',
                editable: false,
                value: '0',
                displayField: 'value',
                valueField: 'code'
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 330,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    id: 'salesEventStartTimeSearch',
                    fieldLabel: '事件日期',
                    name: 'dateFrom',
                    labelWidth: 55,
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD',
                    format: 'Y-m-d',
                    width: 180,
                    dateRange: {
                        begin: 'salesEventStartTimeSearch',
                        end: 'salesEventEndTimeSearch'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    id: 'salesEventEndTimeSearch',
                    fieldLabel: '~',
                    margin: '0 0 0 5',
                    name: 'dateTo',
                    labelSeparator: '',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD',
                    labelWidth: 15,
                    width: 145,
                    format: 'Y-m-d',
                    dateRange: {
                        begin: 'salesEventStartTimeSearch',
                        end: 'salesEventEndTimeSearch'
                    },
                    vtype: 'dateRange'
                } ]
            }, {
                xtype: 'button',
                name: 'superquery',
                text: '检索',
                width: 60
            } ]
        } ];
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            dataIndex: 'isAbolished',
            hidden: true
        }, {
            // 事件ID
            dataIndex: 'eventID',
            hidden: true
        }, {
            header: '事件名称',
            minWidth: 80,
            dataIndex: 'eventName',
            flex: 2,
            renderer: this.rendererValue
        }, {
            header: '客户',
            minWidth: 80,
            dataIndex: 'customerName',
            flex: 1,
            renderer: this.rendererValue
        }, {
            header: '客户联系人',
            dataIndex: 'contactName',
            minWidth: 80,
            flex: 1,
            renderer: this.rendererValue
        }, {
            header: '事件日期',
            dataIndex: 'submitDate',
            minWidth: 80,
            flex: 2
        }, {
            header: '提交人员',
            minWidth: 80,
            dataIndex: 'submitPersonName',
            flex: 1,
            renderer: this.rendererValue
        }, {
            // 现处状态对应值
            dataIndex: 'status',
            hidden: true
        }, {
            header: '现处阶段',
            dataIndex: 'statusName',
            minWidth: 80,
            renderer: this.styleChange,
            flex: 1
        }, {
            header: '处理',
            xtype: 'actioncolumn',
            minWidth: 80,
            align: 'center',
            items: [ {
                icon: 'html/img/handle.png',
                tooltip: '处理',
                handler: function(grid, rowIndex, colIndex, item, e) {
                    var record = grid.getStore().getAt(rowIndex);
                    this.fireEvent('itemclick', grid, record);
                }
            } ]
        } ];
        this.callParent(arguments);
    },
    styleChange: function(val) {
        // 去除字符串首尾空格操作
        val = val.replace(/^\s*/, '');
        val = val.replace(/\s*$/, '');
        if (val == "中标") {
            return '<span style="color:red">' + val + '</span>';
        } else {
            return '<span style="color:green">' + val + '</span>';
        }
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});