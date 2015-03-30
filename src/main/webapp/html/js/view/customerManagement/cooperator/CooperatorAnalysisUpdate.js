Ext.define('CRM.view.customerManagement.cooperator.CooperatorAnalysisUpdate', {
    extend: 'Ext.window.Window',
    alias: 'widget.cooperatoranalysisupdate',
    title: '添加合作伙伴分析',
    width: 640,
    height: 585,
    frame: false,
    autoShow: true,
    constrainHeader: true,
    resizable: false,
    modal: true,
    layout: 'fit',
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            border: false,
            autoScroll: true,
            margin: '1 1 1 1',
            defaultType: 'textarea',
            defaults: {
                labelAlign: 'left',
                labelSeparator: '',
                labelWidth: 100,
                width: 560,
                margin: '5 5 10 20'
            },
            items: [ {
                xtype: 'textfield',
                fieldLabel: 'ID',
                name: 'copAnalysisID',
                hidden: true
            }, {
                xtype: 'combobox',
                fieldLabel: '合作伙伴名称',
                labelSeparator: redStar,
                width: 560,
                labelAlign: 'left',
                name: 'cooperatorID',
                displayField: 'value',
                valueField: 'code',
                id: 'cooperatorID',
                itemId: 'cooperatorName',
                margin: '20 5 10 20',
                blankText: '合作伙伴名称不能为空！',
                store: Ext.getCmp('cooperatorAnalysisList').storeCopName,
                queryMode: 'remote',
                triggerAction: 'all',
                autoSelect: true,
                editable: false,
                forceSelection: true,
                listConfig: {
                    maxHeight: 100
                },
                allowBlank: false,
                // the solution of input html tag
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
            }, {
                fieldLabel: '优势领域',
                labelSeparator: redStar,
                id: 'advantageFieldUpdate',
                height: 62,
                name: 'advantageField',
                allowBlank: false,
                blankText: '优势领域不能为空！',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "优势领域长度不能超过1024个字符！"
            }, {
                fieldLabel: '劣势领域',
                labelSeparator: redStar,
                id: 'disadvantageFieldUpdate',
                height: 62,
                name: 'disadvantageField',
                allowBlank: false,
                blankText: '劣势领域不能为空！',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "劣势领域长度不能超过1024个字符！"
            }, {
                xtype: 'fieldcontainer',
                layout: 'hbox',
                margin: '5 5 5 20',
                width: 580,
                defaultType: 'combobox',
                defaults: {
                    labelSeparator: '',
                    labelWidth: 100,
                    width: 175
                },
                items: [ {
                    name: 'managementAbility',
                    fieldLabel: '管理能力',
                    labelSeparator: redStar,
                    id: 'managementAbilityUpdate',
                    width: 190,
                    store: Ext.getCmp('cooperatorAnalysisList').storeMagAbility,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    editable: false,
                    blankText: '管理能力不能为空！',
                    forceSelection: true,
                    allowBlank: false,
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                }, {
                    name: 'responseSpeed',
                    fieldLabel: '响应速度',
                    labelSeparator: redStar,
                    id: 'responseSpeedUpdate',
                    margin: '0 0 0 7',
                    labelWidth: 80,
                    width: 170,
                    store: Ext.getCmp('cooperatorAnalysisList').storeRespSpeed,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    editable: false,
                    blankText: '响应速度不能为空！',
                    forceSelection: true,
                    allowBlank: false,
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                }, {
                    name: 'trustDegree',
                    fieldLabel: '信 任 度',
                    labelSeparator: redStar,
                    id: 'trustDegreeUpdate',
                    margin: '0 0 0 8',
                    labelWidth: 80,
                    width: 170,
                    store: Ext.getCmp('cooperatorAnalysisList').storeTrustDegree,
                    queryMode: 'local',
                    displayField: 'value',
                    valueField: 'code',
                    editable: false,
                    forceSelection: true,
                    blankText: '信任度不能为空！',
                    allowBlank: false,
                    tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">' + '{[Ext.htmlEncode(values.value)]}</li>', '</tpl></ul>')
                } ]
            }, {
                fieldLabel: '技术水平评价',
                labelSeparator: redStar,
                id: 'engLevelEvaluationUpdate',
                height: 62,
                name: 'engLevelEvaluation',
                allowBlank: false,
                blankText: '技术水平评价不能为空！',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "技术水平评价长度不能超过1024个字符！"
            }, {
                fieldLabel: '合作情况概述',
                labelSeparator: redStar,
                id: 'cooperationSummarizeUpdate',
                height: 62,
                name: 'cooperationSummarize',
                allowBlank: false,
                blankText: '合作情况概述不能为空！',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "合作情况概述长度不能超过1024个字符！"
            }, {
                fieldLabel: '综合分析',
                labelSeparator: redStar,
                id: 'comphsAnalysisUpdate',
                height: 62,
                name: 'comphsAnalysis',
                allowBlank: false,
                blankText: '综合分析不能为空！',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "综合分析长度不能超过1024个字符！"
            }, {
                fieldLabel: '备注:',
                id: 'descriptionsUpdate',
                height: 62,
                name: 'descriptions',
                margin: '5 5 10 20',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "备注长度不能超过1024个字符！"
            } ],
            buttons: [ {
                text: '确定',
                action: 'addOrEditSave'
            }, {
                text: '清空',
                itemId: 'reset',
                action: 'addOrEditReset'
            }, {
                text: '取消',
                action: 'addOrEditCancel'
            } ]
        } ];
        this.callParent(arguments);
    }
});