Ext.define('CRM.view.customerManagement.competitor.AddOrEditCprAnalysis', {
    extend: 'Ext.window.Window',
    alias: 'widget.addoreditcpranalysis',
    title: '添加竞争对手分析',
    id: 'addOrEditCprAnalysis',
    layout: 'absolute',
    width: 600,
    height: 585,
    modal: true,
    autoShow: true,
    autoScroll: true,
    resizable: false,
    constrainHeader: true,
    initComponent: function() {
        this.cprChooseStore = Ext.create('CRM.store.customerManagement.competitor.CprChooseStore');
        this.cprChooseStore.load();
        this.items = [ {
            xtype: 'form',
            height: 888,
            border: 0,
            defaultType: 'textarea',
            defaults: {
                width: 540,
                htmlEncode: true
            },
            items: [ {
                name: 'cprAnalysisID',
                xtype: 'textfield',
                hidden: true
            }, {
                xtype: 'combo',
                margin: '5 10 10 15',
                id: 'competitorInfoId',
                itemId: 'competitorInfoId',
                store: this.cprChooseStore,
                displayField: 'competitorName',
                valueField: 'competitorInfoId',
                name: 'competitorInfoId',
                allowBlank: false,
                editable: false,
                blankText: '竞争对手名称不能为空！',
                fieldLabel: '竞争对手名称',
                labelSeparator: redStar,
                queryMode: 'local',
                emptyText: '输入的竞争对手名称必须存在列表中',
                forceSelection: true,
                tpl: Ext.create('Ext.XTemplate', '<ul><tpl for=".">', '<li role="option" class="x-boundlist-item">{[Ext.htmlEncode(values.competitorName)]}</li>', '</tpl></ul>')
            }, {
                id: 'area',
                itemId: 'area',
                xtype: 'combo',
                displayField: 'value',
                valueField: 'code',
                margin: '10 10 10 15',
                store: Ext.getCmp('cpranalysislist').industryStore,
                queryMode: 'local',
                name: 'area',
                editable: false,
                allowBlank: false,
                forceSelection: true,
                maxLength: 20,
                maxLengthText: '所属领域长度不能超过20个字符！',
                blankText: '所属领域不能为空！',
                emptyText: '输入的所属领域必须存在列表中',
                fieldLabel: '所属领域',
                labelSeparator: redStar
            }, {
                id: 'ability',
                itemId: 'ability',
                name: 'ability',
                margin: '10 10 10 15',
                fieldLabel: '对手能力分析',
                maxLength: 2048,
                height: 77,
                enforceMaxLength: true,
                maxLengthText: '对手能力分析长度不能超过2048个字符！'
            }, {
                id: 'targets',
                itemId: 'targets',
                name: 'targets',
                margin: '10 10 10 15',
                fieldLabel: '对手目标分析',
                maxLength: 2048,
                height: 77,
                enforceMaxLength: true,
                maxLengthText: '对手目标分析长度不能超过2048个字符！'
            }, {
                id: 'strategy',
                itemId: 'strategy',
                name: 'strategy',
                margin: '10 10 10 15',
                fieldLabel: '对手战略分析',
                maxLength: 2048,
                height: 77,
                enforceMaxLength: true,
                maxLengthText: '对手战略分析长度不能超过2048个字符！'
            }, {
                id: 'prediction',
                itemId: 'prediction',
                name: 'prediction',
                margin: '10 10 10 15',
                height: 77,
                fieldLabel: '反应模式预测',
                maxLength: 2048,
                enforceMaxLength: true,
                maxLengthText: '反应模式预测长度不能超过2048个字符！'
            }, {
                xtype: 'textfield',
                id: 'advantage',
                itemId: 'advantage',
                margin: '10 10 10 15',
                name: 'advantage',
                allowBlank: false,
                blankText: '优势不能为空！',
                fieldLabel: '优势',
                labelSeparator: redStar,
                maxLength: 2048,
                enforceMaxLength: true,
                maxLengthText: '优势长度不能超过2048个字符！'
            }, {
                xtype: 'textfield',
                id: 'disadvantage',
                itemId: 'disadvantage',
                name: 'disadvantage',
                allowBlank: false,
                margin: '10 10 10 15',
                blankText: '劣势不能为空！',
                fieldLabel: '劣势',
                enforceMaxLength: true,
                labelSeparator: redStar,
                maxLength: 2048,
                maxLengthText: '劣势长度不能超过2048个字符！'
            }, {
                id: 'advAnalysis',
                itemId: 'advAnalysis',
                name: 'advAnalysis',
                margin: '10 10 10 15',
                height: 77,
                fieldLabel: '优势详细分析',
                maxLength: 2048,
                enforceMaxLength: true,
                maxLengthText: '优势详细分析长度不能超过2048个字符！'
            }, {
                id: 'disadvAnalysis',
                itemId: 'disadvAnalysis',
                name: 'disadvAnalysis',
                margin: '10 10 10 15',
                fieldLabel: '劣势详细分析',
                maxLength: 2048,
                height: 77,
                enforceMaxLength: true,
                maxLengthText: '劣势详细分析长度不能超过2048个字符！'
            }, {
                id: 'others',
                itemId: 'others',
                name: 'others',
                margin: '10 10 10 15',
                fieldLabel: '其他',
                height: 77,
                maxLength: 2048,
                enforceMaxLength: true,
                maxLengthText: '其他长度不能超过2048个字符！'
            }, {
                fieldLabel: '综合竞争力',
                labelSeparator: redStar,
                xtype: 'numberfield',
                id: 'compositeComp',
                margin: '10 10 10 15',
                itemId: 'compositeComp',
                name: 'compositeComp',
                allowBlank: false,
                blankText: '综合竞争力不能为空！',
                nanText: '输入值为非有效数值！',
                step: 1,
                minValue: 0,
                maxValue: 100,
                minText: '综合竞争力的最小值为0！',
                maxText: '综合竞争力的最大值为100！',
                negativeText: '综合竞争力不能为负数！',
                emptyText: '请输入0~100之间的整数'
            }, {
                xtype: 'numberfield',
                id: 'compositeDefense',
                itemId: 'compositeDefense',
                name: 'compositeDefense',
                margin: '10 10 10 15',
                allowBlank: false,
                blankText: '综合防御力不能为空！',
                nanText: '输入值为非有效数值！',
                step: 1,
                minValue: 0,
                maxValue: 100,
                minText: '综合防御力的最小值为0！',
                maxText: '综合防御力的最大值为100！',
                negativeText: '综合防御力不能为负数！',
                fieldLabel: '综合防御力',
                labelSeparator: redStar,
                emptyText: '请输入0~100之间的整数'
            }, {
                id: 'advice',
                itemId: 'advice',
                name: 'advice',
                height: 77,
                margin: '10 10 5 15',
                fieldLabel: '建议',
                maxLength: 2048,
                enforceMaxLength: true,
                maxLengthText: '长度不能超过2048个字符或汉字'
            } ]

        } ];
        this.buttons = [ {
            type: 'submit',
            text: '确定',
            action: 'addOrEditSave'
        }, {
            text: '重置',
            id: 'addOrEditReset',
            action: 'addOrEditReset'
        }, {
            text: '取消',
            id: 'addOrEditClose',
            action: 'addOrEditClose'
        } ];
        this.callParent(arguments);
    }
});