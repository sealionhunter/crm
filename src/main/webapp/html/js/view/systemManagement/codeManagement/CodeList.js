Ext.define('CRM.view.systemManagement.codeManagement.CodeList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.CodeList',
    id: 'CodeList',
    title: 'Code管理',
    multiSelect: true,
    margins: '0 5 0 0',
    minWidth: 160,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    defaultListConfig: {
        loadMask: false
    },
    initComponent: function() {
        var me = this;
        this.store = Ext.create('CRM.store.systemManagement.codeManagement.Code');
        this.modeStore = Ext.create('CRM.store.systemManagement.codeManagement.Mode');
        this.moduleStore = Ext.create('CRM.store.systemManagement.codeManagement.Module');
        this.categoryStore = Ext.create('CRM.store.systemManagement.codeManagement.Category');
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            id: 'codeToolBar',
            activeItem: 0,
            items: [ {
                xtype: 'combobox',
                name: 'mode',
                id: 'viewModeComboBox',
                fieldLabel: 'Code类别',
                store: this.modeStore,
                queryMode: 'local',
                width: 180,
                labelWidth: 60,
                editable: false,
                valueField: 'code',
                displayField: 'value',
                defaultListConfig: {
                    loadMask: false
                }
//            ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl>', '</ul>')
            }, {
                xtype: 'combobox',
                name: 'module',
                id: 'moduleComboBox',
                fieldLabel: '模块',
                store: this.moduleStore,
                queryMode: 'local',
                width: 160,
                labelWidth: 40,
                editable: false,
                valueField: 'code',
                displayField: 'value',
                defaultListConfig: {
                    loadMask: false
                }
//            ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl>', '</ul>')
            }, {
                xtype: 'combobox',
                name: 'category',
                id: 'categoryComboBox',
                fieldLabel: '分类',
                width: 160,
                store: this.categoryStore,
                queryMode: 'local',
                labelWidth: 40,
                editable: false,
                displayField: 'value',
                valueField: 'code',
                defaultListConfig: {
                    loadMask: false
                }
//            ,
//                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl>', '</ul>')
            }, {
                xtype: 'button',
                text: '检索',
                itemId: 'queryButton',
                action: 'queryBtn'
            }, {
                xtype: 'tbfill'
            }, {
                xtype: 'button',
                text: '添加',
                id: 'codeItemsAdd',
                action: 'codeItemsAddBtn'
            }, {
                xtype: 'button',
                text: '编辑',
                id: 'codeItemsEdit',
                action: 'codeItemsEditBtn'
            }, {
                xtype: 'button',
                text: '删除',
                id: 'codeItemsDelete',
                action: 'codeItemsDeleteBtn'
            } ]
        } ];
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 50,
            align: 'right',
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            dataIndex: 'category',
            flex: 2,
            text: 'Code名称',
            minWidth: 160,
            sortable: true
        }, {
            dataIndex: 'value',
            flex: 2,
            text: '数据项',
            minWidth: 160,
            sortable: true,
            renderer: this.rendererValue
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