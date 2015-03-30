Ext.define('CRM.view.contactManagement.contactProfiles.ContactList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.contactlist',
    id: 'contactListCard',
//    title: "联系人基本信息列表",
    minWidth: 100,
//    border:false,
    store: 'contactManagement.contactProfiles.Contacts',
    selModel: {
        selType: 'checkboxmodel'
    },
    loadMask: true,
    initComponent: function() {
        var me = this;
        this.sexStore = Ext.create('CRM.store.code.Code');// 性别
        this.educationStore = Ext.create('CRM.store.code.Code');// 教育背景
        this.contactTypeStore = Ext.create('CRM.store.code.Code');// 教育背景
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "姓名",
            dataIndex: 'contactName',
            sortable: true,
            minWidth: 160,
            flex: 2,
            renderer: this.rendererValue
        }, {
            text: "联系人类型",
            dataIndex: 'contactTypeName',
            minWidth: 160,
            flex: 2,
            sortable: true
        }, {
            text: "单位",
            dataIndex: 'company',
            sortable: true,
            minWidth: 160,
            flex: 2,
            renderer: this.rendererValue
        }, {
            text: "部门",
            dataIndex: 'department',
            sortable: true,
            minWidth: 160,
            flex: 2,
            renderer: this.rendererValue
        }, {
            text: "职务",
            dataIndex: 'job',
            sortable: true,
            renderer: this.rendererValue
        }, {
            text: "联系电话",
            minWidth: 160,
            flex: 2,
            dataIndex: 'phoneNumber',
            sortable: true
        }, {
            text: "邮箱",
            dataIndex: 'email',
            minWidth: 160,
            flex: 2,
            sortable: true
        }, {
            fieldLabel: '联系人ID',
            dataIndex: 'contactID',
            hidden: true
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            items: [ {
                id: 'contactSearchText',
                itemId: 'searchText',
                xtype: 'textfield',
                width: 150,
                enforceMaxLength: true,
                vtype: 'search',
                maxLength: 1024,
                maxLengthText: "检索内容长度不能超过1024个字符！"
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn'
            }, {
                xtype: 'hiddenfield',
                itemId: 'customerID',
                width: 1,
                name: 'customerID'
            }, {
                text: '高级检索',
                xtype: 'button',
                action: 'superQueryBtn'
            }, {
                // 添加填充符
                xtype: "tbfill"
            }, {
                xtype: 'button',
                id: 'ContactAddBtn',
                hidden: true,
                itemId: '21101',
                text: '添加'
            }, {
                xtype: 'button',
                text: '编辑',
                hidden: true,
                id: 'ContactEditBtn',
                itemId: '21102',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                hidden: true,
                id: 'ContactDelBtn',
                itemId: '21103',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'contactAdvanced',
            layout: 'column',
            height: 32,
            defaults: {
                margin: '5 5 5 5',
                labelWidth: 30,
                width: 150
            },
            hidden: true,
            items: [ {
                xtype: 'textfield',
                vtype: 'search',
                name: 'contactName',
                labelAlign: "right",
                enforceMaxLength: true,
                maxLength: 20,
                maxLengthText: "姓名长度不能超过20个字符！",
                fieldLabel: '姓名'
            }, {
                xtype: 'textfield',
                vtype: 'search',
                name: 'contactCompany',
                maxLength: 50,
                enforceMaxLength: true,
                maxLengthText: "单位长度不能超过50个字符！",
                fieldLabel: '单位'
            }, {
                xtype: 'textfield',
                vtype: 'search',
                name: 'contactDepartment',
                labelAlign: "right",
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "部门长度不能超过50个字符！",
                fieldLabel: '部门'
            }, {
                xtype: 'textfield',
                vtype: 'search',
                name: 'contactJob',
                labelAlign: "right",
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "职务长度不能超过50个字符！",
                fieldLabel: '职务'
            }, {
                xtype: 'button',
                text: '检索',
                width: 60,
                action: 'queryBtn'
            } ]
        }, {
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            store: this.store,
            displayInfo: true
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});
