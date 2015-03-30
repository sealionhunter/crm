Ext.define('CRM.view.index.MyWorkingList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.myworkinglist',
    id: 'myWorking',
    title: '我的工作平台',
    store: 'index.WorkStore',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    loadMask: true,
    minWidth: 100,
    initComponent: function() {
        var me = this;
        this.completionStore = Ext.create('CRM.store.code.Code');
        this.priorityStore = Ext.create('CRM.store.code.Code');
        this.jobStyleStore = Ext.create('CRM.store.code.Code');
        this.workTypeStore = Ext.create('CRM.store.code.Code');
        this.completionSearchStore = Ext.create('CRM.store.code.Code');
        this.prioritySearchStore = Ext.create('CRM.store.code.Code');
        this.jobStyleSearchStore = Ext.create('CRM.store.code.Code');
        this.workTypeSearchStore = Ext.create('CRM.store.code.Code');
        this.completionStore.load({
            params: {
                code: '00110002'
            }
        });
        this.priorityStore.load({
            params: {
                code: '00110004'
            }
        });
        this.jobStyleStore.load({
            params: {
                code: '00110001'
            }
        });
        this.workTypeStore.load({
            params: {
                code: '00110003'
            }
        });
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 40,
            align: "right",
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "主题",
            dataIndex: 'theme',
            minWidth: 160,
            sortable: true,
            flex: 2,
            align: 'left',
            renderer: this.rendererValue
        }, {
            text: "客户",
            dataIndex: 'customerName',
            minWidth: 80,
            flex: 1,
            align: 'left',
            sortable: true,
            renderer: this.rendererValue
        }, {
            text: "优先级",
            dataIndex: 'priorityName',
            minWidth: 80,
            sortable: true,
            flex: 1,
            align: 'left',
            renderer: this.rendererValue
        }, {
            text: "开始时间",
            dataIndex: 'startTime',
            minWidth: 120,
            flex: 1.5,
            align: 'center',
            sortable: true
        }, {
            text: "结束期限",
            dataIndex: 'endTime',
            minWidth: 120,
            flex: 1.5,
            align: 'center',
            sortable: true
        }, {
            text: "完成情况",
            dataIndex: 'completionName',
            minWidth: 80,
            flex: 1,
            align: 'left',
            sortable: true,
            renderer: this.rendererValue
        }, {
            text: "工作类型",
            dataIndex: 'workTypeName',
            minWidth: 120,
            flex: 1.5,
            align: 'left',
            sortable: true,
            renderer: this.rendererValue
        }, {
            fieldLabel: '任务ID',
            dataIndex: 'workID',
            hidden: true,
            renderer: this.rendererValue
        }, {
            text: "任务类别",
            dataIndex: 'teamFlag',
            minWidth: 80,
            renderer: function(value, metadata, record, rowIndex) {
                if (value == 0) {
                    return "个人任务";
                } else if (value == -1) {
                    return "分配任务";
                } else {
                    return "被分配任务";
                }
            },
            flex: 1
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            defaults: {
                border: false
            },
            items: [ {
                xtype: 'textfield',
                itemId: 'searchText',
                id: 'workSearchText',
                width: 150,
                selectOnFocus: true,
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "检索条件最大长度不能超过1024个字符！"
            }, {
                xtype: 'button',
                id: 'workQueryBtn',
                action: 'queryBtn',
                text: '检索'
            }, {
                xtype: 'button',
                text: '高级检索',
                id: 'workOpenOrCloseExpertSearch',
                action: 'workOpenOrCloseExpertSearch'
            }, {
                xtype: "tbfill"
            }, {
                xtype: 'button',
                id: 'addTeamWorkBtn',
                text: '分配团队任务',
                action: 'addTeamWork'
            }, {
                xtype: 'button',
                text: '添加',
                action: 'addWork'
            }, {
                xtype: 'button',
                text: '编辑',
                action: 'editWork',
                id: 'workEditBtn',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                action: 'deleteWork',
                id: 'workDeleteBtn',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'workSuperQueryForm',
            layout: 'column',
            hidden: true,
            defaults: {
                xtype: 'combobox',
                allowBlank: false,
                queryMode: 'local',
                labelWidth: 55,
                labelSeparator: ':',
                width: 170,
                forceSelection: true,
                displayField: 'value',
                valueField: 'code',
                margin: '5 5 5 5'
//                    ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            },
            items: [ {
                xtype: 'textfield',
                fieldLabel: '任务主题',
                name: 'theme',
                id: 'workTheme',
                allowBlank: true,
                value: '',
                selectOnFocus: true,
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '主题最大长度不能超过50个字符！'
            }, {
                xtype: 'textfield',
                fieldLabel: '客户名称',
                name: 'customerName',
                id: 'workCustomerName',
                allowBlank: true,
                value: '',
                selectOnFocus: true,
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '客户名称最大长度不能超过50个字符！'
            }, {
                name: 'jobStyle',
                id: 'jobStyleSearchCombobox',
                value: '00',
                fieldLabel: '任务类别',
                editable: false,
                store: this.jobStyleSearchStore
            }, {
                name: 'priority',
                id: 'prioritySearchCombobox',
                value: '00',
                fieldLabel: '优先级',
                labelWidth: 45,
                editable: false,
                store: this.prioritySearchStore
            }, {
                xtype: 'panel',
                width: 310,
                layout: 'column',
                border: false,
                items: [ {
                    xtype: 'datefield',
                    id: 'workDateStartSearch',
                    name: 'searchDateStart',
                    labelWidth: 60,
                    fieldLabel: '结束期限',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    allowBlank: true,
                    width: 180,
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    dateRange: {
                        begin: 'workDateStartSearch',
                        end: 'workDateEndSearch'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    id: 'workDateEndSearch',
                    name: 'searchDateEnd',
                    labelWidth: 10,
                    fieldLabel: '~',
                    labelSeparator: ' ',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    allowBlank: true,
                    width: 130,
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    dateRange: {
                        begin: 'workDateStartSearch',
                        end: 'workDateEndSearch'
                    },
                    vtype: 'dateRange'
                } ]
            }, {
                name: 'workType',
                id: 'workTypeSearchCombobox',
                value: '00',
                editable: false,
                fieldLabel: '工作类型',
                store: this.workTypeSearchStore
            }, {
                name: 'completion',
                id: 'completionSearchCombobox',
                value: '00',
                editable: false,
                fieldLabel: '完成情况',
                store: this.completionSearchStore
            }, {
                xtype: 'button',
                id: 'workSuperQueryBtn',
                text: '检索',
                width: 60,
                action: 'queryBtn'
            } ]
        }, {
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            store: this.store,
            displayInfo: true
        } ];
        this.callParent(arguments);
        var date = Ext.util.Format.date(new Date(), 'Y-m-d');
        this.store.on('beforeload', function(store, options) {
            var new_params = {
                userID: USER_ID,
                searchFlag: 0,
                jsonString: '{}',
                date: date
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        this.callback();
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    },
    callback: function() {
//        if (GROUP_ID == 5) {
//            this.down('button[action=addTeamWork]').hide();
//            this.update();
//        }
    }
});
