Ext.define('CRM.view.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.ProjectTeamList',
    id: 'ProjectTeamList',
    title: '团队基本信息列表',
    multiSelect: true,
    margins: '0 5 0 0',
    minWidth: 100,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        this.store = Ext.create('CRM.store.systemManagement.organizationManagement.projectTeamManagement.ProjectTeamInfo');
        this.departmentStore = Ext.create('CRM.store.systemManagement.userManagement.Department');
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'projectTeamToolBar',
//            height: 30,
            activeItem: 0,
//            defaults: {
//                border: false
//            },
            items: [ {
                xtype: 'combobox',
                name: 'department',
                multiSelect: false,
                store: this.departmentStore,
                queryMode: 'local',
                editable: false,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.departmentName)]}</li>', '</tpl></ul>'),
                labelWidth: 58,
                displayField: 'departmentName',
                valueField: 'departmentID',
                fieldLabel: '所属部门'
            }, {
                xtype: 'textfield',
                name: 'projectTeamName',
                labelWidth: 58,
                enforceMaxLength: true,
                maxLength: 15,
                regex: /^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$/,
                regexText: '团队名称必须为汉字、字母或数字！',
                fieldLabel: '团队名称'
            }, {
                xtype: 'button',
                text: '检索',
                id: 'searchProjectTeam',
                action: 'queryBtn'
            }, '->', {
                text: '成员管理',
                id: 'teamMemberEditBtn',
                action: 'teamMemberEdit',
                disabled: true
            }, {
                text: '添加',
                action: 'projectTeamAdd'
            }, {
                text: '编辑',
                id: 'projectTeamEdit',
                action: 'projectTeamEdit',
                disabled: true
            }, {
                text: '删除',
                id: 'projectTeamDelete',
                action: 'projectTeamDelete',
                disabled: true
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
            text: '团队名称',
            dataIndex: 'projectTeamName',
            minWidth: 100,
            flex: 1,
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '所属部门',
            flex: 1,
            minWidth: 100,
            dataIndex: 'departmentName',
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '团队负责人',
            flex: 1,
            minWidth: 100,
            dataIndex: 'projectTeamLeaderName',
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '团队状态',
            flex: 1,
            minWidth: 100,
            dataIndex: 'projectTeamStatusValue',
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '创建时间',
            flex: 1,
            minWidth: 100,
            dataIndex: 'createTime',
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '结束时间',
            flex: 1,
            minWidth: 100,
            dataIndex: 'endTime',
            renderer: function(value, metadata, record, rowIndex) {
                return Ext.String.htmlEncode(value);
            }
        }, {
            text: '备注',
            flex: 1,
            minWidth: 100,
            dataIndex: 'description',
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
