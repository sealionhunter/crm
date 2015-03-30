Ext.define('CRM.view.customerManagement.cooperator.CooperatorList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.cooperatorlist',
    id: 'cooperatorList',
    store: 'customerManagement.cooperator.Cooperator',
    multiSelect: true,
    title: '合作伙伴基本信息列表',
    minWidth: 100,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    enableKeyNav: true,
    initComponent: function() {
        var me = this;
        this.industryStore = Ext.create('CRM.store.code.Code');
        this.scaleStore = Ext.create('CRM.store.code.Code');
        this.typeStore = Ext.create('CRM.store.code.Code');
        this.industryStore.load({
            params: {
                code: '00010008'
            }
        });
        this.typeStore.load({
            params: {
                code: '00070005'
            }
        });
        this.scaleStore.load({
            params: {
                code: '00010009'
            }
        });
        this.industrySearchStore = Ext.create('CRM.store.code.Code');
        this.scaleSearchStore = Ext.create('CRM.store.code.Code');
        this.dockedItems = [ {
            xtype: 'toolbar',
            dock: 'top',
            id: 'cooperatorQueryTbar',
            items: [ {
                xtype: 'textfield',
                itemId: 'searchText',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "检索内容长度不能超过1024个字符！",
                vtype: 'search',
                width: 150
            }, {
                text: '检索',
                action: 'queryBtn',
                itemId: 'queryBtn'
            }, {
                text: '高级检索',
                action: 'showCooperatorAdvancedSearch',
                itemId: 'showCooperatorAdvancedSearch'
            }, {
                xtype: 'tbfill'
            }, {
                text: '详细分析',
                action: 'showCopAnalysisBtn',
                id: 'analysisCooperator',
                itemId: '14101',
                disabled: true,
                hidden: true
            }, {
                text: '查看联系人',
                action: 'contactSelect',
                id: 'cooperatorContactShow',
                itemId: '14102',
                disabled: true,
                hidden: true
            }, {
                text: '合作履历',
                action: 'showCooperationProject',
                id: 'showCooperationProject',
                itemId: '14103',
                disabled: true,
                hidden: true
            }, {
                text: '添加',
                action: 'createCooperator',
                itemId: '14104',
                hidden: true
            }, {
                text: '编辑',
                action: 'editCooperator',
                id: 'editCooperator',
                itemId: '14105',
                disabled: true,
                hidden: true
            }, {
                text: '删除',
                action: 'delCooperator',
                id: 'delCooperator',
                itemId: '14106',
                disabled: true,
                hidden: true
            } ]
        }, {
            xtype: 'form',
            hidden: true,
            layout: 'column',
            id: 'cooperatorAdvancedSearchForm',
            defaults: {
                width: 160,
                margin: '5 5 5 5'
            },
            items: [ {
                xtype: 'textfield',
                fieldLabel: '公司名称',
                name: 'cooperatorNameSearch',
                enforceMaxLength: true,
                maxLength: 50,
                labelWidth: 56,
                width: 160,
                vtype: 'search',
                maxLengthText: "公司名称长度不能超过50个字符！"
            }, {
                xtype: 'combo',
                fieldLabel: "所属行业",
                name: 'cooperatorIndustrySearch',
                itemId: 'industrySearch',
                queryMode: 'local',
                store: this.industrySearchStore,
                displayField: 'value',
                valueField: 'code',
                value: '00',
                labelWidth: 56,
                width: 160,
                triggerAction: 'all',
                forceSelection: true,
                editable: false,
                listConfig: {
                    emptyText: '未找到匹配值',
                    maxHeight: 240
                },
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'combo',
                fieldLabel: "人员规模",
                name: 'cooperatorScaleSearch',
                itemId: 'scaleSearch',
                queryMode: 'local',
                store: this.scaleSearchStore,
                displayField: 'value',
                valueField: 'code',
                value: '00',
                triggerAction: 'all',
                labelWidth: 56,
                width: 160,
                forceSelection: true,
                editable: false,
                listConfig: {
                    emptyText: '未找到匹配值',
                    maxHeight: 190
                },
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 313,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    fieldLabel: '初次合作日期',
                    name: 'cooperationDateMin',
                    id: 'cooperatorDateSearchMin',
                    format: 'Y-m-d',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    margin: '0 5 0 0',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'cooperatorDateSearchMin',
                        end: 'cooperatorDateSearchMax'
                    },
                    vtype: 'dateRange',
                    width: 183,
                    labelWidth: 78
                }, {
                    xtype: 'datefield',
                    fieldLabel: '～',
                    labelSeparator: ' ',
                    name: 'cooperationDateMax',
                    id: 'cooperatorDateSearchMax',
                    format: 'Y-m-d',
                    minValue: '01/01/1753',
                    margin: '0 0 0 5',
                    minText: '日期不能小于1753-01-01',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'cooperatorDateSearchMin',
                        end: 'cooperatorDateSearchMax'
                    },
                    vtype: 'dateRange',
                    width: 120,
                    labelWidth: 15
                } ]
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 290,
                border: false,
                items: [ {
                    xtype: 'numberfield',
                    fieldLabel: '合作次数',
                    hideTrigger: true,
                    allowDecimals: false,
                    name: 'cooperationTimesMin',
                    minValue: 0,
                    maxValue: 100000000,
                    labelSeparator: ' ',
                    margin: '0 5 0 0',
                    maxLength: 10,
                    enforceMaxLength: true,
                    labelWidth: 55,
                    maxText: '合作次数的最大值为100000000！',
                    width: 160
                }, {
                    xtype: 'numberfield',
                    fieldLabel: '～',
                    hideTrigger: true,
                    allowDecimals: false,
                    margin: '0 0 0 5',
                    labelSeparator: ' ',
                    name: 'cooperationTimesMax',
                    minValue: 0,
                    maxValue: 100000000,
                    maxLength: 10,
                    enforceMaxLength: true,
                    maxText: '合作次数的最大值为100000000！',
                    width: 120,
                    labelWidth: 15
                } ]
            }, {
                xtype: 'button',
                width: 60,
                action: 'advancedSearch',
                text: '检索'
            } ]
        } ];
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 40,
            minWidth: 160,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: "公司名称",
            flex: 2,
            dataIndex: 'cooperatorName',
            minWidth: 130,
            renderer: this.rendererValue,
            sortable: true
        }, {
            header: "所属行业",
            flex: 2,
            dataIndex: 'industryDisplay',
            minWidth: 130,
            renderer: this.rendererValue,
            sortable: true
        }, {
            header: "所在城市",
            flex: 2,
            dataIndex: 'cooperatorCity',
            minWidth: 130,
            renderer: this.rendererValue,
            sortable: true
        }, {
            header: "人员规模",
            flex: 2,
            dataIndex: 'scaleDisplay',
            minWidth: 130,
            renderer: this.rendererValue,
            sortable: true
        }, {
            header: "初次建立合作的时间",
            flex: 2,
            dataIndex: 'cooperationDate',
            minWidth: 130,
            renderer: this.rendererValue,
            sortable: true,
            align: 'center'
        }, {
            header: "合作次数",
            flex: 1,
            dataIndex: 'projectNumber',
            minWidth: 90,
            renderer: this.rendererValue,
            sortable: true
        }, {
            header: "类型",
            flex: 1,
            dataIndex: 'typeDisplay',
            minWidth: 90,
            renderer: this.rendererValue,
            sortable: true
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
