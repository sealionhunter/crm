Ext.define('CRM.view.customerManagement.cooperator.CooperatorAnalysisDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.cooperatoranalysisdetail',
    border: true,
    autoScroll: true,
    collapsible: true,
    hidden: true,
    defaultType: 'displayfield',
    region: 'east',
    width: 300,
    defaults: {
        labelAlign: 'left',
        labelWidth: 80,
        width: 255,
        htmlEncode: true,
        margin: '5 20 5 5'
    },
    items: [ {
        fieldLabel: '合作伙伴名称',
        name: 'cooperatorName'
    }, {
        fieldLabel: '优势领域',
        name: 'advantageField'
    }, {
        fieldLabel: '劣势领域',
        name: 'disadvantageField'
    }, {
        fieldLabel: '管理能力',
        name: 'managementAbilityName'
    }, {
        fieldLabel: '响应速度',
        name: 'responseSpeedName'
    }, {
        fieldLabel: '信任度',
        name: 'trustDegreeName'
    }, {
        fieldLabel: '技术水平评价',
        name: 'engLevelEvaluation'
    }, {
        fieldLabel: '合作情况概述',
        name: 'cooperationSummarize'
    }, {
        fieldLabel: '综合分析',
        name: 'comphsAnalysis'
    }, {
        fieldLabel: '备注',
        name: 'descriptions'
    }, {
        fieldLabel: '创建时间',
        name: 'createTime',
        substr: true
    } ],
    initComponent: function() {
        this.callParent(arguments);
    }
});