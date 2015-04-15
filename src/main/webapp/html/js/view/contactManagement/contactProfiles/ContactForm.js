Ext.define('CRM.view.contactManagement.contactProfiles.ContactForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.contactForm',
    width: 700,
    y: 50,
    id: 'contactForm',
    border: false,
    autoShow: true,
    resizable: false,
    constrainHeader: true,
    modal: true,
    initComponent: function() {
        this.items = {
            xtype: 'form',
            activeTab: 0,
            defaults: {
                htmlEncode: true,
                labelAlign: 'top',
                labelWidth: 90
            },
            items: [ {
                title: '<div style="font-size:12px">基本信息</div>',
                layout: 'anchor',
                items: [ {
                    xtype: 'fieldset',
                    layout: 'column',
                    padding: '5 0 5 15',
                    items: [ {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [ {
                            xtype: 'hiddenfield',
                            name: 'contactID',
                            itemId: 'contactID'
                        }, {
                            xtype: 'hiddenfield',
                            name: 'customerID',
                            itemId: 'customerID'
                        }, {
                            fieldLabel: '姓名',
                            name: 'contactName',
                            id: 'contactName',
                            labelSeparator: redStar,
                            allowBlank: false,
                            enforceMaxLength: true,
                            blankText: "姓名不能为空！",
                            maxLength: 20,
                            maxLengthText: "姓名长度不能超过20个字符！",
                            anchor: '90%'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [ {
                            fieldLabel: '单位',
                            name: 'company',
                            itemId: 'company',
                            labelSeparator: redStar,
                            allowBlank: false,
                            blankText: "单位不能为空！",
                            maxLength: 50,
                            enforceMaxLength: true,
                            maxLengthText: "单位长度不能超过50个字符！",
                            anchor: '90%'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [ {
                            fieldLabel: '出生日期',
                            name: 'birthday',
                            itemId: 'birthday',
                            anchor: '90%',
                            xtype: 'datefield',
                            format: 'Y-m-d',
                            invalidText: '日期格式不正确，正确格式为：YYYY-MM-DD！',
                            maxValue: new Date(),
                            maxText: '出生日期大于今天'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [ {
                            fieldLabel: '部门',
                            name: 'department',
                            itemId: 'department',
                            labelSeparator: redStar,
                            allowBlank: false,
                            blankText: "部门不能为空！",
                            maxLength: 50,
                            enforceMaxLength: true,
                            maxLengthText: "部门长度不能超过50个字符！",
                            anchor: '90%'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [ {
                            fieldLabel: '联系电话',
                            name: 'phoneNumber',
                            itemId: 'phoneNumber',
                            labelSeparator: redStar,
                            vtype: 'phone',
                            enforceMaxLength: true,
                            maxLength: 20,
                            allowBlank: false,
                            blankText: "联系电话不能为空！",
                            anchor: '90%'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [ {
                            fieldLabel: '职务',
                            name: 'job',
                            itemId: 'job',
                            labelSeparator: redStar,
                            allowBlank: false,
                            blankText: "职务不能为空！",
                            maxLength: 50,
                            enforceMaxLength: true,
                            maxLengthText: "职务长度不能超过50个字符！",
                            anchor: '90%'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        items: [ {
                            xtype: 'combo',
                            fieldLabel: '性别',
                            name: 'sex',
                            itemId: 'sex',
                            anchor: '90%',
                            labelSeparator: redStar,
                            store: Ext.getCmp('contactListCard').sexStore,
                            value: '3',
                            queryMode: 'local',
                            forceSelection: true,
                            allowBlank: false,
                            blankText: "性别不能为空！",
                            editable: false,
                            valueField: 'code',
                            displayField: 'value'
                        } ]
                    }, {
                        columnWidth: .5,
                        border: false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [ {
                            xtype: 'combo',
                            fieldLabel: '联系人类型',
                            name: 'contactType',
                            itemId: 'contactType',
                            anchor: '90%',
                            labelSeparator: redStar,
                            store: Ext.getCmp('contactListCard').contactTypeStore,
                            queryMode: 'local',
                            forceSelection: true,
                            allowBlank: false,
                            blankText: "联系人类型不能为空！",
                            editable: false,
                            valueField: 'code',
                            displayField: 'value'
                        }, {
                            fieldLabel: '创建时间',
                            xtype: 'hiddenfield',
                            name: 'createTime'
                        }, {
                            fieldLabel: '更新时间',
                            xtype: 'hiddenfield',
                            name: 'updateTime'
                        } ]
                    }, {
                        columnWidth: 1,
                        border: false,
                        layout: 'anchor',
                        items: [ {
                            fieldLabel: '照片',
                            xtype: 'fileuploadfield',
                            name: 'attach',
                            itemId: 'attach',
                            buttonText: '选择照片',
                            emptyText: "请选择需要上传的附件!"
                        } ]
                    } ]
                },
                // ==============================================================================================
                {
                    xtype: 'tabpanel',
                    plain: true,
                    activeTab: 0,
                    id: 'add_tabpan',
                    height: 350,
                    title: '<font style="font-size:12px">详细信息</font>',
                    collapsed: true,
                    collapsible: true,
                    defaults: {
                        bodyStyle: 'padding:10px'
                    },
                    items: [ {
                        title: '个人详细信息',
                        border: false,
                        defaultType: 'textfield',
                        items: [ {
                            xtype: 'panel',
                            layout: 'column',
                            border: false,
                            padding: '5 0 5 5',
                            items: [ {
                                columnWidth: .5,
                                border: false,
                                layout: 'anchor',
                                defaultType: 'textfield',
                                items: [ {
                                    fieldLabel: '民族',
                                    name: 'contactNational',
                                    maxLength: 10,
                                    enforceMaxLength: true,
                                    maxLengthText: "民族长度不能超过10个字符！"
                                }, {
                                    xtype: 'combo',
                                    fieldLabel: '学历',
                                    name: 'education',
                                    itemId: 'education',
                                    store: Ext.getCmp('contactListCard').educationStore,
                                    queryMode: 'local',
                                    forceSelection: true,
                                    editable: false,
                                    // value : '0',
                                    valueField: 'code',
                                    displayField: 'value'
                                }, {
                                    fieldLabel: '健康状况',
                                    name: 'health',
                                    maxLength: 10,
                                    enforceMaxLength: true,
                                    maxLengthText: "健康状况长度不能超过10个字符！"
                                }, {
                                    fieldLabel: '政治面貌',
                                    name: 'political',
                                    maxLength: 10,
                                    enforceMaxLength: true,
                                    maxLengthText: "政治面貌长度不能超过10个字符！"
                                }, {
                                    fieldLabel: '籍贯',
                                    name: 'nativePlace',
                                    maxLength: 50,
                                    enforceMaxLength: true,
                                    maxLengthText: "籍贯长度不能超过50个字符！"
                                } ]
                            }, {
                                columnWidth: .5,
                                border: false,
                                layout: 'anchor',
                                defaultType: 'textfield',
                                items: [ {
                                    fieldLabel: '邮箱',
                                    name: 'email',
                                    itemId: 'email',
                                    vtype: 'email',
                                    maxLength: 50,
                                    enforceMaxLength: true,
                                    maxLengthText: "最大长度不能超过50个字符！"
                                }, {
                                    fieldLabel: '传真',
                                    name: 'fax',
                                    itemId: 'fax',
                                    maxLength: 20,
                                    enforceMaxLength: true,
                                    vtype: 'fax'
                                }, {
                                    fieldLabel: 'QQ',
                                    name: 'QQ',
                                    itemId: 'QQ',
                                    maxLength: 20,
                                    enforceMaxLength: true,
                                    vtype: 'qq'
                                }, {
                                    fieldLabel: '家庭电话',
                                    name: 'homePhone',
                                    itemId: 'homePhone',
                                    vtype: 'phone',
                                    maxLength: 20,
                                    enforceMaxLength: true
                                }, {
                                    fieldLabel: '家庭地址',
                                    name: 'addr',
                                    maxLength: 50,
                                    enforceMaxLength: true,
                                    maxLengthText: "家庭地址长度不能超过50个字符！"
                                } ]
                            } ]
                        }, {
                            fieldLabel: '爱好',
                            width: 575,
                            name: 'hobby',
                            maxLength: 50,
                            margin: '0 0 5 5',
                            enforceMaxLength: true,
                            maxLengthText: "爱好长度不能超过50个字符！"
                        }, {
                            fieldLabel: '备注',
                            width: 575,
                            xtype: 'textareafield',
                            maxLength: 1024,
                            margin: '5 0 5 5',
                            enforceMaxLength: true,
                            maxLengthText: "备注长度不能超过1024个字符！",
                            name: 'descriptions',
                            itemId: 'descriptions'
                        } ]
                    }, {
                        cls: 'x-plain',
                        title: '教育经历',
                        layout: 'fit',
                        xtype: 'htmleditor',
                        // enforceMaxLength : true,
                        maxLength: 1024,
                        maxLengthText: "教育经历长度不能超过1024个字符！",
                        name: 'eduBackground',
                        itemId: 'eduBackground',
                        htmlEncode: false
                    }, {
                        cls: 'x-plain',
                        title: '工作简历',
                        layout: 'fit',
                        xtype: 'htmleditor',
                        name: 'jobResume',
                        itemId: 'jobResume',
                        htmlEncode: false
                    }, {
                        title: '家庭主要成员',
                        bodyStyle: 'padding:0px',
                        xtype: 'grid',
                        id: 'Family',
                        height: 290,
                        multiSelect: true,
                        frame: true,
                        store: 'contactManagement.contactProfiles.ContactFamilys',
                        loadMask: false,
                        defaultType: 'textfield',
                        selModel: Ext.create('Ext.selection.CheckboxModel', {
                            listeners: {
                                scope: this,
                                selectionchange: function(sm, selections) {
                                    Ext.getCmp('deleteFamilyBtn').setDisabled(selections.length == 0);
                                    Ext.getCmp('editFamilyBtn').setDisabled(selections.length != 1);
                                }
                            }
                        }),
                        columns: [ Ext.create('Ext.grid.RowNumberer', {
                            border: false,
                            text: '行号',
                            width: 35,
                            renderer: function(value, metadata, record, rowIndex) {
                                return rowIndex + 1;
                            }
                        }), {
                            header: '关系',
                            dataIndex: 'familyRelation',
                            width: 44,
                            renderer: this.rendererValue,
                            sortable: true
                        }, {
                            header: '姓名',
                            dataIndex: 'familyName',
                            width: 70,
                            renderer: this.rendererValue,
                            sortable: true
                        }, {
                            header: '出生日期',
                            dataIndex: 'birthday',
                            width: 90,
                            align: 'center',
                            sortable: true
                        }, {
                            header: '政治面貌',
                            width: 64,
                            dataIndex: 'political',
                            renderer: this.rendererValue,
                            sortable: true
                        }, {
                            header: '工作单位',
                            width: 118,
                            dataIndex: 'company',
                            renderer: this.rendererValue,
                            sortable: true
                        }, {
                            header: '职务',
                            dataIndex: 'job',
                            width: 86,
                            renderer: this.rendererValue,
                            sortable: true
                        }, {
                            header: '备注',
                            dataIndex: 'descriptions',
                            width: 118,
                            sortable: false,
                            renderer: this.rendererValue
                        } ],
                        tbar: [ {
                            // 占位符
                            xtype: 'tbfill'
                        }, {
                            xtype: 'button',
                            text: '添加',
                            tooltip: '添加',
                            action: 'addFamily'
                        }, '-', {
                            xtype: 'button',
                            text: '编辑',
                            id: 'editFamilyBtn',
                            disabled: true,
                            tooltip: '编辑',
                            action: 'editFamily'
                        }, '-', {
                            xtype: 'button',
                            text: '删除',
                            id: 'deleteFamilyBtn',
                            disabled: true,
                            tooltip: '删除',
                            action: 'deleteFamilyOrSocial'
                        } ]
                    }, {
                        title: '社会关系',
                        bodyStyle: 'padding:0px',
                        defaultType: 'textfield',
                        xtype: 'grid',
                        id: 'Social',
                        height: 290,
                        multiSelect: true,
                        frame: true,
                        store: 'contactManagement.contactProfiles.ContactSocials',
                        loadMask: false,
                        defaultType: 'textfield',
                        selModel: Ext.create('Ext.selection.CheckboxModel', {
                            listeners: {
                                scope: this,
                                selectionchange: function(sm, selections) {
                                    Ext.getCmp('deleteSocialBtn').setDisabled(selections.length == 0);
                                    Ext.getCmp('editSocialBtn').setDisabled(selections.length != 1);

                                }
                            }
                        }),
                        columns: [ Ext.create('Ext.grid.RowNumberer', {
                            border: false,
                            text: '行号',
                            width: 35,
                            renderer: function(value, metadata, record, rowIndex) {
                                return rowIndex + 1;
                            }
                        }), {
                            header: '关系',
                            dataIndex: 'socialRelation',
                            renderer: this.rendererValue,
                            width: 44,
                            sortable: true
                        }, {
                            header: '姓名',
                            dataIndex: 'socialName',
                            renderer: this.rendererValue,
                            width: 70,
                            sortable: true
                        }, {
                            header: '出生日期',
                            dataIndex: 'birthday',
                            align: 'center',
                            width: 90,
                            sortable: true
                        }, {
                            header: '政治面貌',
                            dataIndex: 'political',
                            renderer: this.rendererValue,
                            width: 64,
                            sortable: true
                        }, {
                            header: '工作单位',
                            dataIndex: 'company',
                            renderer: this.rendererValue,
                            width: 118,
                            sortable: true
                        }, {
                            header: '职务',
                            dataIndex: 'job',
                            renderer: this.rendererValue,
                            width: 86,
                            sortable: true
                        }, {
                            header: '备注',
                            dataIndex: 'descriptions',
                            renderer: this.rendererValue,
                            width: 118,
                            sortable: false
                        } ],
                        tbar: [ {
                            // 占位符
                            xtype: 'tbfill'
                        }, {
                            xtype: 'button',
                            text: '添加',
                            tooltip: '添加',
                            action: 'addSocial'
                        }, '-', {
                            xtype: 'button',
                            text: '编辑',
                            id: 'editSocialBtn',
                            disabled: true,
                            tooltip: '编辑',
                            action: 'editSocial'
                        }, '-', {
                            xtype: 'button',
                            text: '删除',
                            id: 'deleteSocialBtn',
                            disabled: true,
                            tooltip: '删除',
                            action: 'deleteFamilyOrSocial'
                        } ]
                    } ]
                } ]
            } ]
        };
        this.buttons = [ {

            type: 'submit',
            text: '确定',
            action: 'addContactSubmitBtn'
        }, {
            xtype: 'button',
            itemId: 'resetform',
            action: 'addContactResetBtn'
        }, {
            xtype: 'button',
            text: '取消',
            action: 'addContactCancelBtn'
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        return Ext.String.htmlEncode(value);
    }
});
