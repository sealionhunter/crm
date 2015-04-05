Ext.define('CRM.view.customerManagement.customerProfiles.CustomerList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.customerlist',
    title: '客户基本信息列表',
    id: 'customerlist',
    minWidth: 120,
    store: 'customerManagement.customerProfiles.Customer',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.userStore = Ext.create('CRM.store.customerManagement.customerProfiles.User');
        this.businessUnitStore = Ext.create('CRM.store.code.Code');
        this.customerTypeStore = Ext.create('CRM.store.code.Code');
        this.scaleStore = Ext.create('CRM.store.code.Code');
        this.valueEvaluateStore = Ext.create('CRM.store.code.Code');
        this.customerStatementStore = Ext.create('CRM.store.code.Code');
        this.industryStore = Ext.create('CRM.store.code.Code');
        this.feeStore = Ext.create('CRM.store.code.Code');
        this.searchScaleStore = Ext.create('CRM.store.code.Code');
        this.searchIndustryStore = Ext.create('CRM.store.code.Code');
        this.searchFeeStore = Ext.create('CRM.store.code.Code');
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "客户名称",
            dataIndex: 'customerName',
            sortable: true,
            renderer: this.rendererValue,
            minWidth: 160,
            flex: 2
        }, {
            text: "客户经理",
            dataIndex: 'holderName',
            sortable: true,
            renderer: this.rendererValue,
            minWidth: 80,
            flex: 1
        }, {
            text: "客户状态",
            dataIndex: 'customerStatementName',
            minWidth: 80,
            renderer: this.rendererValue,
            flex: 1
        }, {
            text: "注册资金",
            dataIndex: 'scaleName',
            minWidth: 80,
            renderer: this.rendererValue,
            flex: 1
        }, {
            text: "市场名称",
            dataIndex: 'industryName',
            renderer: this.rendererValue,
            minWidth: 80,
            flex: 1
        }, {
            text: "所有权",
            dataIndex: 'feeName',
            minWidth: 80,
            renderer: this.rendererValue,
            sortable: true,
            flex: 1
        }, {
            text: "年产值(万元)",
            dataIndex: 'earningName',
            minWidth: 80,
            renderer: this.rendererValue,
            align: 'right',
            flex: 1
        }, {
            minWidth: 160,
            text: "标准地址",
            dataIndex: 'customerAddr',
            renderer: this.rendererValue,
            flex: 2
        } ];
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            items: [ {
                xtype: 'textfield',
                itemId: 'searchText',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: '检索条件最大长度不能超过1024个字符！',
                width: 150,
                selectOnFocus: true
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn',
                itemId: 'queryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                itemId: 'superBtn',
                action: 'openOrCloseSuperQueryBtn'
            }, {
                xtype: 'tbfill'
            }, {
                hidden: true,
                itemId: '11107',
                text: '导出客户信息',
                action: 'exportCustomer'
            }, {
                text: '客户详情',
                itemId: '11102',
                hidden: true,
                id: 'customerDetailBtn',
                disabled: true,
                action: 'customerDetail'
            }, {
                itemId: '11103',
                hidden: true,
                text: '领导建议',
                id: 'customerLookResBtn',
                disabled: true,
                action: 'customerLookResume'
            }, {
                hidden: true,
                itemId: '11104',
                text: '添加',
                action: 'addCustomer'
            }, {
                id: 'customerEditBtn',
                text: '编辑',
                hidden: true,
                itemId: '11105',
                disabled: true,
                action: 'editCustomer'
            }, {
                id: 'customerDelBtn',
                itemId: '11106',
                hidden: true,
                text: '删除',
                disabled: true,
                action: 'deleteCustomer'
            }, {
                hidden: true,
                itemId: '11205',
                text: '导出客户信息',
                action: 'exportCustomerCommon'
            }, {
                text: '客户详情',
                itemId: '11206',
                hidden: true,
                id: 'customerCommonDetailBtn',
                disabled: true,
                action: 'customerCommonDetail'
            }, {
                hidden: true,
                id: 'customerCommonReceiveBtn',
                itemId: '11204',
                text: '领取客户',
                disabled: true,
                action: 'receiveCustomerCommon'
            }, {
                hidden: true,
                itemId: '11201',
                text: '添加',
                action: 'addCustomerCommon'
            }, {
                id: 'customerCommonEditBtn',
                text: '编辑',
                hidden: true,
                itemId: '11202',
                disabled: true,
                action: 'editCustomerCommon'
            }, {
                id: 'customerCommonDelBtn',
                itemId: '11203',
                hidden: true,
                text: '删除',
                disabled: true,
                action: 'deleteCustomerCommon'
            } ]
        }, {
            xtype: 'form',
            layout: 'column',
            hidden: true,
            defaults: {
                xtype: 'textfield',
                enforceMaxLength: true,
                width: 160,
                margin: '5 5 5 5'
            },
            items: [ {
                fieldLabel: '客户名称',
                name: 'customerName',
                labelWidth: 55,
                maxLength: 50,
                maxLengthText: '客户名称最大长度不能超过50个字符！',
                selectOnFocus: true
            }, {
                fieldLabel: '客户经理',
                itemId: 'holder',
                maxLength: 20,
                maxLengthText: '客户经理最大长度不能超过20个字符！',
                name: 'holder',
                labelWidth: 55,
                selectOnFocus: true
            }, {
                xtype: 'combobox',
                fieldLabel: '注册资金',
                name: 'scale',
                itemId: 'scale',
                allowBlank: true,
                emptyText: '请选择',
                editable: false,
                labelWidth: 55,
                store: this.searchScaleStore,
                displayField: 'value',
                queryMode: 'local',
                value: '00',
                valueField: 'code'
            }, {
                xtype: 'combobox',
                fieldLabel: '市场名称',
                name: 'industry',
                itemId: 'industry',
                allowBlank: true,
                emptyText: '请选择',
                editable: false,
                labelWidth: 55,
                store: this.searchIndustryStore,
                displayField: 'value',
                queryMode: 'local',
                value: '00',
                valueField: 'code'
            }, {
                xtype: 'combobox',
                fieldLabel: '所有权',
                name: 'fee',
                itemId: 'fee',
                allowBlank: true,
                emptyText: '请选择',
                editable: false,
                width: 147,
                labelWidth: 42,
                store: this.searchFeeStore,
                displayField: 'value',
                valueField: 'code',
                value: '00',
                queryMode: 'local'
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 195,
                border: false,
                items: [ {
                    xtype: 'numberfield',
                    fieldLabel: '年产值(万元)',
                    minValue: 0,
                    labelWidth: 80,
                    maxValue: 1000000000,
                    margin: '0 5 0 0',
                    width: 195,
                    maxText: '年产值最大不超过十万亿！',
                    decimalPrecision: 4,
                    itemId: 'earning',
                    name: 'earning',
                    selectOnFocus: true
                } ]
            }, {
                fieldLabel: '标准地址',
                itemId: 'customerAddr',
                maxLength: 50,
                maxLengthText: '标准地址最大长度不能超过50个字符！',
                name: 'customerAddr',
                labelWidth: 55,
                selectOnFocus: true
            }, {
                xtype: 'button',
                action: 'customerSuperQueryBtn',
                text: '检索',
                width: 60
            } ]
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});