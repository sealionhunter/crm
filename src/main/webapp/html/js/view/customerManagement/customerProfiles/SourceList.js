Ext.define('CRM.view.customerManagement.customerProfiles.SourceList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.sourcelist',
    id: 'sourceListCard',
    title: "客户来源列表",
    margins: '0 5 0 0',
    minWidth: 100,
    store: 'customerManagement.customerProfiles.CustomerSource',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    loadMask: true,
    initComponent: function() {
        var me = this;
        this.sourceTypeStore = Ext.create('CRM.store.code.Code');
        this.typeSearchStore = Ext.create('CRM.store.code.Code');
        this.customerStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerName');

        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            boeder: false,
            text: '序号',
            width: 50,
            minWidth: 160,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: "客户",
            dataIndex: 'customerName',
            sortable: true,
            renderer: this.rendererValue,
            minWidth: 160,
            flex: 1
        }, {
            text: "地  区",
            dataIndex: 'sourceArea',
            sortable: true,
            renderer: this.rendererValue,
            minWidth: 160,
            flex: 1
        }, {
            text: "来 源",
            dataIndex: 'sourceTypeName',
            sortable: true,
            renderer: this.rendererValue,
            minWidth: 160,
            flex: 1
        }, {
            text: "备  注",
            dataIndex: 'descriptions',
            sortable: true,
            minWidth: 160,
            renderer: this.rendererValue,
            flex: 3
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            defaults: {
                border: false
            },
            items: [ {
                id: 'sourceSearchText',
                itemId: 'searchText',
                xtype: 'textfield',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "检索内容长度不能超过1024个字符！",
                width: 150
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn'
            }, {
                text: '高级检索',
                xtype: 'button',
                action: 'superQueryBtn'
            }, {
                // 添加填充符
                xtype: "tbfill"
            }, {
                xtype: 'button',
                id: 'SourceAddBtn',
                hidden: true,
                itemId: '11201',
                text: '添加'
            }, {
                xtype: 'button',
                text: '编辑',
                id: 'SourceEditBtn',
                hidden: true,
                itemId: '11202',
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                id: 'SourceDelBtn',
                hidden: true,
                itemId: '11203',
                disabled: true
            } ]
        }, {
            xtype: 'form',
            id: 'sourceAdvanced',
            layout: 'column',
            height: 32,
            defaults: {
                width: 150,
                margin: '5 5 5 5'
            },
            hidden: true,
            items: [ {
                xtype: 'textfield',
                name: 'sourceCustomer',
                labelWidth: 30,
                fieldLabel: '客户',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "客户长度不能超过50个字符！"
            }, {
                xtype: 'textfield',
                name: 'sourceArea',
                labelWidth: 30,
                fieldLabel: '地区',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "地区长度不能超过50个字符！"
            }, {
                xtype: 'combo',
                id: 'sourceTypeCombo',
                name: 'sourceType',
                labelWidth: 30,
                width: 180,
                fieldLabel: '来源',
                editable: false,
                store: this.typeSearchStore,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>'),
                queryMode: 'local',
                forceSelection: true,
                value: '00',
                valueField: 'code',
                displayField: 'value'
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
