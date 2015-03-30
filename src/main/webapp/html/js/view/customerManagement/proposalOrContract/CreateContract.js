Ext.define('CRM.view.customerManagement.proposalOrContract.CreateContract', {
    extend: 'Ext.window.Window',
    alias: 'widget.createContract',
    id: 'createContract',
    constrainHeader: true,
    width: 880,
    height: 550,
    y: 50,
    autoShow: true,
    layout: 'border',
    resizable: false,
    modal: true,
    initComponent: function() {
        this.items = [ {
            region: 'center',
            xtype: 'form',
            layout: 'anchor',
            frame: true,
            bodyStyle: 'padding:5px 5px 5px',
            fieldDefaults: {
                labelAlign: 'top'
            },
            items: [ {
                xtype: 'htmleditor',
                fieldLabel: '内容',
                labelSeparator: redStar,
                height: 370,
                id: 'contractValue',
                name: 'proposalOrContractValue',
                anchor: '100%'
            }, {
                xtype: 'checkbox',
                boxLabel: '销售事件',
                name: 'contractEvent',
                inputValue: '1',
                id: 'contractEvent',
                hidden: true
            }, {
                xtype: 'textarea',
                fieldLabel: '中标',
                name: 'zhongbiao',
                anchor: '100%',
                height: 80,
                id: 'zhongbiao',
                maxLength: 1024,
                enforceMaxLength: true,
                maxLengthText: "中标长度不能超过1024字符"
            } ],
            buttons: [ {
                text: '上一步',
                action: 'selectType'
            }, {
                text: '生成合同',
                action: 'createContract'
            }, {
                text: '取消',
                action: 'cancelContract'
            } ]
        }, {
            xtype: 'contractReferenceInfo'
        } ];
        this.callParent(arguments);
    }
});