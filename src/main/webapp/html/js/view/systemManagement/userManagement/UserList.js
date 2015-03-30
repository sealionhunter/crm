Ext.define('CRM.view.systemManagement.userManagement.UserList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.UserList',
    id: 'UserList',
    title: '用户基本信息列表',
    multiSelect: true,
    margins: '0 5 0 0',
    minWidth: 100,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.store = Ext.create('CRM.store.systemManagement.userManagement.UserInfo');
        this.departmentStore = Ext.create('CRM.store.customerManagement.customerProfiles.Department');
        this.projectTeamStore = Ext.create('CRM.store.customerManagement.customerProfiles.ProjectTeam');
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'userToolBar',
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
                action: 'userAdd'
            }, {
                text: '编辑',
                id: 'userEdit',
                action: 'userEdit',
                disabled: true
            }, {
                text: '删除',
                id: 'userDelete',
                action: 'userDelete',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            itemId: 'queryUser',
            id: 'queryUser',
            layout: 'column',
            hidden: true,
            defaults: {
                labelWidth: 30,
                margin: '5 5 5 5'
            },
            items: [ {
                xtype: 'textfield',
                fieldLabel: '姓名',
                vtype: 'search',
                name: 'realName',
                maxLength: 20,
                enforceMaxLength: true,
                maxLengthText: '姓名长度不能超过20个字符！',
                selectOnFocus: true
            }, {
                xtype: 'combobox',
                fieldLabel: '部门',
                name: 'departmentID',
                queryMode: 'local',
                store: this.departmentStore,
                queryMode: 'local',
                editable: false,
                displayField: 'departmentName',
                valueField: 'departmentID',
                value: 0
//                ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.departmentName)]}</li>', '</tpl></ul>')
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn'
            } ]
        } ];
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "用户名",
            dataIndex: 'userName',
            flex: 2,
            minWidth: 100,
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: "姓名",
            flex: 2,
            minWidth: 100,
            dataIndex: 'realName',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: "部门",
            flex: 2,
            minWidth: 100,
            dataIndex: 'departmentIDB',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: "邮箱",
            flex: 2,
            minWidth: 150,
            dataIndex: 'email',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: "职务",
            flex: 2,
            minWidth: 150,
            dataIndex: 'job',
            sortable: true,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: "手机号码",
            flex: 2,
            minWidth: 150,
            dataIndex: 'mobile',
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