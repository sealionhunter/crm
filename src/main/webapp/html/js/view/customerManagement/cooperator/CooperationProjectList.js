Ext.define('CRM.view.customerManagement.cooperator.CooperationProjectList', {
    extend: 'Ext.window.Window',
    alias: 'widget.cooperationprojectlist',
    id: 'cooperationprojectwindow',
    autoShow: true,
    resizable: false,
    plain: true,
    layout: 'fit',
    constrainHeader: true,
    width: 1000,
    height: 475,
    modal: true,
    initComponent: function() {
        var me = this;
        this.typeStore = Ext.create('CRM.store.code.Code');
        this.typeStore.load({
            params: {
                code: '00010006'
            }
        });
        this.typeSearchStore = Ext.create('CRM.store.code.Code');
        this.items = [ {
            xtype: 'gridpanel',
            store: 'customerManagement.cooperator.ProjectItem',
            id: 'cooperationprojectlist',
            selModel: Ext.create('Ext.selection.CheckboxModel'),
            viewConfig: {
                forceFit: true
            },
            dockedItems: [ {
                dock: 'top',
                xtype: 'toolbar',
                activeItem: 0,
                defaults: {
                    border: false
                },
                items: [ {
                    xtype: 'textfield',
                    itemId: 'searchText',
                    id: 'projectSearchText',
                    width: 150,
                    name: 'searchText',
                    enforceMaxLength: true,
                    maxLength: 1024,
                    maxLengthText: "检索内容长度不能超过1024个字符！",
                    vtype: 'search',
                    selectOnFocus: true
                }, {
                    xtype: 'button',
                    text: '检索',
                    action: 'queryBtn'
                }, {
                    text: '高级检索',
                    action: 'showAdvancedSearchForm',
                    id: 'showProjectAdvancedSearchForm'
                }, {
                    xtype: 'tbfill'
                }, {
                    text: '添加',
                    action: 'createProject'
                }, {
                    text: '编辑',
                    disabled: true,
                    action: 'editProject',
                    id: 'editProject'
                }, {
                    text: '删除',
                    disabled: true,
                    action: 'delProject',
                    id: 'delProject'
                } ]
            }, {
                xtype: 'form',
                layout: 'column',
                id: 'projectAdvancedSearchForm',
                defaults: {
                    width: 150,
                    margin: '5 5 5 5'
                },
                hidden: true,
                items: [ {
                    xtype: 'numberfield',
                    id: 'cooperatorIDSearch',
                    name: 'cooperatorIDSearch',
                    hidden: true
                }, {
                    xtype: 'textfield',
                    name: 'projectNameSearch',
                    labelWidth: 60,
                    enforceMaxLength: true,
                    maxLength: 30,
                    maxLengthText: "项目名称长度不能超过30个字符！",
                    fieldLabel: '项目名称',
                    vtype: 'search'
                }, {
                    xtype: 'combobox',
                    store: this.typeSearchStore,
                    displayField: 'value',
                    valueField: 'code',
                    queryMode: 'local',
                    editable: false,
                    name: 'projectTypeSearch',
                    itemId: 'typeSearch',
                    labelWidth: 33,
                    value: '00',
                    fieldLabel: '状态',
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                }, {
                    xtype: 'textfield',
                    name: 'principalCooperatorSearch',
                    labelWidth: 70,
                    width: 180,
                    enforceMaxLength: true,
                    maxLength: 20,
                    maxLengthText: "项目名称长度不能超过20个字符！",
                    fieldLabel: '对方负责人',
                    vtype: 'search'
                }, {
                    xtype: 'textfield',
                    name: 'principalWeSearch',
                    labelWidth: 70,
                    width: 180,
                    enforceMaxLength: true,
                    maxLength: 20,
                    maxLengthText: "项目名称长度不能超过20个字符！",
                    fieldLabel: '我方负责人',
                    vtype: 'search'
                }, {
                    xtype: 'panel',
                    layout: 'column',
                    width: 322,
                    border: false,
                    items: [ {
                        xtype: 'numberfield',
                        fieldLabel: '项目规模查询',
                        hideTrigger: true,
                        allowDecimals: false,
                        labelWidth: 83,
                        margin: '0 5 0 0',
                        minValue: 0,
                        maxValue: 10000,
                        maxLengthText: '项目规模最大值为10000！',
                        maxLength: 6,
                        enforceMaxLength: true,
                        name: 'projectScaleMin',
                        id: 'projectScaleMin',
                        width: 190,
                        numberRange: {
                            min: 'projectScaleMin',
                            max: 'projectScaleMax'
                        },
                        vtype: 'numberRange'
                    }, {
                        xtype: 'numberfield',
                        fieldLabel: '～',
                        labelSeparator: ' ',
                        hideTrigger: true,
                        allowDecimals: false,
                        minValue: 0,
                        margin: '0 0 0 5',
                        maxLengthText: '项目规模最大值为10000！',
                        maxLength: 6,
                        enforceMaxLength: true,
                        maxValue: 10000,
                        name: 'projectScaleMax',
                        id: 'projectScaleMax',
                        width: 122,
                        labelWidth: 15,
                        numberRange: {
                            min: 'projectScaleMin',
                            max: 'projectScaleMax'
                        },
                        vtype: 'numberRange'
                    } ]
                }, {
                    xtype: 'panel',
                    layout: 'column',
                    width: 300,
                    border: false,
                    items: [ {
                        xtype: 'datefield',
                        fieldLabel: "开始时间",
                        name: 'realBeginTimeMin',
                        labelWidth: 58,
                        invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                        format: 'Y-m-d',
                        minValue: '01/01/1753',
                        margin: '0 5 0 0',
                        minText: '日期不能小于1753-01-01',
                        id: 'realBeginTimeMin',
                        width: 170,
                        dateRange: {
                            begin: 'realBeginTimeMin',
                            end: 'realBeginTimeMax'
                        },
                        vtype: 'dateRange'
                    }, {
                        xtype: 'datefield',
                        fieldLabel: '～',
                        labelSeparator: '',
                        format: 'Y-m-d',
                        minValue: '01/01/1753',
                        minText: '日期不能小于1753-01-01',
                        id: 'realBeginTimeMax',
                        margin: '0 0 0 5',
                        invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                        name: 'realBeginTimeMax',
                        labelWidth: 15,
                        width: 120,
                        dateRange: {
                            begin: 'realBeginTimeMin',
                            end: 'realBeginTimeMax'
                        },
                        vtype: 'dateRange'
                    } ]
                }, {
                    xtype: 'button',
                    text: '检索',
                    width: 60,
                    action: 'advancedSearch'
                } ]
            } ],
            columns: [ Ext.create('Ext.grid.RowNumberer', {
                boeder: false,
                text: '序号',
                width: 40,
                minWidth: 160,
                renderer: function(value, metadata, record, rowIndex) {
                    var page = me.store.currentPage, pageSize = me.store.pageSize;
                    return (page - 1) * pageSize + rowIndex + 1;
                }
            }), {
                header: "项目名称",
                dataIndex: 'projectName',
                renderer: this.rendererValue,
                sortable: true,
                flex: 1
            }, {
                header: "开始时间",
                dataIndex: 'realBeginTime',
                renderer: this.rendererValue,
                sortable: true,
                align: 'center',
                flex: 1
            }, {
                header: "项目总人月数",
                dataIndex: 'projectScale',
                renderer: this.rendererValue,
                sortable: true,
                align: 'right',
                flex: 1
            }, {
                header: "对方人月数",
                dataIndex: 'cooperatorScale',
                renderer: this.rendererValue,
                sortable: true,
                align: 'right',
                flex: 1
            }, {
                header: "对方人数",
                dataIndex: 'cooperatorPeopleNumber',
                renderer: this.rendererValue,
                sortable: true,
                align: 'right',
                flex: 1
            }, {
                header: "我方负责人",
                dataIndex: 'principalWe',
                renderer: this.rendererValue,
                sortable: true,
                flex: 1
            }, {
                header: "对方负责人",
                dataIndex: 'principalCooperator',
                renderer: this.rendererValue,
                sortable: true,
                flex: 1
            }, {
                header: "对方负责人电话",
                dataIndex: 'principalCooperatorPhone',
                renderer: this.rendererValue,
                sortable: true,
                align: 'right',
                flex: 1
            }, {
                header: "状态",
                dataIndex: 'projectTypeDisplay',
                renderer: this.rendererValue,
                sortable: true,
                flex: 1
            } ]
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});
