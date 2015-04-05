Ext.define('CRM.view.customerManagement.proposalOrContract.ProposalList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.proposallist',
    id: 'proposallist',
    title: '建议书列表',
    minWidth: 100,
    store: 'customerManagement.proposalOrContract.ProposalStore', // 选择数据源
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            defaults: {
                border: false
            },
            items: [ {
                name: 'type',
                id: 'proposaltype',
                xtype: 'textfield',
                hidden: true
            }, {
                xtype: 'textfield',
                id: 'search_text_proposal',
                itemId: 'searchText',
                enforceMaxLength: true,
                maxLength: 1024,
                width: 150,
                maxLengthText: '检索内容长度不能超过1024个字符！'
            }, {
                text: '检索',
                action: 'queryBtn',
                id: 'search_proposal'
            }, {
                text: '高级检索',
                action: 'superQueryBtn',
                id: 'advancedSearchProposal'
            }, '->', {
                text: '建议书导出',
                action: 'proposaldetail',
                disabled: true,
                hidden: true,
                itemId: '161201',
                id: 'proposal_detail'
            }, {
                text: '添加',
                action: 'proposaladd',
                hidden: true,
                itemId: '161202',
                id: 'proposalAdd'
            }, {
                text: '编辑',
                action: 'proposaledit',
                hidden: true,
                itemId: '161203',
                disabled: true,
                id: 'proposalEdit'
            }, {
                text: '删除',
                hidden: true,
                itemId: '161204',
                action: 'deleteproposal',
                id: 'deleteproposal',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'proposalAdvanced',
            layout: 'column',
            defaults: {
                margin: '5 5 5 5',
                renderer: this.rendererValue,
                xtype: 'textfield'
            },
            hidden: true,
            items: [ {
                id: 'proposalNameS',
                labelWidth: 30,
                width: 130,
                fieldLabel: '名称',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '名称长度不能超过50个字符！'
            }, {
                id: 'proposalObjectS',
                labelWidth: 30,
                width: 130,
                fieldLabel: '对象',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '对象长度不能超过50个字符！'
            }, {
                id: 'proposalTypeS',
                labelWidth: 30,
                width: 130,
                fieldLabel: '类型',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '类型长度不能超过50个字符！'
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    fieldLabel: "添加时间",
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    id: 'proposalAddDateMin',
                    labelWidth: 55,
                    margin: '0 5 0 0',
                    format: 'Y-m-d',
                    width: 160,
                    dateRange: {
                        begin: 'proposalAddDateMin',
                        end: 'proposalAddDateMax'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    fieldLabel: "~",
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    id: 'proposalAddDateMax',
                    labelSeparator: '',
                    labelWidth: 15,
                    margin: '0 0 0 5',
                    format: 'Y-m-d',
                    width: 115,
                    dateRange: {
                        begin: 'proposalAddDateMin',
                        end: 'proposalAddDateMax'
                    },
                    vtype: 'dateRange'
                } ]
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    fieldLabel: "修改时间",
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    id: 'proposalEditDateMin',
                    labelWidth: 55,
                    margin: '0 5 0 0',
                    format: 'Y-m-d',
                    width: 160,
                    dateRange: {
                        begin: 'proposalEditDateMin',
                        end: 'proposalEditDateMax'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    fieldLabel: "~",
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    id: 'proposalEditDateMax',
                    labelSeparator: '',
                    labelWidth: 15,
                    margin: '0 0 0 5',
                    format: 'Y-m-d',
                    width: 115,
                    dateRange: {
                        begin: 'proposalEditDateMin',
                        end: 'proposalEditDateMax'
                    },
                    vtype: 'dateRange'
                } ]
            }, {
                xtype: 'button',
                text: '检索',
                width: 60,
                id: 'proposalSuperQueryBtn',
                action: 'queryBtn'
            } ]
        } ];
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "编号",
            flex: 0.5,
            dataIndex: 'proposalOrContractID',
            sortable: true,
            hidden: true,
            minWidth: 50,
            align: 'right'
        }, {
            text: "名称",
            flex: 1,
            dataIndex: 'proposalOrContractName',
            sortable: true,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            text: "对象",
            flex: 1,
            dataIndex: 'proposalOrContractObject',
            sortable: true,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            text: "类型",
            flex: 1,
            dataIndex: 'proposalOrContractType',
            sortable: true,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            text: "添加时间",
            flex: 1,
            dataIndex: 'proposalOrContractAddDate',
            sortable: true,
            minWidth: 100,
            format: 'Y/m/d',
            align: 'center'
        }, {
            text: "修改时间",
            flex: 1,
            dataIndex: 'proposalOrContractEditDate',
            sortable: true,
            minWidth: 100,
            format: 'Y/m/d',
            align: 'center'
        }, {
            text: "描述",
            flex: 3,
            minWidth: 200,
            dataIndex: 'descriptions',
            sortable: true,
            renderer: this.rendererValue
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    },
    rendererValue: function (value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});
