Ext.define('CRM.view.customerManagement.cooperator.CooperatorAnalysisList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.cooperatoranalysislist',
    id: 'cooperatorAnalysisList',
    title: '合作伙伴分析列表',
    store: 'customerManagement.cooperator.CooperatorAnalysisStore',
    multiSelect: true,
    enableKeyNav: true,
    minWidth: 100,
    border: true,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    initComponent: function() {
        var me = this;
        // create cooperator analysis combobox stores
        this.storeMagAbility = Ext.create('CRM.store.code.Code', {
            id: 'store_1'
        });
        this.storeRespSpeed = Ext.create('CRM.store.code.Code', {
            id: 'store_2'
        });
        this.storeTrustDegree = Ext.create('CRM.store.code.Code', {
            id: 'store_3'
        });
        this.storeCopName = Ext.create('CRM.store.customerManagement.cooperator.CooperatorNameCombo', {
            id: 'store_4'
        });
        // load stores
        this.storeMagAbility.load({
            params: {
                code: '00070006'
            }
        });
        this.storeRespSpeed.load({
            params: {
                code: '00030007'
            }
        });
        this.storeTrustDegree.load({
            params: {
                code: '00030005'
            }
        });
        this.storeCopName.load();
        this.bbar = Ext.create('CRM.view.PagingToolbar', {
            store: this.store,
        });
        this.tbar = {
            xtype: 'toolbar',
            height: 25,
            items: [ {
                xtype: 'tbfill'
            }, {
                xtype: 'button',
                text: '添加',
                name: 'add',
                itemId: '14201',
                id: 'copAnalysisAddBtn',
                action: 'copAnalysisAddBtn',
                hidden: true,
                disabled: false
            }, {
                xtype: 'button',
                text: '编辑',
                name: 'edit',
                itemId: '14202',
                id: 'copAnalysisEditBtn',
                action: 'copAnalysisEditBtn',
                hidden: true,
                disabled: true
            }, {
                xtype: 'button',
                text: '删除',
                name: 'delete',
                itemId: '14203',
                id: 'copAnalysisDelBtn',
                action: 'copAnalysisDelBtn',
                hidden: true,
                disabled: true
            } ]
        };
        this.columns = [ Ext.create('Ext.grid.RowNumberer', {
            text: '序号',
            width: 40,
            align: 'right',
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }), {
            text: '合作伙伴名称',
            dataIndex: 'cooperatorName',
            sortable: true,
            renderer: this.rendererValue,
            flex: 2,
            minWidth: 150
        }, {
            text: '优势领域',
            dataIndex: 'advantageField',
            sortable: true,
            renderer: this.rendererValue,
            flex: 3,
            minWidth: 100
        }, {
            text: '劣势领域',
            dataIndex: 'disadvantageField',
            sortable: true,
            renderer: this.rendererValue,
            flex: 3,
            minWidth: 100
        }, {
            text: '管理能力',
            dataIndex: 'managementAbilityName',
            sortable: true,
            renderer: this.rendererValue,
            flex: 1,
            minWidth: 100
        }, {
            text: '响应速度',
            dataIndex: 'responseSpeedName',
            sortable: true,
            renderer: this.rendererValue,
            hidden: true,
            flex: 1,
            minWidth: 100
        }, {
            text: '信任度',
            dataIndex: 'trustDegreeName',
            sortable: true,
            renderer: this.rendererValue,
            hidden: true,
            flex: 1,
            minWidth: 100
        }, {
            text: '技术水平评价',
            dataIndex: 'engLevelEvaluation',
            sortable: true,
            renderer: this.rendererValue,
            hidden: true,
            flex: 2,
            minWidth: 150
        }, {
            text: '合作情况概述',
            dataIndex: 'cooperationSummarize',
            sortable: true,
            renderer: this.rendererValue,
            hidden: true,
            flex: 3,
            minWidth: 150
        }, {
            text: '综合分析',
            dataIndex: 'comphsAnalysis',
            sortable: true,
            renderer: this.rendererValue,
            flex: 3,
            minWidth: 100
        }, {
            text: '创建时间',
            dataIndex: 'createTime',
            sortable: true,
            renderer: this.rendererValue,
            hidden: true,
            flex: 1,
            minWidth: 100
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});