Ext.define('CRM.view.customerManagement.customerProfiles.CoopResumeList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.coopResumelist',
    id: 'coopResumelist',
    // width: 900,
    // height: 475,
    layout: 'fit',
    autoShow: true,
//    border: 0,
    // modal: true,
    // minWidth: 100,
    // resizable: false,
    // constrainHeader: true,
    initComponent: function() {
        var me = this;
        this.projectTypeStore = Ext.create('CRM.store.code.Code');
        this.projectTypeSearchStore = Ext.create('CRM.store.code.Code');
        this.store = Ext.create('CRM.store.customerManagement.customerProfiles.CoopResume');
        this.selModel = Ext.create('Ext.selection.CheckboxModel');
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            align: "left",
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: "项目名称",
            renderer: this.rendererValue,
            flex: 1.5,
            dataIndex: 'projectName'
        }, {
            header: "开始日期",
            flex: 1,
            align: 'center',
            dataIndex: 'beginDate'
        }, {
            header: "结束日期",
            flex: 1,
            align: 'center',
            dataIndex: 'endDate'
        }, {
            header: "项目规模(万元)",
            flex: 1,
            align: 'right',
            dataIndex: 'projectScale'
        }, {
            header: "项目人数",
            flex: 1,
            align: 'right',
            dataIndex: 'peopleNumber'
        }, {
            header: "我方负责人",
            renderer: this.rendererValue,
            flex: 1,
            dataIndex: 'principalWe'
        }, {
            header: "对方负责人",
            renderer: this.rendererValue,
            flex: 1,
            dataIndex: 'principalThey'
        }, {
            header: "合作状态",
            renderer: this.rendererValue,
            flex: 1,
            dataIndex: 'projectTypeName'
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            items: [ {
                xtype: 'textfield',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "检索条件最大长度不能超过1024个字符！",
                itemId: 'searchText',
                width: 150,
                selectOnFocus: true
            }, {
                xtype: 'button',
                text: '检索',
                id: 'coopResumeQueryBtn',
                action: 'queryBtn'
            }, {
                xtype: 'hiddenfield',
                itemId: 'customerID',
                width: 1,
                name: 'customerID'
            }, {
                xtype: 'button',
                text: '高级检索',
                action: 'openOrCloseSuperQueryBtn'
            }, {
                xtype: 'tbfill'
            }, {
                xtype: 'button',
                text: '添加',
                action: 'addCoop'
            }, {
                xtype: 'button',
                id: 'coopResumeEditBtn',
                text: '编辑',
                action: 'editCoop',
                disabled: true
            }, {
                xtype: 'button',
                id: 'coopResumeDelBtn',
                text: '删除',
                action: 'deleteCoop',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'coopResumeForm',
            layout: 'column',
            hidden: true,
            defaults: {
                defaultType: 'textfield',
                enforceMaxLength: true,
                labelWidth: 60,
                labelSeparator: ':',
                width: 160,
                margin: '5 5 5 5'
            },
            items: [ {
                xtype: 'textfield',
                fieldLabel: '项目名称',
                maxLength: 50,
                maxLengthText: "项目名称最大长度不能超过50个字符！",
                name: 'projectName'
            }, {
                xtype: 'combobox',
                fieldLabel: '合作状态',
                itemId: 'projectType',
                name: 'projectType',
                // tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li
                // role="option"
                // class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>',
                // '</tpl></ul>'),
                store: this.projectTypeSearchStore,
                queryMode: 'local',
                editable: false,
                value: '00',
                displayField: 'value',
                valueField: 'code'
            }, {
                xtype: 'numberfield',
                fieldLabel: '项目规模(万元)',
                minValue: 0,
                maxLength: 5,
                maxValue: 10000,
                maxText: '项目规模(万元)不超过10000！',
                decimalPrecision: 1,
                labelWidth: 70,
                name: 'projectScale'
            }, {
                xtype: 'numberfield',
                fieldLabel: '项目人数',
                minValue: 0,
                maxLength: 5,
                maxValue: 10000,
                maxText: '项目人数不超过10000！',
                allowDecimals: false,
                name: 'peopleNumber'
            }, {
                fieldLabel: '我方负责人',
                maxLength: 20,
                maxLengthText: "我方负责人最大长度不能超过20个字符！",
                xtype: 'textfield',
                labelWidth: 70,
                name: 'principalWe'
            }, {
                maxLength: 20,
                maxLengthText: "客户负责人最大长度不能超过20个字符！",
                fieldLabel: '客户负责人',
                xtype: 'textfield',
                labelWidth: 70,
                name: 'principalThey'
            }, {
                xtype: 'datefield',
                fieldLabel: '开始日期查询',
                width: 175,
                maxLength: 10,
                labelWidth: 80,
                format: 'Y-m-d',
                invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                minValue: '01/01/1753',
                minText: '日期不能小于1753-1-1！',
                name: 'beginDateSearchMin',
                id: 'beginDateSearchMin',
                dateRange: {
                    begin: 'beginDateSearchMin',
                    end: 'beginDateSearchMax'
                },
                vtype: 'dateRange'
            }, {
                xtype: 'datefield',
                labelSeparator: '~',
                fieldLabel: ' ',
                width: 105,
                maxLength: 10,
                labelWidth: 10,
                format: 'Y-m-d',
                name: 'beginDateSearchMax',
                id: 'beginDateSearchMax',
                invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                minValue: '01/01/1753',
                minText: '日期不能小于开始日期！',
                dateRange: {
                    begin: 'beginDateSearchMin',
                    end: 'beginDateSearchMax'
                },
                vtype: 'dateRange'
            }, {
                xtype: 'button',
                text: '检索',
                action: 'coopResumeSuperQueryBtn',
                width: 60
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
