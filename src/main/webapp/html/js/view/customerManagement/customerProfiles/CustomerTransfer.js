Ext.define('CRM.view.customerManagement.customerProfiles.CustomerTransfer', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.customertransfer',
    id: 'customertransfer',
    title: '客戶转移',
    layout: 'border',
    frame: false,
    initComponent: function() {
        var me = this;
        this.store = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerTransfer');
        this.noHolderStore = Ext.create('CRM.store.customerManagement.customerProfiles.CustomerTransfer');
        Ext.apply(this.noHolderStore.proxy.extraParams, {
            searchFlag: 2
        });
        this.queryModeStore = Ext.create('CRM.store.code.Code');
        this.queryModeFormStore = Ext.create('CRM.store.code.Code');
        me.queryModeStore.load({
            params: {
                code: '00010005'
            },
            callback: function() {
                me.queryModeStore.each(function(record) {
                    if (record.get('code') != '000100050003') {
                        me.queryModeFormStore.add(record);
                    }
                });
                me.down('form').down('combo[itemId=queryMode]').select('000100050001');
                me.queryModeStore.insert(0, {
                    code: '00',
                    value: '- 不限 -'
                });
                me.down('toolbar').down('combo[itemId=queryMode]').select('00');
            }
        });

        this.departmentStore = Ext.create('CRM.store.systemManagement.userManagement.Department');
        this.projectTeamStore = Ext.create('CRM.store.customerManagement.customerProfiles.ProjectTeam');
        this.userStore = Ext.create('CRM.store.customerManagement.customerProfiles.User');
        this.departmentFormStore = Ext.create('CRM.store.systemManagement.userManagement.Department');
        this.projectTeamFormStore = Ext.create('CRM.store.customerManagement.customerProfiles.ProjectTeam');
        this.userFormStore = Ext.create('CRM.store.customerManagement.customerProfiles.User');
        this.items = [ {
            region: "center",
            flex: 2,
            xtype: 'gridpanel',
            title: '可选客户',
            margins: '0 2 0 0',
            store: this.store,
            selType: 'checkboxmodel',
            columns: [ Ext.create('Ext.grid.RowNumberer', {
                text: '序号',
                width: 40,
                renderer: function(value, metadata, record, rowIndex) {
                    var page = me.store.currentPage, pageSize = me.store.pageSize;
                    return (page - 1) * pageSize + rowIndex + 1;
                }
            }), {
                text: "客户经理",
                flex: 1,
                dataIndex: 'holderName',
                renderer: this.rendererValue,
                sortable: true
            }, {
                text: "客户名称",
                flex: 1,
                dataIndex: 'customerName',
                renderer: this.rendererValue,
                sortable: true
            }, {
                text: "所有权",
                flex: 1,
                dataIndex: 'feeName',
                renderer: this.rendererValue,
                sortable: true
            }, {
                text: "标准地址",
                flex: 1,
                dataIndex: 'customerAddr',
                renderer: this.rendererValue,
                sortable: true
            } ],
            tbar: [ {
                xtype: 'combobox',
                fieldLabel: '检索方式',
                name: 'queryMode',
                itemId: 'queryMode',
                labelWidth: 60,
                width: 180,
                store: this.queryModeStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'value',
                valueField: 'code',
                editable: false
            }, {
                xtype: 'combobox',
                fieldLabel: '部门',
                name: 'department',
                itemId: 'department',
                labelWidth: 40,
                hidden: true,
                width: 180,
                store: this.departmentStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'departmentName',
                valueField: 'departmentID',
                editable: false
            }, {
                xtype: 'combobox',
                fieldLabel: '团队',
                itemId: 'projectTeam',
                name: 'projectTeam',
                hidden: true,
                labelWidth: 40,
                width: 180,
                store: this.projectTeamStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'projectTeamName',
                valueField: 'projectTeamID',
                editable: false
            }, {
                xtype: 'combobox',
                fieldLabel: '姓名',
                name: 'user',
                itemId: 'user',
                hidden: true,
                labelWidth: 40,
                width: 180,
                store: this.userStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'realName',
                valueField: 'userID',
                editable: false
            }, {
                xtype: 'button',
                text: '检索',
                width: 50,
                id: 'transfer-search-button',
                action: 'search',
                itemId: 'searchBtn'
            } ],
            dockedItems: [ {
                xtype: 'pagingtoolbar',
                store: this.store,
                dock: 'bottom',
                displayInfo: true
            } ]
        }, {
            flex: 1,
            title: '转移对象',
            region: "east",
            xtype: 'form',
            margin: '0 0 0 2',
            defaultType: 'textfield',
            defaults: {
                x: 20,
                width: 150,
                labelWidth: 60,
                margin: '20 0 20 5'
            },
            items: [ {
                xtype: 'combobox',
                fieldLabel: '检索方式',
                name: 'queryMode',
                itemId: 'queryMode',
                labelWidth: 60,
                width: 220,
                store: this.queryModeFormStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'value',
                valueField: 'code',
                editable: false
            }, {
                xtype: 'combobox',
                fieldLabel: '部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门',
                name: 'department',
                itemId: 'department',
                labelWidth: 60,
                width: 220,
                store: this.departmentFormStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'departmentName',
                valueField: 'departmentID',
                editable: false
            }, {
                xtype: 'combobox',
                fieldLabel: '团&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;队',
                itemId: 'projectTeam',
                name: 'projectTeam',
                hidden: true,
                labelWidth: 60,
                width: 220,
                store: this.projectTeamFormStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'projectTeamName',
                valueField: 'projectTeamID',
                editable: false
            }, {
                xtype: 'combobox',
                fieldLabel: '姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
                name: 'user',
                itemId: 'user',
                labelWidth: 60,
                width: 220,
                store: this.userFormStore,
                queryMode: 'local',
                forceSelection: true,
                displayField: 'realName',
                valueField: 'userID',
                editable: false
            } ],
            buttons: [ {
                text: '确定',
                action: 'save'
            } ]
        } ];
//        this.buttons = [ {
//            text: '确定',
//            action: 'save'
//        }, {
//            text: '确定并关闭',
//            action: 'saveAndClose'
//        }, {
//            text: '取消',
//            action: 'close'
//        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }

});