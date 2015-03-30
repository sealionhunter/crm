Ext.define('CRM.view.contactManagement.contactProfiles.SocialForm', {
    extend: 'Ext.Window',
    alias: 'widget.socialForm',
    id: 'socialForm',
    width: 340,
    height: 430,
    constrainHeader: true,
    resizable: false,
    modal: true,
    closeAction: 'hide',
    initComponent: function() {
        this.items = [ {
            xtype: 'form',
            border: false,
            height: 370,
            defaultType: 'textfield',
            defaults: {
                htmlEncode: true,
                x: 20,
                width: 270,
                labelWidth: 60
            },
            items: [ {
                y: 20,
                fieldLabel: '姓名',
                name: 'socialName',
                labelSeparator: redStar,
                enforceMaxLength: true,
                maxLength: 20,
                maxLengthText: "姓名长度不能超过20个字符！",
                allowBlank: false,
                blankText: "姓名不能为空！"
            }, {
                y: 25,
                fieldLabel: '关系',
                name: 'socialRelation',
                labelSeparator: redStar,
                blankText: "关系不能为空！",
                enforceMaxLength: true,
                maxLength: 20,
                maxLengthText: "关系长度不能超过20个字符！",
                allowBlank: false
            }, {
                y: 35,
                fieldLabel: '出生日期',
                name: 'birthday',
                xtype: 'datefield',
                format: 'Y-m-d',
                invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD',
                maxValue: new Date(),
                maxText: '出生日期大于今天'
            }, {
                y: 40,
                fieldLabel: '政治面貌',
                name: 'political',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "政治面貌长度不能超过50个字符！"
            }, {
                y: 45,
                fieldLabel: '工作单位',
                name: 'company',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "工作单位长度不能超过50个字符！"
            }, {
                y: 50,
                fieldLabel: '职务',
                name: 'job',
                enforceMaxLength: true,
                maxLength: 50,
                maxLengthText: "职务长度不能超过50个字符！"
            }, {
                y: 55,
                fieldLabel: '备注',
                xtype: 'textareafield',
                name: 'descriptions',
                enforceMaxLength: true,
                maxLength: 1024,
                maxLengthText: "备注长度不能超过1024个字符！"
            }, {
                xtype: 'hiddenfield',
                fieldLabel: '社会ID',
                name: 'socialID',
                itemId: 'ID'
            }, {
                xtype: 'hiddenfield',
                fieldLabel: '联系人ID',
                name: 'contactID',
                itemId: 'contactID'
            } ]
        } ];
        this.buttons = [ {
            type: 'submit',
            text: '确定',
            action: 'save'
        }, {
            xtype: 'button',
            itemId: 'resetform',
            action: 'reset'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'cancel'
        } ];
        this.callParent(arguments);
    }
});