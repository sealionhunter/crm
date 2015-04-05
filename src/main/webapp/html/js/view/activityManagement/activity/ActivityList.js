Ext.define('CRM.view.activityManagement.activity.ActivityList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.activitylist',
    title: '营销活动基本信息列表',
    id: 'card-activitylist',
    margins: '0 5 0 0',
    minWidth: 50,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    store: 'activityManagement.activity.ActivityStore',
    initComponent: function() {
        var me = this;
        this.activityTypeCombo = Ext.create('CRM.store.code.Code');// 活动类型
        this.activityEmphasisCombo = Ext.create('CRM.store.code.Code');// 重视程度
        this.activityRangeCombo = Ext.create('CRM.store.code.Code');// 活动范围
        this.activityPeriodCombo = Ext.create('CRM.store.code.Code');// 活动周期
        this.activityStateCombo = Ext.create('CRM.store.code.Code');// 活动状态
        this.typeSearchCombo = Ext.create('CRM.store.code.Code');// search
        // 活动类型
        this.rangeSearchCombo = Ext.create('CRM.store.code.Code');// search
        // 活动范围
        this.stateSearchCombo = Ext.create('CRM.store.code.Code');// search
        // 活动状态

        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            border: false,
            text: '序号',
            width: 50,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            header: '活动名称',
            dataIndex: 'activityName',
            minWidth: 140,
            flex: 1,
            renderer: this.rendererValue
        }, {
            header: '活动类型',
            dataIndex: 'activityTypeName',
            minWidth: 140,
            flex: 1
        }, {
            header: '开始时间',
            dataIndex: 'activityStartTime',
            align: 'center',
            minWidth: 140,
            flex: 0.9
        }, {
            header: '结束时间',
            dataIndex: 'activityEndTime',
            align: 'center',
            minWidth: 140,
            flex: 0.9
        }, {
            header: '活动地点',
            dataIndex: 'activityPlace',
            minWidth: 140,
            flex: 1,
            renderer: this.rendererValue
        }, {
            header: '活动范围',
            dataIndex: 'activityRangeName',
            minWidth: 140,
            flex: 0.8
        }, {
            header: '活动状态',
            dataIndex: 'activityStateName',
            minWidth: 140,
            flex: 0.8
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            activeItem: 0,
            items: [ {
                // condition input text
                xtype: 'textfield',
                itemId: 'searchText',
                id: 'activityQueryText',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "检索内容长度不能超过1024个字符！",
                width: 150,
                selectOnFocus: true
            }, {
                xtype: 'button',
                text: '检索',
                action: 'queryBtn'
            }, {
                xtype: 'button',
                text: '高级检索',
                action: 'superQueryBtn'
            }, {
                xtype: 'tbfill'
            }, {
                xtype: 'button',
                text: '添加',
                hidden: true,
                itemId: '31101',
                id: 'addActivityWindow'
            }, {
                xtype: 'button',
                text: '编辑',
                hidden: true,
                itemId: '31102',
                disabled: true,
                id: 'editActivityWindow'
            }, {
                xtype: 'button',
                text: '删除',
                hidden: true,
                itemId: '31103',
                disabled: true,
                id: 'deleteActivityWindow'
            } ]
        }, {
            xtype: 'form',
            layout: 'column',
            id: 'activitySuperQuery',
            defaultType: 'textfield',
            hidden: true,
            defaults: {
                labelWidth: 55,
                labelSeparator: ':',
                width: 140,
                margin: '5 5 5 5'
            },
            items: [ {
                xtype: 'textfield',
                fieldLabel: '活动名称',
                selectOnFocus: true,
                enforceMaxLength: true,
                maxLength: 30,
                maxLengthText: "活动名称长度不能超过30个字符！",
                name: 'activityName'
            }, {
                xtype: 'combobox',
                fieldLabel: '活动类型',
                width: 200,
                store: this.typeSearchCombo,
                multiSelect: false,// jiaxu
                displaySeparator: ';',
                valueSeparator: ',',
                forceSelection: true,
                queryMode: 'local',
                displayField: 'value',
                valueField: 'code',
                value: '00',
                id: 'activityTypeSearch',
                name: 'activityType',
                editable: false
            }, {
                fieldLabel: '活动地点',
                selectOnFocus: true,
                id: 'activityPlaceSearch',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "活动地点长度不能超过50个字符！",
                name: 'activityPlace'
            }, {
                xtype: 'combobox',
                fieldLabel: '活动状态',
                store: this.stateSearchCombo,
                multiSelect: false,// jiaxu
                displaySeparator: ';',
                valueSeparator: ',',
                forceSelection: true,
                queryMode: 'local',
                displayField: 'value',
                valueField: 'code',
                value: '00',
                id: 'activityStateSearch',
                name: 'activityState',
                editable: false
            }, {
                xtype: 'combobox',
                fieldLabel: '活动范围',
                width: 200,
                store: this.rangeSearchCombo,
                multiSelect: false,// jiaxu
                displaySeparator: ';',
                valueSeparator: ',',
                forceSelection: true,
                queryMode: 'local',
                displayField: 'value',
                valueField: 'code',
                value: '00',
                id: 'activityRangeSearch',
                name: 'activityRange',
                editable: false
            }, {
                xtype: 'panel',
                layout: 'column',
                width: 295,
                border: false,
                items: [ {
                    xtype: 'datefield',
                    name: 'activityStartTime',
                    id: 'activityStartTimeSearch',
                    fieldLabel: '活动时间',
                    width: 160,
                    labelWidth: 55,
                    margin: '0 5 0 0',
                    maxLength: 10,
                    enforceMaxLength: true,
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'activityStartTimeSearch',
                        end: 'activityEndTimeSearch'
                    },
                    vtype: 'dateRange'
                }, {
                    xtype: 'datefield',
                    name: 'activityEndTime',
                    id: 'activityEndTimeSearch',
                    fieldLabel: '~',
                    width: 115,
                    labelWidth: 15,
                    labelSeparator: '',
                    margin: '0 0 0 5',
                    maxLength: 10,
                    enforceMaxLength: true,
                    minValue: '01/01/1753',
                    minText: '日期不能小于1753-01-01',
                    format: 'Y-m-d',
                    invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                    dateRange: {
                        begin: 'activityStartTimeSearch',
                        end: 'activityEndTimeSearch'
                    },
                    vtype: 'dateRange'
                } ]
            }, {
                xtype: 'button',
                action: 'queryBtn',
                text: '检索',
                width: 60
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
