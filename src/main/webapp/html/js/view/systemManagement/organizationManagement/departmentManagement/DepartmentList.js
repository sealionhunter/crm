Ext.define('CRM.view.systemManagement.organizationManagement.departmentManagement.DepartmentList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.DepartmentList',
    id: 'DepartmentList',
    title: '部门基本信息列表',
    loadMask: {
        msg: '加载数据中,请等待......'
    },
    multiSelect: true,
    margins: '0 5 0 0',
    minWidth: 100,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.store = Ext.create('CRM.store.systemManagement.organizationManagement.departmentManagement.DepartmentInfo');
        this.departmentStore = Ext.create('CRM.store.customerManagement.customerProfiles.Department');
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'deparmentToolBar',
            activeItem: 0,
            items: [ {
                xtype: 'textfield',
                itemId: 'searchText',
                maxLength: 1024,
                enforceMaxLength: true,
                vtype: 'search',
                width: 150,
                maxLengthText: '检索条件最大长度不能超过1024个字符！',
                selectOnFocus: true
            }, {
                xtype: 'button',
                text: '检索',
                itemId: 'queryButton',
                action: 'queryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                itemId: 'superBtn',
                action: 'openOrCloseSuperQueryBtn'
            }, '->', {
                text: '添加',
                action: 'departmentAdd'
            }, {
                text: '编辑',
                id: 'departmentEdit',
                action: 'departmentEdit',
                disabled: true
            }, {
                text: '删除',
                id: 'departmentDelete',
                action: 'departmentDelete',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            itemId: 'queryDepartment',
            id: 'queryDepartment',
            layout: 'column',
            hidden: true,
            defaults: {
                labelWidth: 55,
                margin: '5 5 5 5'
            },
            items: [ {
                xtype: 'combobox',
                fieldLabel: '上级部门',
                name: 'fatherDepartmentID',
                id: 'fatherDepartmentIDCombo',
                queryMode: 'local',
                store: this.departmentStore,
                editable: false,
                displayField: 'departmentName',
                valueField: 'departmentID',
                value: 0
//                ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.departmentName)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'textfield',
                fieldLabel: '部门经理',
                maxLength: 20,
                name: 'departmentManager',
                enforceMaxLength: true,
                maxLengthText: '部门经理名字长度不能超过20个字符！'
            }, {
                xtype: 'textfield',
                fieldLabel: '部门名称',
                name: 'departmentName',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: '部门名长度不能超过50个字符！'
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn'
            } ]
        } ];

        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: '部门名称',
            dataIndex: 'departmentName',
            flex: 2,
            minWidth: 150,
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '上级部门',
            dataIndex: 'departmentIDB',
            flex: 2,
            minWidth: 150,
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '部门经理',
            flex: 2,
            minWidth: 100,
            dataIndex: 'managerName',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '部门电话',
            flex: 2,
            minWidth: 100,
            dataIndex: 'departmentPhone',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '创建时间',
            flex: 2,
            minWidth: 100,
            dataIndex: 'createTime',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        } ];

        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.callParent(arguments);
    }
});