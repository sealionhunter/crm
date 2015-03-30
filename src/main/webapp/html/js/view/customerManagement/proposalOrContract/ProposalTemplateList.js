Ext.define('CRM.view.customerManagement.proposalOrContract.ProposalTemplateList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.proposaltemplatelist',
    id: 'proposaltemplatelist',
    title: '建议书模板基本信息列表',
    store: 'customerManagement.proposalOrContract.ProposalTemplateStore',
    multiSelect: true,
    minWidth: 100,
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
                xtype: 'textfield',
                id: 'proposalTemplateQueryText',
                itemId: 'searchText',
                width: 150,
                enforceMaxLength: true,
                maxLength: 1024,
                regex: /^.{1,1024}$/,
                regexText: '检索内容长度不能超过1024个字符！'
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn',
                id: 'proposalTemplateQueryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                action: 'superQueryBtn',
                id: 'advancedSearchProposalTemplate'
            }, '->', {
                text: '模板导出',
                action: 'proposalTemplateCreateDoc',
                disabled: true,
                hidden: true,
                itemId: '161101',
                id: 'proposalTemplateCreateDoc'
            }, {
                text: '添加',
                action: 'add',
                itemId: '161102',
                hidden: true,
                id: 'proposalTemplateListAdd'
            }, {
                text: '编辑',
                action: 'edit',
                disabled: true,
                itemId: '161103',
                hidden: true,
                id: 'proposalTemplateListEdit'
            }, {
                text: '删除',
                action: 'delete',
                itemId: '161104',
                disabled: true,
                hidden: true,
                id: 'proposalTemplateListDel'
            } ]
        }, {
            xtype: 'form',
            id: 'proposalTemplateAdvanced',
            layout: 'column',
            defaults: {
                margin: '5 5 5 5',
                renderer: this.rendererValue,
                xtype: 'textfield',
                labelWidth: 55,
                width: 160
            },
            hidden: true,
            items: [ {
                name: 'type',
                hidden: true
            }, {
                id: 'proposalTemplateNameS',
                name: 'fileTemplateName',
                fieldLabel: '模板名称',
                regex: /^.{1,50}$/,
                enforceMaxLength: true,
                maxLength: 50,
                regexText: '模板名称长度不能超过50个字符！'
            }, {
                id: 'proposalTemplateDescriptionsS',
                name: 'fileTemplateDescriptions',
                fieldLabel: '模板描述',
                regex: /^.{0,1024}$/,
                enforceMaxLength: true,
                maxLength: 1024,
                regexText: '模板描述长度不能超过1024个字符！'
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    id: 'proposalTemplateAddDateStart',
                    name: 'fileTemplateAddDateStart',
                    fieldLabel: '添加时间',
                    width: 160,
                    labelWidth: 55,
                    margin: '0 5 0 0',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'proposalTemplateAddDateStart',
                        end: 'proposalTemplateAddDateEnd'
                    },
                    vtype: 'dateRange',
                    format: 'Y-m-d'
                }, {
                    xtype: 'datefield',
                    id: 'proposalTemplateAddDateEnd',
                    name: 'fileTemplateAddDateEnd',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    labelWidth: 15,
                    margin: '0 0 0 5',
                    fieldLabel: '~',
                    labelSeparator: "", // 分隔符，默认":"
                    width: 115,
                    dateRange: {
                        begin: 'proposalTemplateAddDateStart',
                        end: 'proposalTemplateAddDateEnd'
                    },
                    vtype: 'dateRange',
                    format: 'Y-m-d'
                } ]
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    id: 'proposalTemplateEditDateStart',
                    name: 'fileTemplateEditDateStart',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    fieldLabel: '修改时间',
                    width: 160,
                    labelWidth: 55,
                    margin: '0 5 0 0',
                    dateRange: {
                        begin: 'proposalTemplateEditDateStart',
                        end: 'proposalTemplateEditDateEnd'
                    },
                    vtype: 'dateRange',
                    format: 'Y-m-d'
                }, {
                    xtype: 'datefield',
                    id: 'proposalTemplateEditDateEnd',
                    name: 'fileTemplateEditDateEnd',
                    margin: '0 0 0 5',
                    labelWidth: 15,
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    fieldLabel: '~',
                    labelSeparator: "", // 分隔符，默认":"
                    width: 115,
                    dateRange: {
                        begin: 'proposalTemplateEditDateStart',
                        end: 'proposalTemplateEditDateEnd'
                    },
                    vtype: 'dateRange',
                    format: 'Y-m-d'
                } ]
            }, {
                xtype: 'button',
                text: '检索',
                width: 60,
                action: 'proposalSuperQueryBtn'
            } ]
        } ];
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "模板编号",
            flex: 0.5,
            dataIndex: 'fileTemplateID',
            sortable: true,
            hidden: true,
            minWidth: 100,
            align: 'right'
        }, {
            text: "模板名称",
            flex: 1,
            minWidth: 100,
            dataIndex: 'fileTemplateName',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: "添加时间",
            flex: 1,
            minWidth: 100,
            dataIndex: 'fileTemplateAddDate',
            sortable: true,
            format: 'Y/m/d',
            align: 'center'
        }, {
            text: "修改时间",
            flex: 1,
            minWidth: 100,
            dataIndex: 'fileTemplateEditDate',
            sortable: true,
            format: 'Y/m/d',
            align: 'center'
        }, {
            text: "模板描述",
            flex: 2,
            minWidth: 100,
            dataIndex: 'descriptions',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
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