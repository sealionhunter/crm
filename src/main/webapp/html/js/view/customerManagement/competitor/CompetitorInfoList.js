Ext.define('CRM.view.customerManagement.competitor.CompetitorInfoList', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.competitorInfoList',
    id: 'competitorInfo',
    plain: true, // remove the header border
    activeTab: 0,
    minWidth: 100,
    border: false,
    style: 'background-color:#dfe8f6;',
    initComponent: function() {
        var me = this;
        this.storeNow = Ext.create('CRM.store.customerManagement.competitor.CompetitorInfoNow');
        this.storeNew = Ext.create('CRM.store.customerManagement.competitor.CompetitorInfoNew');
        this.moneyStore = Ext.create('CRM.store.code.Code');
        this.propertyStore = Ext.create('CRM.store.code.Code');
        this.staffStore = Ext.create('CRM.store.code.Code');
        this.typeStore = Ext.create('CRM.store.code.Code');
        this.querymoneyStore = Ext.create('CRM.store.code.Code');
        this.querypropertyStore = Ext.create('CRM.store.code.Code');
        this.querystaffStore = Ext.create('CRM.store.code.Code');
        this.moneyStore.load({
            params: {
                code: '00070001'
            }
        });
        this.propertyStore.load({
            params: {
                code: '00070003'
            }
        });
        this.typeStore.load({
            params: {
                code: '00070004'
            }
        });
        this.staffStore.load({
            params: {
                code: '00070002'
            }
        });
        this.items = [ {
            title: '现有竞争对手',
            id: 'existedcompetitor',
            layout: 'fit',
            border: false,
            items: [ {
                xtype: 'grid',
                name: 'competitorInfoNow',
                id: 'competitorInfoNow',
                border: false,
                dockedItems: [ {
                    xtype: 'toolbar',
                    id: 'existedToolbar',
                    border: false,
                    items: [ {
                        xtype: 'textfield',
                        width: 150,
                        name: 'competitorSearchText',
                        enforceMaxLength: true,
                        maxLength: 1024,
                        maxLengthText: "检索内容长度不能超过1024个字符！",
                        selectOnFocus: true
                    }, {
                        xtype: 'button',
                        text: '检索',
                        name: 'query'
                    }, {
                        xtype: 'button',
                        text: '高級检索',
                        name: 'superqueryButton'
                    }, {
                        xtype: 'tbfill'
                    }, {
                        xtype: 'button',
                        itemId: '131101',
                        id: 'showCprAnalysisWin',
                        text: "详细分析",
                        disabled: true,
                        hidden: true,
                        action: 'showCprAnalysisWin'
                    }, {
                        text: '添加',
                        itemId: '131102',
                        hidden: true,
                        id: 'existedadd'
                    }, {
                        text: '编辑',
                        itemId: '131103',
                        hidden: true,
                        id: 'existedmodify',
                        disabled: true
                    }, {
                        text: '删除',
                        itemId: '131104',
                        hidden: true,
                        id: 'existeddelete',
                        disabled: true
                    } ]
                }, {
                    xtype: 'form',
                    id: 'existedSuperForm',
                    itemId: 'existedSuperForm',
                    hidden: true,
                    layout: 'column',
                    name: 'superQueryTbar',
                    defaults: {
                        labelWidth: 56,
                        labelSeparator: ':',
                        width: 170,
                        margin: '5 5 5 5'
                    },
                    items: [ {
                        xtype: 'textfield',
                        name: 'competitorName',
                        emptyText: '请输入',
                        flex: 1,
                        maxLength: 30,
                        enforceMaxLength: true,
                        vtype: 'search',
                        labelWidth: 80,
                        maxLengthText: "竞争对手名称长度不能超过30个字符！",
                        fieldLabel: '竞争对手名称'
                    }, {
                        xtype: 'textfield',
                        name: 'competitorField',
                        emptyText: '请输入',
                        flex: 1,
                        vtype: 'search',
                        maxLength: 30,
                        enforceMaxLength: true,
                        maxLengthText: "竞争领域长度不能超过30个字符！",
                        fieldLabel: '竞争领域'
                    }, {
                        xtype: 'textfield',
                        name: 'competitorLocation',
                        vtype: 'search',
                        emptyText: '请输入',
                        flex: 1,
                        maxLength: 20,
                        enforceMaxLength: true,
                        maxLengthText: "地理位置长度不能超过20个字符！",
                        fieldLabel: '地理位置'
                    }, {
                        xtype: 'combobox',
                        name: 'competitorPeople',
                        id: 'comboexiPeople',
                        value: 0,
                        editable: false,
                        multiSelect: false,
                        emptyText: '请选择',
                        fieldLabel: '员工规模',
                        queryMode: 'local',
                        store: this.querystaffStore,
                        forceSelection: true,
                        valueField: 'code',
                        value: '00',
                        flex: 1,
                        displayField: 'value'
                    }, {
                        xtype: 'combobox',
                        name: 'competitorProperty',
                        id: 'comboexiProperty',
                        value: 0,
                        emptyText: '请选择',
                        fieldLabel: '对手性质',
                        editable: false,
                        multiSelect: false,
                        queryMode: 'local',
                        store: this.querypropertyStore,
                        forceSelection: true,
                        valueField: 'code',
                        value: '00',
                        flex: 1,
                        displayField: 'value'

                    }, {
                        xtype: 'combobox',
                        name: 'competitorMoney',
                        id: 'comboexiMoney',
                        store: this.querymoneyStore,
                        value: 0,
                        emptyText: '请选择',
                        fieldLabel: '资金力量',
                        editable: false,
                        multiSelect: false,
                        forceSelection: true,
                        queryMode: 'local',
                        valueField: 'code',
                        value: '00',
                        flex: 1,
                        displayField: 'value'
                    }, {
                        xtype: 'panel',
                        layout: 'column',
                        width: 295,
                        border: false,
                        items: [ {
                            xtype: 'datefield',
                            name: 'competitorFoundDateStart',
                            emptyText: '请选择',
                            fieldLabel: '成立时间',
                            flex: 1,
                            width: 160,
                            labelWidth: 55,
                            margin: '0 5 0 0',
                            maxLength: 10,
                            format: 'Y-m-d',
                            invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                            id: 'beginDateSearchMine',
                            dateRange: {
                                begin: 'beginDateSearchMine',
                                end: 'beginDateSearchMaxe'
                            },
                            dateRangeText: '开始日期不能大于结束日期，请重新选择！',
                            vtype: 'dateRange',
                            minValue: '01/01/1753',
                            minText: '日期不能小于1753-01-01'
                        }, {
                            xtype: 'datefield',
                            name: 'competitorFoundDateEnd',
                            emptyText: '请选择',
                            fieldLabel: '～',
                            margin: '0 0 0 5',
                            width: 115,
                            labelWidth: 15,
                            labelSeparator: '',
                            flex: 1,
                            id: 'beginDateSearchMaxe',
                            dateRange: {
                                begin: 'beginDateSearchMine',
                                end: 'beginDateSearchMaxe'
                            },
                            dateRangeText: '开始日期不能大于结束日期，请重新选择！',
                            vtype: 'dateRange',
                            invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                            format: 'Y-m-d',
                            minValue: '01/01/1753',
                            minText: '日期不能小于1753-01-01'
                        } ]
                    }, {
                        xtype: 'button',
                        name: 'superquery',
                        text: '检索',
                        width: 60,
                        algin: 'top'
                    } ]
                } ],
                frame: true,
                viewConfig: {
                    forceFit: true,
                    stripeRows: false
                // loadMask : false
                // 显示线
                },
                store: this.storeNow,
                multiSelect: true,
                border: false,
                selType: 'checkboxmodel',
                columns: [ Ext.create('Ext.grid.RowNumberer', {
                    text: '序号',
                    width: 50,
                    align: "right",
                    renderer: function(value, metadata, record, rowIndex) {
                        var page = me.store.currentPage, pageSize = me.store.pageSize;
                        return (page - 1) * pageSize + rowIndex + 1;
                    }
                }), {
                    header: '竞争对手名称',
                    dataIndex: 'competitorName',
                    flex: 2,
                    minWidth: 80,
                    htmlEncode: true,
                    sortable: true,
                    renderer: this.rendererValue
                }, {
                    header: '竞争领域',
                    dataIndex: 'competitorField',
                    flex: 2,
                    minWidth: 80,
                    sortable: true,
                    renderer: this.rendererValue
                }, {
                    header: '对手性质',
                    dataIndex: 'competitorPropertyB',
                    flex: 2,
                    minWidth: 80,
                    sortable: true
                }, {
                    header: '地理位置',
                    dataIndex: 'competitorLocation',
                    flex: 2,
                    minWidth: 80,
                    sortable: true,
                    renderer: this.rendererValue
                }, {
                    header: '成立时间',
                    dataIndex: 'competitorFoundDate',
                    flex: 2,
                    minWidth: 80,
                    align: "center",
                    sortable: true
                }, {
                    header: '资金力量',
                    dataIndex: 'competitorMoneyB',
                    flex: 2,
                    minWidth: 80,
                    sortable: true
                }, {
                    header: '员工规模',
                    dataIndex: 'competitorPeopleB',
                    flex: 2,
                    minWidth: 80,
                    sortable: true
                }, {
                    header: '备注',
                    dataIndex: 'competitorDescription',
                    flex: 2,
                    minWidth: 80,
                    sortable: true,
                    renderer: this.rendererValue
                } ],
                bbar: {
                    dock: 'bottom',
                    xtype: 'pagingtoolbar',
                    store: this.storeNow,
                    displayInfo: true
                }
            } ]
        }, {
            title: '潜在竞争对手',
            id: 'potentialcompetitor',
            layout: 'fit',
            border: false,
            items: [ {
                xtype: 'grid',
                id: 'competitorInfoNew',
                name: 'competitorInfoNew',
                border: false,
                dockedItems: [ {
                    xtype: 'toolbar',
                    id: 'potentialToolbar',
                    border: false,
                    items: [ {
                        xtype: 'textfield',
                        width: 150,
                        name: 'competitorSearchText',
                        maxLength: 1024,
                        enforceMaxLength: true,
                        vtype: 'search',
                        maxLengthText: "检索的最大长度为1024位",
                        selectOnFocus: true
                    }, {
                        xtype: 'button',
                        text: '检索',
                        name: 'query'
                    }, {
                        xtype: 'button',
                        text: '高級检索',
                        name: 'superqueryButton'
                    }, {
                        xtype: 'tbfill'
                    }, {
                        xtype: 'button',
                        itemId: '131201',
                        id: 'showHiddenCprAnalysisWin',
                        text: "详细分析",
                        hidden: true,
                        disabled: true,
                        action: 'showCprAnalysisWin'
                    }, {
                        text: '添加',
                        itemId: '131202',
                        hidden: true,
                        id: 'potentialadd'
                    }, {
                        text: '编辑',
                        itemId: '131203',
                        hidden: true,
                        id: 'potentialmodify',
                        disabled: true
                    }, {
                        text: '删除',
                        itemId: '131204',
                        hidden: true,
                        id: 'potentialdelete',
                        disabled: true
                    } ]
                }, {
                    xtype: 'form',
                    margin: '0 0 0 0',
                    hidden: true,
                    id: 'potentialSuperForm',
                    name: 'superQueryTbar',
                    layout: 'column',
                    defaults: {
                        labelWidth: 56,
                        labelSeparator: ':',
                        width: 170,
                        margin: '5 5 5 5'
                    },
                    items: [ {
                        xtype: 'textfield',
                        name: 'competitorName',
                        emptyText: '请输入',
                        flex: 1,
                        vtype: 'search',
                        labelWidth: 80,
                        maxLength: 30,
                        enforceMaxLength: true,
                        maxLengthText: "竞争对手名称的最大长度为30位",
                        fieldLabel: '竞争对手名称'
                    }, {
                        xtype: 'textfield',
                        name: 'competitorField',
                        emptyText: '请输入',
                        vtype: 'search',
                        flex: 1,
                        maxLength: 30,
                        enforceMaxLength: true,
                        maxLengthText: "竞争区域的最大长度为30位",
                        fieldLabel: '竞争领域'
                    }, {
                        xtype: 'textfield',
                        name: 'competitorLocation',
                        emptyText: '请输入',
                        vtype: 'search',
                        flex: 1,
                        maxLength: 20,
                        enforceMaxLength: true,
                        maxLengthText: "地理位置的最大长度为20位",
                        fieldLabel: '地理位置'
                    }, {
                        xtype: 'combobox',
                        name: 'competitorPeople',
                        id: 'combopotPeople',
                        value: 0,
                        emptyText: '请选择',
                        fieldLabel: '员工规模',
                        editable: false,
                        multiSelect: false,
                        queryMode: 'local',
                        store: this.querystaffStore,
                        forceSelection: true,
                        valueField: 'code',
                        value: '00',
                        flex: 1,
                        displayField: 'value'
                    }, {
                        xtype: 'combobox',
                        name: 'competitorProperty',
                        id: 'combopotProperty',
                        value: 0,
                        emptyText: '请选择',
                        fieldLabel: '对手性质',
                        editable: false,
                        multiSelect: false,
                        queryMode: 'local',
                        store: this.querypropertyStore,
                        forceSelection: true,
                        valueField: 'code',
                        value: '00',
                        flex: 1,
                        displayField: 'value'
                    }, {
                        xtype: 'combobox',
                        name: 'competitorMoney',
                        id: 'combopotmoney',
                        store: this.querymoneyStore,
                        value: 0,
                        emptyText: '请选择',
                        fieldLabel: '资金力量',
                        editable: false,
                        multiSelect: false,
                        forceSelection: true,
                        queryMode: 'local',
                        valueField: 'code',
                        value: '00',
                        flex: 1,
                        displayField: 'value'
                    }, {
                        xtype: 'panel',
                        layout: 'column',
                        width: 295,
                        border: false,
                        items: [ {
                            xtype: 'datefield',
                            name: 'competitorFoundDateStart',
                            emptyText: '请选择',
                            fieldLabel: '成立时间',
                            flex: 1,
                            width: 160,
                            labelWidth: 55,
                            margin: '0 5 0 0',
                            maxLength: 10,
                            format: 'Y-m-d',
                            invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                            id: 'beginDateSearchMinp',
                            dateRange: {
                                begin: 'beginDateSearchMinp',
                                end: 'beginDateSearchMaxp'
                            },
                            vtype: 'dateRange'
                        }, {
                            xtype: 'datefield',
                            name: 'competitorFoundDateEnd',
                            emptyText: '请选择',
                            fieldLabel: '～',
                            margin: '0 0 0 5',
                            labelWidth: 15,
                            width: 115,
                            labelSeparator: '',
                            format: 'Y-m-d',
                            flex: 1,
                            invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                            id: 'beginDateSearchMaxp',
                            dateRange: {
                                begin: 'beginDateSearchMinp',
                                end: 'beginDateSearchMaxp'
                            },
                            vtype: 'dateRange'
                        } ]
                    }, {
                        xtype: 'button',
                        name: 'superquery',
                        text: '检索',
                        width: 60,
                        margin: 5,
                        algin: 'top'
                    } ]
                } ],
                frame: true,
                viewConfig: {
                    forceFit: true,
                    stripeRows: false
                // loadMask :false
                // 显示线
                },
                store: this.storeNew,
                multiSelect: true,
                selType: 'checkboxmodel',
                columns: [ Ext.create('Ext.grid.RowNumberer', {
                    text: '序号',
                    width: 50,
                    align: "right",
                    renderer: function(value, metadata, record, rowIndex) {
                        var page = me.store.currentPage, pageSize = me.store.pageSize;
                        return (page - 1) * pageSize + rowIndex + 1;
                    }
                }), {
                    header: '竞争对手名称',
                    dataIndex: 'competitorName',
                    flex: 2,
                    minWidth: 80,
                    sortable: true,
                    renderer: this.rendererValue
                }, {
                    header: '竞争领域',
                    dataIndex: 'competitorField',
                    flex: 2,
                    minWidth: 80,
                    sortable: true,
                    renderer: this.rendererValue
                }, {
                    header: '对手性质',
                    dataIndex: 'competitorPropertyB',
                    flex: 2,
                    minWidth: 80,
                    sortable: true
                }, {
                    header: '地理位置',
                    dataIndex: 'competitorLocation',
                    flex: 2,
                    minWidth: 80,
                    sortable: true,
                    renderer: this.rendererValue
                }, {
                    header: '成立时间',
                    dataIndex: 'competitorFoundDate',
                    flex: 2,
                    minWidth: 80,
                    align: "center",
                    sortable: true
                }, {
                    header: '资金力量',
                    dataIndex: 'competitorMoneyB',
                    flex: 2,
                    minWidth: 80,
                    sortable: true
                }, {
                    header: '员工规模',
                    dataIndex: 'competitorPeopleB',
                    flex: 2,
                    minWidth: 80,
                    sortable: true
                }, {
                    header: '备注',
                    dataIndex: 'competitorDescription',
                    flex: 2,
                    minWidth: 80,
                    sortable: true,
                    renderer: this.rendererValue
                } ],
                bbar: {
                    dock: 'bottom',
                    xtype: 'pagingtoolbar',
                    store: this.storeNew,
                    displayInfo: true
                }
            } ]
        } ];
        this.callParent(arguments); // 此处会调用 Component 中的 showAt 方法
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});