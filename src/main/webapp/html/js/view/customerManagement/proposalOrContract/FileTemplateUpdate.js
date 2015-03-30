Ext.define('CRM.view.customerManagement.proposalOrContract.FileTemplateUpdate', {
    extend: 'Ext.Window',
    alias: 'widget.filetemplateupdate',
    id: 'filetemplateupdate',
    constrainHeader: true,
    width: 580,
    height: 590,
    y: 10,
    autoShow: true,
    layout: 'fit',
    resizable: false,
    modal: true,
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            id: 'contractTemplateInfoForm',
            bodyStyle: 'padding:5px 5px 0',
            layout: 'anchor',
            fieldDefaults: {
                anchor: '100%',
                labelAlign: 'top',
                msgTarget: 'side'
            },
            items: [ {
                xtype: 'hidden',
                name: 'fileTemplateID'
            }, {
                xtype: 'hidden',
                name: 'type'
            }, {
                xtype: 'textfield',
                labelSeparator: redStar,
                fieldLabel: '模板名称',
                name: 'fileTemplateName',
                itemId: 'contractTemplateName',
                allowBlank: false,
                blankText: '模板名称不能为空！',
                anchor: '100%',
                enforceMaxLength: true,
                maxLength: 50,
                regex: /^.{1,50}$/,
                regexText: '模板名称长度不能超过50个字符！'
            }, {
                xtype: 'textarea',
                fieldLabel: '模板描述',
                itemId: 'descriptions',
                name: 'descriptions',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: '模板描述长度不能超过1024个字符！'
            }, {
                xtype: 'htmleditor',
                fieldLabel: '模板内容',
                height: 360,
                name: 'fileTemplateValue',
                id: 'contractTemplateValue',
                labelSeparator: redStar
            }, {
                xtype: 'hidden',
                name: 'fileTemplateAddDate',
                format: 'm-d-y',
                id: 'contractTemplateAddDate'
            }, {
                xtype: 'hidden',
                name: 'fileTemplateEditDate',
                id: 'contractTemplateEditDate',
                format: 'm-d-y'
            } ],
            buttons: [ {
                text: '确定',
                action: 'save'
            }, {
                text: '清空',
                action: 'reset'
            }, {
                text: '取消',
                action: 'cancel'
            } ]
        } ];
        this.callParent(arguments);
    }
});