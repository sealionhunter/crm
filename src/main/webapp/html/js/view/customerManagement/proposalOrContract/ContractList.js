Ext.define('CRM.view.customerManagement.proposalOrContract.ContractList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.contractlist',
    id: 'contractlist',
    title: '合同列表',
    minWidth: 100,
    // 选择数据源
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.store = 'customerManagement.proposalOrContract.ContractStore';
        this.payTypeStore = Ext.create('CRM.store.code.Code');
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            defaults: {
                border: false
            },
            items: [ {
                xtype: 'textfield',
                id: 'search_text',
                itemId: 'searchText',
                enforceMaxLength: true,
                maxLength: 1024,
                width: 150,
                maxLengthText: '检索内容长度不能超过1024个字符！'
            }, {
                text: '检索',
                action: 'queryBtn'
            }, {
                text: '高级检索',
                action: 'superQueryBtn'
            }, '->', {
                text: '合同导出',
                action: 'daochu',
                disabled: true,
                id: 'daochu'
            }, {
                text: '添加',
                action: 'add',
                id: 'contractListAdd'
            }, {
                text: '编辑',
                action: 'edit',
                disabled: true,
                id: 'contractListEdit'
            }, {
                text: '删除',
                action: 'delete',
                id: 'deleteContract',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'contractAdvanced',
            layout: 'column',
            hidden: true,
            defaults: {
                margin: '5 5 5 5',
                renderer: this.rendererValue,
                xtype: 'textfield'
            },
            items: [ {
                id: 'contractNameSearch',
                labelWidth: 55,
                width: 160,
                fieldLabel: '合同名称',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '合同名称长度不能超过50个字符！'
            }, {
                id: 'orderIDSearch',
                labelWidth: 55,
                width: 160,
                fieldLabel: '订单编号',
                maxLength: 50,
                maxLengthText: "订单编号长度不能超过50个字符！",
                enforceMaxLength: true
            }, {
                id: 'fileTemplateNameSearch',
                labelWidth: 55,
                width: 160,
                fieldLabel: '合同模板',
                maxLength: 50,
                maxLengthText: "合同模板长度不能超过50个字符！",
                enforceMaxLength: true
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 325,
                border: false,
                items: [ {
                    xtype: 'mouthfield',
                    fieldLabel: "付款截止时间",
                    id: 'payEndTimeMin',
                    editable: false,
                    invalidText: '日期格式不正确，正确格式为：yyyy-MM！',
                    labelWidth: 80,
                    margin: '0 5 0 0',
                    format: 'Y-m',
                    value: "",
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    width: 190
                }, {
                    xtype: 'mouthfield',
                    fieldLabel: "~",
                    labelSeparator: "",
                    value: "",
                    editable: false,
                    invalidText: '日期格式不正确，正确格式为：yyyy-MM！',
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    id: 'payEndTimeMax',
                    labelWidth: 15,
                    margin: '0 0 0 5',
                    format: 'Y-m',
                    width: 115
                } ]
            }, {
                xtype: 'combobox',
                fieldLabel: '付款方式',
                labelWidth: 55,
                width: 180,
                id: 'payTypeSearch',
                store: this.payTypeStore,
                value: '0',
                listConfig: {
                    loadMask: false
                },
                name: 'proposalOrContractObject',
                queryMode: 'local',
                displayField: 'value',
                valueField: 'code',
                editable: false,
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'button',
                text: '检索',
                width: 60,
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
            dataIndex: 'contractID',
            hidden: true
        }, {
            text: "合同名称",
            flex: 1,
            dataIndex: 'contractName',
            sortable: false,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            text: "订单编号",
            flex: 1,
            dataIndex: 'orderID',
            sortable: false,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            text: "合同模板",
            flex: 1,
            dataIndex: 'fileTemplateName',
            sortable: false,
            minWidth: 100,
            renderer: this.rendererValue
        }, {
            text: "付款截止时间",
            flex: 1,
            dataIndex: 'payEndTime',
            sortable: false,
            minWidth: 100,
            format: 'Y/m',
            align: 'center'
        }, {
            text: "付款方式",
            flex: 1,
            dataIndex: 'payType',
            renderer: this.rendererPayType,
            sortable: false,
            minWidth: 100
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    },
    rendererPayType: function(value, metadata, record, rowIndex) {
        var result = "";
        if (this.payTypeStore.getCount() > 0) {
            this.payTypeStore.each(function(r) {
                if (r.get('code') == value) {
                    result = r.get('value');
                    return;
                }
            });
        }
        return result;
    },
    rendererValue: function (value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});
