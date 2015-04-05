Ext.define('CRM.view.customerManagement.proposalOrContract.FileTemplateList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.filetemplatelist',
    id: 'filetemplatelist',
    title: '合同模板基本信息列表',
    store: 'customerManagement.proposalOrContract.FileTemplateStore',
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
                id: 'contractTemplateQueryText',
                itemId: 'searchText',
                width: 150,
                regex: /^.{1,1024}$/,
                enforceMaxLength: true,
                maxLength: 1024,
                regexText: '检索内容长度不能超过1024个字符！'
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn',
                id: 'contractTemplateQueryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                action: 'superQueryBtn',
                id: 'advancedSearchContractTemplate'
            }, '->', {
                text: '模板导出',
                action: 'contractTemplateCreateDoc',
                disabled: true,
                hidden: true,
                itemId: '162101',
                id: 'contractTemplateCreateDoc'
            }, {
                text: '添加',
                action: 'add',
                hidden: true,
                itemId: '162102',
                id: 'contractTemplateListAdd'
            }, {
                text: '编辑',
                action: 'edit',
                hidden: true,
                itemId: '162103',
                disabled: true,
                id: 'contractTemplateListEdit'
            }, {
                text: '删除',
                action: 'delete',
                hidden: true,
                itemId: '162104',
                disabled: true,
                id: 'contractTemplateListDel'
            } ]
        }, {
            xtype: 'form',
            id: 'contractTemplateAdvanced',
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
                id: 'contractTemplateNameS',
                name: 'fileTemplateName',
                fieldLabel: '模板名称',
                regex: /^.{1,50}$/,
                enforceMaxLength: true,
                maxLength: 50,
                regexText: '模板名称长度不能超过50个字符！'
            }, {
                id: 'contractTemplateDescriptionsS',
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
                    id: 'contractTemplateAddDateStart',
                    name: 'fileTemplateAddDateStart',
                    fieldLabel: '添加时间',
                    width: 160,
                    labelWidth: 55,
                    margin: '0 5 0 0',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'contractTemplateAddDateStart',
                        end: 'contractTemplateAddDateEnd'
                    },
                    vtype: 'dateRange',
                    format: 'Y-m-d'
                }, {
                    xtype: 'datefield',
                    id: 'contractTemplateAddDateEnd',
                    name: 'fileTemplateAddDateEnd',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    fieldLabel: '~',
                    labelSeparator: "", // 分隔符，默认":"
                    labelWidth: 15,
                    format: 'Y-m-d',
                    width: 115,
                    margin: '0 0 0 5',
                    dateRange: {
                        begin: 'contractTemplateAddDateStart',
                        end: 'contractTemplateAddDateEnd'
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
                    id: 'contractTemplateEditDateStart',
                    name: 'fileTemplateEditDateStart',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    fieldLabel: '修改时间',
                    width: 160,
                    labelWidth: 55,
                    margin: '0 5 0 0',
                    dateRange: {
                        begin: 'contractTemplateEditDateStart',
                        end: 'contractTemplateEditDateEnd'
                    },
                    vtype: 'dateRange',
                    format: 'Y-m-d'
                }, {
                    xtype: 'datefield',
                    id: 'contractTemplateEditDateEnd',
                    name: 'fileTemplateEditDateEnd',
                    labelWidth: 15,
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    fieldLabel: '~',
                    labelSeparator: "", // 分隔符，默认":"
                    width: 115,
                    margin: '0 0 0 5',
                    dateRange: {
                        begin: 'contractTemplateEditDateStart',
                        end: 'contractTemplateEditDateEnd'
                    },
                    vtype: 'dateRange',
                    format: 'Y-m-d'
                } ]
            }, {
                xtype: 'button',
                text: '检索',
                width: 60,
                action: 'contractSuperQueryBtn'
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
            text: "模板编号",
            flex: 0.5,
            dataIndex: 'fileTemplateID',
            hidden: true,
            sortable: true,
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